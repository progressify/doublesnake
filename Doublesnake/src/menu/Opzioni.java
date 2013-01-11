package menu;

import doublesnake.Names;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Opzioni extends JFrame implements ActionListener {

    private JFrame mainWindow;
    private static Opzioni istanzaOpzioni;
    private JLabel labelSfondo;
    private JButton okButton;
    private int livello = 1;
    private JButton livello1;
    private JButton livello2;
    private JButton livello3;
    private JButton livello4;
    private JButton livello5;
    private static String nomePlayer1 = "Player1";
    private static String nomePlayer2 = "Player2";
    private static JTextField textFieldPlayer1;
    private static JTextField textFieldPlayer2;

    public static JFrame getIstance(JFrame mainWindow) {
        if (istanzaOpzioni == null) {
            istanzaOpzioni = new Opzioni(mainWindow);
        }
        textFieldPlayer1.setText(nomePlayer1);
        textFieldPlayer2.setText(nomePlayer2);
        return istanzaOpzioni;
    }

    private Opzioni(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        setName(Names.NOME_FRAME_OPZIONI);
        setTitle(Names.NOME_FRAME_OPZIONI);
        setSize(Names.OPZIONI_WHITH, Names.OPZIONI_HEIGH);
        labelSfondo = new JLabel();
        labelSfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));
        labelSfondo.setLayout(new BorderLayout());
        labelSfondo.add(createSouthPanel(), BorderLayout.SOUTH);
        labelSfondo.add(createEastPanel(), BorderLayout.EAST);
        add(labelSfondo);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        okButton = new JButton();
        okButton.setIcon(new ImageIcon(Names.PATH_BUTTON_CONFERMA));
        okButton.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_CONFERMA));
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
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel labelSpazioVuoto1 = new JLabel(new ImageIcon(Names.PATH_LABEL_SPAZIOVUOTO));
        JLabel labelSpazioVuoto2 = new JLabel(new ImageIcon(Names.PATH_LABEL_SPAZIOVUOTO));

        JLabel labelLivello = new JLabel(new ImageIcon(Names.PATH_LABEL_LIVELLO));
        livello1 = new JButton();
        livello1.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
        livello1.setToolTipText(Names.TOOLTIP_LIVELLO1);
        livello1.setContentAreaFilled(false);
        livello1.setBorder(null);
        livello1.setFocusPainted(false);
        livello1.addActionListener(this);
        livello2 = new JButton();
        livello2.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
        livello2.setToolTipText(Names.TOOLTIP_LIVELLO2);
        livello2.setContentAreaFilled(false);
        livello2.setBorder(null);
        livello2.setFocusPainted(false);
        livello2.addActionListener(this);
        livello3 = new JButton();
        livello3.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
        livello3.setToolTipText(Names.TOOLTIP_LIVELLO3);
        livello3.setContentAreaFilled(false);
        livello3.setBorder(null);
        livello3.setFocusPainted(false);
        livello3.addActionListener(this);
        livello4 = new JButton();
        livello4.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
        livello4.setToolTipText(Names.TOOLTIP_LIVELLO4);
        livello4.setContentAreaFilled(false);
        livello4.setBorder(null);
        livello4.setFocusPainted(false);
        livello4.addActionListener(this);
        livello5 = new JButton();
        livello5.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
        livello5.setToolTipText(Names.TOOLTIP_LIVELLO5);
        livello5.setContentAreaFilled(false);
        livello5.setBorder(null);
        livello5.setFocusPainted(false);
        livello5.addActionListener(this);

        JPanel tmppanel1 = new JPanel();
        tmppanel1.setOpaque(false);
        tmppanel1.add(livello1);
        tmppanel1.add(livello2);
        tmppanel1.add(livello3);
        tmppanel1.add(livello4);
        tmppanel1.add(livello5);

        JLabel labelPlayer1 = new JLabel(new ImageIcon(Names.PATH_LABEL_NOMEPLAYER1));
        textFieldPlayer1 = new JTextField(nomePlayer1, 20);
        JPanel tmppanel2 = new JPanel();
        tmppanel2.setOpaque(false);
        tmppanel2.add(textFieldPlayer1);
        JLabel labelPlayer2 = new JLabel(new ImageIcon(Names.PATH_LABEL_NOMEPLAYER2));
        textFieldPlayer2 = new JTextField(nomePlayer2, 20);
        JPanel tmppanel3 = new JPanel();
        tmppanel3.add(textFieldPlayer2);
        tmppanel3.setOpaque(false);

        panel.add(labelSpazioVuoto1);
        panel.add(labelSpazioVuoto2);
        panel.add(labelLivello);
        panel.add(tmppanel1);
        panel.add(labelPlayer1);
        panel.add(tmppanel2);
        panel.add(labelPlayer2);
        panel.add(tmppanel3);
        panel.setOpaque(false);

        JPanel tmpPanel4 = new JPanel(new BorderLayout());
        tmpPanel4.add(panel, BorderLayout.NORTH);
        tmpPanel4.setOpaque(false);

        return tmpPanel4;
    }

    public int getLivello() {
        return livello;
    }

    public String getNomePlayer1() {
        return nomePlayer1;
    }

    public String getNomePlayer2() {
        return nomePlayer2;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public void setNomePlayer1(String nomePlayer1) {
        Opzioni.nomePlayer1 = nomePlayer1;
    }

    public void setNomePlayer2(String nomePlayer2) {
        Opzioni.nomePlayer2 = nomePlayer2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == okButton) {
            Names.wwait();
            if (!textFieldPlayer1.getText().equals("")) {
                nomePlayer1 = textFieldPlayer1.getText();
            }
            if (!textFieldPlayer2.getText().equals("")) {
                nomePlayer2 = textFieldPlayer2.getText();
            }
            setVisible(false);
        }
        if (source == livello1) {
            livello1.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello2.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello3.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello4.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello5.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello = 1;
        }
        if (source == livello2) {
            livello1.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello2.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello3.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello4.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello5.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello = 2;
        }
        if (source == livello3) {
            livello1.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello2.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello3.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello4.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello5.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello = 3;
        }
        if (source == livello4) {
            livello1.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello2.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello3.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello4.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello5.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOVUOTO));
            livello = 4;
        }
        if (source == livello5) {
            livello1.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello2.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello3.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello4.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello5.setIcon(new ImageIcon(Names.PATH_BUTTON_LIVELLOPIENO));
            livello = 5;
        }
    }
}
