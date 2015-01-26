import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Jspider extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	public Jspider() {
	}

	@Override
	public void run() {
		setTitle("jSpider");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Panel());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		//Run as cmdLine with url and default dictionary
		if(args.length == 1) {
			new Sender(args[0], null);
		}
		//Run as cmdLine with url and custom dictionary
		else if(args.length == 2) {
			new Sender(args[0], args[1]);
		}
		//Run as GUI
		else
			SwingUtilities.invokeLater(new Jspider());
	}

}
