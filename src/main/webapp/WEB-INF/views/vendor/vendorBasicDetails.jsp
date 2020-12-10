
<div class="tab-pane fade show active" id="basic" role="tabpanel">
<div id="vendorBasicDetailsLoaderDiv"></div>
<form class="new-added-form" id="add_vendor_details_form" name="add_vendor_details_form" >
 	 	<div class="row">	
 	 		
 	 		<div class="col-xl-3 col-lg-6 col-12 form-group">
                 <label>Vendor Name<span class="text-danger">&nbsp;*</span></label>
                 <input type="text" class="form-control"  name="vendorName" id="vendorName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-250"  field-name="Vendor Name">
             </div>
             
             <div class="col-xl-3 col-lg-6 col-12 form-group" id="vendorCodeDiv">
                 <label>Vendor Code<span class="text-danger">&nbsp;*</span></label>
                 <input type="text" class="form-control"  name="vendorCode" id="vendorCode" onblur="validateVendorCode();" data-validation="required validate_Space validate_AlphaNumber validate_length length1-20"  field-name="Vendor Code">
             </div>
             
              <div class="col-xl-3 col-lg-6 col-12 form-group" id="vendorMobileDiv">
                     <label>Vendor Mobile<span class="text-danger">&nbsp;*</span></label>
                     <input type="text" class="form-control"  name="vendorMobile" id="vendorMobile" onblur="validateVendorMobile();" data-validation="required validate_Space validate_int fixed_length length10"  field-name="Vendor Mobile">
              </div>
                          
               <div class="col-xl-3 col-lg-6 col-12 form-group" id="vendorEmailDiv">
                   <label>Vendor Email<span class="text-danger">&nbsp;*</span></label>
                   <input type="text" class="form-control"  name="vendorEmail" id="vendorEmail" onblur="validateVendorEmail();" data-validation="required validate_Space validate_email validate_length length1-100"  field-name="Vendor Email">
               </div>
                          
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Vendor Capacity<span class="text-danger">&nbsp;*</span></label>
                    <input type="text" class="form-control"  name="vendorCapacity" id="vendorCapacity"  data-validation="required validate_Space validate_int validate_length length1-5"  field-name="Vendor Capacity">
                </div>
                          
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Established Year<span class="text-danger">&nbsp;*</span></label>
                    <input type="text" class="form-control"  name="establishedYear" id="establishedYear"  data-validation="required validate_Space validate_int fixed_length length4"  field-name="Established Year">
                </div>
               
                 <div class="col-xl-3 col-lg-6 col-12 form-group">
                   <label>Cost For Two People<span class="text-danger">&nbsp;*</span></label>
                   <input type="text" class="form-control"  name="costForTwoPeople" id="costForTwoPeople" data-validation="required validate_Space validate_int validate_length length1-5"  field-name="Cost For Two People">
                 </div>
                 
                 <div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>Currency<span class="text-danger">&nbsp;*</span></label>
                                    <select class="select2" name="currency" id="currency" data-validation="required" field-name="Currency">
                                        <option value="">Please Select</option>
                                        <option value="USD">US Dollar</option>
                                        <option value="EUR">Euro</option>
                                        <option value="CRC">Costa Rican Colon</option>
                                        <option value="GBP">British Pound Sterling</option>
                                        <option value="ILS">Israeli New Sheqel</option>
                                        <option value="INR">Indian Rupee</option>
                                        <option value="JPY">Japanese Yen</option>
                                        <option value="KRW">South Korean Won</option>
                                        <option value="NGN">Nigerian Naira</option>
                                        <option value="PHP">Philippine Peso</option>
                                        <option value="PLN">Polish Zloty</option>
                                        <option value="PYG">Paraguayan Guarani</option>
                                        <option value="THB">Thai Baht</option>
                                        <option value="UAH">Ukrainian Hryvnia</option>
                                        <option value="VND">Vietnamese Dong</option>
                                        
                                    </select>
              </div>
                          
                  <div class="col-xl-4 col-lg-6 col-12 form-group" id="uploadProfile">
                      <label>Upload Photo</label>
                      <input type="file" class="form-control-file" id="uploadImageBtn" field-name="Upload Image" data-validation-optional="true" data-validation="validateImg">
                      <input type="text" class="form-control" readonly id="uploadImageFile" style="display: none;" name="docFile" >
                  </div>
                  
                  <div class="col-xl-4 col-lg-6 col-12 form-group" id="savedProfile">
                      <label>Uploaded Photo</label>
                      <div class="summaryProfilePic-image" id="uploadImageBtn">
							<div id="savedProfileImage"></div>			
					  </div>
					<div class="clearUpload clearUpload-profile-position" title="Delete Uploaded Photo" onclick="deleteProfileImage();"></div>
                  </div>
                                                  
         	 	  <div class="col-lg-4 col-12 form-group">
                         <label>Vendor Description<span class="text-danger">&nbsp;*</span></label>
                         <textarea class="textarea form-control" name="vendorDescription" id="vendorDescription" cols="10" rows="5" 
                         data-validation="required validate_space" field-name="Vendor Description" ></textarea>
                  </div>
                          
                  <div class="col-lg-4 col-12 form-group">
                         <label>Best Selling Items<span class="text-danger">&nbsp;*</span></label>
                         <textarea class="textarea form-control" name="bestSellingItems" id="bestSellingItems" cols="10" rows="5" 
                         data-validation="required validate_space" field-name="Best Selling Items" ></textarea>
                  </div>
                  
				   <div class="col-12 ui-btn-wrap">
				               <ul>
				               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendorDetails()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
				               </ul>
				   </div>
             
             
       </div>
</form>
</div>
	<div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successMsg" style="color: white;"></span> 
    </div>
          
    <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidMsg" style="color: white;"></span> 
    </div>
    
    
    
    <script>
 function validateVendorMobile(){
	 
	 var vendorMobile = $('input#vendorMobile').val();
	 if(vendorMobile != null  && vendorMobile != '' && vendorMobile != 'undefined'){
		 
		 var vendorUUID = '${vendorUUID}';
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("vendorMobile", vendorMobile);
			formData.append("vendorUUID", vendorUUID);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/validateVendorMobile?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			        	if(result && result.response == "VALID_DATA"){
			        		
			        		$("#save_button").removeAttr("disabled");
				    		$('input#vendorMobile').removeAttr( "class record-exist record-exist-errormsg");
							$('#vendorMobileDiv').find('p.jquery_form_error_message').remove();
							$('input#vendorMobile').attr('class','valid form-control');
							$( "#save_button" ).removeAttr("style");
							$( "#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
							$( "#save_button" ).click(function() {
							});
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/errorPage";
			        	}else{
			        		$('#vendorMobileDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#vendorMobile').attr('class','error form-control');
				    		$("input#vendorMobile").after("<p class='jquery_form_error_message'>Vendor already exists with this mobile number</p>");
							$('input#vendorMobile').attr('record-exist','yes');
							$('input#vendorMobile').attr('record-exist-errorMsg',' Vendor already exists with this mobile number'); 
							$("#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
			        	}
	 
			        },
			    }); 
	 }
 }
 </script> 
 
 <script>
 function validateVendorEmail(){
	 
	 var vendorEmail = $('input#vendorEmail').val();
	 if(vendorEmail != null  && vendorEmail != '' && vendorEmail != 'undefined'){
		 
		 var vendorUUID = '${vendorUUID}';
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("vendorEmail", vendorEmail);
			formData.append("vendorUUID", vendorUUID);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/validateVendorEmail?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			        	if(result && result.response == "VALID_DATA"){
			        		
			        		$("#save_button").removeAttr("disabled");
				    		$('input#vendorEmail').removeAttr( "class record-exist record-exist-errormsg");
							$('#vendorEmailDiv').find('p.jquery_form_error_message').remove();
							$('input#vendorEmail').attr('class','valid form-control');
							$( "#save_button" ).removeAttr("style");
							$( "#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
							$( "#save_button" ).click(function() {
							});
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/errorPage";
			        	}else{
			        		$('#vendorEmailDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#vendorEmail').attr('class','error form-control');
				    		$("input#vendorEmail").after("<p class='jquery_form_error_message'>Vendor already exists with this email</p>");
							$('input#vendorEmail').attr('record-exist','yes');
							$('input#vendorEmail').attr('record-exist-errorMsg',' Vendor already exists with this email'); 
							$("#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
			        	}
	 
			        },
			    }); 
	 }
 }
 </script>  



<script>
 function validateVendorCode(){
	 
	 var vendorCode = $('input#vendorCode').val();
	 if(vendorCode != null  && vendorCode != '' && vendorCode != 'undefined'){
		 
		 var vendorUUID = '${vendorUUID}';
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("vendorCode", vendorCode);
			formData.append("vendorUUID", vendorUUID);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/validateVendorCode?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			        	if(result && result.response == "VALID_DATA"){
			        		
			        		$("#save_button").removeAttr("disabled");
				    		$('input#vendorCode').removeAttr( "class record-exist record-exist-errormsg");
							$('#vendorCodeDiv').find('p.jquery_form_error_message').remove();
							$('input#vendorCode').attr('class','valid form-control');
							$( "#save_button" ).removeAttr("style");
							$( "#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
							$( "#save_button" ).click(function() {
							});
							
			        	}else if (result && result.response === "AWKWARD") {
			        		location.href = "/errorPage";
			        	}else{
			        		$('#vendorCodeDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#vendorCode').attr('class','error form-control');
				    		$("input#vendorCode").after("<p class='jquery_form_error_message'>Vendor already exists with this code</p>");
							$('input#vendorCode').attr('record-exist','yes');
							$('input#vendorCode').attr('record-exist-errorMsg',' Vendor already exists with this code'); 
							$("#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
			        	}
	 
			        },
			    }); 
	 }
 }
 </script>  

<script>
function loadVendorBasicDetails(){
	var vendorUUID = '${vendorUUID}';
	 var appUrl ='${appUrl}';
	   $("#vendorBasicDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getVendorDetails?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
			     success :function(response) {
			    	    
			    	$("#vendorBasicDetailsLoaderDiv").removeAttr("style");
			    	 
			    	 $("#vendorName").val(response.object.vendorName);
			    	 $("#vendorCode").val(response.object.vendorCode);
			    	 $("#vendorEmail").val(response.object.vendorEmail);
			    	 $("#vendorMobile").val(response.object.vendorMobile);
			    	 $("#establishedYear").val(response.object.establishedYear);
			    	 $("#vendorCapacity").val(response.object.vendorCapacity);
			    	 $("#costForTwoPeople").val(response.object.costForTwoPeople);
			    	 $("select[name=currency]").val(response.object.currency); 
			    	 var currencyType = getCurrency(response.object.currency);
			    	 document.getElementById('select2-currency-container').innerHTML = currencyType;
			    	 
			    	 $("textarea[name=vendorDescription]").val(response.object.vendorDescription);
			    	 $("textarea[name=bestSellingItems]").val(response.object.bestSellingItems);
			    	 if(response.object.vendorProfileImg != ''){
			    		 document.getElementById("uploadProfile").style.display="none";
			    		 document.getElementById("savedProfile").style.display="block";
			    		 $("#savedProfileImage").html('<img src="'+response.object.vendorProfileImg+'" data-id= "vendorProfileImage" >');
			    	 }else{
			    		 document.getElementById("savedProfile").style.display="none";
			    		 document.getElementById("uploadProfile").style.display="block";
			    	 }
			    	 
					},
		});
}

function deleteProfileImage(){
	document.getElementById("uploadProfile").style.display="block";
	document.getElementById("savedProfile").style.display="none";
}



function getCurrency(currency){
	var currencyType;
	
	switch (currency) {
	  case "USD":
		currencyType = "US Dollar";
	    break;
	  case "EUR":
		  currencyType = "Euro";
	    break;
	  case "CRC":
		  currencyType = "Costa Rican Colon";
	    break;
	  case "GBP":
		  currencyType = "British Pound Sterling";
	    break;
	  case "ILS":
		  currencyType = "Israeli New Sheqel";
	    break;
	  case "INR":
		  currencyType = "Indian Rupee";
	    break;
	  case  "JPY":
		  currencyType = "Japanese Yen";
	    break;
	  case  "KRW":
		  currencyType = "South Korean Won";
		break;
	  case  "NGN":
		  currencyType = "Nigerian Naira";
		break;
	  case  "PHP":
		  currencyType = "Philippine Peso";
	    break;
	  case  "PLN":
		  currencyType = "Polish Zloty";
	    break;
	  case  "PYG":
		  currencyType = "Paraguayan Guarani";
	    break;
	  case  "THB":
		  currencyType = "Thai Baht";
	    break;
	  case  "UAH":
		  currencyType = "Ukrainian Hryvnia";
	    break;
	  case  "VND":
		  currencyType = "Vietnamese Dong";
	    break;
	}
	
	return currencyType
}

</script>

<style>
.clearUpload.clearUpload-profile-position {
top: -90px !important;
right: -160px !important;
}
</style>


<script>
 function saveVendorDetails(){
	   if ($('#add_vendor_details_form').validate(false, validationSettings)){
		   $("#vendorBasicDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID = "${vendorUUID}";
           var formData = getWayupayFormData("add_vendor_details_form");
			formData.append("vendorUUID", vendorUUID);
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorBasicDetails?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorBasicDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorBasicDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully updated vendor details.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	    loadVendorBasicDetails();
			                   }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }

</script>


 
