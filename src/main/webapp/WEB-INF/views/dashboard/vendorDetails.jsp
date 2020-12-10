<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="ui-modal-box">
  <div class="modal-box">
       <div class="modal right-slide-modal fade" id="right-slide-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                        <div id="vendorDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Vendor Details</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                               
                                           <div class="profile_images_img item-img" id="profileImage"></div>   
                                           
                                           <div class="modal-body">
                                            <ul>    
                                               <li><strong>Vendor Name  : </strong><span id="vendorName"></span></li>
                                               <li><strong>Vendor Code  : </strong><span id="vendorCode"></span></li>
                                               <li><strong>Vendor Email  : </strong><span id="vendorEmail"></span></li>
                                               <li><strong>Vendor Mobile  : </strong><span id="vendorMobile"></span></li>
                                               <li><strong>Established Year  : </strong><span id="establishedYear"></span></li>
                                               <li><strong>Vendor Capacity		: </strong><span id="vendorCapacity"></span></li>
                                               <li><strong>Cost For Two People  : </strong><span id="costForTwoPeople"></span></li>
                                               </ul>
                                            </div>
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fas fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
       </div>
       
       
       <script>
       function getVendorDetails(vendorUUID){
    	   
    	   var appUrl ='${appUrl}';
    	   $("#vendorDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	   $.ajax({
    			  type: "GET",
    			    url: appUrl+"/getVendorDetails?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
    			     success :function(response) {
    			    	    
    			    	 $("#vendorDetailsLoaderDiv").removeAttr("style");
    			    	 
    			    	 $("#profileImage").html('<img src="'+response.object.vendorProfileImg+'" onerror="predefineVendorProfileImage(this);" data-id= "vendorProfileImage" >');
    			    	 $("#vendorName").html(response.object.vendorName);
    			    	 $("#vendorCode").html(response.object.vendorCode);
    			    	 $("#vendorEmail").html(response.object.vendorEmail);
    			    	 $("#vendorMobile").html(response.object.vendorMobile);
    			    	 $("#establishedYear").html(response.object.establishedYear);
    			    	 $("#vendorCapacity").html(response.object.vendorCapacity);
    			    	 $("#costForTwoPeople").html(response.object.costForTwoPeople);
    					},
    		});
       }
       </script>
       
       