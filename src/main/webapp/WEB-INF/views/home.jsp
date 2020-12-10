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
    
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<style>
.navbar-default .navbar-nav>li>a {
    color: white !important;
}
.col-sm-2 {
    width: 10.666667%;
}
.btn-vendor-search {
    color: #fff;
}

 .btn-secondary {
    color: #fff !important;
    background-color: #6c757d !important; 
    border-color: #6c757d !important;
    height: 32px !important;
    border-bottom-right-radius: 10px !important; 
    border-top-right-radius: 10px !important; 
} 
</style>

<body>
<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="clearfix">
	    <div class="navbar-header">
	    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
	      <img src="/resources/img/logo.png" alt="logo" class="homeHeader">
	    </div>
	      <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="/home">ABOUT</a></li>
	        <li><a href="#">SERVICES</a></li>
	        <li><a href="#">PRIVACY POLICY</a></li>
	        <li><a href="/login">LOGIN</a></li>
	      </ul>
	      </div>
	  </div>
</nav>


    <div class="container mt-10">
	    <div class="card mb-3 search_blo">
		    <div class="input-group search-blo">
		        <input name="vendors_txtSearch" type="text"  id="vendors_txtSearch" class="form-control"  placeholder="Search">
		        <div class="input-group-btn">
		            <a class="btn btn-secondary"><span class="fa fa-search"></span></a>
		        </div>
		    </div>
	    </div><br/>
 	<div id="registeredVendorsList" class="row"></div>
 	</div>
 
 <footer class="footer" style="background-color: #474747;">
                WAYUPARTY © 2019. copyrights all rights reserved. Developed by Acculytixs Pvt Ltd.
</footer>



  <script type="text/javascript">
		window.onload = function () {
			navigator.geolocation.getCurrentPosition(function(position) {
			    let latitude = position.coords.latitude;
			    let longitude = position.coords.longitude;
			    
			  });
			getRestaurantsList();
		 };
 </script>
 
 <script>
   function getRestaurantsList(){
	   
	    var appUrl ='${appUrl}';
	   
	   $("#registeredVendorsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
		    	type: "GET",
		    	 url: appUrl+"/ws/getAllregisteredRestaurantsList?${_csrf.parameterName}=${_csrf.token}", 
		        success: function(resultData) {
		   			
		        	var result = "";
		        	var currencyCode = '';
		        	 
		        	if(resultData.data.length > 0){
			        	for (var i=0; i<resultData.data.length; i++)
		   				{  
			        		var opt = resultData.data[i];
			        		
			        		var location = '';
			        		if(opt.location != ''){
			        			location = opt.location;
			        		}else{
			        			location = 'NO DATA';
			        		}
			        		
			        		currencyCode = getCurrency(opt.currency);
			        		
			        		result = result +'<a href="'+appUrl+'/ws/bookService?vendorUUID='+opt.vendorUUID+'" class="card-link"><div class="col-md-4 mb-5">';
			        		result = result +'<div class="vendors-card">';
			        		result = result +'<div class="cardbody">';
			        		result = result +'<img src="'+opt.vendorProfileImg+'" onerror="predefineVendorProfileImage(this)">';
			        		result = result +'</div>';
			        		result = result +'<div class="card">';
			        		result = result +'<div class="card-footer">';
			        		result = result +'<div class="clearfix vendors-title text-success">';
			        		result = result +'<h5>';
			        		result = result +opt.vendorName;
			        		result = result +'</h5>';
			        		result = result +'</div>';
			        		result = result +'<div class="divpadding">';
			        		result = result +'<div class="clearfix row">';
			        		result = result +'<div class="col-sm-1 col-xs-2">';
			        		result = result +'<i class="fa fa-map-marker text-black" aria-hidden="true"></i>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-11 col-xs-10 text-black">'+location+'</div>';
			        		result = result +'<div class="col-sm-1 col-xs-2">';
			        		result = result +'<i class="fa fa-th-list text-black" aria-hidden="true"></i>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-11 col-xs-10 text-black">'+opt.bestSellingItems+'</div>';
			        		result = result +'<div class="col-sm-1 col-xs-2 text-black">';
			        		result = result +'<span>'+currencyCode+'</span>';
			        		result = result +'</div>';
			        		result = result +'<div class="col-sm-11 col-xs-10 text-black">'+opt.costForTwoPeople+'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'<div class="card-footer text-muted">';
			        		result = result +'&nbsp;';
			        		result = result +'<div class="container-fluid" style="position: relative; text-align: center; color: white;">';
			        		result = result +'<div class="bottom_left" style="position: absolute; bottom: 0px; left: 5px;">';
			        		result = result +'<a href="'+appUrl+'/ws/vendorInfo?vendorUUID='+opt.vendorUUID+'" class="card-link"><span class="fa fa-search-plus">&nbsp;</span>Explore</a>';
			        		result = result +'</div>';
			        		result = result +'<div class="bottom_left" style="position: absolute; bottom: 0px; right: 5px;">';
			        		result = result +'<a href="'+appUrl+'/ws/bookService?vendorUUID='+opt.vendorUUID+'" class="card-link"><i class="fa fa-bullhorn" aria-hidden="true"></i>&nbsp;Services </a>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div>';
			        		result = result +'</div></a>';
			        		
			        	
		   				}
		        	}else{
	   					result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/no-records.png" alt=""/></td>';
	   				}
		        	
		        	 $("#registeredVendorsList").empty();  
	   	    	     $("#registeredVendorsList").append(result);
		        },
		    }); 
	   
   }
   </script>
   
   <script>
function getCurrency(currency){
	var currencyType;
	switch (currency) {
	  case "USD":
		currencyType = "&#36";
	    break;
	  case "EUR":
		  currencyType = "&#128";
	    break;
	  case "CRC":
		  currencyType = "&#8353";
	    break;
	  case "GBP":
		  currencyType = "&#163";
	    break;
	  case "ILS":
		  currencyType = "&#8362";
	    break;
	  case "INR":
		  currencyType = "&#x20B9";
	    break;
	  case  "JPY":
		  currencyType = "&#165";
	    break;
	  case  "KRW":
		  currencyType = "&#8361";
		break;
	  case  "NGN":
		  currencyType = "&#8358";
		break;
	  case  "PHP":
		  currencyType = "&#8369";
	    break;
	  case  "PLN":
		  currencyType = "PLN";
	    break;
	  case  "PYG":
		  currencyType = "&#8370";
	    break;
	  case  "THB":
		  currencyType = "&#3647";
	    break;
	  case  "UAH":
		  currencyType = "&#8372";
	    break;
	  case  "VND":
		  currencyType = "&#8363";
	    break;
	  case  "":
		  currencyType = "&#164";
	    break;
	}
	
	return currencyType
}
</script>
   
<script>
	function predefineVendorProfileImage(imageId){
		$(imageId).attr('src','${Wayuparty_appUrl}/resources/img/default-vendor-img.jpg');
	}
	
</script>
   
 

</body>

</html>

