/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeMulti;

import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import snake.Snake;


/**
 *
 * @author Windows Seven
 */
public class ConnectionServer {

  private static int port=4000, maxConnections=0;
  Snake snake;
  private Socket server;
  // Listen for incoming connections and handle them
  public ConnectionServer(Snake snake) {
    int i=0;
    this.snake=snake;
    try{
      ServerSocket listener = new ServerSocket(port);

      while((i++ < maxConnections) || (maxConnections == 0)){
        doComms connection;

        server = listener.accept();
        doComms conn_c= new doComms(server, snake);
        Thread t = new Thread(conn_c);
        t.start();
      }
    } catch (IOException ioe) {
      System.out.println("IOException on socket listen: " + ioe);
      ioe.printStackTrace();
    }
  }
   public void writeDirection(int dir) throws IOException{
    PrintStream out = new PrintStream(server.getOutputStream());    
    out.println(dir);   
    }

}
class doComms implements Runnable {
    private Socket server;
    private String line,input;
    private Snake snake;

    doComms(Socket server, Snake snake) {
      this.server=server;
      this.snake=snake;
    }

    public void run () {
        
      input="";

      try {
        // Get input from the client
        DataInputStream in = new DataInputStream (server.getInputStream());
        PrintStream out = new PrintStream(server.getOutputStream());
        
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