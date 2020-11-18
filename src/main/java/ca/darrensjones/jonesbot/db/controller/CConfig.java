package ca.darrensjones.jonesbot.db.controller;

import java.sql.ResultSet;
import java.util.HashMap;

import ca.darrensjones.jonesbot.bot.BotConfig;
import ca.darrensjones.jonesbot.db.AbstractController;
import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class CConfig extends AbstractController {

	public static BotConfig getConfig() {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "SELECT * FROM dbo.bot_config";
		try {
			ResultSet rs = BotDB.get().select(sql);
			while (rs.next()) {
				map.put(rs.getString("item_key"), rs.getString("item_value"));
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		BotConfig bc = new BotConfig(map.get("BOT_VERSION"), map.get("BOT_TOKEN"), map.get("BOT_OWNER_ID"), map.get("BOT_PREFIX"),
				map.get("TOKEN_OPENWEATHERMAP"));
		return bc;
	}
}