package school.onlineschool.repository;

import org.springframework.data.repository.CrudRepository;
import school.onlineschool.models.Course;

public interface CourseRepo extends CrudRepository<Course, Long> {
    Course findCourseByNameCourse(String id);
}
