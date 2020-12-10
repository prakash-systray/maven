<div class="tab-pane fade" id="music" role="tabpanel">
<div id="vendorMusicDetailsLoaderDiv"></div>
		<section class="clearfix">
				     <div class="select-role clearfix section-action-btns">
				     
				     <div class="clearfix">
					 <div id="musicListDiv"></div>
				     </div>
				     <br/>
                           <div class="ui-btn-wrap">
					             <ul>
					               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveMusicDetails()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
					               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				                   <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
					             </ul>
					       </div>
                     
				        </div>
				 </section>
</div>

<div class="alert icon-alart bg-light-green2" id="successMusicMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successMusicMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidMusicMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidMusicMsg" style="color: white;"></span> 
</div>


<script>
function saveMusicDetails(){
	 var musicIds = [];
	 $("input[name='musicList[]']:checked").each( function (i) {
		 musicIds[i] = $(this).val();
	});
	 
	 var appUrl ='${appUrl}';
	 var vendorUUID = '${vendorUUID}';
	 var formData = new FormData();
	 formData.append("categoryType", "Music Genre");
	 formData.append("vendorUUID", vendorUUID);
	 formData.append("musicIds", musicIds);
	 
		if(musicIds.length > 0){
			 $("#vendorsMusicDetailsLoaderDiv").attr('style','position:absolute; width:150%; height:80%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
			$.ajax({ 
			    data: formData,
		    	contentType: false,
		    	processData: false,
			    type: "POST",
		    	url: appUrl+"/saveVendorProfileCategories?${_csrf.parameterName}=${_csrf.token}",
		        success: function(result) {
		        	 if (result.response === "SUCCESS") {
		        		 $("#successMusicMsgDiv").removeAttr("style");
			    		 $("#successMusicMsgDiv").css({ display: "block" });
				         $("#successMusicMsg").html("Well done! You successfully added music genre.");
				         $("#successMusicMsgDiv").fadeIn(200);
				 	     $('#successMusicMsgDiv').delay(2000).fadeOut('slow');
				 	     $("#vendorsMusicDetailsLoaderDiv").removeAttr("style");
				 	    loadVendorMusicDetails();
			        }else if (result.response === "AWKWARD") {
		        		   $("#vendorsMusicDetailsLoaderDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    	     }else{
			        	 $("#invalidMusicMsgDiv").removeAttr("style");
			    		 $("#invalidMusicMsgDiv").css({ display: "block" });
				         $("#invalidMusicMsg").html("Opps! Something went worng.");
				         $("#invalidMusicMsgDiv").fadeIn(200);
				 	     $('#invalidMusicMsgDiv').delay(2000).fadeOut('slow');
				 	     $("#vendorsMusicDetailsLoaderDiv").removeAttr("style");
			        }
		        },
		 }); 
		}else{
			 $("#vendorsMusicDetailsLoaderDiv").removeAttr("style");
			 $("#invalidMusicMsgDiv").removeAttr("style");
  		     $("#invalidMusicMsgDiv").css({ display: "block" });
	         $("#invalidMusicMsg").html("Opps! Please select atleat one muaic genre.");
	         $("#invalidMusicMsgDiv").fadeIn(200);
	 	     $('#invalidMusicMsgDiv').delay(2000).fadeOut('slow');
		} 
	 
}
</script>

<script>
function loadVendorMusicDetails(){
	
	
	 var appUrl ='${appUrl}';
	 var vendorUUID = "${vendorUUID}";
	 
	 var formData = new FormData();
	 formData.append("categoryType", "Music Genre");
	 formData.append("vendorUUID", vendorUUID);
	   
	 $("#vendorMusicDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
	    	    data: formData,
	    	    contentType: false,
	    	    processData: false,
		    	type: "POST",
		    	url: appUrl+"/getProfileCategories?${_csrf.parameterName}=${_csrf.token}", 
		        success: function(resultData) {
		   			
		        	 var result = "";
		        	
		        	  result = result +'<select id="music-select" multiple="multiple">';
		        	  for (var i=0; i<resultData.data.length; i++)
		   				{ 
		        		  var opt = resultData.data[i];
		        		  var selected = ''
			        		  if(opt.isCategoryConfigured == 'Yes'){
			        			  selected = 'selected'
			        		  }
			        		  result = result +'<option value='+opt.categoryId+' data-section='+opt.categoryName+' '+selected+' >'+opt.categoryName+'</option>';
		   				}
		        	  
		        	  result = result +'result = result +"</select>';
		
                  
		        	 $("#musicListDiv").empty();  
	   	    	     $("#musicListDiv").append(result);
	   	    	     
		        },
		    }); 
	     
		  setTimeout(function () {
			 $("#music-select").treeMultiselect({ enableSelectAll: true},"Music Genre");
			 $("#vendorMusicDetailsLoaderDiv").removeAttr("style");
		  }, 500); 
}
</script>
		 	 	