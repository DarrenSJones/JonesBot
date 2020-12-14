package ca.darrensjones.jonesbot.db.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.darrensjones.jonesbot.db.BotDB;
import ca.darrensjones.jonesbot.db.model.OVersion;
import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-12-14
 * @since 1.0.0 2020-12-14
 */
public class CVersion {

	private static OVersion setRecord(ResultSet rs) throws SQLException {
		OVersion record = new OVersion();
		record.major = rs.getInt("major");
		record.minor = rs.getInt("minor");
		record.patch = rs.getInt("patch");
		record.date = rs.getDate("date").toLocalDate();
		record.changes = rs.getString("changes");
		return record;
	}

	public static List<OVersion> getAll() {
		List<OVersion> list = new ArrayList<OVersion>();
		try {
			ResultSet rs = BotDB.get().select("SELECT major, minor, patch, date, changes FROM version");
			while (rs.next()) list.add(setRecord(rs));
			rs.getStatement().close();
		} catch (Exception e) {
			Reporter.fatal(e.getMessage());
		}
		return list;
	}
}