package thkoeln.st.st2praktikum.exercise;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import thkoeln.st.st2praktikum.exercise.exceptions.TaskFormatException;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class Task {

    @Getter
    private final TaskType taskType;
    @Getter
    private Integer numberOfSteps;
    @Getter
    private UUID gridId;

    //constructor for movement tasks
    public Task(TaskType taskType, Integer numberOfSteps) {
        this.taskType = taskType;
        this.numberOfSteps = numberOfSteps;
        if(numberOfSteps < 0) throw new TaskFormatException();
    }

    //constructor for room dependent tasks
    public Task(TaskType taskType, UUID gridId) {
        this.taskType = taskType;
        this.gridId = gridId;
    }
    protected Task(){
        taskType = null;
        numberOfSteps = null;
        gridId = null;
    }

    /**
     * @param taskString the command in form of a string e.g. [no, 3], [en, <uuid>], [tr, <uuid>]
     */
    public Task(String taskString) {
        taskString = taskString.replace("[","").replace("]","");
        String[] instruction = taskString.split(",");
        switch(instruction[0]){
            case "tr":
                this.taskType = TaskType.TRANSPORT;
                this.gridId = UUID.fromString(instruction[1]); break;
            case "en":
                this.taskType = TaskType.ENTER;
                this.gridId = UUID.fromString(instruction[1]); break;
            case "no":
                this.taskType = TaskType.NORTH;
                this.numberOfSteps = Integer.parseInt(instruction[1]); break;
            case "so":
                this.taskType = TaskType.SOUTH;
                this.numberOfSteps = Integer.parseInt(instruction[1]); break;
            case "ea":
                this.taskType = TaskType.EAST;
                this.numberOfSteps = Integer.parseInt(instruction[1]); break;
            case "we":
                this.taskType = TaskType.WEST;
                this.numberOfSteps = Integer.parseInt(instruction[1]); break;
            default:throw new TaskFormatException();
        }
    }

    public DirectionsType giveDirection(){
        switch (this.taskType){
            case SOUTH: return DirectionsType.SOUTH;
            case NORTH: return DirectionsType.NORTH;
            case EAST:  return DirectionsType.EAST;
            case WEST:  return DirectionsType.WEST;
        }
        return null;
    }
}
