/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeMulti;

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
import snake.Apple;
import snake.Coordinate;
import snake.Punteggio;
import snake.Snake;

public class SnakeMulti extends JPanel implements ActionListener, Runnable {

    private static final long serialVersionUID = 1L;
    private int DELAY;
    //primo snake
    private int x[] = new int[Names.ALL_DOTS];
    private int y[] = new int[Names.ALL_DOTS];
    //secondo snake
    private int k[] = new int[Names.ALL_DOTS];
    private int j[] = new int[Names.ALL_DOTS];
    private int dots;
    private int dotsE;
    private boolean inGame = true;
    private Timer timer;
    private Image apple;
    private Hashtable<String, Image> snake;
    private Hashtable<String, Image> snakeEnemy;
    private Thread th;
    private ArrayList<Coordinate> coordMap;
    private ArrayList<Coordinate> coordMapE;
    private Queue<Directions> coda;
    private Queue<Directions> codaEnemy;
    private Directions lastDirection;
    private Directions lastDirectionE;
    private Punteggio punti;
    private Punteggio puntiE;
    private Apple apples;   

    public SnakeMulti() {
        addKeyListener(new TAdapter());

        snake = new Hashtable<String, Image>();
        snakeEnemy = new Hashtable<String, Image>();

        SelezionaMappa sel = (SelezionaMappa) SelezionaMappa.getIstance(new JFrame());
        if (sel.restituisciCoordinateMappa() != null) {
            coordMap = sel.restituisciCoordinateMappa();
            coordMapE = sel.restituisciCoordinateMappa();

        } else {
            coordMap = new ArrayList<Coordinate>();
            coordMapE = new ArrayList<Coordinate>();
        }
        apples = new Apple(coordMap);
        apples.start();


        codaEnemy = new LinkedList<Directions>();
        coda = new LinkedList<Directions>();

        lastDirection = new Directions(false, false, false, true);
        lastDirectionE = new Directions(false, false, false, true);

        imageLoad(snake, false);
        imageLoad(snakeEnemy, true);

        setFocusable(true);
        setDelay();
        this.start();
    }

    public final void setDelay() {
        Opzioni opz = (Opzioni) Opzioni.getIstance(new JFrame());
        DELAY = opz.getLivello();
    }

    public void paint(Graphics g) {
        super.paint(g);
        //drawMattoncini(g);
        if (inGame && !checkCollisionWithMap()) { //per non fargli effettuare il repaint quando incontra il muro
            g.drawImage(apple, apples.getApple_x(), apples.getApple_y(), null);
            int i = 0;
            SnakeMulti.Directions tmp;
            Queue<Directions> coda;
            Directions lastDirection;
            int x[];
            int y[];
            int dots;
            Hashtable<String, Image> snake;
            int z = 0;

            for (i = 0; i < 2; i++) {
                if (i == 0) {
                    tmp = this.lastDirection;
                    coda = this.coda;
                    x = this.x;
                    y = this.y;
                    dots = this.dots;
                    snake = this.snake;
                    z = 0;

                } else {
                    tmp = this.lastDirectionE;
                    coda = this.codaEnemy;
                    x = this.k;
                    y = this.j;
                    dots = this.dotsE;
                    snake = this.snakeEnemy;
                    z = 0;
                }

                boolean flag = false;
                for (z = 0; z < dots - 1; z++) {
                    flag = false;
                    if (z == 0) {
                        //TESTA

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
            }
            g.dispose();
        } else {
            gameOver(g);
        }
    }

    public void drawingSnake(Graphics g, int dots, Directions lastDirection, Queue<Directions> coda, Hashtable<String, Image> snake, int x[], int y[]) {
        int z;
        boolean flag;
        for (z = 0; z < dots - 1; z++) {
            flag = false;
            if (z == 0) {
                //TESTA
                SnakeMulti.Directions tmp = lastDirection;
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

    }

    /**
     * public void drawMattoncini(Graphics g) { if (coordMap != null) { for (int
     * i = 0; i < coordMap.size(); i++) { g.drawImage(snake.get("mattoncino"),
     * (coordMap.get(i).getX()) * 25, (coordMap.get(i).getY()) * 25, this);
     * g.drawImage(snakeEnemy.get("mattoncino"), (coordMap.get(i).getX()) * 25,
     * (coordMap.get(i).getY()) * 25, this); } } }*
     */
    public void gameOver(Graphics g) {
        String msg = "Game Over";
        Font font = Names.caricaFont();
        FontMetrics metr = this.getFontMetrics(font);
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(msg, (Names.LARGHEZZA_PANNELLO - metr.stringWidth(msg)) / 2, Names.ALTEZZA_PANNELLO / 2);
        timer.stop();
        apples.stop();
        //qui si deve passare il punteggio al record!!
    }

    public boolean checkCollisionWithMap() {
        boolean flag = false;
        if (coordMap.contains(new Coordinate((x[0] / 25), (y[0] / 25)))) {
            flag = true;
            inGame = false;

        } else if (coordMapE.contains(new Coordinate((k[0] / 25), (j[0] / 25)))) {
            flag = true;
            inGame = false;
        }

        return flag;
    }

    public void checkCollision() {

        for (int z = dots; z > 0; z--) {
            if ((x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        for (int z = dotsE; z > 0; z--) {
            if ((k[0] == k[z]) && (j[0] == j[z])) {
                inGame = false;
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
            checkCollision();
            
            manageApple(x, y, dots);
            manageApple(k, j, dotsE);
            
        }
        repaint();
    }

    /**
     * Metodo di servizio, creato unicamente al fine di non appesantire la
     * lettura di actionPerformed
     */
    private void manageApple(int x[], int y[], int dots) {
        

        apples.setVariables(x, y, dots);
        synchronized (this.apples) {
            this.apples.notify();
        }
//        lo avevo usato per contollare l'incremento del serpente
//        int tmpDots = dots;
        dots = apples.getDots();
         System.out.println("dots: " + dots + "Dots apples: "+ apples.getDots());
//        if (dots > tmpDots) {
//            System.out.println("dots: " + dots);
//        }
        
    }

    protected static class Directions {

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

    private void imageLoad(Hashtable<String, Image> snake, boolean player) {
        String testagiu, testasu, testadestra, testasinistra, codasu, codagiu, codadestra, codasinistra, corpoorizzontale, corpovertiale, susinistra, sudestra, giusinistra, giudestra;


        if (player) {
            testagiu=Names.PATH_TESTA_GIU2;
            testasu=Names.PATH_TESTA_SU2;
            testadestra=Names.PATH_TESTA_DESTRA2;
            testasinistra=Names.PATH_TESTA_SINISTRA2;
            codasu=Names.PATH_CODA_SU2;
            codagiu=Names.PATH_CODA_GIU2;
            codasinistra=Names.PATH_CODA_SINISTRA2;
            codadestra=Names.PATH_CODA_DESTRA2;
            corpoorizzontale=Names.PATH_MOVIMETNO_ORIZZONTALE2;
            corpovertiale=Names.PATH_MOVIMETNO_VERTICALE2;   
            susinistra=Names.PATH_ALTO_ALTO_SINISTRA2;
            sudestra=Names.PATH_ALTO_ALTO_DESTRA2;
            giusinistra=Names.PATH_ALTO_BASSO_SINISTRA2;
            giudestra=Names.PATH_ALTO_BASSO_DESTRA2;
            
          
        }
        
        else{
            
            
            testagiu=Names.PATH_TESTA_GIU;
            testasu=Names.PATH_TESTA_SU;
            testadestra=Names.PATH_TESTA_DESTRA;
            testasinistra=Names.PATH_TESTA_SINISTRA;
            codasu=Names.PATH_CODA_SU;
            codagiu=Names.PATH_CODA_GIU;
            codasinistra=Names.PATH_CODA_SINISTRA;
            codadestra=Names.PATH_CODA_DESTRA;
            corpoorizzontale=Names.PATH_MOVIMETNO_ORIZZONTALE;
            corpovertiale=Names.PATH_MOVIMETNO_VERTICALE;   
            susinistra=Names.PATH_ALTO_ALTO_SINISTRA;
            sudestra=Names.PATH_ALTO_ALTO_DESTRA;
            giusinistra=Names.PATH_ALTO_BASSO_SINISTRA;
            giudestra=Names.PATH_ALTO_BASSO_DESTRA;
            
        }


        //PALLINO
        ImageIcon iia = new ImageIcon(Names.PATH_MELA);
        apple = iia.getImage();

        //testa su
        ImageIcon idtsu = new ImageIcon(testasu);
        snake.put("tsu", idtsu.getImage());

        //testa giu
        ImageIcon idtg = new ImageIcon(testagiu);
        snake.put("tg", idtg.getImage());

        //testa destra
        ImageIcon idtd = new ImageIcon(testadestra);
        snake.put("td", idtd.getImage());

        //testa sinistra
        ImageIcon idts = new ImageIcon(testasinistra);
        snake.put("ts", idts.getImage());

        //coda su 
        ImageIcon idcsu = new ImageIcon(codasu);
        snake.put("csu", idcsu.getImage());

        //coda giu
        ImageIcon idcg = new ImageIcon(codagiu);
        snake.put("cg", idcg.getImage());

        //coda sinistra
        ImageIcon idcs = new ImageIcon(codasinistra);
        snake.put("cs", idcs.getImage());

        //coda destra
        ImageIcon idcd = new ImageIcon(codadestra);
        snake.put("cd", idcd.getImage());

        //verso alto a sinistra
        ImageIcon idaas = new ImageIcon(susinistra);
        snake.put("aas", idaas.getImage());

        //verso alto a destra
        ImageIcon idaad = new ImageIcon(sudestra);
        snake.put("aad", idaad.getImage());

        //verso basso a sinistra
        ImageIcon idabs = new ImageIcon(giusinistra);
        snake.put("abs", idabs.getImage());

        //verso basso a destra
        ImageIcon idabd = new ImageIcon(giudestra);
        snake.put("abd", idabd.getImage());

        //corpo orizzontale
        ImageIcon idmo = new ImageIcon(corpoorizzontale);
        snake.put("mo", idmo.getImage());

        //corpo verticale
        ImageIcon idmv = new ImageIcon(corpovertiale);
        snake.put("mv", idmv.getImage());

        //mattoncino
        ImageIcon mattoncino = new ImageIcon(Names.PATH_MATTONCINO);
        snake.put("mattoncino", mattoncino.getImage());
    }

    private void start() {
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        initGame();
    }

    private void insertInTheQueue(Directions dir, boolean player) {
        if (player) {
            if (coda.size() < 2) {
                coda.offer(dir);
                lastDirection = dir;
            }
        } else {
            if (codaEnemy.size() < 2) {
                codaEnemy.offer(dir);
                lastDirectionE = dir;
            }

        }
    }

    public void initGame() {
        dots = 2;
        dotsE = 2;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * Names.DOT_SIZE;
            y[z] = 50;

            k[z] = 100 - z * Names.DOT_SIZE;
            j[z] = 100;
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public synchronized void move() {
        moving(dots, x, y, lastDirection, coda);
        moving(dotsE, k, j, lastDirectionE, codaEnemy);
    }

    private void moving(int dots, int x[], int y[], Directions lastDirection, Queue< Directions> coda) {
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

    public void pauseGame() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    protected class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!lastDirection.isRight()) && timer.isRunning()) {
                Directions dir = new Directions(false, false, true, false);
                insertInTheQueue(dir, true);
            }
            if ((key == KeyEvent.VK_RIGHT) && (!lastDirection.isLeft()) && timer.isRunning()) {
                Directions dir = new Directions(false, false, false, true);
                insertInTheQueue(dir, true);
            }
            if ((key == KeyEvent.VK_UP) && (!lastDirection.isDown()) && timer.isRunning()) {
                Directions dir = new Directions(true, false, false, false);
                insertInTheQueue(dir, true);
            }
            if ((key == KeyEvent.VK_DOWN) && (!lastDirection.isUp()) && timer.isRunning()) {
                Directions dir = new Directions(false, true, false, false);
                insertInTheQueue(dir, true);
            }

            //key second player

            if ((key == KeyEvent.VK_A) && (!lastDirectionE.isRight()) && timer.isRunning()) {
                Directions dir = new Directions(false, false, true, false);
                insertInTheQueue(dir, false);
            }
            if ((key == KeyEvent.VK_D) && (!lastDirectionE.isLeft()) && timer.isRunning()) {
                Directions dir = new Directions(false, false, false, true);
                insertInTheQueue(dir, false);
            }
            if ((key == KeyEvent.VK_W) && (!lastDirectionE.isDown()) && timer.isRunning()) {
                Directions dir = new Directions(true, false, false, false);
                insertInTheQueue(dir, false);
            }
            if ((key == KeyEvent.VK_S) && (!lastDirectionE.isUp()) && timer.isRunning()) {
                Directions dir = new Directions(false, true, false, false);
                insertInTheQueue(dir, false);
            }


            if ((key == KeyEvent.VK_SPACE)) {
                pauseGame();
            }
        }
    }
}
