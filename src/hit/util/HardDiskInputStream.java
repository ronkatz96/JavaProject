package hit.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import hit.memoryunits.Page;

public class HardDiskInputStream extends ObjectInputStream{

	HardDiskInputStream(ObjectInputStream in) throws IOException{
		
		super(in);
	}
	
	public Map<Long,Page<byte[]>> readAllPages() throws ClassNotFoundException, IOException{
		
		Map<Long, Page<byte[]>> map = (Map<Long, Page<byte[]>>)readObject();
		return map;
	}
}
