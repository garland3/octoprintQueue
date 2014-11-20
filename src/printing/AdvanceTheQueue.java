package printing;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import beans.FileURLAndName;
import beans.QueueBean;


@SuppressWarnings("serial")
public class AdvanceTheQueue extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		Logger log = Logger.getLogger(dbwrapper.class.getName());
		log.info("Advance the Queue\n");
		System.out.println("Advance the queue");
		
		HttpSession session = req.getSession();
		QueueBean queB = (QueueBean) session.getAttribute("queB");
		if(queB == null){
			queB = new QueueBean();
		}
		
		
		/*
		 * Check to see if ocotoprint is still printing. IF inactive, then proceed
		 * 
		 * Find the next file in Queued File list, and send the command to octoprint
		 */
		OctoprintCalls call = new OctoprintCalls();
		Boolean isOperational = call.IsPrinterOperational();
		FileURLAndName fileAndUrl = null;
		
		if(isOperational == true){
			// Operation, so ok, we can send a new print job
			
			// Update the DB
			dbwrapper d;
			try {
				d = new dbwrapper();				
				fileAndUrl = d.AdvanceTheQueue();
			} catch (SQLException e) {
				log.info("Error:" + e.toString());
				e.printStackTrace();
			}
			
			// Now send the new command to octoprint
			if(fileAndUrl!=null){
				OctoprintCalls o = new OctoprintCalls();
				o.SendPrintCommand(fileAndUrl);
			}
		
			
		} else {
			queB.setErrorFlag(1);
			queB.mainMessage = "Cannot advance the Queue while actively printing";
			
		}
		
		req.getRequestDispatcher("/MainQueue").forward(req, resp); // forward on to the jsp page

	}
}
