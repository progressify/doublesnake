package snakeRete;

import snake.*;
import doublesnake.Names;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;




public final class AppleHost implements Runnable, Appleable {

    private int apple_x, apple_y;
    private int[] bodyX = new int[Names.ALL_DOTS], bodyY = new int[Names.ALL_DOTS]; //coordinate della testa dello snake
    private Thread th, client;
    private ArrayList<Coordinate> coordMap;
    private Queue<CoordinateSnake> logTesta;
    private Queue<Integer> serp1;
    private Queue<Integer> serp2;
    private boolean run = true;
    private Socket sock;

    public AppleHost(ArrayList<Coordinate> coordOfTheMap) throws IOException {

        coordMap = coordOfTheMap;
        logTesta = new LinkedList<CoordinateSnake>();
        serp1 = new LinkedList<Integer>();
        serp2 = new LinkedList<Integer>();
        locateFirstApple();
        sock= new Socket("127.0.0.1",4000);
        client = new Thread();
    }

    @Override
    public void run() {
        while (run) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AppleHost.class.getName()).log(Level.SEVERE, null, ex);
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
        run = false;
    }

    public synchronized void setVariables(int[] aBodyX, int[] aBodyY, boolean serp) {
        bodyX = aBodyX;
        bodyY = aBodyY;
        logTesta.offer(new CoordinateSnake((new Coordinate(bodyX[0], bodyY[0])), serp));
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
        CoordinateSnake tmpCoord;
        if ((tmpCoord = logTesta.poll()) != null) {
            if ((tmpCoord.getCoord().getX() == apple_x) && (tmpCoord.getCoord().getY() == apple_y)) {
                if (tmpCoord.isSerp()) {
                    serp1.offer(0);
                } else {
                    serp2.offer(0);
                }
                locateApple();
            }
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

    public Integer getSerp1() {
        return serp1.poll();
    }

    public Integer getSerp2() {
        return serp2.poll();
    }

    @Override
    public void appleSetVariable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class CoordinateSnake {

        Coordinate coord;
        boolean serp;

        public CoordinateSnake(Coordinate coord, boolean serp) {
            this.coord = coord;
            this.serp = serp;
        }

        public Coordinate getCoord() {
            return coord;
        }

        public synchronized boolean isSerp() {
            return serp;
        }
    }
}
class appleHost implements Runnable {

    private Socket client;
    private String line, input;
    private Snake snake;
    private int di;
    AppleHost appleClient;
    ObjectOutputStream in;

    appleHost(Socket server, AppleHost appleClient) throws IOException {
        this.client = server;
        this.appleClient=appleClient;
        in = new ObjectOutputStream(client.getOutputStream());
    }

    public void run() {

        try {
            while(true){
            wait();
            in.writeObject(appleClient);
            
            }
        
        } catch (Exception ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }

  
}
