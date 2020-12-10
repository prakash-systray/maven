

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
  
  <style>
  p {
    margin: 0 0 0px 0 !important;
    font-size: 11px !important;
    font-weight: bold !important;
  }
  
  img:hover {
    cursor: pointer;
  }
  </style>  
</head>

<body>
    <div id="preloader"></div>
    <!-- Login Page Start Here -->
    <div class="login-page-wrap">
        <div class="login-page-content">
        <div id="userLoadingDiv"></div>
              <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                    <i class="far fa-hand-point-right bg-light-green3"></i>
                                   <span id="successMsg" style="color: white;"></span> 
             </div>
             
             <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
                                    <i class="fas fa-times bg-pink3"></i>
                                   <span id="invalidMsg" style="color: white;"></span> 
             </div>
            <div class="login-box">
                <div class="item-logo">
                    <img src="/resources/img/logo.png" alt="logo" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/home'" style="width: 250px; height: auto">
                </div>
              <form class="loginForm" id="register_user_form"  name="register_user_form">
                    <div class="form-group">
                        <label>Name<span class="text-danger">&nbsp;*</span></label>
                        <input type="text" placeholder="Enter your name" class="form-control" name="loginUserName"  field-name="Name" data-validation="required validate_Space validate_AlphaNumber validate_length length1-250">
                        
                    </div>
                <div class="row">      
                    <div class="col-xl-6 col-lg-6 col-12 form-group" id="emailDiv">
                        <label>Email<span class="text-danger">&nbsp;*</span></label>
                        <input type="text" placeholder="Enter your email " class="form-control" onblur="validateUserEmail();" name="email" id="email" field-name="Email" data-validation="required validate_Space validate_email validate_length length1-100">
                    </div>
                    
                    <div class="col-xl-6 col-lg-6 col-12 form-group" id="mobileDiv">
                        <label>Mobile<span class="text-danger">&nbsp;*</span></label>
                        <input type="text" placeholder="Enter your mobile " class="form-control" onblur="validateUserMobile();" name="mobile" id="mobile"  field-name="Mobile" data-validation="required validate_Space validate_int fixed_length length10">
                    </div>
              </div>
              
              <div class="row">      
                    <div class="col-xl-6 col-lg-6 col-12  form-group passwordConditions" id="passwordDiv">
                        <label>Password<span class="text-danger">&nbsp;*</span></label>
                        <input type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d.*)(?=.*\W.*)[a-zA-Z0-9\S]{8,}$" field-name="Password"  data-validation="required validate_length length8-15" placeholder="Enter password" class="form-control" name="password" value="${password}" id="password"  onkeydown="$(this).clear();" onkeyup="passwordValidation('password','fp_letter','fp_capital','fp_number','fp_special','fp_length');">
                    
                      <div id="fp_message" class="passHint" style="left:-300px; box-shadow: 1px 1px 1px 2px #ddd; top:-10px;">
				  			<p  id="fp_letter" class="invalid">At least one lowercase letter</p>
				  			<p  id="fp_capital" class="invalid">At least one uppercase letter</p>
				  			<p  id="fp_number" class="invalid">At least one number</p>
				  			<p  id="fp_special" class="invalid">At least one special character</p>
				  			<p  id="fp_length" class="invalid">Minimum 8 characters required</p>
						</div> 
                    </div>
                    
                    
                    <div class="col-xl-6 col-lg-6 col-12  form-group">
                        <label>Confirm Password<span class="text-danger">&nbsp;*</span></label>
                        <input type="password" placeholder="Enter confirm password" class="form-control" name="confirmPassword" id="confirmPassword"  data-validation="required" field-name="Confirm Password">
                    </div>
                    
                    <div class="modal-footer">
                         <input type="button"  value="Submit" id="submit_button" class="btn btn-success" onclick="checkPasswords();"/>
                         <input type="button"  value="Cancel" class="btn btn-success" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/home'" class="btn btn-danger" />
                         <input type="button"  value="Login" class="btn btn-success" onClick="javascript:window.location.href = '${Wayuparty_appUrl}/login'" class="btn btn-login" />
                    </div>
                </div>    
                    
                    
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
    <script src="/resources/js/common.js?"></script>
    <script src="/resources/js/formvalidator.js?"></script>
   
    
<script>
$('body').on('blur', '#register_user_form', function() {
	$("#register_user_form").showHelpOnFocus();
	$("#register_user_form").validateOnBlur(false, validationSettings);  
});
</script>


 <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 

<script type="text/javascript">
	function checkPasswords() {   
		var pass1 = document.getElementById('password');  
	    var pass2 = document.getElementById('confirmPassword');
	    if(pass1.value == pass2.value){
			    $('input#password').removeAttr( "class record-exist record-exist-errormsg");
				$('#passwordDiv').find('p.jquery_form_error_message').remove();
				$('input#password').attr('class','valid form-control');
				saveUser();
	    }
	    else{ 
				
				$('#passwordDiv').find('p.jquery_form_error_message').remove(); 
	    		$('input#password').attr('class','error form-control');
	    		$("input#password").after("<p class='jquery_form_error_message'>Passwords Not Matched</p>");
				$('input#password').attr('record-exist','yes');
				$('input#password').attr('record-exist-errorMsg',' Password and confirm password should be same'); 
	    }
   }

	
	 function saveUser(){
		   if ($('#register_user_form').validate(false, validationSettings)){
			   $("#userLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
			   var appUrl = "${appUrl}";
	           var formData = getWayupayFormData("register_user_form");
	            $.ajax({
					 type : "POST",
					 data: formData,
		    	     processData: false,
		    	     contentType:false,
		        	    	 url: appUrl+"/ws/saveUserRegistration?${_csrf.parameterName}=${_csrf.token}", 
					        success : function(result) {
					        	
					        	   if (result.response === "AWKWARD") {
					        		   $("#userLoadingDiv").removeAttr("style");
					        		   location.href = "/login";
					    		   }  else if (result.response === "SUCCESS") {     
								         $("#userLoadingDiv").removeAttr("style");
								 	     $("#successMsgDiv").removeAttr("style");
							    		 $("#successMsgDiv").css({ display: "block" });
								         $("#successMsg").html("Well done! You successfully registered.<br> Please verify your email and click on Login to continue");
								         $("#successMsgDiv").fadeIn(500);
								 	     $('#successMsgDiv').delay(5000).fadeOut('slow');
								 	     document.getElementById("register_user_form").reset();
				                     }
					        	
					        		
					},
			});   
	           return true;
		   }else{
				return false;
			  } 
	 }
 
</script>
    
    

<script>
$(document).ready(function(){
	$(".passwordConditions input").focus(function(){
		$(".passHint").show();
	});
	$(".passwordConditions input").blur(function(){
		$(".passHint").hide();
	});	
});
</script>

<script>
function passwordValidation(idValue,fp_letter,fp_capital,fp_number,fp_special,fp_length){
	var myInput = $("#"+idValue).val();
	 
	if(myInput.match(/[a-z]/g)){
		$("#"+fp_letter).removeClass("invalid");
		$("#"+fp_letter).addClass("actTe");
	}else{
		$("#"+fp_letter).removeClass("actTe");
		$("#"+fp_letter).addClass("invalid");
	} 
	if(myInput.match(/[A-Z]/g)){
		$("#"+fp_capital).removeClass("invalid");
		$("#"+fp_capital).addClass("actTe");
	}else{
		$("#"+fp_capital).removeClass("actTe");
		$("#"+fp_capital).addClass("invalid");
	}
	if(myInput.match(/[0-9]/g)){
		$("#"+fp_number).removeClass("invalid");
		$("#"+fp_number).addClass("actTe");
	}else{
		$("#"+fp_number).removeClass("actTe");
		$("#"+fp_number).addClass("invalid");
	}
	if(myInput.match(/[$&+,:;=?@#|'<>.^*()%!-]/g)){
		$("#"+fp_special).removeClass("invalid");
		$("#"+fp_special).addClass("actTe");
	}else{
		$("#"+fp_special).removeClass("actTe");
		$("#"+fp_special).addClass("invalid");
	}
	if(myInput.length >= 8){
		$("#"+fp_length).removeClass("invalid");
		$("#"+fp_length).addClass("actTe");
	}else{
		$("#"+fp_length).removeClass("actTe");
		$("#"+fp_length).addClass("invalid");
	}
	
}


</script>

<script>
 function validateUserMobile(){
	 
	 var userMobile = $('input#mobile').val();
	 if(userMobile != null  && userMobile != '' && userMobile != 'undefined'){
		 
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("userMobile", userMobile);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/ws/validateUserMobile?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			        	if(result && result.response == "VALID_DATA"){
			        		
			        		document.getElementById('submit_button').disabled = false;
				    		$('input#mobile').removeAttr( "class record-exist record-exist-errormsg");
							$('#mobileDiv').find('p.jquery_form_error_message').remove();
							$('input#mobile').attr('class','valid form-control');
							$( "#submit_button" ).click(function() {
							});
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/login";
			        	}else{
			        		$('#mobileDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#mobile').attr('class','error form-control');
				    		$("input#mobile").after("<p class='jquery_form_error_message'>User already exists with this mobile number</p>");
							$('input#mobile').attr('record-exist','yes');
							$('input#mobile').attr('record-exist-errorMsg',' User already exists with this mobile number'); 
							document.getElementById('submit_button').disabled = true;
			        	}
	 
			        },
			    }); 
	 }
 }
 
 function validateUserEmail(){
	 
	 var userEmail = $('input#email').val();
	 if(userEmail != null  && userEmail != '' && userEmail != 'undefined'){
		 
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("userEmail", userEmail);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/ws/validateUserEmail?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			        	if(result && result.response == "VALID_DATA"){
			        		
			        		document.getElementById('submit_button').disabled = false;
				    		$('input#email').removeAttr( "class record-exist record-exist-errormsg");
							$('#emailDiv').find('p.jquery_form_error_message').remove();
							$('input#email').attr('class','valid form-control');
							$( "#submit_button" ).click(function() {
							});
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/login";
			        	}else{
			        		$('#emailDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#email').attr('class','error form-control');
				    		$("input#email").after("<p class='jquery_form_error_message'>User already exists with this email</p>");
							$('input#email').attr('record-exist','yes');
							$('input#email').attr('record-exist-errorMsg',' User already exists with this email'); 
							document.getElementById('submit_button').disabled = true;
			        	}
	 
			        },
			    }); 
	 }
 }
 
 </script>  


