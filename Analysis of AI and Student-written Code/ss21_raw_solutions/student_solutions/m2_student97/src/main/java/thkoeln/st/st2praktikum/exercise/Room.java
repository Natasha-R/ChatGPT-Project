package thkoeln.st.st2praktikum.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class Room {

    private UUID id;
    private Integer width;
    private Integer height;
    private List<Barrier> barriers = new ArrayList<Barrier>();
    private HashMap<UUID, Connection> connections = new HashMap<UUID, Connection>();


    public Room (Integer height, Integer width) {
        this.height = height;
        this.width = width;
        this.id = UUID.randomUUID();
    }


    public void addBarrier (Barrier newBarrier) {

        if (newBarrier.getStart().getX() > width + 1 ||
            newBarrier.getStart().getY() > height + 1 ||
            newBarrier.getEnd().getX() > width + 1 || 
            newBarrier.getEnd().getY() > height + 1) {
                throw new UnsupportedOperationException(); 
            }

        this.barriers.add(newBarrier);
    }


    public UUID addConnection (Point sourcePoint, UUID destinationRoomId, Point destinationPoint) {
        Connection newConnection = new Connection(sourcePoint, destinationRoomId, destinationPoint);
        this.connections.put(newConnection.getId(), newConnection);
        return newConnection.getId();
    }


    public Boolean routeIsWalkable (Point initialPoint, Integer targetX, Integer targetY) {

        if (!this.pointIsWithinBoundaries(targetX, targetY)) return false;

        for (Barrier barrier : this.barriers) {
            if (initialPoint.getY() == targetY && barrier.isVertical()) {
                if (!horizontalRouteIsWalkable(initialPoint, targetX, targetY, barrier)) return false;
            }
            else if (initialPoint.getX() == targetX && !barrier.isVertical()) {
                if (!verticalRouteIsWalkable(initialPoint, targetX, targetY, barrier)) return false;
            }
        }

        return true;
    }


    private Boolean pointIsWithinBoundaries(Integer targetX, Integer targetY) {

        if (targetX < 0 || targetY < 0 || targetX >= this.width || targetY >= this.height)
            return false;
        else
            return true;
    }


    private Boolean verticalRouteIsWalkable (Point initialPoint, Integer targetX, Integer targetY, Barrier barrier) {

        if (barrier.getStart().getX() <= initialPoint.getX() && 
            barrier.getEnd().getX() > initialPoint.getX()) {

            if ((initialPoint.getY() == barrier.getStart().getY() - 1 && 
                targetY == barrier.getStart().getY()) || 
                
                (initialPoint.getY() == barrier.getStart().getY() && 
                targetY == barrier.getStart().getY() - 1)) {

                return false;
            }
        }

        return true;
    }


    private Boolean horizontalRouteIsWalkable (Point initialPoint, Integer targetX, Integer targetY, Barrier barrier) {

        if (barrier.getStart().getY() <= initialPoint.getY() && 
            barrier.getEnd().getY() > initialPoint.getY()) {

            if ((initialPoint.getX() == barrier.getStart().getX() - 1 && 
                targetX == barrier.getStart().getX() || 
                
                (initialPoint.getX() == barrier.getStart().getX() && 
                targetX == barrier.getStart().getX() - 1))) {

                return false;
            }
        }

        return true;
    }


    public UUID getId () {
        return this.id;
    }


    public HashMap<UUID, Connection> getConnections () {
        return this.connections;
    }
}
