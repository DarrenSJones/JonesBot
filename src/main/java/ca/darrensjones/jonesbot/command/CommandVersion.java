package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
//import ca.darrensjones.jonesbot.db.controller.CVersion;
//import ca.darrensjones.jonesbot.db.model.OVersion;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-14
 * @since 1.0.0 2020-12-14
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
		return "**" + bot.config.BOT_PREFIX + "version** Displays the current version with changes.";
	}

	@Override
	public void execute(Message message) {
		String current = bot.config.BOT_VERSION;
//		String[] v = current.split("\\.");
//		OVersion version = CVersion.getVersion(v[0], v[1], v[2]);
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Current Version: " + current);
//		eb.setDescription(version.date.toString() + ": " + version.changes);
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}