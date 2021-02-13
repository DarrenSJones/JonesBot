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
		record.guildId = rs.getString("guildId");
		record.channelId = rs.getString("channelId");
		record.time = rs.getString("time");
		record.value = rs.getString("value");

		if (StringUtils.isNotBlank(rs.getString("date"))) {
			String[] d = rs.getString("date").split("/");
			record.date = LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));
		} else {
			record.date = LocalDate.now().with(DayOfWeek.valueOf(rs.getString("day_of_week")));
		}

		return record;
	}

	public static List<String[]> getUniqueChannelsForDate(LocalDate date) { // [GuildId, ChannelId]
		List<String[]> list = new ArrayList<String[]>();
		try {
			String query = String.format(
					"SELECT DISTINCT guild_id, channel_id FROM simple_schedule WHERE date = '%s' OR day_of_week = '%s' ORDER by guild_id, channel_id", date,
					date.getDayOfWeek());
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
					"SELECT id, date, day_of_week, guild_id, channel_id, time, value FROM simple_schedule WHERE date = '%s' OR day_of_week = '%s' ORDER by guild_id, channel_id",
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