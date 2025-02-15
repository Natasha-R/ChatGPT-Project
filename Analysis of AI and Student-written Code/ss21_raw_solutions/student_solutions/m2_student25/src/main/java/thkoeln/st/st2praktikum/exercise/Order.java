package thkoeln.st.st2praktikum.exercise;

import thkoeln.st.st2praktikum.exercise.command.Direction;
import thkoeln.st.st2praktikum.exercise.services.StringService;

import java.util.UUID;

public class Order {

    private OrderType orderType;
    private Integer numberOfSteps;
    private UUID gridId;


    public Order(OrderType orderType, Integer numberOfSteps) {
        this.orderType = orderType;
        this.numberOfSteps = numberOfSteps;
        if (numberOfSteps < 0) throw new RuntimeException("No negative Steps in Order");
    }

    public Order(OrderType orderType, UUID gridId) {
        this.orderType = orderType;
        this.gridId = gridId;
    }

    /**
     * @param orderString the command in form of a string e.g. [no, 3], [en, <uuid>], [tr, <uuid>]
     */
    public Order(String orderString) {
        orderString = orderString.replace("[", "");
        orderString = orderString.replace("]", "");
        String[] resultOrderString = orderString.split(",");
        Direction direction = StringService.translateToDirection(resultOrderString[0]);
        switch (direction) {
            case NO:
                this.orderType = OrderType.NORTH;
                this.numberOfSteps = Integer.parseInt(resultOrderString[1]);
                break;
            case EA:
                this.orderType = OrderType.EAST;
                this.numberOfSteps = Integer.parseInt(resultOrderString[1]);
                break;
            case SO:
                this.orderType = OrderType.SOUTH;
                this.numberOfSteps = Integer.parseInt(resultOrderString[1]);
                break;
            case WE:
                this.orderType = OrderType.WEST;
                this.numberOfSteps = Integer.parseInt(resultOrderString[1]);
                break;
            case EN:
                this.orderType = OrderType.ENTER;
                this.gridId = UUID.fromString(resultOrderString[1]);
                break;
            case TR:
                this.orderType = OrderType.TRANSPORT;
                this.gridId = UUID.fromString(resultOrderString[1]);
                break;
        }
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Integer getNumberOfSteps() {
        return numberOfSteps;
    }

    public UUID getGridId() {
        return gridId;
    }
}
