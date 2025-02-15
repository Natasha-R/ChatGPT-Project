package thkoeln.st.st2praktikum.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class E1ValueObjectParsingTests {

    @Test
    public void moveOrderParsingTest() {
        Order moveOrder = new Order("[so,4]");
        assertEquals(OrderType.SOUTH, moveOrder.getOrderType());
        assertEquals(Integer.valueOf(4), moveOrder.getNumberOfSteps());
    }

    @Test
    public void orderFailedParsingTest() {
        assertThrows(RuntimeException.class, () -> new Order("[soo,4]"));
        assertThrows(RuntimeException.class, () -> new Order("[4, no]"));
        assertThrows(RuntimeException.class, () -> new Order("[[soo,4]]"));
        assertThrows(RuntimeException.class, () -> new Order("(soo,4)"));
    }

    @Test
    public void coordinateParsingTest() {
        Coordinate coordinate = new Coordinate("(2,3)");
        assertEquals(new Coordinate(2, 3), coordinate);
    }

    @Test
    public void coordinateFailedParsingTest() {
        assertThrows(RuntimeException.class, () -> new Coordinate("(2,,3)"));
        assertThrows(RuntimeException.class, () -> new Coordinate("((2,3]"));
        assertThrows(RuntimeException.class, () -> new Coordinate("(1,2,3)"));
        assertThrows(RuntimeException.class, () -> new Coordinate("(1,)"));
    }

    @Test
    public void barrierParsingTest() {
        Barrier barrier = new Barrier("(1,2)-(1,4)");
        assertEquals(new Coordinate(1, 2), barrier.getStart());
        assertEquals(new Coordinate(1, 4), barrier.getEnd());
    }

    @Test
    public void barrierParsingFailedTest() {
        assertThrows(RuntimeException.class, () -> new Barrier("(1,2-1,4)"));
        assertThrows(RuntimeException.class, () -> new Barrier("(1,2),(1,4)"));
        assertThrows(RuntimeException.class, () -> new Barrier("(1,)-(1,4)"));
        assertThrows(RuntimeException.class, () -> new Barrier("(1,4)"));
    }


}
