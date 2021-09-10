package go4code.zadatak1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import go4code.zadatak1.model.Student;
import go4code.zadatak1.model.StudentSubject;
import go4code.zadatak1.model.Subject;
import go4code.zadatak1.repositories.StudentRepository;
import go4code.zadatak1.repositories.StudentSubjectRepository;
import go4code.zadatak1.repositories.SubjectRepository;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService{

	@Autowired
	private StudentSubjectRepository stsuRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	@Override
	public List<StudentSubject> getAllSubjectsWithStudents() {
		return (List<StudentSubject>) stsuRepo.findAll();
	}

	@Override
	public StudentSubject addSubjectToStudent(Integer subjectId, Integer studentId) {
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		if(optionalSubject.isPresent() && optionalStudent.isPresent()) {
			Subject subject = optionalSubject.get();
			Student student = optionalStudent.get();
			if(subject.getActive() && student.getActive()) {
				StudentSubject studentSubject = new StudentSubject();
				studentSubject.setStudent(student);
				studentSubject.setSubject(subject);
				return stsuRepo.save(studentSubject);
			}
		}
		return null;
	}

	@Override
	public StudentSubject changeSubjectForStudent(Integer studentSubjectId, Integer subjectId) {
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		Optional<StudentSubject> optionalStuSub = stsuRepo.findById(studentSubjectId);
		if(optionalStuSub.isPresent() && optionalSubject.isPresent()) {
			StudentSubject studentSubject = optionalStuSub.get();
			Subject subject = optionalSubject.get();
			if(studentSubject.getActive() && subject.getActive()) {
				studentSubject.setSubject(subject);
				return stsuRepo.save(studentSubject);
			}
		}
		return null;
	}

	@Override
	public StudentSubject changeStudentForSubject(Integer studentSubjectId, Integer studentId) {
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		Optional<StudentSubject> optionalStuSub = stsuRepo.findById(studentSubjectId);
		if(optionalStuSub.isPresent() && optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			StudentSubject studentSubject = optionalStuSub.get();
			if(student.getActive() && studentSubject.getActive()) {
				studentSubject.setStudent(student);
				return stsuRepo.save(studentSubject);
			}
		}
		return null;
	}

	@Override
	public StudentSubject deleteStudentSubject(Integer studentSubjectId) {
		Optional<StudentSubject> optionalStuSub = stsuRepo.findById(studentSubjectId);
		if(optionalStuSub.isPresent()) {
			StudentSubject studentSubject = optionalStuSub.get();
			if(studentSubject.getActive()) {
				studentSubject.setActive(false);
				return stsuRepo.save(studentSubject);
			}
		}
		return null;
	}


}
