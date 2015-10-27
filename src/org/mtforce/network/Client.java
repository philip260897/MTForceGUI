package org.mtforce.network;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client 
{
	private Socket socket;
	
	private ObjectInputStream oi;
	private ObjectOutputStream oo;
	
	public Client(String ip, int port)
	{
		try 
		{
			socket = new Socket(InetAddress.getByName(ip), port);
			oi = new ObjectInputStream(socket.getInputStream());
			oo = new ObjectOutputStream(socket.getOutputStream());

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
	
	public void write(CmdPackage pkg) throws Exception
	{
		oo.writeObject(pkg);
		oo.flush();
	}
	
	public InfoPackage read() throws Exception
	{
		InfoPackage dd = (InfoPackage) oi.readObject();
		return dd;
	}
}
