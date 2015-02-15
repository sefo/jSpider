package control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import data.Request;

public class Scanner implements ThreadCompleteListener {
	private Controller controller;
	private URL url;
	private String error = null;
	private String dictionary;
	private boolean running = false;

	public Scanner() {}
	
	public Scanner(Controller controller, String url, String dictionary) {
		this.controller = controller;
		setDictionary(dictionary);
		if(dictionary.isEmpty()) {
			setError("Invalid dictionary file");
			return;
		}
		if(!url.endsWith("/"))
			url += "/";
		try {
			setUrl(new URL(url));
			try {
				FileInputStream fis = new FileInputStream(getDictionary());
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				setRunning(true);
				String line;
				while ((line = br.readLine()) != null && isRunning()) {
					NotifyingThread request = new Request(new URL(getUrl().toString() + line));
					request.addListener(this);
					request.start();
				}
				br.close();
				controller.stopScan();
			} catch(IOException e) {
				setError("Dictionary file error");
			}
		} catch (MalformedURLException e) {
			setError("Invalid URL");
		}
	}

	@Override
	public void notifyOfThreadComplete(Thread thread) {
		String[] results = ((Request) thread).getResults();
		if(results != null)
			controller.updateResults(results);
			//System.out.println(Arrays.toString(((Request) thread).getResults()));
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDictionary() {
		return dictionary;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
}