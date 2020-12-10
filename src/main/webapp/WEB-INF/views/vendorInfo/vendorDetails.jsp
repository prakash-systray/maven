<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <div class="card height-auto">
      <div class="card-body">
          <div class="single-info-details">
                 <div class="vendor_images_img item-img" id="carouselSlideDiv">
                  <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <ul class="carousel-indicators">
                      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                      <li data-target="#myCarousel" data-slide-to="1"></li>
                      <li data-target="#myCarousel" data-slide-to="2"></li>
                      <li data-target="#myCarousel" data-slide-to="3"></li>
                    </ul>
                    <div class="carousel-inner" id="sliderImagesDiv">
                      
                    </div>
                  </div>
                 </div>
                 <div class="vendor_images_img item-img"  style="display: none" id="vendorProfileImage" class="mb-3"></div>
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
                    <div class="card-body">
                    <div id="vendorDetailsLoaderDiv"></div>
                        <div class="heading-layout1 mg-b-25">
                           <div class="custom-tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#basic" role="tab"  aria-selected="true">Basic</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#category"  role="tab" aria-selected="false">Category</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#menu" role="tab" aria-selected="false">Menu</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#facilities" role="tab" aria-selected="false" >Facilities</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#music" role="tab" aria-selected="false" >Music Genre</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#cuisine" role="tab" aria-selected="false" >Cuisine</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#workinghours" role="tab" aria-selected="false" >Working Hours</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#termsandcondidtions" role="tab" aria-selected="false" >T&C</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#gallery" role="tab" aria-selected="false" >Gallery</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#ratings" role="tab" aria-selected="false" >Ratings</a>
                                </li>
                            </ul>
                            
                            <div class="tab-content">
                                     <jsp:include page="vendorBasicInfo.jsp" />
                                     <jsp:include page="vendorCategoriesInfo.jsp" />
                                     <jsp:include page="vendorMenuInfo.jsp" />
                                     <jsp:include page="vendorFacilitiesInfo.jsp" />
                                     <jsp:include page="vendorMusicGenreInfo.jsp" />
                                     <jsp:include page="vendorCuisineInfo.jsp" />
                                     <jsp:include page="vendorWorkingHoursInfo.jsp" />
                                     <jsp:include page="vendorTermsAndConditionsInfo.jsp" />
                                     <jsp:include page="vendorGalleryInfo.jsp" />
                                
                            </div>
                        </div>
                    </div>
                  </div>
                </div>
</div>

<script type="text/javascript">
		window.onload = function () {
			getRestaurantInfo();
		 };
		 
		 
		 function getRestaurantInfo(){
			   var vendorUUID = '${vendorUUID}';
	    	   var appUrl ='${appUrl}';
	    	   $("#vendorInfoLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	    	   $.ajax({
	    			  type: "GET",
	    			    url: appUrl+"/ws/getVendorInfo?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
	    			     success :function(response) {
	    			    	 
	    			    	 $("#vendorName").html(response.object.vendorName);
	    					 $("#vendorLocation").html(response.object.location);
	    					 if(response.object.latitude != '' && response.object.longitude != ''){
	    						var cordinates =  response.object.latitude+","+response.object.longitude;
	    						var directions = '<a class="view-subtitle" style="font-size: 15px; font-weight: normal;" id="vendorDirections" href="https://www.google.com/maps/place/'+cordinates+'" target="_blank">Get Directions</a>';
	    						$("#directions").append(directions);
	    					 }
	    					 $("#vendorDescription").html(response.object.vendorDescription);
	    					 
	    					 
	    					 if(response.object.sliderList != '' && response.object.sliderList.length > 0){
	    						 
	    						 var result = "";
	    			        	  for (var i=0; i<response.object.sliderList.length; i++)
	    			   				{ 
	    			        		  var sliderImage = response.object.sliderList[i];
	    			        		  var active = '';
	    			        		  if(i == 0){
	    			        			  active = 'active';
	    			        		  }
	    			        		  result = result +'<div class="carousel-item '+active+' ">';
	    			        		  result = result +'<img src="'+sliderImage+'">';
	    			        		  result = result +'</div>';
	    			   				}
	    			        	  
	    			        	  $("#sliderImagesDiv").empty();  
		    		   	    	  $("#sliderImagesDiv").append(result);
	    						 
	    					 }else{
	    						 document.getElementById('carouselSlideDiv').style.display='none'
	    					     document.getElementById('vendorProfileImage').style.display='block'
	    					     $("#vendorProfileImage").html('<img src="'+response.object.vendorProfileImg+'" onerror="predefineVendorProfileImage(this);" data-id= "vendorProfileImage" >');
	    					 }
	    					 
	    					 $("#costForTwo").html(response.object.costForTwoPeople);
	    					 $("#vendorCapacity").html(response.object.vendorCapacity);
	    					 $("#establishedYear").html(response.object.establishedYear);
	    					 $("#bestSellingItems").html(response.object.bestSellingItems);
	    					 $("#emailId").html(response.object.vendorEmail);
	    					 
	    					 if(response.object.termsAndConditions != ''){
	    						 $("#termsandcondidtionsDiv").html(response.object.termsAndConditions);
	    					 }else{
	    						 $("#termsandcondidtions").append('<div class="noRecords"><img src="/resources/img/no-records.png" alt=""/></div>');
	    					 }
	    					 
	    					 if(response.object.categoriesList != '' && response.object.categoriesList.length > 0){
	    						 
	    						 var result = "";
	    			        	  for (var i=0; i<response.object.categoriesList.length; i++)
	    			   				{ 
	    			        		  var category = response.object.categoriesList[i];
	    			        		  result = result +'<ul>';
	    			        		  result = result +'<li class="mt-2"><strong>'+category+'</strong><li>';
	    			        		  result = result +'</ul>';
	    			   				}
	    			        	  
	    			        	  $("#vendorsCategoriesDiv").empty();  
		    		   	    	  $("#vendorsCategoriesDiv").append(result);
	    					 }else{
	    						  $("#vendorsCategoriesDiv").empty();  
		    		   	    	  $("#vendorsCategoriesDiv").append('<div class="noRecords"><img src="/resources/img/no-records.png" alt=""/></div>');
	    					 }
	    					 
	    					 
			 				if(response.object.menuList != '' && response.object.menuList.length > 0){
	    						 var result = "";
	    			        	  for (var i=0; i<response.object.menuList.length; i++)
	    			   				{ 
	    			        		  var menu = response.object.menuList[i];
	    			        		     result = result +'<div class="col-md-3 mb-2">';
	    					    		 result = result +'<div class="video-image">';
	    					    		 result = result +'<img src="'+menu+'" >';
	    					    		 result = result +'</div>';
	    					    		 result = result +'</div>';
	    			   				}
	    			        	  
	    			        	  $("#vendorsMenuInfoDiv").empty();  
		    		   	    	  $("#vendorsMenuInfoDiv").append(result);
	    					 }else{
	    						  $("#vendorsMenuInfoDiv").empty();  
		    		   	    	  $("#vendorsMenuInfoDiv").append('<div class="noRecords"><img src="/resources/img/no-records.png" alt=""/></div>');
	    					 }
			 				
			 				
 							if(response.object.facilitiesList != '' && response.object.facilitiesList.length > 0){
	    						 var result = "";
	    			        	  for (var i=0; i<response.object.facilitiesList.length; i++)
	    			   				{ 
	    			        		  var facility = response.object.facilitiesList[i];
	    			        		  result = result +'<ul>';
	    			        		  result = result +'<li class="mt-2"><strong>'+facility+'</strong><li>';
	    			        		  result = result +'</ul>';
	    			   				}
	    			        	  
	    			        	  $("#vendorsFacilitiesDiv").empty();  
		    		   	    	  $("#vendorsFacilitiesDiv").append(result);
	    					 }else{
	    						  $("#vendorsFacilitiesDiv").empty();  
		    		   	    	  $("#vendorsFacilitiesDiv").append('<div class="noRecords"><img src="/resources/img/no-records.png" alt=""/></div>');
	    					 }
 							
 							if(response.object.musicList != '' && response.object.musicList.length > 0){
	    						 var result = "";
	    			        	  for (var i=0; i<response.object.musicList.length; i++)
	    			   				{ 
	    			        		  var music = response.object.musicList[i];
	    			        		  result = result +'<ul>';
	    			        		  result = result +'<li class="mt-2"><strong>'+music+'</strong><li>';
	    			        		  result = result +'</ul>';
	    			   				}
	    			        	  
	    			        	  $("#vendorsMusicGenreDiv").empty();  
		    		   	    	  $("#vendorsMusicGenreDiv").append(result);
	    					 }else{
	    						  $("#vendorsMusicGenreDiv").empty();  
		    		   	    	  $("#vendorsMusicGenreDiv").append('<div class="noRecords"><img src="/resources/img/no-records.png" alt=""/></div>');
	    					 }
 							
 							if(response.object.cuisineList != '' && response.object.cuisineList.length > 0){
	    						 var result = "";
	    			        	  for (var i=0; i<response.object.cuisineList.length; i++)
	    			   				{ 
	    			        		  var cuisine = response.object.cuisineList[i];
	    			        		  result = result +'<ul>';
	    			        		  result = result +'<li class="mt-2"><strong>'+cuisine+'</strong><li>';
	    			        		  result = result +'</ul>';
	    			   				}
	    			        	  
	    			        	  $("#vendorCuisineDiv").empty();  
		    		   	    	  $("#vendorCuisineDiv").append(result);
	    					 }else{
	    						  $("#vendorCuisineDiv").empty();  
		    		   	    	  $("#vendorCuisineDiv").append('<div class="noRecords"><img src="/resources/img/no-records.png" alt=""/></div>');
	    					 }
 							
 							if(response.object.workingHoursList != '' && response.object.workingHoursList.length > 0){
	    						    var result = "";
	    						    result = result +"<div class='container'>";      
	    				        	result = result +"<table border='1' cellpadding='4' style='border-collapse: collapse;' class='table table-bordered table-custom table-hover mt-5'>";
	    				        	result = result +"<thead class='thead-dark'>";
	    				        	result = result +"<tr>";
	    				        	result = result +"<th>Day</th>";
	    				        	result = result +"<th>Start Time</th>";
	    				        	result = result +"<th>End Time</th>";
	    				        	result = result +"</tr>";
	    				        	result = result +"</thead>";
	    				        	result = result +"<tbody>";
	    				        	  for (var i=0; i<response.object.workingHoursList.length; i++){
	    				        	    	var opt = response.object.workingHoursList[i];
	    				        	    	
	    				        	    	result = result +"<tr class='table-light'>";
	    				        	    	result = result +"<th class='width100'>"+opt.workingDay+"</th>";
	    				        	    	result = result +"<td class='width100'>"+opt.startTime+"</td>";
	    				        	    	result = result +"<td class='width100'>"+opt.endTime+"</td>";
	    				        	    	result = result +"</tr>";
	    				        	    	
	    				        	    }

	    			       			result = result +"</tbody>";
	    			       			result = result +"</table>";
	    			       			result = result +"</div>";
	    			        	  
	    			        	  $("#vendorsWorkingHoursDiv").empty();  
		    		   	    	  $("#vendorsWorkingHoursDiv").append(result);
	    					 }else{
	    						  $("#vendorsWorkingHoursDiv").empty();  
		    		   	    	  $("#vendorsWorkingHoursDiv").append('<div class="noRecords"><img src="/resources/img/no-records.png" alt=""/></div>');
	    					 }
 							
 							
 							if((response.object.galleryList != '' && response.object.galleryList.length > 0) || 
 									(response.object.videoList != '' && response.object.videoList.length > 0)){
 								
 								
 								if(response.object.galleryList != '' && response.object.galleryList.length > 0){
 		    						 var result = "";
 		    			        	  for (var i=0; i<response.object.galleryList.length; i++)
 		    			   				{ 
 		    			        		  var gallery = response.object.galleryList[i];
 		    			        		     result = result +'<div class="col-md-3 mb-2">';
 		    					    		 result = result +'<div class="video-image">';
 		    					    		 result = result +'<img src="'+gallery+'" >';
 		    					    		 result = result +'</div>';
 		    					    		 result = result +'</div>';
 		    			   				}
 		    			        	  
 		    			        	  $("#vendorsGalleryInfoDiv").empty();  
 			    		   	    	  $("#vendorsGalleryInfoDiv").append(result);
 		    					 }
 								
 								if(response.object.videoList != '' && response.object.videoList.length > 0){
		    						 var result = "";
		    			        	  for (var i=0; i<response.object.videoList.length; i++)
		    			   				{ 
		    			        		  var video = response.object.videoList[i];
		    			        		     result = result +'<div class="col-md-3 mb-2">';
		    			        		     result = result +'<video width="100%" height="150" controls> ';
		    					  		     result = result +'<source src="'+video+'" >';
		    					  		     result = result +'</video>';
		    					    		 result = result +'</div>';
		    			   				}
		    			        	  
		    			        	  $("#vendorsVideosInfoDiv").empty();  
			    		   	    	  $("#vendorsVideosInfoDiv").append(result);
		    					 }
 								
 								
 								
 							}else{
 								$("#vendorsGalleryInfoDiv").empty();  
		    		   	    	$("#vendorsGalleryInfoDiv").append('<div class="noRecords"><img src="/resources/img/no-records.png" alt=""/></div>');
 							}
	    					 
	    					 $("#vendorInfoLoadingDiv").removeAttr("style");
	    					 
	    					},
	    		});
			 
			 
		 }
		 
</script>

                        