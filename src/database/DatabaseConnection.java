package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	private final static String connectionString = "jdbc:oracle:thin:@192.168.0.109:1521/traffic";
	private final static String username = "traffic";
	private final static String password = "traffic";
	private static java.sql.Connection conn = null;

	public static Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(connectionString, username, password);
			System.out.println("Connection is successful");
		} catch (ClassNotFoundException exception) {
			System.err.println("Driver class was not found");
		} catch (SQLException e) {
			System.err.println("Connection is not successfull ");
		}
		return conn;
	}

	public static Statement createStatement(Connection c) {

		Statement statement = null;
		try {
			statement = c.createStatement();

			System.out.println("Statement created");
			return statement;

		} catch (SQLException e) {

			System.err.println("Statement wasn't created");
		}
		return null;
	}

	public static PreparedStatement createPreparedStatement(String sql, int id) {
		conn = getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			System.out.println("Prepared Statement created");
			return statement;

		} catch (SQLException e) {

			System.err.println("Prepared Statement wasn't created");
		}
		return null;
	}

	public static PreparedStatement createPreparedStatement(Connection c, String sql) {
		conn = c;

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			System.out.println("Prepared Statement created");
			return statement;

		} catch (SQLException e) {

			System.err.println("Prepared Statement wasn't created");
		}
		return null;
	}

	public static ResultSet getResults(String query, int id) {

		PreparedStatement statement = DatabaseConnection.createPreparedStatement(query, id);

		try {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			System.out.println("Results succesfully retrieved");
			return resultSet;
		} catch (SQLException e1) {

			System.err.println("Results were not succesfully retrieved");
			e1.printStackTrace();
		}
		return null;

	}

	public static ResultSet getResults(Statement statement, String query) {

		// Connection connection = DatabaseConnection.getConnection();
		// Statement statement = DatabaseConnection.createStatement(connection);

		try {
			ResultSet resultSet = statement.executeQuery(query);
			System.out.println("Results succesfully retrieved");
			return resultSet;
		} catch (SQLException e1) {

			System.err.println("Results were not succesfully retrieved");
			e1.printStackTrace();
		}
		return null;

	}

	public static ResultSet getResults(PreparedStatement statement, String query, int id) {

		try {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery(query);
			System.out.println("Results succesfully retrieved");
			return resultSet;
		} catch (SQLException e1) {

			System.err.println("Results were not succesfully retrieved");
		}
		return null;

	}

	public static void closeResultset(ResultSet resultSet) {
		try {
			resultSet.close();
			System.out.println("Result Set closed succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Result Set  wasn't closed succesfully");
		}

	}

	public static void closeStatement(Statement statement) {
		try {
			statement.close();
			System.out.println("Statement closed succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Statement wasn't closed succesfully");
		}
	}

	public static void closeConnection(Connection connection) {

		try {
			connection.close();
			System.out.println("Connection closed succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection wasn't closed succesfully");
		}

	}

}
