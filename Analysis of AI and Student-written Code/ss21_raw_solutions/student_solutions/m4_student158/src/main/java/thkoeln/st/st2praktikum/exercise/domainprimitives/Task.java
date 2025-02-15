package thkoeln.st.st2praktikum.exercise.domainprimitives;

import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Task {

    private TaskType taskType;
    private Integer numberOfSteps;
    @JsonIgnore
    private UUID gridId;



    protected Task() {
    }

    public Task(String taskString) {
        checkFormat(taskString);
        String[] taskArray = taskString.replace("[", "").replace("]", "").split(",");
        setValues(taskArray);
    }


    public Task(TaskType taskType, Integer numberOfSteps) {
        this.taskType = taskType;
        setNumberOfSteps(numberOfSteps);
    }

    public Task(TaskType taskType, UUID gridId) {
        this.taskType = taskType;
        this.gridId = gridId;
    }

    private void checkFormat(String taskString){
        Pattern movementPattern = Pattern.compile("^\\[[A-Za-z]{2},[a-z0-9]{1,2}\\]" ,Pattern.CASE_INSENSITIVE);
        Matcher movementMatcher = movementPattern.matcher(taskString);
        Pattern enterOrTransportPattern = Pattern.compile("^\\[[A-Za-z]{2},([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})\\]");
        Matcher enterOrTransportMatcher = enterOrTransportPattern.matcher(taskString);
        if(!(movementMatcher.matches() || enterOrTransportMatcher.matches())){
            throw new IllegalArgumentException("Task String not in expected format");
        }
    }

    private void setValues(String[] taskArray){
        switch (taskArray[0]){
            case"no":
                this.taskType = TaskType.NORTH;
                setNumberOfSteps(Integer.parseInt(taskArray[1]));
                break;
            case "ea":
                this.taskType = TaskType.EAST;
                setNumberOfSteps(Integer.parseInt(taskArray[1]));
                break;
            case "so":
                this.taskType = TaskType.SOUTH;
                setNumberOfSteps(Integer.parseInt(taskArray[1]));
                break;
            case "we":
                this.taskType = TaskType.WEST;
                setNumberOfSteps(Integer.parseInt(taskArray[1]));
                break;
            case "tr":
                this.taskType = TaskType.TRANSPORT;
                this.gridId = UUID.fromString(taskArray[1]);
                break;
            case "en":
                this.taskType = TaskType.ENTER;
                this.gridId = UUID.fromString(taskArray[1]);
                break;
            default:
                throw new IllegalArgumentException("TaskType is unknown.");
        }
    }

    private void setNumberOfSteps(Integer steps){
        if(steps>=0){
            this.numberOfSteps = steps;
        } else {
            throw new IllegalArgumentException("Number Of Steps must no be negative");
        }
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Integer getNumberOfSteps() {
        return numberOfSteps;
    }

    @JsonIgnore
    public UUID getGridId() {
        return gridId;
    }

    public static Task fromString(String taskString ) {
        return new Task(taskString);
    }
    
}
