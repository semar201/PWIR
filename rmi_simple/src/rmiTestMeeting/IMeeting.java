package rmiTestMeeting;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Date;

public interface IMeeting extends Remote {
	public Date getDate() throws RemoteException;
	public void setDate(Date date)throws RemoteException;
}