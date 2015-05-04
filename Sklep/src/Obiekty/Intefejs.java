package Obiekty;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Intefejs extends Remote { 
	public String witaj() throws RemoteException;
	public String command(String cmd) throws RemoteException;
}