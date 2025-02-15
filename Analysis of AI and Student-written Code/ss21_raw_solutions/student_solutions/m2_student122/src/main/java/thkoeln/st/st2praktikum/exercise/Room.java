package thkoeln.st.st2praktikum.exercise;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {

    private UUID id = UUID.randomUUID();

    private Integer width;
    private Integer height;

    private Connection connection;

    private List<Obstacle> obstacleList = new ArrayList<>();


    public Room(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }
}
