package doublesnake;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import snake.GraficaSnake;

/**
 *
 * @author tonino
 */
public final class Names {

    private Names() {
    }
    //proprietà del frame
    public static final String NOME_FRAME = "DOUBLE SNAKE";
    public static final String NOME_FRAME_RECORD = "DOUBLE SNAKE - Record";
    public static final String NOME_FRAME_GIOCA = "DOUBLE SNAKE - Gioco";
    public static final String NOME_FRAME_OPZIONI = "DOUBLE SNAKE - Opzioni";
    public static final String NOME_FRAME_CREAMAPPA = "DOUBLE SNAKE - Crea Mappa";
    public static final String NOME_FRAME_SELEZIONAMAPPA = "DOUBLE SNAKE - Seleziona Mappa";
    public static final int LARGHEZZA_FRAME = 700;
    public static final int ALTEZZA_FRAME = 600;
    public static final int LARGHEZZA_OPZIONI = 460;
    public static final int ALTEZZA_OPZIONI = 230;
    public static final int LARGHEZZA_RECORD = 300;
    public static final int ALTEZZA_RECORD = 330;
    public static final String PATH_SFONDO = "./Grafica/spazio.jpg";
    public static final String PATH_FONT = "./Grafica/digital.ttf";
    //classe record
    public static final String NOME_FILE_RECORD = "record.dat";
    //path delle label
    public static final String PATH_LABEL_SPAZIO = "      ";
    public static final String PATH_LABEL_INSERISCINOMEMAPPA = "./Grafica/inseriscinomemappa.png";
    public static final String PATH_LABEL_NOMEMAPPA = "./Grafica/nomemappa.png";
    public static final String PATH_LABEL_LIVELLO = "./Grafica/livello.png";
    public static final String PATH_LABEL_MULTIGIOCATORE = "./Grafica/multigiocatore.png";
    public static final String PATH_LABEL_GIOCATORESINGOLO = "./Grafica/giocatoresingolo.png";
    public static final String PATH_LABEL_TITOLOAPP = "./Grafica/titolo.png";
    public static final String PATH_LABEL_NOMEPLAYER1 = "./Grafica/nomeplayer1.png";
    public static final String PATH_LABEL_NOMEPLAYER2 = "./Grafica/nomeplayer2.png";
    public static final String PATH_LABEL_SPAZIOVUOTO = "./Grafica/spaziovuoto.png";
    //path dei bottoni
    public static final String PATH_BUTTON_SALVA = "./Grafica/salva.png";
    public static final String PATH_BUTTON_PLAY = "./Grafica/play.png";
    public static final String PATH_BUTTON_CONFERMA = "./Grafica/conferma.png";
    public static final String PATH_BUTTON_GIOCA = "./Grafica/gioca.png";
    public static final String PATH_BUTTON_CREAMAPPA = "./Grafica/creamappa.png";
    public static final String PATH_BUTTON_CARICAMAPPA = "./Grafica/caricamappa.png";
    public static final String PATH_BUTTON_RECORD = "./Grafica/record.png";
    public static final String PATH_BUTTON_STESSOPC = "./Grafica/stessopc.png";
    public static final String PATH_BUTTON_RETELOCALE = "./Grafica/retelocale.png";
    public static final String PATH_BUTTON_OPZIONE = "./Grafica/opzioniverde.png";
    public static final String PATH_BUTTON_LIVELLOPIENO = "./Grafica/pieno.png";
    public static final String PATH_BUTTON_LIVELLOVUOTO = "./Grafica/vuoto.png";
    public static final String PATH_FRECCIA_DESTRA = "./Grafica/frecciaverdedx.png";
    public static final String PATH_FRECCIA_SINISTRA = "./Grafica/frecciaverdesx.png";
    public static final String PATH_BUTTON_AGGIORNA = "./Grafica/pulsantereset2.png";
    public static final String PATH_BUTTON_ANNULLA = "./Grafica/Annulla.png";
    //tooltiptext
    public static final String TOOLTIP_ANNULLABUTTON = "Indietro";
    public static final String TOOLTIP_PAUSA = "Pausa";
    public static final String TOOLTIP_ANNULLABUTTONEDITOR = "Resetta la griglia";
    public static final String TOOLTIP_OKBUTTON_EDITOR = "Memorizza la mappa appena creata";
    public static final String TOOLTIP_OKBUTTON_SELECTOR = "Seleziona la mappa";
    public static final String TOOLTIP_DXBUTTON = "Mappa successiva";
    public static final String TOOLTIP_SXBUTTON = "Mappa precedente";
    public static final String TOOLTIP_LIVELLO1 = "Livello 1";
    public static final String TOOLTIP_LIVELLO2 = "Livello 2";
    public static final String TOOLTIP_LIVELLO3 = "Livello 3";
    public static final String TOOLTIP_LIVELLO4 = "Livello 4";
    public static final String TOOLTIP_LIVELLO5 = "Livello 5";
    public static final String TOOLTIP_NEWGAMEBUTTON = "Nuovo Gioco";
    //tutto quello che mi serve per la griglia
    public static final String NOME_FILE_MAPPE = "mappe.dat";
    public static final int NUMERO_COLONNE = 28;
    public static final int NUMERO_RIGHE = 20;
    //Mela
    public static final String PATH_MELA = "./Grafica/pallino.png";
    
    //Snake Verde
    public static final int ALL_DOTS = 672;
    //testa
    public static final String PATH_TESTA_SU = "./Grafica/snake/tsu.png";
    public static final String PATH_TESTA_GIU = "./Grafica/snake/tg.png";
    public static final String PATH_TESTA_DESTRA = "./Grafica/snake/td.png";
    public static final String PATH_TESTA_SINISTRA = "./Grafica/snake/ts.png";
    //corpo
    public static final String PATH_ALTO_ALTO_DESTRA = "./Grafica/snake/aad.png";
    public static final String PATH_ALTO_ALTO_SINISTRA = "./Grafica/snake/aas.png";
    public static final String PATH_ALTO_BASSO_DESTRA = "./Grafica/snake/abd.png";
    public static final String PATH_ALTO_BASSO_SINISTRA = "./Grafica/snake/abs.png";
    //movimento
    public static final String PATH_MOVIMETNO_VERTICALE = "./Grafica/snake/mv.png";
    public static final String PATH_MOVIMETNO_ORIZZONTALE = "./Grafica/snake/mo.png";
    //coda
    public static final String PATH_CODA_SU = "./Grafica/snake/csu.png";
    public static final String PATH_CODA_GIU = "./Grafica/snake/cg.png";
    public static final String PATH_CODA_DESTRA = "./Grafica/snake/cd.png";
    public static final String PATH_CODA_SINISTRA = "./Grafica/snake/cs.png";
    
    //snake giallo
    //testa
    public static final String PATH_TESTA_SU2 = "./Grafica/snake2/tsu.png";
    public static final String PATH_TESTA_GIU2 = "./Grafica/snake2/tg.png";
    public static final String PATH_TESTA_DESTRA2 = "./Grafica/snake2/td.png";
    public static final String PATH_TESTA_SINISTRA2 = "./Grafica/snake2/ts.png";
    //corpo
    public static final String PATH_ALTO_ALTO_DESTRA2 = "./Grafica/snake2/aad.png";
    public static final String PATH_ALTO_ALTO_SINISTRA2 = "./Grafica/snake2/aas.png";
    public static final String PATH_ALTO_BASSO_DESTRA2 = "./Grafica/snake2/abd.png";
    public static final String PATH_ALTO_BASSO_SINISTRA2 = "./Grafica/snake2/abs.png";
    //movimento
    public static final String PATH_MOVIMETNO_VERTICALE2 = "./Grafica/snake2/mv.png";
    public static final String PATH_MOVIMETNO_ORIZZONTALE2 = "./Grafica/snake2/mo.png";
    //coda
    public static final String PATH_CODA_SU2 = "./Grafica/snake2/csu.png";
    public static final String PATH_CODA_GIU2 = "./Grafica/snake2/cg.png";
    public static final String PATH_CODA_DESTRA2 = "./Grafica/snake2/cd.png";
    public static final String PATH_CODA_SINISTRA2 = "./Grafica/snake2/cs.png";
    //pannello dove si muove lo snake
    public static final String PATH_CAMPO_COMETA = "./Grafica/cometa.jpg";
    public static final String PATH_MATTONCINO = "./Grafica/mattoncino.png";
    public static final int LARGHEZZA_PANNELLO = 700;
    public static final int ALTEZZA_PANNELLO = 500;
    public static final int DOT_SIZE = 25;
    private static Font font = null;

    public static Font caricaFont() {
        if (font == null) {
            try {
                File fl = new File(PATH_FONT);
                FileInputStream fis = new FileInputStream(fl);
                font = Font.createFont(Font.TRUETYPE_FONT, fis);
                font = font.deriveFont(35f);
                System.out.println(font.getSize());
            } catch (FontFormatException ex) {
                font = new Font("Helvetica", Font.BOLD, 35);
                Logger.getLogger(Names.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GraficaSnake.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return font;
    }

    public static void wwait() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
        }
    }
    
    public static void imageLoad(Hashtable<String, Image> snake) {
        //PALLINO
        ImageIcon iia = new ImageIcon(Names.PATH_MELA);
        snake.put("apple" , iia.getImage());

        //testa su
        ImageIcon idtsu = new ImageIcon(Names.PATH_TESTA_SU);
        snake.put("tsu", idtsu.getImage());

        //testa giu
        ImageIcon idtg = new ImageIcon(Names.PATH_TESTA_GIU);
        snake.put("tg", idtg.getImage());

        //testa destra
        ImageIcon idtd = new ImageIcon(Names.PATH_TESTA_DESTRA);
        snake.put("td", idtd.getImage());

        //testa sinistra
        ImageIcon idts = new ImageIcon(Names.PATH_TESTA_SINISTRA);
        snake.put("ts", idts.getImage());

        //coda su 
        ImageIcon idcsu = new ImageIcon(Names.PATH_CODA_SU);
        snake.put("csu", idcsu.getImage());

        //coda giu
        ImageIcon idcg = new ImageIcon(Names.PATH_CODA_GIU);
        snake.put("cg", idcg.getImage());

        //coda sinistra
        ImageIcon idcs = new ImageIcon(Names.PATH_CODA_SINISTRA);
        snake.put("cs", idcs.getImage());

        //coda destra
        ImageIcon idcd = new ImageIcon(Names.PATH_CODA_DESTRA);
        snake.put("cd", idcd.getImage());

        //verso alto a sinistra
        ImageIcon idaas = new ImageIcon(Names.PATH_ALTO_ALTO_SINISTRA);
        snake.put("aas", idaas.getImage());

        //verso alto a destra
        ImageIcon idaad = new ImageIcon(Names.PATH_ALTO_ALTO_DESTRA);
        snake.put("aad", idaad.getImage());

        //verso basso a sinistra
        ImageIcon idabs = new ImageIcon(Names.PATH_ALTO_BASSO_SINISTRA);
        snake.put("abs", idabs.getImage());

        //verso basso a destra
        ImageIcon idabd = new ImageIcon(Names.PATH_ALTO_BASSO_DESTRA);
        snake.put("abd", idabd.getImage());

        //corpo orizzontale
        ImageIcon idmo = new ImageIcon(Names.PATH_MOVIMETNO_ORIZZONTALE);
        snake.put("mo", idmo.getImage());

        //corpo verticale
        ImageIcon idmv = new ImageIcon(Names.PATH_MOVIMETNO_VERTICALE);
        snake.put("mv", idmv.getImage());

        //mattoncino
        ImageIcon mattoncino = new ImageIcon(Names.PATH_MATTONCINO);
        snake.put("mattoncino", mattoncino.getImage());
    }
    
    public static void imageLoad2(Hashtable<String, Image> snake) {
        //PALLINO
        ImageIcon iia = new ImageIcon(Names.PATH_MELA);
        snake.put("apple" , iia.getImage());

        //testa su
        ImageIcon idtsu = new ImageIcon(Names.PATH_TESTA_SU2);
        snake.put("tsu", idtsu.getImage());

        //testa giu
        ImageIcon idtg = new ImageIcon(Names.PATH_TESTA_GIU2);
        snake.put("tg", idtg.getImage());

        //testa destra
        ImageIcon idtd = new ImageIcon(Names.PATH_TESTA_DESTRA2);
        snake.put("td", idtd.getImage());

        //testa sinistra
        ImageIcon idts = new ImageIcon(Names.PATH_TESTA_SINISTRA2);
        snake.put("ts", idts.getImage());

        //coda su 
        ImageIcon idcsu = new ImageIcon(Names.PATH_CODA_SU2);
        snake.put("csu", idcsu.getImage());

        //coda giu
        ImageIcon idcg = new ImageIcon(Names.PATH_CODA_GIU2);
        snake.put("cg", idcg.getImage());

        //coda sinistra
        ImageIcon idcs = new ImageIcon(Names.PATH_CODA_SINISTRA2);
        snake.put("cs", idcs.getImage());

        //coda destra
        ImageIcon idcd = new ImageIcon(Names.PATH_CODA_DESTRA2);
        snake.put("cd", idcd.getImage());

        //verso alto a sinistra
        ImageIcon idaas = new ImageIcon(Names.PATH_ALTO_ALTO_SINISTRA2);
        snake.put("aas", idaas.getImage());

        //verso alto a destra
        ImageIcon idaad = new ImageIcon(Names.PATH_ALTO_ALTO_DESTRA2);
        snake.put("aad", idaad.getImage());

        //verso basso a sinistra
        ImageIcon idabs = new ImageIcon(Names.PATH_ALTO_BASSO_SINISTRA2);
        snake.put("abs", idabs.getImage());

        //verso basso a destra
        ImageIcon idabd = new ImageIcon(Names.PATH_ALTO_BASSO_DESTRA2);
        snake.put("abd", idabd.getImage());

        //corpo orizzontale
        ImageIcon idmo = new ImageIcon(Names.PATH_MOVIMETNO_ORIZZONTALE2);
        snake.put("mo", idmo.getImage());

        //corpo verticale
        ImageIcon idmv = new ImageIcon(Names.PATH_MOVIMETNO_VERTICALE2);
        snake.put("mv", idmv.getImage());

        //mattoncino
        ImageIcon mattoncino = new ImageIcon(Names.PATH_MATTONCINO);
        snake.put("mattoncino", mattoncino.getImage());
    }
}
