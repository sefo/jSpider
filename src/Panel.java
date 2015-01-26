import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;

	public Panel() {
		add(new JLabel("URL: "));
		JTextField url = new JTextField("http://");
		url.setColumns(100);
		add(url);
		add(new JButton("Scan"));
		JTextArea results = new JTextArea("results");
		results.setPreferredSize(new Dimension(800, 500));
		JScrollPane scrollPane = new JScrollPane(results);
		add(scrollPane);
	}
}
