package thkoeln.st.st2praktikum.exercise;

import lombok.Getter;
import thkoeln.st.st2praktikum.exercise.Exception.TaskException;

import java.util.UUID;

public class Task {

    @Getter
    private TaskType taskType;
    @Getter
    private Integer numberOfSteps;
    @Getter
    private UUID gridId;


    public Task(TaskType taskType, Integer numberOfSteps) {
        if (numberOfSteps < 0)
            throw new RuntimeException();
        this.taskType = taskType;
        this.numberOfSteps = numberOfSteps;
    }

    public Task(TaskType taskType, UUID gridId) {
        this.taskType = taskType;
        this.gridId = gridId;
    }

    /**
     * @param taskString the command in form of a string e.g. [no, 3], [en, <uuid>], [tr, <uuid>]
     */
    public Task(String taskString) {
        if (!taskString.matches("^\\[.+,.+]$"))
            throw new TaskException("Formatfehler: " + taskString);

        String[] taskArguments = taskString
                .replaceAll("^\\[|]$", "")
                .split(",", 2);

        switch (taskArguments[0]) {
            case "en":
                taskType = TaskType.ENTER;
                gridId = UUID.fromString(taskArguments[1]);
                break;
            case "tr":
                taskType = TaskType.TRANSPORT;
                gridId = UUID.fromString(taskArguments[1]);
                break;
            default: switch (taskArguments[0]) {
                    case "we": taskType = TaskType.WEST; break;
                    case "ea": taskType = TaskType.EAST; break;
                    case "so": taskType = TaskType.SOUTH; break;
                    case "no": taskType = TaskType.NORTH; break;
                    default:
                        throw new TaskException("Unbekannter Befehl: " + taskString);
                }
                numberOfSteps = Integer.parseInt(taskArguments[1]);
                break;
        }
    }
}
