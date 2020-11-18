package ca.darrensjones.jonesbot.db;

import java.util.HashMap;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class BotDB {

	private static final String defaultDB = "jonesbot";
	private static HashMap<String, JDBC> connections = new HashMap<String, JDBC>();

	public static JDBC get(String key) {
		if (connections.containsKey(key)) return connections.get(key);
		System.out.println(String.format("SQL Connection [%s] is not set.", key));
		return null;
	}

	public static JDBC get() {
		return get(defaultDB);
	}

	public static void init() {
		connections.clear();
		connections.put("jonesbot", new JDBC("localhost:1433", "jonesbot", "jonesbot", defaultDB));
		System.out.println("BotDB initialized.");
	}
}