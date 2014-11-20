package printing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.http.*;





@SuppressWarnings("serial")
public class _DPrintingQueueServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		final Logger log = Logger.getLogger(_DPrintingQueueServlet.class.getName());
		log.info("3Dprinting Queue Serverlet\n");
		
		String message = "";
		
	
		
		 try {
			  URL url = new URL("http://127.0.0.1:5000/api/files/local/fingerBrace.gco");
			  
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoOutput(true);
	           
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-type", "application/json");
	            connection.setRequestProperty("X-Api-Key","2D9548F444144BA2B118B401C908D9E9");
	            connection.setDoInput(true);
	            connection.setDoOutput(true);

	            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
	           writer.write("  {\"command\": \"select\",\"print\":\"true\"}  ");
	           writer.flush();
	          
	           // writer.write("  '{command: cancel}'  ");
	            writer.close();
	         //   connection.connect();
	    
	            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            	message+=receiveResponse(connection);
	            } else {
	                System.out.println(connection.getResponseCode());
	                message+=receiveResponse(connection);
	            }
	        } catch (MalformedURLException e) {
	            // ...
	        	 e.printStackTrace();
	        } catch (IOException e) {
	            // ...
	        	 e.printStackTrace();
	        }
		 
		 log.info(message);
		
		// https://cloud.google.com/appengine/docs/java/urlfetch/
		resp.setContentType("text/html");
		resp.getWriter().println(message);
	}
	
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
