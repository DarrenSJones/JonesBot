package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.command.utilities.Version;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-14
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
		eb.setDescription(Version.readToDo());
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}