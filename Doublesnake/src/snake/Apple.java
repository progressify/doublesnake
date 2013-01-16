package snake;

import doublesnake.Names;
import java.util.ArrayList;

public final class Apple implements Runnable {

    private int apple_x, apple_y, dots = 2;
    private int[] bodyX, bodyY; //coordinate della testa dello snake
    private Thread th;
    private ArrayList<Coordinate> coordMap;
    private Punteggio punti;
    //private Coordinate coordApple;

    public Apple(ArrayList<Coordinate> coordOfTheMap) {
        punti = new Punteggio();
        coordMap = coordOfTheMap;
        locateFirstApple();
    }

    @Override
    public void run() {
        checkApple();
    }

    public void start() {
        th = new Thread(this);
        th.start();
    }

    public void setVariables(int[] aBodyX, int[] aBodyY) {
        bodyX = aBodyX;
        bodyY = aBodyY;
    }

    private void locateFirstApple() {
        do {
            int r = (int) (Math.random() * (Names.NUMERO_COLONNE - 1));
            apple_x = ((r * Names.DOT_SIZE));
            r = (int) (Math.random() * (Names.NUMERO_RIGHE - 1));
            apple_y = ((r * Names.DOT_SIZE));
        } while (checkCollisionWithMap(apple_x, apple_y));
    }

    public void locateApple() {
        do {
            int r = (int) (Math.random() * (Names.NUMERO_COLONNE - 1));
            apple_x = ((r * Names.DOT_SIZE));
            //System.out.println("coord colonna " + r + " " + apple_x);
            r = (int) (Math.random() * (Names.NUMERO_RIGHE - 1));
            apple_y = ((r * Names.DOT_SIZE));
            //System.out.println("coord riga " + r + " " + apple_y);
        } while (controlApple() || checkCollisionWithMap(apple_x, apple_y));
    }

    public boolean checkCollisionWithMap(int xx, int yy) {
        if (coordMap.contains(new Coordinate((xx / 25), (yy / 25)))) {
            System.out.println(true);
            return true;
        }
        return false;
    }

    public synchronized void checkApple() {
        if ((bodyX[0] == apple_x) && (bodyY[0] == apple_y)) {
            dots++;
            punti.aggiungiMela(dots);
            GraficaSnake.aggiornaLabelPunteggio(punti);
            locateApple();
        }
    }

    /**
     * Controlla se la mela viene collocata sotto il corpo del serpente
     *
     * @return true o false a seconda del caso
     */
    public boolean controlApple() {
        for (int i = 0; i < bodyX.length; i++) {
            if (bodyX[i] == apple_x && bodyY[i] == apple_y) {
                return true;
            }
        }
        return false;
    }

    public int getApple_x() {
        return apple_x;
    }

    public int getApple_y() {
        return apple_y;
    }

    public int getDots() {
        return dots;
    }
}
