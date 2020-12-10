package com.acculytixs.wayuparty.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acculytixs.wayuparty.dto.services.PackageMenuOfferingDTO;
import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.vendor.ServicesCategoryDTO;
import com.acculytixs.wayuparty.services.vendor.PackageService;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.services.vendor.VendorServicesService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.SessionMaintainanceData;
import com.acculytixs.wayuparty.util.SessionManager;

@Controller
public class WayupartyServicesController {
	
	@Value("${appUrl}")
	private String appUrl;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	VendorServicesService vendorServicesService;
	
	@Autowired
	PackageService packageService;
	
	
	/***
	 * This service to get vendor services
	 * @param vendorUUID
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/vendorServices")
	public ModelAndView vendorServices(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			String loginUserRole = (String) SessionManager.getSessionAttribute(request, "loginUserRole");
			
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			if(loginUserRole.equalsIgnoreCase("ROLE_ADMIN")) {
				sessionData.setNav(Constants.SERVICES);
			}else {
				sessionData.setNav(Constants.DASHBOARD);
			}
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
				
				List<ServicesDTO> servicesList = vendorService.getVendorServices();
				model.addObject("servicesList", servicesList);
				model.addObject("bottleServiceUUID", servicesList.get(0).getServiceUUID());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("services/vendorServices");
		
		    return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/bottleSettings")
	public ModelAndView bottleSettings(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			try {
				List<ServicesCategoryDTO> serviceCategoryList = vendorServicesService.getServiceCategoriesByServiceId(Constants.VENDOR_SERVICES_BOTTLE);
				model.addObject("serviceCategoryList", serviceCategoryList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("services/bottleSettings");
		
		    return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/bottle")
	public ModelAndView bottle(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			@RequestParam(value = "serviceUUID", required = true) String serviceUUID,
			@RequestParam(value = "bottleUUID", required = false) String bottleUUID,
			HttpServletRequest request) {
			
			ModelAndView model = new ModelAndView();
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.addObject("serviceUUID", serviceUUID);
			model.addObject("bottleUUID", bottleUUID);
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.setViewName("services/bottle");
		
		    return model;
	}
	
	
	@RequestMapping("/bookService")
	public ModelAndView bookService(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
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
			model.setViewName("servicesInfo/bookService");
		
		    return model;
	}
	
	@RequestMapping("/ws/bookService")
	public ModelAndView bookVendorService(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
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
			model.setViewName("servicesInfo/bookVendorService");
		
		    return model;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/table")
	public ModelAndView table(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			@RequestParam(value = "serviceUUID", required = true) String serviceUUID, 
			@RequestParam(value = "tableUUID", required = false) String tableUUID,
			HttpServletRequest request) {
			
			ModelAndView model = new ModelAndView();
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.addObject("serviceUUID", serviceUUID);
			model.addObject("tableUUID", tableUUID);
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.setViewName("services/table");
		
		    return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/entry")
	public ModelAndView entry(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			@RequestParam(value = "serviceUUID", required = true) String serviceUUID,
			@RequestParam(value = "entryUUID", required = false) String entryUUID,
			HttpServletRequest request) {
			
			ModelAndView model = new ModelAndView();
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.addObject("serviceUUID", serviceUUID);
			model.addObject("entryUUID", entryUUID);
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.setViewName("services/entry");
		
		    return model;
	}
	
	@RequestMapping("/cart")
	public ModelAndView cart(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.setViewName("cart/cart");
		
		    return model;
	}
	
	
	@RequestMapping("/packageSettings")
	public ModelAndView packageSettings(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.SERVICES);
			request.getSession().setAttribute("sessionData",sessionData);
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				List<PackageMenuOfferingDTO> menuList = packageService.getMenuOfferingList(vendorId);
				model.addObject("menuList", menuList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addObject("vendorUUID", vendorUUID);
			model.addObject("appUrl", appUrl);
			model.setViewName("services/packageSettings");
		
		    return model;
	}
	
	
	@RequestMapping("/packages")
	public ModelAndView packages(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			@RequestParam(value = "serviceUUID", required = true) String serviceUUID, 
			@RequestParam(value = "packageUUID", required = false) String packageUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.SERVICES);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("vendorUUID", vendorUUID);
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addObject("serviceUUID", serviceUUID);
			model.addObject("packageUUID", packageUUID);
			model.addObject("appUrl", appUrl);
			model.setViewName("services/packages");
		
		    return model;
	}
	
	@RequestMapping("/configureMenuItems")
	public ModelAndView configureMenuItems(@RequestParam(value = "menuItemUUID", required = true) String menuItemUUID,
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.SERVICES);
			request.getSession().setAttribute("sessionData",sessionData);
			
			try {
				PackageMenuOfferingDTO menuOfferingDTO = packageService.getPackMenuOfferingByUUID(menuItemUUID);
				model.addObject("menuItem", menuOfferingDTO.getMenuItem());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addObject("menuItemUUID", menuItemUUID);
			model.addObject("vendorUUID", vendorUUID);
			model.addObject("appUrl", appUrl);
			model.setViewName("services/configureMenuItems");
		
		    return model;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/guest")
	public ModelAndView guest(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			@RequestParam(value = "serviceUUID", required = true) String serviceUUID,
			@RequestParam(value = "guestUUID", required = false) String guestUUID,
			HttpServletRequest request) {
			
			ModelAndView model = new ModelAndView();
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.addObject("serviceUUID", serviceUUID);
			model.addObject("guestUUID", guestUUID);
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.setViewName("services/guest");
		
		    return model;
	}
	

}
