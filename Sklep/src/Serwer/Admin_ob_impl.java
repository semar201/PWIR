package Serwer;

import java.rmi.RemoteException;

import Obiekty.Intefejs;

import java.text.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Admin_ob_impl extends UnicastRemoteObject implements Intefejs {
	private static final long serialVersionUID = 2L;
	Baza_przedmiotow baza;

	public Admin_ob_impl(Baza_przedmiotow baza) throws RemoteException {
		this.baza = baza;
	}

	public String witaj() throws RemoteException {
		System.out.println("Witamy admina");
		return "Witaj adminie";
	}

	public String command(String cmd) {
		if (cmd.length() > 4 && cmd.substring(0, 4).equals("baza")) {
			int id = Integer.parseInt(cmd.substring(5, cmd.length()));
			if (id >= 0 && id < baza.wielkosc)
				return (baza.tab[id].toString());
		}else
		if (cmd.length() > 4 && cmd.substring(0, 4).equals("usun")) {
			int id = Integer.parseInt(cmd.substring(5, cmd.length()));
			if (id >= 0 && id < baza.wielkosc)
			{
				if(baza.usun(id)==1)
				{
					System.out.println("Usunieto ID:"+id);
					return "Usunieto";
				}
			}
		}else
		if (cmd.length() > 5 && cmd.substring(0, 5).equals("dodaj")) {
			
			if(!cmd.contains(" "))
				return null;
			cmd=cmd.substring(cmd.indexOf(" ")+1,cmd.length() );
			String nazwa=cmd.substring(0,cmd.indexOf(" "));
			
			if(!cmd.contains(" "))
				return null;
			cmd=cmd.substring(cmd.indexOf(" ")+1,cmd.length() );
			Double cena=Double.parseDouble( cmd.substring(0,cmd.indexOf(" ")));

			if(!cmd.contains(" "))
				return null;
			cmd=cmd.substring(cmd.indexOf(" ")+1,cmd.length() );
			String opis=cmd;
			
			baza.dodaj(nazwa, cena, opis);
			
		}

		return null;
	}

}