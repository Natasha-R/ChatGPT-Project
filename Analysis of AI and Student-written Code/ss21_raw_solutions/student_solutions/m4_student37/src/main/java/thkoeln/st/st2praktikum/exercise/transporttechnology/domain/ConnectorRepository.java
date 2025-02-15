package thkoeln.st.st2praktikum.exercise.transporttechnology.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface ConnectorRepository extends CrudRepository<Connector, UUID> {
}
