package hit.View;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MMUFlowSelection extends JPanel implements ActionListener{

	private JFrame frame;
	private JPanel pane;
	private JButton remote;
	private JButton local;
	private JLabel label;
	
	public void init() {
		
		frame = new JFrame("MMU Server");
		frame.getContentPane().setPreferredSize(new Dimension(350, 350));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		frame.getContentPane().add(pane);
		remote = new JButton("Remote");
		remote.addActionListener(this);
		//remote.setActionCommand("remote");
		local = new JButton("Local");
		local.addActionListener(this);
		//local.setActionCommand("local");
		label = new JLabel("Choose");
		pane.add(label);
		pane.add(remote);
		pane.add(local);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
