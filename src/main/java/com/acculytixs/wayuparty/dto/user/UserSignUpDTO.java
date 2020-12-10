package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;

public class UserSignUpDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String isValid;

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	
}
