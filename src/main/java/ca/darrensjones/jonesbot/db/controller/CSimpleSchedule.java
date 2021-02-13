package ca.darrensjones.jonesbot.db.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.db.model.OSimpleSchedule;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.2.0 2021-02-12
 * @since 1.2.0 2021-02-12
 */
public class CSimpleSchedule {

	private static OSimpleSchedule setRecord(ResultSet rs) throws SQLException {
		OSimpleSchedule record = new OSimpleSchedule();
		record.id = rs.getInt("id");
		record.guildId = rs.getString("guild_id");
		record.channelId = rs.getString("channel_id");
		record.event_time = rs.getString("event_time");
		record.event_value = rs.getString("event_value");

		if (StringUtils.isNotBlank(rs.getString("event_date"))) {
			String[] d = rs.getString("event_date").split("-");
			record.event_date = LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));
		} else {
			record.event_date = LocalDate.now().with(DayOfWeek.valueOf(rs.getString("event_day_of_week")));
		}

		return record;
	}

	public static List<String[]> getUniqueChannelsForDate(LocalDate date) { // [GuildId, ChannelId]
		List<String[]> list = new ArrayList<String[]>();
		try {
			String query = String.format(
					"SELECT DISTINCT guild_id, channel_id FROM simple_schedule WHERE event_date = '%s' OR event_day_of_week = '%s' ORDER by guild_id, channel_id",
					date, date.getDayOfWeek());
			ResultSet rs = BotDB.get().select(query);
			while (rs.next()) {
				String[] channels = { rs.getString("guild_id"), rs.getString("channel_id") };
				list.add(channels);
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal("CSimpleSchedule getScheduleByDate.", e);
		}
		return list;
	}

	public static List<OSimpleSchedule> getScheduleByDate(LocalDate date) {
		List<OSimpleSchedule> list = new ArrayList<OSimpleSchedule>();
		try {
			String query = String.format(
					"SELECT id, event_date, event_day_of_week, guild_id, channel_id, event_time, event_value FROM simple_schedule WHERE event_date = '%s' OR event_day_of_week = '%s' ORDER by guild_id, channel_id",
					date, date.getDayOfWeek());
			ResultSet rs = BotDB.get().select(query);
			while (rs.next()) list.add(setRecord(rs));
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal("CSimpleSchedule getScheduleByDate.", e);
		}
		return list;
	}
}