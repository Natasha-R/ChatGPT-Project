package thkoeln.st.st2praktikum.exercise.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.context.WebApplicationContext;
import thkoeln.st.springtestlib.core.GenericTests;
import thkoeln.st.st2praktikum.exercise.cleaningdevice.application.CleaningDeviceService;
import thkoeln.st.st2praktikum.exercise.space.application.SpaceService;
import thkoeln.st.st2praktikum.exercise.space.domain.Wall;
import thkoeln.st.st2praktikum.exercise.transportcategory.application.TransportCategoryService;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Task;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Point;

import java.lang.reflect.Method;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public abstract class MovementTests extends GenericTests {

    protected static final String ROBOT_NAME_1 = "Marvin";
    protected static final String ROBOT_NAME_2 = "Darvin";

    protected UUID space1;
    protected UUID space2;
    protected UUID space3;

    protected UUID cleaningDevice1;
    protected UUID cleaningDevice2;

    protected UUID transportCategory1;
    protected UUID transportCategory2;

    protected UUID connection1;
    protected UUID connection2;
    protected UUID connection3;


    protected MovementTests(WebApplicationContext appContext) {
        super(appContext);
    }


    protected void createWorld(CleaningDeviceService cleaningDeviceService, SpaceService spaceService, TransportCategoryService transportCategoryService) {
        space1 = spaceService.addSpace(6,6);
        space2 = spaceService.addSpace(5,5);
        space3 = spaceService.addSpace(3,3);

        spaceService.addWall(space1, Wall.fromString("(0,3)-(2,3)"));
        spaceService.addWall(space1, Wall.fromString("(3,0)-(3,3)"));
        spaceService.addWall(space2, Wall.fromString("(0,2)-(4,2)"));

        cleaningDevice1 = cleaningDeviceService.addCleaningDevice(ROBOT_NAME_1);
        cleaningDevice2 = cleaningDeviceService.addCleaningDevice(ROBOT_NAME_2);

        transportCategory1 = transportCategoryService.addTransportCategory("Staircase");
        transportCategory2 = transportCategoryService.addTransportCategory("Elevator");

        connection1 = transportCategoryService.addConnection(transportCategory1, space1, Point.fromString("(1,1)"), space2, Point.fromString("(0,1)"));
        connection2 = transportCategoryService.addConnection(transportCategory1, space2, Point.fromString("(1,0)"), space3, Point.fromString("(2,2)"));
        connection3 = transportCategoryService.addConnection(transportCategory2, space3, Point.fromString("(2,2)"), space2, Point.fromString("(1,0)"));
    }

    protected void assertPosition(UUID cleaningDeviceId, UUID expectedSpaceId, int expectedX, int expectedY) throws Exception {
        Object cleaningDevice = getCleaningDeviceRepository().findById(cleaningDeviceId).get();

        // Assert Grid
        Method getSpaceMethod = cleaningDevice.getClass().getMethod("getSpaceId");
        assertEquals(expectedSpaceId,
                getSpaceMethod.invoke(cleaningDevice));

        // Assert Pos
        Method getPointMethod = cleaningDevice.getClass().getMethod("getPoint");
        assertEquals(new Point(expectedX, expectedY),
                getPointMethod.invoke(cleaningDevice));
    }

    protected CrudRepository<Object, UUID> getCleaningDeviceRepository() throws Exception {
        return oir.getRepository("thkoeln.st.st2praktikum.exercise.cleaningdevice.domain.CleaningDevice");
    }

    protected void executeTasks(CleaningDeviceService service, UUID cleaningDevice, Task[] tasks) {
        for (Task task : tasks) {
            service.executeCommand(cleaningDevice, task);
        }
    }
}
