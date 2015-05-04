package Firma_przewozowa;

import java.util.Random;

public class Auto {
	
	float dokladnosc_pozycji=(float) 0.1;
	float bak_max;
	float bak_stan;
	int id;
	int stan;    // 0- wolny 1-zajety 2-zajety+brak_paliwa
	int cel; 	 //aktualny cel
	int zrodlo;  //skad jedzie (dla tras)
	int trasa;   //aktualna trasa
	float x;
	float y;
	Auto()
	{
		Random rand = new Random();
		stan=0;
		this.bak_max=rand.nextInt(50)+50;
		this.bak_stan=bak_max;
	}
	void restart()
	{
		bak_stan=bak_max;
		cel=0;
	}
	void ustaw_firmowa_poz(Punkt pkt)
	{
		this.x=pkt.x;
		this.y=pkt.y;
	}
	int czy_cel_osiagniety(Punkt pkt)
	{
		if(this.x<=pkt.x+dokladnosc_pozycji && this.x>=pkt.x-dokladnosc_pozycji && this.y<=pkt.y+dokladnosc_pozycji && this.y>=pkt.y-dokladnosc_pozycji)
		{
			this.x=pkt.x;
			this.y=pkt.y;
			return 1;
		}
		else
			return 0;
	}
	int znajdz_kierunek_x(Punkt pkt)
	{
		/*
		 *  -1 lewo (-1 0)
		 *   1 prawo( 1 0)
		 */
		if(x>pkt.x)
			return -1;
		if(x<pkt.x)
			return 1;
		return 0;
	}
	int znajdz_kierunek_y(Punkt pkt)
	{
		/*
		 * -1 gora (0 -1)
		 *  1 dol  (0  1)
		 */
		if(y>pkt.y)
			return -1;
		if(y<pkt.y)
			return 1;
		return 0;
	}
	
}
