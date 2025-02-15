package thkoeln.st.st2praktikum.exercise.miningmachine.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Command;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Point;
import thkoeln.st.st2praktikum.exercise.field.domain.Field;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MiningMachine implements Moveable {
    @Id
    private UUID miningMachineId;

    private String name;
    @Embedded
    private Point point;

    private UUID field;

    @ElementCollection(targetClass = Command.class,fetch = FetchType.EAGER)
    private List<Command> Commands = new ArrayList<>();

    public MiningMachine(String name) {
        this.name = name;
        this.miningMachineId = UUID.randomUUID();
        this.point = new Point(0,0);
    }


    @Override
    public void moveWest() {
        this.point = new Point(point.getX()-1, point.getY());
    }

    @Override
    public void moveSouth(){
        this.point = new Point(this.point.getX(), this.point.getY()-1);
    }

    @Override
    public void moveEast(){
        this.point = new Point(this.point.getX()+1, this.point.getY());
    }

    @Override
    public void moveNorth(){
        this.point = new Point(this.point.getX(), this.point.getY()+1);
    }

    public Boolean isBlocked(ArrayList<Field> fieldList, Point point) {
        for (Field value : fieldList) {
            if (this.field == value.getFieldID()) {
                if (point.getY() == value.getHeight()) return true;
                if (point.getX() == value.getWidth()) return true;
                for( int i = 0; i < value.getBarriers().size();i++){
                    if( value.getBarriers().get(i).getStart().getY().equals(point.getY()) && value.getBarriers().get(i).getEnd().getY().equals(point.getY()) &&
                            value.getBarriers().get(i).getStart().getX() <= point.getX() &&  point.getX() < value.getBarriers().get(i).getEnd().getX()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public UUID getFieldId() {
            return this.field;
    }
}

