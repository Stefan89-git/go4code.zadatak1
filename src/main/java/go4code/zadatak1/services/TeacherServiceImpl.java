package go4code.zadatak1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import go4code.zadatak1.entities.DTO.TeacherDto;
import go4code.zadatak1.model.Teacher;
import go4code.zadatak1.repositories.TeacherRepository;
import go4code.zadatak1.validation.Validation;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Override
	public List<Teacher> getAllTeachers() {
		return (List<Teacher>) teacherRepo.findAll();
	}

	@Override
	public Teacher createTeacher(TeacherDto newTeacher) {
		Teacher teacher = new Teacher();
		teacher.setName(newTeacher.getName());
		teacher.setLastName(newTeacher.getLastName());
		teacher.setRole(newTeacher.getRole());
		teacher.setActive(true);
		return teacherRepo.save(teacher);
	}

	@Override
	public Teacher changeTeacher(Integer teacherId, TeacherDto newTeacher) {
		Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			Teacher teacher = optionalTeacher.get();
			teacher.setName(Validation.setIfNotNull(teacher.getName(), newTeacher.getName()));
			teacher.setLastName(Validation.setIfNotNull(teacher.getLastName(), newTeacher.getLastName()));
			teacher.setRole(Validation.setIfNotNull(teacher.getRole(), newTeacher.getRole()));
			return teacherRepo.save(teacher);
		}
		return null;
	}

	@Override
	public Teacher deleteTeacher(Integer teacherId) {
		Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			Teacher teacher = optionalTeacher.get();
			if(teacher.getActive()) {
				teacher.setActive(false);
				teacherRepo.save(teacher);
			}
			return teacher;
		}
		return null;
	}

}
