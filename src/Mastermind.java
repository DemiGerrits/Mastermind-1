
// Opstartklasse voor een applicatie
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Mastermind extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		// Maak een frame
		JFrame frame = new Mastermind();
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Mastermind OOP");
		JPanel paneel = new Paneel();
		frame.setContentPane(paneel);
		frame.setVisible(true);
	}
}

class Paneel extends JPanel {
	private static final long serialVersionUID = 1L;

	private boolean knipper, kleurGoed;
	private boolean[] plaatsing;
	private int rij, kolom, x, teller, tellerJuist, tellerAanwezig, tellerSchuif, tellerRij, index;

	private Color bord, pionWit, pionGeel, pionRood, pionGroen, pionBlauw, pionOranje, pionPaars;
	Color[] knipperRij;
	Color[][] kleurRij, controleRij;
	private JButton knopGeel, knopRood, knopGroen, knopBlauw, knopOranje, knopPaars, knopControle;
	private Random r = new Random();
	private String teRadenCode, probeerCode;
	private Timer timer2;

	public Paneel()

	{
		teRadenCode = "";
		plaatsing = new boolean[6];
		for (int i = 0; i < 6; i++) {
			plaatsing[i] = true;

		}
		for (int i = 0; i < 4; i++) {

			do {
				index = r.nextInt(6);

			} while (plaatsing[index] == false);
			plaatsing[index] = false;

			switch (index) {
			case 0:
				teRadenCode += "r";
				break;
			case 1:
				teRadenCode += "g";
				break;
			case 2:
				teRadenCode += "b";
				break;
			case 3:
				teRadenCode += "y";
				break;
			case 4:
				teRadenCode += "o";
				break;
			case 5:
				teRadenCode += "p";
				break;
			}
		}
		probeerCode = "";

		teller = 0;
		x = 400;
		tellerSchuif = 0;
		tellerJuist = 0;
		tellerAanwezig = 0;
		tellerRij = 0;
		kleurGoed = false;
		kleurRij = new Color[8][4];
		controleRij = new Color[8][4];
		knipperRij = new Color[4];
		timer2 = new Timer(100, new TimerHandler());
		knipper = true;
		pionWit = new Color(255, 255, 255);
		bord = new Color(169, 169, 169);
		pionGeel = new Color(255, 242, 0);
		pionRood = new Color(255, 16, 0);
		pionGroen = new Color(21, 255, 0);
		pionBlauw = new Color(0, 157, 255);
		pionOranje = new Color(255, 97, 0);
		pionPaars = new Color(85, 26, 139);

		for (int r = 0; r < 8; r++) {
			for (int k = 0; k < 4; k++) {
				kleurRij[r][k] = pionWit;
			}
		}

		for (int t = 0; t < 8; t++) {
			for (int l = 0; l < 4; l++) {
				controleRij[t][l] = bord;
			}
		}

		// lege knop

		knopGeel = new JButton("Geel");
		KnopHandlerGeel kg = new KnopHandlerGeel();
		knopGeel.addActionListener(kg);
		add(knopGeel);

		knopRood = new JButton("Rood");
		KnopHandlerRood kr = new KnopHandlerRood();
		knopRood.addActionListener(kr);
		add(knopRood);

		knopGroen = new JButton("Groen");
		KnopHandlerGroen kgr = new KnopHandlerGroen();
		knopGroen.addActionListener(kgr);
		add(knopGroen);

		knopBlauw = new JButton("Blauw");
		KnopHandlerBlauw kb = new KnopHandlerBlauw();
		knopBlauw.addActionListener(kb);
		add(knopBlauw);

		knopOranje = new JButton("Oranje");
		KnopHandlerOranje ko = new KnopHandlerOranje();
		knopOranje.addActionListener(ko);
		add(knopOranje);
		
		knopPaars = new JButton("Paars");
		KnopHandlerPaars kp = new KnopHandlerPaars();
		knopPaars.addActionListener(kp);
		add(knopPaars);

		knopControle = new JButton("Controle");
		KnopHandlerControle kcontrol = new KnopHandlerControle();
		knopControle.addActionListener(kcontrol);
		add(knopControle);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawString("teRadenCode " + teRadenCode, 820, 20);
		g.drawString("probeerCode " + probeerCode, 820, 40);
		

		for (int r = 0; r < 8; r++) {
			for (int k = 0; k < 4; k++) {
				g.setColor(kleurRij[r][k]);
				g.fillOval(100 * k + 100, 100 * r + 100, 80, 80);
			}
		}

		for (int t = 0; t < 8; t++) {
			for (int l = 0; l < 4; l++) {
				g.setColor(controleRij[t][l]);
				g.fillOval(100 * l + 115 + x, 100 * t + 115, 50, 50);
			}
		}

	}

	class KnopHandlerGeel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rij = teller / 4;
			kolom = teller % 4;
			kleurRij[rij][kolom] = pionGeel;
			teller++;
			probeerCode += "y";
			repaint();
		}
	}

	class KnopHandlerRood implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rij = teller / 4;
			kolom = teller % 4;
			kleurRij[rij][kolom] = pionRood;
			teller++;
			probeerCode += "r";
			repaint();

		}
	}

	class KnopHandlerGroen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rij = teller / 4;
			kolom = teller % 4;
			kleurRij[rij][kolom] = pionGroen;
			teller++;
			probeerCode += "g";
			repaint();

		}
	}

	class KnopHandlerBlauw implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rij = teller / 4;
			kolom = teller % 4;
			kleurRij[rij][kolom] = pionBlauw;
			teller++;
			probeerCode += "b";
			repaint();

		}
	}
	
	class KnopHandlerOranje implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rij = teller / 4;
			kolom = teller % 4;
			kleurRij[rij][kolom] = pionOranje;
			teller++;
			probeerCode += "o";
			
			if(rij == 8 & kolom == 4) {
				tellerRij--;
				timer2.start();

			}
			repaint();
		}
	}
	
	class KnopHandlerPaars implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rij = teller / 4;
			kolom = teller % 4;
			kleurRij[rij][kolom] = pionPaars;
			teller++;
			probeerCode += "p";
			
			if(rij == 8 & kolom == 4) {
				tellerRij--;
				timer2.start();

			}
			repaint();
		}
	}

	class TimerHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (knipper == true) {
				for (int i = 0; i < 4; i++) {
					kleurRij[tellerRij][i] = knipperRij[i];
				}
				knipper = false;
			} else {
				for (int i = 0; i < 4; i++) {
					kleurRij[tellerRij][i] = Color.black;
				}
				knipper = true;
			}
			repaint();

		}
	}

	class KnopHandlerControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			for (int t = 0; t < 4; t++) {
				for (int p = 0; p < 4; p++) {
					if (probeerCode.substring(t, t + 1).equals(teRadenCode.substring(p, p + 1)) && t == p) {
						tellerJuist++;
					} else if (probeerCode.substring(t, t + 1).equals(teRadenCode.substring(p, p + 1))) {
						tellerAanwezig++;
					}
				}

			}

			if (tellerJuist == 4) {
				for (int i = 0; i < 4; i++) {
					knipperRij[i] = kleurRij[tellerRij][i];
				}
				kleurGoed = true;
			}
			while (tellerJuist > 0) {
				controleRij[tellerRij][tellerSchuif] = Color.white;
				tellerJuist--;
				tellerSchuif++;
			}

			while (tellerAanwezig > 0) {
				controleRij[tellerRij][tellerSchuif] = Color.black;
				tellerAanwezig--;
				tellerSchuif++;
			}

			tellerRij++;
			tellerSchuif = 0;
			probeerCode = "";
			
			
			repaint();
			if (kleurGoed == true) {
				tellerRij--;
				timer2.start();

			}			
		}
	}
}
