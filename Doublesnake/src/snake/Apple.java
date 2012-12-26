package snake;

public class Apple extends Thread {

    private final int RAND_POSX = 28;
    private final int RAND_POSY = 24;
    private final int DOT_SIZE = 25;
    private Coordinate coordApple;

    public Apple() {
    }

    public void locateApple() {

        int r = (int) (Math.random() * RAND_POSX);
        coordApple.setX(r * DOT_SIZE);

        r = (int) (Math.random() % RAND_POSY);
        coordApple.setY(r * DOT_SIZE);
    }

    public int getAppleX() {
        return coordApple.getX();
    }

    public int getAppleY() {
        return coordApple.getY();
    }
}
