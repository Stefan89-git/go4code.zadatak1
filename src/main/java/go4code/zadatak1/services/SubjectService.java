package go4code.zadatak1.services;

import java.util.List;

import go4code.zadatak1.entities.DTO.SubjectDto;
import go4code.zadatak1.model.Subject;

public interface SubjectService {
	
	public List<Subject> getAllSubjects();

	public Subject createSubject(SubjectDto newSubject, Integer teacherId);
	
	public Subject changeSubject(Integer subjecId, SubjectDto newSubject);
	
	public Subject changeTeacherForSubject(Integer subjectId, Integer newTeacherId);
	
	public Subject deleteSubject(Integer subjectId);
}
