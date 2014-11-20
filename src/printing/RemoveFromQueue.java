package printing;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import beans.FileURLAndName;


@SuppressWarnings("serial")
public class RemoveFromQueue extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		Logger log = Logger.getLogger(RemoveFromQueue.class.getName());
		log.info("Remove From  Queue\n");
		//System.out.println("Advance the queue");
		
		String id = req.getParameter("id"); // idQueue
		String action = req.getParameter("action"); // remove
		log.info("Remove an item from the queue \nid: "+id+ " action: "+action+"\n");
		
		// If not null and if not "", then we will try to remove the object
		if(id!=null && !id.equals("") && action!=null && !action.equals("")){
			dbwrapper d = new dbwrapper();
			d.RemoveFromQueue(Integer.parseInt(id));
			
		}
		
//		/*
//		 * Check to see if ocotoprint is still printing. IF inactive, then proceed
//		 * 
//		 * Find the next file in Queued File list, and send the command to octoprint
//		 */
//		OctoprintCalls call = new OctoprintCalls();
//		Boolean isOperational = call.IsPrinterOperational();
//		FileURLAndName fileAndUrl = null;
//		
//		if(isOperational == true){
//			// Operation, so ok, we can send a new print job
//			
//			// Update the DB
//			dbwrapper d;
//			try {
//				d = new dbwrapper();				
//				fileAndUrl = d.AdvanceTheQueue();
//			} catch (ClassNotFoundException | SQLException e) {
//				log.info("Error:" + e.toString());
//				e.printStackTrace();
//			}
//			
//			// Now send the new command to octoprint
//			if(fileAndUrl!=null){
//				OctoprintCalls o = new OctoprintCalls();
//				o.SendPrintCommand(fileAndUrl);
//			}
//		
//			
//		} else {
//			
//		}
		
		req.getRequestDispatcher("/MainQueue").forward(req, resp); // forward on to the jsp page

	}
}
