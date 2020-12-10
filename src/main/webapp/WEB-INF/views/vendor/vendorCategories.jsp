<div class="tab-pane fade" id="category" role="tabpanel">
<div id="vendorCategoryDetailsLoaderDiv"></div>
		<section class="clearfix">
				     <div class="select-role clearfix section-action-btns">
				     
				     <div class="clearfix">
					 <div id="categoriesListDiv"></div>
				     </div>
				     <br/>
                           <div class="ui-btn-wrap">
					             <ul>
					               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveProfileCategories()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
					               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				                   <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
					             </ul>
					       </div>
                     
				        </div>
				 </section>
</div>

<div class="alert icon-alart bg-light-green2" id="successCategoriesMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successCategoriesMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidCategoriesMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidCategoriesMsg" style="color: white;"></span> 
</div>


<script>
function saveProfileCategories(){
	var categoriesIds = [];
	 $("input[name='categoriesList[]']:checked").each( function (i) {
		 categoriesIds[i] = $(this).val();
	});
	 
	 var appUrl ='${appUrl}';
	 var vendorUUID = '${vendorUUID}';
	 var formData = new FormData();
	 formData.append("categoryType", "Category");
	 formData.append("vendorUUID", vendorUUID);
	 formData.append("categoriesIds", categoriesIds);
	 
		if(categoriesIds.length > 0){
			 $("#vendorCategoryDetailsLoaderDiv").attr('style','position:absolute; width:150%; height:80%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
			$.ajax({ 
			    data: formData,
		    	contentType: false,
		    	processData: false,
			    type: "POST",
		    	url: appUrl+"/saveVendorProfileCategories?${_csrf.parameterName}=${_csrf.token}",
		        success: function(result) {
		        	 if (result.response === "SUCCESS") {
		        		 $("#successCategoriesMsgDiv").removeAttr("style");
			    		 $("#successCategoriesMsgDiv").css({ display: "block" });
				         $("#successCategoriesMsg").html("Well done! You successfully added categories.");
				         $("#successCategoriesMsgDiv").fadeIn(200);
				 	     $('#successCategoriesMsgDiv').delay(2000).fadeOut('slow');
				 	     $("#vendorCategoryDetailsLoaderDiv").removeAttr("style");
				 	     loadVendorCategoryDetails();
			        }else if (result.response === "AWKWARD") {
		        		   $("#vendorCategoryDetailsLoaderDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    	     }else{
			        	 $("#invalidCategoriesMsgDiv").removeAttr("style");
			    		 $("#invalidCategoriesMsgDiv").css({ display: "block" });
				         $("#invalidCategoriesMsg").html("Opps! Something went worng.");
				         $("#invalidCategoriesMsgDiv").fadeIn(200);
				 	     $('#invalidCategoriesMsgDiv').delay(2000).fadeOut('slow');
				 	     $("#vendorCategoryDetailsLoaderDiv").removeAttr("style");
			        }
		        },
		 }); 
		}else{
			 $("#vendorCategoryDetailsLoaderDiv").removeAttr("style");
			 $("#invalidCategoriesMsgDiv").removeAttr("style");
  		     $("#invalidCategoriesMsgDiv").css({ display: "block" });
	         $("#invalidCategoriesMsg").html("Opps! Please select atleat one category.");
	         $("#invalidCategoriesMsgDiv").fadeIn(200);
	 	     $('#invalidCategoriesMsgDiv').delay(2000).fadeOut('slow');
		} 
	 
}
</script>

<script>
function loadVendorCategoryDetails(){
	
	
	 var appUrl ='${appUrl}';
	 var vendorUUID = "${vendorUUID}";
	 
	 var formData = new FormData();
	 formData.append("categoryType", "Category");
	 formData.append("vendorUUID", vendorUUID);
	   
	 $("#vendorCategoryDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
	    	    data: formData,
	    	    contentType: false,
	    	    processData: false,
		    	type: "POST",
		    	url: appUrl+"/getProfileCategories?${_csrf.parameterName}=${_csrf.token}", 
		        success: function(resultData) {
		   			
		        	 var result = "";
		        	
		        	  result = result +'<select id="categories-select" multiple="multiple">';
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
		
		        	 $("#categoriesListDiv").empty();  
	   	    	     $("#categoriesListDiv").append(result);
	   	    	     
		        },
		    }); 
	     
		 setTimeout(function () {
			 $("#categories-select").treeMultiselect({ enableSelectAll: true},"Category");
			 $("#vendorCategoryDetailsLoaderDiv").removeAttr("style");
		  }, 500);
}
</script>
		 	 	