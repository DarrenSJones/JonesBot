package ca.darrensjones.jonesbot.db.controller;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.db.model.OSimpleSchedule;
import ca.darrensjones.jonesbot.log.Reporter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.2.0 2021-02-12
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
			int year = Integer.parseInt(d[0]);
			int month = Integer.parseInt(d[1]);
			int day = Integer.parseInt(d[2]);
			record.event_date = LocalDate.of(year, month, day);
		} else {
			String day = rs.getString("event_day_of_week");
			record.event_date = LocalDate.now().with(DayOfWeek.valueOf(day));
		}
		return record;
	}

	public static List<String[]> getUniqueChannelsForDate(LocalDate date) { // [GuildId, ChannelId]
		List<String[]> list = new ArrayList<String[]>();
		try {
			String query = String.format(
					"SELECT DISTINCT guild_id, channel_id FROM simple_schedule WHERE (event_date = '%s' OR event_day_of_week = '%s') ORDER by guild_id, channel_id",
					date, date.getDayOfWeek());
			ResultSet rs = BotDB.get().select(query);
			while (rs.next()) {
				String[] channels = { rs.getString("guild_id"), rs.getString("channel_id") };
				list.add(channels);
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.error("CSimpleSchedule getScheduleByDate.", e);
		}
		return list;
	}

	public static List<OSimpleSchedule> getScheduleByDateAndGuild(LocalDate date, String guildId) {
		List<OSimpleSchedule> list = new ArrayList<OSimpleSchedule>();
		try {
			String query = String.format(
					"SELECT id, event_date, event_day_of_week, guild_id, channel_id, event_time, event_value FROM simple_schedule WHERE (event_date = '%s' OR event_day_of_week = '%s') AND guild_id = '%s' ORDER by guild_id, channel_id, id",
					date, date.getDayOfWeek(), guildId);
			ResultSet rs = BotDB.get().select(query);
			while (rs.next()) {
				list.add(setRecord(rs));
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.error("CSimpleSchedule getScheduleByDate.", e);
		}
		return list;
	}

	public static List<OSimpleSchedule> getScheduleByDateAndChannel(LocalDate date,
			String channelId) {
		List<OSimpleSchedule> list = new ArrayList<OSimpleSchedule>();
		try {
			String query = String.format(
					"SELECT id, event_date, event_day_of_week, guild_id, channel_id, event_time, event_value FROM simple_schedule WHERE (event_date = '%s' OR event_day_of_week = '%s') AND channel_id = '%s' ORDER by guild_id, channel_id, id",
					date, date.getDayOfWeek(), channelId);
			ResultSet rs = BotDB.get().select(query);
			while (rs.next()) {
				list.add(setRecord(rs));
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.error("CSimpleSchedule getScheduleByDate.", e);
		}
		return list;
	}
}