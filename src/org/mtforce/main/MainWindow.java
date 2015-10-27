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

import org.mtforce.network.Client;
import org.mtforce.network.CmdPackage;
import org.mtforce.network.InfoPackage;
import org.mtforce.table.Constants;
import org.mtforce.table.SensorTableModel;
import java.awt.FlowLayout;

public class MainWindow {

	private JFrame frame;
	private static JTable table;
	private static JTextField txtIp;
	private static SensorTableModel tableModel;

	private static Client client = null;
	
	
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
		
		CmdPackage pkg = new CmdPackage();
		pkg.setRequestUpdate(true);
		while(true)
		{
			if(client != null)
			{
				try
				{
					client.write(pkg);
					InfoPackage info = client.read();
					updateInfo(info);
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	private static void updateInfo(InfoPackage info)
	{
		tableModel.updateData(info.getSensors().toArray(new String[info.getSensors().size()]), info.getNames().toArray(new String[info.getNames().size()]), info.getValues().toArray(new Double[info.getValues().size()]));
		tableModel.fireTableDataChanged();
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
		tableModel = new SensorTableModel();
		table = new JTable(tableModel);

		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(250, 200));
		final JTextField dirPathTextField = new JTextField(26);

		JPanel ctrlPane = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
		ctrlPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtIp = new JTextField();
		ctrlPane.add(txtIp);
		txtIp.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(txtIp.getText() != "")
				{
					client = new Client(txtIp.getText(), 9999);
				}
			}
		});
		ctrlPane.add(btnConnect);
		splitPane.setDividerLocation(35);
		splitPane.setEnabled(false);

		// Display it all in a scrolling window and make the window appear
		JFrame frame = new JFrame("Swing JTable Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(splitPane);
		frame.setSize(362, 428);
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setPreferredSize(new Dimension(100,100));
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
