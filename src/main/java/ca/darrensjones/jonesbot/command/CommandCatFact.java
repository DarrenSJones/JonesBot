package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.RequestUtils;
import java.awt.Color;
import java.util.Date;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-11-24
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
		return "**" + bot.getPrefix() + "catfact** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setDescription(getResponse(bot.getConfig().HOST_CATFACT));
		eb.setFooter("Cat Fact", "http://www.nyan.cat/cats/original.gif");
		eb.setTimestamp(new Date().toInstant());
		eb.setColor(new Color(254, 119, 255));

		message.getChannel().sendMessage(eb.build()).queue();
	}

	public static String getResponse(String host) {
		try {
			String response = RequestUtils.getResponseBody(host + "/fact");
			JSONObject obj = (JSONObject) new JSONParser().parse(response);
			return obj.get("fact").toString();
		} catch (Exception e) {
			String message = "Cat Fact response not found.";
			Reporter.error(message);
			e.printStackTrace();
			return message;
		}
	}
}