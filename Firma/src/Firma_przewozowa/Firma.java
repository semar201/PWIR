package Firma_przewozowa;

import java.util.concurrent.Semaphore;

public class Firma {
	Semaphore semafor;
	int ile_aut;
	Auto tab[];
	int predkosc;
	Firma(int ile_aut,int predkosc)
	{
		this.predkosc=predkosc;
		this.ile_aut=ile_aut;
		tab=new Auto[ile_aut];
		for(int i=0;i<ile_aut;i++)
		{
			tab[i]=new Auto();
			tab[i].id=i;
		}
		semafor=new Semaphore(ile_aut);
	}
	Auto wypozycz() throws InterruptedException
	{
		semafor.acquire();
		for(int i=0;i<ile_aut;i++)
			if(tab[i].stan==0)
			{
				tab[i].bak_stan=tab[i].bak_max;
				tab[i].stan=1;
				return tab[i];
			}
		System.err.println("BLAD -> brak wolnego auta");
		return null;
	}
	void oddaj(Auto auto)
	{
		tab[auto.id]=new Auto();
		tab[auto.id].id=auto.id;
		semafor.release();
		//System.out.print(semafor.getQueueLength()+"  ");
	}
	
}
