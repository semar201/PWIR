package rmiTestServer;

import java.rmi.RemoteException;

import rmiTestMeeting.IMeeting;

import java.text.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class MeetingImpl extends UnicastRemoteObject implements IMeeting {
	private static final long serialVersionUID = 1L;
	private Date date = new Date();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd-MM-yyyy");

	public MeetingImpl() throws RemoteException {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}