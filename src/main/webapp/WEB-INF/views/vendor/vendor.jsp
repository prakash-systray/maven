<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="preloader"></div>

<div id="wrapper" class="wrapper bg-ash">
  <jsp:include page="../wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
           <jsp:include page="../wayupartyMasterSideNav.jsp" />
	          <div class="dashboard-content-one">
	            <div class="breadcrumbs-area">
	                    <h3>Add Vendor</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>Add Vendor</li>
	                    </ul>
	             </div>
	             
	        <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                   <i class="far fa-hand-point-right bg-light-green3"></i>
                                  <span id="successMsg" style="color: white;"></span> 
            </div>
            
            <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
                                   <i class="fas fa-times bg-pink3"></i>
                                  <span id="invalidMsg" style="color: white;"></span> 
            </div>
            
            
            <div class="card height-auto">
             <div id="vendorDetailsLoadingDiv"></div>
                   <div class="card-body">
                        <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Vendor Details</h3>
                            </div>
                    	</div>
                    	
                    	 <form class="new-added-form" id="add_vendor_form" name="add_vendor_form" >
                    	 	<div class="row">	
                    	 		<div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Vendor Name<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="vendorName" id="vendorName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-250"  field-name="Vendor Name">
                                </div>
                                
					             <div class="col-xl-4 col-lg-6 col-12 form-group" id="vendorCodeDiv">
					                 <label>Vendor Code<span class="text-danger">&nbsp;*</span></label>
					                 <input type="text" class="form-control"  name="vendorCode" id="vendorCode" onblur="validateVendorCode();" data-validation="required validate_Space validate_AlphaNumber validate_length length1-20"  field-name="Vendor Code">
					             </div>
             
                                <div class="col-xl-4 col-lg-6 col-12 form-group" id="vendorMobileDiv">
                                    <label>Vendor Mobile<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="vendorMobile" id="vendorMobile" onblur="validateVendorMobile();" data-validation="required validate_Space validate_int fixed_length length10"  field-name="Vendor Mobile">
                                </div>
                                
                                 <div class="col-xl-4 col-lg-6 col-12 form-group" id="vendorEmailDiv">
                                    <label>Vendor Email<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="vendorEmail" id="vendorEmail" onblur="validateVendorEmail();" data-validation="required validate_Space validate_email validate_length length1-100"  field-name="Vendor Email">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Vendor Administrator Name<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="vendorAdministratorName" id="vendorAdministratorName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-100"  field-name="Vendor Administrator Name">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Vendor Capacity<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="vendorCapacity" id="vendorCapacity"  data-validation="required validate_Space validate_int validate_length length1-5"  field-name="Vendor Capacity">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Established Year<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="establishedYear" id="establishedYear"  data-validation="required validate_Space validate_int fixed_length length4"  field-name="Established Year">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Cost For Two People<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="costForTwoPeople" id="costForTwoPeople" data-validation="required validate_Space validate_int validate_length length1-5"  field-name="Cost For Two People">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
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
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Upload Photo</label>
                                    <input type="file" class="form-control-file" id="uploadImageBtn" field-name="Upload Image" data-validation-optional="true" data-validation="validateImg">
                                    <input type="text" class="form-control" readonly
										id="uploadImageFile" style="display: none;" name="docFile" >
                                 </div>
                                
                    	 </div>
                    	 
                    	 <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Bank Details</h3>
                            </div>
                    	</div>
                    	
                    	<div class="row">	
                    	 		<div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Beneficiary Name<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="beneficiaryName" id="beneficiaryName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-100"  field-name="Beneficiary Name">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Bank Name<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="bankName" id="bankName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-500"  field-name="Bank Name">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Bank Branch<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="bankBranch" id="bankBranch" data-validation="required validate_Space validate_AlphaNumber validate_length length1-500"  field-name="Bank Branch">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Account Number<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="accountNumber" id="accountNumber" data-validation="required validate_Space validate_int validate_length length1-50"  field-name="Account Number">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Branch Code<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="bankCode" id="bankCode" data-validation="required validate_Space validate_AlphaNumber validate_length length1-50"  field-name="Bank Code">
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Account Type<span class="text-danger">&nbsp;*</span></label>
                                    <select class="select2" name="accountType" id="accountType" data-validation="required" field-name="Account Type">
                                        <option value="">Please Select</option>
                                        <option value="Savings Account">Savings Account</option>
                                        <option value="Current Account">Current Account</option>
                                    </select>
                                </div>
                                
                        </div><br/>        
                    	 
                    	 
                    	 <div class="row">	
						   <div class="col-12 ui-btn-wrap">
						               <ul>
						               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendor()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
						               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendor'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
						               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
						               </ul>
						   </div>
                           
                           </div>
                           
                    	 </form>
                    	
                  </div>
            </div>
            </div>
        </div>
        <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script>
   
   document.getElementById("uploadImageBtn").onchange = function () {
       $('#uploadImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
   };
   $(function () {
       $("#uploadImageBtn").change(function () {
           fileUpload("uploadImageBtn", "vendor_profile_images", "add_vendor_form",0,0);
           $(".clearUpload").show();
       });
   });
 </script>
   
 <script>
 function validateVendorMobile(){
	 
	 var vendorMobile = $('input#vendorMobile').val();
	 if(vendorMobile != null  && vendorMobile != '' && vendorMobile != 'undefined'){
		 
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("vendorMobile", vendorMobile);
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
		 
		 var appUrl ='${appUrl}';
			var formData = new FormData();
			formData.append("vendorEmail", vendorEmail);
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
 function saveVendor(){
	   if ($('#add_vendor_form').validate(false, validationSettings)){
		   $("#vendorDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var formData = getWayupayFormData("add_vendor_form");
	        		
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendor?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorDetailsLoadingDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorDetailsLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully added new vendor.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     location.replace(appUrl+"/dashboard");
			                       }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_vendor_form', function() {
		$("#add_vendor_form").showHelpOnFocus();
		$("#add_vendor_form").validateOnBlur(false, validationSettings);  
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