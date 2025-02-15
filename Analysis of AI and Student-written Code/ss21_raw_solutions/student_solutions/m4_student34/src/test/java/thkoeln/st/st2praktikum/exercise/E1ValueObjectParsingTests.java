package thkoeln.st.st2praktikum.exercise;

import org.junit.jupiter.api.Test;
import thkoeln.st.st2praktikum.exercise.spaceshipdeck.domain.Obstacle;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Task;
import thkoeln.st.st2praktikum.exercise.domainprimitives.TaskType;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Coordinate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class E1ValueObjectParsingTests {

    @Test
    public void moveTaskParsingTest() {
        Task moveTask = Task.fromString("[so,4]");
        assertEquals(TaskType.SOUTH, moveTask.getTaskType());
        assertEquals(Integer.valueOf(4), moveTask.getNumberOfSteps());
    }

    @Test
    public void taskFailedParsingTest() {
        assertThrows(RuntimeException.class, () -> Task.fromString("[soo,4]"));
        assertThrows(RuntimeException.class, () -> Task.fromString("[4, no]"));
        assertThrows(RuntimeException.class, () -> Task.fromString("[[soo,4]]"));
        assertThrows(RuntimeException.class, () -> Task.fromString("(soo,4)"));
    }

    @Test
    public void coordinateParsingTest() {
        Coordinate coordinate = Coordinate.fromString("(2,3)");
        assertEquals(new Coordinate(2, 3), coordinate);
    }

    @Test
    public void coordinateFailedParsingTest() {
        assertThrows(RuntimeException.class, () -> Coordinate.fromString("(2,,3)"));
        assertThrows(RuntimeException.class, () -> Coordinate.fromString("((2,3]"));
        assertThrows(RuntimeException.class, () -> Coordinate.fromString("(1,2,3)"));
        assertThrows(RuntimeException.class, () -> Coordinate.fromString("(1,)"));
    }

    @Test
    public void obstacleParsingTest() {
        Obstacle obstacle = Obstacle.fromString("(1,2)-(1,4)");
        assertEquals(new Coordinate(1, 2), obstacle.getStart());
        assertEquals(new Coordinate(1, 4), obstacle.getEnd());
    }

    @Test
    public void obstacleParsingFailedTest() {
        assertThrows(RuntimeException.class, () -> Obstacle.fromString("(1,2-1,4)"));
        assertThrows(RuntimeException.class, () -> Obstacle.fromString("(1,2),(1,4)"));
        assertThrows(RuntimeException.class, () -> Obstacle.fromString("(1,)-(1,4)"));
        assertThrows(RuntimeException.class, () -> Obstacle.fromString("(1,4)"));
    }


}
