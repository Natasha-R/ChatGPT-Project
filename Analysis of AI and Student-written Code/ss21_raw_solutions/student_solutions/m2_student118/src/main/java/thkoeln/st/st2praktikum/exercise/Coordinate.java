package thkoeln.st.st2praktikum.exercise;

import java.util.Objects;

public class Coordinate {

    private Integer x;
    private Integer y;


    public Coordinate(Integer x, Integer y) {
        if (x>=0 && y>=0) {
            this.x = x;
            this.y = y;
        }else throw new RuntimeException();
    }

    /**
     * @param coordinateString the coordinate in form of a string e.g. (1,2)
     */
    public Coordinate(String coordinateString) {
        Integer x = Integer.parseInt(String.valueOf(coordinateString.charAt(1)));
        Integer y =  Integer.parseInt(String.valueOf(coordinateString.charAt(3)));

        // check if the String is correct
        if (coordinateString.length()>=5 && coordinateString.replaceAll("[0-9]","").equals("(,)") && coordinateString.replaceAll("[^0-9]","").length()==2){
            if (x >=0 && y >=0) {
                this.x = x;
                this.y = y;
            }else throw new RuntimeException();
        }else throw new RuntimeException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
