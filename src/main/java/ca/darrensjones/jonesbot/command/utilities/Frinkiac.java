package ca.darrensjones.jonesbot.command.utilities;

import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import ca.darrensjones.jonesbot.handler.DataHandler;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.RequestUtils;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-24
 * @since   1.0.0 2020-12-08
 */
public class Frinkiac {

	public static String getHelp(String prefix) {
		String s = "Blank commands will display a random image.";
		s += "\nEntering a quote will return an image associated with that quote.";
		s += "\n\"S00E00 0000\" can also be used to search for a specific episode and timestamp.";
		s += "\n**" + prefix + "s {text-search}**: Display an image using the search.";
		s += "\n**" + prefix + "s " + prefix + "d**: Display a random image, with detail.";
		s += "\n**" + prefix + "s " + prefix
				+ "d {text-search}**: Display an image using the search, with detail.";
		s += "\n**" + prefix + "s " + prefix + "l**: Displays details for the last image posted.";
		s += "\n**" + prefix + "s " + prefix
				+ "saved**: Displays a list of saved images with links.";
		s += "\n**" + prefix + "s " + prefix
				+ "regex**: Displays a list of saved images with the regex that matches them.";
		s += "\nAny text entered on a new line will be added to the image as a caption.";
		return s;
	}

	public static EmbedBuilder process(Message message, String prefix, Color color, String host,
			List<OFrinkiacSaved> saved, HashMap<String, String[]> last) {
		return process(message.getTextChannel().getId(), message.getContentDisplay(), prefix, color,
				host, saved, last);
	}

	public static EmbedBuilder process(String textChannelId, String content, String prefix,
			Color color, String host, List<OFrinkiacSaved> savedList,
			HashMap<String, String[]> last) {

		boolean flagDetail = false;

		if (Pattern.compile(prefix + "saved\\s?").matcher(content.toLowerCase()).find()) {
			Reporter.info("Posting Frinkiac saved list.");
			return Frinkiac.buildEmbedSaved(color, host, savedList);
		} else if (Pattern.compile(prefix + "regex\\s?").matcher(content.toLowerCase()).find()) {
			Reporter.info("Posting Frinkiac regex list.");
			return Frinkiac.buildEmbedRegex(color, host, savedList);
		} else if (Pattern.compile(prefix + "l(ast)?\\s?").matcher(content.toLowerCase()).find()) {
			Reporter.info("Posting details from last Frinkiac response.");
			String[] l = last.get(textChannelId);
			return Frinkiac.buildEmbed(false, true, color, host, "[Last] " + l[0], l[1], null);
		}

		if (Pattern.compile(prefix + "d(etail)?\\s?").matcher(content.toLowerCase()).find()) {
			flagDetail = true;
		}

		String caption = null;
		String title, request;
		String response = "";

		// Removes command and subcommands
		String query = content.replaceAll(prefix + "\\w+(\\s+)?", "").trim();

		// Query is used for searching, anything on a new line is used for captioning
		if (query.indexOf("\n") > 0) {
			caption = query.substring(query.indexOf("\n")).trim();
			query = query.substring(0, query.indexOf("\n")).trim();
		}

		if (StringUtils.isBlank(query)) {
			title = "Random Search";
			request = String.format("%s/api/random", host);
		} else if (Frinkiac.hasSaved(savedList, query)) {
			OFrinkiacSaved saved = Frinkiac.getSaved(savedList, query);
			String key = saved.key;
			String timestamp = saved.timestamp;
			title = String.format("Saved: \"%s\"", saved.name);
			request = Frinkiac.buildRequestUrl(host, key, timestamp);
		} else if (Frinkiac.isKeyTimestamp(query)) {
			String key = query.split("\\s+")[0];
			String timestamp = query.split("\\s+")[1];
			title = String.format("Timestamp: \"%s\"", query);
			request = Frinkiac.buildRequestUrl(host, key, timestamp);
		} else {
			title = String.format("Search: \"%s\"", query);
			request = String.format("%s/api/search?q=%s", host, query.replaceAll("\\s+", "%20"));
		}

		Reporter.info(String.format("title:[%s] request:[%s]", title, request));
		response = getResponse(host, request);

		if (StringUtils.isNotBlank(response)) {
			DataHandler.setLast(last, textChannelId, title, response);
			return Frinkiac.buildEmbed(true, flagDetail, color, host, title, response, caption);
		} else {
			return new EmbedBuilder().setTitle(title, host)
					.setDescription("Response not found, try another search.");
		}
	}

	public static EmbedBuilder buildEmbed(boolean flagImage, boolean flagDetail, Color color,
			String host, String title, String response, String caption) {

		EmbedBuilder eb = new EmbedBuilder().setColor(color);

		JSONObject obj = new JSONObject();
		try {
			obj = (JSONObject) new JSONParser().parse(response);
		} catch (Exception e) {
			eb.setTitle("Error: " + title, host);
			eb.setDescription("Error parsing response, please contact your administrator.");
			return eb;
		}

		String season = ((JSONObject) obj.get("Episode")).get("Season").toString();
		String episode = ((JSONObject) obj.get("Episode")).get("EpisodeNumber").toString();
		String key = ((JSONObject) obj.get("Frame")).get("Episode").toString();
		String timestamp = ((JSONObject) obj.get("Frame")).get("Timestamp").toString();
		String episodeTitle = ((JSONObject) obj.get("Episode")).get("Title").toString();

		String subtitles = "\u200B";
		for (Object o : (JSONArray) obj.get("Subtitles")) {
			subtitles += ((JSONObject) o).get("Content").toString() + "\n";
		}

		String image = String.format("%s/meme/%s/%s.jpg", host, key, timestamp);
		if (StringUtils.isNotBlank(caption)) {
			image += "?b64lines=" + new Base64().encodeAsString(caption.getBytes());
		}

		int minutes = (int) Math.max(Math.floor(Integer.parseInt(timestamp) / 1000) / 60, 0);
		int seconds = (int) Math.max(Integer.parseInt(timestamp) / 1000 % 60, 0);
		String description = String.format("Season %s / Episode %s (%s:%s)", season, episode,
				StringUtils.leftPad(Integer.toString(minutes), 2, "0"),
				StringUtils.leftPad(Integer.toString(seconds), 2, "0"));

		if (flagImage) {
			eb.setImage(image);
		}
		if (flagDetail) {
			eb.setTitle(title, String.format("%s/caption/%s/%s", host, key, timestamp));
			eb.setDescription(String.format("\"%s\"", episodeTitle));
			eb.addField(description, subtitles, false);
		}

		return eb;
	}

	public static EmbedBuilder buildEmbedSaved(Color color, String host,
			List<OFrinkiacSaved> list) {
		String description = "Contact your Admin for additions:";
		for (OFrinkiacSaved saved : list) {
			String url = String.format("%s/caption/%s/%s", host, saved.key, saved.timestamp);
			description += String.format("\n%s [%s]", saved.name, url);
		}
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Saved List");
		eb.setDescription(description);
		eb.setColor(color);
		return eb;
	}

	public static EmbedBuilder buildEmbedRegex(Color color, String host,
			List<OFrinkiacSaved> list) {
		String description = "Contact your Admin for additions:";
		for (OFrinkiacSaved saved : list) {
			description += String.format("\n%s: %s", saved.name, saved.regex);
		}
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Saved List: Regex");
		eb.setDescription(description);
		eb.setColor(color);
		return eb;
	}

	public static String getResponse(String host, String request) {
		String response = "";
		try {
			response = RequestUtils.getResponseBody(request);
			Reporter.info("Frinkiac response found.");
			if (request.contains("/api/search?q=")) { // Query Search needs an extra request
				JSONObject obj = (JSONObject) ((JSONArray) new JSONParser().parse(response)).get(0);
				String key = obj.get("Episode").toString();
				String timestamp = obj.get("Timestamp").toString();
				request = Frinkiac.buildRequestUrl(host, key, timestamp);
				response = RequestUtils.getResponseBody(request);
				Reporter.info("Frinkiac query response also found.");
			}
		} catch (Exception e) {
			Reporter.error("Frinkiac response not found.");
			return "";
		}
		return response;
	}

	public static boolean hasSaved(List<OFrinkiacSaved> list, String content) {
		for (OFrinkiacSaved saved : list) {
			String regex = "(?=(\\W|^)(" + saved.regex + ")(\\W|$))";
			if (Pattern.compile(regex).matcher(content.toLowerCase()).find()) {
				return true;
			}
		}
		return false;
	}

	public static OFrinkiacSaved getSaved(List<OFrinkiacSaved> list, String content) {
		for (OFrinkiacSaved saved : list) {
			String regex = "(?=(\\W|^)(" + saved.regex + ")(\\W|$))";
			if (Pattern.compile(regex).matcher(content.toLowerCase()).find()) {
				return saved;
			}
		}
		return null;
	}

	public static boolean isKeyTimestamp(String query) {
		String regex = "^(s\\d{1,2}e\\d{1,2}|movie)\\s+\\d+$";
		if (Pattern.compile(regex).matcher(query.toLowerCase()).find()) {
			return true;
		}
		return false;
	}

	/** Assumes that Key/Timestamp are in the correct format. */
	public static String buildRequestUrl(String host, String key, String timestamp) {
		if (key.equalsIgnoreCase("movie")) {
			key = "Movie"; // Must have this casing
		} else {
			key = key.replace("s", "S").replace("e", "E"); // Must be uppercase
			if (Pattern.compile("^S\\dE").matcher(key).find()) {
				key = "S0" + key.substring(1);
			}
			if (Pattern.compile("E\\d$").matcher(key).find()) {
				key = key.substring(0, 4) + "0" + key.substring(4);
			}
		}
		return String.format("%s/api/caption?e=%s&t=%s", host, key, timestamp);
	}
}