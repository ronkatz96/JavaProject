package hit.memoryunits;
import java.io.*;
import java.util.Map;
import hit.memoryunits.MemoryManagementUnit;

public class HardDisk {

	static final String DEFAULT_FILE_NAME = "hdPages.txt";
	static final int _SIZE = 1000;
	private static final HardDisk instance = new HardDisk();
	private Map<Long,Page<byte[]>> map;
	
	
	private HardDisk(){
		
	}
	
	private void writeHd() throws FileNotFoundException, IOException{
		
		
	}
	
	public static HardDisk getInstance(){
		
		return instance;
	}
	
	public Page<byte[]> pageFault(Long pageId) throws FileNotFoundException, IOException{
		
		map.
		return null;
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Long moveToRamId)throws FileNotFoundException, IOException{
		
		return null;
	}
}
