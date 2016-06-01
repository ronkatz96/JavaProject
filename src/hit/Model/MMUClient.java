package hit.Model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MMUClient {

	public MMUClient() {
		try {
			InetAddress address = InetAddress.getByName("136.243.56.32");
			Socket myServer = new Socket(address, 1234);
			ObjectOutputStream output = new ObjectOutputStream(myServer.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(myServer.getInputStream());

			String messageFromServer = (String) input.readObject();
			System.out.println("message from server: " + messageFromServer);
			output.writeObject("networking is so simple in java");
			output.flush();

			output.close();
			input.close();
			myServer.close();

		} catch (Exception e) {
			System.out.println("Tired of this shit");
			e.printStackTrace();
		}
	}
}
