package thkoeln.st.st2praktikum.exercise;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WallRepository extends CrudRepository<Wall, UUID> {
}
