package thkoeln.st.st2praktikum.exercise.space.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import thkoeln.st.st2praktikum.exercise.space.domain.Space;

import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface SpaceRepository extends CrudRepository<Space, UUID> {

}
