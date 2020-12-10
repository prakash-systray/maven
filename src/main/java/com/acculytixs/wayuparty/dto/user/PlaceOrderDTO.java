package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;

import com.acculytixs.wayuparty.annotations.Required;

public class PlaceOrderDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Required(desc="userUUID")
	private String userUUID;
	
	@Required(desc="cartItems")
	private String cartItems;
	
	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getCartItems() {
		return cartItems;
	}

	public void setCartItems(String cartItems) {
		this.cartItems = cartItems;
	}


}
