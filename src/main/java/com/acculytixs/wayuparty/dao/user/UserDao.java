package com.acculytixs.wayuparty.dao.user;

import java.util.List;

import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.entity.user.User;

public interface UserDao {

	boolean isUserExistedByEmailId(String emailId) throws Exception;
	
	boolean isUserExistedByMobileNumber(String mobileNumber) throws Exception;
	
	UserDTO getUserDetailsByUUID(String userUUID) throws Exception;
	
	void saveUser(User user) throws Exception;
	
	VendorInfoDTO getVendorInfoDetails(String vendorUUID) throws Exception;
	
	List<String> getVendorCustomCategories(List<Long> categoriesIds) throws Exception;
	
	User getUserByUUID(String uuid) throws Exception;
	
	UserLoginDTO getLoginUserDetails(Long userId) throws Exception;
}
