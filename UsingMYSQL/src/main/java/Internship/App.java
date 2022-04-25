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
		UserDao userdao = new UserDao();

		// Saving Users
		// userdao.save(new User("Java"));
		// userdao.save(new User("Python"));

		// Retrieving a user by id
		var users = userdao.findbyId(3);
		if (users.isPresent()) {
			Object u = users.get();
			System.out.println("Retrieved " + users);

			// Updating user
			((User) u).setName("MYSQL");
			userdao.update(u);
		} else {
			System.out.println("No user retrieved");
		}

		// Deleting a user
		userdao.delete(new User(3, null));

		a.disconnect();
		System.out.println("Database is disconnected now");
	}
}
