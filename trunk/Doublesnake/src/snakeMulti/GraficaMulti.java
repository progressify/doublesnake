package snakeMulti;

/**
 *
 * @author pc
 */
import doublesnake.Names;
import gestioneMappe.SelezionaMappa;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import menu.Opzioni;
import snake.Apple;
import snake.Coordinate;
import snake.Punteggio;
import snake.Snake;
import snake.Snake.Directions;

public class GraficaMulti extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel labelSfondo;
    private static JLabel labelPunteggio;
    private JButton newGameButton;
    private JButton pauseButton;
    private Snake snake, snake2;
    private Font font;
    private Apple mela;
    private ArrayList<Coordinate> coordMap;

    public GraficaMulti() {
        font = Names.caricaFont();
        setName(Names.NOME_FRAME_GIOCA);
        setTitle(Names.NOME_FRAME_GIOCA);
        setSize(Names.LARGHEZZA_FRAME, Names.ALTEZZA_FRAME);
        labelSfondo = new JLabel();
        labelSfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));
        labelSfondo.setLayout(new BorderLayout());
        labelSfondo.add(createNorthPanel(), BorderLayout.NORTH);
        labelSfondo.add(createCenterPanel(), BorderLayout.CENTER);
        add(labelSfondo);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JLabel createNorthPanel() {
        JLabel labelSfondoPanel = new JLabel();
        labelSfondoPanel.setSize(Names.ALTEZZA_PANNELLO, Names.LARGHEZZA_PANNELLO);
        labelSfondoPanel.setIcon(new ImageIcon(Names.PATH_CAMPO_COMETA));
        labelSfondoPanel.setLayout(new BorderLayout());
        SelezionaMappa sel = (SelezionaMappa) SelezionaMappa.getIstance(new JFrame());
        if (sel.restituisciCoordinateMappa() != null) {
            coordMap = sel.restituisciCoordinateMappa();
        } else {
            coordMap = new ArrayList<Coordinate>();
        }
        mela = new Apple(coordMap);
        mela.start();
        TAdapter listener = new TAdapter();
        snake = new Snake(true, true, mela, coordMap, listener, null);
        snake2 = new Snake(false, true, mela, coordMap, listener, snake);
        snake.setOther(snake2);
        snake.setOpaque(false);
        snake2.setOpaque(false);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(Names.LARGHEZZA_PANNELLO, Names.ALTEZZA_PANNELLO));
        snake.setBounds(0, 0, Names.LARGHEZZA_PANNELLO, Names.ALTEZZA_PANNELLO);
        snake2.setBounds(0, 0, Names.LARGHEZZA_PANNELLO, Names.ALTEZZA_PANNELLO);
        layeredPane.add(snake, 2);
        layeredPane.add(snake2, 3);
        labelSfondoPanel.add(layeredPane, BorderLayout.NORTH);
        return labelSfondoPanel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        Opzioni opz = (Opzioni) Opzioni.getIstance(this);
//Qui devono andare i nomi dei giocatori con i relativi punteggi!
//        JLabel labelNomePlayer = new JLabel(opz.getNomePlayer1());
//        labelNomePlayer.setForeground(Color.WHITE);
//        labelNomePlayer.setFont(font);
//        labelPunteggio = new JLabel("0");
//        labelPunteggio.setForeground(Color.WHITE);
//        labelPunteggio.setFont(font);
//        panel.add(labelNomePlayer, BorderLayout.LINE_START);
//        panel.add(labelPunteggio, BorderLayout.LINE_END);
        panel.add(createSouthPanel(), BorderLayout.SOUTH);
        panel.setOpaque(false);
        return panel;
    }

    public static void aggiornaLabelPunteggio(Punteggio punti) {
        labelPunteggio.setText("" + punti.getPunti());
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel();
        newGameButton = new JButton();
        newGameButton.setFocusable(false);
        newGameButton.setIcon(new ImageIcon(Names.PATH_BUTTON_AGGIORNA));
        newGameButton.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_AGGIORNA));
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorder(null);
        newGameButton.setToolTipText(Names.TOOLTIP_NEWGAMEBUTTON);
        newGameButton.addActionListener(this);
        pauseButton = new JButton();
        pauseButton.setFocusable(false);
        pauseButton.setIcon(new ImageIcon(Names.PATH_BUTTON_PLAY));
        pauseButton.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_PLAY));
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorder(null);
        pauseButton.setToolTipText(Names.TOOLTIP_PAUSA);
        pauseButton.addActionListener(this);
        panel.add(pauseButton);
        panel.add(newGameButton);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == newGameButton) {
            this.setVisible(false);
            try {
                this.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(GraficaMulti.class.getName()).log(Level.SEVERE, null, ex);
            }
            GraficaMulti graficaSnake = new GraficaMulti();
            graficaSnake.setVisible(true);
        }
        if (source == pauseButton) {
            snake.pauseGame();
        }
    }

    public static void main(String[] args) {
        GraficaMulti prova = new GraficaMulti();
        prova.setVisible(true);
        prova.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_SPACE)) {
                snake.pauseGame();
                snake2.pauseGame();
            }
            if ((key == KeyEvent.VK_LEFT) && (!snake.getLastDirection().isRight()) && snake.getTimer().isRunning()) {
                Directions dir = new Directions(false, false, true, false);
                snake.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_RIGHT) && (!snake.getLastDirection().isLeft()) && snake.getTimer().isRunning()) {
                Directions dir = new Directions(false, false, false, true);
                snake.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_UP) && (!snake.getLastDirection().isDown()) && snake.getTimer().isRunning()) {
                Directions dir = new Directions(true, false, false, false);
                snake.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_DOWN) && (!snake.getLastDirection().isUp()) && snake.getTimer().isRunning()) {
                Directions dir = new Directions(false, true, false, false);
                snake.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_A) && (!snake2.getLastDirection().isRight()) && snake2.getTimer().isRunning()) {
                Directions dir = new Directions(false, false, true, false);
                snake2.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_D) && (!snake2.getLastDirection().isLeft()) && snake2.getTimer().isRunning()) {
                Directions dir = new Directions(false, false, false, true);
                snake2.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_W) && (!snake2.getLastDirection().isDown()) && snake2.getTimer().isRunning()) {
                Directions dir = new Directions(true, false, false, false);
                snake2.insertInTheQueue(dir);
            }
            if ((key == KeyEvent.VK_S) && (!snake2.getLastDirection().isUp()) && snake2.getTimer().isRunning()) {
                Directions dir = new Directions(false, true, false, false);
                snake2.insertInTheQueue(dir);
            }
        }
    }
}
