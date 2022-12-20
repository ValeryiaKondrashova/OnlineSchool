package school.onlineschool.repository;

import org.springframework.data.repository.CrudRepository;
import school.onlineschool.models.Teacher;

public interface TeacherRepo extends CrudRepository<Teacher, Long> {
    Teacher findTeacherByFirstName(String id);
}
