package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-27
 * @since 1.0.0 2020-11-23
 */
public class CommandPing extends AbstractCommand {

	public CommandPing(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Ping";
	}

	@Override
	public String getDescription() {
		return "Pong!";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "ping", "p" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**%sping** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		message.getChannel().sendMessage("Pong!").queue();
	}
}