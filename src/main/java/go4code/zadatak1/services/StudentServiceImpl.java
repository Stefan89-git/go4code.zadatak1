package go4code.zadatak1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import go4code.zadatak1.entities.DTO.StudentDto;
import go4code.zadatak1.model.Student;
import go4code.zadatak1.repositories.StudentRepository;
import go4code.zadatak1.validation.Validation;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<Student> getAllStudents() {
		return (List<Student>) studentRepo.findAll();
	}
	
	@Override
	public Student addStudent(StudentDto newStudent) {
		Student student = new Student();
		student.setName(newStudent.getName());
		student.setLastName(newStudent.getLastName());
		student.setIndeks(newStudent.getIndeks());
		student.setActive(true);
		return studentRepo.save(student);
	}

	@Override
	public Student changeStudent(Integer studentId, StudentDto newStudent) {
		
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		if(optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			student.setName(Validation.setIfNotNull(student.getName(), newStudent.getName()));
			student.setLastName(Validation.setIfNotNull(student.getLastName(), newStudent.getLastName()));
			student.setIndeks(Validation.setIfNotNull(student.getIndeks(), newStudent.getIndeks()));
			return studentRepo.save(student);
		}else {
			return null;
		}
	}

	@Override
	public Student deleteStudent(Integer studentId) {
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		if(optionalStudent.isPresent()) {
			Student student = studentRepo.findById(studentId).get();
			if(student.getActive()) {
				student.setActive(false);
				studentRepo.save(student);
			}
			return student;
		}else {
			return null;
		}
	}

	
}
