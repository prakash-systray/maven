package com.acculytixs.wayuparty.dto.services;

import java.math.BigInteger;
import java.util.List;

public class PackageMenuOfferingDTO {

	private BigInteger menuItemId;
	
	private String menuItem;
	
	List<PackageMenuItemsDTO> menuItemsList;
	
	private String menuItemUUID;
	
	private String vendorUUID;
	
	private String removedMenuItems;
	
	private String itemsOffered;
	
	

	public BigInteger getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(BigInteger menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}

	public List<PackageMenuItemsDTO> getMenuItemsList() {
		return menuItemsList;
	}

	public void setMenuItemsList(List<PackageMenuItemsDTO> menuItemsList) {
		this.menuItemsList = menuItemsList;
	}

	public String getMenuItemUUID() {
		return menuItemUUID;
	}

	public void setMenuItemUUID(String menuItemUUID) {
		this.menuItemUUID = menuItemUUID;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getRemovedMenuItems() {
		return removedMenuItems;
	}

	public void setRemovedMenuItems(String removedMenuItems) {
		this.removedMenuItems = removedMenuItems;
	}

	public String getItemsOffered() {
		return itemsOffered;
	}

	public void setItemsOffered(String itemsOffered) {
		this.itemsOffered = itemsOffered;
	}
	
	
}
