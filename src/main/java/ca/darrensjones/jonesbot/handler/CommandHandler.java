package ca.darrensjones.jonesbot.handler;

import java.util.ArrayList;
import java.util.List;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.AbstractCommand;
import ca.darrensjones.jonesbot.command.CommandHelp;
import ca.darrensjones.jonesbot.command.CommandPing;
import ca.darrensjones.jonesbot.command.CommandReaction;
import ca.darrensjones.jonesbot.log.LogUtils;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-24
 * @since 1.0.0 2020-11-22
 */
public class CommandHandler {

	private final Bot bot;
	private static List<AbstractCommand> commands;

	public CommandHandler(Bot bot) {
		this.bot = bot;
		setCommands();
	}

	public void process(Message message) {
		Reporter.info("Start CommandHandler. " + LogUtils.getMessageInfo(message));

		AbstractCommand c = getCommand(message.getContentDisplay().split("\\s+")[0].substring(bot.config.BOT_PREFIX.length()));
		if (c != null) c.execute(bot, message);
		else Reporter.info("Command not found in list.");

		Reporter.info("End CommandHandler.");
	}

	public boolean isCommand(String content) {
		if (content.startsWith(bot.config.BOT_PREFIX)) return true;
		else return false;
	}

	public List<AbstractCommand> getCommands() {
		return commands;
	}

	public AbstractCommand getCommand(String commandName) {
		for (AbstractCommand c : commands) {
			for (String t : c.getTriggers()) {
				if (t.equalsIgnoreCase(commandName)) return c;
			}
		}
		return null;
	}

	public void setCommands() {
		commands = new ArrayList<AbstractCommand>();
		commands.add(new CommandHelp());
		commands.add(new CommandPing());
		commands.add(new CommandReaction());
	}
}