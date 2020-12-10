package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;

import com.acculytixs.wayuparty.annotations.Required;

public class UserRegistrationDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Required(desc="loginUserName")
	private String loginUserName;
	
	@Required(desc="email")
	private String email;
	
	@Required(desc="mobile")
	private String mobile;
	
	@Required(desc="password")
	private String password;
	
	
	

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
