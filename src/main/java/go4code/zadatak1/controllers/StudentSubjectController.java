package go4code.zadatak1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import go4code.zadatak1.controllers.util.RESTError;
import go4code.zadatak1.model.Student;
import go4code.zadatak1.model.StudentSubject;
import go4code.zadatak1.model.Subject;
import go4code.zadatak1.repositories.StudentRepository;
import go4code.zadatak1.repositories.StudentSubjectRepository;
import go4code.zadatak1.repositories.SubjectRepository;
import go4code.zadatak1.services.StudentSubjectService;

@RestController
@RequestMapping(path = "/studentSubjects")
public class StudentSubjectController {

	@Autowired
	private StudentSubjectRepository stsuRepo;
	
	@Autowired
	private StudentSubjectService stsuService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	@GetMapping
	public ResponseEntity<List<StudentSubject>> getAllSubjectsWithStudents(){
		return new ResponseEntity<List<StudentSubject>>(stsuService.getAllSubjectsWithStudents(), HttpStatus.OK);
	}
	
	@PostMapping(path = "/subject/{subjectId}/student/{studentId}")
	public ResponseEntity<?> addSubjectToStudent (@PathVariable Integer subjectId, @PathVariable Integer studentId){
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		if(!optionalSubject.isPresent()) {
			return new ResponseEntity<>(new RESTError("Subject with id:" + subjectId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		if(!optionalStudent.isPresent()) {
			return new ResponseEntity<>(new RESTError("Student with id:" + studentId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(stsuService.addSubjectToStudent(subjectId, studentId), HttpStatus.OK);
	}
	
	@PutMapping(path = "/studentSubject/{studentSubjectId}/subject/{subjectId}")
	public ResponseEntity<?> changeSubjectForStudent(@PathVariable Integer studentSubjectId, @PathVariable Integer subjectId){
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		if(!optionalSubject.isPresent()) {
			return new ResponseEntity<>(new RESTError("Subject with id:" + subjectId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
		Optional<StudentSubject> optionalStuSub = stsuRepo.findById(studentSubjectId);
		if(!optionalStuSub.isPresent()) {
			return new ResponseEntity<>(new RESTError("StudentSubject with id:" + studentSubjectId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(stsuService.changeSubjectForStudent(studentSubjectId, subjectId), HttpStatus.OK);
	}
	
	@PutMapping(path = "/studentSubject/{studentSubjectId}/student/{studentId}")
	public ResponseEntity<?> changeStudentForSubject(@PathVariable Integer studentSubjectId, @PathVariable Integer studentId){
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		if(!optionalStudent.isPresent()) {
			return new ResponseEntity<>(new RESTError("Student with id:" + studentId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
		Optional<StudentSubject> optionalStuSub = stsuRepo.findById(studentSubjectId);
		if(!optionalStuSub.isPresent()) {
			return new ResponseEntity<>(new RESTError("StudentSubject with id:" + studentSubjectId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(stsuService.changeStudentForSubject(studentSubjectId, studentId), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/studentSubject/{studentSubjectId}")
	public ResponseEntity<?> deleteStudentSubject(@PathVariable Integer studentSubjectId){
		Optional<StudentSubject> optionalStuSub = stsuRepo.findById(studentSubjectId);
		if(!optionalStuSub.isPresent()) {
			return new ResponseEntity<>(new RESTError("StudentSubject with id:" + studentSubjectId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(stsuService.deleteStudentSubject(studentSubjectId), HttpStatus.OK);
	}
	
}
