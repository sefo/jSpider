package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private String[] columns = {"Path", "Response", "Size"};
	private String[][] data;
	private DefaultTableModel model;
	//components
	private JLabel status = new JLabel("Status: not scanning.");
	private JTextField url = new JTextField("http://www.formation-mip.fr/", 20);
	private JButton scan = new JButton("    Scan     ");
	private JButton stop = new JButton("    Stop     ");
	private JButton clear = new JButton("   Clear    ");
	private JTextField dictionary = new JTextField("folders.txt", 20);

	public Panel() {
		// main layout, North = controls, Center = display, South additional controls
		setLayout(new BorderLayout(10, 10));
		
		//top horizontal controls in a JPanel
		JPanel topControls = new JPanel(new GridLayout(3, 1, 0, 10));
		
		//box for status
		Box statusControls = Box.createHorizontalBox();
		statusControls.add(Box.createHorizontalStrut(20));
		statusControls.add(status);
		
		//box for url controls
		Box urlControls = Box.createHorizontalBox();
		urlControls.add(Box.createHorizontalStrut(20));
		urlControls.add(new JLabel("URL:            "));
		url.setMinimumSize(url.getPreferredSize());
		urlControls.add(url);
		urlControls.add(scan);
		urlControls.add(Box.createHorizontalStrut(5));
		stop.setEnabled(false);
		urlControls.add(stop);
		urlControls.add(Box.createHorizontalStrut(5));
		urlControls.add(clear);
		urlControls.add(Box.createHorizontalStrut(150));
		
		//box for dictionary controls
		Box dictControls = Box.createHorizontalBox();
		dictControls.add(Box.createHorizontalStrut(20));
		dictControls.add(new JLabel("Dictionary: "));
		dictionary.setMinimumSize(url.getPreferredSize());
		dictControls.add(dictionary);
		dictControls.add(Box.createHorizontalStrut(5));
		dictControls.add(new JButton("Browse..."));
		dictControls.add(Box.createHorizontalStrut(150));
		
		//Table that goes to the center
		model = new DefaultTableModel(data, columns);
		JTable results = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(results);
		
		//add all elements together
		topControls.add(statusControls);
		topControls.add(urlControls);
		topControls.add(dictControls);
		add(topControls, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	//Components access
	public JLabel getStatus() {
		return status;
	}
	public void setStatus(String value) {
		status.setText(value);
	}
	public String getUrl() {
		return url.getText();
	}
	public void setUrl(String value) {
		url.setText(value);
	}
	public JButton getScan() {
		return scan;
	}
	public JButton getStop() {
		return stop;
	}
	public JButton getClear() {
		return clear;
	}
	public String getDictionary() {
		return dictionary.getText();
	}
	public void setDictionary(String value) {
		dictionary.setText(value);
	}
	public DefaultTableModel getModel() {
		return model;
	}
	
}
