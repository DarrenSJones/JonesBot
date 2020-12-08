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
 * @version 1.0.0 2020-12-08
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
		return record;
	}

	public static List<OFrinkiacSaved> getById(String id) {
		List<OFrinkiacSaved> list = new ArrayList<OFrinkiacSaved>();
		try {
			ResultSet rs = BotDB.get().select("SELECT id, [name], [key], timestamp, regex FROM frinkiac_saved WHERE host_id = " + id + " Order By id");
			while (rs.next()) list.add(setRecord(rs));
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		return list;
	}

	public static List<OFrinkiacSaved> getAll() {
		List<OFrinkiacSaved> list = new ArrayList<OFrinkiacSaved>();
		try {
			ResultSet rs = BotDB.get().select("SELECT id, [name], [key], timestamp, regex FROM frinkiac_saved ORDER BY id");
			while (rs.next()) list.add(setRecord(rs));
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		return list;
	}
}