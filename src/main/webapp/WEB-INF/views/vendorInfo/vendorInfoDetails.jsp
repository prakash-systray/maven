
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/faviconlogo.png">
    <title>WAYUPARTY | Login</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="/resources/css/style.css">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<style>
.navbar-default .navbar-nav>li>a {
    color: white !important;
}
</style>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<body>
<nav class="navbar navbar-dark bg-dark">
                <a href="/home" id="homeLinkIcon" style="color: #be9c52;" class="fa fa-home fa-2x"></a>
                <a class="navbar-brand" href="#" style="padding-left: 21px; text-align: center;">
                    <img id="Image1" src="/resources/img/logo.png" style="height:50px;width:200px;">
                </a>
                <a id="user" class="nav-link" href="/login"><span class="fa fa-user fa-2x" style="color: #be9c52;"></span></a>
</nav>

<div id="wrapper" class="wrapper bg-ash mt-5">
        <div class="dashboard-page-one">
	          <div class="dashboard-content-one">
	            <div class="card mb-3">
	              <div id="vendorInfoLoadingDiv"></div>
	              <jsp:include page="vendorDetails.jsp" />
	              </div>
	             </div>
	     </div>
 </div>
 
  <footer class="footer" style="background-color: #343a40;">
            <div class="container">
                WAYUPARTY © 2019. copyrights all rights reserved. Developed by Acculytixs Pvt Ltd.
            </div>
 </footer>
</body>

</html>




 
 
	 
	             