package snake;

import doublesnake.Names;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import menu.Opzioni;

public class Snake extends JPanel implements ActionListener, Runnable {

    private static final long serialVersionUID = 1L;
    private int DELAY;
    private int x[] = new int[Names.ALL_DOTS];
    private int y[] = new int[Names.ALL_DOTS];
    private int dots;
    private boolean inGame = true;
    private Timer timer;
    private Hashtable<String, Image> snake;
    private Thread th;
    private ArrayList<Coordinate> coordMap;
    private Queue<Directions> coda/*, coda2*/;
    private Directions lastDirection/*, lastDirection2 = new Directions(false, false, true, false)*/;
    private Punteggio punti;
    private Apple apples;
    public boolean giocatore, partita;

    public Snake(boolean gioc, boolean part, Apple mela, ArrayList<Coordinate> coordMappa, KeyAdapter kListener) {
        giocatore = gioc;
        partita = part;
//        SelezionaMappa sel = (SelezionaMappa) SelezionaMappa.getIstance(new JFrame());
//        if (sel.restituisciCoordinateMappa() != null) {
//            coordMap = sel.restituisciCoordinateMappa();
//        } else {
//            coordMap = new ArrayList<Coordinate>();
//        }
        coordMap = coordMappa;
        //apples = new Apple(coordMap);
        apples = mela;
        if (giocatore) {
            snake = new Hashtable<String, Image>();
            Names.imageLoad(snake);
            lastDirection = new Directions(false, false, false, true);
//            apples.start();
        } else {
            snake = new Hashtable<String, Image>();
            //lastDirection2 = new Directions(false, false, true, false);
            lastDirection = new Directions(false, false, true, false);
            Names.imageLoad2(snake);
        }

        addKeyListener(kListener);

        coda = new LinkedList<Directions>();
        //coda2 = new LinkedList<Directions>();

        setFocusable(true);
        setDelay();
        punti = new Punteggio();
        this.start();
    }

    private void start() {
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        initGame();
    }

    public final void setDelay() {
        Opzioni opz = (Opzioni) Opzioni.getIstance(new JFrame());
        DELAY = opz.getLivello();
    }

    public void initGame() {
        dots = 2;
        if (giocatore) {
            for (int z = 0; z < dots; z++) {
                x[z] = 50 - z * Names.DOT_SIZE;
                y[z] = 50;
            }
        } else {
            for (int z = 0; z < dots; z++) {
                x[z] = 650 + z * Names.DOT_SIZE;
                y[z] = 450;
            }
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void drawMattoncini(Graphics g) {
        if (coordMap != null) {
            for (int i = 0; i < coordMap.size(); i++) {
                g.drawImage(snake.get("mattoncino"), (coordMap.get(i).getX()) * 25, (coordMap.get(i).getY()) * 25, this);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Directions tmp;
        super.paint(g);
        drawMattoncini(g);
        if (inGame && !checkCollisionWithMap()) { //per non fargli effettuare il repaint quando incontra il muro
            if (!giocatore || !partita) {
                g.drawImage(snake.get("apple"), apples.getApple_x(), apples.getApple_y(), null);
            }
            int z;
            boolean flag;
            for (z = 0; z < dots - 1; z++) {
                flag = false;
                if (z == 0) {
                    //TESTA
                    //if (giocatore) {
                    tmp = lastDirection;
                    if (coda.size() != 0) {
                        tmp = coda.poll();
                    }
//                    } else {
//                        tmp = lastDirection2;
//                        if (coda2.size() != 0) {
//                            tmp = coda2.remove();
//                        }
//                    }
                    String drawTesta = "";
                    if (tmp.isLeft()) {
                        drawTesta = "ts";
                    }
                    if (tmp.isUp()) {
                        drawTesta = "tsu";
                    }
                    if (tmp.isRight()) {
                        drawTesta = "td";
                    }
                    if (tmp.isDown()) {
                        drawTesta = "tg";
                    }
                    g.drawImage(snake.get(drawTesta), x[z], y[z], this);
                } else {
                    //ANGOLI
                    String drawAngoli = "";
                    if (((y[z + 1] == y[z]) && ((y[z] < y[z - 1])) && ((x[z + 1] < x[z])) && (x[z] == x[z - 1])) || (((y[z + 1] > y[z])) && (y[z] == y[z - 1]) && (x[z + 1] == x[z]) && ((x[z] > x[z - 1])))) {
                        drawAngoli = "aad";
                        flag = true;
                    }
                    if (((y[z + 1] == y[z]) && ((y[z] < y[z - 1])) && ((x[z + 1] > x[z])) && (x[z] == x[z - 1])) || (((y[z + 1] > y[z])) && (y[z] == y[z - 1]) && (x[z + 1] == x[z]) && ((x[z] < x[z - 1])))) {
                        drawAngoli = "aas";
                        flag = true;
                    }
                    if ((((y[z + 1] < y[z])) && (y[z] == y[z - 1]) && (x[z + 1] == x[z]) && ((x[z] > x[z - 1]))) || ((y[z + 1] == y[z]) && (y[z] > y[z - 1]) && (x[z + 1] < x[z]) && (x[z] == x[z - 1]))) {
                        drawAngoli = "abd";
                        flag = true;
                    }
                    if (((y[z + 1] == y[z]) && (y[z] > y[z - 1]) && (x[z + 1] > x[z]) && (x[z] == x[z - 1])) || ((y[z + 1] < y[z]) && (y[z] == y[z - 1]) && (x[z + 1] == x[z]) && (x[z] < x[z - 1]))) {
                        drawAngoli = "abs";
                        flag = true;
                    }
                    g.drawImage(snake.get(drawAngoli), x[z], y[z], this);
                    //CORPO
                    if (flag == false) {
                        String drawCorpo = "";
                        if (y[z] == y[z - 1] && y[z] == y[z + 1]) {
                            drawCorpo = "mo";
                        } else {
                            drawCorpo = "mv";
                        }
                        if (x[z] == x[z - 1] && x[z] == x[z + 1]) {
                            drawCorpo = "mv";
                        } else {
                            drawCorpo = "mo";
                        }
                        g.drawImage(snake.get(drawCorpo), x[z], y[z], this);
                    }
                }
            }
            //CODA
            String drawCoda = "";
            if (x[z - 1] - x[z] == -Names.DOT_SIZE || (x[z] == 0 && x[z - 1] == Names.LARGHEZZA_PANNELLO)) {
                drawCoda = "cs";
            } else if (x[z] == (Names.LARGHEZZA_PANNELLO - Names.DOT_SIZE) && x[z - 1] == 0 || x[z] < x[z - 1]) {
                drawCoda = "cd";
            }
            if (y[z - 1] - y[z] == -Names.DOT_SIZE || (y[z] == 0 && y[z - 1] == Names.ALTEZZA_PANNELLO - Names.DOT_SIZE)) {
                drawCoda = "csu";
            } else if ((y[z - 1] == 0) && (y[z] == (Names.ALTEZZA_PANNELLO - Names.DOT_SIZE)) || y[z] < y[z - 1]) {
                drawCoda = "cg";
            }
            g.drawImage(snake.get(drawCoda), x[z], y[z], null);
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } else {
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        String msg;
        Font font = Names.caricaFont();
        FontMetrics metr = this.getFontMetrics(font);
        g.setColor(Color.RED);
        g.setFont(font);
        if (!partita) {
            msg = "Game Over";
        } else {
            msg = "Hai Vinto: " + "Cazzo!"; //scrivere il nome del giocatore che ha vinto(bisogna capire come devo prendermelo)
        }
        g.drawString(msg, (Names.LARGHEZZA_PANNELLO - metr.stringWidth(msg)) / 2, Names.ALTEZZA_PANNELLO / 2);
        timer.stop();
        apples.stop();
        synchronized (this.apples) {
            this.apples.notify();
        }
        //qui si deve passare il punteggio al record!!
    }

    /**
     * Contralla se il serpente si è scontrato con i mattoncini della mappa
     * attualmente selezionata
     *
     * @param X testa del serpente
     * @param Y
     * @return true se si è verificata una collisione false altrimenti
     */
    public boolean checkCollisionWithMap() {
        boolean flag = false;
        if (coordMap.contains(new Coordinate((x[0] / 25), (y[0] / 25)))) {
            flag = true;
            inGame = false;
        }
        return flag;
    }

    /**
     * Muove il serpente, sposta le coordinate negli ArrayList x e y
     */
    public synchronized void move() {
        Directions tmp;
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
//        if (giocatore) {
        tmp = lastDirection;
        if (coda.size() != 0) {
            tmp = coda.peek();
        }
//        } else {
//            tmp = lastDirection2;
//            if (coda2.size() != 0) {
//                tmp = coda2.peek();
//            }
//        }

        if (tmp.isLeft()) {
            x[0] -= Names.DOT_SIZE;
        }
        if (tmp.isRight()) {
            x[0] += Names.DOT_SIZE;
        }
        if (tmp.isUp()) {
            y[0] -= Names.DOT_SIZE;
        }
        if (tmp.isDown()) {
            y[0] += Names.DOT_SIZE;
        }
        //controllo per andare a capo
        if (y[0] >= Names.ALTEZZA_PANNELLO) {
            y[0] = 0;
        }
        if (y[0] < 0) {
            y[0] = Names.ALTEZZA_PANNELLO - Names.DOT_SIZE;
        }
        if (x[0] >= Names.LARGHEZZA_PANNELLO) {
            x[0] = 0;
        }
        if (x[0] < 0) {
            x[0] = Names.LARGHEZZA_PANNELLO - Names.DOT_SIZE;
        }
    }

    /**
     * Per controllare se il serpente si mangia da solo
     */
    public void checkCollision() {

        for (int z = dots; z > 0; z--) {
            if ((x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            manageApple();
            move();
            checkCollision();
        }
        //repaint();
    }

    /**
     * Metodo di servizio, creato unicamente al fine di non appesantire la
     * lettura di actionPerformed
     */
    private void manageApple() {
        if (giocatore) {
            apples.setVariables(x, y, true);
        } else {
            apples.setVariables(x, y, false);
        }
        synchronized (this.apples) {
            this.apples.notify();
        }
        int tmpDots = dots;
        Integer dot;
        if (giocatore) {
            dot = apples.getSerp1();
        } else {
            dot = apples.getSerp2();
        }
        if (dot != null) {
            dots++;
        }
//        dots = apples.getDots();
        if (dots > tmpDots) {
            punti.aggiungiMela(dots);
            if (!partita) {
                GraficaSnake.aggiornaLabelPunteggio(punti);
            }
        }
    }

    /**
     * Classe interna per gestire il movimento (le direzioni) del serpente
     */
    public static class Directions {

        private boolean left = false;
        private boolean right = true;
        private boolean up = false;
        private boolean down = false;

        public Directions() {
        }

        public Directions(boolean up, boolean down, boolean left, boolean right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

        public void setLeft(boolean left) {
            this.left = left;
        }

        public void setRight(boolean right) {
            this.right = right;
        }

        public void setUp(boolean up) {
            this.up = up;
        }

        public void setDown(boolean down) {
            this.down = down;
        }

        public boolean isLeft() {
            return left;
        }

        public boolean isRight() {
            return right;
        }

        public boolean isUp() {
            return up;
        }

        public boolean isDown() {
            return down;
        }
    }

    /**
     * Questo metodo permette di inserire nella coda solo 2 direzioni per volta,
     * questo serve per evitare appunto che vengano passate più di 2 direzioni
     * erroneamente, e quindi il serpente si scontri da solo
     *
     * @param dir direzione inserita
     */
    public synchronized void insertInTheQueue(Directions dir) {
        if (coda.size() < 2) {
            coda.offer(dir);
            lastDirection = dir;
        }
    }

//    public synchronized void insertInTheQueue2(Directions dir) {
//        if (coda2.size() < 2) {
//            coda2.offer(dir);
//            lastDirection2 = dir;
//        }
//    }
    /**
     * Metodo per mettere in pausa / riprendere il gioco
     */
    public void pauseGame() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

//    public Queue<Directions> getCoda() {
//        return coda;
//    }
//    public Queue<Directions> getCoda2() {
//        return coda2;
//    }
    public Directions getLastDirection() {
        return lastDirection;
    }

//    public Directions getLastDirection2() {
//        return lastDirection2;
//    }
    public Timer getTimer() {
        return timer;
    }
    /**
     * Listener per i tasti
     */
//    public class TAdapter extends KeyAdapter {
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            int key = e.getKeyCode();
//            if ((key == KeyEvent.VK_SPACE)) {
//                pauseGame();
//            }
//            if ((key == KeyEvent.VK_LEFT) && (!lastDirection.isRight()) && timer.isRunning()) {
//                Directions dir = new Directions(false, false, true, false);
//                insertInTheQueue(dir);
//            }
//            if ((key == KeyEvent.VK_RIGHT) && (!lastDirection.isLeft()) && timer.isRunning()) {
//                Directions dir = new Directions(false, false, false, true);
//                insertInTheQueue(dir);
//            }
//            if ((key == KeyEvent.VK_UP) && (!lastDirection.isDown()) && timer.isRunning()) {
//                Directions dir = new Directions(true, false, false, false);
//                insertInTheQueue(dir);
//            }
//            if ((key == KeyEvent.VK_DOWN) && (!lastDirection.isUp()) && timer.isRunning()) {
//                Directions dir = new Directions(false, true, false, false);
//                insertInTheQueue(dir);
//            }
//            if ((key == KeyEvent.VK_A) && (!lastDirection2.isRight()) && timer.isRunning()) {
//                Directions dir = new Directions(false, false, true, false);
//                insertInTheQueue2(dir);
//            }
//            if ((key == KeyEvent.VK_D) && (!lastDirection2.isLeft()) && timer.isRunning()) {
//                Directions dir = new Directions(false, false, false, true);
//                insertInTheQueue2(dir);
//            }
//            if ((key == KeyEvent.VK_W) && (!lastDirection2.isDown()) && timer.isRunning()) {
//                Directions dir = new Directions(true, false, false, false);
//                insertInTheQueue2(dir);
//            }
//            if ((key == KeyEvent.VK_S) && (!lastDirection2.isUp()) && timer.isRunning()) {
//                Directions dir = new Directions(false, true, false, false);
//                insertInTheQueue2(dir);
//            }
//        }
//    }
}
