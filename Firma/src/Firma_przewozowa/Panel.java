package Firma_przewozowa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javafx.scene.layout.Border;

import javax.swing.*;

class Panel extends JFrame {

	private JSplitPane split1;
	private JSplitPane split2;
	private JPanel panel1;// mapa
	private JPanel panel2;// radio
	JRadioButton AutoButton;
	JRadioButton PunktButton;
	JPanel panel3;// butons
	int mnoznik;
	int maxX;
	int maxY;
	int boczneX;

	int maxlab = 10;
	JLabel id;
	JLabel labels[];
	JLabel predkosc;
	int id_int = 0;

	public Panel(Punkt_baza Pbaza, Firma wypozyczalnia) {
		setTitle("Firma przewozowa");
		setBackground(Color.white);

		mnoznik = 20;
		boczneX = 300;
		maxX = 30 * mnoznik;
		maxY = 30 * mnoznik;

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		// setSize(maxX+116,maxY+28);
		setPreferredSize(new Dimension(maxX + boczneX, maxY + 28));
		getContentPane().add(topPanel);
		// Create the panels
		createPanel1(Pbaza, wypozyczalnia); // mapa
		createPanel2(); // radio
		createPanel3(Pbaza, wypozyczalnia); // buttons

		// Create a splitter pane
		split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		topPanel.add(split1, BorderLayout.CENTER);

		split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split2.setLeftComponent(panel2);
		split2.setRightComponent(panel3);
		split2.setSize(boczneX, maxY);
		split1.setLeftComponent(panel1);
		split1.setRightComponent(split2);
		split1.setEnabled(false);
		split2.setEnabled(false);
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createPanel1(Punkt_baza Pbaza, Firma wypozyczalnia) {
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JPanel mapka = new Mapa(Pbaza, mnoznik, wypozyczalnia, maxX, maxY);
		panel1.add(mapka);
		panel1.setPreferredSize(new Dimension(maxX, maxY));
	}

	public void createPanel2() {
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		AutoButton = new JRadioButton("Auto");
		AutoButton.setMnemonic(KeyEvent.VK_B);
		// AutoButton.setActionCommand(AutoButton);
		AutoButton.setSelected(true);
		AutoButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

		PunktButton = new JRadioButton("Punkt");
		PunktButton.setMnemonic(KeyEvent.VK_C);
		// PunktButton.setActionCommand(PunktButton);
		PunktButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

		AutoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id_int = 0;
				czysc_tekst();
			}
		});
		PunktButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id_int = 0;
				czysc_tekst();
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(AutoButton);
		group.add(PunktButton);

		panel2.add(AutoButton);
		panel2.add(PunktButton);

	}

	public void createPanel3(Punkt_baza Pbaza, Firma wypozyczalnia) {
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(maxlab + 3, 0));

		JButton VdownButton = new JButton("◄");
		VdownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (wypozyczalnia.predkosc-1>0)
				{
					wypozyczalnia.predkosc-=1;
				}
			}
		});

		predkosc = new JLabel("predksoc: XX");
		JButton VupButton = new JButton("►");
		VupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (wypozyczalnia.predkosc+1<=30)
				{
					wypozyczalnia.predkosc+=1;
				}
			}
		});
		JPanel VPanel = new JPanel();
		VPanel.add(VdownButton);
		VPanel.add(predkosc);
		VPanel.add(VupButton);
		panel3.add(VPanel);
		
		
		JButton DOWNbutton = new JButton("◄");
		DOWNbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id_int - 1 >= 0)
				{
					czysc_tekst();
					id_int--;
				}
			}
		});
		id = new JLabel("ID");
		JButton UPbutton = new JButton("►");
		UPbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AutoButton.isSelected())
					if (id_int + 1 < wypozyczalnia.ile_aut)
					{
						czysc_tekst();
						id_int++;
					}
				if (PunktButton.isSelected())
					if (id_int + 1 < Pbaza.tab_size)
					{
						czysc_tekst();
						id_int++;
					}
			}
		});

		JPanel idPanel = new JPanel();
		idPanel.add(DOWNbutton);
		idPanel.add(id);
		idPanel.add(UPbutton);
		panel3.add(idPanel);

		
		
		
		labels = new JLabel[maxlab];
		for (int i = 0; i < maxlab; i++) {
			labels[i] = new JLabel("");
			labels[i].setFont(new Font("Arial", 1, 12));
			panel3.add(labels[i]);
		}
		// stan = new JLabel(new
		// Integer((int)wypozyczalnia.tab[0].bak_stan).toString());
		// stan.setFont(new Font("Verdana",1,20));
		// panel3.add(stan);
	}

	void czysc_tekst() {
		for (int i = 0; i < maxlab; i++)
			labels[i].setText("");
	}

	void odswiez(Firma wypozyczalnia, Punkt_baza Pbaza ) {
		repaint(); // mapka

		id.setText("ID: " + new Integer(id_int).toString());
		predkosc.setText("Predkosc wszystkich aut: " + wypozyczalnia.predkosc);

		if (AutoButton.isSelected()) {
			String stan = "Stan: ";
			switch (wypozyczalnia.tab[id_int].stan) {
			case 0:
				stan += "wolny";
				break;
			case 1:
				stan += "zajety";
				break;
			case 2:
				stan += "zajety, brak paliwa";
				break;
			}
			labels[0].setText(stan);
			labels[1].setText("Zrodlo: "
					+ new Integer((int) wypozyczalnia.tab[id_int].zrodlo)
							.toString());
			labels[2].setText("Cel:   "
					+ new Integer((int) wypozyczalnia.tab[id_int].cel)
							.toString());
			labels[3]
					.setText("X:   "
							+ new Integer((int) wypozyczalnia.tab[id_int].x)
									.toString());
			labels[4]
					.setText("Y:   "
							+ new Integer((int) wypozyczalnia.tab[id_int].y)
									.toString());
			labels[5].setText("Paliwo stan: "
					+ new Integer((int) wypozyczalnia.tab[id_int].bak_stan)
							.toString());
			labels[6].setText("Paliwo max: "
					+ new Integer((int) wypozyczalnia.tab[id_int].bak_max)
							.toString());
		}

		if (PunktButton.isSelected()) {

			String typ = "Typ: ";
			switch (Pbaza.tab[id_int].obiekt) {
			case 0:
				typ += "zwykly";
				break;
			case 1:
				typ += "firma";
				break;
			case 2:
				typ += "sklep";
				break;
			case 3:
				typ += "fabryka";
				break;
			case 4:
				typ += "stacja";
				break;
			}
			labels[0].setText(typ);
			labels[1].setText("X: "
					+ new Integer((int) Pbaza.tab[id_int].x).toString());
			labels[2].setText("Y: "
					+ new Integer((int) Pbaza.tab[id_int].y).toString());
			if (Pbaza.tab[id_int].obiekt == 1)// firma
			{
				labels[3].setText("Wszystkich aut: "+wypozyczalnia.ile_aut);
				int wolnych=0;
				for(int i=0;i<wypozyczalnia.ile_aut;i++)
					if(wypozyczalnia.tab[i].stan==0)
						wolnych++;
				labels[4].setText("Wolnych aut: "+wolnych);
			}else
			if (Pbaza.tab[id_int].obiekt == 3)// fabryka
			{
				String stan = "Stan fabryki: ";
				switch (Pbaza.tab[id_int].fabryka_stan) {
				case 0:
					stan += "bezczynna";
					break;
				case 1:
					stan += "czeka na auto";
					break;
				case 2:
					stan += "posiada auto";
					break;
				case 3:
					stan += "auto stoi w trasie";
				}
				labels[3].setText(stan);
			} else if (Pbaza.tab[id_int].obiekt == 4)// stacja
			{
				labels[3].setText("Ilosc dystrybutorow:"
						+ Pbaza.tab[id_int].stacja.ile_stanowisk);
			}

		}
		panel3.updateUI();
	}

}
