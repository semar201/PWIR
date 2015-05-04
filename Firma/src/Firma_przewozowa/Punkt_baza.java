package Firma_przewozowa;

import java.util.Random;

public class Punkt_baza {
	Punkt tab[];
	int tab_size;
	public int znajdz_sklep()
	{
		int tab_pom[]=new int[tab_size];
		int tab_ile=0;
		for(int i=0;i<tab_size;i++)
			if(tab[i].obiekt==2)
			{
				tab_pom[tab_ile]=i;
				tab_ile++;
			}
		Random rand = new Random();
		return tab_pom[rand.nextInt(tab_ile)];
			
	}
	public int znajdz_firme() // poprawic jesli firm ma byc wiecej
	{
		int i=-1;
		for(i=0;i<tab_size;i++)
			if(tab[i].obiekt==1)
				break;
		if(i>=0)
			return i;
		else
		{
			System.err.println("Cos poszlo nie tak, id_firmy na mapie = "+i);
			System.exit(0);
			return -1;
		}
		
	}
	public void fabryki_start(Firma wypozyczalnia, Trasa_baza Tbaza)
	{
		for(int i=0;i<tab_size;i++)
			if(tab[i].obiekt==3)
			{
				new Fabryka(wypozyczalnia,this,i,Tbaza).start();
				//break; // TEST - 1 fabryka
			}
		
	}
	public void ustaw_polaczenie(int idA, int idB)
	{
		//System.out.println("Ustawianie "+idA+" "+idB);
		if(tab[idA].x != tab[idB].x && tab[idA].y != tab[idB].y)
		{
			System.err.println("BLAD: polaczenie "+idA+"-"+idB+" nie moze byc utworzone wsp: "+tab[idA].x+":"+tab[idA].y+" "+tab[idB].x+":"+tab[idB].y);      
		}else
		{
			int dystans;
			if(tab[idA].x!=tab[idB].x)
			{
				if(tab[idA].x>tab[idB].x)
					dystans=(int) (tab[idA].x-tab[idB].x);
				else
					dystans=(int) (tab[idB].x-tab[idA].x);
			}else
			{
				if(tab[idA].y>tab[idB].y)
					dystans=(int) (tab[idA].y-tab[idB].y);
				else
					dystans=(int) (tab[idB].y-tab[idA].y);
			}
			tab[idA].ustaw_sasiada(idB, dystans);
			tab[idB].ustaw_sasiada(idA, dystans);
				
		}
	}
	public void generuj()
	{
		if(tab_size!=0)
		{
			System.err.println("Nie mozna wygenerowac bazy punktow, baza nie jest pusta tab_size:"+tab_size);

		}
		else
		{
			System.out.println("Tworzenie bazy punktow ...");
			tab_size=24;
			tab=new Punkt[tab_size];
			for(int i=0;i<tab_size;i++)
				tab[i]=new Punkt();
			tab[0].ustaw_wsp(2,2,3);
			tab[1].ustaw_wsp(6,2,0);
			tab[2].ustaw_wsp(11,2,4);
			tab[3].ustaw_wsp(15,2,3);
			tab[4].ustaw_wsp(19,2,1);
			tab[5].ustaw_wsp(23,2,3);
			tab[6].ustaw_wsp(6,5,0);
			tab[7].ustaw_wsp(9,5,2);
			tab[8].ustaw_wsp(6,10,2);
			tab[9].ustaw_wsp(15,10,4);
			tab[10].ustaw_wsp(19,10,2);
			tab[11].ustaw_wsp(10,14,2);
			tab[12].ustaw_wsp(15,14,2);
			tab[13].ustaw_wsp(23,12,4);
			tab[14].ustaw_wsp(10,18,3);
			tab[15].ustaw_wsp(15,18,0);
			tab[16].ustaw_wsp(2,20,3);
			tab[17].ustaw_wsp(5,20,0);
			tab[18].ustaw_wsp(5,23,0);
			tab[19].ustaw_wsp(8,23,3);
			tab[20].ustaw_wsp(5,27,4);
			tab[21].ustaw_wsp(15,27,4);
			tab[22].ustaw_wsp(23,27,3);
			tab[23].ustaw_wsp(5, 18, 0);
			System.out.println("Ustawianie polaczen...");
			ustaw_polaczenie(0,1);
			ustaw_polaczenie(1,2);
			ustaw_polaczenie(2,3);
			ustaw_polaczenie(3,4);
			ustaw_polaczenie(4,5);
			ustaw_polaczenie(1,6);
			ustaw_polaczenie(6,7);
			ustaw_polaczenie(6,8);
			ustaw_polaczenie(8,9);
			ustaw_polaczenie(3,9);
			ustaw_polaczenie(4,10);
			ustaw_polaczenie(9,10);
			ustaw_polaczenie(5,13);
			ustaw_polaczenie(9,12);
			ustaw_polaczenie(11,12);
			ustaw_polaczenie(12,15);
			ustaw_polaczenie(14,15);
			ustaw_polaczenie(15,21);
			ustaw_polaczenie(13,22);
			ustaw_polaczenie(21,22);
			ustaw_polaczenie(20,21);
			ustaw_polaczenie(18,20);
			ustaw_polaczenie(18,19);
			ustaw_polaczenie(17,18);
			ustaw_polaczenie(16,17);
			ustaw_polaczenie(23, 14);
			ustaw_polaczenie(23, 17);
			for(int i=0;i<tab_size;i++)
			{
				System.out.print(i+" sasiedzi: ");
				for(int j=0;j<4;j++)
				{
					if(tab[i].tab[j][0]<10)
						System.out.print(" ");
					if(tab[i].tab[j][0]>=0)
						System.out.print(" ");
					System.out.print(" "+tab[i].tab[j][0]);
				}
				System.out.println(" ");
			}
			System.out.println("Zakonczono generowanie mapy");
		}
	}
}
