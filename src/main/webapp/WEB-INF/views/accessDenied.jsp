
<div id="preloader"></div>
 <div id="wrapper" class="wrapper bg-ash">
 <jsp:include page="wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
         <jsp:include page="wayupartyMasterSideNav.jsp" />
          
           <div class="dashboard-content-one">
            <div class="breadcrumbs-area">
                    <ul>
                        <li>
                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
                        </li>
                        <li>Access Denied</li>
                    </ul>
             </div>
             
             <div class="card height-auto">
	             <div class="card-body">
	             <p class="text-danger permissondenied big-txt">
	                    <img src="/resources/img/no-data.png">
	                    &nbsp;&nbsp;&nbsp;You don't have permission to access this page
	                    </p>
	            </div>
            </div>
                 
             </div>
          </div>
        <jsp:include page="wayupartyMasterFooter.jsp" />
   </div>
             