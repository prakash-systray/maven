<div class="tab-pane fade show active" id="menu" role="tabpanel">
<div id="vendorMenuDetailsLoaderDiv"></div>
		<form class="new-added-form" id="add_vendor_menu_images_form" name="add_vendor_menu_images_form" >
		<input type="hidden" id="menuFileCounter" value="0">
		 	 	<div class="row">	
		 	 	
		 	 	<div class="col-12" id="vendorMenuImagesDiv">
                       <p id="menuImagesDiv" class="row"></p>
                 </div>
						                 
			 	 	<div class="col-12 ui-btn-wrap">
     						<ul>
     							 <li><button type="button" class="footer-btn gradient-pastel-green" style="cursor:pointer;" onclick="addMenuFileUpload()"><i class="fas fa-plus mg-l-10"></i>&nbsp;Add Image</button></li>
							</ul>
							
						<div id="menuFileUploadContainer">
						</div>
	                </div>
	                <div class="col-12 ui-btn-wrap">
				               <ul>
				               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendorMenuImages()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
				             </ul>
	   				</div>
		 	 	</div>
		</form>
</div>

<div class="alert icon-alart bg-light-green2" id="successMenuImgMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successMenuImgMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidMenuImgMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidMenuImgMsg" style="color: white;"></span> 
</div>

<script>
function loadMenuImages(){
	
	var sliderFileCounter = $("#sliderFileCounter").val();
    for(var i=0; i<sliderFileCounter; i++){
    	$("#add_vendor_slider_images_form div[id=sliderFileDiv"+i+"]").remove();
    	$("#add_vendor_slider_images_form input[data-id=sliderFile"+i+"]").remove();
    }
    $("#sliderFileCounter").val(0);
    
    var galleryFileCounter = $("#galleryFileCounter").val();
    for(var i=0; i<galleryFileCounter; i++){
    	$("#add_vendor_gallery_images_form div[id=galleryFileDiv"+i+"]").remove();
    	$("#add_vendor_gallery_images_form input[data-id=galleryFile"+i+"]").remove();
    }
    $("#galleryFileCounter").val(0);
	
	var menuFileCounter = $("#menuFileCounter").val();
    for(var i=0; i<menuFileCounter; i++){
    	$("#add_vendor_menu_images_form div[id=menuFileDiv"+i+"]").remove();
    	$("#add_vendor_menu_images_form input[data-id=menuFile"+i+"]").remove();
    }
    $("#menuFileCounter").val(0);
    
    var videosFileCounter = $("#videosFileCounter").val();
    for(var i=0; i<videosFileCounter; i++){
    	$("#add_vendor_videos_images_form div[id=videosFileDiv"+i+"]").remove();
    	$("#add_vendor_videos_images_form input[data-id=videosFile"+i+"]").remove();
    }
    $("#videosFileCounter").val(0);
    
    
	var vendorUUID = '${vendorUUID}';
	 var appUrl ='${appUrl}';
	   $("#vendorMenuDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/loadVendorImages?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
			     success :function(response) {
			    	    
			    	 $("#vendorMenuDetailsLoaderDiv").removeAttr("style");
			    	 if(response.object.menuImages !=  null && response.object.menuImages != '' && response.object.menuImages != 'undefined'){
			    		 
			    		 var result = "";
				    	 var menuImgUrl = response.object.menuImages.split(",");
				    	 $("#menuFileCounter").val(menuImgUrl.length+1);
				    	 
				    	 for(var i=0; i < menuImgUrl.length; i++){
				    		 var menuCounter = i;
				    		 result = result +'<div class="col-md-3 mb-2" id="menuImg-'+menuCounter+'">';
				    		 result = result +'<div class="video-image">';
				    		 result = result +'<img  src="'+menuImgUrl[i]+'" >';
				    		 result = result +'</div>';
				    		 result = result +'<div class="clearUpload clearUpload-position" onclick="removeMenuImg('+menuCounter+')"></div> ';
				    		 result = result +'<input id="menuFile'+menuCounter+'"  type="hidden" data-id="menuFile'+menuCounter+'" name="fileInfo['+menuCounter+'].fileURL" value="'+menuImgUrl[i]+'"/>';
				    		 result = result +'<input id="menuFile'+menuCounter+'"  type="hidden" data-id="menuFile'+menuCounter+'" name="fileInfo['+menuCounter+'].isSavedImg" value="Y"/>';
				    		 result = result +'</div>';
				    	 }
				    	 
				    	 $("#menuImagesDiv").empty();
				    	 $("#menuImagesDiv").append(result);
			    	 }
			    	 
					},
		});
	   
}

function removeMenuImg(id){
	 var inputs =  $("#add_vendor_menu_images_form input[data-id=menuFile"+id+"]");
    for(var i=0, len=inputs.length; i<len; i++){
		        inputs[i].remove();
    }
    
    document.getElementById('menuImg-'+id).style.display='none'
    
}
</script>


<script>
function addMenuFileUpload(){
	var menuFileCounter = $("#menuFileCounter").val();
	$('<div id="menuFileDiv'+menuFileCounter+'">').attr('style','float:left; width:250px; border:1px solid #ddd; margin:0 15px 15px 0; position:relative; padding:5px;overflow: hidden; background-color:#367fa9; color:#fff; ').append('<input id="menuFile'+menuFileCounter+'"  type="file" class="pull-left" field-name="Image" data-validation="required validateFileName validateImg" onchange="uploadMenuButton('+menuFileCounter+',this);"/>' +
            '<a id="Button' + menuFileCounter + '' +
            'value="" onclick = "removeMenuFileUpload(this,'+menuFileCounter+')"><i class="clearUpload clearUpload-position pull-right"></i></a>').appendTo($('#menuFileUploadContainer'));

			menuFileCounter++;
    	    $("#menuFileCounter").val(menuFileCounter);
    		
}


function removeMenuFileUpload(div,id){
	 document.getElementById("menuFileUploadContainer").removeChild(div.parentNode);
	 
	 var inputs =  $("#add_vendor_menu_images_form input[data-id=menuFile"+id+"]");
    for(var i=0, len=inputs.length; i<len; i++){
		        inputs[i].remove();
    }
}

function uploadMenuButton(id,document){
	var fileName = $("#menuFile"+id).val().replace(/C:\\fakepath\\/i, '');
	var vendorId = '${vendorId}';
	if (validate_doc_fileupload(fileName)){
		var docSize = document.files[0].size;
	     if (docSize > 2097152) {
            $('#menuFile'+id).find('p.jquery_form_error_message').remove();
            $("input#menuFile"+id).after("<p class='jquery_form_error_message'>Limit is exceeded than 2MB </span>");
            document.getElementById("menuFile"+id).setAttribute('record-exist', 'yes');
            document.getElementById("menuFile"+id).setAttribute('record-exist-errorMsg', 'Limit is exceeded than 2MB');
        }else{
       	   fileUpload("menuFile"+id+"", "vendor_images", "add_vendor_menu_images_form",vendorId,id);
        }
		
	}
	
}

</script>

<script>
function saveVendorMenuImages(){
	  if ($('#add_vendor_menu_images_form').validate(false, validationSettings)){
		   $("#vendorMenuDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
          var appUrl = "${appUrl}";
          var vendorUUID = "${vendorUUID}";
          var formData = getWayupayFormData("add_vendor_menu_images_form");
		  formData.append("vendorUUID", vendorUUID);
		  formData.append("imageType", "Menu");
		  var menuFileCounter = $("#menuFileCounter").val();;
           $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorsImages?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorMenuDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidMenuImgMsgDiv").removeAttr("style");
					    		 $("#invalidMenuImgMsgDiv").css({ display: "block" });
						         $("#invalidMenuImgMsg").html("Attention! You had missed some data.");
						         $("#invalidMenuImgMsgDiv").fadeIn(500);
						 	     $('#invalidMenuImgMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorMenuDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							 	     loadMenuImages();
							 	    
			                   }
				        	
				        		
				},
		});   
          return true;
	   }else{
			return false;
		  } 
}
</script>
		 	 	