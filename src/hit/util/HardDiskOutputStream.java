package hit.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;

import hit.memoryunits.Page;

public class HardDiskOutputStream extends ObjectOutputStream{

	public HardDiskOutputStream(OutputStream output) throws IOException {
		
		super(output);
	}

	public void writeAllPages(Map<Long,Page<byte[]>> hd) throws IOException{
		
		writeObject(hd);
		
	}
}