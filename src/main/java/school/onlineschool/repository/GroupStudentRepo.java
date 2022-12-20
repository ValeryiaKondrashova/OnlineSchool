package school.onlineschool.repository;

import org.springframework.data.repository.CrudRepository;
import school.onlineschool.models.GroupStudent;

public interface GroupStudentRepo extends CrudRepository<GroupStudent, Long> {
    GroupStudent findGroupStudentByIdGroup(int idGroup);
}
