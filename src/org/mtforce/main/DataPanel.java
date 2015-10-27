package org.mtforce.main;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DataPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public DataPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblLightSensor = new JLabel("Light Sensor");
		add(lblLightSensor, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}

}
