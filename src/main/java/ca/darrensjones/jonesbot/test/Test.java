package ca.darrensjones.jonesbot.test;

import ca.darrensjones.jonesbot.db.JDBCConnector;

public class Test {

	public static void main(String[] args) {
		JDBCConnector j = new JDBCConnector("", "", "");
		j.Test();
	}
}