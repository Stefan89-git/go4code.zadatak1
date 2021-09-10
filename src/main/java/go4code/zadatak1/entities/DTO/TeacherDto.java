package go4code.zadatak1.entities.DTO;

import go4code.zadatak1.model.ETeacherRole;

public class TeacherDto {

	private String name;
	
	private String lastName;
	
	private ETeacherRole role;

	public TeacherDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherDto(String name, String lastName, ETeacherRole role) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ETeacherRole getRole() {
		return role;
	}

	public void setRole(ETeacherRole role) {
		this.role = role;
	}
	
}
