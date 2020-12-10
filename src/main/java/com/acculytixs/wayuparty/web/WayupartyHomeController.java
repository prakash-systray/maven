package com.acculytixs.wayuparty.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorCustomDetailsDTO;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.services.WayupartyLoginService;
import com.acculytixs.wayuparty.services.user.UserService;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.SessionMaintainanceData;
import com.acculytixs.wayuparty.util.SessionManager;

@Controller
public class WayupartyHomeController {

	@Value("${appUrl}")
	private String appUrl;
	
	@Value("${static.path}")
	private String staticPath;
	
	@Autowired
	WayupartyLoginService wayupartyLoginService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("favicon.ico")
	@ResponseBody
	public void returnNoFavicon() {
	}
	
	@RequestMapping(value = {"/", "/home"})
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("home"); 
		return model; 
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("login"); 
		return model; 
	}
	
	@RequestMapping(value = "/errorPage", method = RequestMethod.GET)
	public ModelAndView displayErrorPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errorPage");
		return model;

	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		model.setViewName("accessDenied");
		return model;

	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView permissionDenied() {

		ModelAndView model = new ModelAndView();
		model.setViewName("accessDenied");
		return model;

	}
	
	@RequestMapping(value = "/forgotPassword")
	public ModelAndView forgotPassword(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("forgotPassword"); 
		return model; 
	}
	
	@RequestMapping(value = "/registerUser")
	public ModelAndView registerUser(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("user/registerUser"); 
		return model; 
	}
	
	@RequestMapping(value = "/ws/activateEmail")
	public ModelAndView privacyPloicy(@RequestParam(value = "tocken", required = true) String tocken,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			User user = userService.getUserByUUID(tocken);
			if(user != null) {
				user.setIsEmailVerified(Constants.STRING_Y);
				userService.saveUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("appUrl", appUrl);
		model.setViewName("verifyAccount"); 
		return model; 
	}
	
	/***
	 * This service for dashbord (landing page)
	 * @param request
	 * @param authentication
	 * @return
	 */
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(HttpServletRequest request,Authentication authentication) {
		ModelAndView model = new ModelAndView();
		if(authentication == null || (authentication != null && authentication.getPrincipal() == null)) {
			model.setViewName("login");
		}else {
			String userName =  (String) authentication.getPrincipal();
			UserDTO userDTO = wayupartyLoginService.getLoggedInUserDetailsByUserName(userName);
			SessionManager.setSessionAttribute(request, "userId",userDTO.getUserId());
			SessionManager.setSessionAttribute(request, "appUrl",appUrl);
			
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			if(userDTO.getUserRole().equalsIgnoreCase("ROLE_ADMIN")) {
				model.setViewName("dashboard/vendorDashboard");
			}else if(userDTO.getUserRole().equalsIgnoreCase("ROLE_USER")) {
				model.setViewName("dashboard/userDashboard");
			}else {
				model.setViewName("dashboard/dashboard");
			}
			
		}
		
		return model;
	}
	
	/***
	 * This service to get new vendor adding page. Form this form page we can add new vendor.
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@RequestMapping("/vendor")
	public ModelAndView vendor(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.setViewName("vendor/vendor");
		
		    return model;
	}
	
	/***
	 * This service to get all vendor profile details
	 * @param request
	 * @return
	 */
	@RequestMapping("/vendorProfile")
	public ModelAndView vendorProfileDetails(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			String loginUserRole = (String) SessionManager.getSessionAttribute(request, "loginUserRole");
			
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			if(loginUserRole.equalsIgnoreCase("ROLE_ADMIN")) {
				sessionData.setNav(Constants.EXPLORE);
			}else {
				sessionData.setNav(Constants.DASHBOARD);
			}
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendor/vendorProfileDetails");
		
		    return model;
	}
	
	
	
	@RequestMapping("/vendorInfo")
	public ModelAndView vendorInfo(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendorInfo/vendorInfo");
		
		    return model;
	}
	
	
	@RequestMapping("/ws/vendorInfo")
	public ModelAndView vendorInfoDetails(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendorInfo/vendorInfoDetails");
		
		    return model;
	}
	
	
	@RequestMapping(value = "/ws/vendorTermsAndCondtions")
	public ModelAndView vendorTermsAndCondtions(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		VendorCustomDetailsDTO detailsDTO;
		try {
			detailsDTO = vendorService.getVendorTermsAndCondtionsDetails(vendorUUID);
			model.addObject("termsAndCondtions", detailsDTO.getTermsAndConditions());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    model.setViewName("vendorInfo/termsAndConditions");
		return model;
	}
	
	@RequestMapping("/profile")
	public ModelAndView profile(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
		    String userUUID = (String) SessionManager.getSessionAttribute(request, "loginUserUUId");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.PROFILE);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("userUUID", userUUID);
			model.setViewName("user/profile");
		
		    return model;
	}
	
	
	@RequestMapping("/orders")
	public ModelAndView orders(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
		    String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.ORDERS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
				
				List<ServicesDTO> servicesList = vendorService.getVendorServices();
				model.addObject("servicesList", servicesList);
				model.addObject("bottleServiceUUID", servicesList.get(0).getServiceUUID());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendor/orders");
		
		    return model;
	}
	
}
