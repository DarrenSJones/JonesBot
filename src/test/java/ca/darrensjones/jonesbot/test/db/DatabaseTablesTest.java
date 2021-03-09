package ca.darrensjones.jonesbot.test.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-03-09
 * @since   1.0.0 2020-11-21
 */
public class DatabaseTablesTest {

	/** This test is updated for each new Table added. */
	@Test
	public void allTables() {
		String query = "SELECT * FROM " + "jonesbottest" + ".sys.tables ORDER BY name";
		List<String> tables = new ArrayList<String>();
		try {
			ResultSet rs = BotDB.get().select(query);
			while (rs.next()) {
				tables.add(rs.getString(1));
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.error("Tables allTables.", e);
		}

		Assert.assertEquals(tables.size(), 4);
		Assert.assertEquals(tables.get(0), "bot_config");
		Assert.assertEquals(tables.get(1), "frinkiac_saved");
		Assert.assertEquals(tables.get(2), "reaction");
		Assert.assertEquals(tables.get(3), "simple_schedule");
	}

	@Test(dependsOnMethods = "allTables", alwaysRun = true)
	public void bot_config() {
		List<String> columns = getColumnNames("bot_config");
		Assert.assertEquals(columns.size(), 2);
		Assert.assertEquals(columns.get(0), "item_key");
		Assert.assertEquals(columns.get(1), "item_value");
	}

	@Test(dependsOnMethods = "bot_config", alwaysRun = true)
	public void frinkiac_saved() {
		List<String> columns = getColumnNames("frinkiac_saved");
		Assert.assertEquals(columns.size(), 6);
		Assert.assertEquals(columns.get(0), "id");
		Assert.assertEquals(columns.get(1), "host_id");
		Assert.assertEquals(columns.get(2), "name");
		Assert.assertEquals(columns.get(3), "key");
		Assert.assertEquals(columns.get(4), "timestamp");
		Assert.assertEquals(columns.get(5), "regex");
	}

	// TODO rename the "reaction" table to "autoresponse_reaction"
	@Test(dependsOnMethods = "frinkiac_saved", alwaysRun = true)
	public void reaction() {
		List<String> columns = getColumnNames("reaction");
		Assert.assertEquals(columns.size(), 4);
		Assert.assertEquals(columns.get(0), "id");
		Assert.assertEquals(columns.get(1), "shortcode");
		Assert.assertEquals(columns.get(2), "unicode");
		Assert.assertEquals(columns.get(3), "regex");
	}

	@Test(dependsOnMethods = "reaction", alwaysRun = true)
	public void simple_schedule() {
		List<String> columns = getColumnNames("simple_schedule");
		Assert.assertEquals(columns.size(), 7);
		Assert.assertEquals(columns.get(0), "id");
		Assert.assertEquals(columns.get(1), "event_date");
		Assert.assertEquals(columns.get(2), "event_day_of_week");
		Assert.assertEquals(columns.get(3), "guild_id");
		Assert.assertEquals(columns.get(4), "channel_id");
		Assert.assertEquals(columns.get(5), "event_time");
		Assert.assertEquals(columns.get(6), "event_value");
	}

	/** A standard way of returning column names by Table. Not a test. */
	private List<String> getColumnNames(String tableName) {
		String query = "SELECT * FROM " + tableName;
		List<String> columns = new ArrayList<String>();
		try {
			ResultSet rs = BotDB.get().select(query);
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				columns.add(rs.getMetaData().getColumnName(i));
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.error("Tables getColumnNames.", e);
		}
		return columns;
	}
}