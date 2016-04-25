package hit.processes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Process implements Runnable {

	private int id;
	private hit.memoryunits.MemoryManagementUnit mmu;
	private ProcessCycles processCycles;
	private Object lock = new Object();
	
	
	
	public Process(int id, hit.memoryunits.MemoryManagementUnit mmu, ProcessCycles processCycles)
	{
		this.setId(id);
		this.mmu = mmu;
		this.processCycles = processCycles;
	}
	@Override
	public void run() 
	{
		
		List<ProcessCycle> pages = processCycles.getProcessCycles();
		
		for (int i=0;i<processCycles.size();i++)
		{
			try {
				List<Long> pagesArr = pages.get(i).getPages();
				
				Long[] a = new Long[pages.size()];
				
				synchronized(mmu)
				{mmu.getPages(pagesArr.toArray(a));}
					Thread.sleep((long)pages.get(i).getSleepMs());
					
					
					
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	
		
		System.out.println("This is thread number: " + id);

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
