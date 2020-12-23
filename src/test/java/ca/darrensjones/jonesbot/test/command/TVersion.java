package ca.darrensjones.jonesbot.test.command;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.utilities.Version;

/**
 * @author Darren Jones
 * @version 1.0.2 2020-12-22
 * @since 1.0.2 2020-12-22
 */
public class TVersion {

	@Test
	public void getVersionList() {
		List<String> versions = Version.getVersions();
		Assert.assertEquals(versions.get(0), "1.0.0");
		Assert.assertEquals(versions.get(1), "1.0.1");
		Assert.assertEquals(versions.get(versions.size() - 1), "Unreleased");
	}

	@Test(dependsOnMethods = "getVersionList", alwaysRun = true)
	public void getVersionInfo() {
		List<String> versionsAsc = Version.getVersionEntry(true);
		Assert.assertEquals(versionsAsc.get(0), "[1.0.0] - 2020-12-14 - Initial Release");
		Assert.assertEquals(versionsAsc.get(1), "[1.0.1] - 2020-12-19 - General Cleanup");
		Assert.assertEquals(versionsAsc.get(versionsAsc.size() - 1), "[Unreleased]");

		List<String> versionsDesc = Version.getVersionEntry(false);
		Assert.assertEquals(versionsDesc.get(0), "[Unreleased]");
		Assert.assertEquals(versionsDesc.get(versionsDesc.size() - 2), "[1.0.1] - 2020-12-19 - General Cleanup");
		Assert.assertEquals(versionsDesc.get(versionsDesc.size() - 1), "[1.0.0] - 2020-12-14 - Initial Release");
	}
}