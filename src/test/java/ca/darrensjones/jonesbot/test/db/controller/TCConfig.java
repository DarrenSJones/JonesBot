package ca.darrensjones.jonesbot.test.db.controller;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.db.controller.CConfig;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-21
 * @since 1.1.4 2021-01-21
 */
public class TCConfig {

	@Test
	public void getConfigValues() {
		String[] map = CConfig.getConfigValues();
		Assert.assertEquals(map.length, 5); // prefix, bot_token, bot_owner_id, bot_github_repo, weather_token

		Assert.assertEquals(map[0], "!");
		Assert.assertEquals(map[1], "12345678901234567890123456789012345678901234567890123456789");
		Assert.assertEquals(map[2], "123456789012345678");
		Assert.assertEquals(map[3], "http://localhost:1080");
		Assert.assertEquals(map[4], "12345678901234567890123456789012");
	}

	@Test(dependsOnMethods = "getConfigValues", alwaysRun = true)
	public void getDatabaseVersion() {
		String dbVersion = CConfig.getDatabaseVersion();

		Assert.assertTrue(dbVersion.matches("\\d\\.\\d\\.\\d"));
	}
}