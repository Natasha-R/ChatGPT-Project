package thkoeln.st.st2praktikum.exercise;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ConnectionRepository extends CrudRepository<Connection,UUID> {

}
