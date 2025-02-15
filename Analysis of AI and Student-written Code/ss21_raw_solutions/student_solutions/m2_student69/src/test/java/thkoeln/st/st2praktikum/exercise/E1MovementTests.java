package thkoeln.st.st2praktikum.exercise;

import org.junit.jupiter.api.Test;
import thkoeln.st.st2praktikum.exercise.core.MovementTests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class E1MovementTests extends MovementTests {

    @Test
    public void miningMachineSpawnOnSamePositionTest() {
        MiningMachineService service = new MiningMachineService();
        createWorld(service);

        assertTrue(service.executeCommand(miningMachine1, new Task("[en," + field1 + "]")));
        assertFalse(service.executeCommand(miningMachine2, new Task("[en," + field1 + "]")));
    }

    @Test
    public void moveToAnotherFieldAndBackTest() {
        MiningMachineService service = new MiningMachineService();
        createWorld(service);

        executeTasks(service, miningMachine1, new Task[]{
                new Task("[en," + field2 + "]"),
                new Task("[ea,1]"),
                new Task("[tr," + field3 + "]")
        });

        assertPosition(service, miningMachine1, field3, 2, 2);

        executeTasks(service, miningMachine1, new Task[]{
                new Task("[tr," + field2 + "]")
        });

        assertPosition(service, miningMachine1, field2, 1, 0);
    }

    @Test
    public void moveToAnotherFieldOnWrongPositionTest() {
        MiningMachineService service = new MiningMachineService();
        createWorld(service);

        service.executeCommand(miningMachine1, new Task("[en," + field1 + "]"));
        assertFalse(service.executeCommand(miningMachine1, new Task("[tr," + field2 + "]")));

        assertPosition(service, miningMachine1, field1, 0, 0);
    }

    @Test
    public void bumpIntoBarrierTest() {
        MiningMachineService service = new MiningMachineService();
        createWorld(service);

        executeTasks(service, miningMachine1, new Task[]{
                new Task("[en," + field2 + "]"),
                new Task("[ea,2]"),
                new Task("[no,3]"),
                new Task("[we,1]"),
        });

        assertPosition(service, miningMachine1, field2, 1, 1);
    }

    @Test
    public void moveTwoMiningMachinesAtOnceTest() {
        MiningMachineService service = new MiningMachineService();
        createWorld(service);

        executeTasks(service, miningMachine1, new Task[]{
                new Task("[en," + field1 + "]"),
                new Task("[ea,1]"),
                new Task("[no,4]")
        });

        executeTasks(service, miningMachine2, new Task[]{
                new Task("[en," + field1 + "]"),
                new Task("[ea,2]"),
                new Task("[no,5]")
        });

        assertPosition(service, miningMachine1, field1, 1, 2);
        assertPosition(service, miningMachine2, field1, 2, 5);
    }

    @Test
    public void moveOutOfBoundariesTest() {
        MiningMachineService service = new MiningMachineService();
        createWorld(service);

        executeTasks(service, miningMachine1, new Task[]{
                new Task("[en," + field3 + "]"),
                new Task("[no,5]"),
                new Task("[ea,5]"),
                new Task("[so,5]"),
                new Task("[we,5]"),
                new Task("[no,1]")
        });

        assertPosition(service, miningMachine1, field3, 0, 1);
    }

    @Test
    public void traverseAllFieldsTest() {
        MiningMachineService service = new MiningMachineService();
        createWorld(service);

        executeTasks(service, miningMachine1, new Task[]{
                new Task("[en," + field1 + "]"),
                new Task("[no,1]"),
                new Task("[ea,1]"),
                new Task("[tr," + field2 + "]"),
                new Task("[so,2]"),
                new Task("[ea,1]"),
                new Task("[tr," + field3 + "]"),
                new Task("[we,5]"),
                new Task("[ea,2]"),
                new Task("[tr," + field2 + "]"),
                new Task("[no,4]"),
                new Task("[ea,3]")
        });

        assertPosition(service, miningMachine1, field2, 4, 1);
    }
}
