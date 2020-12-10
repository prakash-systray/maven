package com.acculytixs.wayuparty.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

public class TokenInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final long created = System.currentTimeMillis();
	private final String token;
	private final UserDetails userDetails;
	// TODO expiration etc

	public TokenInfo(String token, UserDetails userDetails) {
		this.token = token;
		this.userDetails = userDetails;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "TokenInfo{" +
			"token='" + token + '\'' +
			", userDetails" + userDetails +
			", created=" + new Date(created) +
			'}';
	}
}
