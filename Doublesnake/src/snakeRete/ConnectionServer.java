/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeRete;

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

  doComms conn_c;
  private static int port=4000, maxConnections=0;
  Snake snake;
  private Socket server;
  // Listen for incoming connections and handle them
  public ConnectionServer(Snake snake) {
    
    this.snake=snake;
    try{
     
        ServerSocket listener = new ServerSocket(port);
        server = listener.accept();
        conn_c= new doComms(server, snake);
        Thread t = new Thread(conn_c);
        t.start();
      
    } catch (IOException ioe) {
      System.out.println("IOException on socket listen: " + ioe);
      ioe.printStackTrace();
    }
  }
   public void writeDirection(int dir) throws IOException{
    conn_c.write(dir);
    }

}
class doComms implements Runnable {
    private Socket server;
    private String line,input;
    private Snake snake;
    DataInputStream in;
    PrintStream out;

    doComms(Socket server, Snake snake) throws IOException {
      this.server=server;
      this.snake=snake;
      in= new DataInputStream(server.getInputStream());
      out= new PrintStream(server.getOutputStream());
    }

    public void run () {
        
      input="";

      try {
   
        while(true) {
           String tmp=in.readLine();
           int key = Integer.parseInt(tmp);
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
    public void write(int dir){
        out.println(dir);
    }
}