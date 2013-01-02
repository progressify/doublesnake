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
    
    public Griglia(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        celle = new Cella[rows * cols];
        colora = new boolean[rows * cols];
        clicker = new Clicker();
        
        for (int i = 0; i < rows * cols; i++) {
            Cella c = new Cella();
            c.addMouseListener(clicker);
            add(c);
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
    
    private void coloraDecolora(Cella c) {
        c.switchColore();
    }
    
    private class Cella extends JPanel {
        
        private boolean colorata;
        private Color bckColore;
        
        public Cella() {
            setOpaque(true);
            bckColore = getBackground();
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
        
        public void switchColore() {
            setBackground(colorata ? bckColore : Color.GREEN);
            colorata = !colorata;
        }
    }
    
    private class Clicker extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent me) {
            coloraDecolora((Cella) me.getSource());
        }
    }

    /**
     * Inserisce un mattoncino nelle coordinate indicate, metodo utilizzato solo
     * dalla classe EditorMappe
     *
     * @param coord coordinate del mattoncino da inserire
     */
    public void inserisciMattoncino(Coordinate coord) {
        boolean flag = false;
        for (Coordinate tmpCoord : mattoncini) {
            if (tmpCoord.equals(coord)) {
                flag = true;
            }
        }
        if (flag == false) {
            mattoncini.add(coord);
        }
    }

    /**
     * Cancella un mattoncino dalle coordinate indicate, metodo utilizzato solo
     * dalla classe EditorMappe
     *
     * @param coord coordinate del mattoncino da cancellare
     */
    public void cancellaMattoncino(Coordinate coord) {
        for (Coordinate tmpCoord : mattoncini) {
            if (tmpCoord.equals(coord)) {
                mattoncini.remove(coord);
            }
        }
    }

    /**
     * Metodo utilizzato solo dalla classe SelezionaMappa, dato un nome della
     * mappa restituisce i mattoncini da disegnare al suo interno
     *
     * @param nomeMappa nome della mappa da visualizzare
     */
    public void disegnaMattoncini(String nomeMappa) {
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
