package thkoeln.st.st2praktikum.exercise;

import java.util.UUID;

public class Connection {
    private UUID sourceFieldId;
    private String sourceCoordinate;
    private UUID destinationFieldId;
    private String destinationCoordinate;

    public UUID getSourceFieldId() {
        return sourceFieldId;
    }

    public void setSourceFieldId(UUID sourceFieldId) {
        this.sourceFieldId = sourceFieldId;
    }

    public String getSourceCoordinate() {
        return sourceCoordinate;
    }

    public void setSourceCoordinate(String sourceCoordinate) {
        this.sourceCoordinate = sourceCoordinate;
    }

    public UUID getDestinationFieldId() {
        return destinationFieldId;
    }

    public void setDestinationFieldId(UUID destinationFieldId) {
        this.destinationFieldId = destinationFieldId;
    }

    public String getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public void setDestinationCoordinate(String destinationCoordinate) {
        this.destinationCoordinate = destinationCoordinate;
    }
    public Connection(UUID sourceFieldId, Point sourcePoint, UUID destinationFieldId, Point destinationPoint)
    {
        this.sourceFieldId = sourceFieldId;
        this.sourceCoordinate = "("+sourcePoint.getX()+","+sourcePoint.getY()+")";
        this.destinationFieldId = destinationFieldId;
        this.destinationCoordinate = "("+destinationPoint.getX()+","+destinationPoint.getY()+")";;

    }
}
