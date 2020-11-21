package ca.darrensjones.jonesbot.db;

import java.util.HashMap;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-21
 * @since 1.0.0 2020-11-18
 */
public class BotDB {

	private static String defaultDB = "jonesbot";
	private static HashMap<String, JDBC> connections = new HashMap<String, JDBC>();

	public static JDBC get(String database) {
		if (connections.containsKey(database)) return connections.get(database);
		Reporter.info(String.format("BotDB connection [%s] is not set in the list.", database));
		return null;
	}

	public static JDBC get() {
		return get(defaultDB);
	}

	public static String getDefaultDB() {
		return defaultDB;
	}

	public static void init() {
		connections.clear();
		connections.put(defaultDB, new JDBC("localhost:1433", "jonesbot", "jonesbot", defaultDB));
		Reporter.info("BotDB connection list set.");
	}

	public static void test() {
		defaultDB = "jonesbottest";
	}
}