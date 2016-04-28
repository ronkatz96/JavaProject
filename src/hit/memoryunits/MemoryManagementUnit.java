package hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.IOException;
import hit.algorithm.IAlgoCache;
import hit.processes.Lock;

public class MemoryManagementUnit {
	
	private IAlgoCache<Long, Long> algo;
	private RAM ram;
	private Lock lock = new Lock();
	
	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Long> algo) 
	{
		this.ram = new RAM(ramCapacity);
		this.algo = algo;
	}
	
	public IAlgoCache<Long, Long> getAlgo()
	{
		return algo;
	}
	
	public void setAlgo(IAlgoCache<Long, Long> algo)
	{	
		this.algo = algo;
	}

	public synchronized RAM getRam() 
	{
		return ram;
	}
	
	public synchronized void setRam(RAM ram) 
	{
		this.ram = ram;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized Page<byte[]>[] getPages(Long[] pageIds) throws FileNotFoundException, IOException
	{
		synchronized(lock){
			if(lock.tryLock()){
				
			try{
		HardDisk hardDrive = HardDisk.getInstance();
		Page<byte[]> [] pagesToReturn = new Page[pageIds.length];
		
		for (int i=0;i<pageIds.length;i++)
		{
			if (algo.getElement(pageIds[i]) == null)
			{
				if (!ram.isRamFull())
				{
					
					ram.addPage(hardDrive.pageFault(pageIds[i]));
					algo.putElement(pageIds[i], pageIds[i]);
				}
				else
				{
					
					Long pageToFlush = algo.putElement(pageIds[i],pageIds[i]);
					Page<byte[]> pageToMoveToHD = ram.getPage(pageToFlush);
					Page<byte[]> pageToMoveToRam = hardDrive.pageReplacement(pageToMoveToHD, pageIds[i]);
					ram.removePage(pageToMoveToHD);
					ram.addPage(pageToMoveToRam);
				}
			}
		
			pagesToReturn[i] = ram.getPage(pageIds[i]);
		}
		return pagesToReturn;
	}
	finally{		
		lock.unlock();}
			}
		}
		return null;
	}
}
		
	

