package snake;

import doublesnake.Names;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraficaSnake extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel labelSfondo;

    public GraficaSnake() {

        setName(Names.NOME_FRAME_CREAMAPPA);
        setTitle(Names.NOME_FRAME_CREAMAPPA);
        setSize(Names.WINDOWS_WHITH, Names.WINDOWS_HEIGH);
        labelSfondo = new JLabel();
        labelSfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));
        labelSfondo.setLayout(new BorderLayout());
        labelSfondo.add(createCenterPanel(), BorderLayout.CENTER);
        add(labelSfondo);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createCenterPanel() {
        Snake snake = new Snake();
        add(snake);
        snake.start();
        snake.setOpaque(false);
        return snake;
    }
}
