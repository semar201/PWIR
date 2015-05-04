package rmiTestServer;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.*;

import rmiTestMeeting.Constatns;

public class MeetingServer {
	public static void main(String[] args) throws Exception {
		System.out.println("Serwer start! ");
		System.setSecurityManager(new RMISecurityManager());
		MeetingImpl impl = new MeetingImpl();
		Registry registry = LocateRegistry
				.createRegistry(Constatns.RMI_REGISTRY_PORT);
//		registry.rebind("rmi://localhost//Meeting", impl);
		registry.rebind("rmi://localhost//NazwiskoStudenta", impl);
		System.out.println("Serwer wystartowal !!! ");
	}
}