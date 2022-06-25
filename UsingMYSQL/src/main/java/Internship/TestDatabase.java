 package Internship;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabase {
	private static TestDatabase db = new TestDatabase();
	private static final String url = "jdbc:mysql://127.0.0.1:3306/intertest?serverTimezone=UTC";
	private Connection conn;

	public static TestDatabase ins() {
		return db;
	}

	private TestDatabase() {
		
	}

	public void connect() throws SQLException {
		
	}
	public Connection getconnection() throws SQLException {
		conn = DriverManager.getConnection(url, "test1", "Hamza221230.");
		return conn;
	}


	public void disconnect() throws SQLException {
		conn.close();
	}
}