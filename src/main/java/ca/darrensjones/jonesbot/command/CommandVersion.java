package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
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
		
		
		message.getChannel().sendMessage("Pong!").queue();
	}
}