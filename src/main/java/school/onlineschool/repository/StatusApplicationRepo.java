package school.onlineschool.repository;

import org.springframework.data.repository.CrudRepository;
import school.onlineschool.models.StatusApplication;

public interface StatusApplicationRepo extends CrudRepository<StatusApplication, Long> {
    StatusApplication findStatusApplicationByNameStatus(String name);
}
