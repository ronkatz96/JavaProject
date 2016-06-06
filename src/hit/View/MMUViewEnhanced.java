package hit.View;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MMUViewEnhanced extends JDialog implements View, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel mmuViewPanel[];
	
	public MMUViewEnhanced()
	{
		mmuViewPanel = new JPanel[4];
		createTable();
		createCounters();
		createButtons();
		createNoteArea();
	}
	private void createNoteArea() 
	{
		mmuViewPanel[3] = new JPanel(new GridLayout(0,1));
		mmuViewPanel[3].add(new JButton("Clear"));
		mmuViewPanel[3].add(new JTextArea());
		mmuViewPanel[3].setSize(500, 500);
	}
	private void createButtons() 
	{
		
		mmuViewPanel[2] = new JPanel();
		GroupLayout groupedButtons = new GroupLayout(mmuViewPanel[2]);
		mmuViewPanel[2].setLayout(groupedButtons);
		JButton playButton = new JButton("Play");
		playButton.setPreferredSize(new Dimension(100,10));
		
		JButton playAllButton = new JButton("Play All");
		playAllButton.setPreferredSize(new Dimension(100,10));
		groupedButtons.setVerticalGroup(
				groupedButtons.createSequentialGroup()
				      .addGroup(groupedButtons.createParallelGroup(GroupLayout.Alignment.BASELINE)
				           .addComponent(playButton)
				           .addComponent(playAllButton))
				);
		groupedButtons.setHorizontalGroup(
				groupedButtons.createSequentialGroup()
				      .addComponent(playButton)
				      .addComponent(playAllButton)
				      .addGroup(groupedButtons.createParallelGroup(GroupLayout.Alignment.LEADING)
				           )
				);
		mmuViewPanel[2].setSize(200, 200);
		
	}
	private void createCounters() 
	{
		mmuViewPanel[1] = new JPanel(new FlowLayout());
		mmuViewPanel[1].add(new JLabel("Page Replacement:"));
		JTextArea PRValue = new JTextArea();
		PRValue.setEditable(false);
		PRValue.setName("PR");
		PRValue.setPreferredSize(new Dimension(100,20));
		mmuViewPanel[1].add(PRValue);
		mmuViewPanel[1].add(new JLabel("Page Fault:"));
		JTextArea PFValue = new JTextArea();
		PFValue.setEditable(false);
		PFValue.setName("PR");
		PFValue.setPreferredSize(new Dimension(100,20));
		mmuViewPanel[1].add(PFValue);
		mmuViewPanel[1].setSize(500, 500);
		
	}
	private void createTable() 
	{
		mmuViewPanel[0] = new JPanel(new GridLayout(0,2));
		mmuViewPanel[0].setSize(500, 500);
		
	}
	@Override
	public void open() {
		

	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		MMUViewEnhanced dialog = new MMUViewEnhanced();
		dialog.setLayout(new GridLayout(2,2,10,10));
		dialog.add(mmuViewPanel[0]);
		dialog.add(mmuViewPanel[1]);
		dialog.add(mmuViewPanel[2]);
		dialog.add(mmuViewPanel[3]);
		dialog.setPreferredSize(new Dimension(1024,768));
//	    dialog.setUndecorated(true);
//	    dialog.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	@Override
	public void setModelData(String[][] data) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
