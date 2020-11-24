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
public class CommandReload extends AbstractCommand {

	public CommandReload() {
		super();
	}

	@Override
	public String getName() {
		return "Reload";
	}

	@Override
	public String getDescription() {
		return "Reloads all lists from the SQL Database.";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "reload" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.OWNER;
	}

	@Override
	public String getHelp() {
		return "**%sreload** " + getDescription();
	}

	@Override
	public void execute(Bot bot, Message message) {
		if (!message.getAuthor().getId().equals(bot.config.BOT_OWNER_ID)) return;
		bot.commandHandler.setCommands();
		bot.reactionHandler.setList();
		message.getChannel().sendMessage("Commands and Reactions reloaded!").queue();
	}
}