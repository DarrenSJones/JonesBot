package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.command.utilities.Version;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.0 2020-12-22
 * @since 1.1.0 2020-12-22
 */
public class CommandRoll extends AbstractCommand {

	public CommandRoll(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Roll";
	}

	@Override
	public String getDescription() {
		return "Roll some dice!";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "roll", "r" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**" + bot.config.BOT_PREFIX + "roll** Rolls a 6-sided die.";
	}

	@Override
	public void execute(Message message) {
	}
}