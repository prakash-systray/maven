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
	                    <h3>Menu Items</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/packageSettings?vendorUUID=${vendorUUID}">Package Settings</a>
	                        </li>
	                        <li>Menu Items</li>
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
                    <div class="card-body">
                    <div id="menuItemsLoadingDiv"></div>
                        <div class="heading-layout1">
                            <div class="item-title">
                                <h3>${menuItem}</h3>
                            </div>
                        </div>
                        
				     <form class="new-added-form" id="add_menu_item_form" name="add_menu_item_form" >
								<input type="hidden" id="menuItemCounter" value="0">
							 	 	<div class="row">	
							 	 	<div class="col-12" id="vendorGalleryImagesDiv">
					                       <p id="galleryImagesDiv" class="row"></p>
					                 </div>
											                 
								 	 	<div class="col-12 ui-btn-wrap">
					     						<ul>
					     							 <li><button type="button" class="footer-btn gradient-pastel-green" style="cursor:pointer;" onclick="addMenuItem()"><i class="fas fa-plus mg-l-10"></i>&nbsp;Add Menu Item</button></li>
												</ul>
											<div id="menuItemContainer">
											</div>
						                </div>
						                <div class="col-12 ui-btn-wrap mt-5">
									               <ul>
									               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="savePackageMenuItems()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
									               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/configureMenuItems?menuItemUUID=${menuItemUUID}&vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
									               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/packageSettings?vendorUUID=${vendorUUID}'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
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
 window.onload = function () {
	    getSavedMenuImages();
	 }; 
	 
	 
 function getSavedMenuImages(){
	 var menuItemUUID = '${menuItemUUID}'
	 
	  var appUrl ='${appUrl}';
	   $("#menuItemsLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/loadSavedMenuItems?${_csrf.parameterName}=${_csrf.token}&menuItemUUID="+menuItemUUID,
			     success :function(response) {
			    	    
			    	 $("#menuItemsLoadingDiv").removeAttr("style");
			    	 
			    		 
			    		 var result = "";
				    	 $("#menuItemCounter").val(response.data.length+1);
				    	 
				    	 for(var i=0; i < response.data.length; i++){
				    		 var opt = response.data[i];
				    		 var menuItemCounter = i;
				    		 $('<div>').attr('style','float:left; width:200px; margin:0 15px 15px 0; position:relative; padding:5px; overflow: hidden;  color:#fff; ').append('<input id="item'+menuItemCounter+'" name="menuItemsList['+menuItemCounter+'].itemName" value="'+opt.itemName+'" type="text" class="pull-left" field-name="Menu Item" class="form-control" data-validation="required validate_space validate_AlphaNumber validate_length length1-50" />' +
				    		            '<a id="Button' + menuItemCounter + '' +
				    		            'value="" onclick = "removeMenuItem(this,'+menuItemCounter+',\''+opt.itemUUID+'\')"><i class="clearUpload clearUpload-text-box-position pull-right"></i></a>').append('<input type="hidden" name="menuItemsList['+menuItemCounter+'].itemUUID" value="'+opt.itemUUID+'" />').appendTo($('#menuItemContainer'));

				    	 }
				    	 
				    	 $("#galleryImagesDiv").empty();
				    	 $("#galleryImagesDiv").append(result);
			    	 
					},
		});
 } 	 
 </script>
 
 
<script>
function addMenuItem(){
	var menuItemCounter = $("#menuItemCounter").val();
	$('<div>').attr('style','float:left; width:200px; margin:0 15px 15px 0; position:relative; padding:5px; overflow: hidden;  color:#fff; ').append('<input id="item'+menuItemCounter+'" name="menuItemsList['+menuItemCounter+'].itemName" type="text" class="pull-left" field-name="Menu Item" class="form-control" data-validation="required validate_space validate_AlphaNumber validate_length length1-50" />' +
            '<a id="Button' + menuItemCounter + '' +
            'value="" onclick = "removeMenuItem(this,'+menuItemCounter+')"><i class="clearUpload clearUpload-text-box-position pull-right"></i></a>').appendTo($('#menuItemContainer'));

			menuItemCounter++;
    	    $("#menuItemCounter").val(menuItemCounter);
    		
}

var deletedMenuItemsUUID = [];
function removeMenuItem(div, id, itemUUID){
	 document.getElementById("menuItemContainer").removeChild(div.parentNode);
	 
	 var inputs =  $("#add_menu_item_form input[data-id=file"+id+"]");
    for(var i=0, len=inputs.length; i<len; i++){
		        inputs[i].remove();
    }
    
    if(itemUUID != null && itemUUID != '' && itemUUID != 'undefined'){
    	deletedMenuItemsUUID = deletedMenuItemsUUID+itemUUID+",";
    }
}


</script>


<script>
 function savePackageMenuItems(){
	   if ($('#add_menu_item_form').validate(false, validationSettings)){
		   $("#menuItemsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var menuItemUUID = '${menuItemUUID}';
           var vendorUUID = '${vendorUUID}';
           var formData = getWayupayFormData("add_menu_item_form");
           formData.append("menuItemUUID", menuItemUUID);
		   formData.append("vendorUUID", vendorUUID);
		   
		   if(deletedMenuItemsUUID != null && deletedMenuItemsUUID != '' && deletedMenuItemsUUID != 'undefined'){
	        	 var removedMenuItems = deletedMenuItemsUUID.substring(0, deletedMenuItemsUUID.length - 1);
	             formData.append("removedMenuItems", removedMenuItems);
	         }
			
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/savePackageMenuItems?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	   if (result && result.response === "INVALID_DATA") {
					        		 $("#menuItemsLoadingDiv").removeAttr("style");
					    		     $("#invalidMsgDiv").removeAttr("style");
						    		 $("#invalidMsgDiv").css({ display: "block" });
							         $("#invalidMsg").html("Attention! You had missed some data.");
							         $("#invalidMsgDiv").fadeIn(500);
							 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
					    		   }
				        	  else if (result.response === "AWKWARD") {
				        		   $("#menuItemsLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully saved menu items.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     location.replace(appUrl+"/configureMenuItems?menuItemUUID="+menuItemUUID+"&vendorUUID="+vendorUUID);
			                       }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_menu_item_form', function() {
		$("#add_menu_item_form").showHelpOnFocus();
		$("#add_menu_item_form").validateOnBlur(false, validationSettings);  
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