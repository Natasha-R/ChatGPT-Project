package thkoeln.st.st2praktikum.exercise;

import thkoeln.st.st2praktikum.exercise.interfaces.Blocker;

public class Barrier implements Blocker {

    private Coordinate start;
    private Coordinate end;
    private Orientation orientation;



    public Barrier(Coordinate pos1, Coordinate pos2) {
        this.start = pos1;
        this.end = pos2;

        if (distanceToOrigin(start) > distanceToOrigin(end)) {
            this.start = pos2;
            this.end = pos1;
        }

        if (!this.start.getX().equals(this.end.getX()) && !this.start.getY().equals(this.end.getY()))
            throw new RuntimeException();
    }

    /**
     * @param barrierString the barrier in form of a string e.g. (1,2)-(1,4)
     */
    public Barrier(String barrierString) {

        start = new Coordinate(barrierString.split("-")[0]);
        end = new Coordinate(barrierString.split("-")[1]);

        if (start.getX() == end.getX()) {
            this.orientation = Orientation.VERTICAL;
        } else {
            this.orientation = Orientation.HORIZONTAL;
        }

        if (distanceToOrigin(start) > distanceToOrigin(end)) {
            this.start = end;
            this.end = start;
        }

        if (!this.start.getX().equals(this.end.getX()) && !this.start.getY().equals(this.end.getY()))
            throw new RuntimeException();
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public boolean blocks(MoveCommand c) {
        Direction moveDirection = c.direction;

        // Cannot collide if moving parallel to the barrier
        if (isParallel(orientation, moveDirection)) {
            return false;
        }

        switch (moveDirection) {
            case EAST: if (c.endPosition.getX().equals(start.getX()) && c.endPosition.getY() >= start.getY() && c.endPosition.getY() < end.getY()) return true;           break;
            case WEST: if (c.startPosition.getX().equals(start.getX()) && c.endPosition.getY() >= start.getY() && c.endPosition.getY() < end.getY()) return true;         break;
            case NORTH: if (c.endPosition.getY().equals(start.getY()) && c.endPosition.getX() >= start.getX() && c.endPosition.getX() < end.getX()) return true;          break;
            case SOUTH: if (c.startPosition.getY().equals(start.getY()) && c.endPosition.getX() >= start.getX() && c.endPosition.getX() < end.getX()) return true;        break;
        }

        return false;
    }

    private boolean isParallel(Orientation barrierOrientation, Direction moveDirection) {
        if (barrierOrientation == Orientation.HORIZONTAL && ( moveDirection == Direction.EAST ||moveDirection == Direction.WEST))
            return true;
        else return barrierOrientation == Orientation.VERTICAL && (moveDirection == Direction.NORTH || moveDirection == Direction.SOUTH);
    }

    private int distanceToOrigin(Coordinate coord) {
        return coord.getX() + coord.getY();
    }
}
