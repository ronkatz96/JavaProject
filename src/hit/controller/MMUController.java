package hit.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import hit.Model.Model;
import hit.View.*;

public class MMUController implements Controller
{
	View viewer;
	Model model;
	MMUFlowSelection flowSelection;
	List<List<String>> delimitedViewerData;
	String[][] processedModelData;
	
	public MMUController(View view, Model model)
	{
		this.viewer = view;
		this.model = model;
		this.flowSelection = new MMUFlowSelection();
	}
	@Override
	public void start() 
	{
		List<String> data = model.getModelData();
		delimitedViewerData = createViewerData(data);
		processModelData();
		viewer.setModelData(processedModelData);
		viewer.open();
		flowSelection.init();
	}
	
	private List<List<String>> createViewerData(List<String> data) 
	{
		List<List<String>> rowsData = new ArrayList<List<String>>();
		for(String element : data)
		{
			List<String> rowString = new ArrayList<String>();
			if (element.contains("["))
			{
				String byteArray = element.substring(element.indexOf("["));
				String[] cutRow = element.split(" ");
				String[] rowString1 = new String[4];
				for (int i = 0; i<3;i++)
				{
					rowString1[i] = cutRow[i];
				}
				rowString1[3] = byteArray;
				for (String element1 : rowString1)
				{
					rowString.add(element1);
				}
				rowsData.add(rowString);
			}
			else
			{
				String[] cutRow = element.split(" ");
				for (String element1 : cutRow)
				{
					rowString.add(element1);
				}
				rowsData.add(rowString);
			}
		}
		for(List<String> element : rowsData)
		{
			if ((element.size() > 2) && (element.get(3).contains("[")))
			{
				String byteArray = element.get(3);
				String subByteArray = byteArray.substring(1, byteArray.length()-2);
				String[] newByteArray = subByteArray.split(",");
				//System.out.print(Collections.toString(newByteArray));
				List<String> listByteArray = new ArrayList<String>();
				for (String subString : newByteArray)
				{
					listByteArray.add(subString);
				}
				element = listByteArray;
				System.out.print("data array:");
				//System.out.println(listByteArray.toString());
			}
		}
		for (List<String> subString : rowsData)
		{
			System.out.println(Arrays.toString(subString.toArray()));
		}
		return rowsData;
	}
	public void processModelData()
	{
		processedModelData = new String[delimitedViewerData.size()][];
		for (int i = 0; i < delimitedViewerData.size(); i++) {
			ArrayList<String> row = (ArrayList<String>) delimitedViewerData.get(i);
			processedModelData[i] = row.toArray(new String[row.size()]);
		}
	}
}
