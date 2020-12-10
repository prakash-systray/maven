<div class="tab-pane fade" id="facilities" role="tabpanel">
<div id="vendorFacilitiesDetailsLoaderDiv"></div>
		<section class="clearfix">
				     <div class="select-role clearfix section-action-btns">
				     
				     <div class="clearfix">
					 <div id="facilitiesListDiv"></div>
				     </div>
				     <br/>
                           <div class="ui-btn-wrap">
					             <ul>
					               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveProfileFacilities()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
					               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				                   <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
					             </ul>
					       </div>
                     
				        </div>
				 </section>
</div>

<div class="alert icon-alart bg-light-green2" id="successFacilitiesMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successFacilitiesMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidFacilitiesMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidFacilitiesMsg" style="color: white;"></span> 
</div>



<script>
function saveProfileFacilities(){
	 var facilitiesIds = [];
	 $("input[name='facilitiesList[]']:checked").each( function (i) {
		 facilitiesIds[i] = $(this).val();
	 }); 
		 var appUrl ='${appUrl}';
		 var vendorUUID = '${vendorUUID}';
		 var formData = new FormData();
		 formData.append("categoryType", "Facilities");
		 formData.append("vendorUUID", vendorUUID);
		 formData.append("facilitiesIds", facilitiesIds);
		 
			if(facilitiesIds.length > 0){
				 $("#vendorsFacilitiesDetailsLoaderDiv").attr('style','position:absolute; width:150%; height:80%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
				$.ajax({ 
				    data: formData,
			    	contentType: false,
			    	processData: false,
				    type: "POST",
			    	url: appUrl+"/saveVendorProfileCategories?${_csrf.parameterName}=${_csrf.token}",
			        success: function(result) {
			        	 if (result.response === "SUCCESS") {
			        		 $("#successFacilitiesMsgDiv").removeAttr("style");
				    		 $("#successFacilitiesMsgDiv").css({ display: "block" });
					         $("#successFacilitiesMsg").html("Well done! You successfully added facilities.");
					         $("#successFacilitiesMsgDiv").fadeIn(200);
					 	     $('#successFacilitiesMsgDiv').delay(2000).fadeOut('slow');
					 	     $("#vendorsFacilitiesDetailsLoaderDiv").removeAttr("style");
					 	     loadVendorFacilitiesDetails();
				        }else if (result.response === "AWKWARD") {
			        		   $("#vendorsFacilitiesDetailsLoaderDiv").removeAttr("style");
			    		        location.href = "/errorPage";
			    	     }else{
				        	 $("#invalidFacilitiesMsgDiv").removeAttr("style");
				    		 $("#invalidFacilitiesMsgDiv").css({ display: "block" });
					         $("#invalidFacilitiesMsg").html("Opps! Something went worng.");
					         $("#invalidFacilitiesMsgDiv").fadeIn(200);
					 	     $('#invalidFacilitiesMsgDiv').delay(2000).fadeOut('slow');
					 	     $("#vendorsFacilitiesDetailsLoaderDiv").removeAttr("style");
				        }
			        },
			 }); 
			}else{
				 $("#vendorsFacilitiesDetailsLoaderDiv").removeAttr("style");
				 $("#invalidFacilitiesMsgDiv").removeAttr("style");
	  		     $("#invalidFacilitiesMsgDiv").css({ display: "block" });
		         $("#invalidFacilitiesMsg").html("Opps! Please select atleat one facility.");
		         $("#invalidFacilitiesMsgDiv").fadeIn(200);
		 	     $('#invalidFacilitiesMsgDiv').delay(2000).fadeOut('slow');
			} 
	
	 
}
</script>

<script>
function loadVendorFacilitiesDetails(){
	
	
	 var appUrl ='${appUrl}';
	 var vendorUUID = "${vendorUUID}";
	 
	 var formData = new FormData();
	 formData.append("categoryType", "Facilities");
	 formData.append("vendorUUID", vendorUUID);
	   
	 $("#vendorFacilitiesDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
	    	    data: formData,
	    	    contentType: false,
	    	    processData: false,
		    	type: "POST",
		    	url: appUrl+"/getProfileCategories?${_csrf.parameterName}=${_csrf.token}", 
		        success: function(resultData) {
		   			
		        	 var result = "";
		        	
		        	  result = result +'<select id="fecilities-select" multiple="multiple">';
		        	  for (var i=0; i<resultData.data.length; i++)
		   				{ 
		        		  var opt = resultData.data[i];
		        		  var selected = ''
			        		  if(opt.isCategoryConfigured == 'Yes'){
			        			  selected = 'selected'
			        		  }
			        		  result = result +'<option value='+opt.categoryId+' data-section='+opt.categoryName+' '+selected+' >'+opt.categoryName+'</option>';
		   				}
		        	  
		        	  result = result +'result = result +"</select>';
                  
		        	 
		        	 $("#facilitiesListDiv").empty();  
	   	    	     $("#facilitiesListDiv").append(result);
	   	    	     
		        },
		    }); 
	     
		  setTimeout(function () {
			 $("#fecilities-select").treeMultiselect({ enableSelectAll: true},"Facilities");
			 $("#vendorFacilitiesDetailsLoaderDiv").removeAttr("style");
		  }, 500); 
}
</script>
		 	 	