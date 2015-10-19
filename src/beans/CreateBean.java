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

@ManagedBean(name = "createBean")
@RequestScoped
public class CreateBean {

	private StudentBA studentBA;

	private String firstName;
	private String lastName;
	private int mark;
	private int age;
	private int gender;
	private int[] studentsCoursesIds;
	private List<Student> studentList = null;
	private List<Course> studentCourses;
	private Locale locale;
	private InputStream inputStream;

	private Student student;

	public CreateBean() throws IOException {
		studentBA = new StudentBA();

	}

	@PostConstruct
	public void initialize() throws IOException {

		System.out.println("Initialized CreateBean");

	}

	public String createStudent() throws IOException {

		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

		// If locale is English
		if (locale == null || locale == Locale.ENGLISH) {
			locale = Locale.ENGLISH;

			inputStream = this.getClass().getResourceAsStream("/messages.properties");
			if (checkFirstName(firstName) && checkLastName(lastName)) {
				String nameExisted = Props.getProperty(inputStream, "error.existed.name");
				FacesMessage firstNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, nameExisted);
				FacesMessage lastNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, nameExisted);
				FacesContext.getCurrentInstance().addMessage("createForm:fNameInput", firstNameMessage);
				FacesContext.getCurrentInstance().addMessage("createForm:lNameInput", lastNameMessage);
				return null;
			} else {
				String createSuccess = Props.getProperty(inputStream, "success.create");
				student = new Student();
				int[] courseIds = new int[studentsCoursesIds.length];
				student = new Student();
				student.setFirstName(firstName);
				student.setLastName(lastName);

				student.setGender(gender);
				student.setAge(age);
				student.setMark(mark);
				for (int i = 0; i < courseIds.length; i++) {
					courseIds[i] = studentsCoursesIds[i];
				}
				student.setCoursesId(courseIds);

				studentBA.createStudent(student, courseIds);

				FacesMessage successful = new FacesMessage(FacesMessage.SEVERITY_INFO, createSuccess, null);
				FacesContext.getCurrentInstance().addMessage(null, successful);

				return "Studentsinfo";
			}

		} else {

			inputStream = this.getClass().getResourceAsStream("/messages_ar.properties");

			if (checkFirstName(firstName) && checkLastName(lastName)) {
				String nameExisted = Props.getProperty(inputStream, "error.existed.name");
				FacesMessage firstNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, nameExisted);
				FacesMessage lastNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, nameExisted);
				FacesContext.getCurrentInstance().addMessage("createForm:fNameInput", firstNameMessage);
				FacesContext.getCurrentInstance().addMessage("createForm:lNameInput", lastNameMessage);
				return null;
			} else {
				String createSuccess = Props.getProperty(inputStream, "success.create");
				student = new Student();
				int[] courseIds = new int[studentsCoursesIds.length];
				student = new Student();
				student.setFirstName(firstName);
				student.setLastName(lastName);

				student.setGender(gender);
				student.setAge(age);
				student.setMark(mark);
				for (int i = 0; i < courseIds.length; i++) {
					courseIds[i] = studentsCoursesIds[i];
				}
				student.setCoursesId(courseIds);

				studentBA.createStudent(student, courseIds);

				FacesMessage successful = new FacesMessage(FacesMessage.SEVERITY_INFO, createSuccess, null);
				FacesContext.getCurrentInstance().addMessage(null, successful);

				return "Studentsinfo";
			}

		}

	}

	public String goToCreate() {
		// locale =
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

		return "create";
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
		studentList = studentBA.getAllStudents();

		for (Iterator<Student> iterator = studentList.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			if (student.getLastName().equals(lastname)) {
				return true;
			}

		}
		return false;

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

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
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

	public List<Course> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<Course> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public Locale getLocale() {
		return locale;
	}

}
