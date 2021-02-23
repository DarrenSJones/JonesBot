package ca.darrensjones.jonesbot.command;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-23
 * @since   1.1.2 2020-12-29
 */
public class CommandGame extends AbstractCommand {

	public CommandGame(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Game";
	}

	@Override
	public String getDescription() {
		return "Starts a new game!";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "game" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.HIDDEN;
	}

	@Override
	public String getHelp() {
		return "**" + bot.getPrefix() + "game** " + getDescription();
	}

	@Override
	public void execute(Message message) {
		bot.gameHandler.process(message);
	}
}