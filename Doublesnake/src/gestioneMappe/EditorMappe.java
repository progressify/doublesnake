/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneMappe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tonino
 */
public class EditorMappe extends JFrame implements ActionListener {

    private int WINDOWS_WHITH = 700;
    private int WINDOWS_HEIGH = 600;
    private JButton okButton;
    private JButton annullaButton;
    private JFrame mainWindow;

    public EditorMappe(JFrame mainWindow) {
        this.mainWindow=mainWindow;
        setName("DOUBLE SNAKE");
        setTitle("DOUBLE SNAKE");
        setSize(WINDOWS_WHITH, WINDOWS_HEIGH);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("./Grafica/spazio.jpg"));
        label.setLayout(new BorderLayout());
        label.add(createCenterPanel(), BorderLayout.CENTER);
        label.add(createsouthPanel(), BorderLayout.SOUTH);
        add(label);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();

        panel.setOpaque(false);
        return panel;
    }

    private JPanel createsouthPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        okButton = new JButton();
        okButton.setIcon(new ImageIcon("./Grafica/giocatoresingolo.png"));
        okButton.setPressedIcon(new ImageIcon("./Grafica/multigiocatore.png"));
        okButton.setContentAreaFilled(false);
        okButton.setBorder(null);
        //devo documentarmi sul significato di questi due metodi XD
        //okButton.setFocusPainted(false);
        //okButton.setBorderPainted(false);
        okButton.addActionListener(this);
        
        JLabel spazio=new JLabel("      ");
        annullaButton = new JButton();
        annullaButton.setIcon(new ImageIcon("./Grafica/multigiocatore.png"));
        annullaButton.setPressedIcon(new ImageIcon("./Grafica/multigiocatore.png"));
        annullaButton.setContentAreaFilled(false);
        annullaButton.setBorder(null);
        annullaButton.addActionListener(this);
        panel.add(okButton);
        panel.add(spazio);
        panel.add(annullaButton);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == okButton) {
            //TODO
        }
        if (source == annullaButton) {
            setVisible(false);
            mainWindow.setVisible(true);
        }
    }
}
