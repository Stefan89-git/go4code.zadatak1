package go4code.zadatak1.services;

import java.util.List;

import go4code.zadatak1.entities.DTO.TeacherDto;
import go4code.zadatak1.model.Teacher;

public interface TeacherService {
	
	public List<Teacher> getAllTeachers();
	
	public Teacher createTeacher(TeacherDto newTeacher);
	
	public Teacher changeTeacher(Integer teacherId, TeacherDto newTeacher);
	
	public Teacher deleteTeacher(Integer teacherId);

}
