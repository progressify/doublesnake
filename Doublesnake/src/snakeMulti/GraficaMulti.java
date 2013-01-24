/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeMulti;

/**
 *
 * @author pc
 */

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
import snake.Punteggio;
import snake.Snake;

public class GraficaMulti extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel labelSfondo;
    private static JLabel labelPunteggio;
    private JButton newGameButton;
    private JButton pauseButton;
    private SnakeMulti snake;
    private Font font;

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
        //labelSfondo.add(createPanelSouth(), BorderLayout.SOUTH); //crea solo un JButton ma per chiarezza del codice ho fatto ugualmente un metodo
        add(labelSfondo);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JLabel createNorthPanel() {
        JLabel labelSfondoPanel = new JLabel();
        labelSfondoPanel.setSize(Names.PANNELLO_HEIGHT, Names.PANNELLO_WIDTH);
        labelSfondoPanel.setIcon(new ImageIcon(Names.PATH_CAMPO_COMETA));
        snake = new SnakeMulti();
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
                Logger.getLogger(snake.GraficaSnake.class.getName()).log(Level.SEVERE, null, ex);
            }
            snake.GraficaSnake graficaSnake = new snake.GraficaSnake();
            graficaSnake.setVisible(true);
        }
        if (source == pauseButton) {
            snake.pauseGame();
        }
    }

    public static void main(String[] args) {
        snake.GraficaSnake prova = new snake.GraficaSnake();
        prova.setVisible(true);
    }
}
