package ca.darrensjones.jonesbot.db.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.db.model.OReaction;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class CReaction {

	private static OReaction setRecord(ResultSet rs) throws SQLException {
		OReaction record = new OReaction();
		record.id = rs.getInt("id");
		record.shortcode = rs.getString("shortcode");
		record.unicode = rs.getString("unicode");
		record.regex = rs.getString("regex");
		return record;
	}

	public static List<OReaction> getAll() {
		List<OReaction> list = new ArrayList<OReaction>();
		try {
			ResultSet rs = BotDB.get().select("SELECT id, shortcode, unicode, regex FROM reaction");
			while (rs.next()) list.add(setRecord(rs));
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		return list;
	}
}