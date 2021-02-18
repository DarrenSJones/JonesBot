package ca.darrensjones.jonesbot.db;

import ca.darrensjones.jonesbot.log.Reporter;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-11-18
 */
public class BotDB {

	private static JDBC jdbc;
	private static Properties properties; // Only contains prod/test database information
	private static String dbBackup;

	/** Set on Bot initialization, before the JDA connection is made. */
	public static void init() {
		setProperties();
		String host = properties.getProperty("prodDbHost");
		String port = properties.getProperty("prodDbPort");
		String database = properties.getProperty("prodDbName");
		String userName = properties.getProperty("prodDbUserName");
		String password = properties.getProperty("prodDbPassword");
		dbBackup = properties.getProperty("prodDbBackup");
		BotDB.set(host + ":" + port, database, userName, password);
	}

	/** This is set on Bot initialization, before the JDA connection is made. */
	public static void initTest() {
		setProperties();
		String host = properties.getProperty("testDbHost");
		String port = properties.getProperty("testDbPort");
		String database = properties.getProperty("testDbName");
		String userName = properties.getProperty("testDbUserName");
		String password = properties.getProperty("testDbPassword");
		dbBackup = properties.getProperty("testDbBackup");
		BotDB.set(host + ":" + port, database, userName, password);
	}

	public static void set(String driver, String database, String userName, String password) {
		jdbc = new JDBC(driver, database, userName, password);
		Reporter.info(String.format("JDBC Set:[%s] Database:[%s] UserName:[%s] Password:[%s]",
				driver, database, userName, password));
	}

	/** Assumes a connection has already been made. */
	public static JDBC get() {
		return jdbc;
	}

	public static String getDbBackup() {
		return dbBackup;
	}

	private static void setProperties() {
		try {
			File file = new File("src/main/resources/config/database.properties");
			properties = new Properties();
			properties.load(new FileInputStream(file));
			Reporter.info("Loaded database properties.");
		} catch (Exception e) {
			Reporter.fatal("Failed to set database properties.", e);
		}
	}
}