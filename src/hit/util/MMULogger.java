package hit.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;



public class MMULogger 
{
	public final static String DEFAULT_FILE_NAME = "log.txt";
	private final static MMULogger instance = new MMULogger();
	private FileHandler handler;
	class OnlyMessageFormatter extends java.util.logging.Formatter
	{
		public OnlyMessageFormatter()
		{
			super();
		}

		@Override
		public String format(final LogRecord arg0) 
		{	
		    StringBuffer message = new StringBuffer(1000);
		    message.append(formatMessage(arg0));
		    message.append(System.getProperty("line.separator"));
		    return message.toString();
		}
	}
	
	private MMULogger()
	{
		try 
		{
			handler = new FileHandler(DEFAULT_FILE_NAME);
			handler.setFormatter(new OnlyMessageFormatter());
		} catch (SecurityException e) 
		{	
			e.printStackTrace();
		} catch (IOException e) 
		{	
			e.printStackTrace();
		}
	}
	
	public static MMULogger getInstance()
	{
		return instance;
	}
	
	public synchronized void write(String command, Level level) 
	{
		LogRecord messageToRecordToFile = new LogRecord(level, command);
		handler.publish(messageToRecordToFile);
		handler.flush();
	}
	
}
