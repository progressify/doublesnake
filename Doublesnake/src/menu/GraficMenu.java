package menu;

import gestioneMappe.EditorMappe;
import gestioneMappe.SelezionaMappa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import snake.GraficaSnake;

/**
 *
 * @author Raffaela
 */
public class GraficMenu extends JPanel implements ActionListener {

    private static final long serialVersionUID = -6216250087094004378L;
    private JFrame windows;
    private int WINDOWS_WHITH = 700;
    private int WINDOWS_HEIGH = 600;
    private JButton bGioca;
    private JButton bCreaMappa;
    private JButton bCaricaMappa;
    private JButton bRecord;
    private JButton bStessoPc;
    private JButton bReteLocale;
    private JButton bOpzione;
    private JLabel label;

    public GraficMenu() {
        windows = new JFrame();
        windows.setName("DOUBLE SNAKE");
        windows.setTitle("DOUBLE SNAKE");
        windows.setSize(WINDOWS_WHITH, WINDOWS_HEIGH);
        label = jSfondo();
        label.add(jPanelMenuSingol());
        label.add(jPanelMenuMulti());
        windows.add(label);
        windows.setResizable(false);
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //imposta il look&feel del sistema operativo ospitante, va impostato solo la prima volta, poi resta memorizzato per tutti i frame
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Impossibile impostare lo stile: " + e);
        }
    }

    private JLabel jSfondo() {
        JLabel sfondo = new JLabel();
        sfondo.setBounds(0, 0, WINDOWS_WHITH, WINDOWS_HEIGH);
        sfondo.setIcon(new ImageIcon("./Grafica/spazio.jpg"));

        JLabel titolo = new JLabel();
        titolo.setBounds(0, 100, 263, 152);
        titolo.setIcon(new ImageIcon("./Grafica/titolo.png"));
        sfondo.add(titolo);

        JLabel multi = new JLabel();
        multi.setBounds(450, 150, 194, 29);
        multi.setIcon(new ImageIcon("./Grafica/multigiocatore.png"));

        sfondo.add(multi);

        JLabel singolo = new JLabel();
        singolo.setBounds(450, 0, 234, 29);
        singolo.setIcon(new ImageIcon("./Grafica/giocatoresingolo.png"));
        sfondo.add(singolo);

        sfondo.setVisible(true);
        return sfondo;
    }

    private JPanel jPanelMenuSingol() {
        JPanel pannelMenu = new JPanel();
        pannelMenu.setLayout(null);
        pannelMenu.setBounds(0, 0, WINDOWS_WHITH, WINDOWS_HEIGH);

        bGioca = new JButton();
        bGioca.addActionListener(this);
        bCreaMappa = new JButton();
        bCreaMappa.addActionListener(this);
        bCaricaMappa = new JButton();
        bCaricaMappa.addActionListener(this);
        bRecord = new JButton();
        bRecord.addActionListener(this);

        bGioca.setBounds(500, 30, 66, 26);
        bGioca.setIcon(new ImageIcon("./Grafica/gioca.png"));
        bGioca.setPressedIcon(new ImageIcon("./Grafica/gioca.png")); //questo serve per fargli cambiare aspetto quando ci clicchi, ora sono la stessa immagine quindi non si nota differenza, sarebbe bello fare un immagine differente da poter applicare
        bGioca.setContentAreaFilled(false);
        bGioca.setBorder(null);
        bGioca.setVisible(true);

        bCreaMappa.setBounds(500, 60, 137, 26);
        bCreaMappa.setIcon(new ImageIcon("./Grafica/creamappa.png"));
        bCreaMappa.setPressedIcon(new ImageIcon("./Grafica/creamappa.png"));
        bCreaMappa.setContentAreaFilled(false);
        bCreaMappa.setBorder(null);
        bCreaMappa.setVisible(true);

        bCaricaMappa.setBounds(500, 90, 155, 26);
        bCaricaMappa.setIcon(new ImageIcon("./Grafica/caricamappa.png"));
        bCaricaMappa.setPressedIcon(new ImageIcon("./Grafica/caricamappa.png"));
        bCaricaMappa.setContentAreaFilled(false);
        bCaricaMappa.setBorder(null);
        bCaricaMappa.setVisible(true);

        bRecord.setBounds(500, 120, 78, 26);
        bRecord.setIcon(new ImageIcon("./Grafica/record.png"));
        bRecord.setPressedIcon(new ImageIcon("./Grafica/record.png"));
        bRecord.setContentAreaFilled(false);
        bRecord.setBorder(null);
        bRecord.setVisible(true);

        pannelMenu.add(bGioca);
        pannelMenu.add(bCreaMappa);
        pannelMenu.add(bCaricaMappa);
        pannelMenu.add(bRecord);

        pannelMenu.setVisible(true);
        pannelMenu.setOpaque(false);
        return (pannelMenu);
    }

    private JPanel jPanelMenuMulti() {
        JPanel pannelMenu = new JPanel();
        pannelMenu.setLayout(null);
        pannelMenu.setBounds(0, 0, WINDOWS_WHITH, WINDOWS_HEIGH);

        bStessoPc = new JButton();
        bStessoPc.addActionListener(this);
        bReteLocale = new JButton();
        bReteLocale.addActionListener(this);
        bOpzione = new JButton();
        bOpzione.addActionListener(this);

        bStessoPc.setBounds(500, 180, 124, 26);
        bStessoPc.setIcon(new ImageIcon("./Grafica/stessopc.png"));
        bStessoPc.setPressedIcon(new ImageIcon("./Grafica/stessopc.png"));
        bStessoPc.setContentAreaFilled(false);
        bStessoPc.setBorder(null);
        bStessoPc.setVisible(true);

        bReteLocale.setBounds(500, 210, 149, 26);
        bReteLocale.setIcon(new ImageIcon("./Grafica/retelocale.png"));
        bReteLocale.setPressedIcon(new ImageIcon("./Grafica/retelocale.png"));
        bReteLocale.setContentAreaFilled(false);
        bReteLocale.setBorder(null);
        bReteLocale.setVisible(true);

        bOpzione.setBounds(450, 240, 101, 30);
        bOpzione.setIcon(new ImageIcon("./Grafica/opzioniverde.png"));
        bOpzione.setPressedIcon(new ImageIcon("./Grafica/opzioniverde.png"));
        bOpzione.setContentAreaFilled(false);
        bOpzione.setBorder(null);
        bOpzione.setVisible(true);


        pannelMenu.add(bStessoPc);
        pannelMenu.add(bReteLocale);
        pannelMenu.add(bOpzione);

        pannelMenu.setVisible(true);
        pannelMenu.setOpaque(false);
        return (pannelMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(EditorMappe.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (source == bGioca) {
            new GraficaSnake();
            //TODO
        }
        if (source == bCreaMappa) {
            windows.setVisible(false);
            JFrame frameEditorMappe = EditorMappe.getIstance(windows);
            frameEditorMappe.setVisible(true);
        }
        if (source == bCaricaMappa) {
            windows.setVisible(false);
            JFrame frameSelectMappe = SelezionaMappa.getIstance(windows);
            frameSelectMappe.setVisible(true);
        }
        if (source == bRecord) {
            //TODO
        }
        if (source == bStessoPc) {
            new GraficaSnake(true);
            //TODO
        }
        if (source == bReteLocale) {
            new GraficaSnake(true);
            //TODO
        }
        if (source == bOpzione) {
            //TODO
        }
    }
}
