package hit.processes;

public class RunConfiguration 
{
	private java.util.List<ProcessCycles> processesCycles;
	
	public RunConfiguration(java.util.List<ProcessCycles> processCycles)
	{
		this.setProcessesCycles(processCycles);
	}

	public java.util.List<ProcessCycles> getProcessesCycles() {
		return processesCycles;
	}


	public void setProcessesCycles(java.util.List<ProcessCycles> processesCycles) {
		this.processesCycles = processesCycles;
	}
	
	public java.lang.String toString()
	{
		//TODO what should be returned here?
		return processesCycles.toString();
	}
}
