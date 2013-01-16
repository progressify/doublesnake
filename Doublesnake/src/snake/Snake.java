package snake;

import doublesnake.Names;
import gestioneMappe.SelezionaMappa;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import menu.Opzioni;

public class Snake extends JPanel implements ActionListener, Runnable {

    private static final long serialVersionUID = 1L;
    private final int ALL_DOTS = 672;
    private int DELAY;
    private int x[] = new int[ALL_DOTS];
    private int y[] = new int[ALL_DOTS];
    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean inGame = true;
    private Timer timer;
    private Image apple;
    private Hashtable<String, Image> snake;
    private Thread th;
    private ArrayList<Coordinate> coordMap;
    private Queue<Directions> coda;
    private Directions lastDirection;

    public Snake() {
        addKeyListener(new TAdapter());
        snake = new Hashtable<String, Image>();
        SelezionaMappa sel = (SelezionaMappa) SelezionaMappa.getIstance(new JFrame());
        if (sel.restituisciCoordinateMappa() != null) {
            coordMap = sel.restituisciCoordinateMappa();
        } else {
            coordMap = new ArrayList<Coordinate>();
        }
        coda = new LinkedList<Directions>();
        lastDirection = new Directions(false, false, false, true);
        imageLoad(snake);
        setFocusable(true);
        setDelay();
        this.start();
    }

    @Override
    public void run() {
        initGame();
    }

    private void start() {       //Creating thread
        th = new Thread(this);
        th.start();
        System.out.println(th.isAlive());
    }

    public void stop() {
        th.interrupt();
    }

    private void imageLoad(Hashtable<String, Image> snake) {
        //PALLINO
        ImageIcon iia = new ImageIcon(Names.PATH_MELA);
        apple = iia.getImage();

        //testa su
        ImageIcon idtsu = new ImageIcon(Names.PATH_TESTA_SU);
        snake.put("tsu", idtsu.getImage());

        //testa giu
        ImageIcon idtg = new ImageIcon(Names.PATH_TESTA_GIU);
        snake.put("tg", idtg.getImage());

        //testa destra
        ImageIcon idtd = new ImageIcon(Names.PATH_TESTA_DESTRA);
        snake.put("td", idtd.getImage());

        //testa sinistra
        ImageIcon idts = new ImageIcon(Names.PATH_TESTA_SINISTRA);
        snake.put("ts", idts.getImage());

        //coda su 
        ImageIcon idcsu = new ImageIcon(Names.PATH_CODA_SU);
        snake.put("csu", idcsu.getImage());

        //coda giu
        ImageIcon idcg = new ImageIcon(Names.PATH_CODA_GIU);
        snake.put("cg", idcg.getImage());

        //coda sinistra
        ImageIcon idcs = new ImageIcon(Names.PATH_CODA_SINISTRA);
        snake.put("cs", idcs.getImage());

        //coda destra
        ImageIcon idcd = new ImageIcon(Names.PATH_CODA_DESTRA);
        snake.put("cd", idcd.getImage());

        //verso alto a sinistra
        ImageIcon idaas = new ImageIcon(Names.PATH_ALTO_ALTO_SINISTRA);
        snake.put("aas", idaas.getImage());

        //verso alto a destra
        ImageIcon idaad = new ImageIcon(Names.PATH_ALTO_ALTO_DESTRA);
        snake.put("aad", idaad.getImage());

        //verso basso a sinistra
        ImageIcon idabs = new ImageIcon(Names.PATH_ALTO_BASSO_SINISTRA);
        snake.put("abs", idabs.getImage());

        //verso basso a destra
        ImageIcon idabd = new ImageIcon(Names.PATH_ALTO_BASSO_DESTRA);
        snake.put("abd", idabd.getImage());

        //corpo orizzontale
        ImageIcon idmo = new ImageIcon(Names.PATH_MOVIMETNO_ORIZZONTALE);
        snake.put("mo", idmo.getImage());

        //corpo verticale
        ImageIcon idmv = new ImageIcon(Names.PATH_MOVIMETNO_VERTICALE);
        snake.put("mv", idmv.getImage());

        //mattoncino
        ImageIcon mattoncino = new ImageIcon(Names.PATH_MATTONCINO);
        snake.put("mattoncino", mattoncino.getImage());
    }

    public final void setDelay() {
        Opzioni opz = (Opzioni) Opzioni.getIstance(new JFrame());
        DELAY = opz.getLivello();
    }

    public void initGame() {
        dots = 2;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * Names.DOT_SIZE;
            y[z] = 50;
        }
        locateApple();
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
        super.paint(g);
        drawMattoncini(g);
        if (inGame && !checkCollisionWithMap(x[0], y[0], "snake")) { //per non fargli effettuare il repaint quando incontra il muro
            g.drawImage(apple, apple_x, apple_y, null);
            int z;
            boolean flag;
            for (z = 0; z < dots - 1; z++) {
                flag = false;
                if (z == 0) {
                    //TESTA
                    Directions tmp = lastDirection;
                    if (coda.size() != 0) {
                        tmp = coda.remove();
                    }
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
            String drawCoda = ""; //ho preferito adottare questa soluzione così sono sicuro che alla fine disegnerà sempre un unica immagine e non immagini sovrapposte
            if (x[z - 1] - x[z] == -25 || (x[z] == 0 && x[z - 1] == Names.PANNELLO_WIDTH)) {
                drawCoda = "cs";
            } else if (x[z] == (Names.PANNELLO_WIDTH - Names.DOT_SIZE) && x[z - 1] == 0 || x[z] < x[z - 1]) {
                drawCoda = "cd";
            }
            if (y[z - 1] - y[z] == -25 || (y[z] == 0 && y[z - 1] == Names.PANNELLO_HEIGHT - Names.DOT_SIZE)) {
                drawCoda = "csu";
            } else if ((y[z - 1] == 0) && (y[z] == (Names.PANNELLO_HEIGHT - Names.DOT_SIZE)) || y[z] < y[z - 1]) {
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
        String msg = "Game Over";
        Font font = Names.caricaFont();
        FontMetrics metr = this.getFontMetrics(font);
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(msg, (Names.PANNELLO_WIDTH - metr.stringWidth(msg)) / 2, Names.PANNELLO_HEIGHT / 2);
        timer.stop();
    }

    public void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    public boolean checkCollisionWithMap(int X, int Y, String chiamante) {
        boolean flag = false;
        if (coordMap.contains(new Coordinate((X / 25), (Y / 25)))) {
            flag = true;
            if (chiamante.equals("snake")) {
                inGame = false;
            }
        }
        return flag;
    }

    public synchronized void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        Directions tmp = lastDirection;
        if (coda.size() != 0) {
            tmp = coda.peek();
        }

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
        if (y[0] >= Names.PANNELLO_HEIGHT) {
            y[0] = 0;
        }
        if (y[0] < 0) {
            y[0] = Names.PANNELLO_HEIGHT - 25;
        }
        if (x[0] >= Names.PANNELLO_WIDTH) {
            x[0] = 0;
        }
        if (x[0] < 0) {
            x[0] = Names.PANNELLO_WIDTH;
        }
    }

    public void checkCollision() {

        for (int z = dots; z > 0; z--) {
            if ((x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }

        }
    }

    public void locateApple() {
        do {
            int r = (int) (Math.random() * (Names.NUMERO_COLONNE - 1));
            apple_x = ((r * Names.DOT_SIZE));
            System.out.println("coord colonna " + r + " " + apple_x);
            r = (int) (Math.random() * (Names.NUMERO_RIGHE - 1));
            apple_y = ((r * Names.DOT_SIZE));
            System.out.println("coord riga " + r + " " + apple_y);
        } while (controlApple() || checkCollisionWithMap(apple_x, apple_y, "apple"));
    }

    /**
     * Controlla se la mela viene collocata sotto il corpo del serpente
     *
     * @return true o false a seconda del caso
     */
    public boolean controlApple() {
        for (int i = 0; i < x.length; i++) {
            if (x[i] == apple_x && y[i] == apple_y) {
                return true;
            }
//            if (apple_y == Names.PANNELLO_HEIGHT) { //condizione forse non necessaria, testo ancora il gioco e vedrò se è il caso di cancellarla definitivamente
//                flag = true;
//            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            move();
            checkCollision();
        }
        repaint();
    }

    private class Directions {

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
     * questo serve per evitare che
     *
     * @param dir direzione inserita
     */
    private synchronized void insertInTheQueue(Directions dir) {
        if (coda.size() < 2) {
            coda.offer(dir);
            lastDirection = dir;
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!lastDirection.isRight())) {
                Directions dir = new Directions(false, false, true, false);
                insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_RIGHT) && (!lastDirection.isLeft())) {
                Directions dir = new Directions(false, false, false, true);
                insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_UP) && (!lastDirection.isDown())) {
                Directions dir = new Directions(true, false, false, false);
                insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_DOWN) && (!lastDirection.isUp())) {
                Directions dir = new Directions(false, true, false, false);
                insertInTheQueue(dir);
            }
        }
    }
}