package Firma_przewozowa;

/*
 * Punkt na mapie
 * skrzyzowanie / zakret / koniec drogi / fabryka / sklep / start / stacja
 */
public class Punkt {
	// id -1 = brak punktu-null
	int tab[][];       // polaczenia z innymi pkt id_pobliskiego_pkt(max 4 punkty) i odleglosc
	int obiekt=0;      // 0-nic 1-firma 2-sklep 3-fabryka 4-stacja
	//int najb_stacja[]; // najblizsza stacja(id) i odleglosc
	Stacja_paliw stacja=null; // jesli na punkcie jest stacja umiesc tu obiekt stacji
	float x;			   //wsp na mapie
	float y;			   //wsp na mapie
	int fabryka_stan=0; // zmienna dla fabryk,  0-brak akcji 1-oczekuje na auto 2-dostala auto 3-auto zdechlo
	public void ustaw_wsp(int x,int y,int obiekt)
	{
		this.x=x;
		this.y=y;
		this.obiekt=obiekt;
		if(obiekt==4)
			this.stacja = new Stacja_paliw(x,y);
		tab=new int[4][];
		for(int i=0;i<4;i++)
		{

			tab[i]=new int[2];
			tab[i][0]=-1;
			tab[i][1]=0;
		}
	}
	public void ustaw_sasiada(int id,int dystans)
	{
		int ok=0;
		for(int i=0;i<4;i++)
		{
			if(tab[i][0]==-1)
			{
				ok=1;
				tab[i][0]=id;
				tab[i][1]=dystans;
				break;
			}
		}
		if(ok==0)
		{
			System.err.println("BLAD: proba dopisania sasiada dla pkt, tablica pelna, parametry:"+id+" "+dystans);
		}
	}
	
}

