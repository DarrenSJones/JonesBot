package ca.darrensjones.jonesbot.test.db.controller;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.db.controller.CVersion;
import ca.darrensjones.jonesbot.db.model.OVersion;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-22
 * @since 1.1.4 2021-01-21
 */
public class TCVersion {

	@Test
	public void getVersionList() {
		List<OVersion> versions = CVersion.getVersionList();

		for (OVersion version : versions) {
			Assert.assertTrue(Integer.toString(version.major).matches("\\d"));
			Assert.assertTrue(Integer.toString(version.minor).matches("\\d"));
			Assert.assertTrue(Integer.toString(version.patch).matches("\\d"));
			Assert.assertTrue(version.date.matches("\\d{4}-\\d{2}-\\d{2}"));
			Assert.assertTrue(version.description.matches("[\\s\\S]{1,100}"));
			Assert.assertTrue(version.getName().matches("1\\.[0-2]\\.\\d"));
		}
	}
}