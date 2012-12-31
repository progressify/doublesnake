/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneMappe;

import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tonino
 */
public class Griglia{

    private ArrayList<ArrayList> mappe;
    private ArrayList mattoncini;
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
            mappe = new ArrayList<ArrayList>();
        }
    }

    public void inserisciMattoncino(int x, int y) {
    }

    public void cancellaMattoncino(int x, int y) {
    }

    private ArrayList<ArrayList> deserializza() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(flmappe));
        ArrayList<ArrayList> temp = (ArrayList<ArrayList>) in.readObject();
        in.close();
        return temp;
    }

    public void memorizzaMappa() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(flmappe));
        out.writeObject(mappe);
        out.close();
    }
}
