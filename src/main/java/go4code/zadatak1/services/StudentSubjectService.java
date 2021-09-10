package go4code.zadatak1.services;

import java.util.List;

import go4code.zadatak1.model.StudentSubject;

public interface StudentSubjectService {

	public List<StudentSubject> getAllSubjectsWithStudents();
	
	public StudentSubject addSubjectToStudent(Integer subjectId, Integer studentId);
	
	public StudentSubject changeSubjectForStudent(Integer studentSubjectId, Integer subjectId);
	
	public StudentSubject changeStudentForSubject(Integer studentSubjectId, Integer studentId);
	
	public StudentSubject deleteStudentSubject(Integer studentSubjectId);
	
	
}
