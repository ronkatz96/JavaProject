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

import hit.Model.MMUModel;
import hit.Model.Model;
import hit.View.MMUView;
import hit.View.View;
import hit.algorithm.*;
import hit.controller.Controller;
import hit.controller.MMUController;
import hit.memoryunits.*;
import hit.processes.*;
import hit.processes.Process;
import hit.util.MMULogger;

/**
 * This Class demonstrates the use of the MMU.
 * It utilizes the configuration.JSON file to create processes which read / write data to the RAM and Hard Disk.
 * @author Oren Cohen and Ron Katz
 *
 */
public class MMUDriver 
{ 
	private static int appIds; // incremented during CreateProcesses method to distinguish between different processes.
	private static final String CONFIG_FILE_NAME = "resources\\configuration\\Configuration.json";
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
		mmu.shutDown();
		
		/*
		 * Work-In-Progress MVC Model:
		 */
		Model model = new MMUModel(MMULogger.DEFAULT_FILE_NAME);
		model.readData();
		View view = new MMUView();
		Controller controller = new MMUController(view, model);
		controller.start();
	}
	
	private static List<Process> createProcesses(List<ProcessCycles> processCycles, MemoryManagementUnit mmu) 
	{
		List<Process> processesToReturn = new ArrayList<Process>();
		appIds = 1;
		for(ProcessCycles element : processCycles)
		{
			processesToReturn.add(new Process(appIds, mmu, element));
			appIds++;
		}
		
		String commandToWrite = String.format("PN: %d", processesToReturn.size() );
		MMULogger.getInstance().write(commandToWrite, Level.INFO);
		return processesToReturn;	
	}
	
	private static void runProcesses(List<Process> applications) 
	{
		executor = Executors.newCachedThreadPool();
		for (Process element: applications)
		{
			executor.execute(element);
		}
		
		executor.shutdown();
		while(!executor.isTerminated());
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
