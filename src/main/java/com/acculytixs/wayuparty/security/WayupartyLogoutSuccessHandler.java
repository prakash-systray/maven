package com.acculytixs.wayuparty.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
 
@Component
public class WayupartyLogoutSuccessHandler implements LogoutSuccessHandler{
	
	 
	
	@Value("${appLoginUrl}")
	private String appLoginUrl;
	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		    String allowed = request.getParameter("allowed");
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(appLoginUrl+(StringUtils.isNotEmpty(allowed) ? "?allowed=false" : ""));
		
	}
	
}