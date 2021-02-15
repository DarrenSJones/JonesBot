package ca.darrensjones.jonesbot.command;

import java.time.LocalDate;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.command.meta.AbstractCommand;
import ca.darrensjones.jonesbot.command.meta.CommandVisibility;
import ca.darrensjones.jonesbot.simpleschedule.SimpleSchedule;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Darren Jones
 * @version 1.2.0 2021-02-15
 * @since 1.2.0 2021-02-15
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
		String guildId = message.getGuild().getId();
		LocalDate date = LocalDate.now();

		EmbedBuilder eb = SimpleSchedule.scheduleCommand(date, guildId);
		message.getChannel().sendMessage(eb.build()).queue();
	}
}