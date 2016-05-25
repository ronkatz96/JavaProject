package hit.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;

public class AuthenticationOutputStream extends ObjectOutputStream{

public AuthenticationOutputStream(OutputStream output) throws IOException {
		
		super(output);
	}

	public void writeAllUsers(LinkedHashMap<String,String> users) throws IOException{
		
		writeObject(users);
	}
}
