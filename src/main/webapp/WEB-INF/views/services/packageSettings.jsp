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
	                    <h3>Package Settings</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        <li>Package Settings</li>
	                    </ul>
	             </div>
                
                <div class="card height-auto">
                    <div class="card-body">
                        <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Package Menu Offering</h3>
                            </div>
                           <div class="dropdown">
                                <a class="dropdown-toggle" href="#" role="button" 
                                data-toggle="dropdown" aria-expanded="false">...</a>
        
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#small-modal" onclick="clearForm();"><i class="fas fa-plus text-dark-pastel-green"></i>Add</a>
                                    <a class="dropdown-item" href="${Wayuparty_appUrl}/packageSettings"><i class="fas fa-redo-alt text-orange-peel"></i>Refresh</a>
                                </div>
                            </div> 
                        </div>
                        
                           <div class="table-responsive">
                            	<table class="table display text-nowrap" id="menuTableList" >
	                                <thead>
	                           			  <tr>
		                                    <th>Offering Item</th>
	                                        <th>Action</th>
	                                     </tr>
                                     </thead>
                                     <tbody>
	                                     <c:forEach var="menu" items="${menuList}">
	                                  	 <tr>
	                                       <td>${menu.menuItem}</td>
	                                       <td>
	                                       <a class="anchorGreen" href="${Wayuparty_appUrl}/configureMenuItems?menuItemUUID=${menu.menuItemUUID}&vendorUUID=${vendorUUID}"><i class="fa fa-key"></i>&nbsp;&nbsp;Configure</a>
	                                          &nbsp;&nbsp; | <a  class="anchorGreen" href="javascript:void(0)" data-toggle="modal" data-target="#small-modal" onclick="getMenuEditForm('${menu.menuItemUUID}');">&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-edit"></i>&nbsp;&nbsp;Edit</a>
	                                       </td>
	                                   	 </tr>
		                                </c:forEach>
	                                </tbody>
                                </table>
                            	</div>
                           
                      </div>
                   </div>
                   <jsp:include page="addPackageMenu.jsp" />
            </div>
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script>
  window.onload = function () {
		getMenuListPagination();	
	 };
	 
  function getMenuListPagination(){
	  $('#menuTableList').DataTable( { 
			"scrollCollapse": false,
			"bLengthChange": true,
			"lengthMenu": [[20, 30, 40, -1], [20, 30, 40, "All"]],
			"bDestroy": true,
			"ordering": false,
			"oLanguage": {
		 		"sSearch": "",
		 		"sSearchPlaceholder":"Search Menu",
		 		 "sZeroRecords": "", "sEmptyTable": ""
		 	}
		});
  }	 
  </script>       
 
 