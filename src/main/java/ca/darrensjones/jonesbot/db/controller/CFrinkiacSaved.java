package ca.darrensjones.jonesbot.db.controller;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.db.model.OFrinkiacSaved;
import ca.darrensjones.jonesbot.log.Reporter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-18
 * @since   1.0.0 2020-12-03
 */
public class CFrinkiacSaved {

	private static OFrinkiacSaved setRecord(ResultSet rs) throws SQLException {
		OFrinkiacSaved record = new OFrinkiacSaved();
		record.id = rs.getInt("id");
		record.name = rs.getString("name");
		record.key = rs.getString("key");
		record.timestamp = rs.getString("timestamp");
		record.regex = rs.getString("regex");
		return record;
	}

	public static List<OFrinkiacSaved> getById(String id) {
		List<OFrinkiacSaved> list = new ArrayList<OFrinkiacSaved>();
		try {
			ResultSet rs = BotDB.get().select(
					"SELECT id, [name], [key], timestamp, regex FROM frinkiac_saved WHERE host_id = "
							+ id + " Order By id");
			while (rs.next()) {
				list.add(setRecord(rs));
			}
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.error("CFrinkiacSaved getById.", e);
		}
		return list;
	}
}