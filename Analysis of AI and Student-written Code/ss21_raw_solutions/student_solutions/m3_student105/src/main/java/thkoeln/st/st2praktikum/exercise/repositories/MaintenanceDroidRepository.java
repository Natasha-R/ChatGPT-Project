package thkoeln.st.st2praktikum.exercise.repositories;

import org.springframework.data.repository.CrudRepository;
import thkoeln.st.st2praktikum.exercise.MaintenanceDroid;

import java.util.UUID;

public interface MaintenanceDroidRepository extends CrudRepository<MaintenanceDroid, UUID> {
}
