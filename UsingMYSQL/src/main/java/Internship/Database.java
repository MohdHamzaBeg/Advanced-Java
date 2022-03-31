package Internship;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Database db = new Database();
	private static final String url = "jdbc:mysql://localhost:3306/internship?serverTimezone=UTC";
	private Connection conn;

	public static Database ins() {
		return db;
	}

	private Database() {

	}

	public void connect() throws SQLException {
		conn = DriverManager.getConnection(url, "root", "Hamza221230.");
	}

	public void disconnect() throws SQLException {
		conn.close();
	}
}