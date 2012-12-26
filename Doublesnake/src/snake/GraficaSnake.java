package snake;

import java.awt.Image;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GraficaSnake extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Hashtable<String, Image> snake1;
    private Hashtable<String, Image> snake2;

    public GraficaSnake() {
        imageLoad(snake1);
    }

    public GraficaSnake(boolean checkMulti) {
        imageLoad(snake1);
        imageLoad(snake2);
    }

    private void imageLoad(Hashtable<String, Image> snake) {
        //testa su
        ImageIcon idtsu = new ImageIcon(this.getClass().getResource("./Grafica/snake/tsu.png"));
        snake.put("tsu", idtsu.getImage());

        //testa giu
        ImageIcon idtg = new ImageIcon(this.getClass().getResource("./Grafica/snake/tg.png"));
        snake.put("tg", idtg.getImage());

        //testa destra
        ImageIcon idtd = new ImageIcon(this.getClass().getResource("./Grafica/snake/td.png"));
        snake.put("td", idtd.getImage());

        //testa sinistra
        ImageIcon idts = new ImageIcon(this.getClass().getResource("./Grafica/snake/ts.png"));
        snake.put("ts", idts.getImage());

        //coda su 
        ImageIcon idcsu = new ImageIcon(this.getClass().getResource("./Grafica/snake/csu.png"));
        snake.put("csu", idcsu.getImage());

        //coda giu
        ImageIcon idcg = new ImageIcon(this.getClass().getResource("./Grafica/snake/cg.png"));
        snake.put("cg", idcg.getImage());

        //cosa sinistra
        ImageIcon idcs = new ImageIcon(this.getClass().getResource("./Grafica/snake/cs.png"));
        snake.put("cs", idcs.getImage());

        //coda destra
        ImageIcon idcd = new ImageIcon(this.getClass().getResource("./Grafica/snake/cd.png"));
        snake.put("cd", idcd.getImage());

        //verso alto a sinistra
        ImageIcon idaas = new ImageIcon(this.getClass().getResource("./Grafica/snake/aas.png"));
        snake.put("aas", idaas.getImage());

        //verso alto a destra
        ImageIcon idaad = new ImageIcon(this.getClass().getResource("./Grafica/snake/aad.png"));
        snake.put("aad", idaad.getImage());

        //verso basso a sinistra
        ImageIcon idabs = new ImageIcon(this.getClass().getResource("./Grafica/snake/abs.png"));
        snake.put("abs", idabs.getImage());

        //verso basso a destra
        ImageIcon idabd = new ImageIcon(this.getClass().getResource("./Grafica/snake/abd.png"));
        snake.put("abd", idabd.getImage());

        //corpo orizzontale
        ImageIcon idmo = new ImageIcon(this.getClass().getResource("./Grafica/snake/mo.png"));
        snake.put("mo", idmo.getImage());

        //corpo verticale
        ImageIcon idmv = new ImageIcon(this.getClass().getResource("./Grafica/snake/mv.png"));
        snake.put("mv", idmv.getImage());

        setFocusable(true);
    }
}
