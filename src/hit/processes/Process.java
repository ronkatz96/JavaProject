package hit.processes;

import hit.memoryunits.Page;
import java.io.IOException;
import java.util.List;

public class Process implements Runnable 
{
	private int id;
	private hit.memoryunits.MemoryManagementUnit mmu;
	private ProcessCycles processCycles;
	
	public Process(int id, hit.memoryunits.MemoryManagementUnit mmu, ProcessCycles processCycles)
	{
		this.setId(id);
		this.mmu = mmu;
		this.processCycles = processCycles;
	}
	
	@Override
	public void run() 
	{
		runContent();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	private synchronized void runContent()
	{
		
		
		for(ProcessCycle currentCycle : processCycles.getProcessCycles())
		{
			Long[] longArray = new Long[currentCycle.getPages().size()];
			try {
				int i;
				Page<byte[]>[] newPages = mmu.getPages(currentCycle.getPages().toArray(longArray));
				List<byte[]> currentBytes = currentCycle.getData();
				for(i = 0; i < newPages.length;i++)
				{
					byte[] currentData = currentBytes.get(i);
					System.out.println(currentData.toString());
					newPages[i].setContent(currentData);
				}
				
				Thread.sleep(currentCycle.getSleepMs());
			} catch (IOException | InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
}
