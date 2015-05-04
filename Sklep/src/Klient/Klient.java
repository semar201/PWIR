package Klient;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.registry.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Obiekty.Constatns;
import Obiekty.Intefejs;

public class Klient {
	public static void main(String[] args) {
		try {
			while(true)
			{
				Registry registry = LocateRegistry.getRegistry("127.0.0.1",
						Constatns.RMI_REGISTRY_PORT);
				Remote remote;
				System.out.println("logowanie : 1-klient 2-admin");
				do{
				char ch=(char) System.in.read();
				if(ch=='1')
				{
					remote = registry.lookup("rmi://localhost//KlientUI");
					break;
				}
				else if(ch=='2')
				{
					remote = registry.lookup("rmi://localhost//AdminUI");
					break;
				} 
				}while(true);
				
				System.setSecurityManager(new RMISecurityManager());
				Scanner scanner = new Scanner(System.in);
				if (remote instanceof Intefejs ) {
					
					Intefejs mojeUI;
					mojeUI = (Intefejs) remote;
					System.out.println(mojeUI.witaj());
					scanner.nextLine();
					while(true)
					{
						System.out.print("> ");
						String line=scanner.nextLine();
						if(line.equals("logout"))
							break; 
						else
						if(line.equals("baza"))
						{
							int id=0;
							System.out.println("Zawartosc bazy:");
							while(true)
							{
								String wynik=mojeUI.command("baza "+id);
								if(wynik==null)
									break;
								System.out.println(id+ " "+wynik);
								id++;
								
							}
						}
						else
						{
							String wynik=mojeUI.command(line);
							if(wynik!=null)
								System.out.println(wynik);
						}
						
					}
					
				}else
					System.out.println("ERROR ?");
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}
}