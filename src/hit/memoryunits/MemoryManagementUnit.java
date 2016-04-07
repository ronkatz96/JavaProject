package hit.memoryunits;

//import java.lang.reflect.Array;
//import java.util.Collection;
//import java.util.List;

import hit.algorithm.IAlgoCache;

public class MemoryManagementUnit {
	
	private IAlgoCache<Page, Long> algo;
	private RAM ram;
	
	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Page, Long> algo) 
	{
		this.ram = new RAM(ramCapacity);
		this.algo = algo;
	}
	
	public IAlgoCache<Page, Long> getAlgo()
	{
		return algo;
	}
	
	public void setAlgo(IAlgoCache<Page, Long> algo)
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
		Page<byte[]> [] arr = new Page[pageIds.length];
		for (int i=0;i<pageIds.length;i++){
			arr[i] = ram.getPage(pageIds[i]);
		}
		return arr;
	}
}
