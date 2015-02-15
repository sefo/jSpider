package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.Panel;
import data.Response;

public class Controller {
	private Panel view;
	private Response model;
	private Controller controller;
	private Scanner scanner;
	private ActionListener scanActionListener;
	private ActionListener stopActionListener;
	private ActionListener clearActionListener;
	private boolean isScanning = false;
	
	public Controller() {}
	
	public Controller(Panel view, Response model) {
		this.view = view;
		this.model = model;
		controller = this;
	}
	
	public void control() {
		scanActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(!isScanning()) {
					scanner = new Scanner(controller, view.getUrl(), view.getDictionary());
					if(scanner.getError() == null) {
						setScanning(true);
						view.getScan().setEnabled(false);
						view.getStop().setEnabled(true);
						view.setStatus(model.getScanning() + " " + scanner.getUrl());
					}
					else
						view.setStatus(scanner.getError());
				}
            }
		};
		view.getScan().addActionListener(scanActionListener);
		stopActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				stopScan();
            }
		};
		view.getStop().addActionListener(stopActionListener);
		clearActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				clearResults();
            }
		};
		view.getClear().addActionListener(clearActionListener); 
	}
	
	public void stopScan() {
		if(isScanning()) {
			setScanning(false);
			scanner.setRunning(false);
			view.getScan().setEnabled(true);
			view.getStop().setEnabled(false);
			view.setStatus(model.getStopped());
		}
	}
	
	public void clearResults() {
		view.getModel().setRowCount(0);
	}
	
	public void updateResults(String[] results) {
		view.getModel().addRow(results);
	}

	public boolean isScanning() {
		return isScanning;
	}

	public void setScanning(boolean isScanning) {
		this.isScanning = isScanning;
	}

}
