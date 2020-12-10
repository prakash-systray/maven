package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.annotations.Required;

public class BottleCategoryInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Required(desc="categoryId")
	private String categoryId;
	
	@Required(desc="vendorUUID")
	private String vendorUUID;
	
	List<BottleDTO> bottleInfo;
	
	private String removedSubCategoriesUUID;
	

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public List<BottleDTO> getBottleInfo() {
		return bottleInfo;
	}

	public void setBottleInfo(List<BottleDTO> bottleInfo) {
		this.bottleInfo = bottleInfo;
	}

	public String getRemovedSubCategoriesUUID() {
		return removedSubCategoriesUUID;
	}

	public void setRemovedSubCategoriesUUID(String removedSubCategoriesUUID) {
		this.removedSubCategoriesUUID = removedSubCategoriesUUID;
	}
	
	
	
	

}
