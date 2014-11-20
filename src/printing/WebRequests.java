package printing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebRequests {
	String stringURL;
	String sendingString;
	int responseCode;
	
	/*
	 * Returning 200 means everything is ok
	 */
	 public int getResponseCode() {
		return responseCode;
	}

	public String getSendingString() {
		return sendingString;
	}

	public void setSendingString(String sendingString) {
		this.sendingString = sendingString;
	}

	HttpURLConnection connection;
	String responseText;
	 
	public WebRequests(String _urlString, String typeOfRequest){
		try{
			
			stringURL = _urlString;
			 URL url = new URL(stringURL);
	         connection = (HttpURLConnection) url.openConnection();
	         connection.setRequestMethod(typeOfRequest);
	         connection.setDoOutput(true);
	         
		} catch (MalformedURLException e) {
			
			responseText = "Error " +e.toString();
			System.out.print(responseText);
        }   catch (IOException e) {
        	e.printStackTrace();
        	responseText = "Error " +e.toString();
        	System.out.print(responseText);
        }
	}
	
	public void AddRequestProperty(String name, String property){
		
		if(connection != null){
		  connection.setRequestProperty(name,property);
		
		} else {
			responseText = "Error - could not add property to webrequest";
			 System.out.print(responseText);
		}
	}
	
//	public void SetRequestMethod(String method){
//		if(connection != null){
//			String result;
//			try {
//				connection.setRequestMethod(method);
//				result = connection.getRequestMethod();
//			} catch (ProtocolException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	
	public String SendRequest(){
		
		responseText = "";
		if(connection != null){
			
			try{
				
				 System.out.println(connection.getURL());
				 System.out.println(connection.getHeaderFields());
				 System.out.println(connection.getRequestMethod());
			
		         //writer.write("message=" + message);
				 if(sendingString != null){
					 OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
					 System.out.println("Sending string: "+sendingString);
			         writer.write(sendingString ); // write the json request
			         writer.close();
				 } else {
					 // http://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily
					 connection.connect();
				 }
		         
		         responseCode= connection.getResponseCode();
	 
		         if (responseCode == HttpURLConnection.HTTP_OK) {
		        	
		         	responseText= receiveResponse(connection);
		             // OK
		         } else {
		         	responseText= receiveResponse(connection) + "\n"+connection.getResponseCode() ;
		         }
			} 
			 catch (MalformedURLException e) {
				 e.printStackTrace();
		        // ...
		    } catch (IOException e) {
		    	e.printStackTrace();
		        // ...
		    }
		}
			
		return responseText;
	}
	
	///
	//
	// http://www.java2s.com/Code/Android/Network/ReceiveResponsefromHttpURLConnection.htm
	//
	public static String receiveResponse(HttpURLConnection conn)
		      throws IOException {
		    conn.setConnectTimeout(10000);
		    conn.setReadTimeout(10000);
		    // retrieve the response from server
		    InputStream is = null;
		    try {
		      is = conn.getInputStream();
		      int ch;
		      StringBuffer sb = new StringBuffer();
		      while ((ch = is.read()) != -1) {
		        sb.append((char) ch);
		      }
		      return sb.toString();
		    } catch (IOException e) {
		      throw e;
		    } finally {
		      if (is != null) {
		        is.close();
		      }
		    }
		  }

}

//try {
//URL url = new URL(URL);
//HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//connection.setDoOutput(true);
//connection.setRequestProperty("X-Api-Key","2D9548F444144BA2B118B401C908D9E9");
//connection.setRequestProperty("Content-type", "application/json");
//connection.setRequestMethod("POST");
//
//OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
////writer.write("message=" + message);
//writer.write(data.toString()); // write the json request
//writer.close();
//
//if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//	responseText= receiveResponse(connection);
//  // OK
//} else {
//	responseText= receiveResponse(connection) + "\n"+connection.getResponseCode() ;
//}
//} catch (MalformedURLException e) {
//// ...
//} catch (IOException e) {
//// ...
//}

//System.out.println("Working Directory = " +
//    System.getProperty("user.dir"));

