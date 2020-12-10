package com.acculytixs.wayuparty.rest.vendor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
import com.acculytixs.wayuparty.dto.vendor.AddVendorDTO;
import com.acculytixs.wayuparty.dto.vendor.TimeSchedulerInfo;
import com.acculytixs.wayuparty.dto.vendor.VendorAddressDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorBankDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorCustomDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorImagesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorProfileCategoriesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorWorkingHoursDTO;
import com.acculytixs.wayuparty.enums.Result;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.Response;
import com.acculytixs.wayuparty.util.ResponseList;


@RestController
public class VendorRestController {
	
	@Autowired
	VendorService vendorService;
	
	
	
	
	/***
	 * This service to validate the vendor mobile. vendor mobile should not duplicate
	 * @param vendorMobile
	 * @param vendorUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = { "/validateVendorMobile",
			"/rest/validateVendorMobile" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateVendorMobile(
			@RequestParam(value = "vendorMobile", required = true) String vendorMobile,
			@RequestParam(value = "vendorUUID", required = false) String vendorUUID, HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<>();

		try {
			boolean flag;
			if (StringUtils.isNotBlank(vendorUUID)) {
				VendorDTO vendorDTO = vendorService.getVendorDetails(vendorUUID);
				if (StringUtils.isNotBlank(vendorDTO.getVendorMobile())
						&& vendorDTO.getVendorMobile().trim().equalsIgnoreCase(vendorMobile.trim())) {
					response.setResponse(Result.VALID_DATA.name());
				} else {
					flag = vendorService.isVendorExistedByPhoneNumber(vendorMobile);
					if (flag == true) {
						response.setResponse(Result.INVALID_DATA.name());
					} else {
						response.setResponse(Result.VALID_DATA.name());
					}

				}
			} else {
				flag = vendorService.isVendorExistedByPhoneNumber(vendorMobile);
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
	
	
	/***
	 * This service to validate the vendor email. vendor email should not duplicate
	 * @param vendorEmail
	 * @param vendorUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = { "/validateVendorEmail",
			"/rest/validateVendorEmail" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateVendorEmail(
			@RequestParam(value = "vendorEmail", required = true) String vendorEmail,
			@RequestParam(value = "vendorUUID", required = false) String vendorUUID, HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<>();

		try {
			boolean flag;
			if (StringUtils.isNotBlank(vendorUUID)) {
				VendorDTO vendorDTO = vendorService.getVendorDetails(vendorUUID);
				if (StringUtils.isNotBlank(vendorDTO.getVendorEmail())
						&& vendorDTO.getVendorEmail().toLowerCase().trim().equalsIgnoreCase(vendorEmail.toLowerCase().trim())) {
					response.setResponse(Result.VALID_DATA.name());
				} else {
					flag = vendorService.isVendorExistedByEmailId(vendorEmail);
					if (flag == true) {
						response.setResponse(Result.INVALID_DATA.name());
					} else {
						response.setResponse(Result.VALID_DATA.name());
					}

				}
			} else {
				flag = vendorService.isVendorExistedByEmailId(vendorEmail);
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
	
	
	/***
	 * This service to validate the vendor code. vendor code should not duplicate
	 * @param vendorCode
	 * @param vendorUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = { "/validateVendorCode",
			"/rest/validateVendorCode" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateVendorCode(
			@RequestParam(value = "vendorCode", required = true) String vendorCode,
			@RequestParam(value = "vendorUUID", required = false) String vendorUUID, HttpServletRequest request)
			throws Exception {
		Response<String> response = new Response<>();

		try {
			boolean flag;
			if (StringUtils.isNotBlank(vendorUUID)) {
				VendorDTO vendorDTO = vendorService.getVendorDetails(vendorUUID);
				if (StringUtils.isNotBlank(vendorDTO.getVendorCode())
						&& vendorDTO.getVendorCode().toLowerCase().trim().equalsIgnoreCase(vendorCode.toLowerCase().trim())) {
					response.setResponse(Result.VALID_DATA.name());
				} else {
					flag = vendorService.isVendorExistedByVendorCode(vendorCode);
					if (flag == true) {
						response.setResponse(Result.INVALID_DATA.name());
					} else {
						response.setResponse(Result.VALID_DATA.name());
					}

				}
			} else {
				flag = vendorService.isVendorExistedByVendorCode(vendorCode);
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
	
	/***
	 * This service to save new vendor
	 * @param addVendorDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@RequestMapping(value = "/saveVendor", method = RequestMethod.POST)
	public Response<String> saveVendor(AddVendorDTO addVendorDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(addVendorDTO)) {
				vendorService.saveVendor(addVendorDTO);
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
	
	/***
	 * This service to get all register vendors list in Super Admin dashboard
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')") 
	@RequestMapping(value = { "/getAllregisteredVendorsList", "/rest/getAllregisteredVendorsList" }, method = RequestMethod.GET)
	public ResponseList<VendorDTO> getAllregisteredVendorsList(HttpServletRequest request) throws Exception {

		ResponseList<VendorDTO> vendorsList = new ResponseList<VendorDTO>();
		try {
			List<VendorDTO> vendorsDTOList = vendorService.getRegisteredVendors();
			vendorsList.setData(vendorsDTOList);
			vendorsList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			vendorsList.setResponse(Result.AWKWARD.name());
			vendorsList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return vendorsList;
	}
	
	/***
	 * This service to get vendor details in for individual vendors in Super Admin dashboard 
	 * @param vendorUUID
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_USER')") 
	@RequestMapping(value = { "/getVendorDetails", "/rest/getVendorDetails" }, method = RequestMethod.GET)
	public Response<VendorDTO> getVendorDetails(
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		Response<VendorDTO> response = new Response<VendorDTO>();

		try {
			VendorDTO vendorDTO = vendorService.getVendorDetails(vendorUUID);
			response.setObject(vendorDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;

	}
	
	
	/***
	 * This service to save vendor basic details in vendor profile summary
	 * @param addVendorDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorBasicDetails", method = RequestMethod.POST)
	public Response<String> saveVendorBasicDetails(VendorDetailsDTO vendorDetailsDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(vendorDetailsDTO)) {
				vendorService.saveVendorBasicDetails(vendorDetailsDTO);
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
	
	/***
	 * This service to get vendor bank account details
	 * @param vendorUUID
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = { "/getVendorBankAccountDetails", "/rest/getVendorBankAccountDetails" }, method = RequestMethod.GET)
	public Response<VendorBankDetailsDTO> getVendorBankAccountDetails(
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		Response<VendorBankDetailsDTO> response = new Response<VendorBankDetailsDTO>();

		try {
			VendorBankDetailsDTO vendorBankDetailsDTO = vendorService.getVendorBankDetails(vendorUUID);
			response.setObject(vendorBankDetailsDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;

	}
	
	/***
	 * This service to save vendor bank details
	 * @param vendorBankDetailsDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorBankDetails", method = RequestMethod.POST)
	public Response<String> saveVendorBankDetails(VendorBankDetailsDTO vendorBankDetailsDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(vendorBankDetailsDTO)) {
				String result = vendorService.saveVendorBankAccountDetails(vendorBankDetailsDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				
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
	
	/***
	 * This service to save vendor address details
	 * @param vendorAddressDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorAddressDetails", method = RequestMethod.POST)
	public Response<String> saveVendorAddressDetails(VendorAddressDTO vendorAddressDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(vendorAddressDTO)) {
				String result = vendorService.saveVendorAddressDetails(vendorAddressDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
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
	
	
	/***
	 * This service to load vendor all categories (ie., categories, Facilities, Music Genre and Cuisine)
	 * @param categoryType
	 * @param vendorUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = { "/getProfileCategories", "/rest/getProfileCategories" }, method = RequestMethod.POST)
	public ResponseList<VendorProfileCategoriesDTO> getProfileCategories(@RequestParam(value = "categoryType", required = true) String categoryType,
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) throws Exception {

		ResponseList<VendorProfileCategoriesDTO> vendorsProfileCategoriesList = new ResponseList<VendorProfileCategoriesDTO>();
		try {
			
			VendorProfileCategoriesDTO profileCategories = vendorService.getVendorProflieCategories(vendorUUID);
			List<VendorProfileCategoriesDTO> vendorsProfileCategoriesDTOList = vendorService.getAllcategoriesByType(categoryType);
			List<VendorProfileCategoriesDTO> profileCategoriesDTOList = new CopyOnWriteArrayList<VendorProfileCategoriesDTO>();
			
			if(categoryType.equalsIgnoreCase(Constants.VENDOR_PROFILE_CATEGORIES_TYPE)) {
				
				if(StringUtils.isNotBlank(profileCategories.getCategoriesIds())) {
					List<Long> categoriesIdsList = new ArrayList<Long>();
					for (String s : Arrays.stream(profileCategories.getCategoriesIds().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
						categoriesIdsList.add(Long.parseLong(s));
		        	 }
					
					for (VendorProfileCategoriesDTO categoriesDTO : vendorsProfileCategoriesDTOList) {
						if (categoriesIdsList.contains(categoriesDTO.getCategoryId().longValue())) {
							categoriesDTO.setIsCategoryConfigured(Constants.YES);
						}
						else {
							categoriesDTO.setIsCategoryConfigured(Constants.NO);
						}
						profileCategoriesDTOList.add(categoriesDTO);
					}
					vendorsProfileCategoriesList.setData(profileCategoriesDTOList);
				}else {
					vendorsProfileCategoriesList.setData(vendorsProfileCategoriesDTOList);
				}
			}
			
			if(categoryType.equalsIgnoreCase(Constants.VENDOR_PROFILE_FACILITIES_TYPE)) {
				
				if(StringUtils.isNotBlank(profileCategories.getFacilitiesIds())) {
					List<Long> categoriesIdsList = new ArrayList<Long>();
					for (String s : Arrays.stream(profileCategories.getFacilitiesIds().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
						categoriesIdsList.add(Long.parseLong(s));
		        	 }
					
					for (VendorProfileCategoriesDTO categoriesDTO : vendorsProfileCategoriesDTOList) {
						if (categoriesIdsList.contains(categoriesDTO.getCategoryId().longValue())) {
							categoriesDTO.setIsCategoryConfigured(Constants.YES);
						}
						else {
							categoriesDTO.setIsCategoryConfigured(Constants.NO);
						}
						profileCategoriesDTOList.add(categoriesDTO);
					}
					vendorsProfileCategoriesList.setData(profileCategoriesDTOList);
				}else {
					vendorsProfileCategoriesList.setData(vendorsProfileCategoriesDTOList);
				}
			}
			
			if(categoryType.equalsIgnoreCase(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE)) {
				
				if(StringUtils.isNotBlank(profileCategories.getMusicIds())) {
					List<Long> categoriesIdsList = new ArrayList<Long>();
					for (String s : Arrays.stream(profileCategories.getMusicIds().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
						categoriesIdsList.add(Long.parseLong(s));
		        	 }
					
					for (VendorProfileCategoriesDTO categoriesDTO : vendorsProfileCategoriesDTOList) {
						if (categoriesIdsList.contains(categoriesDTO.getCategoryId().longValue())) {
							categoriesDTO.setIsCategoryConfigured(Constants.YES);
						}
						else {
							categoriesDTO.setIsCategoryConfigured(Constants.NO);
						}
						profileCategoriesDTOList.add(categoriesDTO);
					}
					vendorsProfileCategoriesList.setData(profileCategoriesDTOList);
				}else {
					vendorsProfileCategoriesList.setData(vendorsProfileCategoriesDTOList);
				}
			}
			
		if(categoryType.equalsIgnoreCase(Constants.VENDOR_PROFILE_CUISINE_TYPE)) {
				
				if(StringUtils.isNotBlank(profileCategories.getCuisineIds())) {
					List<Long> categoriesIdsList = new ArrayList<Long>();
					for (String s : Arrays.stream(profileCategories.getCuisineIds().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
						categoriesIdsList.add(Long.parseLong(s));
		        	 }
					
					for (VendorProfileCategoriesDTO categoriesDTO : vendorsProfileCategoriesDTOList) {
						if (categoriesIdsList.contains(categoriesDTO.getCategoryId().longValue())) {
							categoriesDTO.setIsCategoryConfigured(Constants.YES);
						}
						else {
							categoriesDTO.setIsCategoryConfigured(Constants.NO);
						}
						profileCategoriesDTOList.add(categoriesDTO);
					}
					vendorsProfileCategoriesList.setData(profileCategoriesDTOList);
				}else {
					vendorsProfileCategoriesList.setData(vendorsProfileCategoriesDTOList);
				}
			}
			
			vendorsProfileCategoriesList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			vendorsProfileCategoriesList.setResponse(Result.AWKWARD.name());
			vendorsProfileCategoriesList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return vendorsProfileCategoriesList;
	}
	
	/***
	 * This service to save vendor all categories (ie., categories, Facilities, Music Genre and Cuisine)
	 * @param vendorProfileCategoriesDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorProfileCategories", method = RequestMethod.POST)
	public Response<String> saveVendorProfileCategories(VendorProfileCategoriesDTO vendorProfileCategoriesDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(vendorProfileCategoriesDTO)) {
				String result = vendorService.saveVendorProfileCategoriesDetails(vendorProfileCategoriesDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
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
	
	
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorWorkingHours", method = RequestMethod.POST)
	public Response<String> saveVendorWorkingHours(VendorWorkingHoursDTO vendorWorkingHoursDTO, 
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(vendorWorkingHoursDTO)) {
				String result = vendorService.saveVendorWorkingHoursDetails(vendorWorkingHoursDTO, vendorUUID);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
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
	
	/***
	 * This serive to load vendor working hours details
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = { "/getVendorWorkingHoursDetails", "/rest/getVendorWorkingHoursDetails" }, method = RequestMethod.POST)
	public ResponseList<TimeSchedulerInfo> getVendorWorkingHoursDetails(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) throws Exception {

		ResponseList<TimeSchedulerInfo> timeSchedulerList = new ResponseList<TimeSchedulerInfo>();
		try {
			List<TimeSchedulerInfo> vendorsWorkingHoursDTOList = vendorService.getVendorWorkingHoursDetails(vendorUUID);
			timeSchedulerList.setData(vendorsWorkingHoursDTOList);
			timeSchedulerList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			timeSchedulerList.setResponse(Result.AWKWARD.name());
			timeSchedulerList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return timeSchedulerList;
	}
	
	
	/***
	 * This service to get vendors terms and conditions
	 * @param vendorUUID
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = { "/getVendorTermsAndConditions", "/rest/getVendorTermsAndConditions" }, method = RequestMethod.GET)
	public Response<VendorCustomDetailsDTO> getVendorTermsAndConditions(
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		Response<VendorCustomDetailsDTO> response = new Response<VendorCustomDetailsDTO>();

		try {
			VendorCustomDetailsDTO vendorCustomDetailsDTO = vendorService.getVendorTermsAndCondtionsDetails(vendorUUID);
			response.setObject(vendorCustomDetailsDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;

	}
	
	/***
	 * This serveice to save vendor terms and conditions
	 * @param vendorCustomDetailsDTO
	 * @param vendorUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorTermsAndConditions", method = RequestMethod.POST)
	public Response<String> saveVendorTermsAndConditions(VendorCustomDetailsDTO vendorCustomDetailsDTO, 
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(vendorCustomDetailsDTO)) {
				String result = vendorService.saveVendorTermsAndConditions(vendorCustomDetailsDTO, vendorUUID);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
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
	
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorsImages", method = RequestMethod.POST)
	public Response<String> saveVendorsImages(VendorImagesDTO vendorImagesDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(vendorImagesDTO)) {
				String result = vendorService.saveVendorImages(vendorImagesDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
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
	
	
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
	@RequestMapping(value = { "/loadVendorImages", "/rest/loadVendorImages" }, method = RequestMethod.GET)
	public Response<VendorCustomDetailsDTO> loadVendorImages(
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		Response<VendorCustomDetailsDTO> response = new Response<VendorCustomDetailsDTO>();

		try {
			VendorCustomDetailsDTO vendorCustomDetailsDTO = vendorService.getVendorTermsAndCondtionsDetails(vendorUUID);
			response.setObject(vendorCustomDetailsDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;

	}

}
