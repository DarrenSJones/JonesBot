package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-23
 */
public class CommandPing extends AbstractCommand {

	public CommandPing() {
		super();
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
	public boolean isVisible() {
		return true;
	}

	@Override
	public String getHelp() {
		return "**%sping** " + getDescription();
	}

	@Override
	public void execute(Bot bot, Message message) {
		message.getChannel().sendMessage("Pong!").queue();
	}
}