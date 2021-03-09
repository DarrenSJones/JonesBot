package ca.darrensjones.jonesbot.test.db.controller;

import ca.darrensjones.jonesbot.db.controller.CSimpleSchedule;
import ca.darrensjones.jonesbot.db.model.OSimpleSchedule;
import java.time.LocalDate;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-24
 * @since   1.2.0 2021-02-12
 */
public class CSimpleScheduleTest {

	LocalDate mo = LocalDate.of(2021, 2, 1);
	LocalDate tu = LocalDate.of(2021, 2, 2);
	LocalDate we = LocalDate.of(2021, 2, 3);
	LocalDate th = LocalDate.of(2021, 2, 4);
	LocalDate fr = LocalDate.of(2021, 2, 5);
	LocalDate sa = LocalDate.of(2021, 2, 6);
	LocalDate su = LocalDate.of(2021, 2, 7);

	String guild = "023456789012345678";
	String channel = "223456789012345678";

	@Test
	public void getUniqueChannelsForDate() {

		List<String[]> monday = CSimpleSchedule.getUniqueChannelsForDate(mo);
		Assert.assertEquals(monday.size(), 3);
		Assert.assertEquals(monday.get(0),
				new String[] { "023456789012345678", "223456789012345678" });
		Assert.assertEquals(monday.get(1),
				new String[] { "023456789012345678", "323456789012345678" });
		Assert.assertEquals(monday.get(2),
				new String[] { "123456789012345678", "923456789012345678" });

		List<String[]> tuesday = CSimpleSchedule.getUniqueChannelsForDate(tu);
		Assert.assertEquals(tuesday.size(), 1);
		Assert.assertEquals(tuesday.get(0),
				new String[] { "023456789012345678", "223456789012345678" });

		List<String[]> wednesday = CSimpleSchedule.getUniqueChannelsForDate(we);
		Assert.assertEquals(wednesday.size(), 1);
		Assert.assertEquals(wednesday.get(0),
				new String[] { "023456789012345678", "223456789012345678" });

		List<String[]> thursday = CSimpleSchedule.getUniqueChannelsForDate(th);
		Assert.assertEquals(thursday.size(), 1);
		Assert.assertEquals(thursday.get(0),
				new String[] { "023456789012345678", "223456789012345678" });

		List<String[]> friday = CSimpleSchedule.getUniqueChannelsForDate(fr);
		Assert.assertEquals(friday.size(), 1);
		Assert.assertEquals(friday.get(0),
				new String[] { "023456789012345678", "223456789012345678" });

		List<String[]> saturday = CSimpleSchedule.getUniqueChannelsForDate(sa);
		Assert.assertEquals(saturday.size(), 1);
		Assert.assertEquals(saturday.get(0),
				new String[] { "023456789012345678", "223456789012345678" });

		List<String[]> sunday = CSimpleSchedule.getUniqueChannelsForDate(su);
		Assert.assertEquals(sunday.size(), 1);
		Assert.assertEquals(sunday.get(0),
				new String[] { "023456789012345678", "223456789012345678" });
	}

	@Test(dependsOnMethods = "getUniqueChannelsForDate", alwaysRun = true)
	public void getScheduleByDateAndGuild() {

		List<OSimpleSchedule> monday = CSimpleSchedule.getScheduleByDateAndGuild(mo, guild);
		Assert.assertEquals(monday.size(), 3);
		Assert.assertEquals(monday.get(0).event_time, "1:00 PM");
		Assert.assertEquals(monday.get(0).event_value, "Monday Day of Week!");
		Assert.assertEquals(monday.get(1).event_time, "1:30 PM");
		Assert.assertEquals(monday.get(1).event_value, "Monday Date!");
		Assert.assertEquals(monday.get(2).event_time, "1:30 AM");
		Assert.assertEquals(monday.get(2).event_value, "Another Monday Date!");

		List<OSimpleSchedule> monday2 = CSimpleSchedule.getScheduleByDateAndGuild(mo,
				"123456789012345678");
		Assert.assertEquals(monday2.size(), 1);
		Assert.assertEquals(monday2.get(0).event_time, "1:45 AM");
		Assert.assertEquals(monday2.get(0).event_value, "Yet Another Monday Date!");

		List<OSimpleSchedule> tuesday = CSimpleSchedule.getScheduleByDateAndGuild(tu, guild);
		Assert.assertEquals(tuesday.size(), 2);
		Assert.assertEquals(tuesday.get(0).event_time, "2:00 PM");
		Assert.assertEquals(tuesday.get(0).event_value, "Tuesday Day of Week!");
		Assert.assertEquals(tuesday.get(1).event_time, "2:30 PM");
		Assert.assertEquals(tuesday.get(1).event_value, "Tuesday Date!");

		List<OSimpleSchedule> wednesday = CSimpleSchedule.getScheduleByDateAndGuild(we, guild);
		Assert.assertEquals(wednesday.size(), 2);
		Assert.assertEquals(wednesday.get(0).event_time, "3:00 PM");
		Assert.assertEquals(wednesday.get(0).event_value, "Wednesday Day of Week!");
		Assert.assertEquals(wednesday.get(1).event_time, "3:30 PM");
		Assert.assertEquals(wednesday.get(1).event_value, "Wednesday Date!");

		List<OSimpleSchedule> thursday = CSimpleSchedule.getScheduleByDateAndGuild(th, guild);
		Assert.assertEquals(thursday.size(), 2);
		Assert.assertEquals(thursday.get(0).event_time, "4:00 PM");
		Assert.assertEquals(thursday.get(0).event_value, "Thursday Day of Week!");
		Assert.assertEquals(thursday.get(1).event_time, "4:30 PM");
		Assert.assertEquals(thursday.get(1).event_value, "Thursday Date!");

		List<OSimpleSchedule> friday = CSimpleSchedule.getScheduleByDateAndGuild(fr, guild);
		Assert.assertEquals(friday.size(), 2);
		Assert.assertEquals(friday.get(0).event_time, "5:00 PM");
		Assert.assertEquals(friday.get(0).event_value, "Friday Day of Week!");
		Assert.assertEquals(friday.get(1).event_time, "5:30 PM");
		Assert.assertEquals(friday.get(1).event_value, "Friday Date!");

		List<OSimpleSchedule> saturday = CSimpleSchedule.getScheduleByDateAndGuild(sa, guild);
		Assert.assertEquals(saturday.size(), 2);
		Assert.assertEquals(saturday.get(0).event_time, "6:00 PM");
		Assert.assertEquals(saturday.get(0).event_value, "Saturday Day of Week!");
		Assert.assertEquals(saturday.get(1).event_time, "6:30 PM");
		Assert.assertEquals(saturday.get(1).event_value, "Saturday Date!");

		List<OSimpleSchedule> sunday = CSimpleSchedule.getScheduleByDateAndGuild(su, guild);
		Assert.assertEquals(sunday.size(), 2);
		Assert.assertEquals(sunday.get(0).event_time, "7:00 PM");
		Assert.assertEquals(sunday.get(0).event_value, "Sunday Day of Week!");
		Assert.assertEquals(sunday.get(1).event_time, "7:30 PM");
		Assert.assertEquals(sunday.get(1).event_value, "Sunday Date!");
	}

	@Test(dependsOnMethods = "getScheduleByDateAndGuild", alwaysRun = true)
	public void getScheduleByDateAndChannel() {

		List<OSimpleSchedule> monday = CSimpleSchedule.getScheduleByDateAndChannel(mo, channel);
		Assert.assertEquals(monday.size(), 2);
		Assert.assertEquals(monday.get(0).event_time, "1:00 PM");
		Assert.assertEquals(monday.get(0).event_value, "Monday Day of Week!");
		Assert.assertEquals(monday.get(1).event_time, "1:30 PM");
		Assert.assertEquals(monday.get(1).event_value, "Monday Date!");

		List<OSimpleSchedule> tuesday = CSimpleSchedule.getScheduleByDateAndChannel(tu, channel);
		Assert.assertEquals(tuesday.size(), 2);
		Assert.assertEquals(tuesday.get(0).event_time, "2:00 PM");
		Assert.assertEquals(tuesday.get(0).event_value, "Tuesday Day of Week!");
		Assert.assertEquals(tuesday.get(1).event_time, "2:30 PM");
		Assert.assertEquals(tuesday.get(1).event_value, "Tuesday Date!");

		List<OSimpleSchedule> wednesday = CSimpleSchedule.getScheduleByDateAndChannel(we, channel);
		Assert.assertEquals(wednesday.size(), 2);
		Assert.assertEquals(wednesday.get(0).event_time, "3:00 PM");
		Assert.assertEquals(wednesday.get(0).event_value, "Wednesday Day of Week!");
		Assert.assertEquals(wednesday.get(1).event_time, "3:30 PM");
		Assert.assertEquals(wednesday.get(1).event_value, "Wednesday Date!");

		List<OSimpleSchedule> thursday = CSimpleSchedule.getScheduleByDateAndChannel(th, channel);
		Assert.assertEquals(thursday.size(), 2);
		Assert.assertEquals(thursday.get(0).event_time, "4:00 PM");
		Assert.assertEquals(thursday.get(0).event_value, "Thursday Day of Week!");
		Assert.assertEquals(thursday.get(1).event_time, "4:30 PM");
		Assert.assertEquals(thursday.get(1).event_value, "Thursday Date!");

		List<OSimpleSchedule> friday = CSimpleSchedule.getScheduleByDateAndChannel(fr, channel);
		Assert.assertEquals(friday.size(), 2);
		Assert.assertEquals(friday.get(0).event_time, "5:00 PM");
		Assert.assertEquals(friday.get(0).event_value, "Friday Day of Week!");
		Assert.assertEquals(friday.get(1).event_time, "5:30 PM");
		Assert.assertEquals(friday.get(1).event_value, "Friday Date!");

		List<OSimpleSchedule> saturday = CSimpleSchedule.getScheduleByDateAndChannel(sa, channel);
		Assert.assertEquals(saturday.size(), 2);
		Assert.assertEquals(saturday.get(0).event_time, "6:00 PM");
		Assert.assertEquals(saturday.get(0).event_value, "Saturday Day of Week!");
		Assert.assertEquals(saturday.get(1).event_time, "6:30 PM");
		Assert.assertEquals(saturday.get(1).event_value, "Saturday Date!");

		List<OSimpleSchedule> sunday = CSimpleSchedule.getScheduleByDateAndChannel(su, channel);
		Assert.assertEquals(sunday.size(), 2);
		Assert.assertEquals(sunday.get(0).event_time, "7:00 PM");
		Assert.assertEquals(sunday.get(0).event_value, "Sunday Day of Week!");
		Assert.assertEquals(sunday.get(1).event_time, "7:30 PM");
		Assert.assertEquals(sunday.get(1).event_value, "Sunday Date!");
	}
}