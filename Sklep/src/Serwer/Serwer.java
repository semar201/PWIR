package Serwer;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.*;

import Obiekty.Constatns;

public class Serwer {
	public static void main(String[] args) throws Exception {
		System.out.println("Serwer start! ");
		Baza_przedmiotow baza=new Baza_przedmiotow();

		System.out.println("Generuje baze");
		baza.dodaj("Laptop 1", 2269.99, "Super laptop A");
		baza.dodaj("Laptop 1", 2269.99, "Super laptop B");
		baza.dodaj("Laptop 2", 3469.99, "Super super laptop C");
		baza.dodaj("Laptop 2", 3469.99, "Super super laptop D");
		baza.dodaj("Laptop 4", 6539.99, "Super epicki laptop E");
		baza.dodaj("Laptop 9", 9999.99, "Kosmiczny laptop F");
		System.out.println("baza gotowa! ");
		
		System.setSecurityManager(new RMISecurityManager());
		Klient_ob_impl Kimpl = new Klient_ob_impl(baza);
		Admin_ob_impl Aimpl = new Admin_ob_impl(baza);
		Registry registry = LocateRegistry
				.createRegistry(Constatns.RMI_REGISTRY_PORT);
		registry.rebind("rmi://localhost//KlientUI", Kimpl);
		registry.rebind("rmi://localhost//AdminUI", Aimpl);
		System.out.println("Serwer wystartowal !!! ");
	}
}