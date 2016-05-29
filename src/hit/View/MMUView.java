package hit.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//this is a test. TODO implement actual VIEW.
@SuppressWarnings("serial")
public class MMUView extends JPanel implements View, ActionListener {
	// List<List<String>> modelData;

	int numOfProcesses;
	String[][] modelData;
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
	ExecutorService executor = Executors.newSingleThreadExecutor();

	public MMUView() {

		header = new Object[5];
		data = new Object[5][5];
		backHeader = new Object[5];
		backData = new Object[5][5];
	}

	@Override
	public void open() {
		String PN = modelData[1][1]; // the number of processes is
										// located in the second array
										// of the list in the second
										// cell of that array (1,1)
		numOfProcesses = Integer.parseInt(PN); // gives us the number of
												// Processes for the creation of
												// the Table

		// transform modelData into 2D array
		// array = new String[modelData.size()][];
		// for (int i = 0; i < modelData.size(); i++) {
		// ArrayList<String> row = (ArrayList<String>) modelData.get(i);
		// array[i] = row.toArray(new String[row.size()]);
		// }
		init();
	}

	@Override
	public void setModelData(String[][] data) {
		modelData = data;
	}

	public void processData() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String actionPerf = arg0.getActionCommand();
		if (actionPerf.equals("play")) {
			if (index < modelData.length) {
				String action = modelData[index][0];
				switch (action) {
				case "PF:": {
					pageFaultCounter++;
					pf.setText("Page Fault Amount: " + pageFaultCounter);
					pf.invalidate();
					pagesMap.put(Integer.valueOf(modelData[index][1]), -1);
					isPageFault = true;
					this.invalidate();
					break;
				}
				case "PR:": {
					pageReplacementCounter++;
					pr.setText("Page Replacement Amount: " + pageReplacementCounter);
					pr.invalidate();
					pagesMap.put(Integer.valueOf(modelData[index][4]), Integer.valueOf(modelData[index][2]));
					isPageFault = false;
					this.invalidate();
					break;
				}
				case "GP:":
					int newId = Integer.valueOf(modelData[index][2]);
					int oldId = (pagesMap.get(newId) == null) ? -1 : pagesMap.get(newId);
					Object[] arr = modelData[index][3].substring(1, modelData[index][3].length() - 1).split(",");

					updateBackTable(oldId, newId, arr, isPageFault);
					updateTableView(newId);
					this.invalidate();
					break;
				}
				index++;
			} else {
				JOptionPane.showMessageDialog(frame, "End of log", "Notice", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		else if (actionPerf.equals("play all")) {
			/*
			 * JOptionPane.showMessageDialog(frame,
			 * "We apologize, this button's functionality is not implemented yet!"
			 * , "Error", JOptionPane.ERROR_MESSAGE);
			 */
			while (index < modelData.length) {
				String action = modelData[index][0];
				switch (action) {
				case "PF:": {
					pageFaultCounter++;
					pf.setText("Page Fault Amount: " + pageFaultCounter);
					pf.invalidate();
					pagesMap.put(Integer.valueOf(modelData[index][1]), -1);
					isPageFault = true;
					this.invalidate();
					break;
				}
				case "PR:": {
					pageReplacementCounter++;
					pr.setText("Page Replacement Amount: " + pageReplacementCounter);
					pr.invalidate();
					pagesMap.put(Integer.valueOf(modelData[index][4]), Integer.valueOf(modelData[index][2]));
					isPageFault = false;
					this.invalidate();
					break;
				}
				case "GP:":
					int newId = Integer.valueOf(modelData[index][2]);
					int oldId = (pagesMap.get(newId) == null) ? -1 : pagesMap.get(newId);
					Object[] arr = modelData[index][3].substring(1, modelData[index][3].length() - 1).split(",");

					updateBackTable(oldId, newId, arr, isPageFault);
					updateTableView(newId);
					this.invalidate();
					break;
				}
				index++;
				/*try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}*/
			}
			JOptionPane.showMessageDialog(frame, "End of log", "Notice", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public synchronized void updateBackTable(int oldId, int newId, Object[] data, boolean pf) {

		if (oldId != -1) {
			for (int i = 0; i < backHeader.length; i++) {
				if (backHeader[i] != null && ((String) backHeader[i]).equals(String.valueOf(oldId))) {
					backHeader[i] = String.valueOf(newId);
					for (int j = 0; j < data.length; j++) {
						backData[i][j] = data[j];
					}
				}
			}
		} else if (pf == true) {
			for (int i = 0; i < backHeader.length; i++) {
				if (backHeader[i] == null) {
					backHeader[i] = String.valueOf(newId);
					int column = i;
					for (int j = 0; j < backData.length; j++) {
						backData[j][column] = data[j];
					}
					break;
				}
			}
		}
	}

	public void updateTableView(int newId) {

		if (!pagesMap.isEmpty()) {
			Integer id = pagesMap.remove(newId);
			if (id != null) {
				if (id == -1) {
					// pageFaultCounter++;
					pf.setText("Page Fault Amount: " + pageFaultCounter);
					pf.invalidate();
				} else {
					// pageReplacementCounter++;
					pr.setText("Page Replacement Amount: " + pageReplacementCounter);
					pr.invalidate();
				}
			}
		}

		Object[] processes = prList.getSelectedValuesList().toArray();

		int column = 0;
		for (int i = 0; i < processes.length; i++) {
			String nameOfProcess = modelData[index][1].replace("P", "Process ");
			if (((String) processes[i]).equals(nameOfProcess)) {
				for (int j = 0; j < header.length; j++) {
					if (backHeader[j] != null && ((String) backHeader[j]).equals(String.valueOf(newId))) {
						header[j] = newId;
						column = j;
						break;
					}
				}
				for (int j = 0; j < data.length; j++) {
					data[j][column] = String.valueOf(backData[j][column]);
				}
				tableView.setModel(new DefaultTableModel(data, header));
				tableView.setEnabled(false);
				this.invalidate();
				return;
			} else {
				for (int j = 0; j < header.length; j++) {
					if (header[j] != String.valueOf(newId)) {
						if (backHeader[j] != null && Integer.parseInt((String) backHeader[j]) == newId) {
							header[j] = "*";
							column = j;
						}
						break;
					}
				}
				for (int j = 0; j < data.length; j++) {
					data[j][column] = "*";
				}
				tableView.setModel(new DefaultTableModel(data, header));
				tableView.setEnabled(false);
				this.invalidate();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	void init() {
		Object[] columns = new Object[numOfProcesses];
		for (int i = 0; i < numOfProcesses; i++) {
			columns[i] = String.format("Process %d", i + 1);
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
}
