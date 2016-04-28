package hit.processes;

import java.io.FileNotFoundException;
import hit.processes.Lock;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

public class Process implements Runnable {

	private int id;
	private hit.memoryunits.MemoryManagementUnit mmu;
	private ProcessCycles processCycles;
	private Lock lock = new Lock();
	private long sleepMs;
	
	
	
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
		Thread.State state = Thread.currentThread().getState();
		System.out.println(Thread.currentThread().getName());
		System.out.println("state = " + state);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private synchronized void runContent()
	{
		synchronized(lock){
		lock.lock();
		List<ProcessCycle> pages = processCycles.getProcessCycles();
		
		for (int i=0;i<processCycles.size();i++)
		{
			
				try 
				{
					List<Long> pagesArr = pages.get(i).getPages();
					Long[] a = new Long[pages.size()];
					mmu.getPages(pagesArr.toArray(a));
					sleepMs = (long)pages.get(i).getSleepMs();
					try {
						Thread.sleep(sleepMs);
					} catch (InterruptedException e) {
						
					}
				} 
				catch (FileNotFoundException e) 
				{	
					e.printStackTrace();
				}	 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			
		}
		lock.unlock();}
	}
	
}
