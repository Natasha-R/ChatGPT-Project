package thkoeln.st.st2praktikum.exercise;

import org.junit.jupiter.api.Test;
import thkoeln.st.st2praktikum.exercise.room.domain.Barrier;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Command;
import thkoeln.st.st2praktikum.exercise.domainprimitives.CommandType;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class E1ValueObjectValidationTests {

    @Test
    public void ensureBarrierPointsAreOrderedTest() {
        Barrier barrier1 = new Barrier(new Point(2, 1 ), new Point(4, 1 ));
        assertEquals(new Point(2, 1 ), barrier1.getStart());
        assertEquals(new Point(4, 1 ), barrier1.getEnd());

        Barrier barrier2 = new Barrier(new Point(4, 1 ), new Point(2, 1 ));
        assertEquals(new Point(2, 1 ), barrier2.getStart());
        assertEquals(new Point(4, 1 ), barrier2.getEnd());

        Barrier barrier3 = new Barrier(new Point(2, 4 ), new Point(2, 2 ));
        assertEquals(new Point(2, 2 ), barrier3.getStart());
        assertEquals(new Point(2, 4 ), barrier3.getEnd());
    }

    @Test
    public void noDiagonalBarrierTest() {
        assertThrows(RuntimeException.class, () -> new Barrier(new Point(1, 1 ), new Point(2, 2 )));
        assertThrows(RuntimeException.class, () -> new Barrier(new Point(2, 1 ), new Point(5, 6 )));
    }

    @Test
    public void noNegativePointTest() {
        assertThrows(RuntimeException.class, () -> new Point(-2, 1));
        assertThrows(RuntimeException.class, () -> new Point(2, -1));
    }

    @Test
    public void noNegativeStepsOnCommandTest() {
        assertThrows(RuntimeException.class, () -> new Command(CommandType.NORTH, -2));
    }
}
