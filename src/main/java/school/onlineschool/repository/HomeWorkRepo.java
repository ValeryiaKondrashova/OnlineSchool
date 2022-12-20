package school.onlineschool.repository;

import org.springframework.data.repository.CrudRepository;
import school.onlineschool.models.HomeWork;

public interface HomeWorkRepo extends CrudRepository<HomeWork, Long> {
}
