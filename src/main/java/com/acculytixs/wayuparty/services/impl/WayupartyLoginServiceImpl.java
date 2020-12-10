package com.acculytixs.wayuparty.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.WayupartyLoginDao;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserSignUpDTO;
import com.acculytixs.wayuparty.services.WayupartyLoginService;

@Service
public class WayupartyLoginServiceImpl implements WayupartyLoginService{
	
	@Autowired
	WayupartyLoginDao wayupartyLoginDao;

	@Override
	public UserDTO getUserPassWordByUsername(String userName) {
		return wayupartyLoginDao.getUserPassWordByUsername(userName);
	}


	@Override
	public UserDTO getLoggedInUserDetailsByUserName(String userName) {
		return wayupartyLoginDao.getLoggedInUserDetailsByUserName(userName);
	}

	@Override
	public UserSignUpDTO isValidUserName(String userName) {
		return wayupartyLoginDao.isValidUserName(userName);
	}

	@Override
	public UserSignUpDTO isValidUserMobile(String mobileNumber) {
		return wayupartyLoginDao.isValidUserMobile(mobileNumber);
	}

	

}
