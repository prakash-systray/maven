<div class="tab-pane fade show active" id="service" role="tabpanel">
		<div class="modal-body">
		    <div class="table-responsive">
                   <div id="vendorServicesList"></div>
            </div>
		</div>
     <jsp:include page="vendorServiceDetails.jsp" />
</div>

<script>
   function getVendorServicesList(serviceUUID, serviceType){
	   
	    var appUrl ='${appUrl}';
	    var vendorUUID = '${vendorUUID}';
	   
	   $("#vendorServicesLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
		    	type: "GET",
		    	 url: appUrl+"/getVendorServicesList?${_csrf.parameterName}=${_csrf.token}&serviceUUID="+serviceUUID+"&vendorUUID="+vendorUUID, 
		        success: function(resultData) {
		   			
		        	var result = "";
		        	result = result +"<table class='table display data-table text-nowrap' id='vendorServicesPagination'>";
		        	result = result +"<thead>";
		        	result = result +"<tr>";
		        	result = result +"<th>Image</th>";
		        	result = result +"<th>Service</th>";
		        	result = result +"<th>Category</th>";
		        	if(serviceType == 'Book a bottle'){
		        		result = result +"<th>Bottle Name</th>";
		        	}else if(serviceType == 'Schedule a table'){
		        		result = result +"<th>Table Name</th>";
		        	}else if(serviceType == 'Entry' || serviceType == 'Guest List'){
		        		result = result +"<th>Event Name</th>";
		        	}else if(serviceType == 'Packages'){
		        		result = result +"<th>Package Name</th>";
		        	}
		        	result = result +"<th>Start Date</th>";
		        	result = result +"<th>End Date</th>";
		        	result = result +"<th>Status</th>";
		        	result = result +"</tr>";
		        	result = result +"</thead>";
                    
		        	result = result +"<tbody>";
		        	
		        	if(resultData.data.length > 0){
			        	for (var i=0; i<resultData.data.length; i++)
		   				{  
			        		var opt = resultData.data[i];
			        		
			        		result = result +"<tr>";
			        		
							result = result +"<td>";
			        		result = result +'<div class="media">';
	  		    			result = result +'<div class="mediaimagezoo">';
	  		    			result = result +'<img src="'+opt.serviceImage+'" onerror="predefineVendorServiceImage(this);" alt="image" class="img-fluid rounded mr-3" style="width:75px;">';
	  		    			result = result +'</div>';
			        		result = result +"</td>";
			        		
			        		result = result +"<td>"+opt.service+"</td>";
			        		result = result +"<td>"+opt.category+"</td>";
			        		result = result +"<td>"+opt.subCategory+"</td>";
			        		result = result +"<td>"+opt.serviceStartDate+"</td>";
			        		result = result +"<td>"+opt.serviceEndDate+"</td>";
			        		
			        		result = result +"<td class='width100'>";
			        		result = result +"<div class='header-inline item-header'>";
			        		result = result +"<div class='header-elements'>";
			        		result = result +"<ul>";
			        		result = result +'<li><a href="#" data-toggle="modal" data-target="#right-slide-modal" onclick="getVendorServiceDetails(\''+opt.masterServiceUUID+'\')"><i class="fas fa-eye"></i></a>';
			        		if(serviceType == 'Book a bottle'){
			        			result = result +'&nbsp;&nbsp;<a href="'+appUrl+'/bottle?vendorUUID='+vendorUUID+'&serviceUUID='+serviceUUID+'&bottleUUID='+opt.masterServiceUUID+'" ><i class="fa fa-edit" title="Services"></i></a></li>';
			        		}else if(serviceType == 'Schedule a table'){
			        			result = result +'&nbsp;&nbsp;<a href="'+appUrl+'/table?vendorUUID='+vendorUUID+'&serviceUUID='+serviceUUID+'&tableUUID='+opt.masterServiceUUID+'" ><i class="fa fa-edit" title="Services"></i></a></li>';
			        		}else if(serviceType == 'Entry'){
			        			result = result +'&nbsp;&nbsp;<a href="'+appUrl+'/entry?vendorUUID='+vendorUUID+'&serviceUUID='+serviceUUID+'&entryUUID='+opt.masterServiceUUID+'" ><i class="fa fa-edit" title="Services"></i></a></li>';
			        		}else if(serviceType == 'Guest List'){
			        			result = result +'&nbsp;&nbsp;<a href="'+appUrl+'/guest?vendorUUID='+vendorUUID+'&serviceUUID='+serviceUUID+'&guestUUID='+opt.masterServiceUUID+'" ><i class="fa fa-edit" title="Services"></i></a></li>';
			        		}else if(serviceType == 'Packages'){
			        			result = result +'&nbsp;&nbsp;<a href="'+appUrl+'/packages?vendorUUID='+vendorUUID+'&serviceUUID='+serviceUUID+'&packageUUID='+opt.masterServiceUUID+'" ><i class="fa fa-edit" title="Services"></i></a></li>';
			        		}
			        		else{
			        			result = result +'&nbsp;&nbsp;<a href="#" ><i class="fa fa-edit" title="Services"></i></a></li>';
			        		}
			        		
			        		
			        		result = result +"</ul>";
			        		result = result +"</div>";
			        		result = result +"</div>";
	                        result = result +"</td>";
	                        result = result +"</tr>";
		   				}
		        	}else{
		        		result = result +"<tr>";
	   					result = result+'<td colspan="6" class="noRecords-dashboard-portlets"><img src="/resources/img/no-records.png" alt=""/></td>';
	   					result = result +"</tr>";
	   				}
		        	
	
		        	
		        	result = result +"</tbody>";
		        	result = result +"</table>";
                    
		        	 $("#vendorServicesLoaderDiv").removeAttr("style");
		        	 $("#vendorServicesList").empty();  
	   	    	     $("#vendorServicesList").append(result);
	   	    	    vendorServicesPagination();
		        },
		    }); 
	   
   }
   </script>
   
   <script>
function vendorServicesPagination(){ 
	$('#vendorServicesPagination').DataTable( { 
    	"scrollCollapse": false,
    	"bLengthChange": true,
    	"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
    	"bDestroy": true,
    	"ordering": false,
    	"oLanguage": {
     		"sSearch": "",
     		"sSearchPlaceholder":"Search Service",
     		 "sZeroRecords": "", "sEmptyTable": ""
     	}
    } );
}
</script> 
 
   