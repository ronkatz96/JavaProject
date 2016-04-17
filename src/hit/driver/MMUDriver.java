package hit.driver;
import java.util.List;
import hit.algorithm.*;
import hit.memoryunits.*;
import hit.processes.*;
import hit.processes.Process;

public class MMUDriver 
{ //TODO methods here are stubs! need to be implemented.
	private static int appIds;
	private static final String CONFIG_FILE_NAME = "Configuration.JSON";
	final String DEFAULT_FILE_NAME;
	
	public MMUDriver()
	{
		//TODO initialize finals here.
		DEFAULT_FILE_NAME = HardDisk.getInstance().getFileName();
		
	}
	public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException
	{
		// TODO Problematic!
		hit.memoryunits.MemoryManagementUnit mmu = new MemoryManagementUnit(5, new LRUAlgoCacheImpl<Long, Long>(5));
		RunConfiguration runConfig = readConfigurationFile(); //readConfigurationFile() is a MMUDriver static method.
		List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
		List<hit.processes.Process> processes = createProcesses(processCycles,mmu);
		runProcesses(processes);
	 
	}
	private static List<Process> createProcesses(List<ProcessCycles> processCycles, MemoryManagementUnit mmu) {
		// TODO Auto-generated method stub
		return null;
	}
	private static void runProcesses(List<Process> applications) {
		// TODO Auto-generated method stub
		
	}
	private static RunConfiguration readConfigurationFile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
