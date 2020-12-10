package com.acculytixs.wayuparty.dto.services;

import java.math.BigInteger;

public class PackageMenuItemsDTO {

	private BigInteger itemId;
	
	private String itemName;
	
	private String itemUUID;
	
	private String isItemSelected;
	
	

	public BigInteger getItemId() {
		return itemId;
	}

	public void setItemId(BigInteger itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemUUID() {
		return itemUUID;
	}

	public void setItemUUID(String itemUUID) {
		this.itemUUID = itemUUID;
	}

	public String getIsItemSelected() {
		return isItemSelected;
	}

	public void setIsItemSelected(String isItemSelected) {
		this.isItemSelected = isItemSelected;
	}
	
	
}
