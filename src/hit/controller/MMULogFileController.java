package hit.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MMULogFileController {

	@SuppressWarnings("resource")
	public void start(){
		Socket client = null;
		try{
			ServerSocket server = new ServerSocket(1234);
			server.setSoTimeout(5000);
			client = server.accept();
			ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(client.getInputStream());
			output.writeObject("you are connected to the server");
			output.flush();
			String message = (String)input.readObject();
			System.out.println("message from client is: " + message);
			output.writeObject("bbz i will luv u 4 ever");
			output.flush();
			
			output.close();
			input.close();
			client.close();
		} catch (Exception e) {
			System.out.println("Tired of this shit");
			e.printStackTrace();
		}
		finally
		{
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
