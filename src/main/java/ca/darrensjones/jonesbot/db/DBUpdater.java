package ca.darrensjones.jonesbot.db;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-02-02
 * @since 1.1.4 2021-01-29
 */
public class DBUpdater {

	/**
	 * Executed from the command line using 'gradle database'
	 */
	public static void main(String[] args) {
		String test = "jonesbottest";
		BotDB.initTest();
		dropAllTables(test);
		updateToVersion(test);
		populateTable(test, BotDB.getDbBackup());

		String prod = "jonesbot";
		BotDB.init();
		dropAllTables(prod);
		updateToVersion(prod);
		populateTable(prod, BotDB.getDbBackup());
	}

	private static void dropAllTables(String databaseName) {
		try {
			Reporter.info(String.format("Dropping all tables from [%s].", databaseName));

			List<String> tables = new ArrayList<String>();
			ResultSet rs = BotDB.get().select(String.format("USE %s SELECT name FROM sys.tables;", databaseName));
			while (rs.next()) tables.add(rs.getString("name"));
			rs.getStatement().close();
			Reporter.info(String.format("Found [%s] tables in the database [%s].", tables.size(), databaseName));

			for (String table : tables) {
				String query = String.format("DROP TABLE [%s];", table);
				BotDB.get().execute(query);
				Reporter.info(String.format("JDBC Execute:[%s]", query));
			}

			Reporter.info(String.format("Dropped all tables from [%s].", databaseName));

		} catch (Exception e) {
			Reporter.fatal("DBUpdater dropAllTables.", e);
		}
	}

	private static void updateToVersion(String databaseName) {
		try {
			Reporter.info(String.format("Updating database [%s] to version [1.0.0]", databaseName));

			String[] queries = FileUtils.readFileToString(new File("src/main/resources/db/1_0_0.sql"), StandardCharsets.UTF_8).split("(?<=;)");

			for (String query : queries) {
				query = query.replaceAll("--[\\s\\S]+\\n", "").replaceAll("\\s+", " ").trim();
				BotDB.get().execute(query);
				Reporter.info(String.format("JDBC Execute:[%s]", query));
			}

			Reporter.info(String.format("Updated database [%s] to version [1.0.0]", databaseName));
		} catch (Exception e) {
			Reporter.fatal("DBUpdater updateToVersion.", e);
		}
	}

	private static void populateTable(String databaseName, String dataPath) {
		try {
			Reporter.info(String.format("Populating Database:[%s].", databaseName));

			String[] queries = FileUtils.readFileToString(new File(dataPath), StandardCharsets.UTF_8).split("(?<=;)");

			for (String query : queries) {
				query = query.replaceAll("--[\\s\\S]+\\n", "").replaceAll("\\s+", " ").trim();
				int updates = BotDB.get().update(query);
				Reporter.info(String.format("JDBC Execute, inserted [%s] Row(s):[%s]", updates, query));
			}

			Reporter.info(String.format("Populated Database:[%s].", databaseName));
		} catch (Exception e) {
			Reporter.fatal("DBUpdater populateTable.", e);
		}
	}
}