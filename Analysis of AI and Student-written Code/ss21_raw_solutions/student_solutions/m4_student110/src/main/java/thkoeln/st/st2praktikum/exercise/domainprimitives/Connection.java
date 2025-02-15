package thkoeln.st.st2praktikum.exercise.domainprimitives;

import lombok.Getter;
import lombok.Setter;
import thkoeln.st.st2praktikum.exercise.transportcategory.TransportCategory;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Connection {
    @Id
    @Getter
    private UUID connectionId = UUID.randomUUID();


    @Getter
    @Setter
    @ManyToOne
    private TransportCategory transportCategoryOfConnection;

    @Getter
    @Setter
    private UUID transportCategoryId;



    @Getter
    @Setter
    @Embedded
    private Coordinate sourceCoordinate;

    @Getter
    @Setter
    @Embedded
    private Coordinate destinationCoordinate;

    @Getter
    @Setter
    private UUID sourceRoomId;

    @Getter
    @Setter
    private UUID destinationRoomId;


    public Connection(UUID transportCategoryId, UUID sourceRoomId, Coordinate sourceCoordinate, UUID destinationRoomId, Coordinate destinationCoordinate){
        this.transportCategoryId = transportCategoryId;
        this.sourceRoomId = sourceRoomId;
        this.sourceCoordinate = sourceCoordinate;
        this.destinationRoomId = destinationRoomId;
        this.destinationCoordinate = destinationCoordinate;

    }
    public Connection(){}


    public String getSourceCoordinatAsString(){
        return sourceCoordinate.toString();
    }
    public String getDestinationCoordinatAsString(){
        return destinationCoordinate.toString();
    }

    @Override
    public String toString() {
        return "(" + sourceCoordinate.getX() + "," + sourceCoordinate.getY() + ")";
    }
}
