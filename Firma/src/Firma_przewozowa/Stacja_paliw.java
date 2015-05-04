package Firma_przewozowa;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Stacja_paliw {
	int ile_stanowisk;
	Semaphore semafor;
	
	public Stacja_paliw(int x,int y) {
		Random rand = new Random();
		ile_stanowisk=rand.nextInt(4)+4;
		semafor=new Semaphore(ile_stanowisk);
		System.out.println("Stacja "+x+":"+y+" otworzona.");
		
	}
	void wjedz() throws InterruptedException
	{
		semafor.acquire();
	}
	void wyjedz()
	{
		semafor.release();
	}
	void zatankuj(Auto samochod)
	{
		try {
			wjedz();
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			wyjedz();
			samochod.bak_stan=samochod.bak_max;
			wyjedz();
		} catch (InterruptedException e1) {
			System.err.println("Blad podczas tankowania samochodu id:"+samochod.id);
			e1.printStackTrace();
		}
	}
	
}
