package Serwer;

import java.rmi.RemoteException;

import Obiekty.Intefejs;

import java.text.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Klient_ob_impl extends UnicastRemoteObject implements Intefejs {
		private static final long serialVersionUID = 1L;
		Baza_przedmiotow baza;
		public Klient_ob_impl(Baza_przedmiotow baza) throws RemoteException {
			this.baza=baza;
		}
		public String witaj() throws RemoteException
		{
			System.out.println("Witamy klienta");
			return "Witaj kliencie";
		}
		public String command(String cmd) throws RemoteException
		{

			if (cmd.length() > 4 && cmd.substring(0, 4).equals("baza")) {
				int id = Integer.parseInt(cmd.substring(5, cmd.length()));
				if (id >= 0 && id < baza.wielkosc)
					return (baza.tab[id].toString());
			}else
			if (cmd.length() > 3 && cmd.substring(0, 3).equals("kup")) {
				int id = Integer.parseInt(cmd.substring(4, cmd.length()));
				if (id >= 0 && id < baza.wielkosc)
				{
					if(baza.usun(id)==1)
					{
						System.out.println("Kupiono ID:"+id);
						return "Kupiono";
					}
				}
			}
			return null;
		}
	}