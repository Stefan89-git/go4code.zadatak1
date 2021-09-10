package go4code.zadatak1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import go4code.zadatak1.entities.DTO.SubjectDto;
import go4code.zadatak1.model.Subject;
import go4code.zadatak1.model.Teacher;
import go4code.zadatak1.repositories.SubjectRepository;
import go4code.zadatak1.repositories.TeacherRepository;
import go4code.zadatak1.validation.Validation;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepo;
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	@Override
	public List<Subject> getAllSubjects() {
		return (List<Subject>) subjectRepo.findAll();
	}
	
	@Override
	public Subject createSubject(SubjectDto newSubject, Integer teacherId) {
		Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			Teacher teacher = optionalTeacher.get();
			Subject subject = new Subject();
			subject.setName(newSubject.getName());
			subject.setCode(newSubject.getCode());
			subject.setESPB(newSubject.getESPB());
			subject.setSemester(newSubject.getSemester());
			subject.setYear(newSubject.getYear());
			subject.setTeacher(teacher);
			subject.setActive(true);
			return subjectRepo.save(subject);
		}else {
			return null;
		}
	}

	@Override
	public Subject changeSubject(Integer subjectId, SubjectDto newSubject) {
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		if(optionalSubject.isPresent()) {
			Subject subject = optionalSubject.get();
			subject.setName(Validation.setIfNotNull(subject.getName(), newSubject.getName()));
			subject.setCode(Validation.setIfNotNull(subject.getCode(), newSubject.getCode()));
			subject.setESPB(Validation.setIfNotNull(subject.getESPB(), newSubject.getESPB()));
			subject.setSemester(Validation.setIfNotNull(subject.getSemester(), newSubject.getSemester()));
			subject.setYear(Validation.setIfNotNull(subject.getYear(), newSubject.getYear()));
			return subjectRepo.save(subject);
		}else {
			return null;
		}
	}


	@Override
	public Subject changeTeacherForSubject(Integer subjectId, Integer newTeacherId) {
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		Optional<Teacher> optionalTeacher = teacherRepo.findById(newTeacherId);
		if(optionalSubject.isPresent() && optionalTeacher.isPresent()) {
			Subject subject = optionalSubject.get();
			Teacher teacher = optionalTeacher.get();
			if(subject.getActive() && teacher.getActive()) {
				subject.setTeacher(teacher);
				return subjectRepo.save(subject);
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	
	@Override
	public Subject deleteSubject(Integer subjectId) {
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		if(optionalSubject.isPresent()) {
			Subject subject = optionalSubject.get();
			if(subject.getActive()) {
				subject.setActive(false);
				subjectRepo.save(subject);
			}
			return subject;
		}else {
			return null;
		}
	}

	


}
