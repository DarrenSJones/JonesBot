package ca.darrensjones.jonesbot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.darrensjones.jonesbot.log.Reporter;

/**
 * @author Darren Jones
 * @version 1.1.4 2021-01-29
 * @since 1.0.0 2020-11-18
 */
public class JDBC {

	private final String driver;
	private final String database;
	private final String userName;
	private final String password;
	private Connection connection;

	public JDBC(String driver, String database, String userName, String password) {
		this.driver = driver;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}

	private void createConnection() {
		String url = String.format("jdbc:sqlserver://%s;databaseName=%s;integratedSecurity=true;loginTimeout=10;", driver, database);
		Reporter.info(String.format("Creating JDBC Connection:[%s] UserName:[%s] Password:[%s]", url, userName, password));
		try {
			connection = DriverManager.getConnection(url, userName, password);
			Reporter.info(String.format("JDBC Connection created:[%s]", connection.getCatalog()));
		} catch (SQLException e) {
			Reporter.fatal("Failed to create JDBC Connection.\n" + e.getMessage());
		}
	}

	public Connection getConnection() {
		if (connection == null) createConnection();
		return connection;
	}

	public void execute(String sql) throws SQLException {
		Statement stmt = getConnection().createStatement();
		stmt.execute(sql);
		stmt.close();
	}

	public ResultSet select(String sql) throws SQLException {
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public int update(String sql) throws SQLException {
		Statement stmt = getConnection().createStatement();
		int updates = stmt.executeUpdate(sql);
		stmt.close();
		return updates;
	}
}