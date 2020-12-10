package com.acculytixs.wayuparty;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.GenericFilterBean;

import com.acculytixs.wayuparty.entity.TokenInfo;
import com.acculytixs.wayuparty.services.AuthenticationService;


@Component
@EnableTransactionManagement
@ComponentScan(basePackages = "com.acculytixs.wayuparty")
public class CustomTokenAuthenticationFilter extends GenericFilterBean {


	private static final String REQUEST_ATTR_DO_NOT_CONTINUE = "CustomTokenAuthenticationFilter-doNotContinue";
	
	@Autowired
	AuthenticationService authenticationService;

	private  String logoutLink = "/rest/logout";
	
	private static final String HEADER_TOKEN = "X-CustomToken";
	private static final String HEADER_USERNAME = "X-Username";
	private static final String HEADER_PASSWORD = "X-Password";
	private static final String HEADER_LOGOUT_STATUS = "LOGOUT_STATUS";
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean authenticated = checkToken(httpRequest, httpResponse);
		if (authenticated) {
			checkLogout(httpRequest,httpResponse);
		}
		checkLogin(httpRequest, httpResponse,chain);
		if (canRequestProcessingContinue(httpRequest)) {
			chain.doFilter(request, response);
		}
		
	}
	private void checkLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse,FilterChain chain) throws IOException, ServletException {
		String username = httpRequest.getHeader(HEADER_USERNAME);
		String password = httpRequest.getHeader(HEADER_PASSWORD);
	   if (username != null && password != null) {
			checkUsernameAndPassword(username, password,httpRequest, httpResponse);
			doNotContinueWithRequestProcessing(httpRequest);
			httpRequest.setAttribute(REQUEST_ATTR_DO_NOT_CONTINUE, null);
			checkToken(httpRequest, httpResponse);
			
		}
	}
	private void checkUsernameAndPassword(String username, String password,HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		try{
			TokenInfo tokenInfo = authenticationService.authenticate(username, password,httpRequest,httpRequest.getSession());
			if (tokenInfo != null) {
				httpResponse.setHeader(HEADER_TOKEN, tokenInfo.getToken());
			}
			else {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		catch (AuthenticationException ae) {
			if(ae.getMessage().equalsIgnoreCase("login_unRegistered_error")){
				httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			else if(ae.getMessage().equalsIgnoreCase("login_inActive_error")){
				httpResponse.sendError(HttpServletResponse.SC_MOVED_TEMPORARILY);
			}
		}
	}

	private boolean checkToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String token = httpRequest.getHeader(HEADER_TOKEN);
		if (token == null) {
			token = httpResponse.getHeader(HEADER_TOKEN);
			 if (token == null) {
				 return false;
			}
		}

		if (authenticationService.checkToken(token,httpRequest.getSession())) {
			return true;
		} else {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			doNotContinueWithRequestProcessing(httpRequest);
		}
		return false;
	}

   private void checkLogout(HttpServletRequest httpRequest,HttpServletResponse httpResponse) {
		if (currentLink(httpRequest).equals(logoutLink)) {
			String token = httpRequest.getHeader(HEADER_TOKEN);
			authenticationService.logout(token,httpRequest.getSession());
			doNotContinueWithRequestProcessing(httpRequest);
			httpResponse.setHeader(HEADER_LOGOUT_STATUS,"success");
		}
	}

	private String currentLink(HttpServletRequest httpRequest) {
		if (httpRequest.getPathInfo() == null) {
			return httpRequest.getServletPath();
		}
		return httpRequest.getServletPath() + httpRequest.getPathInfo();
	}

	private void doNotContinueWithRequestProcessing(HttpServletRequest httpRequest) {
		httpRequest.setAttribute(REQUEST_ATTR_DO_NOT_CONTINUE, "");
	}

	private boolean canRequestProcessingContinue(HttpServletRequest httpRequest) {
		return httpRequest.getAttribute(REQUEST_ATTR_DO_NOT_CONTINUE) == null;
	}
 

}
