package com.acculytixs.wayuparty.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.services.WayupartyLoginService;
import com.acculytixs.wayuparty.services.user.UserCartService;
import com.acculytixs.wayuparty.services.user.UserService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.SessionManager;


@Configuration
@EnableTransactionManagement
public class WayupartyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	 
	@Value("${appUrl}")
	private String appUrl;
	
	@Value("${googleMapsLocationApiKey}")
	private String googleMapsLocationApiKey;

	@Autowired
	WayupartyLoginService wayupartyLoginService;
	
	@Autowired
	UserCartService userCartService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		
		UserDTO userDTO = wayupartyLoginService.getLoggedInUserDetailsByUserName(authentication.getName());
		
		SessionManager.setSessionAttribute(request, "loginUserName",userDTO.getLoginUserName());
		SessionManager.setSessionAttribute(request, "loginUserFirstName",userDTO.getFirstName());
		SessionManager.setSessionAttribute(request, "loginUserLastName",userDTO.getLastName());
		SessionManager.setSessionAttribute(request, "loginUserUUId",userDTO.getUserUUID());
		SessionManager.setSessionAttribute(request, "loginUserId",userDTO.getUserId());
		SessionManager.setSessionAttribute(request, "loginUserImgUrl",userDTO.getUserImage());
		SessionManager.setSessionAttribute(request, "loginUserRoleDisplayName",userDTO.getUserRoleDisplayName());
		SessionManager.setSessionAttribute(request, "loginUserRole",userDTO.getUserRole());
		
		if(StringUtils.isNotBlank(userDTO.getVendorName()) && StringUtils.isNotBlank(userDTO.getVendorUUID())) {
			SessionManager.setSessionAttribute(request, "vendorName",userDTO.getVendorName());
			SessionManager.setSessionAttribute(request, "vendorUUID",userDTO.getVendorUUID());
		}
		
		if(StringUtils.isBlank(userDTO.getUserStatus()) || userDTO.getUserStatus().equalsIgnoreCase(Constants.USER_NOT_LOGGED_IN)) {
			try {
				User user = userService.getUserByUUID(userDTO.getUserUUID());
				user.setUserStatus(Constants.USER_LOGGED_IN);
				userService.saveUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		SessionManager.setSessionAttribute(request, "googleMapsLocationApiKey",googleMapsLocationApiKey);
		
		try {
			Long cartCount = userCartService.getUserCartCount(userDTO.getUserId().longValue());
			SessionManager.setSessionAttribute(request, "cartCount",cartCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		SessionManager.setSessionAttribute(request, "appUrl",appUrl);
		 
		response.sendRedirect("/dashboard");
	}

	 
}
