package thkoeln.st.st2praktikum.exercise.room.domain;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import thkoeln.st.st2praktikum.exercise.room.domain.Room;

import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface RoomRepository extends CrudRepository<Room , UUID> {
}
