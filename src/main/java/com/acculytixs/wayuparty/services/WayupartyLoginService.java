package com.acculytixs.wayuparty.services;

import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserSignUpDTO;

public interface WayupartyLoginService {

	
	  UserDTO getUserPassWordByUsername(String userName);
	  
	  UserDTO getLoggedInUserDetailsByUserName(String userName);
	  
	  UserSignUpDTO isValidUserName(String userName);
	 
	  UserSignUpDTO isValidUserMobile(String mobileNumber);
	 
}
