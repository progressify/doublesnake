package doublesnake;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    //pannello dove si muove lo snake
    public static final String PATH_CAMPO_COMETA = "./Grafica/cometa.jpg";
    public static final String PATH_MATTONCINO = "./Grafica/mattoncino.png";
    public static final int PANNELLO_WIDTH = 700;
    public static final int PANNELLO_HEIGHT = 500;
    public static final int DOT_SIZE = 25;

    public static Font caricaFont() {
        Font font = null;
        try {
            File fl = new File(PATH_FONT);
            FileInputStream fis = new FileInputStream(fl);
            font = Font.createFont(Font.TRUETYPE_FONT, fis);
            font = font.deriveFont(38f);
            System.out.println(font.getSize());
        } catch (FontFormatException ex) {
            font = new Font("Helvetica", Font.BOLD, 14);
            Logger.getLogger(Names.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GraficaSnake.class.getName()).log(Level.SEVERE, null, ex);
        }
        return font;
    }

    public static void wwait() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
        }
    }
}
