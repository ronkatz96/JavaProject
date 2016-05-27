package hit.View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hit.Model.Model;
//this is a test. TODO implement actual VIEW.
@SuppressWarnings("serial")
public class MMUView extends JPanel implements View, ActionListener 
{
	List<List<String>> modelData;
	int numOfProcesses;
	
	public MMUView()
	{


	}
	@Override
	public void open() 
	{
		String PN = modelData.get(1).get(1); // the number of processes is located in the second array of the list in the second cell of that array (1,1)
		numOfProcesses = Integer.parseInt(PN); //gives us the number of Processes for the creation of the Table
		System.out.println("this is the number of Processes: " + numOfProcesses);
		List<String> columns = new ArrayList<String>();
		
		for (int i = 1; i<= numOfProcesses; i++)
		{
			columns.add(String.format("Process %d", i));
		}//this is the for he columns.
		
		//transform modelData into 2D array
		String[][] array = new String[modelData.size()][];
		for (int i = 0; i < modelData.size(); i++) {
		    ArrayList<String> row = (ArrayList<String>) modelData.get(i);
		    array[i] = row.toArray(new String[row.size()]);
		}
		
		for (int i = 0; i<array.length; i++)
		{
			if (array[i][0].contains("GP"))
			{
				System.out.println(array[i][3]);
			}
		}
		TableModel table = new DefaultTableModel(new String[5][1],columns.toArray(new String[columns.size()]));
		JTable tableView = new JTable(table);
		JDialog dialog = new JDialog();
		dialog.setTitle("Test");
		dialog.setLayout(new BorderLayout());
		dialog.add(tableView.getTableHeader(), BorderLayout.PAGE_START);
		dialog.add(tableView, BorderLayout.CENTER);
		JButton play = new JButton("Play");
		play.addActionListener(this);
		dialog.add(play, BorderLayout.LINE_START);
		JButton playAll = new JButton("Play All");
		playAll.addActionListener(this);
		dialog.add(playAll, BorderLayout.LINE_END);
		dialog.pack();
		dialog.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("You need to implement this shit!");
	}
	@Override
	public void setModelData(List<List<String>> data) 
	{
		modelData = data;	
	}

}
