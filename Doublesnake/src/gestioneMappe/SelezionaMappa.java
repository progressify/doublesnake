package gestioneMappe;

import doublesnake.Names;
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
import javax.swing.JTextField;

/**
 *
 * @author tonino
 */
public class SelezionaMappa extends JFrame implements ActionListener {

    private JButton okButton;
    private JButton dxButton;
    private JButton sxButton;
    private String[] itr;
    private JFrame mainWindow;
    private JLabel labelSfondo;
    private JPanel panelGriglia;
    private JTextField nomemappa;
    private static SelezionaMappa istanzaSelection;
    private int i = 0;

    public static JFrame getIstance(JFrame mainWindow) {
        if (istanzaSelection == null) {
            istanzaSelection = new SelezionaMappa(mainWindow);
        }
        return istanzaSelection;
    }

    private SelezionaMappa(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        setName(Names.NOME_FRAME);
        setTitle(Names.NOME_FRAME);
        setSize(Names.WINDOWS_WHITH, Names.WINDOWS_HEIGH);
        labelSfondo = new JLabel();
        labelSfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));
        labelSfondo.setLayout(new BorderLayout());
        labelSfondo.add(createCenterPanel(""), BorderLayout.CENTER);
        itr = ((Griglia) panelGriglia).mappePresenti();
        labelSfondo.add(createNorthPanel(), BorderLayout.NORTH);
        labelSfondo.add(createSouthPanel(), BorderLayout.SOUTH);
        labelSfondo.add(createEastPanel(), BorderLayout.EAST);
        labelSfondo.add(createWestPanel(), BorderLayout.WEST);
        add(labelSfondo);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createCenterPanel(String name) {
        panelGriglia = new Griglia(Names.NUMERO_RIGHE, Names.NUMERO_COLONNE, name);
        panelGriglia.setOpaque(false);
        return panelGriglia;
    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel();
        JLabel labelNomeMapp = new JLabel();
        labelNomeMapp.setIcon(new ImageIcon(Names.PATH_LABEL_NOMEMAPPA));
        nomemappa = new JTextField(30);
        nomemappa.setEditable(false);
        panel.add(labelNomeMapp);
        panel.add(nomemappa);
        panel.setOpaque(false);
        return panel;
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        okButton = new JButton();
        okButton.setIcon(new ImageIcon(Names.PATH_BUTTON_SALVA));
        okButton.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_SALVA));
        okButton.setContentAreaFilled(false);
        okButton.setBorder(null);
        okButton.setToolTipText(Names.TOOLTIP_OKBUTTON_SELECTOR);
        okButton.addActionListener(this);

        JPanel paneltemp3 = new JPanel();
        paneltemp3.setOpaque(false);
        paneltemp3.add(okButton);
        panel.add(paneltemp3, BorderLayout.SOUTH);
        panel.setOpaque(false);
        return panel;
    }

    private JPanel createEastPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        dxButton = new JButton();
        dxButton.setIcon(new ImageIcon(Names.PATH_FRECCIA_DESTRA));
        dxButton.setPressedIcon(new ImageIcon(Names.PATH_FRECCIA_DESTRA));
        dxButton.setToolTipText(Names.TOOLTIP_DXBUTTON);
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
        sxButton.setIcon(new ImageIcon(Names.PATH_FRECCIA_SINISTRA));
        sxButton.setPressedIcon(new ImageIcon(Names.PATH_FRECCIA_SINISTRA));
        sxButton.setToolTipText(Names.TOOLTIP_SXBUTTON);
        sxButton.setContentAreaFilled(false);
        sxButton.setBorder(null);
        sxButton.addActionListener(this);

        panel.add(sxButton);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        Object source = e.getSource();

        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(EditorMappe.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (source == okButton) {
            if (nomemappa.getText().equals("")) {
                //TODO nel caso non è stata selezionata nessuna mappa (deve selezionare il layout di default senza mattoncini)
            } else {
                //TODO nel caso viene selezionata una mappa (deve impostare il layout della mappa scelta)
            }
            setVisible(false);
        }
        if (source == dxButton) {
            flag = true;
            String str = "";
            labelSfondo.remove(panelGriglia);
            validate();
            if (i < itr.length) {
                str = itr[i];
                i++;
            } else {
                i = 0;
            }
            System.out.println(str);
            nomemappa.setText(str);
            labelSfondo.add(createCenterPanel(str), BorderLayout.CENTER);
            validate();
        }
        if (source == sxButton) {
            flag = false;
            String str = "";
            labelSfondo.remove(panelGriglia);
            validate();
            if (i > 0) {
                i--;
                str = itr[i];
            } else {
                i = itr.length;
            }
            System.out.println(str);
            nomemappa.setText(str);
            labelSfondo.add(createCenterPanel(str), BorderLayout.CENTER);
            validate();
        }
    }
}
