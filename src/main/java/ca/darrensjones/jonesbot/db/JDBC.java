package ca.darrensjones.jonesbot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-18
 * @since 1.0.0 2020-11-18
 */
public class JDBC {

	private final String driver;
	private final String userName;
	private final String password;
	private final String database;
	private Connection c;

	public JDBC(String driver, String userName, String password, String database) {
		this.driver = driver;
		this.userName = userName;
		this.password = password;
		this.database = database;
	}

	private Connection createConnection() {
		String url = String.format("jdbc:sqlserver://%s;databaseName=%s;integratedSecurity=true;loginTimeout=30;", driver, database);
		System.out.println(String.format("Creating new JDBC Connection:[%s] UserName:[%s] Password:[%s]", url, userName, password));
		try {
			return DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return null;
	}

	public Connection getConnection() {
		if (c == null) c = createConnection();
		return c;
	}

	public ResultSet select(String sql) throws SQLException {
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
}