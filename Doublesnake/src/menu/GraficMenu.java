package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import snake.GraficaSnake;

public class GraficMenu extends JPanel implements ActionListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6216250087094004378L;

	private JFrame windows;
	private int WINDOWS_WHITH = 700;
	private int WINDOWS_HEIGH = 600;
	private JButton bGioca;
	private JButton bCreaMappa;
	private JButton bCaricaMappa;
	private JButton bRecord;
	private JButton bStessoPc;
	private JButton bReteLocale;
	private JButton bOpzione;

	public GraficMenu() {
		windows();
	}

	public void windows(){
		windows= new JFrame();
		windows.setName("DOUBLE SNAKE");
		windows.setTitle("DOUBLE SNAKE");
		windows.setSize(WINDOWS_WHITH,WINDOWS_HEIGH);
		jPanelSfondo();
		jPanelMenuSingol();
		jPanelMenuMulti();
		windows.setResizable(false);
		windows.setVisible(true);
		windows.repaint();
	}	

	private  void jPanelSfondo(){
		JPanel panelSfondo=new JPanel();
		panelSfondo.setBounds(0, 0, WINDOWS_WHITH,WINDOWS_HEIGH);
		panelSfondo.setBorder(new EmptyBorder(5,5,5,5));
		panelSfondo.setLayout(null);

		JLabel sfondo=new JLabel();
		sfondo.setBounds(0,0,700,600);

		sfondo.setIcon(new ImageIcon("./Grafica/spazio.jpg"));

		JLabel titolo=new JLabel();
		titolo.setBounds(0,100,263,152);
		titolo.setIcon(new ImageIcon("./Grafica/titolo.png"));
		sfondo.add(titolo);

		JLabel multi=new JLabel();
		multi.setBounds(450,150,194,29);
		multi.setIcon(new ImageIcon("./Grafica/multigiocatore.png"));
		sfondo.add(multi);

		JLabel singolo=new JLabel();
		singolo.setBounds(450,0,234,29);
		singolo.setIcon(new ImageIcon("./Grafica/giocatoresingolo.png"));
		sfondo.add(singolo);

		panelSfondo.add(sfondo);

		sfondo.setVisible(true);
		panelSfondo.setVisible(true);
		windows.add(panelSfondo);
	}

	private  void jPanelMenuSingol(){
		JPanel pannelMenu = new JPanel();
		pannelMenu.setLayout(null);
		pannelMenu.setBounds(0, 0, WINDOWS_WHITH,WINDOWS_HEIGH);

		bGioca= new JButton();
		bGioca.addActionListener(this);
		//***si può fare anche così pero credo sia può opportuno per chiarezza del codice fare tutto giù, vedi metodo actionPerformed***
		//		bGioca.addActionListener(new ActionListener() {
		//			
		//			public void actionPerformed(ActionEvent arg0) {
		//				new GraficaSnake();
		//			}
		//		});

		bCreaMappa=new JButton();
		bCreaMappa.addActionListener(this);
		bCaricaMappa=new JButton();
		bCaricaMappa.addActionListener(this);
		bRecord=new JButton();
		bRecord.addActionListener(this);

		bGioca.setIcon(new ImageIcon("./Grafica/gioca.png"));
		bGioca.setBounds(500, 30, 66, 26);
		bGioca.setVisible(true);
		bGioca.setBackground(Color.BLACK);
		bGioca.setBorder(null);

		bCreaMappa.setIcon(new ImageIcon("./Grafica/creamappa.png"));
		bCreaMappa.setBounds(500, 60, 137, 26);
		bCreaMappa.setVisible(true);
		bCreaMappa.setBackground(Color.BLACK);
		bCreaMappa.setBorder(null);

		bCaricaMappa.setIcon(new ImageIcon("./Grafica/caricamappa.png"));
		bCaricaMappa.setBounds(500, 90, 155, 26);
		bCaricaMappa.setVisible(true);
		bCaricaMappa.setBackground(Color.BLACK);
		bCaricaMappa.setBorder(null);

		bRecord.setIcon(new ImageIcon("./Grafica/record.png"));
		bRecord.setBounds(500, 120, 78, 26);
		bRecord.setVisible(true);
		bRecord.setBackground(Color.BLACK);
		bRecord.setBorder(null);

		pannelMenu.add(bGioca);
		pannelMenu.add(bCreaMappa);
		pannelMenu.add(bCaricaMappa);
		pannelMenu.add(bRecord);

		pannelMenu.setVisible(true);
		windows.add(pannelMenu);
	}

	private  void jPanelMenuMulti(){
		JPanel pannelMenu = new JPanel();
		pannelMenu.setLayout(null);
		pannelMenu.setBounds(0, 0, WINDOWS_WHITH,WINDOWS_HEIGH);

		bStessoPc= new JButton();
		bStessoPc.addActionListener(this);
		bReteLocale=new JButton();
		bReteLocale.addActionListener(this);
		bOpzione=new JButton();
		bOpzione.addActionListener(this);

		bStessoPc.setIcon(new ImageIcon("./Grafica/stessopc.png"));
		bStessoPc.setBounds(500, 180, 124, 26);
		bStessoPc.setVisible(true);
		bStessoPc.setBackground(Color.BLACK);
		bStessoPc.setBorder(null);

		bReteLocale.setIcon(new ImageIcon("./Grafica/retelocale.png"));
		bReteLocale.setBounds(500, 210, 149, 26);
		bReteLocale.setVisible(true);
		bReteLocale.setBackground(Color.BLACK);
		bReteLocale.setBorder(null);

		bOpzione.setIcon(new ImageIcon("./Grafica/opzioniverde.png"));
		bOpzione.setBounds(450, 240, 101, 30);
		bOpzione.setVisible(true);
		bOpzione.setBackground(Color.BLACK);
		bOpzione.setBorder(null);


		pannelMenu.add(bStessoPc);
		pannelMenu.add(bReteLocale);
		pannelMenu.add(bOpzione);

		pannelMenu.setVisible(true);
		windows.add(pannelMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();

		if(source==bGioca){
			//TODO
			new GraficaSnake();
		}
		if(source==bCreaMappa){
			//TODO
		}
		if(source==bCaricaMappa){
			//TODO
		}
		if(source==bRecord){
			//TODO
		}
		if(source==bStessoPc){
			new GraficaSnake(true);
			//TODO
		}
		if(source==bReteLocale){
			new GraficaSnake(true);
			//TODO
		}
		if(source==bOpzione){
			//TODO
		}
	}

}

