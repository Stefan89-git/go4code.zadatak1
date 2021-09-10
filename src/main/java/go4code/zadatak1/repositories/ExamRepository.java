package go4code.zadatak1.repositories;

import org.springframework.data.repository.CrudRepository;

import go4code.zadatak1.model.Exam;

public interface ExamRepository extends CrudRepository<Exam, Integer> {
	
	
}
