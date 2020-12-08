package ca.darrensjones.jonesbot.command.utilities;

import java.awt.Color;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import net.dv8tion.jda.api.EmbedBuilder;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-08
 * @since 1.0.0 2020-12-08
 */
public class Frinkiac {

	public static EmbedBuilder buildEmbed(boolean flagImage, boolean flagDetail, Color color, String host, String title, String response, String caption) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(color);
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(response);
			JSONObject ep = (JSONObject) json.get("Episode");
//			String key = ep.get("Key").toString();
			String season = ep.get("Season").toString();
			String episodeNumber = ep.get("EpisodeNumber").toString();
			String episode = ((JSONObject) json.get("Frame")).get("Episode").toString();
			String timestamp = ((JSONObject) json.get("Frame")).get("Timestamp").toString();
			String epTitle = ep.get("Title").toString();
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

	public static boolean hasSubcommandSaved(String prefix, String content) {
		if (Pattern.compile(prefix + "saved\\s?").matcher(content.toLowerCase()).find()) return true;
		return false;
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

	public static boolean hasSubcommandRegex(String prefix, String content) {
		if (Pattern.compile(prefix + "regex\\s?").matcher(content.toLowerCase()).find()) return true;
		return false;
	}

	public static EmbedBuilder buildEmbedRegex(Color color, String host, List<OFrinkiacSaved> list) {
		String desc = "Contact your Admin for additions:";
		for (OFrinkiacSaved saved : list) desc += String.format("\n%s: %s", saved.name, saved.regex);
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Saved List");
		eb.setDescription(desc);
		eb.setColor(color);
		return eb;
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