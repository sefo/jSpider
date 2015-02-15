package data;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import control.NotifyingThread;

public class Request extends NotifyingThread {
	private String userAgent = "Mozilla/5.0";
	private URL url = null;
	private String[] results = new String[3];

	public Request(URL url) {
		setUrl(url);
	}

	public void doRun() {
		setResults(sendRequest(getUrl()));
	}

	public String[] sendRequest(URL startUrl) {
		HttpURLConnection connection = null;
		int responseCode = -1;
		String[] result = new String[3];
		try {
			connection = (HttpURLConnection) startUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", userAgent);
			responseCode = connection.getResponseCode();
			if(responseCode != 404) {
				result[0] = startUrl.getPath();
				result[1] = Integer.toString(responseCode);
				result[2] = String.valueOf(connection.getContentLengthLong());
			}
			else {
				return null;
			}
		} catch (IOException e) {
			//e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String[] getResults() {
		return results;
	}

	public void setResults(String[] results) {
		this.results = results;
	}
	
}
