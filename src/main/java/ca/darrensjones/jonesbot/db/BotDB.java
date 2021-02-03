package ca.darrensjones.jonesbot.db;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-02-02
 * @since 1.0.0 2020-11-18
 */
public class BotDB {

	private static JDBC jdbc;
	private static Properties properties; // Only contains prod/test database information
	private static String dbBackup;

	/** This is set on Bot initialization, before the JDA connection is made. */
	public static void init() {
		setProperties();
		String driver = properties.getProperty("prodDbHost") + ":" + properties.getProperty("prodDbPort");
		String database = properties.getProperty("prodDbName");
		String userName = properties.getProperty("prodDbUserName");
		String password = properties.getProperty("prodDbPassword");
		dbBackup = properties.getProperty("prodDbBackup");
		BotDB.set(driver, database, userName, password);
	}

	/** This is set on Bot initialization, before the JDA connection is made. */
	public static void initTest() {
		setProperties();
		String driver = properties.getProperty("testDbHost") + ":" + properties.getProperty("testDbPort");
		String database = properties.getProperty("testDbName");
		String userName = properties.getProperty("testDbUserName");
		String password = properties.getProperty("testDbPassword");
		dbBackup = properties.getProperty("testDbBackup");
		BotDB.set(driver, database, userName, password);
	}

	public static void set(String driver, String database, String userName, String password) {
		jdbc = new JDBC(driver, database, userName, password);
		Reporter.info(String.format("JDBC Set:[%s] Database:[%s] UserName:[%s] Password:[%s]", driver, database, userName, password));
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
			properties = new Properties();
			properties.load(new FileInputStream(new File("src/main/resources/config/database.properties")));
			Reporter.info("Loaded database properties.");
		} catch (Exception e) {
			Reporter.fatal("Failed to set database properties.", e);
		}
	}
}