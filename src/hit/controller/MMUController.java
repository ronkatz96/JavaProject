package hit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import hit.Model.Model;
import hit.View.View;

public class MMUController implements Controller, ActionListener
{
	View viewer;
	Model model;
	
	public MMUController(View view, Model model)
	{
		this.viewer = view;
		this.model = model;
	}
	@Override
	public void start() 
	{
		List<String> data = model.getModelData();
		List<List<String>> delimitedViewerData = createViewerData(data);
		viewer.setModelData(delimitedViewerData);
		viewer.open();
		
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
