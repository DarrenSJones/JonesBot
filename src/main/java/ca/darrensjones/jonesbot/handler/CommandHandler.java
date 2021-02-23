package ca.darrensjones.jonesbot.handler;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.log.Reporter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.reflections.Reflections;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-22
 * @since   1.0.0 2020-11-22
 */
public class CommandHandler {

	private final Bot bot;
	public List<AbstractCommand> commands;

	public CommandHandler(Bot bot) {
		this.bot = bot;
		setCommands();
	}

	public void process(Message message) {
		Reporter.info("Start CommandHandler.");
		Reporter.log(message);

		String content = message.getContentDisplay();
		AbstractCommand command = getCommand(content);

		if (command != null) {
			Reporter.info(String.format("Command found:[%s]", command.getName()));
			if (hasHelp(content)) {
				Reporter.info("Executing 'help' subcommand.");
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("Help: " + command.getName());
				eb.setDescription(command.getHelp());
				eb.setColor(new Color(0, 153, 255));
				message.getChannel().sendMessage(eb.build()).queue();
			} else {
				Reporter.info(String.format("Executing Command:[%s]", command.getName()));
				command.execute(message);
			}
		} else {
			Reporter.warn(String.format("Command not found:[%s]", content));
		}

		Reporter.info("End CommandHandler.");
	}

	/**
	 * @param  content Message Content
	 * @return         True if content starts with Prefix, False otherwise
	 */
	public boolean isCommand(String content) {
		if (content.startsWith(bot.getPrefix())) return true;
		return false;
	}

	/**
	 * @param  content Message Content
	 * @return         Command that matches the given content
	 */
	public AbstractCommand getCommand(String content) {
		if (isCommand(content)) {
			String name = content.split("\\s+")[0].substring(bot.getPrefix().length());
			for (AbstractCommand command : commands) {
				for (String trigger : command.getTriggers()) {
					if (trigger.equalsIgnoreCase(name)) return command;
				}
			}
		}
		return null;
	}

	/**
	 * @param  content Message Content
	 * @return         True if content has a 'help' subcommand, False otherwise
	 */
	public boolean hasHelp(String content) {
		if (isCommand(content)) {
			String[] args = content.split("\\s+");
			for (int i = 1; i < args.length; i++) {
				if (args[i].equalsIgnoreCase(bot.getPrefix() + "help")) return true;
			}
		}
		return false;
	}

	/** Adds all AbtractCommands from the 'command' package to the commands list. */
	public void setCommands() {
		String path = "ca.darrensjones.jonesbot.command";
		ArrayList<AbstractCommand> list = new ArrayList<AbstractCommand>();
		for (Class<? extends AbstractCommand> c : new Reflections(path)
				.getSubTypesOf(AbstractCommand.class)) {
			try {
				AbstractCommand command = c.getConstructor(Bot.class).newInstance(bot);
				list.add(command);
			} catch (Exception e) {
				Reporter.fatal("CommandHandler setCommands.", e);
			}
		}
		commands = list;
		Reporter.info(String.format("Loaded [%s] Commands from [%s]", commands.size(), path));
	}
}