package thkoeln.st.st2praktikum.exercise;

import thkoeln.st.st2praktikum.exercise.converter.StringConverter;

import java.util.UUID;

public class Order {

    private final OrderType orderType;
    private Integer numberOfSteps;
    private UUID gridId;


    public Order(OrderType orderType, Integer numberOfSteps) {
        if ( numberOfSteps < 0 ) {
            throw new IllegalArgumentException( "No negative number fpr Steps allowed" );
        }

        this.orderType = orderType;
        this.numberOfSteps = numberOfSteps;
    }

    public Order(OrderType orderType, UUID gridId) {
        this.orderType = orderType;
        this.gridId = gridId;
    }

    /**
     * @param orderString the command in form of a string e.g. [no, 3], [en, <uuid>], [tr, <uuid>]
     */
    public Order(String orderString) {
        StringConverter convert = new StringConverter();
        this.orderType = convert.toOrderType( orderString );
        switch( orderType ) {
            case ENTER:
            case TRANSPORT:
                this.gridId = convert.toUUID( orderString );
                break;
            case NORTH:
            case EAST:
            case SOUTH:
            case WEST:
                this.numberOfSteps = convert.toSteps( orderString );
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
