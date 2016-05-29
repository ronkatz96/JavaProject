package hit.login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class Authentication {

	static final String DEFAULT_FILE_NAME = "resources\\users\\users.txt";
	private static LinkedHashMap<String, String> users;

	public Authentication() {
		
		try 
		{
			BufferedReader userLog = new BufferedReader(new FileReader(DEFAULT_FILE_NAME));
			while(userLog.readLine() != null){
				String line = userLog.readLine();
				String [] lineArr = line.split(" ");
				users.put(lineArr[0], lineArr[1]);
			}
			userLog.close();
		} 
		catch (IOException e) 
		{
			System.out.println("There are no users");
		}
	}

	public static boolean authenticate(String user, String password) {
		
			if(users.get(user).equals(password)){
				return true;
			}
			else{
				return false;
			}
	}
}
