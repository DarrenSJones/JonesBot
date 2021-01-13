package ca.darrensjones.jonesbot.handler;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.reflections.Reflections;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.log.LogUtils;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.2 2021-01-13
 * @since 1.0.0 2020-11-22
 */
public class CommandHandler {

	private final Bot bot;
	public List<AbstractCommand> commands;

	public CommandHandler(Bot bot) {
		this.bot = bot;
		setCommands();
	}

	public void process(Message message) {
		Reporter.info("Start CommandHandler. " + LogUtils.logMessage(message));

		String content = message.getContentDisplay();
		AbstractCommand c = getCommand(content);

		if (c != null) {
			if (hasHelp(content)) {
				Reporter.info(String.format("Executing Help SubCommand:[%s]", c.getName()));
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("Help: " + c.getName());
				eb.setDescription(c.getHelp());
				eb.setColor(new Color(0, 153, 255));
				message.getChannel().sendMessage(eb.build()).queue();
			} else {
				Reporter.info(String.format("Executing Command:[%s]", c.getName()));
				c.execute(message);
			}
		} else {
			Reporter.info(String.format("Command not found in list:[%s]", content));
		}

		Reporter.info("End CommandHandler.");
	}

	/**
	 * @param content Message Content
	 * @return True if content starts with Prefix, False otherwise.
	 */
	public boolean isCommand(String content) {
		if (content.startsWith(bot.config.BOT_PREFIX)) return true;
		else return false;
	}

	/**
	 * @param content Message Content
	 * @return Command that matches the given content
	 */
	public AbstractCommand getCommand(String content) {
		String name = content.split("\\s+")[0].substring(bot.config.BOT_PREFIX.length());
		if (isCommand(content)) {
			for (AbstractCommand command : commands) {
				for (String t : command.getTriggers()) {
					if (t.equalsIgnoreCase(name)) return command;
				}
			}
		}
		return null;
	}

	/**
	 * @param content Message Content
	 * @return True if content has a 'help' subcommand, False otherwise.
	 */
	public boolean hasHelp(String content) {
		String[] args = content.split("\\s+");
		for (int i = 1; i < args.length; i++) {
			if (args[i].equalsIgnoreCase(bot.config.BOT_PREFIX + "help")) return true;
		}
		return false;
	}

	/**
	 * Adds all AbtractCommands from the 'command' package to the commands list.
	 */
	public void setCommands() {
		ArrayList<AbstractCommand> list = new ArrayList<AbstractCommand>();
		for (Class<? extends AbstractCommand> c : new Reflections("ca.darrensjones.jonesbot.command").getSubTypesOf(AbstractCommand.class)) {
			try {
				AbstractCommand command = c.getConstructor(Bot.class).newInstance(bot);
				list.add(command);
			} catch (Exception e) {
				Reporter.fatal(e.getMessage());
			}
		}
		commands = list;
	}
}