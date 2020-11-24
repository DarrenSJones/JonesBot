package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-24
 */
public class CommandCowbell extends AbstractCommand {

	public CommandCowbell() {
		super();
	}

	@Override
	public String getName() {
		return "Cowbell";
	}

	@Override
	public String getDescription() {
		return "When you need more of it";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "cowbell" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.HIDDEN;
	}

	@Override
	public String getHelp() {
		return "**%scowbell** " + getDescription();
	}

	@Override
	public void execute(Bot bot, Message message) {
		message.getChannel().sendMessage("https://media.giphy.com/media/3o6ozscsz4jRX7xuQE/giphy.gif").queue();
//		message.getChannel().sendFile(new File("src/main/resources/images/cowbell.gif")).queue();
	}
}