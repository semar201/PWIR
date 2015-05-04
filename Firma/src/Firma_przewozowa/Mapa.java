package Firma_przewozowa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Mapa extends JPanel {
	
	Punkt_baza Pbaza;
	int mnoznik;
	Firma wypozyczalnia;
	int maxX;
	int maxY;
	Mapa(Punkt_baza Pbaza,int mnoznik,Firma wypozyczalnia,int maxX, int maxY)
	{
		this.wypozyczalnia=wypozyczalnia;
		this.Pbaza=Pbaza;
		this.mnoznik=mnoznik;
		this.maxX=maxX;
		this.maxY=maxY;
	}
	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		int promien=4;

		BufferedImage Firma = null;
		BufferedImage Sklep = null;
		BufferedImage Fabryka = null;
		BufferedImage Stacja = null;
		BufferedImage AutkoG = null;
		BufferedImage AutkoR = null;
		try {
			Firma = ImageIO.read(new File("img\\Firma.png"));
			Sklep = ImageIO.read(new File("img\\Sklep.png"));
			Fabryka = ImageIO.read(new File("img\\Fabryka.png"));
			Stacja = ImageIO.read(new File("img\\Stacja.png"));
			AutkoG = ImageIO.read(new File("img\\car_green.png"));
			AutkoR = ImageIO.read(new File("img\\car_red.png"));
		} catch (IOException e) {
			System.err.println("Blad przy otwieraniu pliku"+e);
			e.printStackTrace();
		}
		for(int i=0;i<Pbaza.tab_size;i++)
			for(int j=i;j<Pbaza.tab_size;j++)
			{
				if(i==Pbaza.tab[j].tab[0][0] || i==Pbaza.tab[j].tab[1][0] ||i==Pbaza.tab[j].tab[2][0] ||i==Pbaza.tab[j].tab[3][0] )
				{
					g2d.setColor(Color.BLACK);
					g2d.drawLine((int)Pbaza.tab[i].x*mnoznik,(int)Pbaza.tab[i].y*mnoznik, (int)Pbaza.tab[j].x*mnoznik,(int)Pbaza.tab[j].y*mnoznik);
				}
			}

		for(int i=0;i<Pbaza.tab_size;i++)
		{
			g2d.setColor(Color.BLACK);
			//if(Pbaza.tab[i].obiekt>0)
			g2d.drawString(String.valueOf(i),(Pbaza.tab[i].x*mnoznik)+4,(Pbaza.tab[i].y*mnoznik)-4 );
			switch(Pbaza.tab[i].fabryka_stan)
			{
			case 1:
				g2d.setColor(Color.YELLOW);
				break;
			case 2:
				g2d.setColor(Color.GREEN);
				break;
			case 3:
				g2d.setColor(Color.RED);
				break;
			default:
				g2d.setColor(Color.BLACK);
				break;
			}
			switch(Pbaza.tab[i].obiekt)
			{ // firma sklep fabryka stacja
			case 1:
				g.drawImage(Firma, (int)(Pbaza.tab[i].x*mnoznik)+4, (int)(Pbaza.tab[i].y*mnoznik)-47,32,32, null);
				break;
			case 2:
				g.drawImage(Sklep, (int)(Pbaza.tab[i].x*mnoznik)+4, (int)(Pbaza.tab[i].y*mnoznik)-47,32,32, null);
				break;
			case 3:
				g.drawImage(Fabryka, (int)(Pbaza.tab[i].x*mnoznik)+4, (int)(Pbaza.tab[i].y*mnoznik)-47,32,32, null);
				break;
			case 4:
				g.drawImage(Stacja, (int)(Pbaza.tab[i].x*mnoznik)+4, (int)(Pbaza.tab[i].y*mnoznik)-47,32,32, null);
				break;
			}
			g2d.fillOval((int)(Pbaza.tab[i].x*mnoznik)-promien, (int)(Pbaza.tab[i].y*mnoznik)-promien, promien*2, promien*2);
		}
		for(int i=0;i<wypozyczalnia.ile_aut;i++)
		{
			if(wypozyczalnia.tab[i].stan>0)
			{
				g2d.setColor(Color.RED);
				g2d.drawString("ID:  "+String.valueOf(i), (int)(wypozyczalnia.tab[i].x*mnoznik)+8, (int)(wypozyczalnia.tab[i].y*mnoznik)-8 );
				//g2d.drawString("BAK: "+String.valueOf((int)wypozyczalnia.tab[i].bak_stan)+"/"+String.valueOf((int)wypozyczalnia.tab[i].bak_max), (int)(wypozyczalnia.tab[i].x*mnoznik)+8, (int)(wypozyczalnia.tab[i].y*mnoznik)+2 );
				//g2d.drawString("X:  "+String.valueOf((int)wypozyczalnia.tab[i].x) , (int)(wypozyczalnia.tab[i].x*mnoznik)+8, (int)(wypozyczalnia.tab[i].y*mnoznik)+12 );
				//g2d.drawString("Y:  "+String.valueOf((int)wypozyczalnia.tab[i].y), (int)(wypozyczalnia.tab[i].x*mnoznik)+8, (int)(wypozyczalnia.tab[i].y*mnoznik)+22 );
				if(wypozyczalnia.tab[i].stan==2)
					g.drawImage(AutkoR, (int)(wypozyczalnia.tab[i].x*mnoznik)-8, (int)(wypozyczalnia.tab[i].y*mnoznik)-8,16,16, null);
				else
					g.drawImage(AutkoG, (int)(wypozyczalnia.tab[i].x*mnoznik)-8, (int)(wypozyczalnia.tab[i].y*mnoznik)-8,16,16, null);
			}
		}
	}
	
}