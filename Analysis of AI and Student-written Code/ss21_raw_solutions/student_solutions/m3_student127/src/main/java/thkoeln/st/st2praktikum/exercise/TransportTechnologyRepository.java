package thkoeln.st.st2praktikum.exercise;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransportTechnologyRepository extends CrudRepository<TransportTechnology, UUID> {
    List<TransportTechnology> findByConnectionsSourceRoomIdAndConnectionsSourcePoint(UUID uuid, Point point);
}
