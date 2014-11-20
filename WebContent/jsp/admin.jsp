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
                <h1 class="page-header"> Admin Login
                  </h1>
                   <br>
               </div>
        </div>
<%--                     <small><jsp:getProperty name="queB" property="mainMessage"/></small> --%>
              
<!--                 <ol class="breadcrumb"> -->
<!--                     <li><a href="index.html">Home</a> -->
<!--                     </li> -->
<!--                     <li class="active">Two Column Portfolio</li> -->
<!--                 </ol> -->
         
        <!-- /.row -->
        
<!--          Show LoginIn Information  -->

 			<div class="row">
 			    <div class="col-sm-3"></div>
 			    
		         <div class="col-sm-6">
			         <form role="form" action="./admin"  method="GET">
						  <div class="form-group">
						    <label for="username">User name</label>
						  	<input type="text" name = "username" id="username"class="form-control" placeholder="User name">
						  </div>
						  <div class="form-group">
						    <label for="password">Password</label>
						    <input type="password" name="password" class="form-control" id="password" placeholder="Password">
						  </div>
						  
						  <div class="form-group">
						    <label for="apikey">Octoprint API Key</label>
						    <input type="password" name="apikey" class="form-control" id="apikey" placeholder="apikey">
						  </div>
						  
						   <div class="form-group">
						    <label for="dbpassword">Database Password</label>
						    <input type="password" name="dbpassword" class="form-control" id="dbpassword" placeholder="dbpassword">
						  </div>
						  
						   <div class="form-group">
						    <label for="dbusername">Database Username</label>
						    <input type="text" name="dbusername" class="form-control" id="dbusername" placeholder="dbusername">
						  </div>
						  
						  <div class="form-group">
						    <label for="dbconnectionstring">Database Connection String</label>
						    <input type="text" name="dbconnectionstring" class="form-control" id="dbconnectionstring" placeholder="dbconnectionstring">
						  </div>
						  
						  <div class="form-group">
						    <div class="form-group">
						      <button type="submit" class="btn btn-default">Sign in</button>
						    </div>
					    </div>
					 
					</form>
				
				 </div>
				     
				   
		 		</div>
	 		  <div class="col-sm-3"></div>
	 	
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
