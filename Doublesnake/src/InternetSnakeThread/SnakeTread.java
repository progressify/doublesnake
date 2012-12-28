package InternetSnakeThread;

import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class SnakeTread extends Applet implements KeyListener, Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //Variables that hold the width and height of the snake, position of the food,
    //space between the snake pieces, width and height of the applet
    int sW, sH, fX, fY, spc, width, height, dirCode;
    //Variable for snake length
    int sLen;
    //Variable for score
    int score;
    //Variables that hold the current position of the snakes head
    int hX = 0;
    int hY = 0;
    //Variables that hold the current position of the snakes tail
    int tX = 0;
    int tY = 0;
    //Variables that keep the movement pace
    int mX = 0;
    int mY = 0;
    //Variable for food existence
    Boolean isF;
    //Variable for food interval
    int fTime, fSleep, fSlept, fNew;
    //String that holds the current direction of the snake 
    //r = right, l = left, u = up, d = down
    String dir;
    //Arrays that hold the direction changes of the snake
    ArrayList xD = new ArrayList();
    ArrayList yD = new ArrayList();
    ArrayList dirC = new ArrayList();
    //Variable that tracks the size of the ArrayLists
    int idx;

    public void init() {
        //Creating dimension object to get the size of the applet
        Dimension d = this.getSize();

        //Getting size of the applet
        this.width = d.width;
        this.height = d.height;

        //Setting up initial snake length
        this.sLen = 5;

        //Setting initial score
        this.score = 0;

        //Space between snake pieces
        this.spc = 1;

        //Setting up snake and food  width and height
        this.sW = 10;
        this.sH = 10;

        //Setting up initial tail position
        this.tX = 50;
        this.tY = 50;

        //Setting up initial head position
        this.hX = this.tX + (this.sLen * this.sW + this.spc) - this.spc;
        this.hY = this.tY;

        //Setting up initial direction
        this.mX = this.sW;
        this.mY = 0;
        this.dir = "r";
        this.dirCode = 2;

        //Getting initial arraylists size
        this.idx = -1;

        //Setting up interval between food appearance
        this.fTime = 11000; //milliseconds
        this.fSleep = 250; //milliseconds - also it is the snake timer - how many milliseconds add to timer for each move
        this.fNew = 5000; //milliseconds
        this.fSlept = 0;

        //Setting up default food status
        isF = true;

        //Setting up initial food position right in the middle of the applet
        this.fX = this.width / 2;
        this.fY = this.height / 2;

        //Adding keylistener
        addKeyListener(this);
    }

    public void start() {
        //Creating thread
        Thread th = new Thread(this);
        th.start();


    }

    public void run() {
        //Focusing on applet
        requestFocusInWindow();
        requestFocus();

        //Generating movement
        while (true) {
            //Moving snake
            moveSnake(this);

            repaint();
            try {
                Thread.sleep(this.fSleep);
                this.fSlept += fSleep;
            } catch (Exception e) {
            }
        }
    }

    public void moveSnake(SnakeTread snk) {
        //Updating snake head position
        snk.hX += snk.mX;
        snk.hY += snk.mY;

        //Updating snake tail position
        snk.tX = snk.hX - (snk.sLen * snk.sW + snk.spc) + snk.spc;
        snk.tY = snk.hY - (snk.sLen * snk.sW + snk.spc) + snk.spc;
    }

    private void incDif(SnakeTread snk) {
        if (snk.score % 3 == 0 && snk.score != 0) {
            snk.fSleep -= 75;
            snk.fNew -= 1000;
        }
    }

    public void paint(Graphics g) {
        //Drawing game border
        drawBorder(g);

        //Drawing food
        drawFood(g);

        //Drawing snake
        drawSnake(g);

        //Drawing game info
        showInfo(g);
        debug(g);
    }

    private void debug(Graphics g) {
        g.drawString(String.valueOf(this.xD.size()), 300, 300);
        g.drawString(String.valueOf(this.yD.size()), 320, 300);
        g.drawString(String.valueOf(this.dirC.size()), 340, 300);
        g.drawString(String.valueOf(this.idx), 360, 300);

        //Printing current positions
        g.drawString("Current head position: " + String.valueOf(this.hX) + " " + String.valueOf(this.hY), 200, 350);
        g.drawString("Current tail position: " + String.valueOf(this.tX) + " " + String.valueOf(this.tY), 200, 370);
        g.drawString("Current food position: " + String.valueOf(this.fX) + " " + String.valueOf(this.fY), 200, 390);

        //Printing last direction change
        if (this.idx >= 0) {
            g.drawString("Last direction change at: " + String.valueOf(this.xD.get(this.idx).toString()) + " " + String.valueOf(this.yD.get(this.idx).toString()), 200, 330);
        }
    }

    private void showInfo(Graphics i) {
        //Printing score
        i.setColor(Color.black);
        i.drawString("Score: " + String.valueOf(this.score), 10, 375);
    }

    private void drawBorder(Graphics b) {
        b.setColor(Color.black);
        b.drawRect(0, 0, this.width - 1, this.height - 50);
    }

    private void drawSnake(Graphics s) {
        //Distance modifier variable - spreads the parts of the snake
        int mod = 0;

        //Coordinates where to draw
        int x = 0;
        int y = 0;

        int j = 0;

        //Temporary stores the current direction vars
        String tempDir = this.dir;
        int tempDirCode = this.dirCode;
        int ohX = this.hX;
        int ohY = this.hY;

        //Drawing the snake from the head towards the tail
        for (int i = 0; i < this.sLen; i++) {
            //Setting color for the head of the snake
            if (i == 0) {
                s.setColor(Color.black);
            } else {
                s.setColor(Color.gray);
            }

            //1 = left
            //2 = right
            //3 = up
            //4 = down
            switch (dirCode) {
                case 1:
                    x = this.hX + mod;
                    y = this.hY;
                    break;

                case 2:
                    x = this.hX - mod;
                    y = this.hY;
                    break;

                case 3:
                    x = this.hX;
                    y = this.hY + mod;
                    break;

                case 4:
                    x = this.hX;
                    y = this.hY - mod;
                    break;
            }

            //Changing direction of the snake
            mod = bendSnake(s, this, x, y, mod, j);
            j++;

            //Drawing snake part
            s.fillRect(x, y, this.sW, this.sH);

            //Incrementing the modifier with the snakes width + the space
            mod += this.spc + this.sW;
        }

        //Updating snake tail position
        this.tX = x;
        this.tY = y;

        //Restoring direction string and direction code
        this.dir = tempDir;
        this.dirCode = tempDirCode;
        this.hX = ohX;
        this.hY = ohY;

        //Destroying bend data
        destroyBendData(this);
    }

    private int bendSnake(Graphics s, SnakeTread snk, int x, int y, int mod, int j) {
        int idx = snk.xD.size();

        for (int i = idx - 1; i >= 0; i--) {
            if (x == Integer.parseInt(snk.xD.get(i).toString()) && y == Integer.parseInt(snk.yD.get(i).toString())) {
                snk.hX = x;
                snk.hY = y;
                mod = 0;
                doBend(snk, i);
            }
        }

        return mod;
    }

    private void doBend(SnakeTread snk, int i) {
        if (snk.dirC.get(i).toString() == "l") {
            snk.dirCode = 1;
        } else if (snk.dirC.get(i).toString() == "r") {
            snk.dirCode = 2;
        } else if (snk.dirC.get(i).toString() == "u") {
            snk.dirCode = 3;
        } else if (snk.dirC.get(i).toString() == "d") {
            snk.dirCode = 4;
        }
    }

    private void destroyBendData(SnakeTread snk) {
        if (snk.xD.size() - 1 >= 0) {
            if (snk.tX == Integer.parseInt(snk.xD.get(0).toString()) && snk.tY == Integer.parseInt(snk.yD.get(0).toString())) {
                //Destroying bend data
                snk.xD.remove(0);
                snk.yD.remove(0);
                snk.dirC.remove(0);
            }
        }
    }

    private void drawFood(Graphics f) {
        //Setting food color
        f.setColor(Color.red);

        if (this.fNew < this.fSlept && this.isF == false) {
            this.isF = true;
            this.fSlept = 0;

            //Randomizing food coordinates
            Random generator = new Random();

            //Require only square numbers
            Boolean ck1 = false, ck2 = false;
            int nr;

            while (ck1 == false) {
                nr = generator.nextInt(this.width - this.sW);
                if (nr % 10 == 0) {
                    this.fX = nr;
                    ck1 = true;
                }
            }

            while (ck2 == false) {
                nr = generator.nextInt(this.height - this.sW - 50);
                if (nr % 10 == 0) {
                    this.fY = nr;
                    ck2 = true;
                }
            }
        }

        //Drawing food only if it does not exist
        if (this.isF == true) {
            f.fillRect(this.fX, this.fY, this.sW, this.sH);
        }

        //Food eaten or took to long
        if (this.hX == this.fX && this.hY == this.fY && this.isF == true) {
            //Remove food
            this.isF = false;

            //Grow snake
            this.sLen++;

            //Incrementing score
            this.score++;

            //Increase difficulty
            incDif(this);

            //Reseting slept
            this.fSlept = 0;
        } else if (this.fTime == this.fSlept) {
            //Remove food
            this.isF = false;

            //Reseting slept
            this.fSlept = 0;
        }
    }

    private void changeDirection(SnakeTread snk) {
        if (snk.dir == "l") {
            //Setting current snake direction parameters
            snk.dirCode = 1;

            //Setting movement direction
            snk.mX = -snk.sW;
            this.mY = 0;
        } else if (snk.dir == "r") {
            //Setting direction parameters
            snk.dirCode = 2;

            //Setting movement direction
            snk.mX = snk.sW;
            snk.mY = 0;
        } else if (snk.dir == "u") {
            //Setting direction parameters
            snk.dirCode = 3;

            //Setting movement direction
            snk.mX = 0;
            snk.mY = -snk.sW;
        } else if (snk.dir == "d") {
            //Setting direction parameters
            snk.dirCode = 4;

            //Setting movement direction
            snk.mX = 0;
            snk.mY = snk.sW;;
        }
    }

    private void getLastDirection(SnakeTread snk) {
        if (snk.dir == "l") {
            snk.dirC.add("r");
        } else if (snk.dir == "r") {
            snk.dirC.add("l");
        } else if (snk.dir == "u") {
            snk.dirC.add("d");
        } else if (snk.dir == "d") {
            snk.dirC.add("u");
        }
    }

    public void keyPressed(KeyEvent e) {
        //Getting the code of the key that was pressed
        int key = e.getKeyCode();

        Boolean ck = false;

        //Setting up snake direction changes vars
        this.xD.add(this.hX);
        this.yD.add(this.hY);

        switch (key) {
            case KeyEvent.VK_LEFT:
                if (this.dir != "l") {
                    //Setting direction it came from
                    getLastDirection(this);

                    //Setting current snake direction parameters
                    this.dir = "l";

                    changeDirection(this);

                    ck = true;
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (this.dir != "r") {
                    //Setting direction it came from
                    getLastDirection(this);

                    //Setting current snake direction parameters
                    this.dir = "r";

                    changeDirection(this);

                    ck = true;
                }
                break;

            case KeyEvent.VK_UP:
                if (this.dir != "u") {
                    //Setting direction it came from
                    getLastDirection(this);

                    //Setting current snake direction parameters
                    this.dir = "u";

                    changeDirection(this);

                    ck = true;
                }
                break;

            case KeyEvent.VK_DOWN:
                if (this.dir != "d") {
                    //Setting direction it came from
                    getLastDirection(this);

                    //Setting current snake direction parameters
                    this.dir = "d";

                    changeDirection(this);

                    ck = true;
                }
                break;
        }

        //Recalculating arraylists index
        if (ck == true) {
            this.idx++;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}