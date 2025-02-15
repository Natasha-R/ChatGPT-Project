package thkoeln.st.st2praktikum.exercise.core;

import thkoeln.st.st2praktikum.exercise.MiningMachineService;
import thkoeln.st.st2praktikum.exercise.Obstacle;
import thkoeln.st.st2praktikum.exercise.Command;
import thkoeln.st.st2praktikum.exercise.Point;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class MovementTests {

    protected UUID field1;
    protected UUID field2;
    protected UUID field3;

    protected UUID miningMachine1;
    protected UUID miningMachine2;

    protected UUID connection1;
    protected UUID connection2;
    protected UUID connection3;


    protected void createWorld(MiningMachineService service) {
        field1 = service.addField(6,6);
        field2 = service.addField(5,5);
        field3 = service.addField(3,3);

        miningMachine1 = service.addMiningMachine("marvin");
        miningMachine2 = service.addMiningMachine("darwin");

        service.addObstacle(field1, new Obstacle("(0,3)-(2,3)"));
        service.addObstacle(field1, new Obstacle("(3,0)-(3,3)"));
        service.addObstacle(field2, new Obstacle("(0,2)-(4,2)"));

        connection1 = service.addConnection(field1, new Point("(1,1)"), field2, new Point("(0,1)"));
        connection2 = service.addConnection(field2, new Point("(1,0)"), field3, new Point("(2,2)"));
        connection3 = service.addConnection(field3, new Point("(2,2)"), field2, new Point("(1,0)"));
    }

    protected void assertPosition(MiningMachineService service, UUID miningMachineId, UUID expectedFieldId, int expectedX, int expectedY) {
        assertEquals(expectedFieldId,
                service.getMiningMachineFieldId(miningMachineId));
        assertEquals(new Point(expectedX, expectedY),
                service.getPoint(miningMachineId));
    }

    protected void executeCommands(MiningMachineService service, UUID miningMachine, Command[] Commands) {
        for (Command command : Commands) {
            service.executeCommand(miningMachine, command);
        }
    }
}
