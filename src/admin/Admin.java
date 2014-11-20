package admin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import printing.ConnectionParams;
import beans.FileURLAndName;


@SuppressWarnings("serial")
public class Admin extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		Logger log = Logger.getLogger(Admin.class.getName());
		log.info("Login\n");
		System.out.println("Login");
		
		/*
		 * Correct username and password are!!!
		 */
		String userNameAdmin = "admin";
		String passwordAdmin ="cedar";
		
		
		/**
		 * Check to see if they submitted any information
		 */
		String username = req.getParameter("username");
		String password = req.getParameter("password");
	//	String password = req.getParameter("password");
	//	String password = req.getParameter("password");
		if(username!= null && password != null){
			log.info("Attempt Login with : "+username+ " password: "+password+"\n");
			
			
			if(username.equals(userNameAdmin) && password.equals(passwordAdmin)){
				
				
				String apikey = req.getParameter("apikey");
				String dbpassword = req.getParameter("dbpassword");
				String dbusername = req.getParameter("dbusername");
				String dbconnectionstring = req.getParameter("dbconnectionstring");
				
				if(apikey!=null && dbpassword!= null && dbusername!= null && dbconnectionstring!= null){
					ConnectionParams.dbpassword=dbpassword;
					ConnectionParams.XapiKey=apikey;
					ConnectionParams.dbusername = dbusername;
					ConnectionParams.dbconnectionstring = dbconnectionstring;
				
					
					// Save the apikeys
					ConnectionParams.WriteKeysToFile(this,resp);
					
				} else{
				//	System.out.println("Either apikey  dbpassword is null");
					log.info("Either apikey, dbpassword,dbconnectionstring, or dbusername is null");
				}
				
			
			
			
			//	req.getRequestDispatcher("/jsp/admin.jsp").forward(req, resp); // forward on to the jsp page
			} else {
				log.info("Wrong username and password");
				
			}
			
		}
		
		req.getRequestDispatcher("/jsp/admin.jsp").forward(req, resp); // forward on to the jsp page
	}
}