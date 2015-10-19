package database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Course;
import model.Student;

public class StudentDA implements Serializable {

	public StudentDA() {
	}

	public Student retrieveStudent(int id) {
		// Connection connection = DatabaseConnection.getConnection();

		String sql = "SELECT * FROM TEST_STUDENT WHERE ID = ?";
		String sql1 = "SELECT CRS_ID FROM TEST_STD_CRS WHERE STD_ID = ?";
		Student student = new Student();
		PreparedStatement statement = DatabaseConnection.createPreparedStatement(sql, id);
		PreparedStatement statement2 = DatabaseConnection.createPreparedStatement(sql1, id);
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				student.setId(resultSet.getInt("ID"));
				student.setFirstName(resultSet.getString("FIRST_NAME"));
				student.setLastName(resultSet.getString("LAST_NAME"));
				student.setAge(resultSet.getInt("AGE"));
				student.setMark(resultSet.getInt("MARK"));
				student.setGender(resultSet.getInt("GENDER"));

			}
			resultSet.close();
			// For testing
			System.out.println("Student was succesfully retrieved with ID of " + student.getId());

		} catch (SQLException e) {

			// For testing
			System.out.println("Student was not succesfully retrieved");
			return null;
		}

		try {
			resultSet = statement2.executeQuery();
			ArrayList<Integer> courseIds = new ArrayList<Integer>();

			while (resultSet.next()) {
				courseIds.add(resultSet.getInt("CRS_ID"));
			}

			int[] courseIds_ = new int[courseIds.size()];
			for (int i = 0; i < courseIds_.length; i++) {
				courseIds_[i] = courseIds.get(i);
			}

			student.setCoursesId(courseIds_);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student;

	}

	public Course[] getStudentCourses(Student student) {

		ArrayList<Course> courses = new ArrayList<Course>();
		Course course = new Course();
		CourseDA courseDA = new CourseDA();

		String sql = "SELECT * FROM TEST_STD_CRS WHERE STD_ID = ?";
		ResultSet resultSet = DatabaseConnection.getResults(sql, student.getId());

		try {

			while (resultSet.next()) {
				int i = resultSet.getInt("CRS_ID");
				course = courseDA.getCourse(i);
				if (course != null) {
					courses.add(course);
				}

			}
			System.out.println("Student courses were retrieved successfully");

			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Student course was NOT retrieved successfully");
		}

		Course[] courses_ = new Course[courses.size()];
		for (int i = 0; i < courses_.length; i++) {
			courses_[i] = courses.get(i);
		}

		return courses_;
	}

	public void deleteStudent(int id) {

		Connection connection = DatabaseConnection.getConnection();
		String sql1 = "DELETE FROM TEST_STUDENT WHERE ID = ?";
		String sq12 = "DELETE FROM TEST_STD_CRS WHERE STD_ID =?";
		PreparedStatement statement1 = DatabaseConnection.createPreparedStatement(sql1, id);
		PreparedStatement statement2 = DatabaseConnection.createPreparedStatement(sq12, id);
		try {

			System.out.println(connection.getAutoCommit());
			connection.setAutoCommit(false);
			statement1.executeUpdate();
			statement2.executeUpdate();
			connection.commit();
			System.out.println("Row deleted");

		} catch (SQLException e) {

			System.err.println("A problem in preparing the statement for deletion");

		} finally {
			try {
				statement1.close();
				statement2.close();
				connection.close();
				System.out.println("Connections closed succesfully for deleteStudent()");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Connections was not closed succesfully for deleteStudent()");
			}

		}

	}

	public Student updateStudent(Student std, int[] coursesIdsAfter) {

		Connection connection = DatabaseConnection.getConnection();

		String updateStd = "UPDATE TEST_STUDENT SET FIRST_NAME = ?, LAST_NAME = ? , AGE = ?, MARK = ?, GENDER = ? WHERE ID = ?";

		// Updating TEST_STUDENT
		PreparedStatement preparedStatement1 = DatabaseConnection.createPreparedStatement(connection, updateStd);
		try {

			connection.setAutoCommit(false);
			preparedStatement1.setString(1, std.getFirstName());
			preparedStatement1.setString(2, std.getLastName());
			preparedStatement1.setInt(3, std.getAge());
			preparedStatement1.setInt(4, std.getMark());
			preparedStatement1.setInt(5, std.getGender());
			preparedStatement1.setInt(6, std.getId());
			preparedStatement1.executeUpdate();
			DatabaseConnection.closeStatement(preparedStatement1);
			System.out.println("Student was updated successfully in STD table");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Student was not successfully updated");
			e.printStackTrace();
			return null;
		}

		// Deleting from TEST_STD_CRS
		String deleteSTDCrs = "DELETE FROM TEST_STD_CRS WHERE STD_ID = ?";
		PreparedStatement preparedStatement2 = DatabaseConnection.createPreparedStatement(connection, deleteSTDCrs);

		try {
			preparedStatement2.setInt(1, std.getId());
			preparedStatement2.executeUpdate();
			DatabaseConnection.closeStatement(preparedStatement2);
			System.out.println("Student was deleted from TEST_STD_CRS ");
		} catch (SQLException exception) {
			System.err.println("Student was NOT deleted from TEST_STD_CRS ");
		}

		// Inserting into TEST_STD_CRS again
		String insertToCrs = "INSERT INTO TEST_STD_CRS (ID, CRS_ID, STD_ID) VALUES (CRS_STD.nextval,?,?)";
		PreparedStatement preparedStatement3 = DatabaseConnection.createPreparedStatement(connection, insertToCrs);

		try {

			for (int i = 0; i < coursesIdsAfter.length; i++) {

				if (!(coursesIdsAfter[i] == 0)) {
					preparedStatement3.setInt(1, coursesIdsAfter[i]);
					preparedStatement3.setInt(2, std.getId());
					preparedStatement3.executeUpdate();
				}
			}
			connection.commit();
			Student std1 = retrieveStudent(std.getId());
			System.out.println("First name after update  " + std1.getFirstName());
			System.out.println("last name after update  " + std1.getLastName());
			System.out.println("Age after update  " + std1.getAge());
			System.out.println("Mark after update  " + std1.getMark());
			System.out.println("Gender after update  " + std1.getGender());
			System.out.println("Courses after update " + std.getCourseIds().length);
			System.out.println("Student successfully updated");
			return std;

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Student was not successfully updated");
			e.printStackTrace();
			return null;
		}

	}

	public void Create(Student student, int[] coursesids) {

		Connection connection = DatabaseConnection.getConnection();
		String insert = "INSERT INTO TEST_STUDENT (ID, FIRST_NAME, LAST_NAME,AGE,MARK,GENDER) VALUES (?, ?, ?, ?, ?, ?)";
		String insert1 = "INSERT INTO TEST_STD_CRS (ID, CRS_ID, STD_ID) VALUES (CRS_STD.nextval,?,?)";

		PreparedStatement statement = DatabaseConnection.createPreparedStatement(connection, insert);

		try {
			// Inserting to Students table
			connection.setAutoCommit(false);
			statement.setInt(1, getSeq());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getAge());
			statement.setInt(5, student.getMark());
			statement.setInt(6, student.getGender());
			statement.executeUpdate();

			DatabaseConnection.closeStatement(statement);

			System.out.println("Student was created successfully");

		} catch (SQLException e) {

			System.out.println("Student was not created successfully");
		}

		try {
			// Inserting to TEST_CRS_STD table

			PreparedStatement statement1 = DatabaseConnection.createPreparedStatement(connection, insert1);
			for (int i = 0; i < coursesids.length; i++) {

				statement1.setInt(1, coursesids[i]);
				statement1.setInt(2, student.getId());
				statement1.executeUpdate();
			}

			connection.commit();
			DatabaseConnection.closeStatement(statement1);

			System.out.println("Student was added to TEST_CRS_STD successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Student was NOT added to TEST_CRS_STD successfully");
		}

	}

	public int getSeq() {

		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = DatabaseConnection.createPreparedStatement(connection,
				"SELECT TEST_STD.NEXTVAL FROM DUAL");
		ResultSet resultSet;
		int id = 0;
		try {
			resultSet = statement.executeQuery();
			id = 0;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			return id;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("std sequence was not retrieved succesfully");
			return 0;
		}

	}

	public ArrayList<Student> getStudents() {

		Connection connection = DatabaseConnection.getConnection();
		Statement statement1 = DatabaseConnection.createStatement(connection);
		ResultSet rsStudents = DatabaseConnection.getResults(statement1, "SELECT * FROM TEST_STUDENT");

		ArrayList<Student> arrayList = new ArrayList<Student>();

		// Getting data from ResultSet
		// filling the array with result data

		Student student = null;
		try {
			while (rsStudents.next()) {
				student = new Student();
				student.setId(rsStudents.getInt("ID"));
				student.setFirstName(rsStudents.getString("FIRST_NAME"));
				student.setLastName(rsStudents.getString("LAST_NAME"));
				student.setAge(rsStudents.getInt("AGE"));
				student.setMark(rsStudents.getInt("MARK"));
				student.setGender(rsStudents.getInt("GENDER"));
				arrayList.add(student);
			}
			return arrayList;
		} catch (SQLException e) {

			System.err.println("Array of results were not succesfully retrieved");
			return null;
		} finally {
			try {
				statement1.close();
				rsStudents.close();
				rsStudents.close();
				System.out.println("Closing connections for getStudents() was succesfull");
			} catch (SQLException e) {

				System.err.println("Closing connections for getStudents() was NOT succesfull");

			}

		}

	}

	public int[] getStudentCoursesIds(String[] strings) {
		Connection connection = DatabaseConnection.getConnection();
		int[] coursesids = new int[strings.length];
		Course course;
		try {

			for (int i = 0; i < strings.length; i++) {
				course = new Course();
				PreparedStatement preparedStatement = DatabaseConnection.createPreparedStatement(connection,
						"select * from TEST_COURSE where id = ?");
				preparedStatement.setInt(1, Integer.valueOf(strings[i]));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					course.setId(resultSet.getInt(1));
					course.setName(resultSet.getString(2));
				}
				coursesids[i] = course.getId();
			}

			return coursesids;
		} catch (SQLException e) {

			System.err.println("Courses was not succesfully retrieved");
			return null;
		}

	}

}
