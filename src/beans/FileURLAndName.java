package beans;

public class FileURLAndName implements java.io.Serializable{
	
	/**
	 * 
	 */
	static int  counter = 1;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Add the url and the name , and add an id number when adding the object
	 * @param _urlApiCall
	 */
	public FileURLAndName(String _urlApiCall){
		int slashLoc = _urlApiCall.lastIndexOf('/')+1;		//  Add +1 so we start after the slash
		filename = _urlApiCall.substring(slashLoc);
		urlApiCall = _urlApiCall;	
		
		id = counter;
		counter++;
	}
	
	/**
	 * The filename on the octoprint server
	 */
	public String filename;
	
	public String getFilename() {
		return filename;
	}

	/**
	 * String that can be used as an API call
	 */
	public String urlApiCall;
	
	/**
	 * The file number, autoincrement
	 * Used for identification. 
	 */
	public int id;
	
	
	public int getId() {
		return id;
	}
	public String getUrlApiCall() {
		return urlApiCall;
	}	

}
