package beans;

import java.util.ArrayList;
import java.util.List;


public class QueueBean implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	
	int errorFlag = 0;
	
	String errorMessage = "";
	


	
	/**
	 * List of FileURLAndName that represent files on the Octoprint server
	 */
	public List<FileURLAndName> apiFileUrlList = new ArrayList<FileURLAndName>();
	
	/**
	 * The main message we are to display. Perhaps an error message. 
	 */
	public String mainMessage = "Welcome to the 3D printer print job queueing system";
	
	/**
	 * List of files in the print queue DB
	 */
	public List<QueuedFiles> queuedFileList = new ArrayList<QueuedFiles>();
	
	/** --------------------------------
	 * 
	 *  Getters and Setters
	 *  
	  --------------------------------*/

	public String getMainMessage() {
		System.out.println("Getting main message:" + mainMessage);
		return mainMessage;
	}

	public List<QueuedFiles> getQueuedFileList() {
		return queuedFileList;
	}

	public void setMainMessage(String mainMessage) {
		this.mainMessage = mainMessage;
	}

	public List<FileURLAndName> getApiFileUrlList() {
		return apiFileUrlList;
	}

	/**
	 *  Take the list of URLS, and create the FileURLAndName objects and put them into the list. 
	 * @param listOfUrls
	 */
	public void setApiFileUrlList(List<String> listOfUrls) {
		for(int i = 0 ; i < listOfUrls.size() ; i++){
						
			apiFileUrlList.add(new FileURLAndName(listOfUrls.get(i)));
		   
		}
		
	}
	
	
	/**
	 * Find the FileURLAndName associated with a particular id
	 * @param _id
	 * @return
	 */
	public FileURLAndName getFileURLAndNameUsingID(int _id){
		for(FileURLAndName f : apiFileUrlList){
			if(f.getId()== _id){
				return f;
			}			
		}
		// if all else fails return null
		FileURLAndName f = null;
		return f;
	}
	
	
	public int getErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(int errorFlag) {
		this.errorFlag = errorFlag;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	

}

