package thkoeln.st.st2praktikum.exercise;

import org.junit.jupiter.api.Test;
import thkoeln.st.st2praktikum.exercise.field.domain.Obstacle;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Command;
import thkoeln.st.st2praktikum.exercise.domainprimitives.CommandType;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class E1ValueObjectParsingTests {

    @Test
    public void moveCommandParsingTest() {
        Command moveCommand = Command.fromString("[so,4]");
        assertEquals(CommandType.SOUTH, moveCommand.getCommandType());
        assertEquals(Integer.valueOf(4), moveCommand.getNumberOfSteps());
    }

    @Test
    public void commandFailedParsingTest() {
        assertThrows(RuntimeException.class, () -> Command.fromString("[soo,4]"));
        assertThrows(RuntimeException.class, () -> Command.fromString("[4, no]"));
        assertThrows(RuntimeException.class, () -> Command.fromString("[[soo,4]]"));
        assertThrows(RuntimeException.class, () -> Command.fromString("(soo,4)"));
    }

    @Test
    public void pointParsingTest() {
        Point point = Point.fromString("(2,3)");
        assertEquals(new Point(2, 3), point);
    }

    @Test
    public void pointFailedParsingTest() {
        assertThrows(RuntimeException.class, () -> Point.fromString("(2,,3)"));
        assertThrows(RuntimeException.class, () -> Point.fromString("((2,3]"));
        assertThrows(RuntimeException.class, () -> Point.fromString("(1,2,3)"));
        assertThrows(RuntimeException.class, () -> Point.fromString("(1,)"));
    }

    @Test
    public void obstacleParsingTest() {
        Obstacle obstacle = Obstacle.fromString("(1,2)-(1,4)");
        assertEquals(new Point(1, 2), obstacle.getStart());
        assertEquals(new Point(1, 4), obstacle.getEnd());
    }

    @Test
    public void obstacleParsingFailedTest() {
        assertThrows(RuntimeException.class, () -> Obstacle.fromString("(1,2-1,4)"));
        assertThrows(RuntimeException.class, () -> Obstacle.fromString("(1,2),(1,4)"));
        assertThrows(RuntimeException.class, () -> Obstacle.fromString("(1,)-(1,4)"));
        assertThrows(RuntimeException.class, () -> Obstacle.fromString("(1,4)"));
    }


}
