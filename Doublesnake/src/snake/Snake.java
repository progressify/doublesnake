package snake;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;



public class Snake extends Thread implements ActionListener{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 700;
	private final int HEIGHT = 600;
	private static int SIZE_SNAKE=25; 
	private Coordinate[] coordinate;
	
	public Apple apple;
	
	private int DELAY;
	private int dots;
	
	public boolean right;
	public boolean left;
	public boolean down;
	public boolean up;
	public boolean inGame;
	
	private Timer timer;
    
	
	public Snake(int intervallo) {
		DELAY=intervallo;
	}
	

	public void initGame() {
		dots=2;
		for (int z = 0; z < dots; z++) {
			coordinate[z].setX(50-z*SIZE_SNAKE);
			coordinate[z].setY(50);
		} 
		apple.locateApple();
		
		timer = new Timer(DELAY, this);
	    timer.start();

	}
	
	public void move() {

        for (int z = dots; z > 0; z--) {
            coordinate[z].setX( coordinate[z-1].getX());                           
            coordinate[z].setY( coordinate[z-1].getY());                          
        }

        if (left) {
        	if(coordinate[0].getX() == 0)
        		coordinate[0].setX(WIDTH);
        	else
        		coordinate[0].setX(coordinate[0].getX()-SIZE_SNAKE);            //x[0] -= SIZE_SNAKE;  
        }

        if (right) {
        	if(coordinate[0].getX() == WIDTH)
        		coordinate[0].setX(0);
        	else
        		coordinate[0].setX(coordinate[0].getX()+SIZE_SNAKE);			//x[0] += SIZE_SNAKE;
        }

        if (up) {
        	if(coordinate[0].getY() == 0)
        		coordinate[0].setY(HEIGHT);
        	else
        		coordinate[0].setY(coordinate[0].getY()-SIZE_SNAKE);  			//y[0] -= SIZE_SNAKE;
        }

        if (down) {
        	if(coordinate[0].getY() == HEIGHT)
        		coordinate[0].setY(0);
        	else
        		coordinate[0].setY(coordinate[0].getY()+SIZE_SNAKE);			//y[0] += SIZE_SNAKE;
        }
    }
	
	public void checkCollision() {

		//controllo se si mangia da solo
        for (int z = dots; z > 0; z--) {

            if ((z > 5) && (coordinate[0].getX() == coordinate[z].getX()) && (coordinate[0].getY() == coordinate[z].getY())) {
                inGame = false;
                break;
            } 
        }
	}
	
	public void checkApple(){
		if( coordinate[0].getX() == apple.getAppleX() && coordinate[0].getY() == apple.getAppleY() ){
        	dots++;
        	apple.locateApple();
        }
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
				
	}
	
	private class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!right)) {
				left = true;
				up = false;
				down = false;
			}

			if ((key == KeyEvent.VK_RIGHT) && (!left)) {
				right = true;
				up = false;
				down = false;
			}

			if ((key == KeyEvent.VK_UP) && (!down)) {
				up = true;
				right = false;
				left = false;
			}

			if ((key == KeyEvent.VK_DOWN) && (!up)) {
				down = true;
				right = false;
				left = false;
			}
		}
			
	}

	
}


