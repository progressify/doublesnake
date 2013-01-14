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

    public void setDelay() {
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
            g.drawImage(apple, apple_x, apple_y, this);
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

                    if (tmp.isLeft()) {
                        g.drawImage(snake.get("ts"), x[z], y[z], this);
                    }
                    if (tmp.isUp()) {
                        g.drawImage(snake.get("tsu"), x[z], y[z], this);
                    }
                    if (tmp.isRight()) {
                        g.drawImage(snake.get("td"), x[z], y[z], this);
                    }
                    if (tmp.isDown()) {
                        g.drawImage(snake.get("tg"), x[z], y[z], this);
                    }
                } else {
                    //ANGOLI
                    if (((y[z + 1] == y[z]) && (y[z] < y[z - 1]) && (x[z + 1] < x[z]) && (x[z] == x[z - 1])) || ((y[z + 1] > y[z]) && (y[z] == y[z - 1]) && (x[z + 1] == x[z]) && (x[z] > x[z - 1]))) {
                        g.drawImage(snake.get("aad"), x[z], y[z], this);
                        flag = true;
                    }
                    if (((y[z + 1] == y[z]) && (y[z] < y[z - 1]) && (x[z + 1] > x[z]) && (x[z] == x[z - 1])) || ((y[z + 1] > y[z]) && (y[z] == y[z - 1]) && (x[z + 1] == x[z]) && (x[z] < x[z - 1]))) {
                        g.drawImage(snake.get("aas"), x[z], y[z], this);
                        flag = true;
                    }
                    if (((y[z + 1] < y[z]) && (y[z] == y[z - 1]) && (x[z + 1] == x[z]) && (x[z] > x[z - 1])) || ((y[z + 1] == y[z]) && (y[z] > y[z - 1]) && (x[z + 1] < x[z]) && (x[z] == x[z - 1]))) {
                        g.drawImage(snake.get("abd"), x[z], y[z], this);
                        flag = true;
                    }
                    if (((y[z + 1] == y[z]) && (y[z] > y[z - 1]) && (x[z + 1] > x[z]) && (x[z] == x[z - 1])) || ((y[z + 1] < y[z]) && (y[z] == y[z - 1]) && (x[z + 1] == x[z]) && (x[z] < x[z - 1]))) {
                        g.drawImage(snake.get("abs"), x[z], y[z], this);
                        flag = true;
                    }
                    //CORPO
                    if (flag == false) {
                        if (y[z] == y[z - 1] && y[z] == y[z + 1]) {
                            g.drawImage(snake.get("mo"), x[z], y[z], this);
                        } else {
                            g.drawImage(snake.get("mv"), x[z], y[z], this);
                        }

                        if (x[z] == x[z - 1] && x[z] == x[z + 1]) {
                            g.drawImage(snake.get("mv"), x[z], y[z], this);
                        } else {
                            g.drawImage(snake.get("mo"), x[z], y[z], this);
                        }
                    }
                }
            }
            //CODA
            if (x[z] > x[z - 1]) {
                g.drawImage(snake.get("cs"), x[z], y[z], this);
            }
            if (y[z] > y[z - 1]) {
                g.drawImage(snake.get("csu"), x[z], y[z], this);
            }
            if (x[z] < x[z - 1]) {
                g.drawImage(snake.get("cd"), x[z], y[z], this);
            }
            if (y[z] < y[z - 1]) {
                g.drawImage(snake.get("cg"), x[z], y[z], this);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        String msg = "Game Over";

        Font small = new Font("Helvetica", Font.BOLD, 14);

        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Names.PANNELLO_WIDTH - metr.stringWidth(msg)) / 2, Names.PANNELLO_HEIGHT / 2);
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
            y[0] = Names.PANNELLO_HEIGHT;
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
            int r = (int) (Math.random() * (Names.NUMERO_RIGHE - 1));
            apple_x = ((r * Names.DOT_SIZE));
            r = (int) (Math.random() * (Names.NUMERO_COLONNE - 1));
            apple_y = ((r * Names.DOT_SIZE));
        } while (controlApple() || checkCollisionWithMap(apple_x, apple_y, "apple"));
    }

    public boolean controlApple() {
        boolean flag = false;
        for (int i = 0; i < x.length; i++) {
            if (x[i] == apple_x && y[i] == apple_y) {
                flag = true;
            }
            return flag;
        }
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    @Override
    public void run() {
        initGame();
    }

    public void start() {       //Creating thread
        th = new Thread(this);
        th.start();
        System.out.println(th.isAlive());
    }

    public void stop() {
        th.interrupt();
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