package go4code.zadatak1.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import go4code.zadatak1.model.Exam;
import go4code.zadatak1.model.StudentSubject;
import go4code.zadatak1.repositories.ExamRepository;
import go4code.zadatak1.repositories.StudentSubjectRepository;

@Service
public class ExamServiceImpl implements ExamService{

	@Autowired
	private StudentSubjectRepository stsuRepo;
	
//	@Autowired
//	private StudentRepository studentRepo;
//	
//	@Autowired 
//	private SubjectRepository subjectRepo;
	
	@Autowired
	private ExamRepository examRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Exam> getAllExams() {
		return (List<Exam>) examRepo.findAll();
	}
	
	@Override
	public Exam createExam(Integer grade, Integer studentSubjetId) {
		Optional<StudentSubject> optionalStuSub = stsuRepo.findById(studentSubjetId);
		if(optionalStuSub.isPresent()) {
			StudentSubject studentSubject = optionalStuSub.get();
			if(studentSubject.getActive()) {
				Exam exam = new Exam();
				exam.setStudentSubject(studentSubject);
				exam.setGrade(grade);
				return examRepo.save(exam);
			}
		}
		return null;
	}

	@Override
	public Exam changeExam(Integer examId, Integer grade) {
		Optional<Exam> optionalExam = examRepo.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			exam.setGrade(grade);
			return examRepo.save(exam);
		}
		
		return null;
	}

	@Override
	public Exam deleteExam(Integer examId) {
		Optional<Exam> optionalExam = examRepo.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			if(exam.getActive()) {
				exam.setActive(false);
				return examRepo.save(exam);
			}
		}
		return null;
	}

	@Override
	public List<Exam> findAllExamsByStudent(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double avgGradeForStudent(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> studentsAndExams() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Exam> findAllExamsByStudent(Integer studentId) {
//		if(!studentRepo.existsById(studentId)) {
//			return null;
//		}
//		Student student = studentRepo.findById(studentId).get();
//		List<Exam> examsByStudent = examRepo.findByStudent(student);
//		return null;
//	}
//
//	@Override
//	public Double avgGradeForStudent(Integer studentId) {
//		String sql = "select avg(grade) from Exam e where e.student.id = :studentId"; //= (select s from Student s where student_id = :studentId)";
//		Query query = em.createQuery(sql);
//		query.setParameter("studentId", studentId);
//		Double avg = (Double) query.getSingleResult();
//		return avg;
//	}
//
//	@Override
//	public List<String> studentsAndExams() {
//		String sql = "select su.name from Student s, Exam e, Subject su where s.id = e.student and su.id = e.subject\r\n"
//				+ "and e.active = 1 group by su.name order by count(*) desc";
//		Query query = em.createQuery(sql);
//		List<String> studenti = query.getResultList();
//		List<String> lista = new ArrayList<>();
//		for(String s : studenti) {
//			lista.add(s);
//		}
//		return lista;
//	}

	

}
