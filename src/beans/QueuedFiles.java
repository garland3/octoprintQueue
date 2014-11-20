package beans;

public class QueuedFiles implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String filename;
	String estimatedPrintTime;
	Boolean activePrint = null;
	int idQueue;
	
	public int getIdQueue() {
		return idQueue;
	}

	public void setIdQueue(int idQueue) {
		this.idQueue = idQueue;
	}

	public Boolean getActivePrint() {
		return activePrint;
	}

	public void setActivePrint(Boolean activePrint) {
		this.activePrint = activePrint;
	}

	public QueuedFiles(String _filename){
		filename = _filename;
		estimatedPrintTime = null;
	}
	
	public QueuedFiles(String _filename,Boolean active, int _id){
		filename = _filename;
		estimatedPrintTime = null;
		activePrint = active;
		idQueue = _id;
	}

	public String getEstimatedPrintTime() {
		return estimatedPrintTime;
	}

	public String getFilename() {
		return filename;
	}
	

}
