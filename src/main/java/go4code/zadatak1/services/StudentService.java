package go4code.zadatak1.services;

import java.util.List;

import go4code.zadatak1.entities.DTO.StudentDto;
import go4code.zadatak1.model.Student;

public interface StudentService {
	
	public List<Student> getAllStudents();

	public Student addStudent(StudentDto newStudent);
	
	public Student changeStudent(Integer studentId, StudentDto newStudent);
	
	public Student deleteStudent(Integer studentId);
}
