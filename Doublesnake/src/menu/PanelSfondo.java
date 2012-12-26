package menu;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class PanelSfondo extends JPanel{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -7323371464465946808L;
		private Image sfondoImage;     

	    public PanelSfondo(Image sfondoImage) {
	        this.sfondoImage = sfondoImage;
	    }

	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        g.drawImage(sfondoImage, 300, 400, this);
	        
	    }
}
