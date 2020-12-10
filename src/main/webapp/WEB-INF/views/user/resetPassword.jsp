
<div class="tab-pane fade show" id="resetPassword" role="tabpanel">
	<form class="new-added-form" id="reset_password_form" name="reset_password_form" >
 	 	<div class="row" id="profilePasswordDiv">
              <div class="col-xl-4 col-lg-6 col-12 form-group">
                  <label>Current Password<span class="text-danger">&nbsp;*</span></label>
                  <input type="password" class="form-control" name="profileCurrentPassword" id="profileCurrentPassword"  field-name="Current Password"  onblur="validateCurrentPassword();" data-validation="required" >
              </div>
               <div class="col-xl-4 col-lg-6 col-12 passwordConditions form-group">
                   <label>New Password<span class="text-danger">&nbsp;*</span></label>
                   <input type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d.*)(?=.*\W.*)[a-zA-Z0-9\S]{8,}$" field-name="Password" onblur="checkPasswords();" data-validation="required validate_length length8-15" placeholder="Enter password" class="form-control" name="password" value="${password}" id="password"  onkeydown="$(this).clear();" onkeyup="passwordValidation('password','fp_letter','fp_capital','fp_number','fp_special','fp_length');">
					<i class="fa fa-eye" onclick="showPassword();" onmouseover="" style="cursor: pointer;"></i>
					<div id="fp_message" class="passHint" style="left:-260px; margin-top: -5px 0px; box-shadow: 1px 1px 1px 2px #ddd; top:-10px;">
				  			<p  id="fp_letter" class="invalid">At least one lowercase letter</p>
				  			<p  id="fp_capital" class="invalid">At least one uppercase letter</p>
				  			<p  id="fp_number" class="invalid">At least one number</p>
				  			<p  id="fp_special" class="invalid">At least one special character</p>
				  			<p  id="fp_length" class="invalid">Minimum 8 characters required</p>
						</div> 
               
               </div>
                <div class="col-xl-4 col-lg-6 col-12 form-group">
                    <label>Confirm Password<span class="text-danger">&nbsp;*</span></label>
                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"  field-name="Confirm Password" onblur="checkPasswords();" data-validation="required validate_Space" >
                    <i class="fa fa-eye" onclick="showConfirmPassword();" onmouseover="" style="cursor: pointer;"></i>
                </div>
                           
	           <div class="col-12 ui-btn-wrap">
	              <ul>
	              <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveUserPassword();"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
	              <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/profile'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
	              <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
	              </ul>
			   </div>
         </div>
 	</form>
</div>

<div class="alert icon-alart bg-light-green2" id="successPwdMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successPwdMsg" style="color: white;"></span> 
    </div>
          
    <div class="alert icon-alart bg-pink2" role="alert" id="invalidPwdMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidPwdMsg" style="color: white;"></span> 
    </div>

<script>
function checkPasswords() {   
	var pass1 = document.getElementById('password');  
    var pass2 = document.getElementById('confirmPassword');
    if(pass1.value && pass2.value){
	    if(pass1.value == pass2.value){
			    $('input#password').removeAttr( "class record-exist record-exist-errormsg");
				$('#passwordDiv').find('p.jquery_form_error_message').remove();
				$('input#password').attr('class','valid form-control');
				document.getElementById('submit_button').disabled = false;
	    }
	    else{ 
				
				$('#passwordDiv').find('p.jquery_form_error_message').remove(); 
	    		$('input#password').attr('class','error form-control');
	    		$("input#password").after("<p class='jquery_form_error_message'>Passwords Not Matched</p>");
				$('input#password').attr('record-exist','yes');
				$('input#password').attr('record-exist-errorMsg',' Passwords Not Matched.'); 
				document.getElementById('submit_button').disabled = true;
	    }
    }
}

function showPassword(){
	var passwordInput = document.getElementById('password');
	if(passwordInput.type === "password"){
		passwordInput.type = "text"
	}else{
		passwordInput.type = "password"
	}
	
	
}

function showConfirmPassword(){
	var confPasswordInput = document.getElementById('confirmPassword');
	if(confPasswordInput.type === "password"){
		confPasswordInput.type = "text"
	}else{
		confPasswordInput.type = "password"
	}
	
}

</script>


<script type="text/javascript">
	 function validateCurrentPassword() {   
		 var currentPassword = $('input#profileCurrentPassword').val();
		if(currentPassword != null  && currentPassword != '' && currentPassword != 'undefined'){
			var appUrl = '${appUrl}';
			var userUUID = '${userUUID}';
			var formData = new FormData();
			formData.append("userUUID", userUUID);
			formData.append("currentPassword", currentPassword);
		     $.ajax({
		    	 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
			    	 url: appUrl+"/validateCurrentPassword?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			         	 if(result == 'valid'){
				    		$("#save_button").removeAttr("disabled");
				    		$('input#profileCurrentPassword').removeAttr( "class record-exist record-exist-errormsg");
							$('#profilePasswordDiv').find('p.jquery_form_error_message').remove();
							$('input#profileCurrentPassword').attr('class','valid form-control');
							$( "#save_button" ).removeAttr("style");
							$( "#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
							$( "#save_button" ).click(function() {
							});
							
				    	}
				    	else if(result == 'invalid'){
			    		$("#save_button").attr("disabled", "disabled");
				    		$('#profilePasswordDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#profileCurrentPassword').attr('class','error form-control');
				    		$("input#profileCurrentPassword").after("<p class='jquery_form_error_message'>Wrong Password Entered</p>");
							$('input#profileCurrentPassword').attr('record-exist','yes');
							$('input#profileCurrentPassword').attr('record-exist-errorMsg',' Wrong Password Entered'); 
							$("#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
				    	} 
			        },
			    }); 
				
		}
		
	 }
 </script>
 
 <script>
       function saveUserPassword(){
    	   if ($('#reset_password_form').validate(false, validationSettings)){
    		   $("#profileDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
               var appUrl = "${appUrl}";
               var password = $('input#password').val(); 
               var userUUID = '${userUUID}';
   				var formData = new FormData();
   				formData.append("userUUID", userUUID);
   				formData.append("password", password);
               $.ajax({
            	 type : "POST",
  				 data: formData,
  	    	     processData: false,
  	    	     contentType:false,
    	        	    	 url: appUrl+"/resetPassword?${_csrf.parameterName}=${_csrf.token}", 
    				        success : function(result) {
    				        	
    				        	 if (result && result.response === "INVALID_DATA") {
    				        		 $("#profileDetailsLoadingDiv").removeAttr("style");
    				    		     $("#invalidPwdMsgDiv").removeAttr("style");
    					    		 $("#invalidPwdMsgDiv").css({ display: "block" });
    						         $("#invalidPwdMsg").html("Attention! You had missed some data.");
    						         $("#invalidPwdMsgDiv").fadeIn(500);
    						 	     $('#invalidPwdMsgDiv').delay(5000).fadeOut('slow'); 
    				    		   }
    				        	else if (result.response === "AWKWARD") {
    				        		   $("#profileDetailsLoadingDiv").removeAttr("style");
    				    		        location.href = "/errorPage";
    				    		   }  else if (result.response === "SUCCESS") {     
    				    			   $("#profileDetailsLoadingDiv").removeAttr("style");
    							         $("#successPwdMsgDiv").removeAttr("style");
    						    		 $("#successPwdMsgDiv").css({ display: "block" });
    							         $("#successPwdMsg").html("Well done! You successfully updated your password.");
    							         $("#succesPwdsMsgDiv").fadeIn(200);
    							 	     $('#successPwdMsgDiv').delay(2000).fadeOut('slow');
    							 	    document.getElementById("reset_password_form").reset();
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
function getUserPasswordInfo(){
	 
	 $(".passwordConditions input").focus(function(){
			$(".passHint").show();
		});
	 $(".passwordConditions input").blur(function(){
			$(".passHint").hide();
		});	
}
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

