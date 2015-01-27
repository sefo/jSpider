import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Jspider extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private static Panel panel = new Panel();

	public Jspider() {
	}

	@Override
	public void run() {
		setTitle("jSpider");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		//Run as cmdLine with url and default dictionary
		if(args.length == 1) {
			scanURL(args[0], "dico.txt");
			
		}
		//Run as cmdLine with url and custom dictionary
		else if(args.length == 2) {
			scanURL(args[0], args[1]);
		}
		//Run as GUI
		else
			SwingUtilities.invokeLater(new Jspider());
	}

	public static void scanURL(String url, String dictionary) {
		Sender sender = new Sender(url);
		System.out.println(Arrays.toString(sender.getResults()));
		try {
			FileInputStream fis = new FileInputStream(dictionary);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {
				sender = new Sender(url + line);
				if(sender.getResults() != null) {
					System.out.println(Arrays.toString(sender.getResults()));//override toString() to display nice results
					panel.getModel().addRow(sender.getResults());
				}
			}
			br.close();
		} catch(IOException e) {
			System.out.println("Dictionary file error");
		}
	}

}
