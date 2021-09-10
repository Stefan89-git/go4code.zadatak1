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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import go4code.zadatak1.controllers.util.RESTError;
import go4code.zadatak1.entities.DTO.SubjectDto;
import go4code.zadatak1.model.Subject;
import go4code.zadatak1.model.Teacher;
import go4code.zadatak1.repositories.SubjectRepository;
import go4code.zadatak1.repositories.TeacherRepository;
import go4code.zadatak1.services.SubjectService;

@RestController
@RequestMapping(path = "/subjects")
public class SubjectController {
	
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	@GetMapping
	public ResponseEntity<List<Subject>> getAllSubjects(){
		List<Subject> subjects = subjectService.getAllSubjects();
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createSubject(@PathVariable Integer teacherId, @RequestBody SubjectDto newSubject){
		Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			Subject subject = subjectService.createSubject(newSubject, teacherId);
			return new ResponseEntity<>(subject, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Teacher with id:" + teacherId + " doesn't exist."),HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(path = "/{subjectId}")
	public ResponseEntity<?> changeSubject(@PathVariable Integer subjectId, @RequestBody SubjectDto newSubject){
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		if(optionalSubject.isPresent()) {
			Subject subject = subjectService.changeSubject(subjectId, newSubject);
			return new ResponseEntity<>(subject, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Subject with id:" + subjectId + " doesn't exist."),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{subjectId}")
	public ResponseEntity<?> deleteSubject(@PathVariable Integer subjectId){
		Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
		if(optionalSubject.isPresent()) {
			Subject subject = subjectService.deleteSubject(subjectId);
			return new ResponseEntity<>(subject, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Subject with id:" + subjectId + " doesn't exist."),HttpStatus.NOT_FOUND);
		}
	}
}
