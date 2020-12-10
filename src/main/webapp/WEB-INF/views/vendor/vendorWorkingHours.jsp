
<div class="tab-pane fade" id="workinghours" role="tabpanel">
 <div id="vendorWorkingHoursDetailsLoaderDiv"></div>
		<form class="new-added-form" id="add_vendor_working_hours_form" name="add_vendor_working_hours_form" >
	 	 	<div class="row">	
		 	 	<div class="container">
				  <div class="table-responsive">
		 	 		<table class='table table-bordered table-custom table-hover' style="max-width: 80%">
		 	 			<thead class='thead-dark' style="background-color: 000; color: #fff;">
		 	 				<tr>
		 	 				<td>Day</td>
		 	 				<td>Start Time</td>
		 	 				<td>End Time</td>
		 	 				</tr>
		 	 			</thead>
		 	 			<tbody>
		 	 				<tr>
		 	 				<td style="width: 200px;">Sunday<input type="hidden" name="timeSchedulerInfo[0].workingDay" value="Sunday"></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[0].startTime" id="0startTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[0].endTime" id="0endTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				</tr>
		 	 				
		 	 				<tr>
		 	 				<td style="width: 200px;">Monday<input type="hidden" name="timeSchedulerInfo[1].workingDay" value="Monday"></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[1].startTime" id="1startTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[1].endTime" id="1endTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				</tr>
		 	 				
		 	 				<tr>
		 	 				<td style="width: 200px;">Tuesday<input type="hidden" name="timeSchedulerInfo[2].workingDay" value="Tuesday"></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[2].startTime" id="2startTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[2].endTime" id="2endTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				</tr>
		 	 				
		 	 				<tr>
		 	 				<td style="width: 200px;">Wednesday<input type="hidden" name="timeSchedulerInfo[3].workingDay"  value="Wednesday"></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[3].startTime" id="3startTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[3].endTime" id="3endTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				</tr>
		 	 				
		 	 				<tr>
		 	 				<td style="width: 200px;">Thursday<input type="hidden" name="timeSchedulerInfo[4].workingDay" value="Thursday"></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[4].startTime" id="4startTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[4].endTime" id="4endTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				</tr>
		 	 				
		 	 				<tr>
		 	 				<td style="width: 200px;">Friday<input type="hidden" name="timeSchedulerInfo[5].workingDay"  value="Friday"></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[5].startTime" id="5startTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[5].endTime" id="5endTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				</tr>
		 	 				
		 	 				<tr>
		 	 				<td style="width: 200px;">Saturday<input type="hidden" name="timeSchedulerInfo[6].workingDay" value="Saturday"></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[6].startTime" id="6startTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				<td><div class='input-group bootstrap-timepicker'><input type='text' class='form-control' style="max-width: 170px;" name="timeSchedulerInfo[6].endTime" id="6endTime"><span class='input-group-addon'>&nbsp;<i class='fa fa-clock'></i></span></div></td>
		 	 				</tr>
		 	 			</tbody>
		 	 		</table>
		 	 	</div>
	 			</div>
	 			 <div class="col-12 ui-btn-wrap">
				               <ul>
				               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendorWorkingHours()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
				               </ul>
				   </div>
 	 	    </div>
		 </form>
 </div>
 
 <script>
 function loadWrokingHoursDetails(){
	 $('.bootstrap-timepicker > input').timepicker();
	 
	 var appUrl ='${appUrl}';
	 var vendorUUID = "${vendorUUID}";
	 
	 var formData = new FormData();
	 formData.append("vendorUUID", vendorUUID);
	   
	 $("#vendorWorkingHoursDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
	    	    data: formData,
	    	    contentType: false,
	    	    processData: false,
		    	type: "POST",
		    	url: appUrl+"/getVendorWorkingHoursDetails?${_csrf.parameterName}=${_csrf.token}", 
		        success: function(resultData) {
		   			if(resultData.data != null){
		        	  for (var i=0; i<resultData.data.length; i++)
		   				{ 
		        		  var opt = resultData.data[i];
		        		  if(opt.workingDay == 'Sunday'){
		        			  $("#0startTime").val(opt.startTime);
		        			  $("#0endTime").val(opt.endTime);
		        		  }else if(opt.workingDay == 'Monday'){
		        			  $("#1startTime").val(opt.startTime);
		        			  $("#1endTime").val(opt.endTime);
		        		  }else if(opt.workingDay == 'Tuesday'){
		        			  $("#2startTime").val(opt.startTime);
		        			  $("#2endTime").val(opt.endTime);
		        		  }else if(opt.workingDay == 'Wednesday'){
		        			  $("#3startTime").val(opt.startTime);
		        			  $("#3endTime").val(opt.endTime);
		        		  }else if(opt.workingDay == 'Thursday'){
		        			  $("#4startTime").val(opt.startTime);
		        			  $("#4endTime").val(opt.endTime);
		        		  }else if(opt.workingDay == 'Friday'){
		        			  $("#5startTime").val(opt.startTime);
		        			  $("#5endTime").val(opt.endTime);
		        		  }else if(opt.workingDay == 'Saturday'){
		        			  $("#6startTime").val(opt.startTime);
		        			  $("#6endTime").val(opt.endTime);
		        		  }
		   				}
		       		 }
		        	  
		        	  $("#vendorWorkingHoursDetailsLoaderDiv").removeAttr("style");
		        },
		    }); 
 }
 </script> 
 
 <script>
 function saveVendorWorkingHours(){
	 
	 if ($('#add_vendor_working_hours_form').validate(false, validationSettings)){
		   $("#vendorWorkingHoursDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
         var appUrl = "${appUrl}";
         var vendorUUID = "${vendorUUID}";
         var formData = getWayupayFormData("add_vendor_working_hours_form");
			formData.append("vendorUUID", vendorUUID);
          $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorWorkingHours?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorWorkingHoursDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidWorkingMsgDiv").removeAttr("style");
					    		 $("#invalidWorkingMsgDiv").css({ display: "block" });
						         $("#invalidWorkingMsg").html("Attention! You had missed some data.");
						         $("#invalidWorkingMsgDiv").fadeIn(500);
						 	     $('#invalidWorkingMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorWorkingHoursDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {    
				    			     $("#vendorWorkingHoursDetailsLoaderDiv").removeAttr("style");
							         $("#invalidWorkingMsgDiv").removeAttr("style");
						    		 $("#invalidWorkingMsgDiv").css({ display: "block" });
							         $("#invalidWorkingMsg").html("Well done! You successfully saved vendor working hours.");
							         $("#invalidWorkingMsgDiv").fadeIn(200);
							 	     $('#invalidWorkingMsgDiv').delay(2000).fadeOut('slow');
							 	     loadWrokingHoursDetails();
			                   }
				        	
				        		
				},
		});   
         return true;
	   }else{
			return false;
		  } 
	 
 }
 </script>
 
 		 	 	