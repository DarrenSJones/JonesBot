package ca.darrensjones.jonesbot.test.db.controller;

import java.time.LocalDate;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.db.controller.CVersion;
import ca.darrensjones.jonesbot.db.model.OVersion;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-14
 * @since 1.0.0 2020-12-14
 */
public class TCVersion {

	@Test
	public void getAll() {
		List<OVersion> l = CVersion.getAll();
		Assert.assertEquals(l.size(), 1);
		Assert.assertEquals(l.get(0).major, 1);
		Assert.assertEquals(l.get(0).minor, 0);
		Assert.assertEquals(l.get(0).patch, 0);
		Assert.assertEquals(l.get(0).date, LocalDate.of(2020, 12, 14));
		Assert.assertEquals(l.get(0).changes, "First version! Contains everything in the old Bot except the schedule.");
	}

	@Test(dependsOnMethods = "getAll", alwaysRun = true)
	public void getVersion() {

		// Valid
		OVersion o = CVersion.getVersion("1", "0", "0");
		Assert.assertEquals(o.major, 1);
		Assert.assertEquals(o.minor, 0);
		Assert.assertEquals(o.patch, 0);
		Assert.assertEquals(o.date, LocalDate.of(2020, 12, 14));
		Assert.assertEquals(o.changes, "First version! Contains everything in the old Bot except the schedule.");

		// Invalid
		Assert.assertNull(CVersion.getVersion("0", "0", "0"));
	}
}