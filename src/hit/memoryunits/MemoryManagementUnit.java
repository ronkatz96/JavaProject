package hit.memoryunits;

//import java.lang.reflect.Array;
//import java.util.Collection;
//import java.util.List;

import hit.algorithm.IAlgoCache;

public class MemoryManagementUnit {
	
	private IAlgoCache<java.lang.Long,java.lang.Long> algo;
	private RAM ram;
	
	public MemoryManagementUnit(int ramCapacity, IAlgoCache<java.lang.Long,java.lang.Long> algo) 
	{
		this.ram = new RAM(ramCapacity);
		this.algo = algo;
	}
	
	public IAlgoCache<java.lang.Long,java.lang.Long> getAlgo()
	{
		return algo;
	}
	
	public RAM getRam() 
	{
		return ram;
	}
	
	public void setRam(RAM ram) 
	{
		this.ram = ram;
	}
	
	public Page<byte[]>[] getPages(java.lang.Long[] pageIds)
	{
		return null;
		
		
	}
}
