package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-08
 * @since 1.0.0 2020-12-08
 */
public class CommandFuturama extends AbstractCommand {

	public CommandFuturama(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Futurama";
	}

	@Override
	public String getDescription() {
		return "Returns an image from http://morbotron.com";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "futurama", "f" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**%sfuturama** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		message.getChannel().sendMessage("Command: Futurama").queue();
	}
}