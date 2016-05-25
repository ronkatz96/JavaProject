package hit.login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import hit.util.AuthenticationInputStream;


public class Authentication {

	static final String DEFAULT_FILE_NAME = "resources\\users\\users.txt";
	static final int _SIZE = 500;
	private static LinkedHashMap<String, String> users;
	
	
	public Authentication() {
		
	}
	
	public static boolean authenticate(String user, String password) {
		
		try 
		{
			ReadUsers();
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			System.out.println("there are no users");
		}
		for (Entry<String, String> entry: users.entrySet()){
			if ((entry.getKey() == user) && (entry.getValue() == password))
				return true;
		}
		return false;
	}
	
	private synchronized static void ReadUsers() throws ClassNotFoundException, FileNotFoundException, IOException
	{		
		AuthenticationInputStream input;
		input = new AuthenticationInputStream(new FileInputStream(DEFAULT_FILE_NAME));
		if (input != null)
		{
			users = input.readAllPages();
			input.close();
		}
	}
}

