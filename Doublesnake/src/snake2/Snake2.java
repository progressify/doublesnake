package snake2;

import javax.swing.JFrame;

public class Snake2 extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Snake2() {

        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 340);
        setLocationRelativeTo(null);
        setTitle("Snake");

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Snake2 snake = new Snake2();
    }
}