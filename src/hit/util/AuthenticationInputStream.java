package hit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.LinkedHashMap;

public class AuthenticationInputStream extends ObjectInputStream{
	
	public AuthenticationInputStream(InputStream in) throws IOException{
		
		super(in);
	}
	
	public LinkedHashMap<String, String> readAllUsers() throws ClassNotFoundException, IOException{
	
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, String>allUsers = (LinkedHashMap<String, String>)readObject();
		return allUsers;
	}
}
