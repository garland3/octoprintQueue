package printing;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
//import org.json.simple.JSONObject;




import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import beans.FileURLAndName;

public class OctoprintCalls {
	Logger log;
	
	/**
	 * 
	 * 
	 * This is helpful http://www.mkyong.com/java/json-simple-example-read-and-write-json/
	 * @return
	 */
	public  JSONObject GetFileList(){
		
		log = Logger.getLogger(dbwrapper.class.getName());
		log.info("Get file list \n");
		
		JSONObject data = null;
		// Send a request to get all the available files and put them in the Bean
			
		 
		String URL = ConnectionParams.getGetFiles();
		String responseText = "";
		    
	    WebRequests w= new WebRequests(URL, "GET");
	    w.AddRequestProperty("X-Api-Key",ConnectionParams.getXapiKey());
	    //w.AddRequestProperty("Content-type", "application/json");
	    //w.SetRequestMethod("GET");
	    //w.setSendingString(data.toString());
	    responseText+=w.SendRequest();
	    
		log.info( "Call Octoprint /api/files response\n");
		//log.info(responseText+ "\n\n");
	   // System.out.println(responseText);
	    
	    //try {
	    	//  Object obj=JSONValue.parse(responseText);
	    //	  JSONArray array=(JSONArray)obj;
	    	//data  = new JSONObject(responseText);
	    	
	    	JSONObject jsonObj;
	        JSONParser parser = new JSONParser();
	        
	        Object obj = null;
			try {
				obj = parser.parse(responseText);
			} catch (ParseException e) {
				responseText = "JSONObject error";
				e.printStackTrace();
			}
			JSONObject jsonObject = (JSONObject) obj;

			// JSONArray array=(JSONArray)obj;
		//} catch (JSONException e) {
		//	responseText = "JSONObject error";
		//	e.printStackTrace();
		//}
		    
	    return jsonObject;
	}
	
	/**
	 * Returns true if the Octoprint status is "Operational" = NOT Printing anything right now
	 * @return
	 */
	public Boolean IsPrinterOperational(){
		
		log = Logger.getLogger(dbwrapper.class.getName());
		log.info("IsPrinterOperational /api/job");
		
		JSONObject data = null;
		// Send a request to get all the available files and put them in the Bean
			
		 
		String URL = ConnectionParams.getStatus();
		String responseText = "";
		    
	    WebRequests w= new WebRequests(URL, "GET");
	    w.AddRequestProperty("X-Api-Key",ConnectionParams.getXapiKey());
	    //w.AddRequestProperty("Content-type", "application/json");
	    //w.SetRequestMethod("GET");
	    //w.setSendingString(data.toString());
	    responseText+=w.SendRequest();
	    
		//log.info( "Call Octoprint /api/files response\n");
		//log.info(responseText+ "\n\n");
	   // System.out.println(responseText);
	    
		String booleanState = "";
//	    try {
//	    	data  = new JSONObject(responseText);
//	    	 booleanState = data.getString("state");
//	    	log.info(booleanState);
//		} catch (JSONException e) {
//			responseText = "JSONObject error";
//			e.printStackTrace();
//		}
	    
	    JSONObject jsonObj;
        JSONParser parser = new JSONParser();
        
        Object obj = null;
		try {
			obj = parser.parse(responseText);
			jsonObj = (JSONObject) obj;
			booleanState =(String) jsonObj.get("state");
		} catch (ParseException e) {
			responseText = "JSONObject error";
			e.printStackTrace();
		}

	
	 
	    if(booleanState.equals("Operational")){
	    	return true;
	    } else{
	    	return false;
	    }
	    	
		
		
		
	}
	
	/**
	 * Sends a print command based on the url specified in the args object
	 * 
	 * Selects and prints the file
	 * @param furl
	 * @return true on success
	 */
	public Boolean SendPrintCommand(FileURLAndName furl){
		
		log = Logger.getLogger(dbwrapper.class.getName());
		log.info("SendPrintCommand /api/files .. command: select, print:true");
		//String message = "";
		
		//JSONObject data;
		//try {
		//data = new JSONObject( " {\"command\": \"select\",\"print\":\"true\"} ");
		//http://stackoverflow.com/questions/23628513/how-do-i-make-jsonobject-tostring-escape-unicode-characters
		//log.info(data.toString());
		// https://code.google.com/p/json-simple/w/list
		
		
		// Because I'm having issues with the octoprint calls and escaping the string, I'm writting, I'm
		// going to make the HttpURLConnection myself instead of using the WebRequests class

		 try {
			 	URL url = new URL(furl.getUrlApiCall());
			  
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoOutput(true);
	           
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-type", "application/json");
	            connection.setRequestProperty("X-Api-Key",ConnectionParams.getXapiKey());
	            connection.setDoInput(true);
	            connection.setDoOutput(true);

	            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
	            // Solution to escaped json file problem
	           writer.write("  {\"command\": \"select\",\"print\":\"true\"}  ");
	            writer.close();	        
	    
	            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            	//message+=receiveResponse(connection);
	            } else {
	                System.out.println(connection.getResponseCode());
	               // message+=receiveResponse(connection);
	            }
	        } catch (MalformedURLException e) {
	            // ...
	        	 e.printStackTrace();
	        } catch (IOException e) {
	            // ...
	        	 e.printStackTrace();
	        }
	    return true;
	 
		
	}

}
