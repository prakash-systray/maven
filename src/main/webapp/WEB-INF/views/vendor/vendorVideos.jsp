<div class="tab-pane fade show active" id="videos" role="tabpanel">
<div id="vendorVideosDetailsLoaderDiv"></div>
		<form class="new-added-form" id="add_vendor_videos_form" name="add_vendor_videos_form" >
		<input type="hidden" id="videosFileCounter" value="0">
		 	 	<div class="row">	
		 	 	
		 	 	<div class="col-12" id="vendorVideosImagesDiv">
                       <p id="vendorVideosDiv" class="row"></p>
                 </div>
						                 
			 	 	<div class="col-12 ui-btn-wrap">
     						<ul>
     							 <li><button type="button" class="footer-btn gradient-pastel-green" style="cursor:pointer;" onclick="addVideosFileUpload()"><i class="fas fa-plus mg-l-10"></i>&nbsp;Add Video</button></li>
							</ul>
							
						<div id="videosFileUploadContainer">
						</div>
	                </div>
	                <div class="col-12 ui-btn-wrap">
				               <ul>
				               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveVendorVideos()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendorProfile?vendorUUID=${vendorUUID}'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
				             </ul>
	   				</div>
		 	 	</div>
		</form>
</div>

<div class="alert icon-alart bg-light-green2" id="successVideosImgMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successVideosImgMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidVideosImgMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidVideosImgMsg" style="color: white;"></span> 
</div>

<script>
function loadVideosImages(){
	
	var sliderFileCounter = $("#sliderFileCounter").val();
    for(var i=0; i<sliderFileCounter; i++){
    	$("#add_vendor_slider_images_form div[id=sliderFileDiv"+i+"]").remove();
    	$("#add_vendor_slider_images_form input[data-id=sliderFile"+i+"]").remove();
    }
    $("#sliderFileCounter").val(0);
    
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
	
	var videosFileCounter = $("#videosFileCounter").val();
    for(var i=0; i<videosFileCounter; i++){
    	$("#add_vendor_videos_form div[id=videosFileDiv"+i+"]").remove();
    	$("#add_vendor_videos_form input[data-id=videosFile"+i+"]").remove();
    }
    $("#videosFileCounter").val(0);
    
    
	var vendorUUID = '${vendorUUID}';
	 var appUrl ='${appUrl}';
	   $("#vendorVideosDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/loadVendorImages?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
			     success :function(response) {
			    	    
			    	 $("#vendorVideosDetailsLoaderDiv").removeAttr("style");
			    	 
			    	 if(response.object.vendorVideos !=  null && response.object.vendorVideos != '' && response.object.vendorVideos != 'undefined'){
			    		 
			    		 var result = "";
				    	 var videosImgUrl = response.object.vendorVideos.split(",");
				    	 $("#videosFileCounter").val(videosImgUrl.length+1);
				    	 
				    	 for(var i=0; i < videosImgUrl.length; i++){
				    		 var videosCounter = i;
				    		 result = result +'<div class="col-md-3 mb-2"  id="videosImg-'+videosCounter+'">';
				    		 result = result +'<video width="100%" height="100" controls> ';
				  		     result = result +'<source src="'+videosImgUrl[i]+'" >';
				  		     result = result +'</video>';
				    		 result = result +'<div class="clearUpload clearUpload-position" onclick="removeVideosImg('+videosCounter+')"></div> ';
				    		 result = result +'<input id="videosFile'+videosCounter+'"  type="hidden" data-id="videosFile'+videosCounter+'" name="fileInfo['+videosCounter+'].fileURL" value="'+videosImgUrl[i]+'"/>';
				    		 result = result +'<input id="videosFile'+videosCounter+'"  type="hidden" data-id="videosFile'+videosCounter+'" name="fileInfo['+videosCounter+'].isSavedImg" value="Y"/>';
				    		 result = result +'</div>';
				    	 }
				    	 
				    	 $("#vendorVideosDiv").empty();
				    	 $("#vendorVideosDiv").append(result);
			    	 }
			    	 
					},
		});
	   
}

function removeVideosImg(id){
	 var inputs =  $("#add_vendor_videos_form input[data-id=videosFile"+id+"]");
    for(var i=0, len=inputs.length; i<len; i++){
		        inputs[i].remove();
    }
    
    document.getElementById('videosImg-'+id).style.display='none'
    
}
</script>


<script>
function addVideosFileUpload(){
	var videosFileCounter = $("#videosFileCounter").val();
	$('<div id="videosFileDiv'+videosFileCounter+'">').attr('style','float:left; width:250px; border:0px solid #ddd; margin:0 15px 15px 0; position:relative; padding:5px;overflow: hidden; background-color:#367fa9; color:#fff; ').append('<input id="videosFile'+videosFileCounter+'"  type="file" class="pull-left" field-name="Image" data-validation="required validateFileName validate_Video" onchange="uploadVideosButton('+videosFileCounter+',this);"/>' +
            '<a id="Button' + videosFileCounter + '' +
            'value="" onclick = "removeVideosFileUpload(this,'+videosFileCounter+')"><i class="clearUpload clearUpload-position pull-right"></i></a>').appendTo($('#videosFileUploadContainer'));

			videosFileCounter++;
    	    $("#videosFileCounter").val(videosFileCounter);
    		
}


function removeVideosFileUpload(div,id){
	 document.getElementById("videosFileUploadContainer").removeChild(div.parentNode);
	 
	 var inputs =  $("#add_vendor_videos_form input[data-id=videosFile"+id+"]");
    for(var i=0, len=inputs.length; i<len; i++){
		        inputs[i].remove();
    }
}

function uploadVideosButton(id,document){
	var fileName = $("#videosFile"+id).val().replace(/C:\\fakepath\\/i, '');
	var vendorId = '${vendorId}';
	if (validate_video_fileupload(fileName)){
		var docSize = document.files[0].size;
	     if (docSize > 209715200) {
            $('#videosFile'+id).find('p.jquery_form_error_message').remove();
            $("input#videosFile"+id).after("<p class='jquery_form_error_message'>Limit is exceeded than 200 MB </span>");
            document.getElementById("videosFile"+id).setAttribute('record-exist', 'yes');
            document.getElementById("videosFile"+id).setAttribute('record-exist-errorMsg', 'Limit is exceeded than 200 MB');
        }else{
           $("#vendorVideosDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
       	   fileUpload("videosFile"+id+"", "vendor_images", "add_vendor_videos_form",vendorId,id);
       	    setTimeout(function () {
			 $("#vendorVideosDetailsLoaderDiv").removeAttr("style");
		  }, 8000); 
        }
		
	}
	
	   function validate_video_fileupload(fileName)
       {
           var allowed_extensions = new Array("mp4","webm","flv","wmv","wma","ogg", "oga", "ogv", "ogx","3gp", "3gp2", "3g2", "3gpp", "3gpp2");
           var file_extension = fileName.split('.').pop(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

           for (var i = 0; i <= allowed_extensions.length; i++)
           {
               if (allowed_extensions[i] == file_extension)
               {
                   return true; // valid file extension
               }
           }

           return false;
       }
	
}

</script>

<script>
function saveVendorVideos(){
	  if ($('#add_vendor_videos_form').validate(false, validationSettings)){
		   $("#vendorVideosDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
          var appUrl = "${appUrl}";
          var vendorUUID = "${vendorUUID}";
          var formData = getWayupayFormData("add_vendor_videos_form");
		  formData.append("vendorUUID", vendorUUID);
		  formData.append("imageType", "Videos");
		  var videosFileCounter = $("#videosFileCounter").val();;
           $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorsImages?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorVideosDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidVideosImgMsgDiv").removeAttr("style");
					    		 $("#invalidVideosImgMsgDiv").css({ display: "block" });
						         $("#invalidVideosImgMsg").html("Attention! You had missed some data.");
						         $("#invalidVideosImgMsgDiv").fadeIn(500);
						 	     $('#invalidVideosImgMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorVideosDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							 	     loadVideosImages();
							 	    
			                   }
				        	
				        		
				},
		});   
          return true;
	   }else{
			return false;
		  } 
}
</script>
		 	 	