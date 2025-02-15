package thkoeln.st.st2praktikum.exercise;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import thkoeln.st.st2praktikum.exercise.CustomExceptions.DiagonalBarrierException;
import thkoeln.st.st2praktikum.exercise.CustomExceptions.InvalidStringFormatException;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Setter
public class Barrier {
    @Embedded
    private Point start;
    @Embedded
    private Point end;

    public Boolean isBlocking(Point currentPoint, Point nextPoint) {
        //sorting to half the possible cases
        if (currentPoint.isGreaterThan(nextPoint)) {
            Point temp = currentPoint;
            currentPoint = nextPoint;
            nextPoint = temp;
        }

        if (fetchAxis().equals("horizontal") && isCrossingThePlane(start.getY(), currentPoint.getY(), nextPoint.getY())) {
            return nextPoint.getX() >= start.getX() && nextPoint.getX() < end.getX();
        } else if (fetchAxis().equals("vertical") && isCrossingThePlane(start.getX(), currentPoint.getX(), nextPoint.getX())) {
            return nextPoint.getY() >= start.getY() && nextPoint.getY() < end.getY();
        }

        return false;
    }

    private String fetchAxis() {
        if (start.getY().equals(end.getY())) {
            return "horizontal";
        } else if (start.getX().equals(end.getX())) {
            return "vertical";
        } else {
            throw new DiagonalBarrierException("The barrier " + this.toString() + " is neither vertical nor horizontal.");
        }
    }

    private Boolean isCrossingThePlane(int plane, int current, int next) {
        return current < plane && next == plane;
    }

    private void checkIfBarrierStringIsValid(String barrierString) {
        Pattern pattern = Pattern.compile("^\\([0-9]+,[0-9]+\\)-\\([0-9]+,[0-9]+\\)$");
        Matcher matcher = pattern.matcher(barrierString);
        if (!matcher.matches()) {
            throw new InvalidStringFormatException("\"" + barrierString + "\" is not a valid barrierString in Barrier().");
        }
    }

    public Barrier(Point pos1, Point pos2) {
        //TODO: Creat new exception for this.
        if (pos1.isGreaterThan(pos2)) {
            this.start = pos2;
            this.end = pos1;
        } else {
            this.start = pos1;
            this.end = pos2;
        }
        fetchAxis(); // Throws an exception if the the barrier is neither vertical nor horizontal.
    }

    @Override
    public String toString() {
        return start.toString() + "-" + end.toString();
    }


    /**
     * @param barrierString the barrier in form of a string e.g. (1,2)-(1,4)
     */
    public Barrier(String barrierString) {
        checkIfBarrierStringIsValid(barrierString);

        final StringTokenizer st = new StringTokenizer(barrierString, "-");
        final Point _start = new Point(st.nextToken());
        final Point _end = new Point(st.nextToken());

        if (_start.isGreaterThan(_end)) {
            this.start = _end;
            this.end = _start;
        } else {
            this.start = _start;
            this.end = _end;
        }
        fetchAxis(); // Throws an exception if the the barrier is neither vertical nor horizontal.
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

}
