package hit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;

import hit.memoryunits.Page;

public class HardDiskInputStream extends ObjectInputStream{
	
	public HardDiskInputStream(InputStream in) throws IOException{
		
		super(in);
	}
	
	public Map<Long,Page<byte[]>> readAllPages() throws ClassNotFoundException, IOException{
	
		Map<Long, Page<byte[]>>allPages = (Map<Long, Page<byte[]>>)readObject();
		return allPages;
	}
}

//herro, is it me you're looking for?
