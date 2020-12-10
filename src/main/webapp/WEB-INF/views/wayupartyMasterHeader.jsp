
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/x-icon" href="/resources/favicon.ico" />
    <title>WAYUPARTY</title>
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="/resources/css/normalize.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="/resources/css/main.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    
     <link rel="stylesheet" href="/resources/css/select2.min.css">
    <!-- Date Picker CSS -->
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
    
    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="/resources/css/all.min.css">
    <!-- Flaticon CSS -->
    <link rel="stylesheet" href="/resources/fonts/flaticon.css">
    <!-- Full Calender CSS -->
    <link rel="stylesheet" href="/resources/css/fullcalendar.min.css">
    <!-- Animate CSS -->
    <link rel="stylesheet" href="/resources/css/animate.min.css">
    
     <!-- Data Table CSS -->
    <link rel="stylesheet" href="/resources/css/jquery.dataTables.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/resources/css/style.css">
    <!-- Modernize js -->
    <script src="/resources/js/modernizr-3.6.0.min.js"></script>
    
    <script src="https://maps.google.com/maps/api/js?libraries=places&v=3&key=${Wayuparty_googleMapsLocationApiKey}" type="text/javascript"></script>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
</head>


<body>

 <!-- Header Menu Area Start Here -->
 
        <div class="navbar navbar-expand-md header-menu-one bg-light">
            <div class="nav-bar-header-one">
                <div class="header-logo">
                    <a href="${Wayuparty_appUrl}/dashboard">
                        <img src="/resources/img/logo.png" alt="logo" class="dashboardLogoImg">
                    </a>
                </div>
                 <div class="toggle-button sidebar-toggle">
                    <button type="button" class="item-link">
                        <span class="btn-icon-wrap">
                            <span></span>
                            <span></span>
                            <span></span>
                        </span>
                    </button>
                </div>
            </div>
            <div class="d-md-none mobile-nav-bar">
               <button class="navbar-toggler pulse-animation" type="button" data-toggle="collapse" data-target="#mobile-navbar" aria-expanded="false">
                    <i class="far fa-arrow-alt-circle-down"></i>
                </button>
                <button type="button" class="navbar-toggler sidebar-toggle-mobile">
                    <i class="fas fa-bars"></i>
                </button>
            </div>
            <div class="header-main-menu collapse navbar-collapse" id="mobile-navbar">
                <ul class="navbar-nav">
                   <li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                    <span><h3>${Wayuparty_vendorName}</h3></span> 
                    </sec:authorize>
                     
                   </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="navbar-item dropdown header-admin">
                        <a class="navbar-nav-link" href="#" role="button" data-toggle="dropdown"
                            aria-expanded="false">
                            <span class="admin-title">
                              <h5 class="item-title">${Wayuparty_loginUserName}</h5>
                                <span>${Wayuparty_loginUserRoleDisplayName}</span> 
                            </span>
                            
                            <span class="headProfileIcon">
                          		 <img src="${Wayuparty_loginUserImgUrl}" class="img-circle" onerror="predefineHeaderProfileImage(this)">
                            </span>
                        </a>
                        
                    </li>
           
           <sec:authorize access="hasRole('ROLE_USER')">          
         		<li class="navbar-item dropdown header-message">
                        <a class="navbar-nav-link dropdown-toggle" href="${Wayuparty_appUrl}/cart" aria-expanded="false">
                            <i class="shopping-cart"></i>
                            <span>${Wayuparty_cartCount}</span>
                        </a>
         		</li>
            </sec:authorize>         
                </ul>
            </div>
        </div>
        <!-- Header Menu Area End Here -->
 
</body>






<script>
	function predefineProfileImage(imageId){
		$(imageId).attr('src','${Wayuparty_appUrl}/resources/img/userprofile-img.png');
	}
	
</script>

<script>
	function predefineHeaderProfileImage(imageId){
		$(imageId).attr('src','${Wayuparty_appUrl}/resources/img/userprofile-img.png');
	}
	
</script>

<script>
	function predefineVendorProfileImage(imageId){
		$(imageId).attr('src','${Wayuparty_appUrl}/resources/img/default-vendor-img.jpg');
	}
	
</script>

<script>
	function predefineVendorServiceImage(imageId){
		$(imageId).attr('src','${Wayuparty_appUrl}/resources/img/glass.jpg');
	}
	
</script>

</html>