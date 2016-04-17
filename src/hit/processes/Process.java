package hit.processes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Process implements Runnable {

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
		List<ProcessCycle> pages = processCycles.getProcessCycles();
		
		for (int i=0;i<processCycles.size();i++)
		{
			try {
				mmu.getPages((Long[])pages.get(i).getPages().toArray());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
