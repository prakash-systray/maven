<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="preloader"></div>


<style>
.navbar-default .navbar-nav>li>a {
    color: white !important;
}
.nav .nav-tabs .vendor-cart .nav-item {
max-width: 120px !important;
} 

</style>

<div id="wrapper" class="wrapper bg-ash">
  <jsp:include page="../wayupartyMasterHeader.jsp" />
        <div class="dashboard-page-one">
           <jsp:include page="../wayupartyMasterSideNav.jsp" />
	          <div class="dashboard-content-one">
	            <div class="breadcrumbs-area">
	                    <h3>Vendor Services</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        <li>Vendor Services</li>
	                    </ul>
	             </div>
	              <div id="vendorServicesLoadingDiv"></div>
	              <jsp:include page="servicesList.jsp" />
	             </div>
	     </div>
	      <jsp:include page="../wayupartyMasterFooter.jsp" />
	      <script src="/resources/js/jquery.input-stepper.js"></script>  
 </div>
 
 
	 
	             