package webservice;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequest {

	private final String USER_AGENT = "Mozilla/5.0";
	private String CONTENT_TYPE = "";

	public String getCONTENT_TYPE() {
		return CONTENT_TYPE;
	}

	public void setCONTENT_TYPE(String cONTENT_TYPE) {
		CONTENT_TYPE = cONTENT_TYPE;
	}

	public static void main(String[] args) throws Exception {

		HttpRequest http = new HttpRequest();

		System.out.println("Testing 1 - Send Http GET request");
		String url="http://cardev.policybazaar.com:8080/api/productInfo/849/?enquiryId=OTI0NTUy&tabName=allPlans&isMulti=false&incomeTerm=20&increasingIncomePercentage=0&monthlyIncome=&oneTimePayout=&v=1500356277653";
		String str=http.sendGet(url,"");
        System.out.println(str);
		System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();

	}

	// HTTP GET request
	public String sendGet(String url,String parameters) throws Exception {

		

		URL obj = new URL(url+parameters);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
		return response.toString();

	}

	// HTTP POST request
	public String sendPost(String url,String urlParameters) throws Exception {

		if(url.indexOf("https:") ==0)
		{
			return sendSecurePost(url, urlParameters);
		}else
		{
		   return  sendUnSecurePost(url, urlParameters);
		}
		

	}

	private String sendSecurePost(String url,String urlParameters) throws Exception
	{
		//String url = "https://selfsolve.apple.com/wcResults.do";
				URL obj = new URL(url);
				HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				//HttpURLConnection con = (HttpURLConnection) obj.openConnection();

				//add reuqest header
				con.setRequestMethod("POST");
				con.setRequestProperty("User-Agent", USER_AGENT);
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				//con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
				con.setRequestProperty("Content-Type", CONTENT_TYPE);

				//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

				// Send post request
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Post parameters : " + urlParameters);
				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				//print result
				System.out.println(response.toString());
				return response.toString();
	}
	
	private String sendUnSecurePost(String url,String urlParameters) throws Exception
	{
		//String url = "https://selfsolve.apple.com/wcResults.do";
				URL obj = new URL(url);
				//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();

				//add reuqest header
				con.setRequestMethod("POST");
				con.setRequestProperty("User-Agent", USER_AGENT);
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				con.setRequestProperty("Content-Type", CONTENT_TYPE);

				//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

				// Send post request
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Post parameters : " + urlParameters);
				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				//print result
				System.out.println(response.toString());
				return response.toString();
	}
	
	
}