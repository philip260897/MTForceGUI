package org.mtforce.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.mtforce.table.Constants;
import org.mtforce.table.SensorTableModel;

public class MainWindow {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainWindow window = new MainWindow();
					//window.frame.setVisible(true);
					
					setLookAndFeel(Constants.NIMBUS_LF);
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 533, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public static void createAndShowGUI( ) throws Exception {
		String[] sensors = {"DistanceSensor", "","", "LightSensor"};
		String[] names = {"Distance", "Distance2","", "Luminance"};
		Double[] values= {2.91, 3.10,null, 5.0};

		// Create a TableModel object to represent the contents of the directory
		SensorTableModel tableModel = new SensorTableModel(sensors, names, values);

		// Create a JTable and tell it to display our model
		final JTable table = new JTable(tableModel);

		// Put the JTable in a JScrollPane to handle scrolling
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(250, 200));

		final JTextField dirPathTextField = new JTextField(26);



		JPanel ctrlPane = new JPanel();

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
		splitPane.setDividerLocation(35);
		splitPane.setEnabled(false);

		// Display it all in a scrolling window and make the window appear
		JFrame frame = new JFrame("Swing JTable Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(splitPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void setLookAndFeel(String lf) throws Exception {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (lf.equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If the given lf is not available, you can set the GUI the system
			// default L&F.
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
	}
}
