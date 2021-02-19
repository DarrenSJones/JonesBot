package ca.darrensjones.jonesbot.simpleschedule;

import ca.darrensjones.jonesbot.bot.Bot;
import ca.darrensjones.jonesbot.db.controller.CSimpleSchedule;
import ca.darrensjones.jonesbot.db.model.OSimpleSchedule;
import ca.darrensjones.jonesbot.log.Reporter;
import java.awt.Color;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.2.0 2021-02-15
 */
public class SimpleSchedule {

	public static void scheduleCron(Bot bot) {
		Reporter.info("Schedule Cron Start.");

		LocalDate date = LocalDate.now();

		List<String[]> channels = CSimpleSchedule.getUniqueChannelsForDate(date);
		Reporter.info(String.format("Found [%s] Channels with Scheduled Events. Date:[%s]",
				channels.size(), date.toString()));

		for (String[] channel : channels) { // Each channel that has an event
			String guildId = channel[0];
			String channelId = channel[1];

			// All events for the channel
			List<OSimpleSchedule> events = CSimpleSchedule.getScheduleByDateAndChannel(date,
					channelId);
			Reporter.info(String.format("Found [%s] Scheduled Events. Date:[%s] Channel:[%s]",
					channels.size(), date.toString(), channelId));

			EmbedBuilder eb = buildBaseEmbed(date);
			for (OSimpleSchedule event : events) {
				eb.addField(event.event_time, event.event_value, false);
				Reporter.info(String.format("Found Event. Time:[%s] Value:[%s]", event.event_time,
						event.event_value));
			}

			bot.jda.getTextChannelById(channelId).sendMessage(eb.build()).queue();
			Reporter.info(
					String.format("Schedule Cron Posted. Guild:[%s] Channel:[%s] EventCount:[%s]",
							guildId, channelId, eb.getFields().size()));
		}

		Reporter.info("Schedule Cron End.");
	}

	public static EmbedBuilder scheduleCommand(LocalDate date, String guildId) {
		Reporter.info("Schedule Command Start.");

		// All events for the guild, by channel
		List<OSimpleSchedule> events = CSimpleSchedule.getScheduleByDateAndGuild(date, guildId);
		Reporter.info(String.format("Found [%s] Scheduled Events. Date[%s] Guild[%s]",
				events.size(), date.toString(), guildId));

		EmbedBuilder eb = buildBaseEmbed(date);
		for (OSimpleSchedule event : events) {
			eb.addField(event.event_time, event.event_value, false);
			Reporter.info(String.format("Found Event. Time:[%s] Value:[%s]", event.event_time,
					event.event_value));
		}

		Reporter.info("Schedule Command End.");
		return eb;
	}

	private static EmbedBuilder buildBaseEmbed(LocalDate date) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(String.format("Scheduled Events: %s-%s-%s", date.getYear(),
				date.getMonthValue(), date.getDayOfMonth()));
		eb.setColor(new Color(0, 153, 255));
		eb.setFooter("Times Listed in UTC-06:00",
				"https://vignette.wikia.nocookie.net/en.futurama/images/2/2f/Lincoln.jpg");
		eb.setTimestamp(new Date().toInstant());
		return eb;
	}
}