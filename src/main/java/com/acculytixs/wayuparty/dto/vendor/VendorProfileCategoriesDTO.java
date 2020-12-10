package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.math.BigInteger;

public class VendorProfileCategoriesDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigInteger categoryId;
	
	private String categoryName;
	
	private String categoryType;
	
	private String categoriesIds;
	
	private String facilitiesIds;
	
	private String musicIds;
	
	private String cuisineIds;
	
	private String vendorUUID;
	
	private String isCategoryConfigured;

	public BigInteger getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(BigInteger categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoriesIds() {
		return categoriesIds;
	}

	public void setCategoriesIds(String categoriesIds) {
		this.categoriesIds = categoriesIds;
	}

	public String getFacilitiesIds() {
		return facilitiesIds;
	}

	public void setFacilitiesIds(String facilitiesIds) {
		this.facilitiesIds = facilitiesIds;
	}

	public String getMusicIds() {
		return musicIds;
	}

	public void setMusicIds(String musicIds) {
		this.musicIds = musicIds;
	}

	public String getCuisineIds() {
		return cuisineIds;
	}

	public void setCuisineIds(String cuisineIds) {
		this.cuisineIds = cuisineIds;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getIsCategoryConfigured() {
		return isCategoryConfigured;
	}

	public void setIsCategoryConfigured(String isCategoryConfigured) {
		this.isCategoryConfigured = isCategoryConfigured;
	}

	
	
	

}
