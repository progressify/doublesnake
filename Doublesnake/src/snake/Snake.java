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
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import menu.Opzioni;

public class Snake extends JPanel implements ActionListener, Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final int ALL_DOTS = 672;
    private int DELAY;
    private int x[] = new int[ALL_DOTS];
    private int y[] = new int[ALL_DOTS];
    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private Timer timer;
    private Image apple;
    private Hashtable<String, Image> snake;

    public Snake() {

        addKeyListener(new TAdapter());
        snake = new Hashtable<String, Image>();

        //JLabel sfondo=new JLabel();
        //sfondo.setBounds(0, 0, Names.PANNELLO_WIDTH, Names.PANNELLO_HEIGHT);
        //sfondo.setIcon(new ImageIcon(Names.PATH_CAMPO_COMETA));
        //sfondo.setVisible(true);
        //add(sfondo);
        //setBounds(0, 0, Names.PANNELLO_WIDTH, Names.PANNELLO_HEIGHT);

        imageLoad(snake);
        //initGame();
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
        setDelay();
        setFocusable(true);
    }

    public void setDelay() {
        Opzioni opz = (Opzioni) Opzioni.getIstance(new JFrame());
        int temp = opz.getLivello();
        if (temp == 1) {
            DELAY = 300;
        }
        if (temp == 2) {
            DELAY = 250;
        }
        if (temp == 3) {
            DELAY = 200;
        }
        if (temp == 4) {
            DELAY = 125;
        }
        if (temp == 5) {
            DELAY = 50;
        }
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            int z;
            boolean flag;
            for (z = 0; z < dots - 1; z++) {
                flag = false;
                if (z == 0) {
                    //TESTA
                    if (left) {
                        g.drawImage(snake.get("ts"), x[z], y[z], this);
                    }
                    if (up) {
                        g.drawImage(snake.get("tsu"), x[z], y[z], this);
                    }
                    if (right) {
                        g.drawImage(snake.get("td"), x[z], y[z], this);
                    }
                    if (down) {
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

    public void move() {

        for (int z = dots; z > 0; z--) {
            /*if(x[z]==WIDTH)
             x[z]=0;
             if(x[z]==0)
             x[z]=WIDTH;
             if(y[z]==HEIGHT)
             y[z]=0;
             if(y[z]==0)
             y[z]=HEIGHT;*/

            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (left) {
            x[0] -= Names.DOT_SIZE;
        }

        if (right) {
            x[0] += Names.DOT_SIZE;
        }

        if (up) {
            y[0] -= Names.DOT_SIZE;
        }

        if (down) {
            y[0] += Names.DOT_SIZE;
        }
    }

    public void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] > Names.PANNELLO_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 1) {
            inGame = false;
        }

        if (x[0] > Names.PANNELLO_WIDTH) {
            inGame = false;
        }

        if (x[0] < 1) {
            inGame = false;
        }
    }

    public void locateApple() {
        do{
            int r = (int) (Math.random() * Names.NUMERO_RIGHE);
            apple_x = ((r * Names.DOT_SIZE));
            r = (int) (Math.random() * Names.NUMERO_COLONNE);
            apple_y = ((r * Names.DOT_SIZE));
        }while(controlApple());
    }
    
    public boolean controlApple(){
        boolean flag=false;
        for (int i = 0; i < x.length; i++) {
            if(x[i]==apple_x && y[i]==apple_y)
                flag=true;
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
    
    public void start() {
        //Creating thread
        Thread th = new Thread(this);
        th.start();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
                right = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
                left = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                down = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                up = false;
                left = false;
            }
        }
    }
}