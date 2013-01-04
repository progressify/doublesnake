package Internetsnake2;

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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class Board extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    private final int DOT_SIZE = 25;
    private final int ALL_DOTS = 900;
    private final int RAND_POSX = 20;
    private final int RAND_POSY = 12;
    private final int DELAY = 200;
    private int x[] = new int[ALL_DOTS];
    private int y[] = new int[ALL_DOTS];
    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean left =false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private Timer timer;
    private Image apple;
    private Hashtable<String, Image> snake1;

    public Board() {
        addKeyListener(new TAdapter());
        snake1=new Hashtable<String, Image>();
        
        JLabel sfondo=new JLabel();
        sfondo.setBounds(0, 0, WIDTH, HEIGHT);
        sfondo.setIcon(new ImageIcon("./Grafica/stella.jpg"));
        sfondo.setVisible(true);
        add(sfondo);
        
        setBounds(0, 0, WIDTH, HEIGHT);
        
        
        imageLoad(snake1);
        initGame();
    }
    
    private void imageLoad(Hashtable<String, Image> snake) {
        //PALLINO
        ImageIcon iia = new ImageIcon(this.getClass().getResource("pallino.png"));
        apple = iia.getImage();
        
        //testa su
        ImageIcon idtsu = new ImageIcon(this.getClass().getResource("snake/tsu.png"));
        snake.put("tsu", idtsu.getImage());

        //testa giu
        ImageIcon idtg = new ImageIcon(this.getClass().getResource("snake/tg.png"));
        snake.put("tg", idtg.getImage());

        //testa destra
        ImageIcon idtd = new ImageIcon(this.getClass().getResource("snake/td.png"));
        snake.put("td", idtd.getImage());

        //testa sinistra
        ImageIcon idts = new ImageIcon(this.getClass().getResource("snake/ts.png"));
        snake.put("ts", idts.getImage());

        //coda su 
        ImageIcon idcsu = new ImageIcon(this.getClass().getResource("snake/csu.png"));
        snake.put("csu", idcsu.getImage());

        //coda giu
        ImageIcon idcg = new ImageIcon(this.getClass().getResource("snake/cg.png"));
        snake.put("cg", idcg.getImage());

        //coda sinistra
        ImageIcon idcs = new ImageIcon(this.getClass().getResource("snake/cs.png"));
        snake.put("cs", idcs.getImage());

        //coda destra
        ImageIcon idcd = new ImageIcon(this.getClass().getResource("snake/cd.png"));
        snake.put("cd", idcd.getImage());

        //verso alto a sinistra
        ImageIcon idaas = new ImageIcon(this.getClass().getResource("snake/aas.png"));
        snake.put("aas", idaas.getImage());

        //verso alto a destra
        ImageIcon idaad = new ImageIcon(this.getClass().getResource("snake/aad.png"));
        snake.put("aad", idaad.getImage());

        //verso basso a sinistra
        ImageIcon idabs = new ImageIcon(this.getClass().getResource("snake/abs.png"));
        snake.put("abs", idabs.getImage());

        //verso basso a destra
        ImageIcon idabd = new ImageIcon(this.getClass().getResource("snake/abd.png"));
        snake.put("abd", idabd.getImage());

        //corpo orizzontale
        ImageIcon idmo = new ImageIcon(this.getClass().getResource("snake/mo.png"));
        snake.put("mo", idmo.getImage());

        //corpo verticale
        ImageIcon idmv = new ImageIcon(this.getClass().getResource("snake/mv.png"));
        snake.put("mv", idmv.getImage());

        setFocusable(true);
    }

    public void initGame() {
        dots = 2;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * DOT_SIZE;
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
            for (z = 0; z < dots-1; z++){
                flag=false;
                if (z == 0) {
                    //TESTA
                    if(left){
                        g.drawImage(snake1.get("ts"), x[z], y[z], this);
                    }
                    if(up){
                        g.drawImage(snake1.get("tsu"), x[z], y[z], this);
                    }
                    if(right){
                        g.drawImage(snake1.get("td"), x[z], y[z], this);
                    }
                    if(down){
                        g.drawImage(snake1.get("tg"), x[z], y[z], this);
                    }
                } else{
                    //ANGOLI
                    if( ( (y[z+1]==y[z]) && (y[z]<y[z-1]) && (x[z+1]<x[z]) && (x[z]==x[z-1]) ) || ( (y[z+1]>y[z]) && (y[z]==y[z-1]) && (x[z+1]==x[z]) && (x[z]>x[z-1]) )){
                        g.drawImage(snake1.get("aad"), x[z], y[z], this);
                        flag=true;
                    }
                    if(( (y[z+1]==y[z]) && (y[z]<y[z-1]) && (x[z+1]>x[z]) && (x[z]==x[z-1]) ) || ( (y[z+1]>y[z]) && (y[z]==y[z-1]) && (x[z+1]==x[z]) && (x[z]<x[z-1]) ) ){
                        g.drawImage(snake1.get("aas"), x[z], y[z], this);
                        flag=true;
                    }
                    if(( (y[z+1]<y[z]) && (y[z]==y[z-1]) && (x[z+1]==x[z]) && (x[z]>x[z-1]) ) || ( (y[z+1]==y[z]) && (y[z]>y[z-1]) && (x[z+1]<x[z]) && (x[z]==x[z-1]) ) ){
                        g.drawImage(snake1.get("abd"), x[z], y[z], this);
                        flag=true;
                    }
                    if(( (y[z+1]==y[z]) && (y[z]>y[z-1]) && (x[z+1]>x[z]) && (x[z]==x[z-1]) ) || ( (y[z+1]<y[z]) && (y[z]==y[z-1]) && (x[z+1]==x[z]) && (x[z]<x[z-1]) ) ){
                        g.drawImage(snake1.get("abs"), x[z], y[z], this); 
                        flag=true;
                    }
                     //CORPO
                    if(flag==false){
                        if(y[z]==y[z-1] && y[z]==y[z+1]) {                
                            g.drawImage(snake1.get("mo"), x[z], y[z], this);
                        }
                        else {
                            g.drawImage(snake1.get("mv"), x[z], y[z], this);
                        } 

                        if(x[z]==x[z-1] && x[z]==x[z+1]) {
                            g.drawImage(snake1.get("mv"), x[z], y[z], this);
                        }
                        else {
                            g.drawImage(snake1.get("mo"), x[z], y[z], this);
                        }
                    }
                }
            } 
            //CODA
            if(x[z]>x[z-1]){
                g.drawImage(snake1.get("cs"), x[z], y[z], this);
            }
            if(y[z]>y[z-1]){
                g.drawImage(snake1.get("csu"), x[z], y[z], this);
            }
            if(x[z]<x[z-1]){
                g.drawImage(snake1.get("cd"), x[z], y[z], this);
            }
            if(y[z]<y[z-1]){
                g.drawImage(snake1.get("cg"), x[z], y[z], this);
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
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
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
            x[0] -= DOT_SIZE;
        }

        if (right) {
            x[0] += DOT_SIZE;
        }

        if (up) {
            y[0] -= DOT_SIZE;
        }

        if (down) {
            y[0] += DOT_SIZE;
        }
    }

    public void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] > HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] > WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
    }

    public void locateApple() {
        int r = (int) (Math.random() * RAND_POSX);
        apple_x = ((r * DOT_SIZE));
        r = (int) (Math.random() * RAND_POSY);
        apple_y = ((r * DOT_SIZE));
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

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up=false;
                down=false;
                right=false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up=false;
                down=false;
                left=false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right=false;
                down=false;
                left=false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right=false;
                up=false;
                left=false;
            }
        }
    }
}