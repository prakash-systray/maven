package com.acculytixs.wayuparty.services.impl;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.entity.TokenInfo;
import com.acculytixs.wayuparty.services.TokenManager;
import com.acculytixs.wayuparty.services.WayupartyLoginService;
import com.acculytixs.wayuparty.util.SessionManager;

@Service
public class TokenManagerImpl implements TokenManager{

	@Override
	public void removeUserDetails(UserDetails userDetails,HttpSession session) {
		Map<String, UserDetails> validUsers = (Map<String, UserDetails>) session.getAttribute("validUsers");
		Map<UserDetails, TokenInfo> tokens = (Map<UserDetails, TokenInfo>) session.getAttribute("tokens");
		TokenInfo token = tokens.remove(userDetails);
		if (token != null) {
			validUsers.remove(token.getToken());
		}
	}

	@Override
	public UserDetails removeToken(String token,HttpSession session) {
		Map<String, UserDetails> validUsers = (Map<String, UserDetails>) session.getAttribute("validUsers");
		Map<UserDetails, TokenInfo> tokens = (Map<UserDetails, TokenInfo>) session.getAttribute("tokens");
		UserDetails userDetails = validUsers.remove(token);
		if (userDetails != null) {
			tokens.remove(userDetails);
		}
		return userDetails;
	}

	@Override
	public UserDetails getUserDetails(String token,HttpSession session) {
		Map<String, UserDetails> validUsers = (Map<String, UserDetails>) session.getAttribute("validUsers");
		if(validUsers == null || validUsers.isEmpty()){
			validUsers = new HashMap<>();
		}
		return validUsers.get(token);
	}

	@Override
	public Collection<TokenInfo> getUserTokens(UserDetails userDetails,HttpSession session) {
		Map<UserDetails, TokenInfo> tokens = (Map<UserDetails, TokenInfo>) session.getAttribute("tokens");
		return Arrays.asList(tokens.get(userDetails));
	}

	@Override
	public Map<String, UserDetails> getValidUsers(HttpSession session) {
		Map<String, UserDetails> validUsers = (Map<String, UserDetails>) session.getAttribute("validUsers");
		return Collections.unmodifiableMap(validUsers);
	}

	@Override
	public TokenInfo createNewToken(UserDetails userDetails, HttpServletRequest request,
			WayupartyLoginService wayupartyLoginService) {
		HttpSession session = request.getSession();
		Map<String, UserDetails> validUsers = (Map<String, UserDetails>) session.getAttribute("validUsers");
		Map<UserDetails, TokenInfo> tokens = (Map<UserDetails, TokenInfo>) session.getAttribute("tokens");
		if(validUsers == null){
			validUsers = new HashMap<>();
		}
		if(tokens == null){
			tokens = new HashMap<>();
		}
		
		String token;
		Collection<TokenInfo> tokenInfoList = null;
		TokenInfo tokenInfo = null;
		if(tokens != null && !tokens.isEmpty()){
			tokenInfoList = getUserTokens(userDetails,session);
		}
		
		if(tokenInfoList != null && !tokenInfoList.isEmpty() && !tokenInfoList.contains(null)){
			tokenInfo = tokenInfoList.iterator().next();
			token = tokenInfo.getToken();
		}
		else{
			do {
				token = generateToken();
			} while (validUsers.containsKey(token));
			 tokenInfo = new TokenInfo(token, userDetails);
			UserDetails previous = validUsers.put(token, userDetails);
			if (previous != null) {
				System.out.println(" *** SERIOUS PROBLEM HERE - we generated the same token (randomly?)!");
				return null;
			}
			tokens.put(userDetails, tokenInfo);
			UserDTO userDTO = wayupartyLoginService.getLoggedInUserDetailsByUserName(userDetails.getUsername());
			
			try {
				if(userDTO != null) {
					SessionManager.setSessionAttribute(request, "userId", userDTO.getUserId());
				}
			} catch (Exception e) {}
			
		}

		
		session.setAttribute("validUsers", validUsers);
		session.setAttribute("tokens", tokens);
		return tokenInfo;
	}
	
	private String generateToken() {
		byte[] tokenBytes = new byte[32];
		new SecureRandom().nextBytes(tokenBytes);
		return new String(Base64.encode(tokenBytes), StandardCharsets.UTF_8);
	}
	
	
}
