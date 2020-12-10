package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.math.BigInteger;

public class ServicesCategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigInteger categoryId;
	
	private String categoryName;
	
	private String categoryUUID;
	
	

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

	public String getCategoryUUID() {
		return categoryUUID;
	}

	public void setCategoryUUID(String categoryUUID) {
		this.categoryUUID = categoryUUID;
	}
	
	
	

}
