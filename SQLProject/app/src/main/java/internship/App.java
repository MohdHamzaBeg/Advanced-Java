package internship;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException {
		int[] id={1,2,3};
		String[] name ={"Java", "Python", "C"};
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String db = "jdbc:sqlite:FirstDB.db";
		var c = DriverManager.getConnection(db); // Here c is variable which contains connection
													// to our FirstDB database
		System.out.println(c);

		// Creating a table in our database
		var stmt = c.createStatement();
		var sql = "create table if not exists user(id integer primary key, name text not null)";
		// Every word written in the above double quotes are the commands of SQlite
		// which is creating
		// a columns of the first row named user in our local database with the help of
		// execute method
		stmt.execute(sql);

		// Adding data to the table
		// 1. Using SQL commmands- This method is for limited amount of data:-

		/*
		 * sql = "insert into user (id, name) values (0, 'Java')";
		 * stmt.execute(sql);
		 * sql = "insert into user (id, name) values (1, 'Python')";
		 * stmt.execute(sql);
		 * sql = "insert into user (id, name) values (2, 'C')";
		 * stmt.execute(sql); //
		 * // Printing the table- To print, we have to get a ResultSet object then print
		 * // the table in a loop set
		 * sql = "select name, id from user";
		 * var rs = stmt.executeQuery(sql);
		 * while (rs.next()) {
		 * int id = rs.getInt("id");
		 * String name = rs.getString("name");
		 * System.out.println("ID: " + id + "      name: " + name);
		 * }
		 */

		// 2. Using preparestatement method- This method is beneficial for large amount of data:-
		sql="insert into user (id, name) values (?, ?)";
		var prepstmt= c.prepareStatement(sql); // Making an object of preparestatement() method through 
											   // the object of getconnection() method
		for (int i = 0; i < 2; i++) {
			prepstmt.setInt(1, id[i]);
			prepstmt.setString(2,name[i]);
			prepstmt.executeUpdate(); // To print the data which was inserted through prepstmt method
		}
		sql = "select name, id from user";
		  var rs = stmt.executeQuery(sql);
		  while (rs.next()) {
		  int ids = rs.getInt("id");
		  String names = rs.getString("name");
		  System.out.println("ID: " + ids + "      name: " + names);
		  }

		// Deleting the table and closing the creating statement object as well as the 
		// connection with the database
		sql = "drop table user";
		stmt.execute(sql);
		stmt.close();
		c.close();
	}

}
