package snake;

import javax.swing.JFrame;
import menu.Opzioni;

/**
 *
 * @author tonino
 */
public class Punteggio {

    private String playerName;
    private int punti;
    private Opzioni opz = (Opzioni) Opzioni.getIstance(new JFrame());

    public Punteggio() {
        playerName = opz.getNomePlayer1();
    }

    public Punteggio(int iPunti) {      //questo forse si può omettere vedrò in corso d'opera se servirà!
        playerName = opz.getNomePlayer1();
        punti = iPunti;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPunti() {
        return punti;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public void aggiungiMela(int lunghezzaSerpente) {
        int livello;
        int tmpLivello = opz.getLivello();
        switch (tmpLivello) {
            case 300:
                livello = 1;
                break;
            case 250:
                livello = 2;
                break;
            case 200:
                livello = 3;
                break;
            case 125:
                livello = 4;
                break;
            default:
                livello = 5;
        }
        punti += (5 * (livello + (lunghezzaSerpente / 5)));
    }
}
