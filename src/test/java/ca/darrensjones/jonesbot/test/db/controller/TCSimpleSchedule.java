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

	@Test
	public void getUniqueChannelsForDate() {

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 1)).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 1)).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 1)).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 2)).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 2)).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 2)).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 3)).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 3)).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 3)).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 4)).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 4)).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 4)).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 5)).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 5)).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 5)).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 6)).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 6)).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 6)).get(0)[1], "223456789012345678");

		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 7)).size(), 1);
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 7)).get(0)[0], "023456789012345678");
		Assert.assertEquals(CSimpleSchedule.getUniqueChannelsForDate(LocalDate.of(2021, 2, 7)).get(0)[1], "223456789012345678");
	}

	@Test(dependsOnMethods = "getUniqueChannelsForDate", alwaysRun = true)
	public void getScheduleByDate() {

	}
}