package ca.darrensjones.jonesbot.command;

import java.awt.Color;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.command.utilities.Frinkiac;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.RequestUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-08
 * @since 1.0.0 2020-11-28
 */
public class CommandSimpsons extends AbstractCommand {

	private final Color color;
	private final String host;

	public CommandSimpsons(Bot bot) {
		super(bot);
		this.color = new Color(254, 217, 15);
		this.host = bot.config.SIMPSONS_HOST;
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

		if (Frinkiac.hasSubcommandSaved(bot.config.BOT_PREFIX, message.getContentDisplay())) {
			message.getChannel().sendMessage(Frinkiac.buildEmbedSaved(color, host, bot.dataHandler.simpsonsSaved).build()).queue();
			return;
		} else if (Frinkiac.hasSubcommandRegex(bot.config.BOT_PREFIX, message.getContentDisplay())) {
			message.getChannel().sendMessage(Frinkiac.buildEmbedRegex(color, host, bot.dataHandler.simpsonsSaved).build()).queue();
			return;
		} else if (Frinkiac.hasSubcommandLast(bot.config.BOT_PREFIX, message.getContentDisplay())) {
			String lastTitle = bot.dataHandler.simpsonsLast.get(message.getTextChannel().getId())[0];
			String lastResponse = bot.dataHandler.simpsonsLast.get(message.getTextChannel().getId())[1];
			message.getChannel().sendMessage(Frinkiac.buildEmbed(false, true, color, host, "[Last] " + lastTitle, lastResponse, "").build()).queue();
			return;
		}

		boolean flagDetail = Pattern.compile(bot.config.BOT_PREFIX + "d(etail)?\\s?").matcher(message.getContentDisplay().toLowerCase()).find();
		String content = message.getContentDisplay().replaceAll(bot.config.BOT_PREFIX + "\\w+(\\s+)?", "").trim(); // Removes subcommands
		String query, caption, title, request, response;

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
			request = host + "/api/random";
		} else if (Frinkiac.hasSaved(bot.dataHandler.simpsonsSaved, query)) {
			OFrinkiacSaved s = Frinkiac.getSaved(bot.dataHandler.simpsonsSaved, query);
			title = String.format("Saved: \"%s\"", s.name);
			request = String.format("%s/api/caption?e=%s&t=%s", host, s.key, s.timestamp);
		} else if (Frinkiac.isKeyTimestamp(query)) {
			title = String.format("Timestamp: \"%s\"", query);
			request = Frinkiac.buildRequestUrlKeyTimestamp(host, query.split("\\s+")[0], query.split("\\s+")[1]);
		} else {
			title = String.format("Search: \"%s\"", query);
			request = host + "/api/search?q=" + query.trim().replaceAll("\\s+", "%20");
		}

		try {
			response = RequestUtils.getResponseBody(request, bot.config.TEST);
			if (StringUtils.isBlank(response) || response.equals("[]")) {
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle(title, host);
				eb.setDescription("Response not found for: " + request);
				message.getChannel().sendMessage(eb.build()).queue();
				return;

			} else if (request.contains("api/search?q=")) { // Query Search needs an extra request
				JSONObject json = (JSONObject) ((JSONArray) new JSONParser().parse(response)).get(0);
				request = Frinkiac.buildRequestUrlKeyTimestamp(host, json.get("Episode").toString(), json.get("Timestamp").toString());
				response = RequestUtils.getResponseBody(request, bot.config.TEST);
			}

			bot.dataHandler.setLastSimpsons(message.getTextChannel().getId(), title, response);

			message.getChannel().sendMessage(Frinkiac.buildEmbed(true, flagDetail, color, host, title, response, caption).build()).queue();

		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
	}
}