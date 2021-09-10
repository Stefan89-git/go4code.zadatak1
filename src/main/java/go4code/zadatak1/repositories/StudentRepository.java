package go4code.zadatak1.repositories;

import org.springframework.data.repository.CrudRepository;

import go4code.zadatak1.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
