package main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import control.Controller;
import data.Response;
import ui.Panel;

public class Jspider extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	public Jspider() {}

	@Override
	public void run() {
		setTitle("jSpider");
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel panel = new Panel();
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		Response model = new Response();
		Controller controller = new Controller(panel, model);
		controller.control();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Jspider());
	}

}