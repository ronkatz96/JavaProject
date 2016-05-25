package hit.applicationservice;

import java.net.Socket;
import java.util.LinkedHashMap;

public class MMULogService {

	static final String DEFAULT_FILE_NAME = "resources\\logcache\\clientsLog.txt";
	static final int _SIZE = 500;
	private static LinkedHashMap<Long, String> log;
	
	public MMULogService(Socket socket, String filName){
		
	}
}
