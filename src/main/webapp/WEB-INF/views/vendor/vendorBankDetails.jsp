
 <div class="tab-pane fade" id="bank" role="tabpanel">
 <div id="vendorBankDetailsLoaderDiv"></div>
		<form class="new-added-form" id="add_vendor_bank_account_form" name="add_vendor_bank_account_form" >
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
		                       
		                     <div class="col-12 ui-btn-wrap">
				               <ul>
				               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendorBankDetails()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
				             </ul>
				   			</div>
		                                
		          </div>
		</form>
</div>

<div class="alert icon-alart bg-light-green2" id="successBankMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successBankMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidBankMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidBankMsg" style="color: white;"></span> 
</div>



<script>
function loadVendorBankDetails(){
	var vendorUUID = '${vendorUUID}';
	 var appUrl ='${appUrl}';
	   $("#vendorBankDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getVendorBankAccountDetails?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
			     success :function(response) {
			    	    
			    	 $("#vendorBankDetailsLoaderDiv").removeAttr("style");
			    	 
			    	 $("#beneficiaryName").val(response.object.beneficiaryName);
			    	 $("#bankName").val(response.object.bankName);
			    	 $("#bankBranch").val(response.object.bankBranch);
			    	 $("#accountNumber").val(response.object.accountNumber);
			    	 $("#bankCode").val(response.object.bankCode);
			    	 $("select[name=accountType]").val(response.object.accountType); 
			    	 document.getElementById('select2-accountType-container').innerHTML = response.object.accountType;
					},
		});
}

</script>

<script>
 function saveVendorBankDetails(){
	   if ($('#add_vendor_bank_account_form').validate(false, validationSettings)){
		   $("#vendorBankDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID = "${vendorUUID}";
           var formData = getWayupayFormData("add_vendor_bank_account_form");
			formData.append("vendorUUID", vendorUUID);
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorBankDetails?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorBankDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidBankMsgDiv").removeAttr("style");
					    		 $("#invalidBankMsgDiv").css({ display: "block" });
						         $("#invalidBankMsg").html("Attention! You had missed some data.");
						         $("#invalidBankMsgDiv").fadeIn(500);
						 	     $('#invalidBankMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorBankDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successBankMsgDiv").removeAttr("style");
						    		 $("#successBankMsgDiv").css({ display: "block" });
							         $("#successBankMsg").html("Well done! You successfully updated vendor bank details.");
							         $("#successBankMsgDiv").fadeIn(200);
							 	     $('#successBankMsgDiv').delay(2000).fadeOut('slow');
							 	     loadVendorBankDetails();
			                   }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }

</script>