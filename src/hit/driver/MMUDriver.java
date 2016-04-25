package hit.driver;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import hit.algorithm.*;
import hit.memoryunits.*;
import hit.processes.*;
import hit.processes.Process;

public class MMUDriver 
{ //TODO methods here are stubs! need to be implemented.
	private static int appIds;
	private static final String CONFIG_FILE_NAME = "Configuration.json";
	final String DEFAULT_FILE_NAME;
	private static Executor executor;
	private static hit.memoryunits.MemoryManagementUnit mmu;
	
	public MMUDriver()
	{
		//TODO initialize finals here.
		DEFAULT_FILE_NAME = HardDisk.getInstance().getFileName();
		
	}
	public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException
	{
		// TODO Problematic!
		mmu = new MemoryManagementUnit(5, new LRUAlgoCacheImpl<Long, Long>(5));
		synchronized(mmu){
			RunConfiguration runConfig = readConfigurationFile(); //readConfigurationFile() is a MMUDriver static method.
		
		List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
		List<hit.processes.Process> processes = createProcesses(processCycles,mmu);
		runProcesses(processes);}
	 
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
		executor = Executors.newFixedThreadPool(4);
		
		for (Process element: applications)
		{
			executor.execute(element);
			
		}
		
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
