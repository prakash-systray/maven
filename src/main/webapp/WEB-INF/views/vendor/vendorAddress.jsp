

<div class="tab-pane fade" id="address" role="tabpanel">
<div id="vendorAddressDetailsLoaderDiv"></div>
		<form class="new-added-form" id="add_vendor_address_form" name="add_vendor_address_form" >
		 	 	<div class="row">	
         	 		<div class="col-xl-6 col-lg-6 col-12 form-group">
                         <label>Location<span class="text-danger">&nbsp;*</span></label>
                         <input type="text" class="form-control"  name="vendorLocation" id="vendorLocation"  data-validation="required validate_space"  field-name="Location">
                         <span class="fas fa-undo btn btn-primary refresh-icon" title="Refresh"  onclick="clearVendorLocation();"></span>
                    </div>
              </div><br/>
              
               <div class="row"> 
               
               		<div class="col-xl-3 col-lg-6 col-12 form-group">
                         <label>Country<span class="text-danger">&nbsp;*</span></label>
                         <input type="text" class="form-control"  name="country" id="country"  data-validation="required validate_Space validate_AlphaNumber validate_length length1-100"  field-name="Country">
                    </div> 
                       
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                         <label>State<span class="text-danger">&nbsp;*</span></label>
                         <input type="text" class="form-control"  name="state" id="state"  data-validation="required validate_Space validate_AlphaNumber validate_length length1-100"  field-name="State">
                    </div>
                    
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                         <label>City<span class="text-danger">&nbsp;*</span></label>
                         <input type="text" class="form-control"  name="city" id="city"  data-validation="required validate_Space validate_AlphaNumber validate_length length1-100"  field-name="City">
                    </div>
                    
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                         <label>Pincode<span class="text-danger">&nbsp;*</span></label>
                         <input type="text" class="form-control"  name="pincode" id="pincode"  data-validation="required validate_Space validate_length length1-20"  field-name="Pin Code">
                    </div>
                    
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                         <label>Latitude<span class="text-danger">&nbsp;*</span></label>
                         <input type="text" class="form-control"  name="latitude" id="latitude"  data-validation="required validate_Space validate_latitude"  field-name="Latitude">
                    </div>
                    
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                         <label>Longitude<span class="text-danger">&nbsp;*</span></label>
                         <input type="text" class="form-control"  name="longitude" id="longitude"  data-validation="required validate_Space validate_longitude"  field-name="Longitude">
                    </div>
                    
                     <div class="col-xl-3 col-lg-6 col-12 form-group">
                         <label>Phone Number<span class="text-danger">&nbsp;*</span></label>
                         <input type="text" class="form-control"  name="phoneNumber" id="phoneNumber" onblur="validatePhoneNumber();" data-validation="required validate_Space validate_int fixed_length length10"  field-name="Vendor Mobile">
                    </div>
                    
	                <div class="col-lg-3 col-12 form-group">
	                    <label>Vendor Address<span class="text-danger">&nbsp;*</span></label>
	                    <textarea class="textarea form-control" name="vendorAddress" id="vendorAddress" cols="10" rows="3" 
	                    data-validation="required validate_space" field-name="Vendor Address" ></textarea>
	                </div>
	                
	                <div class="col-12 ui-btn-wrap">
				               <ul>
				               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendorAddressDetails()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
				             </ul>
				   	</div>
		                       
                </div>
                
       </form>
</div>

<div class="alert icon-alart bg-light-green2" id="successAddressMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successAddressMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidAddressMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidAddressMsg" style="color: white;"></span> 
</div>


<script>
 function saveVendorAddressDetails(){
	   if ($('#add_vendor_address_form').validate(false, validationSettings)){
		   $("#vendorAddressDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID = "${vendorUUID}";
           var formData = getWayupayFormData("add_vendor_address_form");
			formData.append("vendorUUID", vendorUUID);
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorAddressDetails?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorAddressDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidAddressMsgDiv").removeAttr("style");
					    		 $("#invalidAddressMsgDiv").css({ display: "block" });
						         $("#invalidAddressMsg").html("Attention! You had missed some data.");
						         $("#invalidAddressMsgDiv").fadeIn(500);
						 	     $('#invalidAddressMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorAddressDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successAddressMsgDiv").removeAttr("style");
						    		 $("#successAddressMsgDiv").css({ display: "block" });
							         $("#successAddressMsg").html("Well done! You successfully updated vendor details.");
							         $("#successAddressMsgDiv").fadeIn(200);
							 	     $('#successAddressMsgDiv').delay(2000).fadeOut('slow');
							 	     loadVendorAddressDetails();
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
function loadVendorAddressDetails(){
	googleApiLocation('vendorLocation','country');
	
	
	var vendorUUID = '${vendorUUID}';
	 var appUrl ='${appUrl}';
	   $("#vendorAddressDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getVendorDetails?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
			     success :function(response) {
			    	    
			    	$("#vendorAddressDetailsLoaderDiv").removeAttr("style");
			    	 
			    	 $("#vendorLocation").val(response.object.location);
			    	 $("#country").val(response.object.country);
			    	 $("#state").val(response.object.state);
			    	 $("#city").val(response.object.city);
			    	 $("#pincode").val(response.object.pincode);
			    	 $("#longitude").val(response.object.longitude);
			    	 $("#latitude").val(response.object.latitude);
			    	 $("#phoneNumber").val(response.object.phoneNumber);
			    	 $("textarea[name=vendorAddress]").val(response.object.vendorAddress);
			    	 
					},
		});
	
	
}
</script>

 <script>
function googleApiLocation(locationId) {
	initialize(locationId);
}
function initialize(locationId) {
    	autocomplete = new google.maps.places.Autocomplete((document.getElementById(locationId)), {
    		types: ['geocode'],
	        componentRestrictions: {
	        	country: ["IN","US","AU","SG","GB","FR"]
	        }
      
        });
  google.maps.event.addListener(autocomplete, 'place_changed', function() {
  place = autocomplete.getPlace();
  var address = {};
  for (var i = 0; i < place.address_components.length; i++) {
    var addressType = place.address_components[i].types[0];
    switch (addressType) {
    case "route":
   	 address.name=place.address_components[i].long_name;
   	 break;
     case "locality":
       address.city = place.address_components[i].long_name;
       break;
     case "administrative_area_level_1":
       address.state = place.address_components[i].long_name;
       break;
     case "country":
       address.country = place.address_components[i].long_name;
       break;
     case "postal_code":
       address.zipcode = place.address_components[i].long_name;
        
    }
  }
  
  $("#country").val('');
  $("#state").val('');
  $("#city").val('');
  $("#pincode").val('');
  
  $("#country").val(address.country);
  $("#state").val(address.state);
  $("#city").val(address.city);
  $("#pincode").val(address.zipcode);
  });
}
</script>

<script>
function clearVendorLocation(){
	  $("#vendorLocation").val('');
	  $("#country").val('');
	  $("#state").val('');
	  $("#city").val('');
	  $("#pincode").val('');
}
</script>


                  