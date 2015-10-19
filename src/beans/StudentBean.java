package beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import business.StudentBA;
import model.Course;
import model.Student;

@ManagedBean(name = "student")
@ViewScoped
public class StudentBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	StudentBA studentBA = new StudentBA();
	Student student;

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private int mark;
	private int gender;
	private int[] studentsCoursesIds;

	private List<Student> studentList;
	private List<Course> allCoursesList;
	private static Map<String, Object> locals;
	private String localeCode;
	private Locale locale;
	
	public StudentBean() {

		System.out.println("Constructed Student bean");

		locals = new LinkedHashMap<String, Object>();
		locals.put("English", Locale.ENGLISH);
		locals.put("Arabic", new Locale("ar", "JO"));
	}

	@PostConstruct
	public void initialize() {

	}

	@PreDestroy
	public void destroy() {
		System.out.println("Destroyed Student bean");
	}

	public String goTo() {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String param = params.get("action");

		String goTo = null;

		if (studentBA.isNumeric(param)) {

			return null;
		} else {
			switch (param) {
			case "create":
				goTo = "create";
				break;
			case "search":
				goTo = "search";
				break;
			case "update":
				System.out.println(id);
				student = studentBA.retrieveStudent(id);
				setStudentData(student);
				goTo = "update";
				break;
			case "students":
				goTo = "Studentsinfo";
				break;
			default:
				break;
			}
			return goTo;
		}

	}

	public String changeLocale() {

		for (Map.Entry<String, Object> entry : locals.entrySet()) {

			if (entry.getKey().equals(localeCode)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
				HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);

				httpSession.setAttribute("locale", (Locale) entry.getValue());

			}

		}
		return "Studentsinfo";
	}

	public String deleteStudent() {

		studentBA.deleteStudent(id);
		return "Studentsinfo";

	}
	
	public String throwError() throws Exception{
		
		throw new Exception("Exception raised");
	}

	public String searchStudent() {
		student = new Student();
		student = studentBA.retrieveStudent(id);
		setFirstName(student.getFirstName());
		setLastName(student.getLastName());
		setAge(student.getAge());
		setGender(student.getGender());
		setMark(student.getMark());

		return "update";
	}

	// ----------Getters and Setters------------
	public void setStudentData(Student std) {
		setId(std.getId());
		setFirstName(std.getFirstName());
		setLastName(std.getLastName());
		setAge(std.getAge());
		setGender(std.getGender());
		setMark(std.getMark());
		setStudentsCoursesList(std.getCourseIds());
	}

	public Locale getLocale() {
		return locale;
	}

	public Map<String, Object> getLocals() {
		return locals;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int[] getStudentsCoursesList() {
		return studentsCoursesIds;
	}

	public void setStudentsCoursesList(int[] studentsCoursesIds) {
		this.studentsCoursesIds = studentsCoursesIds;
	}

	public List<Student> getStudentList() {
		if (this.studentList == null || this.studentList.isEmpty())
			this.studentList = studentBA.getAllStudents();

		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Course> getCoursesList() {
		if ((this.allCoursesList == null) || this.allCoursesList.isEmpty())
			this.allCoursesList = studentBA.getAllCourses();

		return allCoursesList;
	}

	public void setCoursesList(List<Student> coursesList) {
		this.allCoursesList = studentBA.getAllCourses();
	}

}
