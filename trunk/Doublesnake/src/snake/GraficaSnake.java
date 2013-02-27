package snake;

import doublesnake.Names;
import gestioneMappe.SelezionaMappa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import menu.Opzioni;
import snake.Snake.Directions;
import menu.WindowAdapterInner;
public class GraficaSnake extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel labelSfondo;
    private static JLabel labelPunteggio;
    private JButton newGameButton;
    private JButton pauseButton;
    private Snake snake;
    private Font font;
    private Apple mela;
    private ArrayList<Coordinate> coordMap;
    private WindowAdapterInner closer;

    public GraficaSnake(WindowAdapterInner closer) {
        font = Names.caricaFont();
        this.closer=closer;
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

        SelezionaMappa sel = (SelezionaMappa) SelezionaMappa.getIstance(new JFrame());
        if (sel.restituisciCoordinateMappa() != null) {
            coordMap = sel.restituisciCoordinateMappa();
        } else {
            coordMap = new ArrayList<Coordinate>();
        }
        mela = new Apple(coordMap);
        mela.start();
        TAdapter listener = new TAdapter();
        snake = new Snake(true, false, mela, coordMap, listener, null);
        snake.setOpaque(false);
        labelSfondoPanel.setLayout(new BorderLayout());
        labelSfondoPanel.add(snake, BorderLayout.CENTER);
        return labelSfondoPanel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        Opzioni opz = (Opzioni) Opzioni.getIstance(this);
        JLabel labelNomePlayer = new JLabel(opz.getNomePlayer1());
        labelNomePlayer.setForeground(Color.WHITE);
        labelNomePlayer.setFont(font);
        labelPunteggio = new JLabel("0");
        labelPunteggio.setForeground(Color.WHITE);
        labelPunteggio.setFont(font);
        panel.add(labelNomePlayer, BorderLayout.LINE_START);
        panel.add(labelPunteggio, BorderLayout.LINE_END);
        panel.add(createSubSouthPanel(), BorderLayout.SOUTH);
        panel.setOpaque(false);
        return panel;
    }

    public static void aggiornaLabelPunteggio(Punteggio punti) {
        labelPunteggio.setText("" + punti.getPunti());
    }

    private JPanel createSubSouthPanel() {
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
                Logger.getLogger(GraficaSnake.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            GraficaSnake graficaSnake = new GraficaSnake(closer);
            graficaSnake.setVisible(true);
            graficaSnake.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            graficaSnake.addWindowListener(closer);
        }
        if (source == pauseButton) {
            snake.pauseGame();
        }
    }

    public static void main(String[] args) {
        GraficaSnake prova = new GraficaSnake(null);
        prova.setVisible(true);
        prova.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_SPACE)) {
                snake.pauseGame();
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
        }
    }
}
