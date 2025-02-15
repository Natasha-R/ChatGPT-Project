package thkoeln.st.st2praktikum.exercise;

import org.junit.jupiter.api.Test;
import thkoeln.st.st2praktikum.exercise.field.domain.Wall;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Order;
import thkoeln.st.st2praktikum.exercise.domainprimitives.OrderType;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Coordinate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class E1ValueObjectValidationTests {

    @Test
    public void ensureWallCoordinatesAreOrderedTest() {
        Wall wall1 = new Wall(new Coordinate(2, 1 ), new Coordinate(4, 1 ));
        assertEquals(new Coordinate(2, 1 ), wall1.getStart());
        assertEquals(new Coordinate(4, 1 ), wall1.getEnd());

        Wall wall2 = new Wall(new Coordinate(4, 1 ), new Coordinate(2, 1 ));
        assertEquals(new Coordinate(2, 1 ), wall2.getStart());
        assertEquals(new Coordinate(4, 1 ), wall2.getEnd());

        Wall wall3 = new Wall(new Coordinate(2, 4 ), new Coordinate(2, 2 ));
        assertEquals(new Coordinate(2, 2 ), wall3.getStart());
        assertEquals(new Coordinate(2, 4 ), wall3.getEnd());
    }

    @Test
    public void noDiagonalWallTest() {
        assertThrows(RuntimeException.class, () -> new Wall(new Coordinate(1, 1 ), new Coordinate(2, 2 )));
        assertThrows(RuntimeException.class, () -> new Wall(new Coordinate(2, 1 ), new Coordinate(5, 6 )));
    }

    @Test
    public void noNegativeCoordinateTest() {
        assertThrows(RuntimeException.class, () -> new Coordinate(-2, 1));
        assertThrows(RuntimeException.class, () -> new Coordinate(2, -1));
    }

    @Test
    public void noNegativeStepsOnOrderTest() {
        assertThrows(RuntimeException.class, () -> new Order(OrderType.NORTH, -2));
    }
}
