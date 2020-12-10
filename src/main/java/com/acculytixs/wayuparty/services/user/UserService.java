package com.acculytixs.wayuparty.services.user;

import java.util.List;

import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserProfileDTO;
import com.acculytixs.wayuparty.dto.user.UserRegistrationDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.entity.user.User;

public interface UserService {
	
	boolean isUserExistedByEmailId(String emailId) throws Exception;
	
	boolean isUserExistedByMobileNumber(String mobileNumber) throws Exception;
	
	UserDTO getUserDetailsByUUID(String userUUID) throws Exception;
	
	String saveNewUserRegistration(UserRegistrationDTO userRegistrationDTO) throws Exception;
	
	VendorInfoDTO getVendorInfoDetails(String vendorUUID) throws Exception;
	
	List<String> getVendorCustomCategories(List<Long> categoriesIds) throws Exception;
	
	String saveUserProfile(UserProfileDTO userProfileDTO) throws Exception;
	
	User getUserByUUID(String uuid) throws Exception;
	
	String resetUserPassword(String password, String userUUID) throws Exception;
	
	UserLoginDTO getLoginUserDetails(Long userId) throws Exception;
	
	void saveUser(User user) throws Exception;

}
