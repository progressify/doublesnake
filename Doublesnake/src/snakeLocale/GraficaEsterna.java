/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeLocale;

import doublesnake.Names;
import gestioneMappe.SelezionaMappa;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import snake.GraficaSnake;
import snake.Snake;

/**
 *
 * @author pc
 */
public class GraficaEsterna extends GraficaSnake {
    
    
    private Snake snakeEnemy;
     private static final long serialVersionUID = 1L;
    private JLabel labelSfondo;
    private static JLabel labelPunteggio;
    private JButton newGameButton;

    protected Snake snake;

    private JButton pauseButton;

    private Font font;
    
    
    public GraficaEsterna(){
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
 
    
    @Override
      public JLabel createNorthPanel() {
        JLabel labelSfondoPanel = new JLabel();
        labelSfondoPanel.setSize(Names.PANNELLO_HEIGHT, Names.PANNELLO_WIDTH);
        labelSfondoPanel.setIcon(new ImageIcon(Names.PATH_CAMPO_COMETA));
        snake = new snakeEsterno(true);
        snakeEnemy = new snakeEsterno(snake.getMap(), false);
        
        labelSfondoPanel.setLayout(new BorderLayout());
       
        labelSfondoPanel.add(snake, BorderLayout.CENTER);
        labelSfondoPanel.add(snakeEnemy, BorderLayout.CENTER);
        snake.setOpaque(false);
        snakeEnemy.setOpaque(false);
        return labelSfondoPanel;
    }
    
}
