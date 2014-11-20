package printing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import beans.FileURLAndName;
import beans.QueuedFiles;


public class dbwrapper {
	String url;
	Logger log;
	 Connection con;
	
	public dbwrapper() {
		
		log = Logger.getLogger(dbwrapper.class.getName());
		log.info("Starting DB wrapper\n");
		
		
	//	if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			  // Load the class that provides the new "jdbc:google:mysql://" prefix.
		//	  Class.forName("com.mysql.jdbc.GoogleDriver");
		//	  url = "jdbc:google:mysql://<your-project-id>:<your-instance-name>/<your-database-name>?user=root";
		//	} else {
			  // Local MySQL instance to use during development.
		//	  Class.forName("com.mysql.jdbc.Driver");
			  url = "jdbc:mysql://127.0.0.1:3306/ThreeDPrintingQueue?user=root";
		//	}
		
		
		
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver());
			// con = DriverManager.getConnection("jdbc:mysql:/localhost:3306/ThreeDPrintingQueue","root","");
			 
			 con = DriverManager.getConnection(ConnectionParams.dbconnectionstring, ConnectionParams.dbusername, ConnectionParams.dbpassword);
			//con = DriverManager.getConnection(url);
			 if(con == null){
				 log.info("Null DB connection\n");
				// eBean.errorMessage = "Could not connect to the db. Null";
				 return;
				 
			 }
		 } catch (SQLException | ClassNotFoundException e1) {
				
			 	log.info("Could not connect to the db\n");
			 	//eBean.errorMessage = "Could not connect to the db";
				e1.printStackTrace();
				return;
		}
		
	}
	
	public void AddFileToPrintQueueTable(FileURLAndName f) throws SQLException{
		
		
		PreparedStatement s1 = con.prepareStatement(
				"INSERT INTO Queue (filename,apicall)"+
				" VALUES ('"+f.getFilename() +"','"+f.getUrlApiCall() +"')");
        s1.execute();	    		 
    	
        con.close();
	}
	
	/**
	 * Gets a list of Queued files from the DB
	 * @return
	 * @throws SQLException
	 */
	public List<QueuedFiles> GetQueuedFileList() throws SQLException{
		List<QueuedFiles> list = new ArrayList<QueuedFiles> ();
		
		/**
		 * Make a SQL request to get the filenames int the queue, put the resutls into the list
		 */
		PreparedStatement s;
		try {
			s = con.prepareStatement("select filename,activeprint,idQueue from Queue");
			ResultSet sResult =  s.executeQuery();
				while (sResult.next()) {
					String filename = sResult.getString(1);
					Boolean active = sResult.getBoolean(2);
					int id = sResult.getInt(3);
					list.add(new QueuedFiles(filename,active,id));
					//String ExperimentName = sResult.getString(2);
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   catch (Exception e ) {
			log.info("Exception while advancing the queue :" +e.toString());
			e.printStackTrace();
		} finally{
			if(con!=null){
				 con.close();
			}
		
		}
    	
        return list;
		
	}
	
	public FileURLAndName AdvanceTheQueue() throws SQLException{
	
		log.info("Advance the queue db wrapper called");
		FileURLAndName fileAndUrl = null;
		/**
		 * Make a SQL request to get the filenames int the queue, put the resutls into the list
		 */
		PreparedStatement s;
		try {
			/*
			 * Get id of the the current active print file
			 * Change the active print to the next row with id+1
			 * -- Remove from old row
			 * -- Add to the new row
			 * 
			 *  Get the apicall from the new row
			 */
			
			
			// Find the active print in the list
			String name = null;
			int id ;
			Boolean active = false;
			String apicall = "";
			s = con.prepareStatement("select idQueue,filename,activeprint,apicall from Queue  ");
			ResultSet sResult =  s.executeQuery();
			
			// http://stackoverflow.com/questions/192078/how-do-i-get-the-size-of-a-java-sql-resultset
			int rowcount = 0;
			if (sResult.last()) {
			  rowcount = sResult.getRow();
			  sResult.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
			} 
			
			if (rowcount!=0){
				while(sResult.next() == true){
					
					 active = sResult.getBoolean(3);
					 
					 // If we find the current row to be the active print, then ...
					 if(active == true){
						 id = sResult.getInt(1);
						 name = sResult.getString(2);
						 log.info("Old active print: "+name+" id: "+Integer.toString(id));
						 
						 //Remove active print from current active print
						String statement = "update Queue set activeprint=false  where idQueue="+Integer.toString(id) ;							   
						log.info(statement);					
						s = con.prepareStatement(statement);
						s.execute();
						 
						 break;
					 }				 
				}
				
				if(active == true){
					
					/*
					 * If we did find an active, print, then advance to the next row and set it as the active print. 
					 */
					
					// Advance to the next row. 
					sResult.next() ;
					 id = sResult.getInt(1);
					 name = sResult.getString(2);
					 active = sResult.getBoolean(3);
					 apicall = sResult.getString(4);
					 
					 String statement = "update Queue set activeprint=true  where idQueue="+Integer.toString(id) ;							   
					log.info(statement);					
					s = con.prepareStatement(statement);
					s.execute();
					
					// Create the object to return
					fileAndUrl = new FileURLAndName(apicall);
					
					
					
				} else {
				// If we did not break out of the loop, then we did not find an active print. 
					// Set the first row to the active print
					
						System.out.println("Nothing in the queue is actively printing, setting the first row");
						sResult.first();
						 id = sResult.getInt(1);
						 apicall = sResult.getString(4);
						 
						 String statement = "update Queue set activeprint=true  where idQueue="+Integer.toString(id) ;							   
						log.info(statement);					
						s = con.prepareStatement(statement);
						s.execute();	
						
						// Create the object to return
						fileAndUrl = new FileURLAndName(apicall);
				}
				
				
				
			} else {
				
				/*
				 * If there are no rows, then do nothing
				 */
				
				log.info("No items in the Queue");
			}
				
				
		} catch (SQLException e ) {
			
			log.info("Exception while advancing the queue :" +e.toString());
			e.printStackTrace();
		}  catch (Exception e ) {
			log.info("Exception while advancing the queue :" +e.toString());
			e.printStackTrace();
		} finally{
			if(con!=null){
				 con.close();
			}
		}
    	
       return fileAndUrl;
		
	}

	/**
	 * Remove stuff from the Queue
	 * @param integer
	 */
	public void RemoveFromQueue(Integer integer) {
		
			   
		//log.info(statement);					
		try {
			PreparedStatement s;
			String statement2 = "delete from Queue where idQueue=" + integer.toString();	
			s = con.prepareStatement(statement2);
			s.execute();
		} catch (SQLException e) {
			log.info("Could not remove the file");
			e.printStackTrace();
		}
	
		
	}
}
