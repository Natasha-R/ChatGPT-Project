package thkoeln.st.st2praktikum.exercise.transportcategory.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Connection;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Coordinate;

import thkoeln.st.st2praktikum.exercise.transportcategory.domain.TransportCategory;
import thkoeln.st.st2praktikum.exercise.transportcategory.domain.TransportCategoryList;
import thkoeln.st.st2praktikum.exercise.transportcategory.domain.TransportCategoryRepository;


import java.util.UUID;


@Service
public class TransportCategoryService {
    TransportCategoryList transportCategoryList = new TransportCategoryList();

    @Autowired
    TransportCategoryRepository TransportCategoryRepository;
    /**
     * This method adds a transport category
     * @param category the type of the transport category
     * @return the UUID of the created transport category
     */
    public UUID addTransportCategory (String category){
        UUID transportCategoryUUID = UUID.randomUUID();
        TransportCategory transportCategory = new TransportCategory(category,transportCategoryUUID);
        TransportCategoryRepository.save(transportCategory);
        transportCategoryList.add(transportCategory);
        return transportCategoryUUID;
    }

    /**
     * This method adds a traversable connection between two rooms based on a transport category. Connections only work in one direction.
     * @param transportCategoryId the transport category which is used by the connection
     * @param sourceRoomId the ID of the room where the entry point of the connection is located
     * @param sourceCoordinate the coordinate of the entry point
     * @param destinationRoomId the ID of the room where the exit point of the connection is located
     * @param destinationCoordinate the coordinate of the exit point
     * @return the UUID of the created connection
     */
    public UUID addConnection(UUID transportCategoryId, UUID sourceRoomId, Coordinate sourceCoordinate, UUID destinationRoomId, Coordinate destinationCoordinate) {
        UUID connectionUUID = UUID.randomUUID();
        Connection newConnection = new Connection(sourceRoomId, sourceCoordinate, destinationRoomId, destinationCoordinate, connectionUUID,transportCategoryId);
        for(TransportCategory transportCategory : transportCategoryList.getTransportCategoryList()){
            if(transportCategory.getTransportCategoryId() == transportCategoryId){
                TransportCategoryRepository.deleteById(transportCategoryId);
                transportCategory.getListConnection().add( newConnection);
                TransportCategoryRepository.save(transportCategory);
            }
        }

        return connectionUUID;
    }

}
