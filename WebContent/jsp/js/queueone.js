/**
 * 
 */
$(document).ready(function(){
	
	/**
	 * Get the nearest row
	 */
	function getRow(formElement) {
	    return $(formElement).closest('tr').attr('id');
	}
	
	/**
	 * When a button of class "print button is clicked, then send the "print" and "id" to the server as a GET request
	 */
  $(".printbutton").click(function(){	 
		  //	alert("Advance the queue clicked");
			 $.get("./MainQueue",
					 {
						 action:"print",
						 id:getRow(this)
					 },setTimeout(function(){location.reload(true);},500)); // the location.reload cases the jsp page to refresh. 
		  });
  
  	/**
	 * When the Next button is clicked, then send the message to the main queue. 
	 */
  $(".nextbutton").click(function(){	 
	  //	alert("Advance the queue clicked");
		 $.get("./AdvanceTheQueue",
				 {
					 action:"advance",
					 id:getRow(this)
				 },setTimeout(function(){location.reload(true);},500)); // the location.reload cases the jsp page to refresh. 
	  });
  
		  /**
			 * When a button of class "print button is clicked, then send the "print" and "id" to the server as a GET request
			 */
		$(".removebutton").click(function(){	 
			  //	alert("Advance the queue clicked");
			 $.get("./RemoveFromQueue",
					 {
						 action:"remove",
						 id:getRow(this)
					 },setTimeout(function(){location.reload(true);},500)); // the location.reload cases the jsp page to refresh. 
		  });
  
  
  // Find all the "true" classes, and change them to "info" which will highlight them according to the bootstraps documentation
  // This will show the active print
  //document.getElementById("true").className += " info";
  //document.getElementsByTagName(tagname)
  x =document.getElementsByClassName("true");
  y =  x[0];
  y.className = " info";
  
  //$("true").addClass("info");
 // $(".true").className += " info";
 // var x = document.getElementsByClassName("true");
  // x[1].className += "info";
});