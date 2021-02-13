package ca.darrensjones.jonesbot.command;

import java.awt.Color;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.2.0 2021-02-13
 * @since 1.2.0 2021-02-13
 */
public class CommandSimpleSchedule extends AbstractCommand {

	public CommandSimpleSchedule(Bot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		return "Simple Schedule";
	}

	@Override
	public String getDescription() {
		return "A Simple Schedule for JonesBot";
	}

	@Override
	public String[] getTriggers() {
		return new String[] { "simpleschedule", "schedule" };
	}

	@Override
	public CommandVisibility visibility() {
		return CommandVisibility.PUBLIC;
	}

	@Override
	public String getHelp() {
		return "**" + bot.getPrefix() + "schedule** Displays today's schedule.";
	}

	@Override
	public void execute(Message message) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(String.format("Current Version: %s", bot.getConfig().VERSION), String.format("%s/blob/dev/CHANGELOG.md", bot.getConfig().BOT_GITHUB));
		eb.setDescription("The change log is linked above");
		eb.setColor(new Color(0, 153, 255));
		message.getChannel().sendMessage(eb.build()).queue();
	}
}