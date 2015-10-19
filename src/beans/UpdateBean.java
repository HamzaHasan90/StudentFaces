package beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import business.StudentBA;
import model.Course;
import model.Student;
import properties.Props;

@ManagedBean(name = "updateBean")
@RequestScoped
public class UpdateBean {

	private StudentBA studentBA;

	private int id;
	private String firstName;
	private String lastName;
	private int mark;
	private int age;

	private int gender;
	private List<Course> studentCourses;
	private List<Student> studentList = null;
	private int[] studentsCoursesIds;

	private InputStream inputStream;
	// private InputStream inputStreamAR;

	private Locale locale;

	private Student student;

	public UpdateBean() throws IOException {
		studentBA = new StudentBA();

	}

	@PostConstruct
	public void initialize() throws IOException {

		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		if (locale != null)
			System.out.println("Local in update is  " + locale.getCountry());

	}

	public String updateStudent() throws IOException {
		
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		
		// If language is English
		if (locale == null || locale == Locale.ENGLISH) {

			inputStream = this.getClass().getResourceAsStream("/messages.properties");

			if (checkFirstName(firstName.trim()) && checkLastName(lastName.trim())) {
				String NameExisted = Props.getProperty(inputStream, "error.existed.name");

				FacesMessage firstNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, NameExisted);
				FacesMessage lastNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, NameExisted);
				FacesContext.getCurrentInstance().addMessage("updateForm:fNameInput", firstNameMessage);
				FacesContext.getCurrentInstance().addMessage("updateForm:lNameInput", lastNameMessage);

				return null;

			} else {

				System.out.println(locale.toString());

				String savedSuccess = Props.getProperty(inputStream, "success.save").toString();
				int[] courseIds = new int[studentsCoursesIds.length];
				student = new Student();

				student.setFirstName(firstName.trim());
				student.setLastName(lastName.trim());
				student.setId(id);
				student.setMark(mark);
				student.setAge(age);
				student.setGender(gender);
				for (int i = 0; i < courseIds.length; i++) {
					courseIds[i] = studentsCoursesIds[i];
				}
				student.setCoursesId(courseIds);
				studentBA.updateStudent(student, studentsCoursesIds);
				FacesMessage successful = new FacesMessage(FacesMessage.SEVERITY_INFO, savedSuccess, null);
				FacesContext.getCurrentInstance().addMessage(null, successful);
				return "Studentsinfo";

			}

			// If language is Arabic
		} else {

			inputStream = this.getClass().getResourceAsStream("/messages_ar.properties");

			if (checkFirstName(firstName.trim()) && checkLastName(lastName.trim())) {
				String NameExisted = Props.getProperty(inputStream, "error.existed.name");

				FacesMessage firstNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, NameExisted);
				FacesMessage lastNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, NameExisted);
				FacesContext.getCurrentInstance().addMessage("updateForm:fNameInput", firstNameMessage);
				FacesContext.getCurrentInstance().addMessage("updateForm:lNameInput", lastNameMessage);

				return null;

			} else {

				String savedSuccess = Props.getProperty(inputStream, "success.save").toString();
				int[] courseIds = new int[studentsCoursesIds.length];
				student = new Student();

				student.setFirstName(firstName.trim());
				student.setLastName(lastName.trim());
				student.setId(id);
				student.setMark(mark);
				student.setAge(age);
				student.setGender(gender);
				for (int i = 0; i < courseIds.length; i++) {
					courseIds[i] = studentsCoursesIds[i];
				}
				student.setCoursesId(courseIds);
				studentBA.updateStudent(student, studentsCoursesIds);
				FacesMessage successful = new FacesMessage(FacesMessage.SEVERITY_INFO, savedSuccess, null);
				FacesContext.getCurrentInstance().addMessage(null, successful);
				return "Studentsinfo";

			}
		}

	}

	public String retrieveforUpdate() {

		studentBA = new StudentBA();
		student = new Student();

		student = studentBA.retrieveStudent(id);
		setId(student.getId());
		setFirstName(student.getFirstName());
		setLastName(student.getLastName());
		setMark(student.getMark());
		setAge(student.getAge());
		setGender(student.getGender());
		setStudentsCoursesIds(student.getCourseIds());

		return "update";

	}

	public String retrieveforSearch() throws IOException {

		if (checkStudentId(id)) {

			studentBA = new StudentBA();
			student = new Student();

			student = studentBA.retrieveStudent(id);
			setId(student.getId());
			setFirstName(student.getFirstName());
			setLastName(student.getLastName());
			setMark(student.getMark());
			setAge(student.getAge());
			setGender(student.getGender());
			setStudentsCoursesIds(student.getCourseIds());

			return "update";

		} else {

			if (locale == null || locale == Locale.ENGLISH) {

				// If Locale is English

				inputStream = this.getClass().getResourceAsStream("/messages.properties");
				locale = Locale.ENGLISH;
				System.out.println(locale.toString());

				String noStudentError = Props.getProperty(inputStream, "error.nostudent");
				FacesMessage idMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, noStudentError, null);

				FacesContext.getCurrentInstance().addMessage("searchForm:searchinput", idMessage);
			} else {

				// If Arabic
				inputStream = this.getClass().getResourceAsStream("/messages_ar.properties");

				System.out.println(locale.toString());

				String noStudentError = Props.getProperty(inputStream, "error.nostudent");
				FacesMessage idMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, noStudentError, null);

				FacesContext.getCurrentInstance().addMessage("searchForm:searchinput", idMessage);
			}
		}
		return null;
	}

	public boolean checkFirstName(String firstName) {
		studentList = studentBA.getAllStudents();

		for (Iterator<Student> iterator = studentList.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			if (student.getFirstName().equals(firstName)) {
				return true;
			}

		}
		return false;

	}

	public boolean checkLastName(String lastname) {

		if (studentList.isEmpty() || studentList == null)
			studentList = studentBA.getAllStudents();

		for (Iterator<Student> iterator = studentList.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			if (student.getLastName().equals(lastname)) {
				return true;
			}

		}

		return false;
	}

	public boolean checkStudentId(int i) {
		studentList = studentBA.getAllStudents();
		for (Iterator<Student> iterator = studentList.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			if (student.getId() == i) {
				return true;
			}

		}
		return false;

	}

	public String goToUpdate() {
		return "update";
	}

	// ========Getters and setters=====================

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

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public List<Course> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<Course> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int[] getStudentsCoursesIds() {
		return studentsCoursesIds;
	}

	public void setStudentsCoursesIds(int[] studentsCoursesIds) {
		this.studentsCoursesIds = studentsCoursesIds;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
