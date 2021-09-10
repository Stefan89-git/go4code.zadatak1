package go4code.zadatak1.services;

import java.util.List;

import go4code.zadatak1.model.Exam;

public interface ExamService {
	
	public List<Exam> getAllExams();

	public Exam createExam(Integer grade, Integer studentSubjectId);
	
	public Exam changeExam(Integer examId, Integer grade);
	
	public Exam deleteExam(Integer examId);
	
	public List<Exam> findAllExamsByStudent(Integer studentId);
	
	public Double avgGradeForStudent(Integer studentId);
	
	public List<String> studentsAndExams();
}
