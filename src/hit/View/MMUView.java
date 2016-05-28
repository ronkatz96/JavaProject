package hit.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//this is a test. TODO implement actual VIEW.
@SuppressWarnings("serial")
public class MMUView extends JPanel implements View, ActionListener {
	List<List<String>> modelData;
	int numOfProcesses;
	String[][] array;
	private JFrame frame;
	private JButton play;
	private JPanel pane;
	// private MMUPanel mmuPanel;
	private JPanel southPane;
	private JButton playAll;
	private JPanel labelPane;
	private JPanel eastPane;
	private JTable tableView;
	private JScrollPane scrollPane;
	private JPanel listPane;
	private JList<Object> prList;
	private int pageFaultCounter = 0;
	private int pageReplacementCounter = 0;
	private int index = 1;
	private Object[] header;
	private Object[][] data;
	private Object[] backHeader;
	private Object[][] backData;
	private HashMap<Integer, Integer> pagesMap = new HashMap<Integer, Integer>();
	private boolean isPageFault;
	private JLabel pr;
	private JLabel pf;

	public MMUView() {
		
		header = new Object[5];
		data = new Object[5][5];
		backHeader = new Object[5];
		backData = new Object[5][5];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void open() {
		String PN = modelData.get(1).get(1); // the number of processes is
												// located in the second array
												// of the list in the second
												// cell of that array (1,1)
		numOfProcesses = Integer.parseInt(PN); // gives us the number of
												// Processes for the creation of
												// the Table
		System.out.println("this is the number of Processes: " + numOfProcesses);
		Object[] columns = new Object[numOfProcesses];

		for (int i = 0; i < numOfProcesses; i++) {
			columns[i] = String.format("Process %d", i + 1);
		} // this is the for he columns.

		// transform modelData into 2D array
		array = new String[modelData.size()][];
		for (int i = 0; i < modelData.size(); i++) {
			ArrayList<String> row = (ArrayList<String>) modelData.get(i);
			array[i] = row.toArray(new String[row.size()]);
		}

		for (int i = 0; i < array.length; i++) {
			if (array[i][0].contains("GP")) {
				System.out.println(array[i][3]);
			}
		}

		frame = new JFrame("MMU Simulator");
		frame.getContentPane().setPreferredSize(new Dimension(1000, 350));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mmuPanel = new MMUPanel();
		pane = new JPanel();
		pane.setLayout(new BorderLayout());
		frame.getContentPane().add(pane);

		southPane = new JPanel(new GridLayout(1, 2));
		play = new JButton("Play");
		southPane.add(play);
		play.addActionListener(this);
		play.setActionCommand("play");
		playAll = new JButton("Play All");
		southPane.add(playAll);
		playAll.addActionListener(this);
		playAll.setActionCommand("play all");
		labelPane = new JPanel(new GridLayout(2, 1));
		pr = new JLabel("Page Replacement Amount: " + pageReplacementCounter);
		labelPane.add(pr);
		pf = new JLabel("Page Fault Amount: " + pageFaultCounter);
		labelPane.add(pf);

		eastPane = new JPanel(new GridLayout(2, 1));
		eastPane.add(labelPane);

		listPane = new JPanel(new BorderLayout());
		JLabel processes = new JLabel("Processes");
		listPane.add(processes, BorderLayout.NORTH);
		prList = new JList(columns);
		listPane.add(prList, BorderLayout.CENTER);
		eastPane.add(listPane);

		TableModel table = new DefaultTableModel(new String[5][5], new String[] { " ", " ", " ", " ", " " });
		tableView = new JTable(table);

		scrollPane = new JScrollPane(tableView);
		tableView.setPreferredScrollableViewportSize(tableView.getPreferredSize());
		pane.add(scrollPane);
		pane.add(eastPane, BorderLayout.EAST);
		pane.add(southPane, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * public void ChangedRamStatePLAY(ActionListener ListenForPlayButton) {
	 * play.addActionListener(ListenForPlayButton); }
	 */

	@Override
	public void setModelData(List<List<String>> data) {
		modelData = data;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String actionPerf = arg0.getActionCommand();
		if (actionPerf.equals("play")) {
			if (index < modelData.size()) {
				String action = array[index][0];
				switch (action) {
				case "PF:": {
					pageFaultCounter++;
					pagesMap.put(Integer.valueOf(array[index][1]), -1);
					isPageFault = true;
					break;
				}
				case "PR:": {
					pageReplacementCounter++;
					pagesMap.put(Integer.valueOf(array[index][4]), Integer.valueOf(array[index][2]));
					isPageFault = false;
					break;
				}
				case "GP:":
					int newId = Integer.valueOf(array[index][2]);
					int oldId = (pagesMap.get(newId) == null) ? -1 : pagesMap.get(newId);
					Object[] arr = array[index][3].substring(1, array[index][3].length() - 1).split(",");

					updateBackTable(oldId, newId, arr, isPageFault);
					updateTableView(newId);
					break;
				}
				index++;
			} else {
				JOptionPane.showMessageDialog(frame, "End of memory", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (actionPerf.equals("play all")) {
			while (index < modelData.size()) {
				String action = array[index][0];
				switch (action) {
				case "PF": {
					break;
				}
				case "PR": {
					break;
				}
				case "GP":
					break;
				}
				index++;
			}
		}
	}

	public void updateBackTable(int oldId, int newId, Object[] data, boolean pf) {

		if (oldId != -1) {
			for (int i = 0; i < backHeader.length; i++) {
				if ((int) backHeader[i] == oldId) {
					backHeader[i] = String.valueOf(newId);
					for (int j = 0; j < data.length; i++) {
						backData[i][j] = data[j];
					}
				}
			}
		} else if (pf == true) {
			for (int i = 0; i < backHeader.length; i++) {
				if (backHeader[i] == null) {
					backHeader[i] = String.valueOf(newId);
					for (int j = 0; j < data.length; i++) {
						backData[i][j] = 123;
					}
					break;
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void updateTableView(int newId) {

		if (!pagesMap.isEmpty()) {
			Integer id = pagesMap.remove(newId);
			if (id != null) {
				if (id == -1) {
					pageFaultCounter++;
					pf.setText("Page Replacement Amount: " + pageReplacementCounter);
				} else {
					pageReplacementCounter++;
					pr.setText("Page Fault Amount: " + pageFaultCounter);
				}
			}
		}
		
		Object[] processes = prList.getSelectedValues();
		
		int column = 0;
		for (int i = 0;i<processes.length;i++){
			if (processes[i] == array[index][1].replace("P", "Process ")){
				for (int j = 0;j < header.length;i++ ){
					if(backHeader[j]!=null && (int)backHeader[j] == newId){
						header[j] = newId;
						column = j;
						break;
					}
				}
				for (int j = 0 ; j < data.length ; j++){
					data[j][column] = backData[j][column];
				}
				tableView.setModel(new DefaultTableModel(data,header));
				tableView.setEnabled(false);
			}
			else{
				for (int j = 0;j < header.length;i++ ){
					if(backHeader[j]!=null && (int)backHeader[j] == newId){
						header[j] = 0;
						column = j;
						break;
					}
				}
				for (int j = 0 ; j < data.length ; j++){
					data[j][column] = "*";
				}
				tableView.setModel(new DefaultTableModel(data,header));
				tableView.setEnabled(false);
			}
		}
	}
}
