package ca.darrensjones.jonesbot.db;

import ca.darrensjones.jonesbot.log.Reporter;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-22
 * @since   1.0.0 2020-11-18
 */
public class BotDB {

	private static JDBC jdbc;
	private static String dbBackup;

	/** Set on Bot initialization, before the JDA connection is made. */
	public static void init() {
		Properties properties = readProperties();

		String host = properties.getProperty("prodDbHost");
		String port = properties.getProperty("prodDbPort");
		String database = properties.getProperty("prodDbName");
		String userName = properties.getProperty("prodDbUserName");
		String password = properties.getProperty("prodDbPassword");

		set(host + ":" + port, database, userName, password);
		dbBackup = properties.getProperty("prodDbBackup");
		Reporter.info("BotDB initialized.");
	}

	/** Set on Bot Test initialization, before the JDA connection is made. */
	public static void initTest() {
		Properties properties = readProperties();

		String host = properties.getProperty("testDbHost");
		String port = properties.getProperty("testDbPort");
		String database = properties.getProperty("testDbName");
		String userName = properties.getProperty("testDbUserName");
		String password = properties.getProperty("testDbPassword");

		set(host + ":" + port, database, userName, password);
		dbBackup = properties.getProperty("testDbBackup");
		Reporter.info("BotDB test initialized.");
	}

	public static void set(String driver, String database, String userName, String password) {
		jdbc = new JDBC(driver, database, userName, password);
		Reporter.info(
				String.format("JDBC set. driver:[%s] database:[%s] userName:[%s] password:[%s]",
						driver, database, userName, password));
	}

	public static JDBC get() {
		return jdbc;
	}

	public static String getDbBackup() {
		return dbBackup;
	}

	private static Properties readProperties() {
		File file = new File("src/main/resources/config/database.properties");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
			Reporter.info("Database properties read.");
		} catch (Exception e) {
			Reporter.fatal("Database properties failed to read.", e);
		}
		return properties;
	}
}