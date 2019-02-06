package JDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDao {

	private Connection connection;

	public BaseDao() {

		try {
			connection = ConnectionDB.getConnection();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	protected Connection getConnection() {
		return connection;
	}

	protected void setConnection(Connection connection) {
		this.connection = connection;
	}

}
