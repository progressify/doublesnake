package snake;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.setX(x);
        this.setY(y);
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
