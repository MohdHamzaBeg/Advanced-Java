package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SwingDatabase {
	private static SwingDatabase db = new SwingDatabase();
	private static final String url = "jdbc:mysql://127.0.0.1:3306/swingdb?serverTimezone=UTC";
	private Connection conn;

	public static SwingDatabase ins() {
		return db;
	}

	private SwingDatabase() {

	}

	public void connect() throws SQLException {

	}

	public Connection getconnection() throws SQLException {
		conn = DriverManager.getConnection(url, "swing", "Hamza221230.");
		return conn;
	}

	public void disconnect() throws SQLException {
		conn.close();
	}
}
