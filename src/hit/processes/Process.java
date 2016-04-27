package hit.processes;

import java.io.FileNotFoundException;
import hit.memoryunits.Page;
import java.io.IOException;
import java.util.Arrays;

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
	public synchronized void run() 
	{
		try {
			runContent();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private void runContent() throws FileNotFoundException, IOException
	{
		byte[] bytes = new byte[8];
		Arrays.fill( bytes, (byte) 0 );
		for(ProcessCycle currentCycle : processCycles.getProcessCycles())
		{
			Long[] longArray = new Long[currentCycle.getPages().size()];
			try 
			{
				int i;
				Page<byte[]>[] newPages = mmu.getPages(currentCycle.getPages().toArray(longArray));
				for(i = 0; i < newPages.length;i++)
				{
					newPages[i].setContent(bytes);
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
	

