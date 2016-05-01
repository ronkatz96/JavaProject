package hit.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;



public class MMULogger 
{
	class OnlyMessageFormatter extends java.util.logging.Formatter
	{

		@Override
		public String format(LogRecord arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	static String DEFAULT_FILE_NAME;
	final static MMULogger instance = new MMULogger();
	
	static MMULogger getInstance()
	{
		return instance;
	}
	
	void write(String command, Level level) 
	{
		//TODO add content.
	}
	
}
