<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


<div class="card height-auto">
      <div class="card-body">
          <div class="single-info-details">
                 <div class="vendor_images_img item-img"  id="vendorProfileImage" class="mb-3"></div>
                 <div class="item-content">
                     <div class="header-inline item-header">
                         <h3 class="text-dark-medium font-medium"><span id="vendorName"></span><br/>
                         <span class="view-subtitle" style="font-size: 15px;">
                         <i class="fa fa-map-marker" aria-hidden="true"></i>&nbsp;
                                        <span id="vendorLocation"></span></span><br/>
                                        <span id="directions"></span>
                                        </h3>
                     </div>
                     <p style="color: black;" id="vendorDescription"></p>
                 </div>
             </div>
     </div>
</div>   

  <div class="card height-auto">   
     <div class="card ui-tab-card">
     <div id="serviceslistLoadingDiv"></div>
       <div class="card-body">
	       <div class="icon-tab">
	       		<div id="servicesListDiv"></div>
	       </div>
       </div>
                 
      <div class="ui-btn-wrap">   
       <div class="card-body">
          <div id="categoriesListDiv"></div>
        </div> 
       </div> 
      
       
       <div class="card-body">
       		<div class="service_wrap">
          	<div id="categoryServicesListDiv"></div>
          </div>
       </div> 
       
       <div class="modal-body">
       <div class="modal-box">
       <div class="modal pop-up-modal fade" id="pop-up-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content mt-10">
                                        <div id="vendorDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Terms & Conditions</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                               
                                           <div class="modal-body">
                                            <ul>    
                                               <li><span id="termsAndConditions"></span></li>
                                               </ul>
                                            </div>
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fa fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
       <jsp:include page="buyService.jsp" />
       </div>
    </div>
 </div>
 
 
 <script type="text/javascript">
 
		window.onload = function () {
			getVendorServicesInfo();
		 };
		 
		 var currencyCode = '';
		 
		 function getVendorServicesInfo(){
			   var vendorUUID = '${vendorUUID}';
	    	   var appUrl ='${appUrl}';
	    	   $("#serviceslistLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	    	   $.ajax({
	    			  type: "GET",
	    			    url: appUrl+"/ws/getServicesList?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
	    			     success :function(response) {
	    			    	 var serviceUUID = '';
	    			    	 
	    			    	 $("#vendorProfileImage").html('<img src="'+response.object.profileImage+'" onerror="predefineVendorProfileImage(this);" data-id= "vendorProfileImage" >');
	    			    	 $("#vendorName").html(response.object.vendorName);
	    					 $("#vendorLocation").html(response.object.location);
	    					 if(response.object.latitude != '' && response.object.longitude != ''){
	    						var cordinates =  response.object.latitude+","+response.object.longitude;
	    						var directions = '<a class="view-subtitle" style="font-size: 15px; font-weight: normal;" id="vendorDirections" href="https://www.google.com/maps/place/'+cordinates+'" target="_blank">Get Directions</a>';
	    						$("#directions").append(directions);
	    					 }
	    					 $("#vendorDescription").html(response.object.description);
	    					 
	    					 currencyCode = getCurrency(response.object.currency);
	    					 
		 					if(response.object.servicesList != '' && response.object.servicesList.length > 0){
	    						 
	    						 var result = "";
	    						 
	    						 result = result +'<ul class="nav nav-tabs vendor-cart" role="tablist">';
	    						  for (var i=0; i<response.object.servicesList.length; i++)
	    			   				{ 
	    			        		  var service = response.object.servicesList[i];
	    			        		  var active = '';
	    			        		  if(i == 0){
	    			        			  active = 'active';
	    			        			  serviceUUID = service.serviceUUID;
	    			        		  }
	    			        		  result = result +'<li class="nav-item">';
	    			        		  result = result +'<a class="nav-link border-pastel-gold '+active+'" data-toggle="tab" href="#service"  onclick="getServiceCategories(\''+service.serviceUUID+'\')" role="tab" aria-selected="true" ><img src="'+service.serviceImage+'"></img></a><span><strong>'+service.serviceName+'</strong></span>';
	    			        		  result = result +'</li>';
	    			   				} 
	                               result = result +'</ul>';
	    			        	  
	    			        	  $("#servicesListDiv").empty();  
		    		   	    	  $("#servicesListDiv").append(result);
	    						 
	    					 }
		 					getServiceCategories(serviceUUID);
	    					 $("#serviceslistLoadingDiv").removeAttr("style");
	    					},
	    		});
		 }
</script>


<script>
function getCurrency(currency){
	var currencyType;
	switch (currency) {
	  case "USD":
		currencyType = "&#36";
	    break;
	  case "EUR":
		  currencyType = "&#128";
	    break;
	  case "CRC":
		  currencyType = "&#8353";
	    break;
	  case "GBP":
		  currencyType = "&#163";
	    break;
	  case "ILS":
		  currencyType = "&#8362";
	    break;
	  case "INR":
		  currencyType = "&#x20B9";
	    break;
	  case  "JPY":
		  currencyType = "&#165";
	    break;
	  case  "KRW":
		  currencyType = "&#8361";
		break;
	  case  "NGN":
		  currencyType = "&#8358";
		break;
	  case  "PHP":
		  currencyType = "&#8369";
	    break;
	  case  "PLN":
		  currencyType = "PLN";
	    break;
	  case  "PYG":
		  currencyType = "&#8370";
	    break;
	  case  "THB":
		  currencyType = "&#3647";
	    break;
	  case  "UAH":
		  currencyType = "&#8372";
	    break;
	  case  "VND":
		  currencyType = "&#8363";
	    break;
	  case  "":
		  currencyType = "&#164";
	    break;
	}
	
	return currencyType
}
</script>


<script>
function getServiceCategories(serviceUUID){
	
	   var appUrl ='${appUrl}';
	   $("#serviceslistLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/ws/getServiceCategoriesList?${_csrf.parameterName}=${_csrf.token}&serviceUUID="+serviceUUID,
			     success :function(resultData) {
					 
			    	 if(resultData.data.length > 0){
						 
						 var result = "";
						 var categoryUUID = "";
						 
						 result = result +'<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">';
						 for (var i=0; i<resultData.data.length; i++)
			   				{ 
							  var opt = resultData.data[i];
							  var selected = '';
			        		  result = result +'<div class="btn-group" role="group" style="padding: 2px;">';
			        		  result = result +'<a class="btn btn-outline-primary btn-sm" id="category'+opt.categoryUUID+'" onclick="getCategoryServices(\''+opt.categoryUUID+'\')">'+opt.categoryName+'</a>';
			        		  result = result +'</div>';
			   				} 
                           result = result +'<div>';
			        	  
			        	  $("#categoriesListDiv").empty();  
			        	  $("#categoryServicesListDiv").empty(); 
    		   	    	  $("#categoriesListDiv").append(result);
						 
					 }
					 $("#serviceslistLoadingDiv").removeAttr("style");
					},
		});
}
</script>

<style>
/* .datepicker-inline .datepicker {
width: 250px !important;
} */

.service_wrap_name span{
cursor: pointer;
}
</style>

<script>
function getCategoryServices(categoryUUID){
	 $( '.btn-toolbar .btn-group' ).find( 'a' ).removeClass('selected');
	 $("#category"+categoryUUID).addClass( 'btn btn-outline-primary btn-sm selected' );
	 var vendorUUID = '${vendorUUID}';
	 var appUrl = '${appUrl}';
	 $("#serviceslistLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/ws/getCategoryServicesList?${_csrf.parameterName}=${_csrf.token}&categoryUUID="+categoryUUID+"&vendorUUID="+vendorUUID,
			     success :function(resultData) {
					 
			    	 var result = "";
			    	 var serviceStartDates = [];
			    	 var serviceEndDates = [];
			    	 var serviceUUIDs = [];
			    	 if(resultData.data.length > 0){
						 
						 for (var i=0; i<resultData.data.length; i++)
			   				{ 
							  var opt = resultData.data[i];
							  
							  result = result+'<div class="service_wrap_inn" data-id="4">';
							  result = result+'<div class="service_wrap_top">';
							  result = result+'<img src="/resources/img/logo.png" width="180" height="130">';
							  result = result+'<div class="service_wrap_top_text">Price<br><span>'+currencyCode+' '+opt.offerPrice+'</span></div>';
							  result = result+'</div>';
							  result = result+'<div class="service_wrap_name">';
							  result = result+opt.subCategory;
							  result = result+'<div class="row">';
							  result = result+'<span class="col-xl-6 col-lg-6 col-12">Actual Price :&nbsp;'+currencyCode+' '+opt.actualPrice+'</span>';
							  result = result+'<span class="col-xl-6 col-lg-6 col-12">Offer Price :&nbsp;'+currencyCode+' '+opt.offerPrice+'</span>';
							  result = result+'<span class="col-xl-6 col-lg-6 col-12">Start Date : '+opt.startDate+'</span>';
							  result = result+'<span class="col-xl-6 col-lg-6 col-12">End Date : '+opt.endDate+'</span>';
							  if(opt.service == 'Guest List'){
								  result = result+'<span class="col-xl-6 col-lg-6 col-12">Entry Time : '+opt.guestEntryTime+'</span>';
							  }
							  result = result+'<span class="col-xl-6 col-lg-6 col-12">Qty : <button type="button" onclick="quantityDec(\''+opt.masterServiceUUID+'\')">-</button><input type="number" readonly step="1" min="1" value="1" id="number'+opt.masterServiceUUID+'" style="max-width: 30px;"><button type="button" onclick="quantityInc(\''+opt.masterServiceUUID+'\')">+</button></span>';
							  result = result+'<span class="col-xl-6 col-lg-6 col-12">Allowed : '+opt.allowed+'</span>';
							  result = result+'<span class="col-xl-6 col-lg-6 col-12 text-danger" data-toggle="modal" data-target="#pop-up-modal" onclick="getTermsAndConditions(\''+opt.termsAndConditions+'\')">Terms & Conditions</span>';
							  result = result+'</div>';
							  result = result+'<div class="service_wrap_user_img">';
							  result = result+'<img src="'+opt.serviceImage+'" onerror="predefineVendorServiceImage(this);" >';
							  result = result+'</div>';
							  result = result+'</div>';
							  result = result+'<div class="service_wrap_content">';
							  result = result+'<div class="row">';
							  result = result+'<div class="col-lg-8 col-sm-6 col-12">';
							  result = result+'<div class="event-calendar-box mb-5">';
							  result = result+'<div class="item-content table-responsive">';
							  result = result+'<div id="serviceCalendar'+opt.masterServiceUUID+'"></div>';
							  result = result+'</div>';
							  result = result+'</div>';
							  result = result+'</div>';
							 
							 
							  result = result+'<div class="col-lg-4 col-sm-6 col-12">';
							  result = result+'<div class="heading-layout1 mg-b-17">';
							  result = result+'<div class="item-title">';
							  result = result+'<div id="eventTitleDiv"></div>';
							  result = result+'<h5>Time Slots</h5>';
							  result = result+'<div class="timeslot-box-wrap">';
							  
							  for (var j=0; j<opt.timeSlotList.length; j++){
				        	    	var slots = opt.timeSlotList[j];
				        	    	
				        	    	result = result +"<ul class='table-light mt-2'>";
				        	    	result = result +'<li style="font-size: 10.5px; font-weight: 600"><input type="radio"  value="'+slots.startTime+' to '+slots.endTime+'" onclick="getTimeSlots(\''+opt.masterServiceUUID+'\');" name="timeslot'+opt.masterServiceUUID+'">&nbsp;'+slots.startTime+' to '+slots.endTime+'</li>';
				        	    	result = result +"</ul>";
				        	    	
				        	    }
							  result = result+'<input type="hidden" id="bookedTimeSlot'+opt.masterServiceUUID+'" />';
							  result = result+'</div>';
							  result = result+'</div>';
							  result = result+'</div>';
							  result = result+'</div>';
							  result = result+'</div>';
							  
							  
							  result = result+'<div class="clear"></div>';
							  result = result+'</div>';
							  
							  result = result+'<div class="service_wrap_bot">';
							  
 							  if(opt.service == 'Packages'){
 								 result = result+'<a data-button="customize" data-id="4" href="javascript:void(0)" data-toggle="modal" data-target="#right-slide-modal" onclick="getServiceDetails(\''+opt.masterServiceUUID+'\',\''+categoryUUID+'\')">Customize</a>';
							  }else{
								  result = result+'<a data-button="buyService" data-id="4" href="javascript:void(0)" data-toggle="modal" data-target="#right-slide-modal" onclick="getServiceDetails(\''+opt.masterServiceUUID+'\',\''+categoryUUID+'\')">Buy Now</a>';
							  }
							  
							  result = result+'<div class="clear"></div>';
							  result = result+'</div>';
							  result = result+'</div>';
							  
							  $("#categoryServicesListDiv").empty(); 
			   	    	      $("#categoryServicesListDiv").append(result);
							  
			   	    	   serviceStartDates.push(opt.serviceStartDate);
			   	    	   serviceEndDates.push(opt.serviceEndDate);
			   	    	   serviceUUIDs.push(opt.masterServiceUUID);
							  
			   				} 
						 
					 }else{
						 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/no-records.png" alt=""/></td>';
						 $("#categoryServicesListDiv").empty(); 
		   	    	     $("#categoryServicesListDiv").append(result);
					 }
			    	 
			    	 if(resultData.data.length > 0){
			    		 for (var i=0; i<resultData.data.length; i++){
			    			 getServiceDates(serviceStartDates[i],serviceEndDates[i],serviceUUIDs[i]);
				    	 }
			    	 }
			    	 
	   	    	      function getServiceDates(serviceStartDate, serviceEndDate,serviceUUID){
	   	    	    	var startDateParts = serviceStartDate.split('-');
	   	    	    	var minimumdate = new Date(startDateParts[0], startDateParts[1] - 1, startDateParts[2]);
	   	    	    	var endDateParts = serviceEndDate.split('-');
	   	    	    	var maximumdate = new Date(endDateParts[0], endDateParts[1] - 1, endDateParts[2]);
	   	    	        var dateToday = new Date();
	   	    	        if(minimumdate < dateToday){
	   	    	        	minimumdate = dateToday;
	   	    	        }
		   	    	    $('#serviceCalendar'+serviceUUID).datepicker({
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
		   	    	        height: '50px',
		   	    	        width: '50px'
		   	    	      },
		   	    	      minDate: minimumdate,
		   	    	      maxDate: maximumdate
		   	    	    });
	   	    	      }
	   	    	      
					 $("#serviceslistLoadingDiv").removeAttr("style");
					},
		});
	 
	 
	 
}
</script>


<script>
function getTermsAndConditions(termsAndConditions){
	$("#termsAndConditions").empty();
	$("#termsAndConditions").html(termsAndConditions);
}

function quantityInc(maserServiceUUID){
	var quantity = document.getElementById("number"+maserServiceUUID);
	quantity.stepUp(1); 
}

function quantityDec(maserServiceUUID){
	var quantity = document.getElementById("number"+maserServiceUUID);
	quantity.stepDown(1); 
}
</script>
