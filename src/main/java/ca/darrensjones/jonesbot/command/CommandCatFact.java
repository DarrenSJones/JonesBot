package ca.darrensjones.jonesbot.command;

import java.awt.Color;
import java.util.Date;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.log.Reporter;
import ca.darrensjones.jonesbot.utilities.RequestUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-26
 * @since 1.0.0 2020-11-24
 */
public class CommandCatFact extends AbstractCommand {

	public CommandCatFact() {
		super();
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
		return "**%scatfact** " + getDescription();
	}

	@Override
	public void execute(Bot bot, Message message) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setDescription(getResponse(bot.config.HOST_CATFACT));
		eb.setFooter("Cat Fact", "http://www.nyan.cat/cats/original.gif");
		eb.setTimestamp(new Date().toInstant());
		eb.setColor(new Color(254, 119, 255));

		message.getChannel().sendMessage(eb.build()).queue();
	}

	public String getResponse(String host) {
		try {
			return RequestUtils.getResponseBodyField(host + "/fact", "fact");
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
			return "Cat Fact not found.";
		}
	}
}