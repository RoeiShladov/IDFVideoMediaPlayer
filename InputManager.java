package SocketServerClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.*;

public class InputManager extends Thread 
{// TODO: initialize socket
	private Socket incomingSocket;

	public InputManager(Socket _in) 
	{
		incomingSocket = _in;
	}

	public void run()
	{
		String clientSentence, capitalizedSentence;
		try {
				BufferedReader inFromClient = new BufferedReader( new InputStreamReader(incomingSocket.getInputStream() ) );
				ObjectOutputStream outToClient = new ObjectOutputStream( incomingSocket.getOutputStream() );
				while (true)
				{// TODO: change to stream reading packet by packet
					clientSentence = inFromClient.readLine(); // TODO: read datagram
															// packet instead of
															// line
					capitalizedSentence = clientSentence.toUpperCase() + "\n";
					outToClient.writeBytes( capitalizedSentence );
				}
			}
			catch ( IOException e )// TODO: add socket timeout exception and additional if needed
			{
				System.out.println("EROR Occured!!");
			}
			finally
			{
				// to do: close socket
			}
	}
}
