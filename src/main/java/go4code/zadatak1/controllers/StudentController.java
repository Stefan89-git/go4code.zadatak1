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
import go4code.zadatak1.entities.DTO.StudentDto;
import go4code.zadatak1.model.Student;
import go4code.zadatak1.repositories.StudentRepository;
import go4code.zadatak1.services.StudentService;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Student> crateStudent(@RequestBody StudentDto newStudent){
		Student student = studentService.addStudent(newStudent);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@PutMapping(path = "/{studentId}")
	public ResponseEntity<?> changeStudent(@PathVariable Integer studentId, @RequestBody StudentDto newStudent){
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		if(optionalStudent.isPresent()) {
			Student student = studentService.changeStudent(studentId, newStudent);
			return new ResponseEntity<>(student, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Student with id:" + studentId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{studentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId){
		Optional<Student> optionalStudent = studentRepo.findById(studentId);
		if(optionalStudent.isPresent()) {
			Student student = studentService.deleteStudent(studentId);
			return new ResponseEntity<>(student, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Student with id:" + studentId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
	}
}
