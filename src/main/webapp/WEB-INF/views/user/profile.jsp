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
	                    <h3>Profile</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>Profile</li>
	                    </ul>
	             </div>
	             
            
            <div class="card height-auto">
             <div id="profileDetailsLoadingDiv"></div>
                   <div class="card-body">
                        
                    	<div class="row rowFlex">
		                    <div class="col m8 s12 colFlex" id="mainColFlex">
		                         <div class="Mpan MpanSection">
		                            <div class="MpanBody" id="innerFormArea">
			                             <div class="MpanBodyPersonal">
					                         <div class="MpanSection">
						                          <div class="tab-content">
						                          	<jsp:include page="userProfile.jsp"/>
						                          	<jsp:include page="resetPassword.jsp"/>
						                          </div>
					                         </div>
				                         </div>
			                        </div>
			                        
			                        <div class="MpanNav" id="MpanNav">
										    <ul class="topSection nav nav-tabs">
										        <li>
										            <a data-toggle="tab" href="#userProfile" id="profileDetails" aria-selected="true" role="tab" onclick="getUserProfileInfo();"
										              class="leftTabSection active show">
										                <i class="fas fa-user"></i><br/>
										                <span class="tab-name mt-2">User Profile</span>
										            </a>
										        </li>
										        <li>
										            <a data-toggle="tab" href="#resetPassword" id="passwordDetails" onclick="getUserPasswordInfo();" role="tab" aria-selected="false"   
										            class="leftTabSection">
										                <i class="fas fa-key"></i><br/>
										                <span class="tab-name mt-2">Reset Password</span>
										            </a>
										        </li>
										    </ul>
										</div>
			                     </div>
			                 </div>
                    	 </div>
                    	
                  </div>
            </div>
            </div>
        </div>
        <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script>
   document.getElementById("uploadImageBtn").onchange = function () {
       $('#uploadImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
   };
   $(function () {
       $("#uploadImageBtn").change(function () {
           fileUpload("uploadImageBtn", "user_profile_images", "user_profile_form",0,0);
           $(".clearUpload").show();
       });
   });
</script> 

<script>
function validate_doc_fileupload(fileName)
{
    var allowed_extensions = new Array("jpg","JPG","jpeg","png");
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
</script>


<script>
 $('body').on('blur', '#user_profile_form', function() {
		$("#user_profile_form").showHelpOnFocus();
		$("#user_profile_form").validateOnBlur(false, validationSettings);  
   });
 
 $('body').on('blur', '#reset_password_form', function() {
		$("#reset_password_form").showHelpOnFocus();
		$("#reset_password_form").validateOnBlur(false, validationSettings);  
   });
</script> 


 
 <style>
 .leftTabSection.active.show{
 color:#be9c52 !important;
 }
 .row {
    margin-right: -30px !important;
}
 </style>
 
 
 <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 