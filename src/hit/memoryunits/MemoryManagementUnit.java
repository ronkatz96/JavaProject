package hit.memoryunits;

//import java.lang.reflect.Array;
//import java.util.Collection;
//import java.util.List;

import hit.algorithm.IAlgoCache;

public class MemoryManagementUnit {
	
	private IAlgoCache<Long, Page<byte[]>> algo;
	private RAM ram;
	
	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Page<byte[]>> algo) 
	{
		this.ram = new RAM(ramCapacity);
		this.algo = algo;
	}
	
	public IAlgoCache<Long, Page<byte[]>> getAlgo()
	{
		return algo;
	}
	
	public void setAlgo(IAlgoCache<Long, Page<byte[]>> algo)
	{	
		this.algo = algo;
	}

	public RAM getRam() 
	{
		return ram;
	}
	
	public void setRam(RAM ram) 
	{
		this.ram = ram;
	}
	
	public Page<byte[]>[] getPages(Long[] pageIds)
	{
		@SuppressWarnings("unchecked")
		Page<byte[]> [] arr = new Page[pageIds.length];
		for (int i=0;i<pageIds.length;i++){
			if (ram.getPage(pageIds[i]) == null){
				
			}
			else{
				arr[i] = ram.getPage(pageIds[i]);
			}
		}
		return arr;
	}
}
