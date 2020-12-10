package ca.darrensjones.jonesbot.command.utilities;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import ca.darrensjones.jonesbot.handler.DataHandler;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.RequestUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-09
 * @since 1.0.0 2020-12-08
 */
public class Frinkiac {

	public static String getHelp(String prefix) {
		String s = "Blank commands will display a random image.";
		s += "\nEntering a quote will return an image associated with that quote.";
		s += "\n\"S00E00 0000\" can also be used to search for a specific episode and timestamp.";
		s += "\n**" + prefix + "s {text-search}**: Display an image using the search.";
		s += "\n**" + prefix + "s " + prefix + "d**: Display a random image, with detail.";
		s += "\n**" + prefix + "s " + prefix + "d {text-search}**: Display an image using the search, with detail.";
		s += "\n**" + prefix + "s " + prefix + "l**: Displays details for the last image posted.";
		s += "\n**" + prefix + "s " + prefix + "saved**: Displays a list of saved images with links.";
		s += "\n**" + prefix + "s " + prefix + "regex**: Displays a list of saved images with the regex that matches them.";
		s += "\nAny text entered on a new line will be added to the image as a caption.";
		return s;
	}

	public static EmbedBuilder process(Message message, String prefix, Color color, String host, List<OFrinkiacSaved> saved, HashMap<String, String[]> last) {

		boolean flagDetail = false;

		if (Pattern.compile(prefix + "saved\\s?").matcher(message.getContentDisplay().toLowerCase()).find()) {
			return Frinkiac.buildEmbedSaved(color, host, saved);
		} else if (Pattern.compile(prefix + "regex\\s?").matcher(message.getContentDisplay().toLowerCase()).find()) {
			return Frinkiac.buildEmbedRegex(color, host, saved);
		} else if (Pattern.compile(prefix + "l(ast)?\\s?").matcher(message.getContentDisplay().toLowerCase()).find()) {
			String[] l = last.get(message.getTextChannel().getId());
			return Frinkiac.buildEmbed(false, true, color, host, "[Last] " + l[0], l[1], null);
		} else if (Pattern.compile(prefix + "d(etail)?\\s?").matcher(message.getContentDisplay().toLowerCase()).find()) {
			flagDetail = true;
		}

		String query = message.getContentDisplay().replaceAll(prefix + "\\w+(\\s+)?", "").trim(); // Removes command and subcommands
		String caption = null;
		String title, request;
		String response = "";

		// Query is used for searching, anything on a new line is used for captioning
		if (query.indexOf("\n") > 0) {
			caption = query.substring(query.indexOf("\n")).trim();
			query = query.substring(0, query.indexOf("\n")).trim();
		}

		if (StringUtils.isBlank(query)) {
			title = "Random Search";
			request = host + "/api/random";
		} else if (Frinkiac.hasSaved(saved, query)) {
			OFrinkiacSaved s = Frinkiac.getSaved(saved, query);
			title = String.format("Saved: \"%s\"", s.name);
			request = Frinkiac.buildRequestUrlKeyTimestamp(host, s.key, s.timestamp);
		} else if (Frinkiac.isKeyTimestamp(query)) {
			title = String.format("Timestamp: \"%s\"", query);
			request = Frinkiac.buildRequestUrlKeyTimestamp(host, query.split("\\s+")[0], query.split("\\s+")[1]);
		} else {
			title = String.format("Search: \"%s\"", query);
			request = host + "/api/search?q=" + query.trim().replaceAll("\\s+", "%20");
		}

		response = getResponse(host, request);

		if (StringUtils.isNotBlank(response)) {
			DataHandler.setLast(last, message.getTextChannel().getId(), title, response);
			return Frinkiac.buildEmbed(true, flagDetail, color, host, title, response, caption);
		} else {
			return new EmbedBuilder().setTitle(title, host).setDescription("Response not found, try another search.");
		}
	}

	public static EmbedBuilder buildEmbed(boolean flagImage, boolean flagDetail, Color color, String host, String title, String response, String caption) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(color);
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(response);
//			String key = ((JSONObject) json.get("Episode")).get("Key").toString();
			String season = ((JSONObject) json.get("Episode")).get("Season").toString();
			String episodeNumber = ((JSONObject) json.get("Episode")).get("EpisodeNumber").toString();
			String episode = ((JSONObject) json.get("Frame")).get("Episode").toString();
			String timestamp = ((JSONObject) json.get("Frame")).get("Timestamp").toString();
			String epTitle = ((JSONObject) json.get("Episode")).get("Title").toString();
			String image = String.format("%s/meme/%s/%s.jpg", host, episode, timestamp);
			if (StringUtils.isNotBlank(caption)) image += "?b64lines=" + new Base64().encodeAsString(caption.getBytes());
			String url = String.format("%s/caption/%s/%s", host, episode, timestamp);
			String subtitles = "\u200B";
			for (Object obj : (JSONArray) json.get("Subtitles")) subtitles += ((JSONObject) obj).get("Content").toString() + "\n";
			String minutes = StringUtils.leftPad(Integer.toString((int) Math.max(0, Math.floor((Integer.parseInt(timestamp) / 1000) / 60))), 2, "0");
			String seconds = StringUtils.leftPad(Integer.toString((int) Math.max(0, (Integer.parseInt(timestamp) / 1000) % 60)), 2, "0");
			String description = String.format("Season %s / Episode %s (%s:%s)", season, episodeNumber, minutes, seconds);

			if (flagImage) eb.setImage(image);
			if (flagDetail) {
				eb.setTitle(title, url);
				eb.setDescription(String.format("\"%s\"", epTitle));
				eb.addField(description, subtitles, false);
			}
		} catch (Exception e) {
			eb.setTitle("Error: " + title, host);
			eb.setDescription("Error parsing response, please contact your administrator.");
		}
		return eb;
	}

	public static EmbedBuilder buildEmbedSaved(Color color, String host, List<OFrinkiacSaved> list) {
		String desc = "Contact your Admin for additions:";
		for (OFrinkiacSaved saved : list) desc += String.format("\n%s [%s]", saved.name, String.format("%s/caption/%s/%s", host, saved.key, saved.timestamp));
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Saved List");
		eb.setDescription(desc);
		eb.setColor(color);
		return eb;
	}

	public static EmbedBuilder buildEmbedRegex(Color color, String host, List<OFrinkiacSaved> list) {
		String desc = "Contact your Admin for additions:";
		for (OFrinkiacSaved saved : list) desc += String.format("\n%s: %s", saved.name, saved.regex);
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Saved List: Regex");
		eb.setDescription(desc);
		eb.setColor(color);
		return eb;
	}

	public static String getResponse(String host, String request) {
		String response = "";
		try {
			String resp = RequestUtils.getResponseBody(request);
			if (request.contains("/api/search?q=") && StringUtils.isNotBlank(resp) && !resp.equals("[]")) { // Query Search needs an extra request
				JSONObject json = (JSONObject) ((JSONArray) new JSONParser().parse(resp)).get(0);
				request = Frinkiac.buildRequestUrlKeyTimestamp(host, json.get("Episode").toString(), json.get("Timestamp").toString());
				resp = RequestUtils.getResponseBody(request);
			}
			if (StringUtils.isNotBlank(resp) && !resp.equals("[]") && !resp.equals("Not Found\n")) response = resp;

		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		return response;
	}

	public static boolean hasSaved(List<OFrinkiacSaved> list, String content) {
		for (OFrinkiacSaved saved : list) {
			String regex = "(?=(\\W|^)" + saved.regex + "(\\W|$))";
			if (Pattern.compile(regex).matcher(content.toLowerCase()).find()) return true;
		}
		return false;
	}

	public static OFrinkiacSaved getSaved(List<OFrinkiacSaved> list, String content) {
		for (OFrinkiacSaved saved : list) {
			String regex = "(?=(\\W|^)" + saved.regex + "(\\W|$))";
			if (Pattern.compile(regex).matcher(content.toLowerCase()).find()) return saved;
		}
		return null;
	}

	public static boolean isKeyTimestamp(String query) {
		if (Pattern.compile("^(s\\d{1,2}e\\d{1,2}|movie)\\s+\\d+$").matcher(query.toLowerCase()).find()) return true;
		return false;
	}

	/**
	 * Assumes that Key/Timestamp are in the correct format.
	 */
	public static String buildRequestUrlKeyTimestamp(String host, String key, String timestamp) {
		if (key.equalsIgnoreCase("movie")) {
			key = "Movie"; // Must have this casing
		} else {
			key = key.replace("s", "S").replace("e", "E"); // Must be uppercase
			if (Pattern.compile("^S\\dE").matcher(key).find()) key = "S0" + key.substring(1);
			if (Pattern.compile("E\\d$").matcher(key).find()) key = key.substring(0, 4) + "0" + key.substring(4);
		}
		return String.format("%s/api/caption?e=%s&t=%s", host, key, timestamp);
	}
}