package hit.util;

import java.io.IOException;
import java.io.OutputStream;

import hit.memoryunits.Page;

public class HardDiskOutputStream extends java.io.ObjectOutputStream{

	public HardDiskOutputStream(OutputStream output) throws IOException {
			
	}

	public void writeAllPages(java.util.Map<Long,Page<byte[]>> hd){
		
	}
}
