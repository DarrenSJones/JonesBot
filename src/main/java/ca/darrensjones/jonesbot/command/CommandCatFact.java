package ca.darrensjones.jonesbot.command;

import java.awt.Color;
import java.util.Date;

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
 * @version 1.0.0 2020-12-09
 * @since 1.0.0 2020-11-24
 */
public class CommandCatFact extends AbstractCommand {

	public CommandCatFact(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "CatFact";
	}

	@Override
	public String getDescription() {
		return "Displays a random cat fact from https://catfact.ninja";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "catfact", "catfacts" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**" + bot.config.BOT_PREFIX + "catfact** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setDescription(getResponse(bot.config.CATFACT_HOST));
		eb.setFooter("Cat Fact", "http://www.nyan.cat/cats/original.gif");
		eb.setTimestamp(new Date().toInstant());
		eb.setColor(new Color(254, 119, 255));

		message.getChannel().sendMessage(eb.build()).queue();
	}

	public static String getResponse(String host) {
		try {
			String response = RequestUtils.getResponseBody(host + "/fact");
			JSONObject json = (JSONObject) new JSONParser().parse(response);
			return json.get("fact").toString();
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
			return "Cat Fact not found.";
		}
	}
}