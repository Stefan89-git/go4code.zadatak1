package go4code.zadatak1.entities.DTO;

public class StudentDto {

	private String name;
	
	private String lastName;
	
	private Integer indeks;

	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDto(String name, String lastName, Integer indeks) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.indeks = indeks;
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

	public Integer getIndeks() {
		return indeks;
	}

	public void setIndeks(Integer indeks) {
		this.indeks = indeks;
	}
	
	
}
