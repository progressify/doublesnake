package menu;

import doublesnake.Names;
import gestioneMappe.EditorMappe;
import gestioneMappe.SelezionaMappa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import snake.GraficaSnake;
import snakeMulti.GraficaMulti;
import snakeRete.GraficaMultiOn;

/**
 *
 * @author Raffaela
 */
public class GraficMenu extends JPanel implements ActionListener {

    private static final long serialVersionUID = -6216250087094004378L;
    private JFrame windows;
    private JButton bGioca;
    private JButton bCreaMappa;
    private JButton bCaricaMappa;
    private JButton bRecord;
    private JButton bStessoPc;
    private JButton bReteLocale;
    private JButton bOpzione;
    private JLabel label;
    private MusicHandler music;

    public GraficMenu() {
        windows = new JFrame();
        windows.setName(Names.NOME_FRAME);
        windows.setTitle(Names.NOME_FRAME);
        windows.setSize(Names.LARGHEZZA_FRAME, Names.ALTEZZA_FRAME);
        label = jSfondo();
        label.add(jPanelMenuSingol());
        label.add(jPanelMenuMulti());
        windows.add(label);
        windows.setResizable(false);
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        music = new MusicHandler();
        //imposta il look&feel del sistema operativo ospitante, va impostato solo la prima volta, poi resta memorizzato per tutti i frame
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Impossibile impostare lo stile: " + e);
        }
    }

    private JLabel jSfondo() {
        JLabel sfondo = new JLabel();
        sfondo.setBounds(0, 0, Names.LARGHEZZA_FRAME, Names.ALTEZZA_FRAME);
        sfondo.setIcon(new ImageIcon(Names.PATH_SFONDO));

        JLabel titolo = new JLabel();
        titolo.setBounds(0, 100, 263, 152);
        titolo.setIcon(new ImageIcon(Names.PATH_LABEL_TITOLOAPP));
        sfondo.add(titolo);

        JLabel multi = new JLabel();
        multi.setBounds(450, 150, 194, 29);
        multi.setIcon(new ImageIcon(Names.PATH_LABEL_MULTIGIOCATORE));

        sfondo.add(multi);

        JLabel singolo = new JLabel();
        singolo.setBounds(450, 0, 234, 29);
        singolo.setIcon(new ImageIcon(Names.PATH_LABEL_GIOCATORESINGOLO));
        sfondo.add(singolo);

        sfondo.setVisible(true);
        return sfondo;
    }

    private JPanel jPanelMenuSingol() {
        JPanel pannelMenu = new JPanel();
        pannelMenu.setLayout(null);
        pannelMenu.setBounds(0, 0, Names.LARGHEZZA_FRAME, Names.ALTEZZA_FRAME);

        bGioca = new JButton();
        bGioca.addActionListener(this);
        bCreaMappa = new JButton();
        bCreaMappa.addActionListener(this);
        bCaricaMappa = new JButton();
        bCaricaMappa.addActionListener(this);
        bRecord = new JButton();
        bRecord.addActionListener(this);

        bGioca.setBounds(500, 30, 66, 26);
        bGioca.setIcon(new ImageIcon(Names.PATH_BUTTON_GIOCA));
        bGioca.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_GIOCA)); //questo serve per fargli cambiare aspetto quando ci clicchi, ora sono la stessa immagine quindi non si nota differenza, sarebbe bello fare un immagine differente da poter applicare
        bGioca.setContentAreaFilled(false);
        bGioca.setBorder(null);
        bGioca.setVisible(true);

        bCreaMappa.setBounds(500, 60, 137, 26);
        bCreaMappa.setIcon(new ImageIcon(Names.PATH_BUTTON_CREAMAPPA));
        bCreaMappa.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_CREAMAPPA));
        bCreaMappa.setContentAreaFilled(false);
        bCreaMappa.setBorder(null);
        bCreaMappa.setVisible(true);

        bCaricaMappa.setBounds(500, 90, 155, 26);
        bCaricaMappa.setIcon(new ImageIcon(Names.PATH_BUTTON_CARICAMAPPA));
        bCaricaMappa.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_CARICAMAPPA));
        bCaricaMappa.setContentAreaFilled(false);
        bCaricaMappa.setBorder(null);
        bCaricaMappa.setVisible(true);

        bRecord.setBounds(500, 120, 78, 26);
        bRecord.setIcon(new ImageIcon(Names.PATH_BUTTON_RECORD));
        bRecord.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_RECORD));
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
        pannelMenu.setBounds(0, 0, Names.LARGHEZZA_FRAME, Names.ALTEZZA_FRAME);

        bStessoPc = new JButton();
        bStessoPc.addActionListener(this);
        bReteLocale = new JButton();
        bReteLocale.addActionListener(this);
        bOpzione = new JButton();
        bOpzione.addActionListener(this);

        bStessoPc.setBounds(500, 180, 124, 26);
        bStessoPc.setIcon(new ImageIcon(Names.PATH_BUTTON_STESSOPC));
        bStessoPc.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_STESSOPC));
        bStessoPc.setContentAreaFilled(false);
        bStessoPc.setBorder(null);
        bStessoPc.setVisible(true);

        bReteLocale.setBounds(500, 210, 149, 26);
        bReteLocale.setIcon(new ImageIcon(Names.PATH_BUTTON_RETELOCALE));
        bReteLocale.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_RETELOCALE));
        bReteLocale.setContentAreaFilled(false);
        bReteLocale.setBorder(null);
        bReteLocale.setVisible(true);

        bOpzione.setBounds(450, 240, 101, 30);
        bOpzione.setIcon(new ImageIcon(Names.PATH_BUTTON_OPZIONE));
        bOpzione.setPressedIcon(new ImageIcon(Names.PATH_BUTTON_OPZIONE));
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
        Names.wwait();
        WindowAdapterInner closer= new WindowAdapterInner(music);
        if (source == bGioca) {
            music.setLocation("single.wav");
            JFrame snake = new GraficaSnake(closer);
            snake.setVisible(true);
            snake.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            snake.addWindowListener(closer);
        }
            if (source == bCreaMappa) {
                JFrame frameEditorMappe = EditorMappe.getIstance(windows);
    
                frameEditorMappe.setVisible(true);
            }
            if (source == bCaricaMappa) {
                JFrame frameSelectMappe = SelezionaMappa.getIstance(windows);
                ((SelezionaMappa) frameSelectMappe).ricarica();
                frameSelectMappe.setVisible(true);
            }
            if (source == bRecord) {
                JFrame frameOpzioni = Record.getIstance(windows);
                frameOpzioni.setVisible(true);
            }
            if (source == bStessoPc) {
                music.setLocation("multi.wav");
                final JFrame snakePC = new GraficaMulti(closer);
                snakePC.setVisible(true);
                snakePC.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                snakePC.addWindowListener(closer);
            }
            if (source == bReteLocale) {
                
//            JFrame snake;
//            try {
//                snake = new GraficaMultiOn();
//                snake.setVisible(true);
//            } catch (IOException ex) {
//                Logger.getLogger(GraficMenu.class.getName()).log(Level.SEVERE, null, ex);
//            }
                
                music.setLocation("multi.wav");
                JFrame frameRete = GraficaRete.getIstance(windows);
                frameRete.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frameRete.addWindowListener(closer);
                frameRete.setVisible(true);

            }
            if (source == bOpzione) {
                JFrame frameOpzioni = Opzioni.getIstance(windows);
                frameOpzioni.setVisible(true);
            }
        }
    }
