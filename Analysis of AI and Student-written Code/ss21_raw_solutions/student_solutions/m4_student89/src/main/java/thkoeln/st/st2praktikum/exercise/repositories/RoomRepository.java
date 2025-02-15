package thkoeln.st.st2praktikum.exercise.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import thkoeln.st.st2praktikum.exercise.room.domain.Room;


import java.util.UUID;

@Repository
public interface RoomRepository extends CrudRepository<Room,UUID>{
    public Room getRoomByid(UUID id);
}
