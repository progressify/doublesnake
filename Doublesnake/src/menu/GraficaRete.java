package menu;

import doublesnake.Names;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author tonino
 */
public class GraficaRete extends JFrame implements ActionListener {

    private JFrame mainWindow;
    private static GraficaRete istanzaGraficaRete;
    private JLabel labelSfondo;
    private JButton host;
    private JButton client;
    private String ipClient;

    public static JFrame getIstance(JFrame mainWindow) {
        if (istanzaGraficaRete == null) {
            istanzaGraficaRete = new GraficaRete(mainWindow);
        }
        return istanzaGraficaRete;
    }

    private GraficaRete(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        setName(Names.NOME_FRAME_RETE);
        setTitle(Names.NOME_FRAME_RETE);
        setSize(Names.LARGHEZZA_RETE, Names.ALTEZZA_RETE);
        labelSfondo = new JLabel();
        labelSfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));
        labelSfondo.setLayout(new BorderLayout());
        labelSfondo.add(createCenterPanel(), BorderLayout.CENTER);
        add(labelSfondo);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        host = new JButton();
        host.setIcon(new ImageIcon(Names.PATH_BUTTON_HOST));
        host.setContentAreaFilled(false);
        host.setBorder(null);
        host.setToolTipText(Names.TOOLTIP_HOST);
        host.addActionListener(this);
        
        client = new JButton();
        client.setIcon(new ImageIcon(Names.PATH_BUTTON_CLIENT));
        client.setContentAreaFilled(false);
        client.setBorder(null);
        client.setToolTipText(Names.TOOLTIP_CLIENT);
        client.addActionListener(this);
        
        
        panel.add(host,BorderLayout.NORTH);
        panel.add(client, BorderLayout.SOUTH);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Names.wwait();

        if (source == host) {
            JOptionPane.showMessageDialog(rootPane, "fai visualizzare qui l'ip");
        }
        if (source == client) {
            ipClient=JOptionPane.showInputDialog(rootPane, source);
        }
    }
}
