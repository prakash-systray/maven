package com.acculytixs.wayuparty.security;

 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.services.WayupartyLoginService;


@Component
public class WayupartyAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private WayupartyLoginService wayupartyLoginService;
	
	@Autowired
	private WayupartyPasswordEncoder wayupartyPasswordEncoder;

	 
	
	@Override
    public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
  
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserDTO userDTO = wayupartyLoginService.getUserPassWordByUsername(username);
		if(userDTO != null && userDTO.getPassword() != null) {
			if(wayupartyPasswordEncoder.matches(password, userDTO.getPassword())) { 
				Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
				setAuths.add(new SimpleGrantedAuthority(userDTO.getUserRole()));
				List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>(setAuths);
				 return new UsernamePasswordAuthenticationToken(username, password,authsList);
				}else {
				throw new BadCredentialsException("login_password_error");
			}
		}else {
			throw new BadCredentialsException("login_password_error");
		}
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    

}
