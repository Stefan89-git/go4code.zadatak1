package go4code.zadatak1.entities.DTO;

public class SubjectDto {

	private String code;
	
	private String name;
	
	private Integer ESPB;
	
	private Integer semester;
	
	private Integer year;

	public SubjectDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubjectDto(String code, String name, Integer eSPB, Integer semester, Integer year) {
		super();
		this.code = code;
		this.name = name;
		ESPB = eSPB;
		this.semester = semester;
		this.year = year;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getESPB() {
		return ESPB;
	}

	public void setESPB(Integer eSPB) {
		ESPB = eSPB;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	
}
