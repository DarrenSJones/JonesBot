package ca.darrensjones.jonesbot.handler;

import java.util.ArrayList;
import java.util.List;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.AbstractCommand;
import ca.darrensjones.jonesbot.command.CommandPing;
import ca.darrensjones.jonesbot.command.CommandReaction;
import ca.darrensjones.jonesbot.log.LogUtils;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-23
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

		String content = message.getContentDisplay().toLowerCase();
		String com = content.split("\\s+")[0].substring(bot.config.BOT_PREFIX.length());
		for (AbstractCommand c : commands) {
			if (c.getName().equalsIgnoreCase(com)) c.execute(message);
		}

		Reporter.info("End CommandHandler.");
	}

	public boolean isCommand(String content) {
		if (content.startsWith(bot.config.BOT_PREFIX)) return true;
		else return false;
	}

	public void setCommands() {
		commands = new ArrayList<AbstractCommand>();
		commands.add(new CommandPing());
		commands.add(new CommandReaction());
	}
}