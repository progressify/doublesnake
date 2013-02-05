package snakeMulti;

/**
 *
 * @author pc
 */
import doublesnake.Names;
import gestioneMappe.SelezionaMappa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import menu.Opzioni;
import snake.Apple;
import snake.Coordinate;
import snake.Punteggio;
import snake.Snake;

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
        //labelSfondo.add(createCenterPanel(), BorderLayout.CENTER);
        //labelSfondo.add(createPanelSouth(), BorderLayout.SOUTH); //crea solo un JButton ma per chiarezza del codice ho fatto ugualmente un metodo
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
        snake = new Snake(true, true, mela, coordMap);
        snake2 = new Snake(false, true, mela, coordMap);

        labelSfondoPanel.setLayout(new BorderLayout());
        labelSfondoPanel.add(snake, BorderLayout.CENTER);
        labelSfondoPanel.add(snake2, BorderLayout.CENTER);
        snake.setOpaque(false);
        snake2.setOpaque(false);
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
}
