/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeLocale;

import doublesnake.Names;
import gestioneMappe.SelezionaMappa;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.Timer;
import snake.Apple;
import snake.Coordinate;
import snake.Punteggio;
import snake.Snake;

/**
 *
 * @author pc
 */
public class snakeEsterno extends Snake {
    
     private static final long serialVersionUID = 1L;
    private int DELAY;
    private int x[] = new int[Names.ALL_DOTS];
    private int y[] = new int[Names.ALL_DOTS];
    private int dots;
    private boolean inGame = true;
    private Timer timer;
    private Image apple;
    private Hashtable<String, Image> snake;
    private Thread th;
    private ArrayList<Coordinate> coordMap;
    private Queue<Directions> coda;
    private Directions lastDirection;
    private Punteggio punti;
    private Apple apples;
    
    
     public snakeEsterno(boolean player){
         super();
     }
    
    
    public snakeEsterno(SelezionaMappa map, boolean player){
        
        addKeyListener(new snakeEsterno.TAdapter());
        snake = new Hashtable<String, Image>();
        SelezionaMappa sel = map;
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
    
    
      private void start() {
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        initGame();
    }
    
    
     protected class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_A) && (!lastDirection.isRight()) && timer.isRunning()) {
                Directions dir = new Directions(false, false, true, false);
                insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_D) && (!lastDirection.isLeft()) && timer.isRunning()) {
                Directions dir = new Directions(false, false, false, true);
                insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_W) && (!lastDirection.isDown()) && timer.isRunning()) {
                Directions dir = new Directions(true, false, false, false);
                insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_S) && (!lastDirection.isUp()) && timer.isRunning()) {
                Directions dir = new Directions(false, true, false, false);
                insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_SPACE)) {
                pauseGame();
            }
        }
    }
    
}
