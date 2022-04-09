package Internship;

// This class is the BTS of using and operating on Database and it is used by a user through
// App.java class
import java.sql.SQLException;
import java.util.Optional;

public class UserDao implements Dao {

	@Override
	public void save(Object t) {
		var conn = Database.ins().getconnection();
	}

	@Override
	public void update(Object t) {
		var conn = Database.ins().getconnection();
		try {
			var stmt= conn.prepareStatement("Update user set name=? where id=?");
			stmt.setString(1, ((User) t).getName());
			stmt.setInt(1, ((User) t).getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoExceptions(e);
		}
	}

	@Override
	public void delete(Object t) {
		var conn = Database.ins().getconnection();
		try {
			var stmt = conn.prepareStatement("delete from user where id=?");
			stmt.setInt(1, ((User) t).getId()); // setting first value of id=1
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoExceptions(e);
		}

	}

	@Override
	public Optional findbyId(int id) {
		var conn = Database.ins().getconnection();
		try {
			var stmt = conn.prepareStatement("select name from user where id=?");
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			if (rs.next()) {
				var name = rs.getString("name");
				User user = new User(id, name);
				return Optional.of(user);
			}

			stmt.close();
		} catch (SQLException e) {
			throw new DaoExceptions(e);
		}
		return Optional.empty();
	}

}
