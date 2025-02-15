package thkoeln.st.st2praktikum.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.context.WebApplicationContext;
import thkoeln.st.st2praktikum.exercise.core.MovementTests;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class E1MovementTests extends MovementTests {

    private CleaningDeviceService service;

    @Autowired
    public E1MovementTests(WebApplicationContext appContext, CleaningDeviceService service) {
        super(appContext);

        this.service = service;
    }

    @BeforeEach
    public void init() {
        createWorld(service);
    }

    @Test
    @Transactional
    public void cleaningDevicesSpawnOnSamePositionTest() {
        assertTrue(service.executeCommand(cleaningDevice1, new Order("[en," + space1 + "]")));
        assertFalse(service.executeCommand(cleaningDevice2, new Order("[en," + space1 + "]")));
    }

    @Test
    @Transactional
    public void moveToAnotherSpaceAndBackTest() throws Exception {
        executeOrders(service, cleaningDevice1, new Order[]{
                new Order("[en," + space2 + "]"),
                new Order("[ea,1]"),
                new Order("[tr," + space3 + "]")
        });

        assertPosition(cleaningDevice1, space3, 2, 2);

        executeOrders(service, cleaningDevice1, new Order[]{
                new Order("[tr," + space2 + "]")
        });

        assertPosition(cleaningDevice1, space2, 1, 0);
    }

    @Test
    @Transactional
    public void moveToAnotherSpaceOnWrongPositionTest() throws Exception {
        service.executeCommand(cleaningDevice1, new Order("[en," + space1 + "]"));
        assertFalse(service.executeCommand(cleaningDevice1, new Order("[tr," + space2 + "]")));

        assertPosition(cleaningDevice1, space1, 0, 0);
    }

    @Test
    @Transactional
    public void bumpIntoObstacleTest() throws Exception {
        executeOrders(service, cleaningDevice1, new Order[]{
                new Order("[en," + space2 + "]"),
                new Order("[ea,2]"),
                new Order("[no,3]"),
                new Order("[we,1]"),
        });

        assertPosition(cleaningDevice1, space2, 1, 1);
    }

    @Test
    @Transactional
    public void moveTwoCleaningDevicesAtOnceTest() throws Exception {
        executeOrders(service, cleaningDevice1, new Order[]{
                new Order("[en," + space1 + "]"),
                new Order("[ea,1]"),
                new Order("[no,4]")
        });

        executeOrders(service, cleaningDevice2, new Order[]{
                new Order("[en," + space1 + "]"),
                new Order("[ea,2]"),
                new Order("[no,5]")
        });

        assertPosition(cleaningDevice1, space1, 1, 2);
        assertPosition(cleaningDevice2, space1, 2, 5);

    }

    @Test
    @Transactional
    public void moveOutOfBoundariesTest() throws Exception {
        executeOrders(service, cleaningDevice1, new Order[]{
                new Order("[en," + space3 + "]"),
                new Order("[no,5]"),
                new Order("[ea,5]"),
                new Order("[so,5]"),
                new Order("[we,5]"),
                new Order("[no,1]")
        });

        assertPosition(cleaningDevice1, space3, 0, 1);
    }

    @Test
    @Transactional
    public void traverseOnOneSpaceTest() throws Exception {
        service.executeCommand(cleaningDevice1, new Order("[en," + space1 + "]"));
        assertPosition(cleaningDevice1, space1, 0, 0);

        service.executeCommand(cleaningDevice1, new Order("[ea,2]"));
        assertPosition(cleaningDevice1, space1, 2, 0);

        service.executeCommand(cleaningDevice1, new Order("[no,4]"));
        assertPosition(cleaningDevice1, space1, 2, 4);

        service.executeCommand(cleaningDevice1, new Order("[we,5]"));
        assertPosition(cleaningDevice1, space1, 0, 4);
    }

    @Test
    @Transactional
    public void traverseAllSpacesTest() throws Exception {
        executeOrders(service, cleaningDevice1, new Order[]{
                new Order("[en," + space1 + "]"),
                new Order("[no,1]"),
                new Order("[ea,1]"),
                new Order("[tr," + space2 + "]"),
                new Order("[so,2]"),
                new Order("[ea,1]"),
                new Order("[tr," + space3 + "]"),
                new Order("[we,5]"),
                new Order("[ea,2]"),
                new Order("[tr," + space2 + "]"),
                new Order("[no,4]"),
                new Order("[ea,3]")
        });

        assertPosition(cleaningDevice1, space2, 4, 1);
    }
}
