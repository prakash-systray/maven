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
	                    <h3>Entry</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        
	                        <li>
	                            <a href="${Wayuparty_appUrl}/vendorServices?vendorUUID=${vendorUUID}">Services</a>
	                        </li>
                        
	                        <li>Entry</li>
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
             <div id="entryDetailsLoadingDiv"></div>
                   <div class="card-body">
                        <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Entry Details</h3>
                            </div>
                    	</div>
                    	
                    	 <form class="new-added-form" id="add_entry_service_form" name="add_entry_service_form" >
                    	 	<div class="row mt-2">	
                    	 		
                    	 		<div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>Ticket Category<span class="text-danger">&nbsp;*</span></label>
                                    <select class="select2" name="entryType" id="entryType" data-validation="required"  field-name="Ticket Category">
                                    </select>
                                 </div>
                                 
                                 <div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>Event Name<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="eventName" id="eventName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-100"  field-name="Event Name">
                                </div>
                                
                               <div class="col-xl-3 col-lg-6 col-12 form-group" id="entryDiv">
                                         <label>Start Date<span class="text-danger">&nbsp;*</span></label>
                                         <input type="text" placeholder="dd/mm/yyyy" class="form-control start-date-datepicker"
                                             data-position='bottom right' name="startDate" id="startDate" data-validation="required" field-name="Start Date">
                                         <i class="far fa-calendar-alt"></i>
                               </div>
                               
                               <div class="col-xl-3 col-lg-6 col-12 form-group">
                                         <label>End Date<span class="text-danger">&nbsp;*</span></label>
                                         <input type="text" placeholder="dd/mm/yyyy" class="form-control end-date-datepicker"
                                             data-position='bottom right' name="endDate" id="endDate" data-validation="required" field-name="End Date">
                                         <i class="far fa-calendar-alt"></i>
                               </div> 
                                 
                                <div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>Actual Price<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="actualPrice" id="actualPrice"  onblur="comparePrices();" data-validation="required validate_Space validate_float validate_length length1-10"  field-name="Actual Price">
                                </div>
                                
                                 <div class="col-xl-3 col-lg-6 col-12 form-group" id="priceDiv">
                                    <label>Offer Price<span class="text-danger">&nbsp;*</span></label>
                                    <input type="text" class="form-control"  name="offerPrice" id="offerPrice" onblur="comparePrices();" data-validation="required validate_Space validate_float validate_length length1-10"  field-name="Offer Price">
                                </div>
                                
                                <div class="col-xl-3 col-lg-6 col-12 form-group" id="divTimeRow">
                                    <label>Start Time<span class="text-danger">&nbsp;*</span></label>
                                    <div class='input-group bootstrap-timepicker'><input type='text' readonly='readonly' class='form-control' data-validation='required' field-name='Start Time' name='startTime' id='startTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 15px;'></i></span></div>
                                </div>
                                
                                <div class="col-xl-3 col-lg-6 col-12 form-group">
                                    <label>End Time<span class="text-danger">&nbsp;*</span></label>
                                    <div class='input-group bootstrap-timepicker'><input type='text' readonly='readonly' class='form-control' data-validation='required' field-name='End Time' name='endTime' id='endTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 15px;'></i></span></div>
                                </div>
                                
                               
				                <div class="col-xl-4 col-lg-6 col-12 form-group">
                                    <label>Music Genre<span class="text-danger">&nbsp;*</span></label>
                                    <select class="select2" class="form-control" multiple name="musicGenre" id="musicGenre"  data-validation="required" field-name="Music Genre">
                                       
                                    </select>
	                              </div>
				                  
				                <div class="col-lg-4 col-12 form-group">
                                    <label>Artist<span class="text-danger">&nbsp;*</span></label>
                                    <textarea class="textarea form-control" name="artist" id="artist" cols="10" rows="5" 
                                    data-validation="required validate_space" field-name="Artist" ></textarea>
                                </div>
                                
                                <div class="col-xl-4 col-lg-6 col-12 form-group" id="serviceImg">
                                    <label>Upload Image<span class="text-danger">&nbsp;*</span></label>
                                    <input type="file" class="form-control-file" id="uploadImageBtn" field-name="Upload Image"  data-validation="required validateImg">
                                    <input type="text" class="form-control" readonly
										id="uploadImageFile" style="display: none;" name="docFile" >
                                 </div>
                                 
				                  <div class="col-xl-4 col-lg-6 col-12 form-group" id="savedServiceImg" style="display: none">
				                      <label>Uploaded Photo</label>
				                      <div class="summaryProfilePic-image" id="uploadImageBtn">
											<div id="savedImage"></div>			
									  </div>
									<div class="clearUpload clearUpload-profile-position" title="Delete Uploaded Photo" onclick="deleteServiceImage();"></div>
				                  </div>
                                
                                <div class="col-lg-4 col-12 form-group">
                                    <label>Event Location<span class="text-danger">&nbsp;*</span></label>
                                    <textarea class="textarea form-control" name="eventLocation" id="eventLocation" cols="10" rows="5" 
                                    data-validation="required validate_space" field-name="Event Location" ></textarea>
                                </div>
                                
                                 <div class="col-lg-4 col-12 form-group">
                                    <label>Description<span class="text-danger">&nbsp;*</span></label>
                                    <textarea class="textarea form-control" name="description" id="description" cols="10" rows="5" 
                                    data-validation="required validate_space" field-name="Description" ></textarea>
                                </div>
                                
                                <div class="col-lg-4 col-12 form-group">
                                    <label>Terms & Condidtions<span class="text-danger">&nbsp;*</span></label>
                                    <textarea class="textarea form-control" name="termsAndConditions" id="termsAndConditions" cols="10" rows="5" 
                                    data-validation="required validate_space" field-name="Terms & Conditions" ></textarea>
                                </div>
                                
					             
                    	   </div><br/>
                    	 
                    	 <div class="row">	
						   <div class="col-12 ui-btn-wrap">
						               <ul>
						               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="compareTimeSlots()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
						               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/bottle?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
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
             <script src="/resources/js/bootstrap-timepicker.js"></script> 
            </div>
            
            
     <script type="text/javascript">
		window.onload = function () {
			getEntryCategories();
			var entryUUID = '${entryUUID}';
			if(entryUUID != null && entryUUID != '' && entryUUID != 'undefined'){
				getEntryDetails(entryUUID);
				$('.bootstrap-timepicker > input').timepicker();
			}else{
				getMusicGenre();
				$('.bootstrap-timepicker > input').timepicker();
			}
			
		 };
    </script>	
    
        <script>
    function comparePrices(){
    	var actualPrice = $('#actualPrice').val();
    	var offerPrice = $('#offerPrice').val();
    	
    	if(actualPrice && offerPrice){
    		if((parseFloat(offerPrice) == parseFloat(actualPrice)) || 
    				(parseFloat(offerPrice) < parseFloat(actualPrice))){
    			
    			$("#save_button").removeAttr("disabled");
	    		$('input#offerPrice').removeAttr( "class record-exist record-exist-errormsg");
				$('#priceDiv').find('p.jquery_form_error_message').remove();
				$('input#offerPrice').attr('class','valid form-control');
				$( "#save_button" ).removeAttr("style");
				$( "#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
				$( "#save_button" ).click(function() {
				});
    		}else{
    			$('#priceDiv').find('p.jquery_form_error_message').remove(); 
	    		$('input#offerPrice').attr('class','error form-control');
	    		$("input#offerPrice").after("<p class='jquery_form_error_message'>Offer Price should less than or equal to Actual Price</p>");
				$('input#offerPrice').attr('record-exist','yes');
				$('input#offerPrice').attr('record-exist-errorMsg',' Offer Price should less than or equal to Actual Price'); 
				$("#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
    		}
    	}
    	
    	
    }
    </script>
    
    <script>
	   function getEntryDetails(entryUUID){
	    	   var appUrl ='${appUrl}';
	    	   $("#entryDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	    	   $.ajax({
	    			  type: "GET",
	    			    url: appUrl+"/getVendorMasterServiceDetails?${_csrf.parameterName}=${_csrf.token}&serviceUUID="+entryUUID,
	    			     success :function(response) {
	    			    	    
	    			    	 $("select[name=entryType").val(response.object.categoryId); 
	    			    	 document.getElementById('select2-entryType-container').innerHTML = response.object.category;
	    			    	 $("#eventName").val(response.object.subCategory);
	    			    	 $("#actualPrice").val(response.object.actualPrice);
	    			    	 $("#offerPrice").val(response.object.offerPrice);
	    			    	 $("#startDate").val(response.object.serviceStartDate);
	    			    	 $("#endDate").val(response.object.serviceEndDate);
	    			    	 getSavedEndDateDatePicker();
	    			    	 
	    			    	 if(response.object.serviceImage != ''){
	    			    		 document.getElementById("serviceImg").style.display="none";
	    			    		 document.getElementById("savedServiceImg").style.display="block";
	    			    		 $("#savedImage").html('<img src="'+response.object.serviceImage+'" data-id= "vendorProfileImage" >');
	    			    		 $("#uploadImageBtn").removeAttr('data-validation');
	    			    		 $("#uploadImageBtn").attr('data-validation','validateImg');
	    			    		 $("#uploadImageBtn").attr('data-validation-optional','true');
	    			    	 }else{
	    			    		 document.getElementById("savedServiceImg").style.display="none";
	    			    		 document.getElementById("serviceImg").style.display="block";
	    			    	 }
	    			    	 
	    			    	 getMusicGenre(response.object.musicGenre);
	    			    	 $("textarea[name=description]").val(response.object.description);
	    			    	 $("textarea[name=eventLocation]").val(response.object.eventLocation);
	    			    	 $("textarea[name=artist]").val(response.object.artist);
	    			    	 $("textarea[name=termsAndConditions]").val(response.object.termsAndConditions);
	    			    	 
	    			    	 
	    			    	 for (var i=0; i<response.object.timeSlotList.length; i++){
				    			 var opt = response.object.timeSlotList[i];
				    			 $("#startTime").val(opt.startTime);
		    			    	 $("#endTime").val(opt.endTime);
				    		
	    			    	 }
	    			    	 
	    			    	$("#entryDetailsLoadingDiv").removeAttr("style");
	    			    	
	    			    	$('.bootstrap-timepicker > input').timepicker();
	    			    	
	    					},
	    		});
	    }
	   
	   function deleteServiceImage(){
			document.getElementById("serviceImg").style.display="block";
			document.getElementById("savedServiceImg").style.display="none";
			$("#uploadImageBtn").removeAttr('data-validation-optional');
   		    $("#uploadImageBtn").attr('data-validation','required validateImg');
		}
	   
	   function deleteTimeRow(rowId){
			document.getElementById("divTimeRow"+rowId).remove();
			
			 var timeRowCount = document.getElementById("divTimeRow").value;
			 $("input[id = 'divTimeRow']").val(parseInt(timeRowCount) - 1);
			 
			 var documentsRow = document.getElementById("divTimeRow").value;
		}
    </script>
    
     
  <script>
    function getEntryCategories(){
    	$("#entryDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	 var appUrl ='${appUrl}';
    	 var serviceUUID = '${serviceUUID}';
		 $.ajax({
		    	type: "GET",
		        url: appUrl+"/getServiceCategoriesTypes?serviceUUID="+serviceUUID, 
		        success: function(response) {
		        	 var output = "";
		        	output = output + "<option value=''>Select Category</option>";
		        	for (var i=0; i<response.data.length; i++)
					{
			        		var opt = response.data[i];
			        		output = output + "<option value='" +opt.categoryId+ "' >"+opt.categoryName+"</option>";
			        		
			        		
					}
		        	$("#entryType").empty();
			        $("#entryType").append(output); 
			        $('#entryType').trigger("chosen:updated");
			        $("#entryDetailsLoadingDiv").removeAttr("style");
		        },
		    });
    }
    
    
    function getMusicGenre(musicGenre){
    
     $("#entryDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
   	 var appUrl = '${appUrl}';
   	 var vendorUUID = '${vendorUUID}';
   	 var formData = new FormData();
	 formData.append("categoryType", "Music Genre");
	 formData.append("vendorUUID", vendorUUID);
	 
     $.ajax({
 	    data: formData,
 	    contentType: false,
 	    processData: false,
	    	type: "POST",
	    	url: appUrl+"/getProfileCategories?${_csrf.parameterName}=${_csrf.token}", 
	        success: function(resultData) {
	   			
	        	 var result = "";
	        	
	        	  for (var i=0; i<resultData.data.length; i++)
	   				{ 
	        		  var opt = resultData.data[i];
	        		  if(opt.isCategoryConfigured == 'Yes'){
	        			  var selected = ''
	        			  if(musicGenre != null && musicGenre != '' && musicGenre != 'undefined'){
	        				  var musicGenreList = musicGenre.split(',');
	        				  if(musicGenreList.includes(opt.categoryName)){
	        					  selected = 'selected';
	        				  }
	        			  }
	        			  
	        			  result = result + "<option value='" +opt.categoryName+ "' "+selected+">"+opt.categoryName+"</option>";
	        		  }
	   				}
	        	  
	        	 $("#musicGenre").empty();  
	    	     $("#musicGenre").append(result);
	    	     $('#musicGenre').trigger("chosen:updated");
			     $("#entryDetailsLoadingDiv").removeAttr("style");
	    	     
	        },
	    });
    }
    
    
    
    var dateToday = new Date();
    $('.start-date-datepicker').datepicker({
      language: {
        days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
        daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        today: 'Today',
        clear: 'Clear',
        dateFormat: 'dd/mm/yyyy',
        firstDay: 0,
      },
      minDate: dateToday,
      onSelect : function(selected) {
      	getEndDateDatePicker();
  	 }
    });
    
    function getEndDateDatePicker(){
    	 var endDatepicker = $('#endDate').datepicker().data('datepicker');
    	 endDatepicker.clear();
    	 var startDate = $('#startDate').val();
    	 var dateParts = startDate.split('/');
    	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
        $('.end-date-datepicker').datepicker({
            language: {
              days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
              daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
              daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
              months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
              monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
              today: 'Today',
              clear: 'Clear',
              dateFormat: 'dd/mm/yyyy',
              firstDay: 0,
            },
            minDate: minimumdate,
            onSelect : function(selected) {
              validateVendorService();
          	 }
            
        });
    }
    
    function getSavedEndDateDatePicker(){
     	 var startDate = $('#startDate').val();
     	 var dateParts = startDate.split('/');
     	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
         $('.end-date-datepicker').datepicker({
             language: {
               days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
               daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
               daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
               months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
               monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
               today: 'Today',
               clear: 'Clear',
               dateFormat: 'dd/mm/yyyy',
               firstDay: 0,
             },
             minDate: minimumdate,
             onSelect : function(selected) {
           	  validateVendorService();
           	 }
             
         });
     }
    
    
    
    function validateVendorService(){
    	 var startDate = $('#startDate').val();
    	 var endDate = $('#endDate').val();
    	 var serviceId = $('#entryType').val();
    	 
    	 if(startDate != null  && startDate != '' && startDate != 'undefined' && 
    			 endDate != null  && endDate != '' && endDate != 'undefined'){
    		 var vendorUUID = '${vendorUUID}';
    		 var appUrl = '${appUrl}';
    		 var entryUUID = '${entryUUID}';
    		 var formData = new FormData();
    		 formData.append("vendorUUID", vendorUUID);
    		 formData.append("startDate", startDate);
    		 formData.append("endDate", endDate);
    		 formData.append("serviceId", serviceId);
    		 formData.append("masterServiceUUID", entryUUID);
    		 
    		     $.ajax({
    		    		data: formData,
    		    	    contentType: false,
    		    	    processData: false,
    			    	type: "POST",
    			    	url: appUrl+"/validateVendorService?${_csrf.parameterName}=${_csrf.token}", 
    			        success: function(result) {
    			        	
    			        	
    			        	if(result && result.response == "VALID_DATA"){
    			        		
    			        		$("#save_button").removeAttr("disabled");
    				    		$('input#startDate').removeAttr( "class record-exist record-exist-errormsg");
    							$('#tableDiv').find('p.jquery_form_error_message').remove();
    							$('input#startDate').attr('class','valid form-control');
    							$( "#save_button" ).removeAttr("style");
    							$( "#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
    							$( "#save_button" ).click(function() {
    							});
    							
    			        	}else if (result && result.response === "AWKWARD") {
    			        		location.href = "/errorPage";
    			        	}else{
    			        		$('#tableDiv').find('p.jquery_form_error_message').remove(); 
    				    		$('input#startDate').attr('class','error form-control');
    				    		$("input#startDate").after("<p class='jquery_form_error_message'>Service is already exists between these dates</p>");
    							$('input#startDate').attr('record-exist','yes');
    							$('input#startDate').attr('record-exist-errorMsg',' Service is already exists between these dates'); 
    							$("#save_button" ).addClass('btn-fill-lg btn-gradient-green btn-hover-bluedark').val('Save');
    			        	}
    	 
    			        },
    			    }); 
    	 }
    	 
    }
    
    
    function compareTimeSlots() {
    	
        var strStartTime = document.getElementById("startTime").value;
        var strEndTime = document.getElementById("endTime").value;

        var startTime = new Date().setHours(GetHours(strStartTime), GetMinutes(strStartTime), 0);
        var endTime = new Date(startTime)
        endTime = endTime.setHours(GetHours(strEndTime), GetMinutes(strEndTime), 0);
        if (startTime < endTime) {
            $('input#startTime').removeAttr("class record-exist record-exist-errormsg");
			$('input#startTime').attr('class','valid form-control');
			$('input#startTime').attr('style','background-color:#f0f1f3');
			$('#divTimeRow').find('p.jquery_form_error_message').remove();
			$( "#save_button" ).removeAttr("style");
			$( "#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
        }else{
        	$('#divTimeRow').find('p.jquery_form_error_message').remove(); 
    		$('input#startTime').attr('class','error form-control');
    		$('input#startTime').attr('record-exist','yes');
    		$('input#startTime').attr('style','background-color: #f65151');
    		$('input#startTime').after("<p class='jquery_form_error_message'></p>");
    		$('input#startTime').attr('record-exist-errorMsg',''); 
			$("#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
        }
    	
    	saveEntryService();
  
    }
    function GetHours(d) {
        var h = parseInt(d.split(':')[0]);
        if (d.split(':')[1].split(' ')[1] == "PM") {
            h = h + 12;
        }
        return h;
    }
    function GetMinutes(d) {
        return parseInt(d.split(':')[1].split(' ')[0]);
    }
    
 </script>
 
 
 <script>
 function saveEntryService(){
	   if ($('#add_entry_service_form').validate(false, validationSettings)){
		   $("#entryDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID = "${vendorUUID}";
           var serviceUUID = "${serviceUUID}";
           var entryUUID = "${entryUUID}";
           var formData = getWayupayFormData("add_entry_service_form");
           formData.append("vendorUUID",vendorUUID);
           formData.append("serviceUUID",serviceUUID);
           formData.append("entryUUID",entryUUID);
           
             $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveEntryService?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#entryDetailsLoadingDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#entryDetailsLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully added new entry service.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     location.replace(appUrl+"/vendorServices?vendorUUID="+vendorUUID);
			                       }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_entry_service_form', function() {
		$("#add_entry_service_form").showHelpOnFocus();
		$("#add_entry_service_form").validateOnBlur(false, validationSettings);  
   });
 </script>
 
 
  <script>
   
   document.getElementById("uploadImageBtn").onchange = function () {
       $('#uploadImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
   };
   $(function () {
       $("#uploadImageBtn").change(function () {
    	   var vendorId = '${vendorId}';
           fileUpload("uploadImageBtn", "vendor_services_images", "add_entry_service_form",vendorId,0);
           $(".clearUpload").show();
       });
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
 
 
 <style>
 
 .select2-container--default .select2-selection--multiple .select2-selection__rendered {
     background-color: #f0f1f3 !important;
     border-radius: 4px !important;
     padding: 5px 15px !important;
}

 .select2-container--default.select2-container--focus .select2-selection--multiple {
    border: 1px solid #ced4da !important;
    outline: 0 !important;
    background-color: #f0f1f3 !important;
    height: auto;
    border-radius: 4px;
    padding: 5px 15px;
    }
    
    
 </style>