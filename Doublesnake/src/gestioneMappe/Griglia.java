package gestioneMappe;

import doublesnake.Names;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import snake.Coordinate;

/**
 *
 * @author tonino
 */
public class Griglia extends JPanel {

    private Map<String, ArrayList<Coordinate>> mappe;
    private ArrayList<Coordinate> mattoncini;
    private File flmappe = new File(Names.NOME_FILE_MAPPE);
    //variabili per la griglia
    private Clicker clicker;
    private Cella[] celle;
    private boolean[] colora;

    public Griglia(int rows, int cols, boolean isEditable) {
        setLayout(new GridLayout(rows, cols));
        celle = new Cella[rows * cols];
        colora = new boolean[rows * cols];
        clicker = new Clicker();
        int r = 0;
        int c = 0;

        for (int i = 0; i < rows * cols; i++) {
            if (((i + 1) % Names.NUMERO_COLONNE) == 0) {
                r++; 
                c = 0; 
            }
            Cella tmpC = new Cella(r, c);
            if (isEditable) {
                tmpC.addMouseListener(clicker);
            }
            add(tmpC);
            c++;
        }

        mattoncini = new ArrayList();
        try {
            if (flmappe.exists()) {
                mappe = deserializzaMappe();
            } else {
                mappe = new HashMap<String, ArrayList<Coordinate>>();
            }
        } catch (IOException ex) {
            Logger.getLogger(Griglia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Griglia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class Cella extends JPanel {

        private final int riga;
        private final int colonna;
        private boolean colorata;
        private Color bckColore;

        public Cella(int row, int col) {
            riga = row;
            colonna = col;
            setOpaque(true);
            bckColore = getBackground();
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }

        public void switchColore() {
            setBackground(colorata ? bckColore : Color.GREEN);
            colorata = !colorata;
        }
        
        public Coordinate restituisciCoordinate(){
            return new Coordinate(riga, riga);
        }
    }

    private void coloraDecolora(Cella c) {
        c.switchColore();
    }

    private class Clicker extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent me) {
            Cella obj = (Cella) me.getSource();
            coloraDecolora(obj);
            inserisci_CancellaMattoncino(obj.restituisciCoordinate());
        }
    }

    /**
     * Se le coordinate sono già contenute nell'ArrayList "mattoncini" lo
     * rimuove altrimenti lo inserisce nelle coordinate indicate, metodo
     * utilizzato solo dalla classe EditorMappe
     *
     * @param coord coordinate del mattoncino da inserire/cancellare
     */
    public void inserisci_CancellaMattoncino(Coordinate coord) {
        boolean flag = false;
        for (Coordinate tmpCoord : mattoncini) {
            if (tmpCoord.equals(coord)) {
                flag = true;
            }
        }
        if (flag == false) {
            mattoncini.add(coord);
        } else {
            mattoncini.remove(coord);
        }
    }

    /**
     * Metodo utilizzato solo dalla classe SelezionaMappa, dato un nome della
     * mappa restituisce i mattoncini da disegnare al suo interno
     *
     * @param nomeMappa nome della mappa da visualizzare
     */
    public void disegnaMappa(String nomeMappa) {
        mattoncini = mappe.get(nomeMappa);
        //TODO
    }

    /**
     * Metodo utilizzato solo dalla classe EditorMappe, serve per memorizzare
     * una mappa appena creata all'interno della struttura dati "Map mappe"
     *
     * @param nomeMappa nome della mappa preso in input dalla form
     * @param mattonciniMappa mattoncini cliccati nella griglia
     * @return true se la mappa è stata correttamente salvata, false se esiste
     * già una mappa con lo stesso nome
     */
    public boolean salvaMappa(String nomeMappa, ArrayList<Coordinate> mattonciniMappa) {
        boolean result = false;
        if (!mappe.containsKey(nomeMappa)) {
            mappe.put(nomeMappa, mattonciniMappa);
            result = true;
        }
        return result;
    }

    /**
     * Carica dal file nella struttura dati "Map mappe", tutte le mappe fin ora
     * create
     *
     * @return restituisce la struttura dati contenenti tutte le mappe fin ora
     * create
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Map<String, ArrayList<Coordinate>> deserializzaMappe() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(flmappe));
        Map<String, ArrayList<Coordinate>> temp = (Map<String, ArrayList<Coordinate>>) in.readObject();
        in.close();
        return temp;
    }

    /**
     * Scrive su un file tutte le mappe presenti nella struttura dati "Map
     * mappe"
     *
     * @throws IOException
     */
    public void serializzaMappe() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(flmappe));
        out.writeObject(mappe);
        out.close();
    }
}
