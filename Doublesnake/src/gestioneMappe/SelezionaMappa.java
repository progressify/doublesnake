package gestioneMappe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tonino
 */
public class SelezionaMappa extends JFrame implements ActionListener {

    private int WINDOWS_WHITH = 700;
    private int WINDOWS_HEIGH = 600;
    private JButton okButton;
    private JButton dxButton;
    private JButton sxButton;
    private JButton annullaButton;
    private JFrame mainWindow;
    private static SelezionaMappa istanzaSelection;

    public static JFrame getIstance(JFrame mainWindow) {
        if (istanzaSelection == null) {
            istanzaSelection = new SelezionaMappa(mainWindow);
        }
        return istanzaSelection;
    }

    private SelezionaMappa(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        setName("DOUBLE SNAKE");
        setTitle("DOUBLE SNAKE");
        setSize(WINDOWS_WHITH, WINDOWS_HEIGH);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("./Grafica/spazio.jpg"));
        label.setLayout(new BorderLayout());
        label.add(createCenterPanel(), BorderLayout.CENTER);
        label.add(createsouthPanel(), BorderLayout.SOUTH);
        label.add(createEastPanel(), BorderLayout.EAST);
        label.add(createWestPanel(), BorderLayout.WEST);
        add(label);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();

//        Griglia grid=new Griglia();
//        panel.add(grid);

        panel.setOpaque(false);
        return panel;
    }

    private JPanel createsouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        okButton = new JButton();
        okButton.setIcon(new ImageIcon("./Grafica/creamappa.png"));
        okButton.setPressedIcon(new ImageIcon("./Grafica/creamappa.png"));
        okButton.setContentAreaFilled(false);
        okButton.setBorder(null);
        okButton.setToolTipText("Memorizza la mappa appena creata");
        okButton.addActionListener(this);

        JLabel spazio = new JLabel("      ");
        annullaButton = new JButton();
        annullaButton.setIcon(new ImageIcon("./Grafica/multigiocatore.png"));
        annullaButton.setPressedIcon(new ImageIcon("./Grafica/retelocale.png"));
        annullaButton.setToolTipText("Indietro");
        annullaButton.setContentAreaFilled(false);
        annullaButton.setBorder(null);
        annullaButton.addActionListener(this);
        
        JPanel paneltemp3 = new JPanel();
        paneltemp3.setOpaque(false);
        paneltemp3.add(okButton);
        paneltemp3.add(spazio);
        paneltemp3.add(annullaButton);
        panel.add(paneltemp3, BorderLayout.SOUTH);
        panel.setOpaque(false);
        return panel;
    }

    private JPanel createEastPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        dxButton = new JButton();
        dxButton.setIcon(new ImageIcon("./Grafica/frecciabludx.png"));
        dxButton.setPressedIcon(new ImageIcon("./Grafica/frecciabludx.png"));
        dxButton.setToolTipText("Mappa successiva");
        dxButton.setContentAreaFilled(false);
        dxButton.setBorder(null);
        dxButton.addActionListener(this);

        panel.add(dxButton);
        panel.setOpaque(false);
        return panel;
    }

    private JPanel createWestPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        sxButton = new JButton();
        sxButton.setIcon(new ImageIcon("./Grafica/frecciaverdesx.png"));
        sxButton.setPressedIcon(new ImageIcon("./Grafica/frecciaverdesx.png"));
        sxButton.setToolTipText("Mappa precedente");
        sxButton.setContentAreaFilled(false);
        sxButton.setBorder(null);
        sxButton.addActionListener(this);

        panel.add(sxButton);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(EditorMappe.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (source == okButton) {
            //TODO
        }
        if (source == annullaButton) {
            setVisible(false);
            mainWindow.setVisible(true);
        }
        if (source == dxButton) {
            //TODO
        }
        if (source == sxButton) {
            //TODO
        }
    }
}
