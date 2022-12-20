package school.onlineschool.repository;

import org.springframework.data.repository.CrudRepository;
import school.onlineschool.models.Application;

public interface ApplicationRepo extends CrudRepository<Application, Long> {
    int countByCourse(String course);
}
