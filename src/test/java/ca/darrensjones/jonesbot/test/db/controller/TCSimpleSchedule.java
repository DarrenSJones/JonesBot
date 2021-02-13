package ca.darrensjones.jonesbot.test.db.controller;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.db.controller.CSimpleSchedule;

/**
 * @author Darren Jones
 * @version 1.2.0 2021-02-12
 * @since 1.2.0 2021-02-12
 */
public class TCSimpleSchedule {

	LocalDate monday = LocalDate.of(2021, 2, 1);
	LocalDate tuesday = LocalDate.of(2021, 2, 2);
	LocalDate wednesday = LocalDate.of(2021, 2, 3);
	LocalDate thursday = LocalDate.of(2021, 2, 4);
	LocalDate friday = LocalDate.of(2021, 2, 5);
	LocalDate saturday = LocalDate.of(2021, 2, 6);
	LocalDate sunday = LocalDate.of(2021, 2, 7);

	@Test
	public void getUniqueChannelsForDate() {

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(monday).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(monday).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(monday).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(tuesday).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(tuesday).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(tuesday).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(wednesday).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(wednesday).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(wednesday).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(thursday).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(thursday).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(thursday).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(friday).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(friday).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(friday).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(saturday).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(saturday).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(saturday).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(sunday).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(sunday).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(sunday).get(0)[1], "223456789012345678");
	}

	@Test(dependsOnMethods = "getUniqueChannelsForDate", alwaysRun = true)
	public void getScheduleByDate() {

		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(monday).size(), 2);
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(monday).get(0).guildId, "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(monday).get(0).channelId, "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(tuesday).size(), 2);
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(tuesday).get(0).guildId, "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(tuesday).get(0).channelId, "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(wednesday).size(), 2);
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(wednesday).get(0).guildId, "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(wednesday).get(0).channelId, "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(thursday).size(), 2);
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(thursday).get(0).guildId, "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(thursday).get(0).channelId, "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(friday).size(), 2);
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(friday).get(0).guildId, "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(friday).get(0).channelId, "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(saturday).size(), 2);
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(saturday).get(0).guildId, "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(saturday).get(0).channelId, "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(sunday).size(), 2);
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(sunday).get(0).guildId, "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getScheduleByDate(sunday).get(0).channelId, "223456789012345678");
	}
}