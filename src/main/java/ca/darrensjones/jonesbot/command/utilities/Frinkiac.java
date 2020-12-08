package ca.darrensjones.jonesbot.command.utilities;

import java.awt.Color;
import java.util.List;
import java.util.regex.Pattern;

import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import net.dv8tion.jda.api.EmbedBuilder;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-08
 * @since 1.0.0 2020-12-08
 */
public class Frinkiac {

	public static EmbedBuilder buildEmbedSaved(String host, List<OFrinkiacSaved> list, Color color) {
		String desc = "Contact your Admin for additions:";
		for (OFrinkiacSaved saved : list) desc += String.format("\n%s [%s]", saved.name, String.format("%s/caption/%s/%s", host, saved.key, saved.timestamp));

		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Saved List");
		eb.setDescription(desc);
		eb.setColor(color);
		return eb;
	}

	public static EmbedBuilder buildEmbedRegex(String host, List<OFrinkiacSaved> list, Color color) {
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