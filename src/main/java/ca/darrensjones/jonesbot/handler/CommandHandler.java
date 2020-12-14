package ca.darrensjones.jonesbot.handler;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.*;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.log.LogUtils;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-14
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

		String content = message.getContentDisplay();
		AbstractCommand c = getCommand(content.split("\\s+")[0].substring(bot.config.BOT_PREFIX.length()));

		if (c != null) {
			if (hasHelp(content)) {
				Reporter.info("Sub-Help found");
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("Sub-Help: " + c.getName());
				eb.setDescription(c.getHelp());
				eb.setColor(new Color(0, 153, 255));
				message.getChannel().sendMessage(eb.build()).queue();
			} else {
				Reporter.info(String.format("Executing Command: [%s]", c.getName()));
				c.execute(message);
			}
		} else {
			Reporter.info("Command not found in list.");
		}

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

	public boolean hasHelp(String content) {
		String[] args = content.split("\\s+");
		for (int i = 1; i < args.length; i++) {
			if (args[i].equalsIgnoreCase(bot.config.BOT_PREFIX + "help")) return true;
		}
		return false;
	}

	public void setCommands() {
		commands = new ArrayList<AbstractCommand>();
		commands.add(new CommandCatFact(bot));
		commands.add(new CommandCowbell(bot));
		commands.add(new CommandFuturama(bot));
		commands.add(new CommandHelp(bot));
		commands.add(new CommandOwner(bot));
		commands.add(new CommandPing(bot));
		commands.add(new CommandReaction(bot));
		commands.add(new CommandReload(bot));
		commands.add(new CommandRickMorty(bot));
		commands.add(new CommandSimpsons(bot));
		commands.add(new CommandVersion(bot));
		commands.add(new CommandWeather(bot));
	}
}