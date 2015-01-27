import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Sender {
	private String userAgent = "Mozilla/5.0";
	private URL lookupURL = null;
	private String[] results = new String[3];
	
	public Sender() {}
	
	public Sender(String url) {
		if(!url.endsWith("/"))
			url += "/";
		try {
			lookupURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		setResults(sendGet(lookupURL));
	}
	
	public String[] sendGet(URL startUrl) {
		HttpURLConnection connection = null;
		int responseCode = -1;
		String inputLine = "";
		StringBuilder response = null;
		BufferedReader _in = null;
		String[] result = new String[3];
		try {
			connection = (HttpURLConnection) startUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", userAgent);
			responseCode = connection.getResponseCode();
			if(responseCode != 404) {
				//System.out.println("\nSending 'GET' request to URL : " + startUrl);
				//System.out.println("Response Code : " + responseCode);
				result[1] = Integer.toString(responseCode);
				_in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				response = new StringBuilder();
				while ((inputLine = _in.readLine()) != null) {
					response.append(inputLine);
				}
				_in.close();
				//System.out.println("Size: " + response.toString().length());
				//System.out.println("Path: " + startUrl.getPath());
				result[0] = startUrl.getPath();
				result[2] = Integer.toString(response.toString().length());
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

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public URL getLookupUrl() {
		return lookupURL;
	}

	public void setLookupUrl(URL lookupUrl) {
		this.lookupURL = lookupUrl;
	}

	public String[] getResults() {
		return results;
	}

	public void setResults(String[] results) {
		this.results = results;
	}
	
}
