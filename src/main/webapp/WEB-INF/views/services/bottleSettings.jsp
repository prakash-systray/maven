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
	                    <h3>Bottle Settings</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        
	                         <li>
	                            <a href="${Wayuparty_appUrl}/vendorServices?vendorUUID=${vendorUUID}">Services</a>
	                        </li>
                        
	                        <li>Bottle Settings</li>
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
            
            
            <div class="card ui-tab-card">
             <div id="bottleDetailsLoadingDiv"></div>
                   <div class="card-body">
                        <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Bottle Details</h3>
                            </div>
                    	</div>
                    	
        	 		   <div class="vertical-tab">
                            <ul class="nav nav-tabs flex-column" role="tablist">
                     			<c:forEach var="category" items="${serviceCategoryList}">
	                       			<li class="nav-item">
	                                    <a class="nav-link" style="cursor:pointer;" id="bottle${category.categoryId}" onclick="getBottleCategories('${category.categoryId}')" aria-selected="true"><img src="/resources/img/bottle.jpg"></img>&nbsp;<b>${category.categoryName}</b></a>
	                                </li>
                        		</c:forEach>
                            </ul>
                            <div class="tab-content">
                            <div class="item-title">
                                <h3>Bottle Name</h3>
                            </div>
                              <form class="new-added-form" id="add_bottle_form" name="add_bottle_form" >
						 		   <div id="bottlesListDiv"></div>
					 		</form>
                            </div>
                        </div>
                    	
       </div>
   </div>
            </div>
        </div>
        <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 
 <style>
 .nav-item img{
	max-width: 30 !important;
    margin-left: -25px;
}

.ui-tab-card .card-body .vertical-tab .nav-tabs .nav-item .nav-link:after {
  content: "";
  width: 0;
  height: 0;
  border-left: 10px solid #000000a8;
  border-top: 8px solid transparent;
  border-bottom: 8px solid transparent;
  position: absolute;
  top: 50%;
  right: 0;
  transform: translateY(-50%);
  visibility: hidden;
  opacity: 0;
}

.ui-tab-card .card-body .vertical-tab .nav-tabs {
  margin-right: 150px !important;
}

.ui-tab-card .card-body .vertical-tab .nav-tabs .nav-item .nav-link:hover {
  border-left: 5px solid #be9c52 !important;
}
 </style>
 
  <script type="text/javascript">
		window.onload = function () {
			 getBottleCategories(1);
		 };
 </script>
 
 <div id="bottleTd" style="display: none; float: left;">
	  <input type="text" style="width:350px; height: 45px; border:1px solid #ddd; margin:0 15px 3px 0; font-size:15px; position:relative; border-color: #c3bfb8;" class='form-control' id='REPLACEROWIDbottleName' name="bottleInfo[REPLACEROWID].bottleName"  data-validation="required validate_space" field-name="Bottle Name" > 
</div>

<div id="divRemoveBottleTd" style="display: none; float: left;">
   &nbsp;<a href="javascript:void(0);"><i class="fa fa-times text-danger" onclick="removeBottleRow(REPLACEROWID);"></i></a>
</div> 

 
 <script>
 function getBottleCategories(categoryId){
 $( '.nav-tabs li' ).find( 'a' ).removeClass('active');
 $("#bottle"+categoryId).addClass( 'nav-link active' );
 var appUrl = '${appUrl}';
 var vendorUUID = '${vendorUUID}';
 
 $("#bottleDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
 
 $.ajax({
 	type: "GET",
 	 url: appUrl+"/getServiceSubCategoryDetails?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID+"&categoryId="+categoryId, 
     success: function(response) {
     	var rowCount = "";
     	var result = "";
     	var dataValidationString = "";
	        	 if(response.data != null && response.data.length > 0){
	        		 rowCount = parseInt(response.data.length) + parseInt(1);
	        		 dataValidationString = "data-validation-optional='true' data-validation='validate_space'";
	        		 result = result +"<table  cellpadding='0' cellspacing='0'>";
	        		 result = result +"<input type='hidden' id='savedBottleRowCount' value='"+response.data.length+"' />";
	        		 for (var i=0; i<response.data.length; i++){
		    			 var counter = i+1;
		    			 var opt = response.data[i];
		    			 
					 	 result = result +"<tr id='divSavedBottleRow"+counter+"'>";
					 	 result = result +'<td><input type="text" style="width:350px; height: 45px; font-size:15px; border:1px solid #ddd; margin:0 15px 3px 0; position:relative; border-color: #c3bfb8;" class="form-control"  value="'+opt.subCategoryName+'" data-validation="required validate_space" field-name="Bottle Name" name="bottleInfo['+counter+'].bottleName" id="'+counter+'bottleName"></td>';
					 	 result = result +'<td><input type="hidden" class="form-control"  value="'+opt.subCategoryUUID+'"  name="bottleInfo['+counter+'].bottleUUID" id="'+counter+'bottleUUID"></td>';
					 	 result = result +'<td><a href="javascript:void(0);">&nbsp;<i class="fa fa-times text-danger"  onclick="deleteBottleName(\''+counter+'\',\''+opt.subCategoryUUID+'\')"></i></a></td>';
					 	 result = result +"</tr>";
					 	
	        		 }
	        		result = result +"</table>"; 
	        		 
	        	 }else{
	        		 rowCount = "1";
	        		 dataValidationString = "data-validation='required validate_space'";
	        	 }
     	
	        	 result = result +"<input type='hidden' name='bottleAttcahmentMaxRowNumber'  id='bottleAttcahmentMaxRowNumber' value='1' />";
	         	 result = result +"<input type='hidden' id='addedBottleRowCount' value='"+rowCount+"' />";
	        	 result = result +"<table   id='bottleAttachmentTable' cellpadding='0' cellspacing='0'>";
	        	 result = result +"<tr id='divBottleRow0'>";
	        	 result = result +"<td><input type='text' style='width:350px; height: 45px; font-size:15px; border:1px solid #ddd; margin:0 15px 3px 0; position:relative; border-color: #c3bfb8;' class='form-control' "+dataValidationString+" field-name='Bottle Name' name='bottleInfo[0].bottleName' id='0bottleName'></td>";
	        	 result = result +"<td>&nbsp;<a href='javascript:void(0);'><i class='fa fa-plus text-success' id='addBottleRow' onclick='addBottleRow()'></i></a></td>";
	        	 result = result +"</tr>";
	        	 result = result +"</table>";
	        	 result = result +'<div class="modal-footer">';
	        	 result = result +'<button type="button" style="cursor:pointer;" class="btn-fill-lg font-normal text-light gradient-pastel-green" onclick="saveBottleDetails(\''+categoryId+'\')"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button>';
	        	 result = result +"</div>";
		  	
	        	 $("#bottlesListDiv").empty();
	         	 $("#bottlesListDiv").append(result);
		         $("#bottleDetailsLoadingDiv").removeAttr("style");
     },
 });

 }
 </script>
 
 
 <script>
 function saveBottleDetails(categoryId){
	 if ($('#add_bottle_form').validate(false, validationSettings)){
		 $("#bottleDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
         var appUrl = "${appUrl}";
         var vendorUUID = '${vendorUUID}';
         var formData = getWayupayFormData("add_bottle_form");
         formData.append("categoryId", categoryId);
         formData.append("vendorUUID", vendorUUID);
         if(subCategoriesUUID != null && subCategoriesUUID != '' && subCategoriesUUID != 'undefined'){
        	 var removedSubCategoriesUUID = subCategoriesUUID.substring(0, subCategoriesUUID.length - 1);
             formData.append("removedSubCategoriesUUID", removedSubCategoriesUUID);
         }
         
           $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveCategoryBottles?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#bottleDetailsLoadingDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#bottleDetailsLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							 	     getBottleCategories(categoryId);
			                       }
				        	
				        		
				},
		});   
         return true;
	   }else{
			return false;
		  } 
}

$('body').on('blur', '#add_bottle_form', function() {
		$("#add_bottle_form").showHelpOnFocus();
		$("#add_bottle_form").validateOnBlur(false, validationSettings);  
 });
 </script>
 
 
 <script>
function addBottleRow(){  
	var checkMaxTsRowNum = $("#bottleAttcahmentMaxRowNumber").val();
	var bottleRowCount = $("#addedBottleRowCount").val();
	var tbl = document.getElementById("bottleAttachmentTable");
	var maxRowNum = parseInt($("input[name = 'bottleAttcahmentMaxRowNumber']").val());
	$("input[name = 'bottleAttcahmentMaxRowNumber']").val(maxRowNum + 1);
	$("input[id = 'addedBottleRowCount']").val(parseInt(bottleRowCount) + parseInt(1));
	var row = tbl.insertRow(maxRowNum);
	row.setAttribute("id", "divBottleRow" + bottleRowCount);
	
	
	var cell0 = row.insertCell(0);
	var divDocTypeTdStr = document.getElementById("bottleTd").innerHTML;
	divDocTypeTdStr = divDocTypeTdStr.replace(/REPLACEROWID/g, (bottleRowCount));
	cell0.innerHTML = divDocTypeTdStr;
	
	var cell1 = row.insertCell(1);
	var divDocRemoveTdStr = document.getElementById("divRemoveBottleTd").innerHTML;
	divDocRemoveTdStr = divDocRemoveTdStr.replace(/REPLACEROWID/g, (bottleRowCount));
	cell1.innerHTML = divDocRemoveTdStr;
	
}	
</script>


<script type="text/javascript">
	function removeBottleRow(rowId) {
		
		 var addedBottleRowCount = document.getElementById("addedBottleRowCount").value;
		 $("input[id = 'addedBottleRowCount']").val(parseInt(addedBottleRowCount) - 1);
		 
		 var documentsRow = document.getElementById("addedBottleRowCount").value;
		
		 if(documentsRow == 1){
			 $("#0bottleName").removeAttr("data-validation-optional");
			 $("#0bottleName").removeAttr("data-validation");
			 $("#0bottleName").attr("data-validation", "required validate_space");
			 
		 }
		 document.getElementById("divBottleRow"+rowId).style.display = 'none';
		 document.getElementById("divBottleRow"+rowId).value= '';
		 var  rId = "divBottleRow"+rowId;
		 var r = document.getElementById("bottleAttachmentTable").rows[rId];
          var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
	}
	
	var subCategoriesUUID = [];
	function deleteBottleName(rowId, subCategoryUUID){
		document.getElementById("divSavedBottleRow"+rowId).remove();
		
		 var savedBottleRowCount = document.getElementById("savedBottleRowCount").value;
		 $("input[id = 'savedBottleRowCount']").val(parseInt(savedBottleRowCount) - 1);
		 
		 var bottleRow = document.getElementById("savedBottleRowCount").value;
		 if(bottleRow == 0){
			 $("#0bottleName").removeAttr("data-validation-optional");
			 $("#0bottleName").removeAttr("data-validation");
			 $("#0bottleName").attr("data-validation", "required validate_space");
		 }
		 subCategoriesUUID = subCategoriesUUID+subCategoryUUID+",";
	}
</script>
 
 <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 