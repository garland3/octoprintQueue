<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basic Queue</title>

<script src='https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.1.min.js'
	type="text/javascript"></script>
<script src='/jsp/js/queueone.js' type="text/javascript"></script>

</head>
<body>
<h1>Basic Queue</h1>
<h3>Version 1</h3>


<jsp:useBean id="queB"   scope="session"  class="beans.QueueBean" />
<jsp:getProperty name="queB" property="mainMessage"/></p> 
<h3>Files Available to print</h3>

 <% // http://stackoverflow.com/questions/16331462/how-to-display-arraylist-of-bean-objects-on-jsp-page
   
  // http://stackoverflow.com/questions/5202774/how-to-access-array-list-in-jsp-if-i-pass-bean -->  
  // http://stackoverflow.com/tags/servlets/info
  // http://stackoverflow.com/tags/jsp/info
  // http://stackoverflow.com/tags/el/info%>

<table border="1" style="width:50%">
	<tr>
	    <td>File Name</td>
	    <td>Click to Add to Print Queue</td> 
<!-- 	    <td>hidden code api call</td> -->
	</tr>
    <c:forEach var="Bean" items="${queB.apiFileUrlList}">
        <tr id= "${Bean.id}" >
        	 <td>
                <c:out value="${Bean.filename}"/>
            </td>
             <td>
                <button class="printbutton" type="button">Print!</button>
            </td>
<!--             <td> -->
<%--                 <c:out value="${Bean.urlApiCall}"/> --%>
<!--             </td> -->
        </tr>
    </c:forEach>
</table>

<div>
	<p>	
	<h2>Click the Next button to advance the queue</h2>
    <button class="nextbutton" type="button">Next</button>
   </p>
</div>

<div>
<h2>Queued Files</h2>
	<p>	
	<table border="1" style="width:50%">
	<tr>
	    <td>File Name</td>
	      <td>Active Print</td>
	</tr>
	
    <c:forEach var="Bean" items="${queB.queuedFileList}">
        <tr >
        	 <td>
                <c:out value="${Bean.filename}"/>
            </td>   
            	 <td>
                <c:out value="${Bean.activePrint}"/>
            </td>   
        </tr>
    </c:forEach>
</table>
   </p>
</div>

</body>
</html>