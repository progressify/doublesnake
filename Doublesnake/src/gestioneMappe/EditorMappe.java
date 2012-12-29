/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneMappe;

import java.awt.BorderLayout;
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

    public EditorMappe() {
        setName("DOUBLE SNAKE");
        setTitle("DOUBLE SNAKE");
        setSize(WINDOWS_WHITH, WINDOWS_HEIGH);
        //setLayout(new BorderLayout());
//        JPanel centerPanel = createCenterPanel();
//        add(centerPanel,BorderLayout.CENTER);
        JPanel southPanel = createsouthPanel();
        add(southPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        repaint();
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        JLabel sfondo = new JLabel();
        sfondo.setBounds(0, 0, WINDOWS_WHITH, WINDOWS_HEIGH);
        sfondo.setIcon(new ImageIcon("./Grafica/spazio.jpg"));
        panel.add(sfondo);
        return panel;
    }

    private JPanel createsouthPanel() {
        JPanel panel = new JPanel();
        okButton = new JButton();
        okButton.setContentAreaFilled(false);
        okButton.setBorder(null);
        okButton.setFocusPainted(false);
        okButton.setIcon(new ImageIcon("./Grafica/multigiocatore.png"));
        okButton.setPressedIcon(new ImageIcon("./Grafica/multigiocatore.png"));
        okButton.addActionListener(this);
//        okButton.setBorderPainted(false);
//        okButton.setContentAreaFilled(false);
//        okButton.setOpaque(true);
        panel.add(okButton);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == okButton) {
            //TODO
        }
    }
}
