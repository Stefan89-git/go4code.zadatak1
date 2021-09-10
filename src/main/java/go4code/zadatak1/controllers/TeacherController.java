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
import go4code.zadatak1.entities.DTO.TeacherDto;
import go4code.zadatak1.model.Teacher;
import go4code.zadatak1.repositories.TeacherRepository;
import go4code.zadatak1.services.TeacherService;

@RestController
@RequestMapping(path = "/teachers")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping
	public ResponseEntity<List<Teacher>> getAllTeachers(){
		return new ResponseEntity<List<Teacher>>(teacherService.getAllTeachers(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDto newTeacher){
		Teacher teacher = teacherService.createTeacher(newTeacher);
		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}
	
	@PutMapping(path = "/{teacherId}")
	public ResponseEntity<?> changeTeacher(@PathVariable Integer teacherId, @RequestBody TeacherDto newTeacher){
		Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			Teacher teacher = teacherService.changeTeacher(teacherId, newTeacher);
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Teacher with id:" + teacherId + " doesn't exist."),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{teacherId}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Integer teacherId){
		Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			Teacher teacher = teacherService.deleteTeacher(teacherId);
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Teacher with id:" + teacherId + " doesn't exist."),HttpStatus.NOT_FOUND);
		}
	}
}
