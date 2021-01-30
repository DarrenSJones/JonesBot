package ca.darrensjones.jonesbot.db;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.1.3 2021-01-19
 * @since 1.0.0 2020-11-18
 */
public class BotDB {

	private static JDBC jdbc;

	/** This is set on Bot initialization, before the JDA connection is made. */
	public static void set(String driver, String database, String userName, String password) {
		jdbc = new JDBC(driver, database, userName, password);
		Reporter.info(String.format("JDBC Set:[%s] Database:[%s] UserName:[%s] Password:[%s]", driver, database, userName, password));
	}

	/** Assumes a connection has already been made. */
	public static JDBC get() {
		return jdbc;
	}
}