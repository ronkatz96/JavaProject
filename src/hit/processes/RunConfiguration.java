package hit.processes;

public class RunConfiguration 
{
	private java.util.List<ProcessCycles> processCycles;
	
	public RunConfiguration(java.util.List<ProcessCycles> processCycles)
	{
		this.processCycles = processCycles;
	}

	public java.util.List<ProcessCycles> getProcessCycles() {
		return processCycles;
	}

	public void setProcessCycles(java.util.List<ProcessCycles> processCycles) {
		this.processCycles = processCycles;
	}
	
	public java.lang.String toString()
	{
		//TODO what should be returned here?
		return "Something";
	}
}
