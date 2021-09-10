package go4code.zadatak1.entities.DTO;

import go4code.zadatak1.model.StudentSubject;

public class ExamDto {

	private Integer grade;
	
	private StudentSubject studentSubject;

	public ExamDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamDto(Integer grade, StudentSubject studentSubject) {
		super();
		this.grade = grade;
		this.studentSubject = studentSubject;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public StudentSubject getStudentSubject() {
		return studentSubject;
	}

	public void setStudentSubject(StudentSubject studentSubject) {
		this.studentSubject = studentSubject;
	}
	
	
}
