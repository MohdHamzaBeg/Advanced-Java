package Internship;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws SQLException {
		var a = Database.ins();
		a.connect();
		System.out.println("Database is connected now");
		a.disconnect();
		System.out.println("Database is disconnected now");
	}
}
