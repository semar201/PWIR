package Serwer;

public class Baza_przedmiotow {
	Przedmiot tab[];
	int wielkosc;
	Baza_przedmiotow()
	{
		wielkosc=0;
	}
	int dodaj(String nazwa,double cena,String opis)
	{
		if(wielkosc>0)
		{
//			System.out.println("powiekszanie bazy");
			Przedmiot tab_pom[] = new Przedmiot[wielkosc];
			for(int i=0;i<wielkosc; i++)
				tab_pom[i]=tab[i];
			tab=new Przedmiot[wielkosc+1];
			for(int i=0;i<wielkosc; i++)
				tab[i]=tab_pom[i];

		}else
		{
//			System.out.println("Nowa baza");
			tab=new Przedmiot[wielkosc+1];
		}
		tab[wielkosc]=new Przedmiot();
//		System.out.println("Wprowadznie nowego przedmiotu id:"+wielkosc);
		tab[wielkosc].nazwa=nazwa;
		tab[wielkosc].cena=cena;
		tab[wielkosc].opis=opis;
		System.out.println("Dodano: "+tab[wielkosc].toString());
		wielkosc++;
		return 1;
	}
	int usun(int id)
	{
		if(id<wielkosc && id>=0)
		{
			Przedmiot tab_pom[] = new Przedmiot[wielkosc];
			for(int i=0;i<wielkosc; i++)
				tab_pom[i]=tab[i];
			tab=new Przedmiot[wielkosc-1];
			for(int i=0;i<wielkosc-1; i++)
				if(i<id)
					tab[i]=tab_pom[i];
				else
					tab[i]=tab_pom[i+1];
			wielkosc --;
			return 1;
		}else
		{
			return 0;
		}
	}
}
