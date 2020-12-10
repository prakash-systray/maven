<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="ui-modal-box">
  <div class="modal-box">
         <div class="modal fade" id="small-modal" tabindex="-1" role="dialog" aria-hidden="true">
                     <div class="modal-dialog modal-sm" role="document">
                         <div class="dashboard-content-one modal-content mt-10">
                         <div id="addMenuLoadingDiv"></div>
                          <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                   <i class="far fa-hand-point-right bg-light-green3"></i>
                                  <span id="successMsg" style="color: white;"></span> 
            			  </div>
                             <div class="modal-header">
                                 <h5 class="modal-title">Menu Item</h5>
                                 <button type="button" class="close" data-dismiss="modal"
                                     aria-label="Close">
                                     <span aria-hidden="true">&times;</span>
                                 </button>
                             </div>
                             <form class="new-added-form" id="add_menu_form" name="add_menu_form" >
                                 <div class="row gutters-15">
	                     			<div class="form-group col-12" id="menuItemDiv">
	                                    <label>Menu Item <span class="text-danger">&nbsp;*</span></label>
	                                    <input type="text" class="form-control" id="menuItem" name="menuItem" data-validation="required validate_Space validate_length length1-100"  field-name="Menu Item">
	                                    <input type="hidden" name="menuItemUUID" id="menuItemUUID">
	                                </div>
	                                 <div class="clearfix"></div>
                                 <div class="clearfix"></div>
                                </div>
                             <div class="modal-footer">
                                 <button type="button" class="footer-btn text-light gradient-pastel-green" id="save_button" onclick="validateMenuItem()">Save</button>
                                 <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal">Close</button>
                             </div>
                             </form>
                         </div>
                     </div>
        </div>
  </div>
</div>

<script>
function clearForm(){
	document.getElementById("add_menu_form").reset();
	$("#menuItem").val('');
	$("#menuItemUUID").val('');
	
	$("#save_button").removeAttr("disabled");
	$('#menuItemDiv').find('p.jquery_form_error_message').remove();
	
}

function getMenuEditForm(menuItemUUID){
	
	document.getElementById("add_menu_form").reset();
	$("#menuItem").val('');
	$("#menuItemUUID").val('');
	
	$("#save_button").removeAttr("disabled");
	$('#menuItemDiv').find('p.jquery_form_error_message').remove();
	getMenuItemDetails(menuItemUUID);
	
} 

</script>

<script>
   function getMenuItemDetails(menuItemUUID){
	   var appUrl ='${appUrl}';
	   $("#addMenuLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getPackageMenuItemDetails?${_csrf.parameterName}=${_csrf.token}&menuItemUUID="+menuItemUUID,
			     success :function(result) {
			    	    
			    	 if (result && result.response === "SUCCESS") {
			    		 $("#menuItem").val(result.object.menuItem);
			    		 $("#menuItemUUID").val(result.object.menuItemUUID);
			    		 $("#addMenuLoadingDiv").removeAttr("style");
			    		 
			    	 }else if (result && result.response === "AWKWARD") {
		        		 location.href = "/errorPage";
			          } 
			    	 
					},
		});
   }
   </script> 


<script type="text/javascript">
	 function validateMenuItem() {   
		var menuItem = $('input#menuItem').val();
		var menuItemUUID =  $('input#menuItemUUID').val();
		if(menuItem != null  && menuItem != '' && menuItem != 'undefined'){
			var appUrl ='${appUrl}';
			var vendorUUID ='${vendorUUID}';
			var formData = new FormData();
			formData.append("menuItem", menuItem);
			formData.append("menuItemUUID", menuItemUUID);
			formData.append("vendorUUID", vendorUUID);
		     $.ajax({
		    		data: formData,
		    	    contentType: false,
		    	    processData: false,
			    	type: "POST",
			    	 url: appUrl+"/validateMenuName?${_csrf.parameterName}=${_csrf.token}", 
			        success: function(result) {
			        	
			        	
			        	if(result && result.response == "VALID_DATA"){
			        		
			        		$("#save_button").removeAttr("disabled");
				    		$('input#menuItem').removeAttr( "class record-exist record-exist-errormsg");
							$('#menuItemDiv').find('p.jquery_form_error_message').remove();
							$('input#menuItem').attr('class','valid form-control');
							$( "#save_button" ).removeAttr("style");
							$( "#save_button" ).addClass('footer-btn bg-linkedin').val('Save');
							saveMenuOfferingItem();
			        	}else if (result && result.response === "AWKWARD") {
			        		 location.href = "/errorPage";
			        	}else{
			        		$('#menuItemDiv').find('p.jquery_form_error_message').remove(); 
				    		$('input#menuItem').attr('class','error form-control');
				    		$("input#menuItem").after("<p class='jquery_form_error_message'>Menu Offering Name already exists</p>");
							$('input#menuItem').attr('record-exist','yes');
							$('input#menuItem').attr('record-exist-errorMsg',' Menu Offering Name already exists'); 
							$("#save_button" ).addClass('footer-btn bg-linkedin').val('Save');
			        	}
	 
			        },
			    }); 
				
		}
		
	 }
 </script>
 
 
 
 <script>
 function saveMenuOfferingItem(){
	   if ($('#add_menu_form').validate(false, validationSettings)){
		   $("#addMenuLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID ='${vendorUUID}';
           var formData = getWayupayFormData("add_menu_form");
           formData.append("vendorUUID", vendorUUID);
             $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveMenuOfferingItem?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	   if (result.response === "AWKWARD") {
				        		   $("#addMenuLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully added new menu Item.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     location.replace(appUrl+"/packageSettings?vendorUUID="+vendorUUID);
			                       }
				        	
				        		
				},
		});    
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_menu_form', function() {
		$("#add_menu_form").showHelpOnFocus();
		$("#add_menu_form").validateOnBlur(false, validationSettings);  
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