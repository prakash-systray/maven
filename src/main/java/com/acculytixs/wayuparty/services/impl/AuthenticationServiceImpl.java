package com.acculytixs.wayuparty.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.entity.TokenInfo;
import com.acculytixs.wayuparty.services.AuthenticationService;
import com.acculytixs.wayuparty.services.TokenManager;
import com.acculytixs.wayuparty.services.WayupartyLoginService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	

	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private  TokenManager tokenManager;
	
	@Autowired
	WayupartyLoginService wayupartyLoginService;

	@Override
	public TokenInfo authenticate(String login, String password,HttpServletRequest request,HttpSession session) throws AuthenticationException {
		
		
		   Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
		
			authentication = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			if (authentication.getPrincipal() != null) {
				String userName =  (String) authentication.getPrincipal();
				UserDetails userDetails = loadUserByUsername(userName);
				TokenInfo newToken = tokenManager.createNewToken(userDetails,request,wayupartyLoginService);
				if (newToken == null) {
					return null;
				}
				return newToken;
			}
			return null;	
	}
	
	
	
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		UserDTO userDTO = wayupartyLoginService.getUserPassWordByUsername(username);
		if(userDTO != null && userDTO.getPassword() != null) {
			userDTO.setUserName(username);
				Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
				setAuths.add(new SimpleGrantedAuthority(userDTO.getUserRole()));
				List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>(setAuths);
				return buildUserForAuthentication(userDTO, authsList);
				}else {
				return null;
		}
		
	}
	
	
		private User buildUserForAuthentication(UserDTO user, List<GrantedAuthority> authorities) {
			return new User(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
		}

		 

	@Override
	public boolean checkToken(String token,HttpSession session) {
		UserDetails userDetails = tokenManager.getUserDetails(token,session);
		if (userDetails == null) {
			return false;
		}

		Authentication securityToken = new PreAuthenticatedAuthenticationToken(
			userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(securityToken);

		return true;
	}

	@Override
	public void logout(String token,HttpSession session) {
		UserDetails logoutUser = tokenManager.removeToken(token,session);
		session.invalidate();
		SecurityContextHolder.clearContext();
	}

	@Override
	public UserDetails currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return (UserDetails) authentication.getPrincipal();
	}

	
}
