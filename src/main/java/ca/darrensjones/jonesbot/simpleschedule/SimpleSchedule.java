package ca.darrensjones.jonesbot.simpleschedule;

import java.awt.Color;
import java.time.LocalDate;
import java.util.Date;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.controller.CSimpleSchedule;
import ca.darrensjones.jonesbot.db.model.OSimpleSchedule;
import ca.darrensjones.jonesbot.log.Reporter;
import net.dv8tion.jda.api.EmbedBuilder;

/**
 * @author Darren Jones
 * @version 1.2.0 2021-02-15
 * @since 1.2.0 2021-02-15
 */
public class SimpleSchedule {

	public static void scheduleCron(Bot bot) {
		Reporter.info("Schedule Cron Start");

		LocalDate date = LocalDate.now();

		for (String[] channels : CSimpleSchedule.getUniqueChannelsForDate(date)) { // Each channel that has an event
			String guildId = channels[0];
			String channelId = channels[1];

			EmbedBuilder eb = buildBaseEmbed(date);
			for (OSimpleSchedule event : CSimpleSchedule.getScheduleByDateAndChannel(date, channelId)) { // All events for the channel
				eb.addField(event.event_time, event.event_value, false);
			}

			bot.jda.getTextChannelById(channelId).sendMessage(eb.build()).queue();
			Reporter.info(String.format("Cron Schedule Sent - Guild:[%s] Channel:[%s] EventCount:[%s]", guildId, channelId, eb.getFields().size()), true);
		}

		Reporter.info("Schedule Cron End");
	}

	public static EmbedBuilder scheduleCommand(LocalDate date, String guildId) {
		Reporter.info("Schedule Command Start");

		EmbedBuilder eb = buildBaseEmbed(date);
		for (OSimpleSchedule event : CSimpleSchedule.getScheduleByDateAndGuild(date, guildId)) { // All events for the channel
			eb.addField(event.event_time, event.event_value, false);
		}

		Reporter.info("Schedule Command End");
		return eb;
	}

	private static EmbedBuilder buildBaseEmbed(LocalDate date) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(String.format("Schedule: %s/%s/%s", date.getYear(), date.getMonthValue(), date.getDayOfMonth()));
		eb.setColor(new Color(0, 153, 255));
		eb.setFooter("Automated JonesBot Message - Times are listed in CST", "https://vignette.wikia.nocookie.net/en.futurama/images/2/2f/Lincoln.jpg");
		eb.setTimestamp(new Date().toInstant());
		return eb;
	}
}