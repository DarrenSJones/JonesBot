package ca.darrensjones.jonesbot.db;

import ca.darrensjones.jonesbot.log.Reporter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author  Darren Jones
 * @version 1.2.1 2021-02-22
 * @since   1.0.0 2020-11-18
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

	public void createConnection() {
		String url = String.format(
				"jdbc:sqlserver://%s;databaseName=%s;integratedSecurity=true;loginTimeout=10;",
				driver, database);
		Reporter.info(String.format("JDBC connecting. url:[%s] userName:[%s] password:[%s]", url,
				userName, password));
		try {
			connection = DriverManager.getConnection(url, userName, password);
			Reporter.info(String.format("JDBC connected. database:[%s]", connection.getCatalog()));
		} catch (SQLException e) {
			Reporter.fatal("Failed to create JDBC connection.", e);
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