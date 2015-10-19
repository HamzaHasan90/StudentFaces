package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Course;

public class CourseDA {

	public ArrayList<Course> getAllCourses() {

		Connection connection = DatabaseConnection.getConnection();

		String sql = "select * from TEST_COURSE";
		PreparedStatement preparedStatement = DatabaseConnection.createPreparedStatement(connection, sql);
		ResultSet rsCourses = DatabaseConnection.getResults(preparedStatement,sql);
		List<Course> list = new ArrayList<Course>();
		Course course;
		try {
			rsCourses = preparedStatement.executeQuery();
			while (rsCourses.next()) {
				course = new Course();
				course.setId(rsCourses.getInt(1));
				course.setName(rsCourses.getString(2));
				list.add(course);
			}

			return (ArrayList<Course>) list;

		} catch (SQLException e) {

			System.err.println("Results were not succesfully retrieved");
			return null;
		}

	}

	public Course getCourse(int id) {

		Connection connection = DatabaseConnection.getConnection();
		Course course = new Course();
		try {
			PreparedStatement preparedStatement = DatabaseConnection.createPreparedStatement(connection,
					"select * from TEST_COURSE where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				course.setId(resultSet.getInt(1));
				course.setName(resultSet.getString(2));
			}

			return course;

		} catch (SQLException e)

		{

			System.err.println("Course was not succesfully retrieved");
			return null;
		}

	}

	

}
