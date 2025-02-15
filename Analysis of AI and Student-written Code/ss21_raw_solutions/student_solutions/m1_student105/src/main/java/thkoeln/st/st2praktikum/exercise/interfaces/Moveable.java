package thkoeln.st.st2praktikum.exercise.interfaces;

import thkoeln.st.st2praktikum.exercise.entities.Coordinate;

public interface Moveable {
    Coordinate getCurrentPosition();
    void setCurrentPosition(Coordinate coordinate);
    Grid getGrid();
}
