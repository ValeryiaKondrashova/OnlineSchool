package school.onlineschool.repository;

import org.springframework.data.repository.CrudRepository;
import school.onlineschool.models.Student;

public interface StudentRepo extends CrudRepository<Student, Long> {
}