package hit.driver;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonToken;

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
	@SuppressWarnings("null")
	private static List<Process> createProcesses(List<ProcessCycles> processCycles, MemoryManagementUnit mmu) 
	{
		List<Process> processesToReturn = null;
		for(ProcessCycles element : processCycles)
		{
			processesToReturn.add(new Process(appIds, mmu, element));
		}
		return processesToReturn;
		
	}
	private static void runProcesses(List<Process> applications) {
		Executor executor = Executors.newCachedThreadPool();
		
		for (Process element: applications)
		{
			executor.execute(element);
		}
		
	}
	private static RunConfiguration readConfigurationFile() throws UnsupportedEncodingException, IOException 
	{
		RunConfiguration configToReturn = null;
		try(Reader reader = new InputStreamReader(JsonToken.class.getResourceAsStream(CONFIG_FILE_NAME), "UTF-8")){
            Gson gson = new GsonBuilder().create();
             configToReturn = gson.fromJson(reader, RunConfiguration.class);
            
        }
		return configToReturn;
    
		
		
	}
	
	
}
