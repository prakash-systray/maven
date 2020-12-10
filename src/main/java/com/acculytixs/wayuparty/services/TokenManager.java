package com.acculytixs.wayuparty.services;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;

import com.acculytixs.wayuparty.entity.TokenInfo;

public interface TokenManager {
	
	TokenInfo createNewToken(UserDetails userDetails,HttpServletRequest request,WayupartyLoginService wayupartyLoginService);
	
	/** Removes all tokens for user. */
	void removeUserDetails(UserDetails userDetails,HttpSession session);

	/** Removes a single token. */
	UserDetails removeToken(String token,HttpSession session);

	/** Returns user details for a token. */
	UserDetails getUserDetails(String token,HttpSession session);

	/** Returns a collection with token information for a particular user. */
	Collection<TokenInfo> getUserTokens(UserDetails userDetails,HttpSession session);

	/** Returns a map from valid tokens to users. */
	Map<String, UserDetails> getValidUsers(HttpSession session);
}
