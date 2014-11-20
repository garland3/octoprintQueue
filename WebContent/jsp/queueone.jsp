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

    <title>3D Printer Queuee</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/modern-business.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">3D Printing Queue</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/login">Login</a>
                    </li>
<!--                     <li> -->
<!--                         <a href="services.html">Services</a> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                         <a href="contact.html">Contact</a> -->
<!--                     </li> -->
<!--                     <li class="dropdown active"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown">Portfolio <b class="caret"></b></a> -->
<!--                         <ul class="dropdown-menu"> -->
<!--                             <li> -->
<!--                                 <a href="portfolio-1-col.html">1 Column Portfolio</a> -->
<!--                             </li> -->
<!--                             <li class="active"> -->
<!--                                 <a href="portfolio-2-col.html">2 Column Portfolio</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="portfolio-3-col.html">3 Column Portfolio</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="portfolio-4-col.html">4 Column Portfolio</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="portfolio-item.html">Single Portfolio Item</a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     <li class="dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown">Blog <b class="caret"></b></a> -->
<!--                         <ul class="dropdown-menu"> -->
<!--                             <li> -->
<!--                                 <a href="blog-home-1.html">Blog Home 1</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="blog-home-2.html">Blog Home 2</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="blog-post.html">Blog Post</a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     <li class="dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown">Other Pages <b class="caret"></b></a> -->
<!--                         <ul class="dropdown-menu"> -->
<!--                             <li> -->
<!--                                 <a href="full-width.html">Full Width Page</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="sidebar.html">Sidebar Page</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="faq.html">FAQ</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="404.html">404</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="pricing.html">Pricing Table</a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </li> -->
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
    
    <jsp:useBean id="queB"   scope="session"  class="beans.QueueBean" />


        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"> 3D Printer Queue Control 
                   <br>
                    <small><jsp:getProperty name="queB" property="mainMessage"/></p> </small>
                </h1>
<!--                 <ol class="breadcrumb"> -->
<!--                     <li><a href="index.html">Home</a> -->
<!--                     </li> -->
<!--                     <li class="active">Two Column Portfolio</li> -->
<!--                 </ol> -->
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <div class="col-md-6 ">
                
                <h3>
                 Files Available on Octoprint<br><br>
               
                </h3>
            
              
                <table class="table">
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
				                <button class="btn btn-lg btn-default btn-block printbutton" type="button">Print</button>
				            </td>
				        </tr>
				    </c:forEach>
				</table>
             
            </div>
            <div class="col-md-6 ">
                
                <h3>
                 Queue
                </h3>
               <table class="table">
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
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <div class="col-md-6 img-portfolio">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-hover" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="portfolio-item.html">Project Three</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
            <div class="col-md-6 img-portfolio">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-hover" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="portfolio-item.html">Project Four</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <div class="col-md-6 img-portfolio">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-hover" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="portfolio-item.html">Project Five</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
            <div class="col-md-6 img-portfolio">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-hover" src="http://placehold.it/700x400" alt="">
                </a>
                <h3>
                    <a href="portfolio-item.html">Project Six</a>
                </h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam viverra euismod odio, gravida pellentesque urna varius vitae.</p>
            </div>
        </div>
        <!-- /.row -->

        <hr>

        <!-- Pagination -->
        <div class="row text-center">
            <div class="col-lg-12">
                <ul class="pagination">
                    <li>
                        <a href="#">&laquo;</a>
                    </li>
                    <li class="active">
                        <a href="#">1</a>
                    </li>
                    <li>
                        <a href="#">2</a>
                    </li>
                    <li>
                        <a href="#">3</a>
                    </li>
                    <li>
                        <a href="#">4</a>
                    </li>
                    <li>
                        <a href="#">5</a>
                    </li>
                    <li>
                        <a href="#">&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    
    <script src='/jsp/js/queueone.js' type="text/javascript"></script>

</body>

</html>
