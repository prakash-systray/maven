<div class="container">

 <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
      <i class="far fa-hand-point-right bg-light-green3"></i>
     <span id="successMsg" style="color: white;"></span> 
 </div>
 
<div class="card height-auto">
<div class="card-body">
  <div class="row">
    
    <div class="col-sm-12 col-md-8">
    <div class="cart_widget">
      <h4>My Cart<span  id="mycartDiv"></span></h4>
      <div id="cartListDiv"></div>
    </div>
  </div>

    <div class="col-sm-12 col-md-4" id="priceDetailsDiv">
        <div class="cart_widget">
        <h4 class="text-muted text-uppercase">Price Details</h4>        
            <div class="clearfix cart_pricelist">
                    <ul class="list-unstyled">
                        <li>Price<span id="cartItemsDiv"></span><span class="pull-right" id="totalPrice"></span></li>
                        <li><strong>Total Amount</strong> <span class="pull-right" id="totalAmount"><i class="fa fa-rupee text-muted"></i></span></li>
                    </ul>
            </div>
    </div>
    </div>

  <div class="clearfix col-sm-12 col-md-8" id="placeOrderDiv">
    <div class="modal-footer">
          <button type="button" class="btn-fill-lmd radius-30 text-light gradient-pastel-green" onclick="placeOrder();"><i class="fa fa-shopping-bag mg-l-10"></i>&nbsp;Place Order</button>
      </div>
  </div>

  </div>
</div>
</div>
</div>



								            
<style>
.pull-right {
    float: right!important;
}
</style>


<script>
var cartItems = [];
function getCartList(){
	
	   var appUrl ='${appUrl}';
	   $("#cartListLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getCartList?${_csrf.parameterName}=${_csrf.token}",
			     success :function(resultData) {
					 
			    	 var result = "";
			    	 if(resultData.data.length > 0){
						 
						 $("#cartItemsDiv").html('('+resultData.data.length+ ' Items)');
						 $("#mycartDiv").html('('+resultData.data.length+')');
						 
						 var totalAmount = '';
						 var currencyCode = '';
						 
						 for (var i=0; i<resultData.data.length; i++)
			   				{ 
							  var opt = resultData.data[i];
							  cartItems.push(opt.cartUUID);
							  currencyCode = getCurrency(opt.currency);
							  result = result +'<div class="clearfix row cart_widget_inner">';
							  result = result +'<div class="col-sm-12 col-md-2 text-center">';
							  result = result +'<div class="item_img cart_margn_top">';
							  result = result +'<img src="'+opt.serviceImage+'" class="img-responsive" />';
							  result = result +'</div>';
							  result = result +'</div>';
							  result = result +'<div class="col-sm-12 col-md-10">';
							  result = result +'<div class="row">';
							  result = result +'<div class="col-sm-12 col-md-8 cart_margn_top">';
							  result = result +'<p>'+opt.serviceName+'</p>';
							  result = result +'<p class="text-muted">Price : '+currencyCode+' '+opt.orderAmount+'</p>';
							  result = result +'<p class="text-muted">Quantity : '+opt.quantity+'</p>';
							  result = result +'<p class="text-muted">Amount : '+currencyCode+' '+opt.totalAmount+'</p>';
							  result = result +'</div>';
							  result = result +'<div class="col-sm-12 col-md-4">';
							  result = result +'<div class="cart_margn_top">';
							  result = result +'<p>'+opt.serviceOrderDate+'</p>';
							  result = result +'<p>'+opt.timeSlot+'</p>';
							  result = result +'<p class="small text-danger">No Order Replacement</p>';
							  result = result +'<a href="javascript:void(0)" onclick="removeCartItem(\''+opt.cartUUID+'\')" class="text-uppercase btn">Remove</a>';
							  result = result +'</div>';
							  result = result +'</div>';
							  result = result +'</div>';
							  result = result +'</div>';
							  result = result +'</div>';
							  
							  totalAmount = Number(opt.totalAmount) + Number(totalAmount);
							
			   				} 
			        	  
			        	  $("#cartListDiv").empty();  
    		   	    	  $("#cartListDiv").append(result);
    		   	    	  
    		   	    	 $("#totalPrice").html(currencyCode+"&nbsp;"+totalAmount);
						 $("#totalAmount").html(currencyCode+"&nbsp;"+totalAmount);
						 
					 }else{
						 document.getElementById("priceDetailsDiv").style.display="none";
						 document.getElementById("placeOrderDiv").style.display="none";
						 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/no-records.png" alt=""/></td>';
						 $("#cartListDiv").empty();  
   		   	    	     $("#cartListDiv").append(result);
					 }
					 $("#cartListLoadingDiv").removeAttr("style");
					},
		});
	
}



function placeOrder(){
	 var appUrl ='${appUrl}';
	 $("#cartListLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
		var userUUID = '${Wayuparty_loginUserUUId}';
		alert(userUUID);
		var formData = new FormData();
		formData.append("cartItems", cartItems);
		formData.append("userUUID", userUUID);
	   
	   $.ajax({
		   data: formData,
   	    contentType: false,
   	    processData: false,
			  type: "POST",
			    url: appUrl+"/placeOrder",
			     success :function(result) {
			    	 
			    	   if (result.response === "AWKWARD") {
		        		   $("#cartListLoadingDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    		   }else if (result.response === "SUCCESS") {     
					         $("#successMsgDiv").removeAttr("style");
				    		 $("#successMsgDiv").css({ display: "block" });
					         $("#successMsg").html("Well done! You successfully placed Order.");
					         $("#successMsgDiv").fadeIn(200);
					 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
					 	     location.replace(appUrl+"/cart");
					 	     $("#cartListLoadingDiv").removeAttr("style");
	                       }
			    	
					 
					},
		});
}


function removeCartItem(cartUUID){
	
	 var appUrl ='${appUrl}';
	   $("#cartListLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "POST",
			    url: appUrl+"/removeCartItem?cartUUID="+cartUUID,
			     success :function(result) {
			    	 
			    	   if (result.response === "AWKWARD") {
		        		   $("#cartListLoadingDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    		   }else if (result.response === "SUCCESS") {     
					         $("#successMsgDiv").removeAttr("style");
				    		 $("#successMsgDiv").css({ display: "block" });
					         $("#successMsg").html("Well done! You successfully removed items from cart.");
					         $("#successMsgDiv").fadeIn(200);
					 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
					 	     location.replace(appUrl+"/cart");
					 	     $("#cartListLoadingDiv").removeAttr("style");
	                       }
			    	
					 
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

