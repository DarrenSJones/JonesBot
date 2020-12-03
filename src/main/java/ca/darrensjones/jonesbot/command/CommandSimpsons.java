package ca.darrensjones.jonesbot.command;

import java.awt.Color;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.RequestUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-28
 * @since 1.0.0 2020-11-28
 */
public class CommandSimpsons extends AbstractCommand {

	public CommandSimpsons(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Simpsons";
	}

	@Override
	public String getDescription() {
		return "Returns an image from http://frinkiac.com";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "simpsons", "s" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**%ssimpsons** " + getDescription();
	}

	@Override
	public void execute(Message message) {

		String baseUrl = "https://frinkiac.com";

		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(new Color(254, 217, 15));

//		boolean flagSaved = Pattern.compile(bot.config.BOT_PREFIX + "saved?\\s?").matcher(message.getContentDisplay().toLowerCase()).find();
//		boolean flagRegex = Pattern.compile(bot.config.BOT_PREFIX + "regex?\\s?").matcher(message.getContentDisplay().toLowerCase()).find();
//		boolean flagLast = Pattern.compile(bot.config.BOT_PREFIX + "l(ast)?\\s?").matcher(message.getContentDisplay().toLowerCase()).find();
		boolean flagDetail = Pattern.compile(bot.config.BOT_PREFIX + "d(etail)?\\s?").matcher(message.getContentDisplay().toLowerCase()).find();

		String content = message.getContentDisplay().replaceAll(bot.config.BOT_PREFIX + "\\w+(\\s+)?", "").trim();
		String query, caption;
		String title, request, response;

		// Query is used for searching, anything on a new line is used for captioning
		if (content.indexOf("\n") > 0) {
			query = content.substring(0, content.indexOf("\n")).trim();
			caption = content.substring(content.indexOf("\n"), content.length()).trim();
		} else {
			query = content;
			caption = "";
		}

		if (StringUtils.isBlank(query)) {
			title = "Random Search";
			request = baseUrl + "/api/random";
//		} else if (savedList.hasSaved(query)) {
//			request = savedList.getSaved(query).getRequestApi();
		} else if (isKeyTimestamp(query)) {
			title = String.format("Timestamp: \"%s\"", query);
			request = buildRequestUrlKeyTimestamp(baseUrl, query.split("\\s+")[0], query.split("\\s+")[1]);
		} else {
			title = String.format("Search: \"%s\"", query);
			request = baseUrl + "/api/search?q=" + query.trim().replaceAll("\\s+", "%20");
		}

		try {
			response = RequestUtils.getResponseBody(request, bot.config.TEST);
			if (StringUtils.isBlank(response) || response.equals("[]")) {
				eb.setTitle(title, baseUrl);
				eb.setDescription("Response not found for: " + request);
				message.getChannel().sendMessage(eb.build()).queue();
				return;

			} else if (request.contains("api/search?q=")) { // Query Search needs an extra request
				JSONObject json = (JSONObject) ((JSONArray) new JSONParser().parse(response)).get(0);
				request = buildRequestUrlKeyTimestamp(baseUrl, json.get("Episode").toString(), json.get("Timestamp").toString());
				response = RequestUtils.getResponseBody(request, bot.config.TEST);
			}

			// Parses response for relevant info
			JSONObject json = (JSONObject) new JSONParser().parse(response);
			JSONObject ep = (JSONObject) json.get("Episode");
			String key = ep.get("Key").toString();
			String season = ep.get("Season").toString();
			String episodeNumber = ep.get("EpisodeNumber").toString();
			String episode = ((JSONObject) json.get("Frame")).get("Episode").toString();
			String timestamp = ((JSONObject) json.get("Frame")).get("Timestamp").toString();
			String epTitle = ep.get("Title").toString();
			String image = String.format("%s/meme/%s/%s.jpg", baseUrl, episode, timestamp);
			if (StringUtils.isNotBlank(caption)) image += "?b64lines=" + new Base64().encodeAsString(caption.getBytes());
			String url = String.format("%s/caption/%s/%s", baseUrl, episode, timestamp);
			String subtitles = "\u200B";
			for (Object obj : (JSONArray) json.get("Subtitles")) subtitles += ((JSONObject) obj).get("Content").toString() + "\n";
			int t = Integer.parseInt(timestamp) / 1000;
			String minutes = StringUtils.leftPad(Integer.toString((int) Math.max(0, Math.floor(t / 60))), 2, "0");
			String seconds = StringUtils.leftPad(Integer.toString((int) Math.max(0, t % 60)), 2, "0");
			String description = String.format("Season %s / Episode %s (%s:%s)", season, episodeNumber, minutes, seconds);
			Reporter.info(String.format("Key:[%s] Timestamp:[%s]", key, timestamp));

			eb.setImage(image);
			if (flagDetail) {
				eb.setTitle(title, url);
				eb.setDescription(String.format("\"%s\"", epTitle));
				eb.addField(description, subtitles, false);
			}

			Reporter.info("Embed Built");
			message.getChannel().sendMessage(eb.build()).queue();

		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
	}

	public static boolean isKeyTimestamp(String query) {
		if (Pattern.compile("^(s\\d{1,2}e\\d{1,2}|movie)\\s+\\d+$").matcher(query.toLowerCase()).find()) return true;
		return false;
	}

	public static String buildRequestUrlKeyTimestamp(String host, String key, String timestamp) {
		if (key.equalsIgnoreCase("movie")) {
			key = "Movie";
		} else {
			key = key.replace("s", "S").replace("e", "E"); // Must be uppercase
			if (Pattern.compile("^S\\dE").matcher(key).find()) key = "S0" + key.substring(1);
			if (Pattern.compile("E\\d$").matcher(key).find()) key = key.substring(0, 4) + "0" + key.substring(4);
		}
		return host + "/api/caption?e=" + key + "&t=" + timestamp;
	}
}