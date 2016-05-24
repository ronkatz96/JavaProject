package hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import hit.algorithm.IAlgoCache;
import hit.util.MMULogger;

public class MemoryManagementUnit {
	
	private IAlgoCache<Long, Long> algo;
	private RAM ram;
	
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
	public synchronized Page<byte[]>[] getPages(Long[] pageIds, boolean[] writePges) throws FileNotFoundException, IOException
	{		
		HardDisk hardDrive = HardDisk.getInstance();
		Page<byte[]> [] pagesToReturn = new Page[pageIds.length];
		
		for (int i=0;i<pageIds.length;i++)
		{
			if (algo.getElement(pageIds[i]) == null)
			{
				
				if (!ram.isRamFull())
				{
					Page<byte[]> pageToAdd = hardDrive.pageFault(pageIds[i]);
					if (pageToAdd.getContent() == null)
							writePges[i] = true;
					else
							writePges[i] = false;
					ram.addPage(pageToAdd);
					algo.putElement(pageIds[i], pageIds[i]);
					
				}
				else
				{
					
					Long pageToFlush = algo.putElement(pageIds[i],pageIds[i]);
					Page<byte[]> pageToMoveToHD = ram.getPage(pageToFlush);
					Page<byte[]> pageToMoveToRam = hardDrive.pageReplacement(pageToMoveToHD, pageIds[i]);
					String commandToWrite = String.format("PR: MTH %d MTR %d",pageToMoveToHD.getPageId(), pageIds[i] );
					MMULogger.getInstance().write(commandToWrite, Level.INFO);
					ram.removePage(pageToMoveToHD);
					ram.addPage(pageToMoveToRam);
				}
			}
		
			pagesToReturn[i] = ram.getPage(pageIds[i]);
		}
		
		return pagesToReturn;
	}
	
	public void shutDown()
	{
		HardDisk.getInstance().writeRamToDisk(ram.getPages());
	}
}
		
	

