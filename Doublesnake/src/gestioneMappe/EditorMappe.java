package gestioneMappe;

import doublesnake.Names;
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
        setName(Names.NOME_FRAME);
        setTitle(Names.NOME_FRAME);
        setSize(Names.WINDOWS_WHITH, Names.WINDOWS_HEIGH);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Names.PATH_SFONDO));
        label.setLayout(new BorderLayout());
        label.add(createCenterPanel(), BorderLayout.CENTER);
        label.add(createsouthPanel(), BorderLayout.SOUTH);
        add(label);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createCenterPanel() {
        JPanel panel = new Griglia(Names.NUMERO_RIGHE, Names.NUMERO_COLONNE, true);
        panel.setOpaque(false);
        return panel;
    }

    private JPanel createsouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Names.PATH_LABEL_INSERISCINOMEMAPPA));
        JTextField nomemappa = new JTextField(30);

        okButton = new JButton();
        okButton.setIcon(new ImageIcon(Names.PATH_BUTTON_SALVA));
        okButton.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_SALVA));
        okButton.setContentAreaFilled(false);
        okButton.setBorder(null);
        okButton.setToolTipText(Names.TOOLTIP_OKBUTTON_EDITOR);
        okButton.addActionListener(this);

        JLabel spazio = new JLabel(Names.PATH_LABEL_SPAZIO);
        annullaButton = new JButton();
        annullaButton.setIcon(new ImageIcon("./Grafica/multigiocatore.png"));
        annullaButton.setPressedIcon(new ImageIcon("./Grafica/retelocale.png"));
        annullaButton.setToolTipText(Names.TOOLTIP_ANNULLABUTTON);
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
            setVisible(false);
            //TODO
        }
        if (source == annullaButton) {
            setVisible(false);
        }
    }
}
