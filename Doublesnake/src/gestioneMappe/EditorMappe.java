package gestioneMappe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    private static EditorMappe istanzaEditor;

    public static JFrame getIstance(JFrame mainWindow) {
        if (istanzaEditor == null) {
            istanzaEditor = new EditorMappe(mainWindow);
        }
        return istanzaEditor;
    }

    private EditorMappe(JFrame mainWindow) {
        this.mainWindow = mainWindow;
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

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("./Grafica/inseriscinomemappa.png"));
        JTextField nomemappa = new JTextField(30);

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
        JPanel paneltemp1 = new JPanel();
        paneltemp1.setOpaque(false);
        paneltemp1.add(label);
        panel.add(paneltemp1, BorderLayout.NORTH);
        JPanel paneltemp2 = new JPanel();
        paneltemp2.setOpaque(false);
        paneltemp2.add(nomemappa);
        panel.add(paneltemp2, BorderLayout.CENTER);
        JPanel paneltemp3 = new JPanel();
        paneltemp3.setOpaque(false);
        paneltemp3.add(okButton);
        paneltemp3.add(spazio);
        paneltemp3.add(annullaButton);
        panel.add(paneltemp3, BorderLayout.SOUTH);
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
    }
}
