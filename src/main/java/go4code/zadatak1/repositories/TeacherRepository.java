package go4code.zadatak1.repositories;

import org.springframework.data.repository.CrudRepository;

import go4code.zadatak1.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

}
