<div class="ui-modal-box">
  <div class="modal-box">
       <div class="modal right-slide-modal fade" id="right-slide-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                        <div id="serviceDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Service Details</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                           
                                           <div class="modal-body service-info">
                                           <!-- <div class="service_details_images_img item-img" id="serviceImage"></div>  -->
                                            <ul class="row">    
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Service  : </strong><span id="serviceName"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Actual Price	: </strong><span id="actualPriceCurrency"></span>&nbsp;<span id="actualPrice"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Offer Price  : </strong><span id="offerPriceCurrency"></span>&nbsp;<span id="offerPrice"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Order Date  : </strong><span id="orderDate"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Time Slot  : </strong><span id="timeslot"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12" style="display: none;" id="guestEntryTimeDiv"><strong>Guest Entry Time  : </strong><span id="guestEntryTime"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Allowed  : </strong><span id="allowed"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Quantity  : </strong><span id="quantity"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Total Amount  : </strong><span id="totalPriceCurrency"></span>&nbsp;<span id="totalAmount"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12" style="display: none;" id="eventLocationDiv"><strong>Event Location  : </strong><span id="eventLocation"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12" style="display: none;" id="musicGenreDiv"><strong>Music Genre  : </strong><span id="musicGenre"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12" style="display: none;" id="artistDiv"><strong>Artist  : </strong><span id="artist"></span></li>
                                               <li class="col-xl-12 col-lg-6 col-12"><strong>Description  : </strong><span id="description"></span></li>
                                             </ul>
                                 
                                            <div id="packagesMenuList"></div>          
                                             
                                            </div>
                                            
								            <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
								                                   <i class="far fa-hand-point-right bg-light-green3"></i>
								                                  <span id="successMsg" style="color: white;"></span> 
								            </div>
                                            
								            <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
								                                   <i class="fa fa-exclamation-triangle"></i>
								                                  <span id="invalidMsg" style="color: white;"></span> 
								            </div>
                                            
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn text-light gradient-orange-peel" id="addToCart"><i class="fa fa-shopping-cart mg-l-10"></i>&nbsp;Add To Cart</button>
                                               <!-- <button type="button" class="footer-btn text-light gradient-pastel-green" id="placeOrder"><i class="fa fa-save mg-l-10"></i>&nbsp;Place Order</button> -->
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fa fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
       </div>
       <button type="button" style="display:none" class="modal-trigger" id="signup" data-toggle="modal" data-target="#sign-up"></button>
       <jsp:include page="cartUserLogin.jsp" />
   <script>
   
   function getSelectedDateFromCalendar(selectedDate) {
		var date = selectedDate;
		return date;
	}
   
   </script>    
   
   
   <script>
       function getServiceDetails(serviceUUID,categoryUUID){
    	   $("#orderDate").html('');
	       $("#timeslot").html('');
    	   var orderDate = getSelectedDateFromCalendar($("#serviceCalendar"+serviceUUID).val());
    	   var timeslot = $("input[name='timeslot"+serviceUUID+"']:checked").val();
    	   $("#actualPriceCurrency").html(currencyCode);
    	   $("#offerPriceCurrency").html(currencyCode);
    	   $("#totalPriceCurrency").html(currencyCode);
    	   var appUrl ='${appUrl}';
    	   $("#serviceDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	   $.ajax({
    			  type: "GET",
    			    url: appUrl+"/ws/getVendorServiceDetails?${_csrf.parameterName}=${_csrf.token}&serviceUUID="+serviceUUID,
    			     success :function(response) {
    			    	    
    			    	 $("#serviceImage").html('<img src="'+response.object.serviceImage+'" onerror="predefineVendorServiceImage(this);" data-id= "vendorProfileImage" >');
    			    	 $("#serviceName").html(response.object.service);
    			    	 $("#serviceCategory").html(response.object.category);
    			    	 $("#serviceName").html(response.object.subCategory);
    			    	 $("#actualPrice").html(response.object.actualPrice);
    			    	 $("#offerPrice").html(response.object.offerPrice);
    			    	 $("#allowed").html(response.object.allowed);
    			    	 $("#orderDate").html(orderDate);
    			    	 $("#timeslot").html(timeslot);
    			    	 
    			    	 if(response.object.eventLocation != '' && response.object.musicGenre != '' && response.object.artist != ''){
    			    		  document.getElementById('eventLocationDiv').style.display='block'
    				    	  $("#eventLocation").html(response.object.eventLocation);
    			    		  
    			    		  document.getElementById('musicGenreDiv').style.display='block'
        				      $("#musicGenre").html(response.object.musicGenre);
    			    		  
    			    		  document.getElementById('artistDiv').style.display='block'
        				      $("#artist").html(response.object.artist);
    			    	 }else{
    			    		 document.getElementById('eventLocationDiv').style.display='none'
       				    	  $("#eventLocation").html('');
    			    		 
    			    		  document.getElementById('musicGenreDiv').style.display='none'
            				  $("#musicGenre").html('');
        			    		  
       			    		  document.getElementById('artistDiv').style.display='none'
           				      $("#artist").html('');
    			    	 }
    			    	 
    			    	 if(response.object.guestEntryTime != ''){
    			    		 document.getElementById('guestEntryTimeDiv').style.display='block'
       				    	 $("#guestEntryTime").html(response.object.guestEntryTime); 
    			    	 }else{
    			    		 document.getElementById('guestEntryTimeDiv').style.display='none'
       				    	  $("#guestEntryTime").html(response.object.guestEntryTime);
    			    	 }
    			    	 
    			    	 $("#description").html(response.object.description);
    			    	 var quantity = $("#number"+serviceUUID).val();
    			    	 var totalAmount =  Number(response.object.offerPrice) * Number(quantity);
    			    	 $("#quantity").html(quantity);
    			    	 $("#totalAmount").html(totalAmount);
    			    	 
    			    	 if(response.object.packageMenuItems != '' && response.object.packageMenuItems.length > 0){
    			    		 
    			    		 var result = '';
    			    		 result = result +'<div class="border-nav-tab mt-5">';
    			    		 result = result +'<ul class="nav nav-tabs" role="tablist">';
	    			    	   for (var i=0; i< response.object.packageMenuItems.length; i++)
	 	  	        	    	{
	    			    		   var menu = response.object.packageMenuItems[i];
	    			    		   var active = '';
	    			    		   if(i == 0){
			                    		 active = 'active';
			                    	 }
	    			    		   result = result +'<li class="nav-item offering-menu-list">';
	    			    		   result = result +'<a class="nav-link '+active+'" data-toggle="tab" href="#'+menu.menuItemUUID+'" role="tab" aria-selected="true">'+menu.menuItem+'</a>';
	    			    		   result = result +'</li>';
	 	  	        	    	}
		                     result = result +'</ul>';
		                     
		                     result = result +'<div class="tab-content">';
		                     for (var i=0; i< response.object.packageMenuItems.length; i++)
	 	  	        	    	{
		                    	 var menu = response.object.packageMenuItems[i];
		                    	 var active = '';
		                    	 if(i == 0){
		                    		 active = 'show active';
		                    	 }
		                    	 result = result +'<div class="tab-pane fade '+active+'" id="'+menu.menuItemUUID+'" role="tabpanel">';
		                    	 result = result +'<input type="hidden" id="offeringCount'+menu.menuItemUUID+'" value="'+menu.itemsOffered+'">';
		                    	 result = result +'<input type="hidden" id="selectedCount'+menu.menuItemUUID+'" value="0">';
		                    	 result = result +'<div class="mt-3 text-success">Select any '+menu.itemsOffered+' items</div>';
		                    	 result = result +"<ul class='row mt-3'>";
	    			        	  for (var k=0; k<menu.menuItemsList.length; k++)
	    			   				{ 
	    			        		  var menuItems = menu.menuItemsList[k];
	    			        		  result = result +'<li class="col-xl-6 col-lg-6 col-12 mt-2"><input  value="'+menuItems.itemUUID+'" name="menuItemsList[]" type="checkbox" onclick="selectMenuItem(\''+menuItems.itemUUID+'\',\''+menu.menuItemUUID+'\')" id="menuItem'+menuItems.itemUUID+'">&nbsp;&nbsp;<strong>'+menuItems.itemName+'</strong></input></li>';
	    			   				}
   			        			result = result +"</ul>";
                                result = result +'</div>';
	 	  	        	    	}
		                     result = result +'</div>';
	    			    	 result = result +'</div>';
	    			    	 
	    			    	 $("#packagesMenuList").empty();
	    			    	 $("#packagesMenuList").append(result);
    			    		 
    			    	 }else{
    			    		 $("#packagesMenuList").empty();
    			    	 }
    			    	 
    			    	 var addToCartElement = document.getElementById('addToCart');
    			    	 addToCartElement.onclick = function() {
    			    		 addToCart(serviceUUID,categoryUUID,response.object.serviceId,response.object.vendorId,response.object.offerPrice,quantity,totalAmount,response.object.currency)
    			    	 }
    			    	 
    			    	/*  var placeOrderElement = document.getElementById('placeOrder');
    			    	 placeOrderElement.onclick = function() {
    			    		 placeOrder(serviceUUID)
    			    	 } */
    			    	 
    			    	 $("#serviceDetailsLoaderDiv").removeAttr("style");
    					},
    		});
       }
       
       function selectMenuItem(itemUUID, menuUUID){
    	   var offeringItems = $("#offeringCount"+menuUUID).val();
    	   var selectedItems = $("#selectedCount"+menuUUID).val();
    	   
    	   if($("#menuItem"+itemUUID).is(':checked')){
    		   $("#selectedCount"+menuUUID).val(parseInt(selectedItems)+1);
    		   if($("#selectedCount"+menuUUID).val() > offeringItems){
    			   $("#menuItem"+itemUUID).prop('checked', false);
    			   var itemsCount = $("#selectedCount"+menuUUID).val();
    			   $("#selectedCount"+menuUUID).val(parseInt(itemsCount)-1);
    		   }
    	   }else{
    		   $("#selectedCount"+menuUUID).val(parseInt(selectedItems)-1);
    	   }
       }
       
       function getTimeSlots(serviceUUID){
    	   var timeslot = $("input[name='timeslot"+serviceUUID+"']:checked").val();
    	   $("#bookedTimeSlot"+serviceUUID).val(timeslot)
    	   
       }
       
       function placeOrder(serviceUUID){
    	   var orderDate = getSelectedDateFromCalendar($("#serviceCalendar"+serviceUUID).val());
    	   var timeslot = $("#bookedTimeSlot"+serviceUUID).val();
    	   
    	   if(orderDate == '' || timeslot == ''){
    		     $("#invalidMsgDiv").removeAttr("style");
	    		 $("#invalidMsgDiv").css({ display: "block" });
		         $("#invalidMsg").html("Attention! Please choose order date and time slot.");
		         $("#invalidMsgDiv").fadeIn(500);
		 	     $('#invalidMsgDiv').delay(1000).fadeOut('slow'); 
    	   }
       }
       
       let cartMap;
       
       function addToCart(serviceUUID,categoryUUID,vendorMasterServiceId,vendorId,offerPrice,quantity,totalAmount,currency){
    	   
    	   var orderDate = getSelectedDateFromCalendar($("#serviceCalendar"+serviceUUID).val());
    	   var timeslot = $("#bookedTimeSlot"+serviceUUID).val();
    	   
    	   if(orderDate == '' || timeslot == ''){
    		     $("#invalidMsgDiv").removeAttr("style");
	    		 $("#invalidMsgDiv").css({ display: "block" });
		         $("#invalidMsg").html("Attention! Please choose order date and time slot.");
		         $("#invalidMsgDiv").fadeIn(500);
		 	     $('#invalidMsgDiv').delay(1000).fadeOut('slow'); 
    	   }else{
    		   
    		   $("#serviceDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
               var appUrl = "${appUrl}";
               var formData = new FormData();
               
	             var packageMenuItems = [];
	  	   		 $("input[name='menuItemsList[]']:checked").each( function (i) {
	  	   			packageMenuItems[i] = $(this).val();
	  	   		 });
	  	   		 
  	   		 
   			   formData.append("serviceId", vendorMasterServiceId);
   			   formData.append("vendorId", vendorId);
   			   formData.append("serviceOrderDate", orderDate);
   			   formData.append("timeslot", timeslot);
   			   formData.append("quantity", quantity);
   			   formData.append("orderAmount", offerPrice);
   			   formData.append("totalAmount", totalAmount);
   			   formData.append("currency", currency);
   			   formData.append("packageMenuItems", packageMenuItems);
   			   
   			   
                $.ajax({
    				 type : "POST",
    				 data: formData,
    	    	     processData: false,
    	    	     contentType:false,
    	        	    	 url: appUrl+"/ws/saveToCart?${_csrf.parameterName}=${_csrf.token}", 
    				        success : function(result) {
    				        	
    				        	 if (result && result.response === "CART_SERVICE_EXISTS") {
    				        		 $("#serviceDetailsLoaderDiv").removeAttr("style");
    				    		     $("#invalidMsgDiv").removeAttr("style");
    					    		 $("#invalidMsgDiv").css({ display: "block" });
    						         $("#invalidMsg").html("Attention! You had already added to cart.");
    						         $("#invalidMsgDiv").fadeIn(500);
    						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
    				    		   }else if (result && result.response === "CLEAR_EXISTING_CART") {
    				        		 $("#serviceDetailsLoaderDiv").removeAttr("style");
    				    		     $("#invalidMsgDiv").removeAttr("style");
    					    		 $("#invalidMsgDiv").css({ display: "block" });
    						         $("#invalidMsg").html("Attention! Please clear exising cart and continue.");
    						         $("#invalidMsgDiv").fadeIn(500);
    						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
    				    		   }else if (result.response === "AWKWARD") {
    				        		   $("#serviceDetailsLoaderDiv").removeAttr("style");
    				    		        location.href = "/errorPage";
    				    		   }else if (result.response === "SUCCESS") {    
    				    			     $("#serviceDetailsLoaderDiv").removeAttr("style");
    							         $("#successMsgDiv").removeAttr("style");
    						    		 $("#successMsgDiv").css({ display: "block" });
    							         $("#successMsg").html("Well done! You successfully added to cart.");
    							         $("#successMsgDiv").fadeIn(200);
    							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
    							 	     getCategoryServices(categoryUUID);
    			                    }else if (result && result.response === "UN_AUTHORIZED_USER") {
    			                    	
    			                    	cartMap = new Map([
    			                    		  ['serviceId', vendorMasterServiceId],
    			                    		  ['vendorId', vendorId],
    			                    		  ['serviceOrderDate', orderDate],
    			                    		  ['timeslot', timeslot],
    			                    		  ['quantity', quantity],
    			                    		  ['orderAmount', offerPrice],
    			                    		  ['totalAmount', totalAmount],
    			                    		  ['currency', currency],
    			                    		  ['categoryUUID', categoryUUID],
    			                    		  ['packageMenuItems', packageMenuItems]
    			                    		]);
    			                    	
    				        		 $("#serviceDetailsLoaderDiv").removeAttr("style");
    				        		 $("#signup").click();
    								 $(".modal sign-up-modal").fadeIn();
    				    		   }
    				        	
    				        		
    				},
    		}); 
    	   }
       }
       
       </script>
   
   