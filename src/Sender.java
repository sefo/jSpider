import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Sender {
	private String userAgent = "Mozilla/5.0";
	private URL lookupURL = null;
	boolean scanning = false;
	private String dictionary;
	
	public Sender() {}
	
	public Sender(String url, String dictionary) {
		if(dictionary == null)
			setDictionary("folders.txt");
		else
			setDictionary(dictionary);
		if(!url.endsWith("/"))
			url += "/";
		try {
			lookupURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		sendGet(lookupURL);
	}
	
	public void sendGet(URL startUrl) {
		HttpURLConnection connection = null;
		int responseCode = -1;
		String inputLine = "";
		StringBuilder response = null;
		BufferedReader _in = null;
		try {
			connection = (HttpURLConnection) startUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", userAgent);
			responseCode = connection.getResponseCode();
			if(responseCode != 404) {
				System.out.println("\nSending 'GET' request to URL : " + startUrl);
				System.out.println("Response Code : " + responseCode);
				_in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				response = new StringBuilder();
				while ((inputLine = _in.readLine()) != null) {
					response.append(inputLine);
				}
				_in.close();
				System.out.println("Size: " + response.toString().length());
				System.out.println("Path: " + startUrl.getPath());
			}
			if(!scanning) {
				scanning = true;
				scanURL();
			}
		} catch (IOException e) {
			System.out.println("folder is not reachable");
		}
	}
	
	private void scanURL() {
		try {
			FileInputStream fis = new FileInputStream(dictionary);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {
				URL url = new URL(lookupURL + line);
				sendGet(url);
			}
			br.close();
		} catch(IOException e) {
			System.out.println("Dictionary file error");
		}
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getDictionary() {
		return dictionary;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}
	
}
