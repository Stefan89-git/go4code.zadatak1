package go4code.zadatak1.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import go4code.zadatak1.controllers.util.RESTError;
import go4code.zadatak1.model.Exam;
import go4code.zadatak1.model.StudentSubject;
import go4code.zadatak1.repositories.ExamRepository;
import go4code.zadatak1.repositories.StudentSubjectRepository;
import go4code.zadatak1.services.ExamService;

@RestController
@RequestMapping(path = "/exams")
public class ExamController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private StudentSubjectRepository stsuRepo;
	
	@GetMapping
	public ResponseEntity<List<Exam>> getAllExams(){
		List<Exam> exams = examService.getAllExams();
		logger.info("Get all exams");
		return new ResponseEntity<>(exams, HttpStatus.OK);
	}
	
	@PostMapping(path = "/{studentSubjectId}")
	public ResponseEntity<?> createExam(@RequestParam Integer grade, @PathVariable Integer studentSubjetId){
		Optional<StudentSubject> optionalStuSub = stsuRepo.findById(studentSubjetId);
		if(optionalStuSub.isPresent()) {
			Exam exam = examService.createExam(grade, studentSubjetId);
			return new ResponseEntity<>(exam, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("StudentSubject with id:" + studentSubjetId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(path = "/{examId}")
	public ResponseEntity<?> changeExam(@PathVariable Integer examId, @RequestParam Integer grade){
		Optional<Exam> optionalExam = examRepo.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = examService.changeExam(examId, grade);
			return new ResponseEntity<>(exam, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Exam with id:" + examId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{examId}")
	public ResponseEntity<?> deleteExam(@PathVariable Integer examId){
		Optional<Exam> optionalExam = examRepo.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = examService.deleteExam(examId);
			return new ResponseEntity<>(exam, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new RESTError("Exam with id:" + examId + " doesn't exist."), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/{studentId}")
	public ResponseEntity<List<Exam>> getAllExamsByStudent(@PathVariable Integer studentId){
		List<Exam> examsByStudent = examService.findAllExamsByStudent(studentId);
		logger.info("Get all exams by student ");
		return new ResponseEntity<>(examsByStudent, HttpStatus.OK);
	}
	
	@GetMapping(path = "/avgGrade/{studentId}")
	public ResponseEntity<Double> avgGradeForStudent(@PathVariable Integer studentId){
		Double avgGrade = examService.avgGradeForStudent(studentId);
		return new ResponseEntity<>(avgGrade, HttpStatus.OK);
	}
	

	@GetMapping(path = "/students")
	public ResponseEntity<List<String>> studentsAndExams(){
		List<String> lista = examService.studentsAndExams();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}
