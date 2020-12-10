package com.acculytixs.wayuparty.rest.vendor;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acculytixs.wayuparty.annotations.Validator;
import com.acculytixs.wayuparty.dto.services.PackageMenuItemsDTO;
import com.acculytixs.wayuparty.dto.services.PackageMenuOfferingDTO;
import com.acculytixs.wayuparty.dto.services.ServicesInfoDTO;
import com.acculytixs.wayuparty.dto.services.VendorBottleServiceDTO;
import com.acculytixs.wayuparty.dto.services.VendorEntryServiceDTO;
import com.acculytixs.wayuparty.dto.services.VendorPackageDTO;
import com.acculytixs.wayuparty.dto.services.VendorServicesDTO;
import com.acculytixs.wayuparty.dto.services.VendorTableServiceDTO;
import com.acculytixs.wayuparty.dto.vendor.BottleCategoryInfo;
import com.acculytixs.wayuparty.dto.vendor.ServiceSubCategoryDTO;
import com.acculytixs.wayuparty.dto.vendor.ServicesCategoryDTO;
import com.acculytixs.wayuparty.entity.vendor.Vendors;
import com.acculytixs.wayuparty.enums.Result;
import com.acculytixs.wayuparty.services.vendor.PackageService;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.services.vendor.VendorServicesService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.Response;
import com.acculytixs.wayuparty.util.ResponseList;
import com.acculytixs.wayuparty.util.SessionManager;

@RestController
public class WayupartyServicesRestController {

	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	VendorServicesService vendorServicesService;
	
	@Autowired
	PackageService packageService;
	
	/***
	 * This service to save bottle types
	 * @param bottleCategoryInfo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/saveCategoryBottles", method = RequestMethod.POST)
	public Response<String> saveCategoryBottles(BottleCategoryInfo bottleCategoryInfo, HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<String>();
		
		try {
			if(Validator.validate(bottleCategoryInfo)){
				String result = vendorServicesService.saveBottleCategories(bottleCategoryInfo);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
			}else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
		}
		
		return response;
	}
	
	/***
	 * This service to get bottle types List
	 * @param categoryId
	 * @param vendorUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getServiceSubCategoryDetails", method = RequestMethod.GET)
	public ResponseList<ServiceSubCategoryDTO> getServiceSubCategoryDetails(@RequestParam(value = "categoryId", required = true) Long categoryId,
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request)
			throws Exception {
		ResponseList<ServiceSubCategoryDTO> subCategoriesList = new ResponseList<ServiceSubCategoryDTO>();
		try {
			Vendors vendors = vendorService.getVendorByUUID(vendorUUID);
			List<ServiceSubCategoryDTO> subCategoriesDTOList = vendorServicesService.getVendorServiceSubCategories(categoryId, vendors.getId());
			subCategoriesList.setData(subCategoriesDTOList);
			subCategoriesList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			subCategoriesList.setResponse(Result.AWKWARD.name());
			subCategoriesList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return subCategoriesList;
	}
	
	/***
	 * This service to get bottle types
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getServiceCategoriesTypes", method = RequestMethod.GET)
	public ResponseList<ServicesCategoryDTO> getServiceCategoriesTypes(@RequestParam(value="serviceUUID", required=true) String serviceUUID,
			HttpServletRequest request) throws Exception {

		ResponseList<ServicesCategoryDTO> serviceTypesList = new ResponseList<ServicesCategoryDTO>();
		try {
			Long serviceId = vendorService.getServiceIdByUUID(serviceUUID);
			List<ServicesCategoryDTO> serviceCategoryList = vendorServicesService.getServiceCategoriesByServiceId(serviceId);
			serviceTypesList.setData(serviceCategoryList);
			serviceTypesList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			serviceTypesList.setResponse(Result.AWKWARD.name());
			serviceTypesList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return serviceTypesList;
	}
	
	/***
	 * This service to save new bottle service
	 * @param vendorBottleServiceDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/saveBottleService", method = RequestMethod.POST)
	public Response<String> saveBottleService(VendorBottleServiceDTO vendorBottleServiceDTO, HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<String>();
		
		try {
			if(Validator.validate(vendorBottleServiceDTO)){
				BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
				String result = vendorServicesService.saveBottleService(vendorBottleServiceDTO, loginUserId.longValue());
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
			}else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
		}
		
		return response;
	}
	
	/***
	 * This service to get all vendor services list
	 * @param serviceUUID
	 * @param vendorUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getVendorServicesList", method = RequestMethod.GET)
	public ResponseList<VendorServicesDTO> getVendorServicesList(@RequestParam(value="serviceUUID", required=true) String serviceUUID,
			@RequestParam(value="vendorUUID", required=true) String vendorUUID, HttpServletRequest request) throws Exception {
		
		ResponseList<VendorServicesDTO> vendorServicesList = new ResponseList<VendorServicesDTO>();
		try {
			Long serviceId = vendorService.getServiceIdByUUID(serviceUUID);
			Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
			vendorServicesList = vendorServicesService.getVendorServicesList(serviceId, vendorId);
			vendorServicesList.setResponse(Result.SUCCESS.name());
		}catch (Exception e) {
			vendorServicesList.setResponse(Result.AWKWARD.name());
			vendorServicesList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return vendorServicesList;
	}
	
	/***
	 * This service to get vendor service details
	 * @param serviceUUID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/getVendorServiceDetails", "/rest/getVendorServiceDetails", "/ws/getVendorServiceDetails" }, method = RequestMethod.GET)
	public Response<VendorServicesDTO> getVendorServiceDetails(
			@RequestParam(value = "serviceUUID", required = true) String serviceUUID, HttpServletRequest request) {
		Response<VendorServicesDTO> response = new Response<VendorServicesDTO>();

		try {
			VendorServicesDTO serviceDTO = vendorServicesService.getVendorServiceDetails(serviceUUID);
			response.setObject(serviceDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = { "/validateVendorBottleService", "/rest/validateVendorBottleService" }, method = RequestMethod.POST)
	public  Response<String> validateVendorBottleService(
			@RequestParam(value = "startDate", required = true) String startDate,
			@RequestParam(value = "endDate", required = true) String endDate,
			@RequestParam(value = "bottleId", required = true) String bottleId,
			@RequestParam(value = "bottleUUID", required = false) String bottleUUID,
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<>();

		try {
			Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
			Date serviceStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);  
			Date serviceEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);  
			
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			 startDate = formatter.format(serviceStartDate);  
			 endDate = formatter.format(serviceEndDate); 
			 boolean flag;
			 if(StringUtils.isNotBlank(bottleUUID)) {
				 flag = vendorServicesService.isSavedServiceExistsBySubCategoryIdAndVendorId(startDate, endDate, Long.valueOf(bottleId), vendorId); 
			 }else {
				 flag = vendorServicesService.isServiceExistsBySubCategoryIdAndVendorId(startDate, endDate, Long.valueOf(bottleId), vendorId);
			 }
			 
				if (flag == true) {
					response.setResponse(Result.INVALID_DATA.name());
				} else {
					response.setResponse(Result.VALID_DATA.name());
				}


		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	@RequestMapping(value = { "/getServicesList", "/rest/getServicesList", "/ws/getServicesList" }, method = RequestMethod.GET)
	public Response<ServicesInfoDTO> getServicesList(
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		Response<ServicesInfoDTO> response = new Response<ServicesInfoDTO>();

		try {
			ServicesInfoDTO serviceInfoDTO = vendorServicesService.getServicesList(vendorUUID);
			response.setObject(serviceInfoDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value = { "/getServiceCategoriesList", "/rest/getServiceCategoriesList", "/ws/getServiceCategoriesList" }, method = RequestMethod.GET)
	public ResponseList<ServicesCategoryDTO> getServiceCategoriesList(@RequestParam(value="serviceUUID", required=true) String serviceUUID,
			 HttpServletRequest request) throws Exception {
		
		ResponseList<ServicesCategoryDTO> vendorServiceCategoriesList = new ResponseList<ServicesCategoryDTO>();
		try {
			Long serviceId = vendorService.getServiceIdByUUID(serviceUUID);
			List<ServicesCategoryDTO> serviceCategoryList = vendorServicesService.getServiceCategoriesByServiceId(serviceId);
			vendorServiceCategoriesList.setData(serviceCategoryList);
			vendorServiceCategoriesList.setResponse(Result.SUCCESS.name());
		}catch (Exception e) {
			vendorServiceCategoriesList.setResponse(Result.AWKWARD.name());
			vendorServiceCategoriesList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return vendorServiceCategoriesList;
	}
	
	
	@RequestMapping(value = "/ws/getCategoryServicesList", method = RequestMethod.GET)
	public ResponseList<VendorServicesDTO> getCategoryServicesList(@RequestParam(value="categoryUUID", required=true) String categoryUUID,
			@RequestParam(value="vendorUUID", required=true) String vendorUUID, HttpServletRequest request) throws Exception {
		
		ResponseList<VendorServicesDTO> vendorServicesList = new ResponseList<VendorServicesDTO>();
		try {
			Long categoryId = vendorService.getServiceCategoryIdByUUID(categoryUUID);
			Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
			List<VendorServicesDTO> vendorServicesDTOList = vendorServicesService.getVendorCategoryServicesList(categoryId, vendorId);
			vendorServicesList.setData(vendorServicesDTOList);
			vendorServicesList.setResponse(Result.SUCCESS.name());
		}catch (Exception e) {
			vendorServicesList.setResponse(Result.AWKWARD.name());
			vendorServicesList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return vendorServicesList;
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = { "/validateVendorService", "/rest/validateVendorService" }, method = RequestMethod.POST)
	public  Response<String> validateVendorService(
			@RequestParam(value = "startDate", required = true) String startDate,
			@RequestParam(value = "endDate", required = true) String endDate,
			@RequestParam(value = "serviceId", required = true) String serviceId,
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, 
			@RequestParam(value = "masterServiceUUID", required = true) String masterServiceUUID,HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<>();

		try {
			Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
			Date serviceStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);  
			Date serviceEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);  
			
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			 startDate = formatter.format(serviceStartDate);  
			 endDate = formatter.format(serviceEndDate); 
			 boolean flag;
			 if(StringUtils.isNotBlank(masterServiceUUID)) {
				 flag = vendorServicesService.isSavedServiceExistsByCategoryIdAndVendorId(startDate, endDate, Long.valueOf(serviceId), vendorId);
			 }else {
				 flag = vendorServicesService.isServiceExistsByCategoryIdAndVendorId(startDate, endDate, Long.valueOf(serviceId), vendorId);
			 }
			 
				if (flag == true) {
					response.setResponse(Result.INVALID_DATA.name());
				} else {
					response.setResponse(Result.VALID_DATA.name());
				}


		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service to save vendor table schedule
	 * @param vendorTableServiceDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/saveTableService", method = RequestMethod.POST)
	public Response<String> saveTableService(VendorTableServiceDTO vendorTableServiceDTO, HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<String>();
		
		try {
			if(Validator.validate(vendorTableServiceDTO)){
				BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
				String result = vendorServicesService.saveTableService(vendorTableServiceDTO, loginUserId.longValue());
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
			}else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
		}
		
		return response;
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/saveEntryService", method = RequestMethod.POST)
	public Response<String> saveEntryService(VendorEntryServiceDTO vendorEntryServiceDTO, HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<String>();
		
		try {
			if(Validator.validate(vendorEntryServiceDTO)){
				BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
				String result = vendorServicesService.saveEntryService(vendorEntryServiceDTO, loginUserId.longValue());
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
			}else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
		}
		
		return response;
	}
	
	
	/***
	 * This service to get vendor master service details
	 * @param serviceUUID
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getVendorMasterServiceDetails", method = RequestMethod.GET)
	public Response<VendorServicesDTO> getVendorMasterServiceDetails(
			@RequestParam(value = "serviceUUID", required = true) String serviceUUID, HttpServletRequest request) {
		Response<VendorServicesDTO> response = new Response<VendorServicesDTO>();

		try {
			VendorServicesDTO serviceDTO = vendorServicesService.getVendorServiceDetailsByMasterServiceUUID(serviceUUID);
			response.setObject(serviceDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = { "/getPackageMenuOfferingList", "/rest/getPackageMenuOfferingList" }, method = RequestMethod.GET)
	public ResponseList<PackageMenuOfferingDTO> getPackageMenuOfferingList(@RequestParam(value="vendorUUID", required=true) String vendorUUID,
			@RequestParam(value="packageUUID", required=false) String packageUUID,
			 HttpServletRequest request) throws Exception {
		
		ResponseList<PackageMenuOfferingDTO> packageMenuOfferingDTOList = new ResponseList<PackageMenuOfferingDTO>();
		try {
			Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
			List<PackageMenuOfferingDTO> packageMenuOfferingList = packageService.getPackageMenuOfferingList(vendorId,packageUUID);
			packageMenuOfferingDTOList.setData(packageMenuOfferingList);
			packageMenuOfferingDTOList.setResponse(Result.SUCCESS.name());
		}catch (Exception e) {
			packageMenuOfferingDTOList.setResponse(Result.AWKWARD.name());
			packageMenuOfferingDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return packageMenuOfferingDTOList;
	}
	
	
	
	@RequestMapping(value = { "/validateMenuName", "/rest/validateMenuName" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateMenuName(
			@RequestParam(value = "menuItem", required = true) String menuItem,
			@RequestParam(value = "menuItemUUID", required = false) String menuItemUUID,
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) throws Exception {
		Response<String> response = new Response<>();
		try {
			boolean flag;
			Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
			if (StringUtils.isNotBlank(menuItemUUID)) {
				PackageMenuOfferingDTO packageMenuOfferingDTO = packageService.getPackMenuOfferingByUUID(menuItemUUID);
				if (packageMenuOfferingDTO.getMenuItem().toLowerCase().trim()
						.equalsIgnoreCase(menuItem.toLowerCase().trim())) {
					response.setResponse(Result.VALID_DATA.name());
				} else {
					flag = packageService.isMenuNameExists(menuItem, vendorId);
					if (flag == true) {
						response.setResponse(Result.INVALID_DATA.name());
					} else {
						response.setResponse(Result.VALID_DATA.name());
					}
				}
			} else {
				flag = packageService.isMenuNameExists(menuItem, vendorId);
				if (flag == true) {
					response.setResponse(Result.INVALID_DATA.name());
				} else {
					response.setResponse(Result.VALID_DATA.name());
				}
			}

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/saveMenuOfferingItem", method = RequestMethod.POST)
	public Response<String> saveMenuOfferingItem(PackageMenuOfferingDTO packageMenuOfferingDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(packageMenuOfferingDTO)) {
				BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
				Long vendorId = vendorService.getVendorIdByUUID(packageMenuOfferingDTO.getVendorUUID());
				packageService.savePackageOfferingItem(packageMenuOfferingDTO, vendorId, loginUserId.longValue());
				response.setResponse(Result.SUCCESS.name());
			} else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getPackageMenuItemDetails", method = RequestMethod.GET)
	public Response<PackageMenuOfferingDTO> getPackageMenuItemDetails(
			@RequestParam(value = "menuItemUUID", required = true) String menuItemUUID, HttpServletRequest request) {
		Response<PackageMenuOfferingDTO> response = new Response<PackageMenuOfferingDTO>();

		try {
			PackageMenuOfferingDTO packageMenuOfferingDTO = packageService.getPackMenuOfferingByUUID(menuItemUUID);
			response.setObject(packageMenuOfferingDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/savePackageMenuItems", method = RequestMethod.POST)
	public Response<String> savePackageMenuItems(PackageMenuOfferingDTO packageMenuOfferingDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(packageMenuOfferingDTO)) {
				BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
				Long vendorId = vendorService.getVendorIdByUUID(packageMenuOfferingDTO.getVendorUUID());
				packageService.savePackageOfferingMenuItems(packageMenuOfferingDTO, vendorId, loginUserId.longValue());
				response.setResponse(Result.SUCCESS.name());
			} else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}

	
	@RequestMapping(value = { "/loadSavedMenuItems", "/rest/loadSavedMenuItems" }, method = RequestMethod.GET)
	public ResponseList<PackageMenuItemsDTO> loadSavedMenuItems(@RequestParam(value="menuItemUUID", required=true) String menuItemUUID,
			 HttpServletRequest request) throws Exception {
		
		ResponseList<PackageMenuItemsDTO> packageMenuItemsDTOList = new ResponseList<PackageMenuItemsDTO>();
		try {
			PackageMenuOfferingDTO packageMenuOfferingDTO = packageService.getPackMenuOfferingByUUID(menuItemUUID);
			List<PackageMenuItemsDTO> packageMenuItemsList = packageService.getPackageMenuItemsList(packageMenuOfferingDTO.getMenuItemId().longValue());
			packageMenuItemsDTOList.setData(packageMenuItemsList);
			packageMenuItemsDTOList.setResponse(Result.SUCCESS.name());
		}catch (Exception e) {
			packageMenuItemsDTOList.setResponse(Result.AWKWARD.name());
			packageMenuItemsDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return packageMenuItemsDTOList;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorPackage", method = RequestMethod.POST)
	public Response<String> saveVendorPackage(VendorPackageDTO vendorPackageDTO,
			@RequestParam(value = "packageOfferingMenu", required = false) String packageOfferingMenu, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(vendorPackageDTO) && StringUtils.isNotBlank(packageOfferingMenu)) {
				BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
				String result = vendorServicesService.savePackageService(vendorPackageDTO, packageOfferingMenu, loginUserId.longValue());
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
				response.setResponse(Result.SUCCESS.name());
			} else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}

}
