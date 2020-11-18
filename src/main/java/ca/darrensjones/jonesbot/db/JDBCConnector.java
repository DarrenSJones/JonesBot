package ca.darrensjones.jonesbot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Darren Jones
 * @version 1.0.0 2020-11-17
 * @since 1.0.0 2020-11-17
 */
public class JDBCConnector {

	private String driver;
	private String userName;
	private String password;

	public JDBCConnector(String driver, String userName, String password) {
		this.driver = driver;
		this.userName = userName;
		this.password = password;
	}

	public void Test() {

		String url = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;loginTimeout=30;";

		try (Connection connection = DriverManager.getConnection(url); Statement stmt = connection.createStatement();) {
			System.out.println("CONNECTION CONNECTED!");

			String sql = "SELECT TOP 10 * FROM JonesBot.dbo.TestTable";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("key"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}