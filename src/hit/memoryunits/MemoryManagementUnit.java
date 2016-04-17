package hit.memoryunits;
import hit.memoryunits.*;

import java.io.FileNotFoundException;
import java.io.IOException;

//import java.lang.reflect.Array;
//import java.util.Collection;
//import java.util.List;

import hit.algorithm.IAlgoCache;

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

	public RAM getRam() 
	{
		return ram;
	}
	
	public void setRam(RAM ram) 
	{
		this.ram = ram;
	}
	
	
	@SuppressWarnings("unchecked")
	public Page<byte[]>[] getPages(Long[] pageIds) throws FileNotFoundException, IOException
	{
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
					Page<bytep[]> pageToMoveToHD = ram.getPage(pageToFlush);
					Page<byte[]> pageToMoveToRam = hardDrive.pageReplacement(pageToMoveToHD, pageIds[i]);
					ram.removePage(pageToMoveToRam);
					ram.addPage(pageToMoveToRam);
				}
			}
			pagesToReturn[i] = ram.getPage(pageIds[i]);
		}
	}
}
