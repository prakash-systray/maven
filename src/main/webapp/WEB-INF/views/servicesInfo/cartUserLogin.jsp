<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
 <div class="ui-modal-box">
 	 <div class="modal-box">
 	 
 	 <div class="modal sign-up-modal fade" id="sign-up" tabindex="-1" role="dialog"
                                    aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                        <div id="userLoginLoading"></div>
                                        
                                            <div class="modal-body">
                                                <div class="close-btn">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="item-logo">
                                                    <img src="/resources/img/logo.png" alt="logo" style="width: 250px; height: auto">
                                                </div>
		                                        <div class="alert icon-alart bg-pink2" role="alert" id="invalidloginMsgDiv" style="display: none;">
					                                   <i class="fa fa-times bg-pink3"></i>
					                                   <span id="invalidloginMsg" style="color: white;"></span> 
		            							</div>
                                               <form class="new-added-form" id="login_user_form" name="login_user_form" >
                                                    <div class="row gutters-15">
                                                        <div class="form-group col-12">
                                                            <input type="text" placeholder="User Name" name="userName" 
                                                                class="form-control" data-validation="required" field-name="User Name">
                                                        </div>
                                                        <div class="form-group col-12">
                                                            <input type="password" placeholder="Password"
                                                                class="form-control" name="password" data-validation="required" field-name="Password">
                                                        </div>
                                                        <div class="form-group col-12">
                                                            <button type="button" class="login-btn" onclick="cartUserLogin();">Login</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
 	 </div>
 </div>
 
 
 <style>
 .sign-up-modal .modal-content{
 width: 80% !important;
 }
 </style>
 
 
 <script>
 function cartUserLogin(){
	 
	 $("#userLoginLoading").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
     var appUrl = "${appUrl}";
     var formData = getWayupayFormData("login_user_form");
     
	 $.ajax({
		 type : "POST",
		 data: formData,
	     processData: false,
	     contentType:false,
    	    	 url: appUrl+"/ws/loginCartUser?${_csrf.parameterName}=${_csrf.token}", 
		        success : function(result) {
		        	
		        	 if (result && result.response === "INVALID_CREDENTIALS") {
		        		 $("#userLoginLoading").removeAttr("style");
		        		 $("#invalidloginMsgDiv").removeAttr("style");
			    		 $("#invalidloginMsgDiv").css({ display: "block" });
				         $("#invalidloginMsg").html("Invalid Credentials");
				         $("#invalidloginMsgDiv").fadeIn(500);
				 	     $('#invalidloginMsgDiv').delay(5000).fadeOut('slow');
		    		   }
		        	else if (result.response === "AWKWARD") {
		        		   $("#userLoginLoading").removeAttr("style");
		    		        location.href = "/errorPage";
		    		   }else if (result.response === "SUCCESS") {     
					       
		    			   $("#userLoginLoading").removeAttr("style");
		    			   addLoginUserCart(result.object.userId);
		    			   
	                   }
		        	
		        		
		},
});   
 }
 $('body').on('blur', '#login_user_form', function() {
		$("#login_user_form").showHelpOnFocus();
		$("#login_user_form").validateOnBlur(false, validationSettings);  
});
 </script>
 
 
 <script>
 function addLoginUserCart(userId){
	 
	 $("#serviceDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
     var appUrl = "${appUrl}";
     var formData = new FormData();
		   formData.append("serviceId", cartMap.get("serviceId"));
		   formData.append("vendorId", cartMap.get("vendorId"));
		   formData.append("serviceOrderDate", cartMap.get("serviceOrderDate"));
		   formData.append("timeslot", cartMap.get("timeslot"));
		   formData.append("quantity", cartMap.get("quantity"));
		   formData.append("orderAmount", cartMap.get("orderAmount"));
		   formData.append("totalAmount", cartMap.get("totalAmount"));
		   formData.append("currency", cartMap.get("currency"));
		   formData.append("packageMenuItems", cartMap.get("packageMenuItems"));
		   formData.append("userId", userId);
		   
		   
      $.ajax({
			 type : "POST",
			 data: formData,
  	     processData: false,
  	     contentType:false,
      	    	 url: appUrl+"/ws/saveToHomeLoginCart?${_csrf.parameterName}=${_csrf.token}", 
			        success : function(result) {
			        	
			        	 if (result && result.response === "CART_SERVICE_EXISTS") {
			        		 $("#serviceDetailsLoaderDiv").removeAttr("style");
			    		     $("#invalidMsgDiv").removeAttr("style");
				    		 $("#invalidMsgDiv").css({ display: "block" });
					         $("#invalidMsg").html("Attention! You had already added to cart. Please login and place order");
					         $("#invalidMsgDiv").fadeIn(500);
					 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
			    		   }else if (result && result.response === "CLEAR_EXISTING_CART") {
			        		 $("#serviceDetailsLoaderDiv").removeAttr("style");
			    		     $("#invalidMsgDiv").removeAttr("style");
				    		 $("#invalidMsgDiv").css({ display: "block" });
					         $("#invalidMsg").html("Attention! Please login and clear exising cart to continue");
					         $("#invalidMsgDiv").fadeIn(500);
					 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
			    		   }else if (result.response === "AWKWARD") {
			        		   $("#serviceDetailsLoaderDiv").removeAttr("style");
			    		        location.href = "/errorPage";
			    		   }else if (result.response === "SUCCESS") {     
			    			     $("#serviceDetailsLoaderDiv").removeAttr("style");
						         $("#successMsgDiv").removeAttr("style");
					    		 $("#successMsgDiv").css({ display: "block" });
						         $("#successMsg").html("Well done! You successfully added to cart. Please login and place order");
						         $("#successMsgDiv").fadeIn(200);
						 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
						 	     cartMap = '';
						 	     document.getElementById("login_user_form").reset();
						 	     getCategoryServices(cartMap.get("categoryUUID"));
		                    }
			        	
			        		
			},
	});
 }
 </script>
 
 
 <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 