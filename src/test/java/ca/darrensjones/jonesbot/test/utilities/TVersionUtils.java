package ca.darrensjones.jonesbot.test.utilities;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.command.utilities.Version;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-21
 * @since 1.1.4 2021-01-21
 */
public class TVersionUtils {

	@Test
	public void getVersionList() {
//		List<String> versionsAsc = Version.getVersions(true);
//		Assert.assertEquals(versionsAsc.get(0), "1.0.0");
//		Assert.assertEquals(versionsAsc.get(1), "1.0.1");
//
//		List<String> versionsDesc = Version.getVersions(false);
//		Assert.assertEquals(versionsDesc.get(versionsDesc.size() - 2), "1.0.1");
//		Assert.assertEquals(versionsDesc.get(versionsDesc.size() - 1), "1.0.0");
	}

	@Test(dependsOnMethods = "getVersionList", alwaysRun = true)
	public void getVersionInfo() {
//		List<String> versionsAsc = Version.getVersionEntry(true);
//		Assert.assertEquals(versionsAsc.get(0).trim(), "[1.0.0] - 2020-12-14 - Initial Release");
//		Assert.assertEquals(versionsAsc.get(1).trim(), "[1.0.1] - 2020-12-19 - General Cleanup");
//
//		List<String> versionsDesc = Version.getVersionEntry(false);
//		Assert.assertEquals(versionsDesc.get(versionsDesc.size() - 2).trim(), "[1.0.1] - 2020-12-19 - General Cleanup");
//		Assert.assertEquals(versionsDesc.get(versionsDesc.size() - 1).trim(), "[1.0.0] - 2020-12-14 - Initial Release");
	}
}