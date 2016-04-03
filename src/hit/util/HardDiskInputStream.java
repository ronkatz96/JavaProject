package hit.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import hit.memoryunits.Page;

public class HardDiskInputStream extends ObjectInputStream{

	HardDiskInputStream(ObjectInputStream in) throws IOException{
		
	}
	
	public java.util.Map<Long,Page<byte[]>> readAllPages(){
		
	}
}
