package rmiTestClient;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.registry.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rmiTestMeeting.Constatns;
import rmiTestMeeting.IMeeting;

public class MeetingClient {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd-MM-yyyy");
	public static void main(String[] args) {
		try {
			System.out.println("Client start!");
			System.setSecurityManager(new RMISecurityManager());
			Registry registry = LocateRegistry.getRegistry("127.0.0.1",
					Constatns.RMI_REGISTRY_PORT);
			Remote remote = registry.lookup("rmi://localhost//NazwiskoStudenta");
			Date date = null;
			IMeeting meeting;
			System.out.println("Client wystartowal !!!");
			if (remote instanceof IMeeting) {
				meeting = (IMeeting) remote;
				date = meeting.getDate();
				System.out.println("Data z systemu zdalnego: " + date);
				System.out.print("\n\nPodaj Date (dzien-miesiac-rok):");
				BufferedReader bis = new BufferedReader(new InputStreamReader(
						System.in));
				String line = bis.readLine();
				
				date = simpleDateFormat.parse(line);
				
				meeting.setDate(date);
				System.out.println("\n\nDate zmieniono !!!!\n\n");
				System.out.println("Data z systemu zdalnego: "
						+ meeting.getDate() + "\n\n");
				bis.readLine();
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}
}