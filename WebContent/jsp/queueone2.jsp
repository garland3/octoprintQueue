<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>3D Printer Queue</title>
    
  

    <!-- Bootstrap Core CSS -->
    <link href="./jsp/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="./jsp/css/modern-business.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="./jsp/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->


	<%@ include file="header.jsp" %>
	
    <!-- Page Content -->
    <div class="container">
    
    <jsp:useBean id="queB"   scope="session"  class="beans.QueueBean" />


        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-sm-12">
                <h1 class="page-header"> 3D Printer Queue Control 
                   <br>
                    <small><jsp:getProperty name="queB" property="mainMessage"/></small>
                </h1>
<!--                 <ol class="breadcrumb"> -->
<!--                     <li><a href="index.html">Home</a> -->
<!--                     </li> -->
<!--                     <li class="active">Two Column Portfolio</li> -->
<!--                 </ol> -->
            </div>
        </div>
        <!-- /.row -->
        
<!--          Show the next button  -->

 			<div class="row">
 			    <div class="col-sm-3"></div>
		         <div class="col-sm-6">
			         <p>	
						
					    <button class="button btn btn-primary  btn-block nextbutton" type="button">Advance Queue</button>
				   </p>
				     
				   
		 		</div>
	 		  <div class="col-sm-3"></div>
	 		</div>
	 		 <hr>

        

        <!-- Projects Row -->
        <div class="row">
            <div class="col-lg-6 ">
                
                <h3>
                 Files Available on Octoprint<br><br>
               
                </h3>
            
              
                <table class="table table-striped">
					<tr>
					    <td>File Name</td>
					    <td>Click to Add to Print Queue</td>
					</tr>
				    <c:forEach var="Bean" items="${queB.apiFileUrlList}">
				        <tr id= "${Bean.id}" >
				        	 <td>
				                <c:out value="${Bean.filename}"/>
				            </td>
				             <td>
				                <button class="btn btn-sm btn-default btn-block printbutton" type="button">Print</button>
				            </td>
				        </tr>
				    </c:forEach>
				</table>
             
            </div>
            
            
            <div class="col-lg-6 ">
                
                <h3>
                 Queue
                </h3>
               <table class="table activeprinttable">
				<tr>
				    <td>File Name</td>
			      <td>Remove</td>
				</tr>
				
			    <c:forEach var="Bean" items="${queB.queuedFileList}">
			        <tr   id= "<c:out value="${Bean.idQueue}"/>"  >
			        	 <td class= "<c:out value="${Bean.activePrint}"/>" 
			        	     
			        	    >
			                <c:out value="${Bean.filename}"/>
			               
			            </td>   
  						<td>
				                <button class="btn btn-sm btn-default btn-block removebutton" type="button">Remove</button>
				            </td>
			        </tr>
			    </c:forEach>
		</table>
            </div>
        </div>
        
        
        <!-- /.row -->
		 <hr>
	   <div class="row">
			 <div class="col-md-12 ">
				 <h4>Settings</h4>
				 Z height = 154.4<br>
				 
				 PLA plastic purple <br>
				 Temperature 210 C <br>
				 <a href="http://127.0.0.1:5000/">Link to Octoprint</a><br>
				 <a href="http://130.127.199.172:8080/">Link to Tomcat Home</a><br>
				 <a href="http://130.127.199.172:8080/manager/html">Link to Tomcat Manager</a><br>
				  <a href="http://130.127.199.172/phpmyadmin">Link to PHP myadmin</a> 
				 <a href="https://github.com/garland3/octoprintQueue">Link Source Code on GitHub</a>
			 </div>
		 </div>
      
		<br>
        <hr>

        <!-- Footer -->
     <%@ include file="footer.jsp" %>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="./jsp/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./jsp/js/bootstrap.min.js"></script>
    
    <script src='./jsp/js/queueone.js' type="text/javascript"></script>

</body>

</html>
