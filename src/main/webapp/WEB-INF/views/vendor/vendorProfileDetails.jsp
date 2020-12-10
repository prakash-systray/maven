<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="/resources/css/jquery.tree-multiselect.css">

<div id="preloader"></div>

   <div id="wrapper" class="wrapper bg-ash">
    <jsp:include page="../wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
          <jsp:include page="../wayupartyMasterSideNav.jsp" />
            <div class="dashboard-content-one">
                <div class="breadcrumbs-area">
	                    <h3>Vendor Profile</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>Vendor Profile</li>
	                    </ul>
	             </div>
	             
                
                <div class="card ui-tab-card">
                    <div class="card-body">
                    <div id="vendorDetailsLoaderDiv"></div>
                        <div class="heading-layout1 mg-b-25">
                           <div class="custom-tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#basic" role="tab" onclick="loadVendorBasicDetails();" aria-selected="true">Basic</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#bank" role="tab" onclick="loadVendorBankDetails();" aria-selected="false">Bank</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#address" role="tab" onclick="loadVendorAddressDetails();" aria-selected="false">Address</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#category" onclick="loadVendorCategoryDetails();" role="tab" aria-selected="false">Category</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#facilities" role="tab" aria-selected="false" onclick="loadVendorFacilitiesDetails();" >Facilities</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#music" role="tab" aria-selected="false" onclick="loadVendorMusicDetails();">Music Genre</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#cuisine" role="tab" aria-selected="false" onclick="loadVendorCuisineDetails();">Cuisine</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#workinghours" role="tab" aria-selected="false" onclick="loadWrokingHoursDetails();">Working Hours</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#termsandcondidtions" role="tab" aria-selected="false" onclick="loadTermsAndConditions();">T&C</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#images" role="tab" aria-selected="false" onclick="loadSliderImages();">Images</a>
                                </li>
                            </ul>
                            
                            <div class="tab-content">
                                    
                                <jsp:include page="vendorBasicDetails.jsp" />
                                <jsp:include page="vendorBankDetails.jsp" />
                                <jsp:include page="vendorAddress.jsp" />
                                <jsp:include page="vendorCategories.jsp" />
                                <jsp:include page="vendorFacilities.jsp" />
                                <jsp:include page="vendorMusicGenre.jsp" />
                                <jsp:include page="vendorCuisine.jsp" />
                                <jsp:include page="vendorWorkingHours.jsp" />
                                <jsp:include page="vendorTermsAndConditions.jsp" />
                                <jsp:include page="vendorImages.jsp" />
                                
                            </div>
                        </div>
                    </div>
                  </div>
                </div>
            </div>
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
       <script src="/resources/js/jquery.tree-multiselect.js"></script>
       <script src="/resources/js/bootstrap-timepicker.js"></script> 
 </div>
 
  
 
 
  <script type="text/javascript">
		window.onload = function () {
			loadVendorBasicDetails();
		 };
 </script>
 
 <script>
 $('.bootstrap-timepicker > input').timepicker();
 </script>
 
 
 <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script>   

<script>
 $('body').on('blur', '#add_vendor_details_form', function() {
		$("#add_vendor_details_form").showHelpOnFocus();
		$("#add_vendor_details_form").validateOnBlur(false, validationSettings);  
   });
</script> 

<script>
 $('body').on('blur', '#add_vendor_bank_account_form', function() {
		$("#add_vendor_bank_account_form").showHelpOnFocus();
		$("#add_vendor_bank_account_form").validateOnBlur(false, validationSettings);  
   });
</script> 

<script>
 $('body').on('blur', '#add_vendor_address_form', function() {
		$("#add_vendor_address_form").showHelpOnFocus();
		$("#add_vendor_address_form").validateOnBlur(false, validationSettings);  
   });
</script> 

<script>
 $('body').on('blur', '#add_vendor_terms_form', function() {
		$("#add_vendor_terms_form").showHelpOnFocus();
		$("#add_vendor_terms_form").validateOnBlur(false, validationSettings);  
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
   document.getElementById("uploadImageBtn").onchange = function () {
       $('#uploadImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
   };
   $(function () {
       $("#uploadImageBtn").change(function () {
           fileUpload("uploadImageBtn", "vendor_profile_images", "add_vendor_details_form",0,0);
           $(".clearUpload").show();
       });
   });
</script>        