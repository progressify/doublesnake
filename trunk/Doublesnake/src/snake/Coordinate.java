package snake;

import java.io.Serializable;

public class Coordinate implements Serializable {

    private int x;
    private int y;

    public Coordinate() {
    }

    public Coordinate(int X, int Y) {
        x = X;
        y = Y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        Coordinate other = (Coordinate) otherObject;
        return x == other.getX() && y == other.getY();
    }
}
