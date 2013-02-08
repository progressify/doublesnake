/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeMulti;

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
    
    public ConnectionClient(Snake snake, String addr) {
        
        int i = 0;
        
        try {
            client = new Socket(addr, 4444);
            doCommsz connection;     
            doCommsz conn_c = new doCommsz(client, snake);
            Thread t = new Thread(conn_c);
            t.start();
        } catch (IOException ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }
    
    public void writeDirection(int dir) throws IOException{
    PrintStream out = new PrintStream(client.getOutputStream());    
    out.println(dir);   
    }
    
    
}

class doCommsz implements Runnable {
    private Socket client;
    private String line,input;
    private Snake snake;

    doCommsz(Socket server, Snake snake) {
      this.client=server;
      this.snake=snake;
    }

    public void run () {
        
      input="";

      try {
        // Get input from the client
        DataInputStream in = new DataInputStream (client.getInputStream());
        PrintStream out = new PrintStream(client.getOutputStream());
        
        
        
        
        while((line = in.readUTF()) != null && !line.equals(".")) {
           int key = Integer.parseInt(line);
           
            
           if ((key == KeyEvent.VK_A) && (!snake.getLastDirection().isRight()) && snake.getTimer().isRunning()) {
                Snake.Directions dir = new Snake.Directions(false, false, true, false);
                snake.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_D) && (!snake.getLastDirection().isLeft()) && snake.getTimer().isRunning()) {
                Snake.Directions dir = new Snake.Directions(false, false, false, true);
                snake.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_W) && (!snake.getLastDirection().isDown()) && snake.getTimer().isRunning()) {
                Snake.Directions dir = new Snake.Directions(true, false, false, false);
                snake.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_S) && (!snake.getLastDirection().isUp()) && snake.getTimer().isRunning()) {
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
}
