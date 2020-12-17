package ca.darrensjones.jonesbot.db;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.1 2020-12-17
 * @since 1.0.0 2020-11-18
 */
public class BotDB {

	private static JDBC jdbc;

	public static void init(String driver, String database, String userName, String password) {
		jdbc = new JDBC(driver, database, userName, password);
		Reporter.info(String.format("JDBC Set:[%s] Database:[%s] UserName:[%s] Password:[%s]", driver, database, userName, password));
	}

	public static JDBC get() {
		return jdbc;
	}
}