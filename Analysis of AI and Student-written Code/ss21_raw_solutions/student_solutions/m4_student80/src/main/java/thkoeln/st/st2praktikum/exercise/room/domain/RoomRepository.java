package thkoeln.st.st2praktikum.exercise.room.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoomRepository extends CrudRepository<Room, UUID> {
}