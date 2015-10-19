package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import database.CourseDA;
import database.StudentDA;
import model.Course;
import model.Student;

public class StudentBA implements Serializable {

	private String errorFName;
	private String errorLName;
	private String errorAgeIntegerMessage;
	private String errorMarkIntegerMessage;
	private String ageErrorMessage;
	private String markErrorMessage;
	private StudentDA studentDA;
	private Student student;
	private CourseDA courseDA;

	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		studentDA = new StudentDA();

		students = studentDA.getStudents();

		return students;
	}

	public void deleteStudent(int id) {
		studentDA = new StudentDA();
		studentDA.deleteStudent(id);
	}
	
	public void updateStudent(Student student, int[] courseIds){
		studentDA = new StudentDA();
		studentDA.updateStudent(student, courseIds);
		
	}
	
	
	public void createStudent(Student student, int[] courseIds){
		studentDA = new StudentDA();
		studentDA.Create(student, courseIds);
	}
	
	public Student retrieveStudent(int i){
		studentDA = new StudentDA();
		student = studentDA.retrieveStudent(i);
		
		return student;
	}
	
	
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		courseDA = new CourseDA();

		courses = courseDA.getAllCourses();

		return courses;
	}
	

	public boolean isNumeric(String str) {
		try {
			int d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/*
	 * public int createStudent(HttpServletRequest request, HttpServletResponse
	 * response, beans.StudentBean studentBean) throws ServletException,
	 * IOException {
	 * 
	 * courseDA = new CourseDA(); da = new StudentDA(); int stdId = da.getSeq();
	 * 
	 * String firstName = request.getParameter("firstName"); String lastName =
	 * request.getParameter("lastName"); String age_ =
	 * request.getParameter("age"); String mark_ = request.getParameter("mark");
	 * String gender = request.getParameter("gender");
	 * 
	 * 
	 * String firstName = studentBean.getFirstName(); String lastName =
	 * studentBean.getLastName(); String age_ =
	 * String.valueOf(studentBean.getAge()); String mark_ =
	 * String.valueOf(studentBean.getMark()); String gender =
	 * String.valueOf(studentBean.getGender());
	 * 
	 * int[] courses = studentBean.getCourseIds();
	 * 
	 * int[] coursesIds = new int[courses.length]; for (int i = 0; i <
	 * coursesIds.length; i++) { coursesIds[i] = Integer.valueOf(courses[i]); }
	 * 
	 * System.out.println("number of retrieved courses is " +
	 * coursesIds.length);
	 * 
	 * if (checkforInteger(request, response)) {
	 * 
	 * student = new Student(); student.setId(stdId);
	 * student.setFirstName(firstName); student.setLastName(lastName);
	 * student.setAge(Integer.valueOf(studentBean.getAge()));
	 * student.setMark(Integer.valueOf(studentBean.getMark()));
	 * student.setGender(Integer.valueOf(studentBean.getGender()));
	 * 
	 * student.setCoursesId(coursesIds);
	 * 
	 * da.Create(student, coursesIds); return 1; } else { ArrayList<Course>
	 * AllCourses_ = courseDA.getAllCourses(); request.setAttribute("firstName",
	 * firstName); request.setAttribute("lastName", lastName);
	 * request.setAttribute("age", age_); request.setAttribute("mark", mark_);
	 * request.setAttribute("gender", gender); request.setAttribute("courses",
	 * AllCourses_);
	 * request.getRequestDispatcher("/WEB-INF/createStudent.jsp").forward(
	 * request, response); return 0; }
	 * 
	 * }
	 * 
	 * public void updateStudent(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * courseDA = new CourseDA(); da = new StudentDA(); int stdId =
	 * Integer.valueOf(request.getParameter("stdId"));
	 * 
	 * String[] coursesIds = request.getParameterValues("coursesIds");
	 * 
	 * String firstName = request.getParameter("firstName"); String lastName =
	 * request.getParameter("lastName");
	 * 
	 * String gender = request.getParameter("gender");
	 * 
	 * if (checkforInteger(request, response)) {
	 * 
	 * int age = Integer.valueOf(request.getParameter("age")); int mark =
	 * Integer.valueOf(request.getParameter("mark"));
	 * 
	 * ArrayList<Integer> editedCoursesIds = new ArrayList<Integer>(); for (int
	 * i = 0; i < coursesIds.length; i++) {
	 * 
	 * if (isInteger(coursesIds[i])) {
	 * 
	 * editedCoursesIds.add(Integer.valueOf(coursesIds[i])); } }
	 * 
	 * int[] editedCoursesIds_ = new int[editedCoursesIds.size()];
	 * 
	 * for (int i = 0; i < editedCoursesIds_.length; i++) {
	 * 
	 * editedCoursesIds_[i] = Integer.valueOf(coursesIds[i]);
	 * 
	 * }
	 * 
	 * Student updatedStudent = new Student(); updatedStudent.setId(stdId);
	 * updatedStudent.setFirstName(firstName);
	 * updatedStudent.setLastName(lastName); updatedStudent.setAge(age);
	 * updatedStudent.setMark(mark);
	 * updatedStudent.setGender(Integer.valueOf(gender));
	 * updatedStudent.setCoursesId(editedCoursesIds_);
	 * 
	 * da.updateStudent(updatedStudent, editedCoursesIds_);
	 * 
	 * } else { ArrayList<Course> courses = courseDA.getAllCourses(); Student
	 * student = da.retrieveStudent(stdId); Course[] studentCourses =
	 * da.getStudentCourses(student);
	 * 
	 * request.setAttribute("student", student);
	 * 
	 * request.setAttribute("courses", studentCourses); //
	 * request.setAttribute("studentCourses", studentCourses);
	 * request.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(
	 * request, response);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public int updateStudent(HttpServletRequest request, HttpServletResponse
	 * response, beans.StudentBean studentBean) throws ServletException,
	 * IOException {
	 * 
	 * courseDA = new CourseDA(); da = new StudentDA(); int stdId =
	 * studentBean.getId();
	 * 
	 * int[] coursesIds = studentBean.getCourseIds();
	 * 
	 * String firstName = studentBean.getFirstName(); String lastName =
	 * studentBean.getLastName();
	 * 
	 * String gender = request.getParameter("gender");
	 * 
	 * if (checkforInteger(request, response, studentBean)) {
	 * 
	 * int age = Integer.valueOf(request.getParameter("age")); int mark =
	 * Integer.valueOf(request.getParameter("mark"));
	 * 
	 * ArrayList<Integer> editedCoursesIds = new ArrayList<Integer>(); for (int
	 * i = 0; i < coursesIds.length; i++) {
	 * 
	 * if (coursesIds[i] != 0) {
	 * 
	 * editedCoursesIds.add(coursesIds[i]); } }
	 * 
	 * int[] editedCoursesIds_ = new int[editedCoursesIds.size()];
	 * 
	 * for (int i = 0; i < editedCoursesIds_.length; i++) {
	 * 
	 * editedCoursesIds_[i] = Integer.valueOf(coursesIds[i]);
	 * 
	 * }
	 * 
	 * Student updatedStudent = new Student(); updatedStudent.setId(stdId);
	 * updatedStudent.setFirstName(firstName);
	 * updatedStudent.setLastName(lastName); updatedStudent.setAge(age);
	 * updatedStudent.setMark(mark);
	 * updatedStudent.setGender(Integer.valueOf(gender));
	 * updatedStudent.setCoursesId(editedCoursesIds_);
	 * 
	 * da.updateStudent(updatedStudent, editedCoursesIds_); return 1;
	 * 
	 * } else { ArrayList<Course> courses = courseDA.getAllCourses(); Student
	 * student = da.retrieveStudent(stdId); Course[] studentCourses =
	 * da.getStudentCourses(student);
	 * 
	 * request.setAttribute("student", student);
	 * 
	 * request.setAttribute("courses", courses);
	 * request.setAttribute("studentCourses", studentCourses);
	 * request.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(
	 * request, response); return 0;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public void deleteStudent(HttpServletRequest request, HttpServletResponse
	 * response, beans.StudentBean student) { da = new StudentDA(); int id =
	 * student.getId();
	 * 
	 * da.deleteStudent(id);
	 * 
	 * }
	 * 
	 * public void searchBy(HttpServletRequest request, HttpServletResponse
	 * response, beans.StudentBean studentBean) { da = new StudentDA(); courseDA
	 * = new CourseDA(); ArrayList<Course> courses = courseDA.getAllCourses();
	 * int id = studentBean.getId(); StudentDA da = new StudentDA(); Student
	 * student = da.retrieveStudent(id); Course[] studentCourses =
	 * da.getStudentCourses(student);
	 * 
	 * System.out.println("Student Id retrieved for search is" +
	 * student.getId());
	 * 
	 * request.setAttribute("studentID", student.getId());
	 * request.setAttribute("student", student);
	 * request.setAttribute("studentCourses", studentCourses);
	 * request.setAttribute("courses", courses); }
	 * 
	 * public void editStudent(HttpServletRequest request, HttpServletResponse
	 * response, beans.StudentBean studentBean) { da = new StudentDA(); courseDA
	 * = new CourseDA(); // int id = Integer.valueOf((String)
	 * request.getParameter("stdId")); int id = studentBean.getId(); Student
	 * student = da.retrieveStudent(id); ArrayList<Course> courses =
	 * courseDA.getAllCourses();
	 * 
	 * Course[] studentCourses = da.getStudentCourses(student);
	 * 
	 * List<Course> selectedCourses = new ArrayList<Course>();
	 * 
	 * for (int i = 0; i < courses.size(); i++) {
	 * 
	 * for (int j = 0; j < studentCourses.length; j++) { if
	 * (courses.get(i).getId() == studentCourses[j].getId()) {
	 * courses.get(i).setChecked("true"); } }
	 * 
	 * }
	 * 
	 * request.setAttribute("studentCourses", studentCourses);
	 * request.setAttribute("selectedCourses", selectedCourses);
	 * request.setAttribute("student", student); request.setAttribute("courses",
	 * courses); }
	 * 
	 * public void searchBy(HttpServletRequest request, HttpServletResponse
	 * response, ActionForm actionForm) {
	 * 
	 * da = new StudentDA(); courseDA = new CourseDA(); beans.StudentBean
	 * studentBean = (beans.StudentBean) actionForm; ArrayList<Course> courses =
	 * courseDA.getAllCourses(); int id = Integer.valueOf(studentBean.getId());
	 * StudentDA da = new StudentDA(); Student student = da.retrieveStudent(id);
	 * Course[] studentCourses = da.getStudentCourses(student);
	 * 
	 * System.out.println("Student Id retrieved for search is" +
	 * student.getId());
	 * 
	 * StudentResult result = new StudentResult();
	 * 
	 * result.setId(student.getId());
	 * result.setFirstName(student.getFirstName());
	 * result.setLastName(student.getLastName());
	 * result.setAge(student.getAge()); result.setMark(student.getMark());
	 * result.setGender(student.getGender());
	 * 
	 * request.setAttribute("result", result); }
	 * 
	 * public boolean checkforInteger(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * String firstName = request.getParameter("firstName"); String lastName =
	 * request.getParameter("lastName");
	 * 
	 * if (firstName != "") {
	 * 
	 * if (lastName != "") {
	 * 
	 * if (isInteger(request.getParameter("age"))) {
	 * 
	 * if (isInteger(request.getParameter("mark"))) {
	 * 
	 * int age = Integer.valueOf(request.getParameter("age")); int mark =
	 * Integer.valueOf(request.getParameter("mark"));
	 * 
	 * if (isValideAge(age)) {
	 * 
	 * if (isValidMark(mark)) { return true;
	 * 
	 * } else {
	 * 
	 * markErrorMessage = " Please enter a valid mark between 1 and 100, " +
	 * mark + " is wrong"; request.setAttribute("markErrorMessage",
	 * markErrorMessage); return false; } } else { ageErrorMessage =
	 * " Please enter a valid age between 1 and 100, " + age + " is wrong";
	 * request.setAttribute("ageErrorMessage", ageErrorMessage); return false; }
	 * 
	 * } else { errorMarkIntegerMessage = " Please enter a number ";
	 * request.setAttribute("errorMarkIntegerMessage", errorMarkIntegerMessage);
	 * return false;
	 * 
	 * }
	 * 
	 * } else { errorAgeIntegerMessage = " Please enter a number ";
	 * request.setAttribute("errorAgeIntegerMessage", errorAgeIntegerMessage);
	 * return false; } } else { errorLName = " Please enter your last name ";
	 * request.setAttribute("errorLName", errorLName); return false; } } else {
	 * errorFName = " Please enter your First name ";
	 * request.setAttribute("errorFName", errorFName); return false; }
	 * 
	 * }
	 * 
	 * public boolean checkforInteger(HttpServletRequest request,
	 * HttpServletResponse response, beans.StudentBean studentBean) {
	 * 
	 * String firstName = studentBean.getFirstName(); String lastName =
	 * studentBean.getLastName();
	 * 
	 * if (firstName != "") {
	 * 
	 * if (lastName != "") {
	 * 
	 * if (isInteger(studentBean.getAge())) {
	 * 
	 * if (isInteger(studentBean.getMark())) {
	 * 
	 * int age = Integer.valueOf(studentBean.getAge()); int mark =
	 * Integer.valueOf(studentBean.getMark());
	 * 
	 * if (isValideAge(age)) {
	 * 
	 * if (isValidMark(mark)) { return true;
	 * 
	 * } else {
	 * 
	 * markErrorMessage = " Please enter a valid mark between 1 and 100, " +
	 * mark + " is wrong"; request.setAttribute("markErrorMessage",
	 * markErrorMessage); return false; } } else { ageErrorMessage =
	 * " Please enter a valid age between 1 and 100, " + age + " is wrong";
	 * request.setAttribute("ageErrorMessage", ageErrorMessage); return false; }
	 * 
	 * } else { errorMarkIntegerMessage = " Please enter a number ";
	 * request.setAttribute("errorMarkIntegerMessage", errorMarkIntegerMessage);
	 * return false;
	 * 
	 * }
	 * 
	 * } else { errorAgeIntegerMessage = " Please enter a number ";
	 * request.setAttribute("errorAgeIntegerMessage", errorAgeIntegerMessage);
	 * return false; } } else { errorLName = " Please enter your last name ";
	 * request.setAttribute("errorLName", errorLName); return false; } } else {
	 * errorFName = " Please enter your First name ";
	 * request.setAttribute("errorFName", errorFName); return false; }
	 * 
	 * }
	 * 
	 * private boolean isValideAge(int i) { if (i < 0 || i > 100) { return
	 * false; } return true; }
	 * 
	 * private boolean isValidMark(int i) { if (i < 0 || i > 100) { return
	 * false; }
	 * 
	 * return true; }
	 * 
	 * private boolean isInteger(String s) {
	 * 
	 * try { Integer.parseInt(s); return true; } catch (NumberFormatException e)
	 * {
	 * 
	 * System.err.println("Input is not an integer"); return false; }
	 * 
	 * }
	 */
}
