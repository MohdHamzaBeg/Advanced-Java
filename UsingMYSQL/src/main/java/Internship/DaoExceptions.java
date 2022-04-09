package Internship;

import java.sql.SQLException;

public class DaoExceptions extends RuntimeException {

	/**
	 * This is UID below is created jsut to ignore warning of inheriting and exception class 
	 */
	private static final long serialVersionUID = 1L;

	public DaoExceptions(SQLException e) {
		super(e);
	}
}
