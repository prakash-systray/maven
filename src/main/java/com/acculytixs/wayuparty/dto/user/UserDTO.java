package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;
import java.math.BigInteger;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 private BigInteger userId;
	 
	 private String firstName;
	 
	 private String lastName;
	 
	 private String loginUserName;
	 
	 private String userName;
	 
	 private  String password;
	 
	 private String userRole;
	 
	 private String userRoleDisplayName;
	 
	 private String userImage;
	 
	 private String userUUID;
	 
	 private String createdDate;
	 
	 private String userEmail;
	 
	 private String userMobile;
	 
	 private String dob;
	 
	 private String vendorName;
	 
	 private String vendorUUID;
	 
	 private String userStatus;


	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUserRoleDisplayName() {
		return userRoleDisplayName;
	}

	public void setUserRoleDisplayName(String userRoleDisplayName) {
		this.userRoleDisplayName = userRoleDisplayName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	
	
}
