package snake;

import doublesnake.Names;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import menu.Opzioni;

public class GraficaSnake extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel labelSfondo;
    private static JLabel labelPunteggio;
    private JButton newGameButton;
    private Snake snake;
    private Font font;

    public GraficaSnake() {
        font = Names.caricaFont();
        setName(Names.NOME_FRAME_GIOCA);
        setTitle(Names.NOME_FRAME_GIOCA);
        setSize(Names.LARGHEZZA_FRAME, Names.ALTEZZA_FRAME);
        labelSfondo = new JLabel();
        labelSfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));
        labelSfondo.setLayout(new BorderLayout());
        labelSfondo.add(createNorthPanel(), BorderLayout.NORTH);
        labelSfondo.add(createCenterPanel(), BorderLayout.CENTER);
        labelSfondo.add(createPanelSouth(), BorderLayout.SOUTH); //crea solo un JButton ma per chiarezza del codice ho fatto ugualmente un metodo
        add(labelSfondo);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JLabel createNorthPanel() {
        JLabel labelSfondoPanel = new JLabel();
        labelSfondoPanel.setSize(Names.PANNELLO_HEIGHT, Names.PANNELLO_WIDTH);
        labelSfondoPanel.setIcon(new ImageIcon(Names.PATH_CAMPO_COMETA));
        snake = new Snake();
        labelSfondoPanel.setLayout(new BorderLayout());
        labelSfondoPanel.add(snake, BorderLayout.CENTER);
        snake.setOpaque(false);
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
        panel.add(labelNomePlayer, BorderLayout.WEST);
        panel.add(labelPunteggio, BorderLayout.EAST);
        panel.setOpaque(false);
        return panel;
    }

    public static void aggiornaLabelPunteggio(Punteggio punti) {
        labelPunteggio.setText("" + punti.getPunti());
    }

    private JButton createPanelSouth() {
        newGameButton = new JButton();
        newGameButton.setIcon(new ImageIcon(Names.PATH_BUTTON_AGGIORNA));
        newGameButton.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_AGGIORNA));
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorder(null);
        newGameButton.setToolTipText(Names.TOOLTIP_NEWGAMEBUTTON);
        newGameButton.addActionListener(this);
        return newGameButton;
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
            GraficaSnake graficaSnake = new GraficaSnake();
            graficaSnake.setVisible(true);
        }
    }

    public static void main(String[] args) {
        GraficaSnake prova = new GraficaSnake();
        prova.setVisible(true);
    }
}
