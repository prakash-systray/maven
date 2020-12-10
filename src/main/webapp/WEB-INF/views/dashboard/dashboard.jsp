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
                    <h3>${Wayuparty_loginUserRoleDisplayName} Dashboard</h3>
                </div>
                
                <div class="card height-auto">
                    <div class="card-body">
                    <div id="registeredVendorsLoadingDiv"></div>
                        <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Registered Vendors</h3>
                            </div>
                           <div class="dropdown">
                               <a class="text-black" href="${Wayuparty_appUrl}/vendor"><i class="fas fa-plus text-dark-pastel-green"></i>&nbsp;Add Vendor</a>
                            </div> 
                        </div>
                        
                           <div class="table-responsive">
                            	<div id="registeredVendorsList"></div>
                           </div>
                           
                      </div>
                   </div>
                   <jsp:include page="vendorDetails.jsp" />
            </div>
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script type="text/javascript">
		window.onload = function () {
			getVendorsList();
		 };
 </script>
 
 <script>
   function getVendorsList(){
	   
	    var appUrl ='${appUrl}';
	   
	   $("#registeredVendorsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
		    	type: "GET",
		    	 url: appUrl+"/getAllregisteredVendorsList?${_csrf.parameterName}=${_csrf.token}", 
		        success: function(resultData) {
		   			
		        	var result = "";
		        	result = result +"<table class='table display data-table text-nowrap' id='vendorPagination'>";
		        	result = result +"<thead>";
		        	result = result +"<tr>";
		        	result = result +"<th>Photo</th>";
		        	result = result +"<th>Vendor Name</th>";
		        	result = result +"<th>Vendor Email</th>";
		        	result = result +"<th>Vendor Mobile</th>";
		        	result = result +"<th>Actions</th>";
		        	result = result +"</tr>";
		        	result = result +"</thead>";
                    
		        	result = result +"<tbody>";
		        	
		        	if(resultData.data.length > 0){
			        	for (var i=0; i<resultData.data.length; i++)
		   				{  
			        		var opt = resultData.data[i];
			        		
			        		result = result +"<tr>";
			        		result = result +"<td class='width80'>";
			        		
			        		result = result +'<div class="media">';
	  		    			result = result +'<div class="mediaimagezoo">';
	  		    			result = result +'<img src="'+opt.vendorProfileImg+'" onerror="predefineVendorProfileImage(this);" alt="image" class="img-fluid rounded mr-3" style="width:75px;">';
	  		    			result = result +'</div>';
	  		    			result = result +'</div>';
			        		
			        		result = result +"</td>";
			        		result = result +"<td>"+opt.vendorName+"</td>";
			        		result = result +"<td>"+opt.vendorEmail+"</td>";
			        		result = result +"<td>"+opt.vendorMobile+"</td>";
			        		
			        		result = result +"<td class='width100'>";
			        		result = result +"<div class='header-inline item-header'>";
			        		result = result +"<div class='header-elements'>";
			        		result = result +"<ul>";
			        		result = result +'<li><a href="#" data-toggle="modal" data-target="#right-slide-modal" onclick="getVendorDetails(\''+opt.vendorUUID+'\')"><i class="fas fa-eye"></i></a>';
			        		'<sec:authorize access="hasRole(\'ROLE_SUPER_ADMIN\')">'
			        			result = result +'&nbsp;&nbsp;<a href="'+appUrl+'/vendorProfile?vendorUUID='+opt.vendorUUID+'" ><i class="fa fa-search-plus" title="Explore"></i></a>';
			        			result = result +'&nbsp;&nbsp;<a href="#" ><i class="fa fa-bullhorn" title="Services"></i></a></li>';
			        		'</sec:authorize>';
			        		result = result +"</ul>";
			        		result = result +"</div>";
			        		result = result +"</div>";
	                        result = result +"</td>";
	                        result = result +"</tr>";
		   				}
		        	}else{
		        		result = result +"<tr>";
	   					result = result+'<td colspan="5" class="noRecords-dashboard-portlets"><img src="/resources/img/no-records.png" alt=""/></td>';
	   					result = result +"</tr>";
	   				}
		        	
	
		        	
		        	result = result +"</tbody>";
		        	result = result +"</table>";
                    
		        	 $("#registeredVendorsLoadingDiv").removeAttr("style");
		        	 $("#registeredVendorsList").empty();  
	   	    	     $("#registeredVendorsList").append(result);
	   	    	     vendorPagination();
		        },
		    }); 
	   
   }
   </script>
   
   <script>
function vendorPagination(){ 
	$('#vendorPagination').DataTable( { 
    	"scrollCollapse": false,
    	"bLengthChange": true,
    	"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
    	"bDestroy": true,
    	"ordering": false,
    	"oLanguage": {
     		"sSearch": "",
     		"sSearchPlaceholder":"Search Vendor",
     		 "sZeroRecords": "", "sEmptyTable": ""
     	}
    } );
}
</script> 
 
                