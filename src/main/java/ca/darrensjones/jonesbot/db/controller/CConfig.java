package ca.darrensjones.jonesbot.db.controller;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.log.Reporter;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-11-18
 */
public class CConfig {

	public static String[] getConfigValues() {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ResultSet rs = BotDB.get().select("SELECT item_key, item_value FROM bot_config");
			while (rs.next()) {
				map.put(rs.getString("item_key"), rs.getString("item_value"));
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.error("CConfig getConfigValues.");
			e.printStackTrace();
		}
		return new String[] { map.get("BOT_PREFIX"), map.get("BOT_TOKEN"), map.get("BOT_OWNER_ID"),
				map.get("BOT_GITHUB_REPO"), map.get("WEATHER_TOKEN") };
	}

	public static String getDatabaseVersion() {
		String version = "";
		try {
			ResultSet rs = BotDB.get().select(
					"SELECT item_key, item_value FROM bot_config WHERE item_key = 'DB_VERSION'");
			if (rs.next()) {
				version = rs.getString("item_value");
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.error("CConfig getDatabaseVersion.");
			e.printStackTrace();
		}
		return version;
	}
}