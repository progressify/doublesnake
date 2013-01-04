package snake;

import doublesnake.Names;
import javax.swing.JFrame;

public class GraficaSnake extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GraficaSnake() {
        Snake snake=new Snake();
        add(snake);
        snake.run();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Names.WINDOWS_WHITH, Names.WINDOWS_HEIGH);
        
        setLocationRelativeTo(null);
        setTitle("Snake");

        setResizable(false);
        setVisible(true);
    }
    
}
