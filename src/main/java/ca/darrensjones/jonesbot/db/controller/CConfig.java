package ca.darrensjones.jonesbot.db.controller;

import java.sql.ResultSet;
import java.util.HashMap;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-18
 * @since 1.0.0 2020-11-18
 */
public class CConfig {

	public static String[] getConfigValues() {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			ResultSet rs = BotDB.get().select("SELECT item_key, item_value FROM bot_config");
			while (rs.next()) map.put(rs.getString("item_key"), rs.getString("item_value"));
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		return new String[] { map.get("BOT_PREFIX"), map.get("BOT_TOKEN"), map.get("BOT_OWNER_ID"), map.get("BOT_GITHUB_REPO"), map.get("WEATHER_TOKEN") };
	}
}