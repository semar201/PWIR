package Serwer;

public class Przedmiot {

	double cena;
	String opis;
	String nazwa;
	
	Przedmiot()
	{
		cena=0.0f;
		opis="Bez_opisu";
		nazwa="Bez_nazwy";
	}
	public String toString()
	{
		return ("Nazwa: "+nazwa+"   Cena: "+cena+"   Opis: "+opis);
	}
//	Przedmiot(Przedmiot p)
//	{
//		this.cena=p.cena;
//		this.opis=p.opis;
//		this.nazwa=p.nazwa;
//	}
}
