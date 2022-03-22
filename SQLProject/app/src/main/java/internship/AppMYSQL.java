// This file is incomplete and throws error due to some issue with the URL. Don't run it


package internship;

import java.sql.DriverManager;
import java.sql.SQLException;

public class AppMYSQL {

	public static void main(String[] args) throws SQLException {
		int[] id={1,2,3};
		String[] name ={"Java", "Python", "C"};
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String db = "jdbc:mysql//root@localhost:3306/Internship";
		var c = DriverManager.getConnection(db,"root", "Hamza221230."); // Here c is variable which contains connection
													// to our FirstDB database
		System.out.println(c);

		var stmt = c.createStatement();
		var sql="insert into user (id, name) values (?, ?)";
		var prepstmt= c.prepareStatement(sql); // Making an object of preparestatement() method through 
											   // the object of getconnection() method
		for (int i = 0; i < 2; i++) {
			prepstmt.setInt(1, id[i]);
			prepstmt.setString(2,name[i]);
		}
		sql = "select name, id from user";
		  var rs = stmt.executeQuery(sql);
		  while (rs.next()) {
		  int ids = rs.getInt("id");
		  String names = rs.getString("name");
		  System.out.println("ID: " + ids + "      name: " + names);
		  }
		  stmt.execute(sql);
			stmt.close();


	}

}
