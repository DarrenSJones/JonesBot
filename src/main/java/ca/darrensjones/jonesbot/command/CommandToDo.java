package ca.darrensjones.jonesbot.command;

import java.awt.Color;
import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-02-02
 * @since 1.0.2 2020-12-22
 */
public class CommandToDo extends AbstractCommand {

	public CommandToDo(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "To Do";
	}

	@Override
	public String getDescription() {
		return "JonesBot To Do List";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "todo" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.HIDDEN;
	}

	@Override
	public String getHelp() {
		return "**" + bot.getPrefix() + "todo** Displays the current to do list.";
	}

	@Override
	public void execute(Message message) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("JonesBot To Do List");
		eb.setDescription(readToDo());
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}

	private static String readToDo() {
		try {
			return FileUtils.readFileToString(new File("todo.txt"), StandardCharsets.UTF_8);
		} catch (Exception e) {
			Reporter.fatal("ReadToDo Error.", e);
			return null;
		}
	}
}