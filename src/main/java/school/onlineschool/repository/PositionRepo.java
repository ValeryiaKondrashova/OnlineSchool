package school.onlineschool.repository;

import org.springframework.data.repository.CrudRepository;
import school.onlineschool.models.Position;

public interface PositionRepo extends CrudRepository<Position, Long> {
    Position findPositionByNamePosition(String namePosition);
}
