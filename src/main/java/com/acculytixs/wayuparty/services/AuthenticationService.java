package com.acculytixs.wayuparty.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;

import com.acculytixs.wayuparty.entity.TokenInfo;

public interface AuthenticationService {

	/**
	 * Authenticates the user and returns valid token. If anything fails, {@code null} is returned instead.
	 * Prepares {@link org.springframework.security.core.context.SecurityContext} if authentication succeeded.
	 */
	TokenInfo authenticate(String login, String password,HttpServletRequest request,HttpSession session);

	/**
	 * Checks the authentication token and if it is valid prepares
	 * {@link org.springframework.security.core.context.SecurityContext} and returns true.
	 */
	boolean checkToken(String token,HttpSession session);

	/** Logouts the user - token is invalidated/forgotten. */
	void logout(String token,HttpSession session);

	/** Returns current user or {@code null} if there is no authentication or user is anonymous. */
	UserDetails currentUser();

}
