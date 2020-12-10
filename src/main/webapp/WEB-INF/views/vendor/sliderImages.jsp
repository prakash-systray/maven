<div class="tab-pane fade show active" id="slider" role="tabpanel">
<div id="vendorSliderDetailsLoaderDiv"></div>
		<form class="new-added-form" id="add_vendor_slider_images_form" name="add_vendor_slider_images_form" >
		<input type="hidden" id="sliderFileCounter" value="0">
		 	 	<div class="row">	
		 	 	
		 	 	<div class="col-12" id="vendorSliderImagesDiv">
                       <p id="sliderImagesDiv" class="row"></p>
                 </div>
						                 
			 	 	<div class="col-12 ui-btn-wrap">
     						<ul>
     							 <li><button type="button" class="footer-btn gradient-pastel-green" style="cursor:pointer;" onclick="addSliderFileUpload()"><i class="fas fa-plus mg-l-10"></i>&nbsp;Add Image</button></li>
							</ul>
						<div id="sliderFileUploadContainer">
						</div>
	                </div>
	                <div class="col-12 ui-btn-wrap">
				               <ul>
				               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendorSliderImages()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
				             </ul>
	   				</div>
		 	 	</div>
		</form>
</div>

<div class="alert icon-alart bg-light-green2" id="successSliderImgMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successSliderImgMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidSliderImgMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidSliderImgMsg" style="color: white;"></span> 
</div>

<script>
function loadSliderImages(){
    var menuFileCounter = $("#menuFileCounter").val();
    for(var i=0; i<menuFileCounter; i++){
    	$("#add_vendor_menu_images_form div[id=menuFileDiv"+i+"]").remove();
    	$("#add_vendor_menu_images_form input[data-id=menuFile"+i+"]").remove();
    }
    $("#menuFileCounter").val(0);
	
	var galleryFileCounter = $("#galleryFileCounter").val();
    for(var i=0; i<galleryFileCounter; i++){
    	$("#add_vendor_gallery_images_form div[id=galleryFileDiv"+i+"]").remove();
    	$("#add_vendor_gallery_images_form input[data-id=galleryFile"+i+"]").remove();
    }
    $("#galleryFileCounter").val(0);
	
	var sliderFileCounter = $("#sliderFileCounter").val();
    for(var i=0; i<sliderFileCounter; i++){
    	$("#add_vendor_slider_images_form div[id=sliderFileDiv"+i+"]").remove();
    	$("#add_vendor_slider_images_form input[data-id=sliderFile"+i+"]").remove();
    }
    $("#sliderFileCounter").val(0);
    
    
    var videosFileCounter = $("#videosFileCounter").val();
    for(var i=0; i<videosFileCounter; i++){
    	$("#add_vendor_videos_images_form div[id=videosFileDiv"+i+"]").remove();
    	$("#add_vendor_videos_images_form input[data-id=videosFile"+i+"]").remove();
    }
    $("#videosFileCounter").val(0);
    
	var vendorUUID = '${vendorUUID}';
	 var appUrl ='${appUrl}';
	   $("#vendorSliderDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/loadVendorImages?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
			     success :function(response) {
			    	    
			    	 $("#vendorSliderDetailsLoaderDiv").removeAttr("style");
			    	 
			    	 if(response.object.sliderImages !=  null && response.object.sliderImages != '' && response.object.sliderImages != 'undefined'){
			    		 
			    		 var result = "";
				    	 var sliderImgUrl = response.object.sliderImages.split(",");
				    	 $("#sliderFileCounter").val(sliderImgUrl.length+1);
				    	 
				    	 for(var i=0; i < sliderImgUrl.length; i++){
				    		 var sliderCounter = i;
				    		 result = result +'<div class="col-md-3 mb-2" id="sliderImg-'+sliderCounter+'">';
				    		 result = result +'<div class="video-image">';
				    		 result = result +'<img  src="'+sliderImgUrl[i]+'" >';
				    		 result = result +'</div>';
				    		 result = result +'<div class="clearUpload clearUpload-position" onclick="removeSliderImg('+sliderCounter+')"></div> ';
				    		 result = result +'<input id="sliderFile'+sliderCounter+'"  type="hidden" data-id="sliderFile'+sliderCounter+'" name="fileInfo['+sliderCounter+'].fileURL" value="'+sliderImgUrl[i]+'"/>';
				    		 result = result +'<input id="sliderFile'+sliderCounter+'"  type="hidden" data-id="sliderFile'+sliderCounter+'" name="fileInfo['+sliderCounter+'].isSavedImg" value="Y"/>';
				    		 result = result +'</div>';
				    	 }
				    	 
				    	 $("#sliderImagesDiv").empty();
				    	 $("#sliderImagesDiv").append(result);
			    	 }
			    	 
					},
		});
	   
}

function removeSliderImg(id){
	 var inputs =  $("#add_vendor_slider_images_form input[data-id=sliderFile"+id+"]");
    for(var i=0, len=inputs.length; i<len; i++){
		        inputs[i].remove();
    }
    
    document.getElementById('sliderImg-'+id).style.display='none'
    
}
</script>


<script>
function addSliderFileUpload(){
	var sliderFileCounter = $("#sliderFileCounter").val();
	$('<div id="sliderFileDiv'+sliderFileCounter+'">').attr('style','float:left; width:250px; border:1px solid #ddd; margin:0 15px 15px 0; position:relative; padding:5px;overflow: hidden; background-color:#367fa9; color:#fff; ').append('<input id="sliderFile'+sliderFileCounter+'"  type="file" class="pull-left" field-name="Image" data-validation="required validateFileName validateImg" onchange="uploadSliderButton('+sliderFileCounter+',this);"/>' +
            '<a id="Button' + sliderFileCounter + '' +
            'value="" onclick = "removeSliderFileUpload(this,'+sliderFileCounter+')"><i class="clearUpload clearUpload-position pull-right"></i></a>').appendTo($('#sliderFileUploadContainer'));

			sliderFileCounter++;
    	    $("#sliderFileCounter").val(sliderFileCounter);
    		
}


function removeSliderFileUpload(div,id){
	 document.getElementById("sliderFileUploadContainer").removeChild(div.parentNode);
	 
	 var inputs =  $("#add_vendor_slider_images_form input[data-id=sliderFile"+id+"]");
    for(var i=0, len=inputs.length; i<len; i++){
		        inputs[i].remove();
    }
}

function uploadSliderButton(id,document){
	var fileName = $("#sliderFile"+id).val().replace(/C:\\fakepath\\/i, '');
	var vendorId = '${vendorId}';
	if (validate_doc_fileupload(fileName)){
		var docSize = document.files[0].size;
	     if (docSize > 2097152) {
            $('#sliderFile'+id).find('p.jquery_form_error_message').remove();
            $("input#sliderFile"+id).after("<p class='jquery_form_error_message'>Limit is exceeded than 2MB </span>");
            document.getElementById("sliderFile"+id).setAttribute('record-exist', 'yes');
            document.getElementById("sliderFile"+id).setAttribute('record-exist-errorMsg', 'Limit is exceeded than 2MB');
        }else{
       	   fileUpload("sliderFile"+id+"", "vendor_images", "add_vendor_slider_images_form",vendorId,id);
        }
		
	}
	
}

</script>

<script>
function saveVendorSliderImages(){
	  if ($('#add_vendor_slider_images_form').validate(false, validationSettings)){
		   $("#vendorSliderDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
          var appUrl = "${appUrl}";
          var vendorUUID = "${vendorUUID}";
          var formData = getWayupayFormData("add_vendor_slider_images_form");
		  formData.append("vendorUUID", vendorUUID);
		  formData.append("imageType", "Slider");
		  var sliderFileCounter = $("#sliderFileCounter").val();;
           $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorsImages?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorSliderDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidSliderImgMsgDiv").removeAttr("style");
					    		 $("#invalidSliderImgMsgDiv").css({ display: "block" });
						         $("#invalidSliderImgMsg").html("Attention! You had missed some data.");
						         $("#invalidSliderImgMsgDiv").fadeIn(500);
						 	     $('#invalidSliderImgMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorSliderDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							 	     loadSliderImages();
							 	    
			                   }
				        	
				        		
				},
		});   
          return true;
	   }else{
			return false;
		  } 
}
</script>
		 	 	