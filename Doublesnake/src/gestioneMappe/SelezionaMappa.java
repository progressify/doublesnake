package gestioneMappe;

import doublesnake.Names;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import snake.Coordinate;

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
    private JTextField nomeMappaFieldText;
    private static String nomeMappa = "LayoutStandard";
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
        setName(Names.NOME_FRAME_SELEZIONAMAPPA);
        setTitle(Names.NOME_FRAME_SELEZIONAMAPPA);
        setSize(Names.WINDOWS_WIDTH, Names.WINDOWS_HEIGH);
        labelSfondo = new JLabel();
        labelSfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));
        labelSfondo.setLayout(new BorderLayout());
        labelSfondo.add(createCenterPanel("LayoutStandard"), BorderLayout.CENTER);
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
        nomeMappaFieldText = new JTextField(20);
        nomeMappaFieldText.setText("LayoutStandard");
        nomeMappaFieldText.setEditable(false);
        panel.add(labelNomeMapp);
        panel.add(nomeMappaFieldText);
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

        panel.add(okButton, BorderLayout.SOUTH);
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

    public ArrayList<Coordinate> restituisciCoordinateMappa() {
        return ((Griglia) panelGriglia).restituisciCoordinateMappa(nomeMappa);
    }

    public void ricarica() {
        try {
            itr = ((Griglia) panelGriglia).ricarica();
        } catch (IOException ex) {
            Logger.getLogger(SelezionaMappa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SelezionaMappa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Names.wwait();

        if (source == okButton) {
            nomeMappa = nomeMappaFieldText.getText();
            setVisible(false);
        }
        if (source == dxButton) {
            String str = "LayoutStandard";
            labelSfondo.remove(panelGriglia);
            validate();
            if (i < itr.length) {
                str = itr[i++];
            } else {
                i = 0;
            }
            System.out.println(str);
            nomeMappaFieldText.setText(str);
            labelSfondo.add(createCenterPanel(str), BorderLayout.CENTER);
            validate();
        }
        if (source == sxButton) {
            String str = "LayoutStandard";
            labelSfondo.remove(panelGriglia);
            validate();
            if (i > 0) {
                str = itr[--i];
            } else {
                i = itr.length;
            }
            System.out.println(str);
            nomeMappaFieldText.setText(str);
            labelSfondo.add(createCenterPanel(str), BorderLayout.CENTER);
            validate();
        }
    }
}
