package ca.darrensjones.jonesbot.db.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-03
 * @since 1.0.0 2020-12-03
 */
public class CFrinkiacSaved {

	private static OFrinkiacSaved setRecord(ResultSet rs) throws SQLException {
		OFrinkiacSaved record = new OFrinkiacSaved();
		record.id = rs.getInt("id");
		record.name = rs.getString("name");
		record.key = rs.getString("key");
		record.timestamp = rs.getString("timestamp");
		record.regex = rs.getString("regex");
		record.host = rs.getString("host");
		record.color = rs.getString("color");
		return record;
	}

	public static List<OFrinkiacSaved> getAll() {
		List<OFrinkiacSaved> list = new ArrayList<OFrinkiacSaved>();
		try {
			ResultSet rs = BotDB.get().select(
					"SELECT s.id, [name], [key], timestamp, regex, host, color FROM frinkiac_saved s JOIN frinkiac_host h ON s.host_id = h.id ORDER BY s.id");
			while (rs.next()) list.add(setRecord(rs));
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		return list;
	}
}