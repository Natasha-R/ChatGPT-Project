package thkoeln.st.st2praktikum.exercise;

import lombok.Getter;

@Getter
public class Step {
    private final Point first;
    private final Point second;
    private final Boolean useable;

    public Step(Point first, Point second, Boolean useable) {
        this.first = first;
        this.second = second;
        this.useable = useable;
    }

    public Boolean equals(Point pFirst, Point pSecond) {
        return pFirst.getX() == this.first.getX() && pFirst.getY() == this.first.getY() &&
                pSecond.getX() == this.second.getX() && pSecond.getY() == this.second.getY();
    }

}
