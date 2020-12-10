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
                    <h3>Admin Dashboard</h3>
                </div>
                
                <div class="row gutters-20">
                   <div id="portletsLoadingDiv"></div>
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <div class="serve-iconblo">
                                        <img src="/resources/img/bottle.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Bottle</div>
                                        <div class="item-number"><span id="bottleCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/table.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Table</div>
                                        <div class="item-number"><span  id="tableCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                   <div class="serve-iconblo">
                                        <img src="/resources/img/packages.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Packages</div>
                                        <div class="item-number"><span  id="packageCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/entry.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Entry</div>
                                        <div class="item-number"><span  id="entryCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/guest.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Guest List</div>
                                        <div class="item-number"><span  id="entryCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/surprise.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Surprise</div>
                                        <div class="item-number"><span  id="surpriseCount">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xl-3 col-sm-6 col-12">
                        <div class="dashboard-summery-one mg-b-20">
                            <div class="row align-items-center">
                                <div class="col-6">
                                     <div class="serve-iconblo">
                                        <img src="/resources/img/offers.jpg"></img>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="item-content">
                                        <div class="item-title">Offers</div>
                                        <div class="item-number"><span  id="offersCount">0</span></div>
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