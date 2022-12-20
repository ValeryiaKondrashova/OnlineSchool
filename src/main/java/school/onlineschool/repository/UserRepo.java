package school.onlineschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.onlineschool.models.User;

public interface UserRepo extends JpaRepository <User, Long>{
    User findByUsername(String username);
}
