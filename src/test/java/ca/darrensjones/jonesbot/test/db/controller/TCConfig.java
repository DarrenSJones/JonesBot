package ca.darrensjones.jonesbot.test.db.controller;

import ca.darrensjones.jonesbot.db.controller.CConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-24
 * @since   1.1.4 2021-01-21
 */
public class TCConfig {

	@Test
	public void getConfigValues() {
		String[] c = CConfig.getConfigValues();
		Assert.assertEquals(c.length, 5);

		// [prefix, bot_token, bot_owner_id, bot_github_repo, weather_token]
		Assert.assertEquals(c[0], "!");
		Assert.assertEquals(c[1], "12345678901234567890123456789012345678901234567890123456789");
		Assert.assertEquals(c[2], "123456789012345678");
		Assert.assertEquals(c[3], "http://localhost:1080");
		Assert.assertEquals(c[4], "12345678901234567890123456789012");
	}

	@Test(dependsOnMethods = "getConfigValues", alwaysRun = true)
	public void getDatabaseVersion() {
		String databaseVersion = CConfig.getDatabaseVersion();

		Assert.assertTrue(databaseVersion.matches("\\d\\.\\d\\.\\d"));
	}
}