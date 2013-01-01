package gestioneMappe;

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
import snake.Coordinate;

/**
 *
 * @author tonino
 */
public class Griglia {
    
    private Map<String, ArrayList<Coordinate>> mappe;
    private ArrayList<Coordinate> mattoncini;
    private File flmappe = new File("mappe.dat");
    
    public Griglia() {
        mattoncini = new ArrayList();
        if (flmappe.exists()) {
            try {
                mappe = deserializza();
            } catch (IOException ex) {
                Logger.getLogger(Griglia.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Griglia.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mappe = new HashMap<String, ArrayList<Coordinate>>();
        }
    }
    
    public void inserisciMattoncino(int x, int y) {
    }
    
    public void cancellaMattoncino(int x, int y) {
    }
    
    public boolean salvaMappa(String nomeMappa, ArrayList<Coordinate> mattonciniMappa) {
        boolean result=false;
        if (!mappe.containsKey(nomeMappa)) {
            mappe.put(nomeMappa, mattonciniMappa);
             result=true;
        }
        return result;
    }
    
    private Map<String, ArrayList<Coordinate>> deserializza() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(flmappe));
        Map<String, ArrayList<Coordinate>> temp = (Map<String, ArrayList<Coordinate>>) in.readObject();
        in.close();
        return temp;
    }
    
    public void serializzaMappe() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(flmappe));
        out.writeObject(mappe);
        out.close();
    }
}
