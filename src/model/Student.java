package model;

import java.io.Serializable;

public class Student implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private int mark;
	private int gender;
	private int[] courseIds;
	
	
	

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getCourseIds() {
		return courseIds;
	}

	public void setCoursesId(int[] courseIds) {
		this.courseIds = courseIds;
	}

}
