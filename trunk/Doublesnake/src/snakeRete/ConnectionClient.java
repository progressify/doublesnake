/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeRete;

import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import snake.Snake;

/**
 *
 * @author Windows Seven
 */
public class ConnectionClient {

    private Socket client;
    private doCommsz conn_c ;
    public ConnectionClient(Snake snake, String addr) {

        int i = 0;

        try {
            client = new Socket(addr, 4000);
            conn_c = new doCommsz(client, snake);
            Thread t = new Thread(conn_c);
            t.start();
        } catch (IOException ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }

    public void writeDirection(int dir) throws IOException {
        conn_c.write(dir);
    }
}

class doCommsz implements Runnable {

    private Socket client;
    private String line, input;
    private Snake snake;
    private int di;
    DataInputStream in;
    PrintStream out;

    doCommsz(Socket server, Snake snake) throws IOException {
        this.client = server;
        this.snake = snake;
        in = new DataInputStream(client.getInputStream());
        out = new PrintStream(client.getOutputStream());
    }

    public void run() {

        input = "";

        try {
            // Get input from the client

            while (true) {
                String tmp= in.readLine();
                int key=Integer.parseInt(tmp);
                
                if ((key == KeyEvent.VK_LEFT) && (!snake.getLastDirection().isRight()) && snake.getTimer().isRunning()) {
                    Snake.Directions dir = new Snake.Directions(false, false, true, false);
                    snake.insertInTheQueue(dir);
                }
                if ((key == KeyEvent.VK_RIGHT) && (!snake.getLastDirection().isLeft()) && snake.getTimer().isRunning()) {
                    Snake.Directions dir = new Snake.Directions(false, false, false, true);
                    snake.insertInTheQueue(dir);
                }
                if ((key == KeyEvent.VK_UP) && (!snake.getLastDirection().isDown()) && snake.getTimer().isRunning()) {
                    Snake.Directions dir = new Snake.Directions(true, false, false, false);
                    snake.insertInTheQueue(dir);
                }
                if ((key == KeyEvent.VK_DOWN) && (!snake.getLastDirection().isUp()) && snake.getTimer().isRunning()) {
                    Snake.Directions dir = new Snake.Directions(false, true, false, false);
                    snake.insertInTheQueue(dir);
                }

            }

            // Now write to the client




        } catch (IOException ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }

    public void write(int dir) {
        out.println(dir);
    }
}
