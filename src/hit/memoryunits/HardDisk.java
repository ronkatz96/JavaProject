package hit.memoryunits;
import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import hit.util.HardDiskInputStream;
import hit.util.HardDiskOutputStream;
import hit.util.MMULogger;

@SuppressWarnings("serial")
public class HardDisk implements Serializable{

	static final String DEFAULT_FILE_NAME = "hdPages.bin";
	static final int _SIZE = 500;
	private static final HardDisk instance = new HardDisk();
	private static Map<Long,Page<byte[]>> hdpages;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HardDisk()
	{

		try 
		{
			ReadHd();
		} 
		catch (IOException | ClassNotFoundException e) 
		{

		}
		if (hdpages == null)
		{
			hdpages = new ConcurrentHashMap<Long,Page<byte[]>>(_SIZE);
			for(int i=0 ; i<_SIZE;i++)
			{
				hdpages.put((long) i, new Page((long) i, i));
			}
			WriteHd();
		}	
	}
	
	private synchronized void WriteHd()
	{

		HardDiskOutputStream output;
		try 
		{
			output = new HardDiskOutputStream(new FileOutputStream(DEFAULT_FILE_NAME));
			output.writeAllPages(hdpages);
			output.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public synchronized void ReadHd() throws ClassNotFoundException, FileNotFoundException, IOException
	{		
		HardDiskInputStream input;
		input = new HardDiskInputStream(new FileInputStream(DEFAULT_FILE_NAME));
		if (input != null)
		{
			HardDisk.hdpages = input.readAllPages();
			input.close();
		}
	}
	public synchronized static HardDisk getInstance()
	{	
		return instance;
	}
	
	public String getFileName()
	{	
		return DEFAULT_FILE_NAME;
	}
	
	
	public synchronized Page<byte[]> pageFault(Long pageId) throws FileNotFoundException, IOException
	{	
		Page<byte[]> pageToReturn = hdpages.get(pageId);
		MMULogger.getInstance().write(String.format("PF: %d", pageId), Level.INFO);
		return pageToReturn;
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Long moveToRamId)throws FileNotFoundException, IOException
	{	
		hdpages.put(moveToHdPage.getPageId(), moveToHdPage);
		WriteHd();
		Page<byte[]> pageToReturn = hdpages.get(moveToRamId);
		//String commandToWrite = String.format("PR: MTH %d MTR %d",moveToHdPage.getPageId(), moveToRamId );
		//MMULogger.getInstance().write(commandToWrite, Level.INFO);
		return pageToReturn;
	}
}
