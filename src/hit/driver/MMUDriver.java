package hit.driver;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import hit.algorithm.*;
import hit.memoryunits.*;
import hit.processes.*;
import hit.processes.Process;
import hit.util.MMULogger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * This Class demonstrates the use of the MMU.
 * It utilizes the configuration.JSON file to create processes which read / write data to the RAM and Hard Disk.
 * @author Oren Cohen and Ron Katz
 *
 */
public class MMUDriver 
{ 
	private static int appIds; // incremented during CreateProcesses method to distinguish between different processes.
	private static final String CONFIG_FILE_NAME = "Configuration.json";
	final String DEFAULT_FILE_NAME; // instantiated during constructor with the current name.
	private static ExecutorService executor; // utilized to run the processes.
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
		MMULogger.getInstance().write("success", Level.INFO);
		MMULogger.getInstance().write("test2", Level.INFO);
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
