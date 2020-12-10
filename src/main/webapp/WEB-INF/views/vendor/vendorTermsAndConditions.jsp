<div class="tab-pane fade" id="termsandcondidtions" role="tabpanel">
<div id="vendorTermsDetailsLoaderDiv"></div>
		<form class="new-added-form" id="add_vendor_terms_form" name="add_vendor_terms_form" >
	 	 	<div class="row">
		 	 		<div class="col-lg-10 col-12 form-group">
	                    <label>Terms & Conditions<span class="text-danger">&nbsp;*</span></label>
	                    <textarea class="textarea form-control" name="termsAndConditions" id="termsAndConditions" cols="10" rows="10" 
	                    data-validation="required validate_space" field-name="Terms & Conditions" ></textarea>
	                </div>
	                
	           <div class="col-12 ui-btn-wrap">
		             <ul>
		               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendorTermsAndConditions()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
		               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
		               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
		             </ul>
			   </div>
	 	 	</div>
		</form>
</div>


<div class="alert icon-alart bg-light-green2" id="successTermsMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successTermsMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidTermsMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidTermsMsg" style="color: white;"></span> 
</div>



<script>
function loadTermsAndConditions(){
	var vendorUUID = '${vendorUUID}';
	 var appUrl ='${appUrl}';
	   $("#vendorTermsDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getVendorTermsAndConditions?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
			     success :function(response) {
			    	    
			    	 $("textarea[name=termsAndConditions]").val(response.object.termsAndConditions);
			    	 $("#vendorTermsDetailsLoaderDiv").removeAttr("style");
			    	 
					},
		});
}
</script>

<script>
function saveVendorTermsAndConditions(){
	
	   if ($('#add_vendor_terms_form').validate(false, validationSettings)){
		   $("#vendorTermsDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID = "${vendorUUID}";
           var formData = getWayupayFormData("add_vendor_terms_form");
			formData.append("vendorUUID", vendorUUID);
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorTermsAndConditions?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorTermsDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidTermsMsgDiv").removeAttr("style");
					    		 $("#invalidTermsMsgDiv").css({ display: "block" });
						         $("#invalidTermsMsg").html("Attention! You had missed some data.");
						         $("#invalidTermsMsgDiv").fadeIn(500);
						 	     $('#invalidTermsMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorAddressDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successTermsMsgDiv").removeAttr("style");
						    		 $("#successTermsMsgDiv").css({ display: "block" });
							         $("#successTermsMsg").html("Well done! You successfully saved vendor terms & conditions.");
							         $("#successTermsMsgDiv").fadeIn(200);
							 	     $('#successTermsMsgDiv').delay(2000).fadeOut('slow');
							 	     loadTermsAndConditions();
			                   }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
	
}
</script>
		 	 	