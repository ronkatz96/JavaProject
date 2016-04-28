package hit.driver;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import hit.algorithm.*;
import hit.memoryunits.*;
import hit.processes.*;
import hit.processes.Process;

public class MMUDriver 
{ 
	private static int appIds;
	private static final String CONFIG_FILE_NAME = "Configuration.json";
	final String DEFAULT_FILE_NAME;
	private static ExecutorService executor;
	private static hit.memoryunits.MemoryManagementUnit mmu;
	
	public MMUDriver()
	{
		
		DEFAULT_FILE_NAME = HardDisk.getInstance().getFileName();
		
	}
	public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException
	{
		
		mmu = new MemoryManagementUnit(5, new LRUAlgoCacheImpl<Long, Long>(5));
		
			RunConfiguration runConfig = readConfigurationFile(); //readConfigurationFile() is a MMUDriver static method.
		
		List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
		List<hit.processes.Process> processes = createProcesses(processCycles,mmu);
		runProcesses(processes);
		//Currently this method, shutDown, doesn't do anything. this is a place holder.
		mmu.shutDown();
		
	 
	}
	private static List<Process> createProcesses(List<ProcessCycles> processCycles, MemoryManagementUnit mmu) 
	{
		List<Process> processesToReturn = new ArrayList<Process>();
		appIds = 0;
		for(ProcessCycles element : processCycles)
		{
			processesToReturn.add(new Process(appIds, mmu, element));
			appIds++;
		}
		return processesToReturn;
		
	}
	private static void runProcesses(List<Process> applications) {
		executor = Executors.newCachedThreadPool();
		for (Process element: applications)
		{
			executor.execute(element);
		}
		executor.shutdown();
		while(!executor.isTerminated());
		System.out.println("All Threads have finished Executing!");
	}
	private static RunConfiguration readConfigurationFile() throws UnsupportedEncodingException, IOException 
	{
		RunConfiguration configToReturn = null;
		try
		{
			configToReturn = new Gson().fromJson(new JsonReader(new FileReader(CONFIG_FILE_NAME)), RunConfiguration.class);
		}
		finally
		{
			
		}
		return configToReturn;
	}

	
	
}
