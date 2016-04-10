package hit.processes;

public class ProcessCycles 
{
	private java.util.List<ProcessCycle> processCycles;
	
	public ProcessCycles(java.util.List<ProcessCycle> processCycles)
	{
		this.processCycles = processCycles;
	}

	public java.util.List<ProcessCycle> getProcessCycles() {
		return processCycles;
	}

	public void setProcessCycles(java.util.List<ProcessCycle> processCycles) {
		this.processCycles = processCycles;
	}
	
	public java.lang.String toString()
	{
		
		return processCycles.toString();
	}
}
