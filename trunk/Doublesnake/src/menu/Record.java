package menu;

import doublesnake.Names;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author tonino
 */
public class Record extends JFrame implements ActionListener {

    private static Record istanzaRecord;
    private JFrame mainWindow;
    private JLabel labelSfondo;
    private JButton okButton;
    private Map<String, Integer> punteggi;
    private File flrecord = new File(Names.NOME_FILE_RECORD);
    private JTextArea area;
    private JScrollPane scroll;

    public static JFrame getIstance(JFrame mainWindow) {
        if (istanzaRecord == null) {
            istanzaRecord = new Record(mainWindow);
        }
        return istanzaRecord;
    }

    private Record(JFrame mainWindow) {

        try {
            if (flrecord.exists()) {
                punteggi = deserializzaRecord();
            } else {
                punteggi = new HashMap<String, Integer>();
            }
        } catch (IOException ex) {
            Logger.getLogger(Record.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Record.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mainWindow = mainWindow;
        setName(Names.NOME_FRAME_RECORD);
        setTitle(Names.NOME_FRAME_RECORD);
        setSize(Names.LARGHEZZA_RECORD, Names.ALTEZZA_RECORD);
        labelSfondo = new JLabel();
        labelSfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));
        labelSfondo.setLayout(new BorderLayout());
        labelSfondo.add(createCenterPanel(), BorderLayout.CENTER);
        labelSfondo.add(createSouthPanel(), BorderLayout.SOUTH);
        add(labelSfondo);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void aggiornaPunteggio(String nomeMappa, int punti) { //da rivedere, i record verranno assegnati per nome giocatore non per mappe
        int tmp = punteggi.get(nomeMappa);
        if (tmp < punti) {
            punteggi.put(nomeMappa, tmp);

            try {
                serializzaRecord();
            } catch (IOException ex) {
                Logger.getLogger(Record.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();

        JLabel labelSpazio = new JLabel(new ImageIcon(Names.PATH_LABEL_SPAZIOVUOTO));
        area = new JTextArea(12, 32);
        area.setEditable(false);
        scroll = new JScrollPane(area);
        panel.add(labelSpazio);
        panel.add(scroll);
        panel.setOpaque(false);
        return panel;
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel();

        okButton = new JButton(new ImageIcon(Names.PATH_SFONDO));
        okButton.setIcon(new ImageIcon(Names.PATH_BUTTON_CONFERMA));
        okButton.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_CONFERMA));
        okButton.setContentAreaFilled(false);
        okButton.setBorder(null);
        okButton.setToolTipText(Names.TOOLTIP_OKBUTTON_SELECTOR);
        okButton.addActionListener(this);

        panel.setOpaque(false);
        panel.add(okButton);
        return panel;
    }

    /**
     * Carica dal file nella struttura dati "Map punteggi", tutti i record
     *
     * @return restituisce la struttura dati contenenti tutti record
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Map<String, Integer> deserializzaRecord() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(flrecord));
        Map<String, Integer> temp = (Map<String, Integer>) in.readObject();
        in.close();
        return temp;
    }

    /**
     * Scrive su un file tutte i record presenti nella struttura dati "Map
     * punteggi"
     *
     * @throws IOException
     */
    public void serializzaRecord() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(flrecord));
        out.writeObject(punteggi);
        out.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Names.wwait();

        if (source == okButton) {
            setVisible(false);
        }
    }
}