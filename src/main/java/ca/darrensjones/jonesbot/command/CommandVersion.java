package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.db.model.OVersion;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-12-14
 */
public class CommandVersion extends AbstractCommand {

	public CommandVersion(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Version";
	}

	@Override
	public String getDescription() {
		return "JonesBot Version Information";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "version", "v" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**" + bot.getPrefix() + "version** Displays current version information.";
	}

	@Override
	public void execute(Message message) {
		String description = "";
		for (OVersion v : bot.getConfig().VERSION_LIST) {
			description += "\n" + v.description;
		}

		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(String.format("Current Version: %s", bot.getConfig().VERSION));
		eb.setDescription(description);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}