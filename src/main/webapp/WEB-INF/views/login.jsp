

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/faviconlogo.png">
    <title>WAYUPARTY | Login</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>

<style>
img:hover {
    cursor: pointer;
}
</style>

<body>
    <div id="preloader"></div>
    <!-- Login Page Start Here -->
    <div class="login-page-wrap">
        <div class="login-page-content">
            <div class="login-box">
                <div class="item-logo">
                    <img src="/resources/img/logo.png" alt="logo" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/home'" style="width: 250px; height: auto">
                </div>
               <form class="loginForm" name="login-form" method="post" action="/login">
                    <div class="form-group">
                        <label>User name<span class="text-danger">&nbsp;*</span></label>
                        <input type="text" placeholder="Enter user name" class="form-control" name="userName"  required>
                        
                    </div>
                    <div class="form-group">
                        <label>Password<span class="text-danger">&nbsp;*</span></label>
                        <input type="password" placeholder="Enter password" class="form-control" name="password"  required>
                    </div>
                    <div class="form-group d-flex align-items-center justify-content-between">
                        <a href="#" onClick="javascript:window.location.href = '#'" class="forgot-btn">Forgot Password?</a>
                    </div>
                    
                    <div class="modal-footer">
                                <input type="submit"  value="Get Started" class="btn btn-success" />
                                <input type="button"  value="Cancel" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/home'" class="btn btn-danger" />
                            </div>
                    
                    <div class="form-group lglink" style="text-align: left">
                                <a href="#" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/registerUser'" class="logina">Register New User!</a>  
                    </div>
                    
                     <c:if test="${!empty login_password_error}">
			   			<p class="redColor" id="colorHiding" style="display:block">Your username or password is incorrect </p>
					</c:if>
                </form>
            </div>
        </div>
    </div>
    <!-- Login Page End Here -->
    
    <!-- jquery-->
    <script src="/resources/js/jquery-3.3.1.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/jquery.scrollUp.min.js"></script>
    <script src="/resources/js/main.js"></script>
   
    
    



