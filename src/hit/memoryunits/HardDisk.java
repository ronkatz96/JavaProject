package hit.memoryunits;
import java.io.*;
import java.util.Map;
import hit.util.HardDiskInputStream;
import hit.util.HardDiskOutputStream;

public class HardDisk {

	static final String DEFAULT_FILE_NAME = "hdPages.txt";
	static final int _SIZE = 1000;
	private static final HardDisk instance = new HardDisk();;
	private static Map<Long,Page<byte[]>> hdpages;

	
	private HardDisk(){
		try {
			//TODO add logic to create map for the first time and if exists, to load it from hdpages.txt
			ReadHd();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void WriteHd(){
		
		HardDiskOutputStream output;
		try {
			output = new HardDiskOutputStream(new FileOutputStream(DEFAULT_FILE_NAME));
			output.writeAllPages(hdpages);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ReadHd() throws ClassNotFoundException{
		
		HardDiskInputStream input;
		try {
			input = new HardDiskInputStream(new FileInputStream(DEFAULT_FILE_NAME));
			HardDisk.hdpages = input.readAllPages();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static HardDisk getInstance(){
		
		return instance;
	}
	
	public Page<byte[]> pageFault(Long pageId) throws FileNotFoundException, IOException{
		
		return hdpages.get(pageId);
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Long moveToRamId)throws FileNotFoundException, IOException{
		
		hdpages.put(moveToHdPage.getPageId(), moveToHdPage);
		WriteHd();
		return hdpages.get(moveToRamId);
	}
}
