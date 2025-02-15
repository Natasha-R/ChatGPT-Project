package thkoeln.st.st2praktikum.exercise.field.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Coordinate;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Rectangle;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Getter

public class Wall implements Collidable {

    @Getter
    private Coordinate start;
    @Getter
    private Coordinate end;

    @Override
    public Rectangle getBounds() {
        return Rectangle.fromCoordinates(start,end);
    }

    /**
     * @param wallString the wall in form of a string e.g. (1,2)-(1,4)
     */
    public static Wall fromString(String wallString) {
        String [] coordinateStrings = wallString.split("-");
        Coordinate start = Coordinate.fromString(coordinateStrings[0]);
        Coordinate end = Coordinate.fromString(coordinateStrings[1]);

        String expectedInputString = start.toString() + "-" + end.toString();
        if(!expectedInputString.equals(wallString)){
            throw new IllegalArgumentException("the wall string was ill-formatted");
        }
        return new Wall(start,end);
    }

    public Wall(Coordinate pos1, Coordinate pos2) {
        ensureValidCoordinateInput(pos1,pos2);
        setInRightOrder(pos1,pos2);
    }

    void setInRightOrder(Coordinate c1, Coordinate c2){
        if(c1.getX() <= c2.getX() && c1.getY() <= c2.getY()){
            this.start = c1;
            this.end = c2;
        } else {
            this.start = c2;
            this.end = c1;
        }
    }

    void ensureValidCoordinateInput(Coordinate start, Coordinate end){
        if(start.getX() < end.getX() && start.getY() != end.getY()){
            throw new IllegalArgumentException(
                    "a wall must be either vertical or horizontal"
            );
        }
        if(start.getY() < end.getY() && start.getX() != end.getX()){
            throw new IllegalArgumentException(
                    "a wall must be either vertical or horizontal"
            );
        }
    }


    @Override
    public String toString(){
        return getStart() + "-" + getEnd();
    }

}

/*
public class Wall {

    private Coordinate start;
    private Coordinate end;


    public Wall(Coordinate start, Coordinate end) {
        
        this.start = start;
        this.end = end;
        
    }
    
    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    /**
     * @param wallString the wall in form of a string e.g. (1,2)-(1,4)
     *//*
    public static Wall fromString(String wallString) {
        throw new UnsupportedOperationException();
    }
    
}*/
