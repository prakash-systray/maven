package com.acculytixs.wayuparty.rest.user;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acculytixs.wayuparty.annotations.Validator;
import com.acculytixs.wayuparty.dto.user.CartDTO;
import com.acculytixs.wayuparty.dto.user.UserCartDTO;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserProfileDTO;
import com.acculytixs.wayuparty.dto.user.UserRegistrationDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.entity.user.UserCart;
import com.acculytixs.wayuparty.enums.Result;
import com.acculytixs.wayuparty.security.WayupartyPasswordEncoder;
import com.acculytixs.wayuparty.services.WayupartyLoginService;
import com.acculytixs.wayuparty.services.user.UserCartService;
import com.acculytixs.wayuparty.services.user.UserService;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.services.vendor.VendorServicesService;
import com.acculytixs.wayuparty.util.Response;
import com.acculytixs.wayuparty.util.ResponseList;
import com.acculytixs.wayuparty.util.SessionManager;

@RestController
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	WayupartyPasswordEncoder wayupartyPasswordEncoder;
	
	@Autowired
	UserCartService userCartService;
	
	@Autowired
	private WayupartyLoginService wayupartyLoginService;
	
	@Autowired
	VendorServicesService vendorServicesService;
	
	
	/***
	 * This service to validate user email is existed or not from registration form
	 * @param userEmail
	 * @param userUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = { "/ws/validateUserEmail","/validateUserEmail" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateUserEmail(
		@RequestParam(value = "userEmail", required = true) String userEmail,
		@RequestParam(value = "userUUID", required = false) String userUUID, HttpServletRequest request)
		throws Exception {
	Response<String> response = new Response<>();
	
	try {
		boolean flag;
		if (StringUtils.isNotBlank(userUUID)) {
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			if (StringUtils.isNotBlank(userDTO.getUserEmail())
					&& userDTO.getUserEmail().toLowerCase().trim().equalsIgnoreCase(userEmail.toLowerCase().trim())) {
				response.setResponse(Result.VALID_DATA.name());
			} else {
				flag = userService.isUserExistedByEmailId(userEmail);
				if (flag == true) {
					response.setResponse(Result.INVALID_DATA.name());
				} else {
					response.setResponse(Result.VALID_DATA.name());
				}
	
			}
		} else {
			flag = userService.isUserExistedByEmailId(userEmail);
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
	 * This service to validate user mobile is existed or not from registration form
	 * @param userMobile
	 * @param userUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/ws/validateUserMobile","/validateUserMobile" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateUserMobile(
		@RequestParam(value = "userMobile", required = true) String userMobile,
		@RequestParam(value = "userUUID", required = false) String userUUID, HttpServletRequest request)
		throws Exception {
	Response<String> response = new Response<>();
	
	try {
		boolean flag;
		if (StringUtils.isNotBlank(userUUID)) {
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			if (StringUtils.isNotBlank(userDTO.getUserMobile())
					&& userDTO.getUserMobile().trim().equalsIgnoreCase(userMobile.trim())) {
				response.setResponse(Result.VALID_DATA.name());
			} else {
				flag = userService.isUserExistedByMobileNumber(userMobile);
				if (flag == true) {
					response.setResponse(Result.INVALID_DATA.name());
				} else {
					response.setResponse(Result.VALID_DATA.name());
				}
	
			}
		} else {
			flag = userService.isUserExistedByMobileNumber(userMobile);
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
	 * This service to register new user from registration form
	 * @param userRegistrationDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/saveUserRegistration", method = RequestMethod.POST)
	public Response<String> saveUserRegistration(UserRegistrationDTO userRegistrationDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(userRegistrationDTO)) {
				userService.saveNewUserRegistration(userRegistrationDTO);
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
	 * This service to get all registered vendors for user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/getAllregisteredRestaurantsList", "/rest/getAllregisteredRestaurantsList" , "/ws/getAllregisteredRestaurantsList" }, method = RequestMethod.GET)
	public ResponseList<VendorDTO> getAllregisteredVendorsList(@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit,HttpServletRequest request) throws Exception {

		ResponseList<VendorDTO> vendorsList = new ResponseList<VendorDTO>();
		try {
			List<VendorDTO> vendorsDTOList = vendorService.getRegisteredRestaurantsList(offset, limit);
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
	 * This service to get vendor details
	 * @param vendorUUID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/getVendorInfo", "/rest/getVendorInfo", "/ws/getVendorInfo" }, method = RequestMethod.GET)
	public Response<VendorInfoDTO> getVendorDetails(
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		Response<VendorInfoDTO> response = new Response<VendorInfoDTO>();

		try {
			VendorInfoDTO vendorInfoDTO = userService.getVendorInfoDetails(vendorUUID);
			response.setObject(vendorInfoDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;

	}
	
	/***
	 * This service to get user details
	 * @param userUUID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/getUserDetails", "/rest/getUserDetails"}, method = RequestMethod.GET)
	public Response<UserDTO> getUserDetails(
			@RequestParam(value = "userUUID", required = true) String userUUID, HttpServletRequest request) {
		Response<UserDTO> response = new Response<UserDTO>();

		try {
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			response.setObject(userDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;

	}
	
	/***
	 * This service to update user profile
	 * @param userProfileDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveUserProfileDetails", method = RequestMethod.POST)
	public Response<String> saveUserProfileDetails(UserProfileDTO userProfileDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(userProfileDTO)) {
				userService.saveUserProfile(userProfileDTO);
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
	 * This service to validate current password
	 * @param currentPassword
	 * @param userUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/validateCurrentPassword","/rest/validateCurrentPassword" }, method = RequestMethod.POST)
	public String validateCurrentPassword(
		@RequestParam(value = "currentPassword", required = true) String currentPassword,
		@RequestParam(value = "userUUID", required = true) String userUUID,
		HttpServletRequest request) throws Exception {
	
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			if (wayupartyPasswordEncoder.matches(currentPassword, userDTO.getPassword())) {
				return "valid";
			} else {
				return "invalid";
			}
	}
	
	/**
	 * This service to reset new password
	 * @param password
	 * @param userUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public Response<String> resetPassword(@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "userUUID", required = true) String userUUID, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (StringUtils.isNotBlank(password)) {
				userService.resetUserPassword(password, userUUID);
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
	 * This service to add service to cart form mobile side
	 * @param userCartDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rest/addToCart", method = RequestMethod.POST)
	public Response<String> addToCart(@RequestBody UserCartDTO userCartDTO,
			HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();

		try {
			
			UserDTO userDTO = userService.getUserDetailsByUUID(userCartDTO.getUserUUID());
			VendorDTO vendorDTO = vendorService.getVendorDetails(userCartDTO.getVendorUUID());
			Long serviceId = vendorServicesService.getVendorMasterServiceIdByUUID(userCartDTO.getMasterServiceUUID());
			Long vendorId = userCartService.getVendorIdForExistingCartByUserId(userDTO.getUserId().longValue());
			
			if(vendorId == null || vendorId == vendorDTO.getVendorId().longValue()) {
				
				if(StringUtils.isNotBlank(userCartDTO.getServiceOrderDate()) && StringUtils.isNotBlank(userCartDTO.getTimeslot())) {
					userCartDTO.setServiceId(serviceId);
					userCartDTO.setUserId(userDTO.getUserId().longValue());
					userCartDTO.setVendorId(vendorDTO.getVendorId().longValue());
					boolean flag = userCartService.getCartServiceExists(userCartDTO);
					if(flag == true) {
						response.setResponse(Result.CART_SERVICE_EXISTS.name());
						response.setResponseMessage(Result.CART_SERVICE_EXISTS.getValue());
					}else {
						userCartService.addToCart(userCartDTO);
						response.setResponse(Result.SUCCESS.name());
						response.setResponseMessage(Result.SUCCESS.getValue());
					}
				}else {
					if(StringUtils.isBlank(userCartDTO.getServiceOrderDate())){
						response.setResponse(Result.INVALID_ORDER_DATE.name());
						response.setResponseMessage(Result.INVALID_ORDER_DATE.getValue());
					}else if(StringUtils.isBlank(userCartDTO.getTimeslot())){
						response.setResponse(Result.INVALID_TIME_SLOT.name());
						response.setResponseMessage(Result.INVALID_TIME_SLOT.getValue());
					}
				}
			
			}else {
				response.setResponse(Result.CLEAR_EXISTING_CART.name());
				response.setResponseMessage(Result.CLEAR_EXISTING_CART.getValue());
			}
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service to add service to cart from web side
	 * @param userCartDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/saveToCart", method = RequestMethod.POST)
	public Response<String> saveToCart(UserCartDTO userCartDTO, Authentication authentication,
			HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
			if(authentication == null || (authentication != null && authentication.getPrincipal() == null)) {
				response.setResponse(Result.UN_AUTHORIZED_USER.name());
			}else {
				BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
				userCartDTO.setUserId(loginUserId.longValue());
				Long vendorId = userCartService.getVendorIdForExistingCartByUserId(loginUserId.longValue());
				if(vendorId == null || vendorId == userCartDTO.getVendorId()) {
					boolean flag = userCartService.getCartServiceExists(userCartDTO);
					if(flag == true) {
						response.setResponse(Result.CART_SERVICE_EXISTS.name());
						response.setResponseMessage(Result.CART_SERVICE_EXISTS.getValue());
					}else {
						userCartService.addToCart(userCartDTO);
						Long cartCount = userCartService.getUserCartCount(loginUserId.longValue());
						SessionManager.removeSessionAttribute(request, "cartCount");
						SessionManager.setSessionAttribute(request, "cartCount",cartCount);
						response.setResponse(Result.SUCCESS.name());
					}
				}else {
					response.setResponse(Result.CLEAR_EXISTING_CART.name());
					response.setResponseMessage(Result.CLEAR_EXISTING_CART.getValue());
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
	 * This service to get user cart list
	 * @param userId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/getCartList", "/rest/getCartList" }, method = RequestMethod.GET)
	public ResponseList<CartDTO> getCartList(@RequestParam(value = "userUUID", required = false) String userUUID,
			HttpServletRequest request) throws Exception {
		
		Long userId = null;
		if(StringUtils.isNotBlank(userUUID)) {
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			userId = userDTO.getUserId().longValue();
		}else {
			BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
			userId = loginUserId.longValue();
		}

		ResponseList<CartDTO> cartDTOList = new ResponseList<CartDTO>();
		try {
			List<CartDTO> cartList = userCartService.getUserCartList(userId);
			cartDTOList.setData(cartList);
			cartDTOList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			cartDTOList.setResponse(Result.AWKWARD.name());
			cartDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return cartDTOList;
	}
	
	/***
	 * This service for user login from mobile
	 * @param loginDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/loginRegisteredUser", method = RequestMethod.POST)
	public Response<UserLoginDTO> loginRegisteredUser(@RequestBody UserLoginDTO loginDTO, HttpServletRequest request)
			throws Exception {

		Response<UserLoginDTO> response = new Response<UserLoginDTO>();
		try {
			UserLoginDTO userLoginDTO = new UserLoginDTO();
			UserDTO userDTO = wayupartyLoginService.getUserPassWordByUsername(loginDTO.getUserName());
			if (userDTO != null && userDTO.getPassword() != null && userDTO.getUserRole().equalsIgnoreCase("ROLE_USER")) {
				if(wayupartyPasswordEncoder.matches(loginDTO.getPassword(), userDTO.getPassword())) { 
					userLoginDTO = userService.getLoginUserDetails(userDTO.getUserId().longValue());
					response.setObject(userLoginDTO);
					response.setResponse(Result.SUCCESS.name());
					response.setResponseMessage(Result.SUCCESS.getValue());
				} else {
					response.setResponse(Result.INVALID_CREDENTIALS.name());
					response.setResponseMessage(Result.INVALID_CREDENTIALS.getValue());
				}
			} else {
				response.setResponse(Result.INVALID_CREDENTIALS.name());
				response.setResponseMessage(Result.INVALID_CREDENTIALS.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service to remove cart item from moblie
	 * @param cartUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rest/removeCartItem", method = RequestMethod.POST)
	public Response<String> removeCartItem(@RequestParam(value = "cartUUID", required = true) String cartUUID,
			HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			UserCart userCart = userCartService.getUserCartbyUUID(cartUUID);
			userCartService.deleteCartItem(userCart);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.CART_ITEM_REMOVED.getValue());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service to remove cart item from web
	 * @param cartUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeCartItem", method = RequestMethod.POST)
	public Response<String> deleteCartItem(@RequestParam(value = "cartUUID", required = true) String cartUUID,
			HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();
		BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
		try {
			UserCart userCart = userCartService.getUserCartbyUUID(cartUUID);
			userCartService.deleteCartItem(userCart);
			Long cartCount = userCartService.getUserCartCount(loginUserId.longValue());
			SessionManager.removeSessionAttribute(request, "cartCount");
			SessionManager.setSessionAttribute(request, "cartCount",cartCount);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.CART_ITEM_REMOVED.getValue());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	/***
	 * This service for user login from mobile
	 * @param loginDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/loginCartUser", method = RequestMethod.POST)
	public Response<UserLoginDTO> loginCartUser(UserLoginDTO loginDTO, HttpServletRequest request)
			throws Exception {

		Response<UserLoginDTO> response = new Response<UserLoginDTO>();
		try {
			UserLoginDTO userLoginDTO = new UserLoginDTO();
			UserDTO userDTO = wayupartyLoginService.getUserPassWordByUsername(loginDTO.getUserName());
			if (userDTO != null && userDTO.getPassword() != null) {
				if(wayupartyPasswordEncoder.matches(loginDTO.getPassword(), userDTO.getPassword())) { 
					userLoginDTO = userService.getLoginUserDetails(userDTO.getUserId().longValue());
					response.setObject(userLoginDTO);
					response.setResponse(Result.SUCCESS.name());
					response.setResponseMessage(Result.SUCCESS.getValue());
				} else {
					response.setResponse(Result.INVALID_CREDENTIALS.name());
					response.setResponseMessage(Result.INVALID_CREDENTIALS.getValue());
				}
			} else {
				response.setResponse(Result.INVALID_CREDENTIALS.name());
				response.setResponseMessage(Result.INVALID_CREDENTIALS.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service for save to cart from home login
	 * @param userCartDTO
	 * @param authentication
	 * @param request
	 * @param httpResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/saveToHomeLoginCart", method = RequestMethod.POST)
	public Response<String> saveToHomeLoginCart(UserCartDTO userCartDTO, HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
				Long vendorId = userCartService.getVendorIdForExistingCartByUserId(userCartDTO.getUserId());
				if(vendorId == null || vendorId == userCartDTO.getVendorId()) {
					boolean flag = userCartService.getCartServiceExists(userCartDTO);
					if(flag == true) {
						response.setResponse(Result.CART_SERVICE_EXISTS.name());
						response.setResponseMessage(Result.CART_SERVICE_EXISTS.getValue());
					}else {
						userCartService.addToCart(userCartDTO);
						Long cartCount = userCartService.getUserCartCount(userCartDTO.getUserId());
						SessionManager.removeSessionAttribute(request, "cartCount");
						SessionManager.setSessionAttribute(request, "cartCount",cartCount);
						response.setResponse(Result.SUCCESS.name());
					}
				}else {
					response.setResponse(Result.CLEAR_EXISTING_CART.name());
					response.setResponseMessage(Result.CLEAR_EXISTING_CART.getValue());
				}
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	

}
