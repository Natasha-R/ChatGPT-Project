package thkoeln.st.st2praktikum.exercise;

import java.util.UUID;

public class Space {
    private Cell[][] space;
    private final int spaceWidth;
    private final int spaceHeight;
    private UUID spaceId;

    public Space(int width, int height) {
        this.spaceId = UUID.randomUUID();
        this.spaceHeight = height;
        this.spaceWidth = width;
        this.space = new Cell[this.spaceHeight][this.spaceWidth];
        for (int i = 0; i < this.spaceWidth; i++) {
            for (int j = 0; j < this.spaceHeight; j++) {
                this.space[i][j] = new Cell();
            }
        }
    }

    /**
     * Method sets walls in a space from a given string
     * @param wall wall
     */
    public void setWall(Wall wall) {
        int startX = wall.getStart().getX();
        int startY = wall.getStart().getY();
        int endX = wall.getEnd().getX();
        int endY = wall.getEnd().getY();

        if ((endX - startX) != 0) {

            for (int i = startX; i < endX; i++) {
                this.space[i][startY].setBorderSouth(true);
                this.space[i][startY - 1].setBorderNorth(true);
            }

        } else if ((endY - startY) != 0) {

            for (int i = startY; i < endY; i++) {
                this.space[startX][i].setBorderWest(true);
                this.space[startX - 1][i].setBorderEast(true);
            }

        }
    }

    public int getSpaceWidth() { return this.spaceWidth; }
    public int getSpaceHeight() { return this.spaceHeight; }
    public UUID getSpaceId() { return this.spaceId; }
    public Cell getSpaceCell(int x, int y) { return this.space[x][y]; }
}
