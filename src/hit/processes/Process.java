package hit.processes;

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
	public void run() {
		// TODO Auto-generated method stub

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
