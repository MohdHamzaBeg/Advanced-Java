package Internship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
	private Connection conn;
	private List<User> users;

	private static final int testusersno = 4;

	private List<User> loadUsers() throws IOException {
		// Taking texts
		// from my
		// assignment
		// and making a list of users from it.

		var temp = Files.lines(Paths.get("../Assignment 1.rtf")).map(line -> line.split("[^A-Za-z]"))
				.map(Arrays::asList).flatMap(list -> list.stream())
				.filter(word -> word.length() > 3 && word.length() < 20).map(word -> new User(word)).limit(testusersno)
				.collect(Collectors.toList());
		// temp.forEach(System.out::println);
		return temp;
	}

	@Before
	public void setUp() throws SQLException, IOException {
		users = loadUsers();
		// System.out.println(users);
		// System.out.println(users.size());
		conn = TestDatabase.ins().getconnection();

		System.out.println("Connection made");
		conn.setAutoCommit(false);
	}

	@After
	public void tearDown() throws SQLException {
		TestDatabase.ins().disconnect();
		System.out.println("Connection lost");
	}

	@Test
	public void testdelete() throws SQLException {
		UserDao ud = new UserDao();

		for (var u : users) {
			// ud.save(u);
		}
		var maxID = getMaxID();
		for (int i = 0; i < users.size(); i++) {
			int id = (maxID - users.size()) + i + 1;
			users.get(i).setId(id);
		}
		var deleteuserIndex = testusersno / 2;
		var deleteuser = users.get(deleteuserIndex);
		users.remove(deleteuser);
		System.out.println(deleteuser);
		System.out.println(users);
		ud.delete(deleteuser);
		var ru = getUsersInRange((maxID - testusersno) + 1, maxID);
		System.out.println(ru);
		assertEquals("size of list is not equal to number of test users", ru.size(), users.size());
		assertEquals("list does not match with saved users", users, ru);

	}

	@Test
	public void testFindbyidandUpdate() throws SQLException {
		var user = users.get(0);
		UserDao ud = new UserDao();
		var maxID = getMaxID();
		user.setId(maxID);
		var ruopt = ud.findbyId(1000);
		assertTrue("no user retrieved", ruopt.isPresent());
		var retrieveduser = ruopt.get();
		assertEquals("retrieved user doesn't match with 0th user", user, retrieveduser);
		user.setName("abcde");
		ud.update(user);
		ruopt = ud.findbyId(maxID);
		assertTrue("no user retrieved", ruopt.isPresent());
		retrieveduser = ruopt.get();
		assertEquals("Updated user doesn't match with 0th user", user, retrieveduser);

	}

	private int getMaxID() throws SQLException {
		var stmt = conn.createStatement();
		var rs = stmt.executeQuery("select max(id) as id from user");
		rs.next();
		var id = rs.getInt("id");
		stmt.close();
		return id;
	}

	private List<User> getUsersInRange(int minID, int maxID) throws SQLException {
		List<User> retrieved = new ArrayList<User>();
		var stmt = conn.prepareStatement("select id, name from user where id>= ? and id<= ?");
		stmt.setInt(1, minID);
		stmt.setInt(2, maxID);
		var rs = stmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			var user = new User(id, name);
			retrieved.add(user);
		}
		stmt.close();
		return retrieved;
	}

	// @Test
	public void testsavemultiple() throws SQLException {
		UserDao ud = new UserDao();

		for (var u : users) {
			ud.save(u);
		}
		var maxID = getMaxID();
		for (int i = 0; i < users.size(); i++) {
			int id = (maxID - users.size()) + i + 1;
			users.get(i).setId(id);
		}
		var ru = getUsersInRange((maxID - users.size()) + 1, maxID);
		assertEquals("size of list is not equal to number of test users", ru.size(), testusersno);
		assertEquals("list does not match with saved users", users, ru);

	}

	// @Test
	public void testSave() throws SQLException { // Testing the save method of my UserDao class
		UserDao ud = new UserDao();
		User user = new User("Javascript");
		// ud.save(user);
		var stmt = conn.createStatement();
		var rs = stmt.executeQuery("select id, name from user order by id desc");
		var result = rs.next();
		assertTrue("Cannot retrieve inserted user", result);
		var name = rs.getString("name");
		assertEquals("User name does not match retrieved", user.getName(), name);
		stmt.close();
	}

}
