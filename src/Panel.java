import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public Panel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		JLabel labelURL = new JLabel("URL: ");
		add(labelURL, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		JTextField url = new JTextField("http://www.");
		url.setColumns(100);
		add(url, c);
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0;
		JButton scan = new JButton("Scan"); 
		add(scan, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		JTextField status = new JTextField("stopped");
		add(status, c);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		JTextField dico = new JTextField("dico.txt");
		add(dico, c);
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		JButton stop = new JButton("Stop");
		add(stop, c);
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(15,0,0,0);
		model = new DefaultTableModel(data, columns);
		JTable results = new JTable(model);
		results.setPreferredSize(new Dimension(600, 500));
		JScrollPane scrollPane = new JScrollPane(results);
		add(scrollPane, c);
        scan.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                Jspider.scanURL(url.getText(), dico.getText());
                status.setText("started");
            }
        });
        stop.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                Jspider.setRunning(false);
                status.setText("stopped");
            }
        });
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
}
