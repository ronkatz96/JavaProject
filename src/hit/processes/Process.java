package hit.processes;

import hit.memoryunits.Page;
import hit.util.MMULogger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class Process implements Runnable 
{
	private int id;
	private hit.memoryunits.MemoryManagementUnit mmu;
	private ProcessCycles processCycles;
	/**
	 * Constructor for Process. This constructor doesn't create anything new but rather keeps all of the parameters' references.
	 * @param id - The ID of the Process.
	 * @param mmu - The composite reference to the MMU. 
	 * @param processCycles - The collection of Cycles to be processed.
	 */
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
	
	/** 
	 * @return id - The ID of the current Process.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id - The ID to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * a synchronized method to handle the thread business. goes over each cycle this process holds, gets the requested pages and write into them.
	 * Should be noted that Oria and I bounced some ideas about the implementation of this method.
	 */
	private synchronized void runContent()
	{	
		int cycleSize = 0;
		int i = 0;
		for(ProcessCycle currentCycle : processCycles.getProcessCycles())
		{
			cycleSize = currentCycle.getPages().size();
			try {
				Long[] pageIdsArray = new Long[cycleSize];
				Page<byte[]>[] newPages = mmu.getPages(currentCycle.getPages().toArray(pageIdsArray));
				List<byte[]> currentBytes = currentCycle.getData();
				for(i = 0; i < newPages.length;i++)
				{
					byte[] currentData = currentBytes.get(i);
					System.out.println(Arrays.toString(currentData));
					newPages[i].setContent(currentData);
					String stringToWrite = String.format("GP: P%d %d %s",this.id, i, Arrays.toString(currentData));
					MMULogger.getInstance().write(stringToWrite, Level.INFO);
				}
				
				Thread.sleep(currentCycle.getSleepMs());
				} 
				catch (IOException | InterruptedException e) 
				{
					e.printStackTrace();
				}
		}
	}
	
}
