package com.acculytixs.wayuparty.dto.services;

import java.util.List;

public class MenuOfferingItemsDTO {

	private String menuItem;
	
	private String menuItemUUID;
	
	private String itemsOffered;
	
	List<MenuItemsDTO> menuItemsList;
	

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}

	public String getMenuItemUUID() {
		return menuItemUUID;
	}

	public void setMenuItemUUID(String menuItemUUID) {
		this.menuItemUUID = menuItemUUID;
	}

	public String getItemsOffered() {
		return itemsOffered;
	}

	public void setItemsOffered(String itemsOffered) {
		this.itemsOffered = itemsOffered;
	}

	public List<MenuItemsDTO> getMenuItemsList() {
		return menuItemsList;
	}

	public void setMenuItemsList(List<MenuItemsDTO> menuItemsList) {
		this.menuItemsList = menuItemsList;
	}
	
	
}
