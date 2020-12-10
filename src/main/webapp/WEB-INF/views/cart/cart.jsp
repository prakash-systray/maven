<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="preloader"></div>

<div id="wrapper" class="wrapper bg-ash">
  <jsp:include page="../wayupartyMasterHeader.jsp" />
        <div class="dashboard-page-one">
           <jsp:include page="../wayupartyMasterSideNav.jsp" />
	          <div class="dashboard-content-one">
	            <div class="breadcrumbs-area">
	                    <h3>Cart</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        <li>User Cart</li>
	                    </ul>
	             </div>
	              <div id="cartListLoadingDiv"></div>
	              <jsp:include page="cartList.jsp" />
	             </div>
	     </div>
	      <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script>
 window.onload = function () {
		getCartList();
	 };
 </script>
 
 
	 
	             