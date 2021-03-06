package hit.Model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;


public class MMUModel implements Model{
	
	private final String MODEL_RESOURCE_FILE;
	private List<String> modelData;
	
	
	public MMUModel (String nameOfFile)
	{
		MODEL_RESOURCE_FILE = nameOfFile;
	}
	
	@Override
	public void readData() 
	{
		try {
			setModelData(Files.readAllLines(new File(MODEL_RESOURCE_FILE).toPath(), Charset.defaultCharset() ));
			//System.out.println(modelData.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> getModelData() {
		return modelData;
	}

	private void setModelData(List<String> modelData) {
		this.modelData = modelData;
	}
}
