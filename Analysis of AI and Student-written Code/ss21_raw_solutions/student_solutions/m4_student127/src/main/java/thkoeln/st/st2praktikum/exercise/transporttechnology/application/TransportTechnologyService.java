package thkoeln.st.st2praktikum.exercise.transporttechnology.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Point;
import thkoeln.st.st2praktikum.exercise.transporttechnology.domain.Connection;
import thkoeln.st.st2praktikum.exercise.transporttechnology.domain.ConnectionRepository;
import thkoeln.st.st2praktikum.exercise.transporttechnology.domain.TransportTechnology;
import thkoeln.st.st2praktikum.exercise.transporttechnology.domain.TransportTechnologyRepository;


import java.util.UUID;


@Service
public class TransportTechnologyService {
    private ConnectionRepository connectionRepository;
    private TransportTechnologyRepository transportTechnologyRepository;

    @Autowired
    public TransportTechnologyService(ConnectionRepository connectionRepository, TransportTechnologyRepository transportTechnologyRepository) {
        this.connectionRepository = connectionRepository;
        this.transportTechnologyRepository = transportTechnologyRepository;
    }

    /**
     * This method adds a transport technology
     * @param technology the type of the transport technology
     * @return the UUID of the created transport technology
     */
    public UUID addTransportTechnology(String technology) {
        TransportTechnology newTransportTechnology = new TransportTechnology(technology);
        transportTechnologyRepository.save(newTransportTechnology);
        return newTransportTechnology.getId();
    }

    /**
     * This method adds a traversable connection between two rooms based on a transport technology. Connections only work in one direction.
     * @param transportTechnologyId the transport technology which is used by the connection
     * @param sourceRoomId the ID of the room where the entry point of the connection is located
     * @param sourcePoint the point of the entry point
     * @param destinationRoomId the ID of the room where the exit point of the connection is located
     * @param destinationPoint the point of the exit point
     * @return the UUID of the created connection
     */
    public UUID addConnection(UUID transportTechnologyId, UUID sourceRoomId, Point sourcePoint, UUID destinationRoomId, Point destinationPoint) {
        Connection newConnection = new Connection(
                sourceRoomId,
                sourcePoint,
                destinationRoomId,
                destinationPoint
        );
        connectionRepository.save(newConnection);
        return transportTechnologyRepository
                .findById(transportTechnologyId)
                .stream()
                .findFirst()
                .orElse(null)
                .addConnection(newConnection);
    }

    public TransportTechnology getTransportTechnologyByConnectionsSourceRoomIdAndConnectionsSourcePoint(UUID connectionSourceRoomId, Point sourcePoint) {
        return transportTechnologyRepository.findByConnectionsSourceRoomIdAndConnectionsSourcePoint(connectionSourceRoomId, sourcePoint)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
