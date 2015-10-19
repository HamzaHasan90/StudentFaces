package model;

import java.io.Serializable;

public class Course implements Serializable {

	private int id;
	private String name;
	private String checked;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name.toString();
	}

}
