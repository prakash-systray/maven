<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="sidebar-main sidebar-menu-one sidebar-expand-md sidebar-color">
               <div class="mobile-sidebar-header d-md-none">
                    <div class="header-logo">
                        <a href="${Wayuparty_appUrl}/dashboard"><img src="/resources/img/logo.png" alt="logo" class="dashboardLogoImg"></a>
                    </div>
               </div>
                <div class="sidebar-menu-content">
                    <ul class="nav nav-sidebar-menu sidebar-toggle-view">
                        
                            <li class="nav-item">
                            <a href="${Wayuparty_appUrl}/dashboard" class="${sessionData.nav == 'Dashboard' ? 'nav-link menu-active' : 'nav-link'}"><i
                                    class="flaticon-dashboard"></i><span>Dashboard</span></a>
                            </li>
                            
                            <li class="nav-item">
                            <a href="${Wayuparty_appUrl}/profile" class="${sessionData.nav == 'Profile' ? 'nav-link menu-active' : 'nav-link'}"><i
                                    class="flaticon-user"></i><span>Profile</span></a>
                            </li>
                            
                            <li class="nav-item">
                            <a href="${Wayuparty_appUrl}/orders" class="${sessionData.nav == 'Orders' ? 'nav-link menu-active' : 'nav-link'}"><i
                                    class="flaticon-shopping-list"></i><span>Orders</span></a>
                            </li>
                            
                            <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                            <li class="nav-item">
                            <a href="${Wayuparty_appUrl}/vendorProfile?vendorUUID=${Wayuparty_vendorUUID}" class="${sessionData.nav == 'Explore' ? 'nav-link menu-active' : 'nav-link'}"><i
                                    class="fas fa-search-plus"></i><span>Details</span></a>
                            </li>
                            
                            <li class="nav-item">
                            <a href="${Wayuparty_appUrl}/vendorServices?vendorUUID=${Wayuparty_vendorUUID}" class="${sessionData.nav == 'Services' ? 'nav-link menu-active' : 'nav-link'}"><i
                                    class="fas fa-bullhorn"></i><span>Services</span></a>
                            </li>
                            </sec:authorize>
                            
                            <li class="nav-item">
                            <a href="${Wayuparty_appUrl}/logout" class="nav-link" ><i class="flaticon-turn-off"></i><span>Sign Out</span></a>
                            </li>
                            
                    </ul>
                </div>
            </div>