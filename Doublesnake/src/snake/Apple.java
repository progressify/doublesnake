package snake;

import doublesnake.Names;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Apple implements Runnable {

    private int apple_x, apple_y, dots = 2;
    private int[] bodyX = new int[Names.ALL_DOTS], bodyY = new int[Names.ALL_DOTS]; //coordinate della testa dello snake
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
        while (true) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Apple.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            checkApple();
        }
    }

    public void start() {
        th = new Thread(this);
        th.start();
    }

    public void stop() {
        th.interrupt();
    }

    public synchronized void setVariables(int[] aBodyX, int[] aBodyY) {
        bodyX = aBodyX;
        bodyY = aBodyY;
    }

    /**
     * Posiziona la prima mela
     */
    private void locateFirstApple() {
        do {
            int r = (int) (Math.random() * (Names.NUMERO_COLONNE - 1));
            apple_x = ((r * Names.DOT_SIZE));
            r = (int) (Math.random() * (Names.NUMERO_RIGHE - 1));
            apple_y = ((r * Names.DOT_SIZE));
        } while (checkCollisionWithMap());
    }

    /**
     * Genera le coordinate di una nuova mela
     */
    public void locateApple() {
        do {
            int r = (int) (Math.random() * (Names.NUMERO_COLONNE - 1));
            apple_x = ((r * Names.DOT_SIZE));
            r = (int) (Math.random() * (Names.NUMERO_RIGHE - 1));
            apple_y = ((r * Names.DOT_SIZE));
        } while (controlApple() || checkCollisionWithMap());
    }

    public boolean checkCollisionWithMap() {
        if (coordMap.contains(new Coordinate((apple_x / 25), (apple_y / 25)))) {
            return true;
        }
        return false;
    }

    /**
     * Controlla se mela viene mangiata
     */
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

    public synchronized int getApple_x() {
        return apple_x;
    }

    public synchronized int getApple_y() {
        return apple_y;
    }

    public synchronized int getDots() {
        return dots;
    }
}
