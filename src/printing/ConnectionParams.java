package printing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class ConnectionParams {
	public static String dbpassword ="";
	public static String dbusername ="";
	public static String dbconnectionstring ="";
	public static String XapiKey ="";
	//static String XapiKey = "2D9548F444144BA2B118B401C908D9E9"; // laptop
	//static String XapiKey = "98F1F86D667E4416A561305FBB48A1F6"; // actual 3d printer
	public static String getXapiKey() {
		//if(XapiKey.equals("")){
		//	GetKeyFromFile();
		//}
		
		return XapiKey;
	}

	// NOTE: this is where the server is running, and not where the code in Eclipse is located. 
	// C:\Users\Anthony G\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\QueueV2\WEB-INF
	//
	// Look at the console output to see the file location for the apikey.txt
	
	public static void GetKeyFromFile(HttpServlet h, HttpServletResponse r)  {
		String filename = "/WEB-INF/apikey.txt";
		
		ServletContext context = h.getServletContext();
		String path = context.getRealPath(filename);
		//context.getreal
		// http://stackoverflow.com/questions/12160639/what-does-mean-in-the-method-servletcontext-getrealpath
		Properties p = new Properties();
		
		//
		// First get the file InputStream using ServletContext.getResourceAsStream()
		// method.
		//
		try {
			InputStream is = new FileInputStream(path);
			if (is != null) {
				p.load(is);
				XapiKey = p.getProperty("apikey");
				//AddressOfOcotoprint = p.getProperty("AddressOfOcotoprint");
			    dbpassword =p.getProperty("dbpassword");
			    dbusername =p.getProperty("dbusername");
			    dbconnectionstring =p.getProperty("dbconnectionstring");
			    
				System.out.println(p.toString());
				System.out.println(path);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		
	}
	
	public static void WriteKeysToFile(HttpServlet h, HttpServletResponse r)  {
		String filename = "/WEB-INF/apikey.txt";
		
		ServletContext context = h.getServletContext();
		String path = context.getRealPath(filename);
		//context.getreal
		// http://stackoverflow.com/questions/12160639/what-does-mean-in-the-method-servletcontext-getrealpath
		Properties prop = new Properties();
		
		//
		// First get the file InputStream using ServletContext.getResourceAsStream()
		// method.
		//
		//OutputStream output = null;
		try {
			//output = new FileOutputStream(path);
			 
			// set the properties value
			prop.setProperty("dbpassword", dbpassword);
			prop.setProperty("apikey", XapiKey);
			prop.setProperty("dbusername", dbusername);
			prop.setProperty("dbconnectionstring", dbconnectionstring);
		//	prop.setProperty("dbpassword", "password");
			
			System.out.println(prop.toString());
			System.out.println(path);
			
			File file = new File(path);
			FileOutputStream fileOut = new FileOutputStream(file);
			
			prop.store(fileOut, "Connection properties");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		
	}

	static String AddressOfOcotoprint = "http://127.0.0.1:5000"; // when deploying, this will be the same. 
	//static String AddressOfOcotoprint = "http://130.127.199.35:5000";
	static String GetFiles = "/api/files";
	static String GetJob = "/api/job";

	/*
	 * Get files list 
	 *  Return the whole address
	 */
	
	public static String getGetFiles() {
		return AddressOfOcotoprint+GetFiles;
	}
	
	public static String getStatus() {
		return AddressOfOcotoprint+GetJob;
	}

	

}
