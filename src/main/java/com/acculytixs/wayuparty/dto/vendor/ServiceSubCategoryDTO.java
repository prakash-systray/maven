package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.math.BigInteger;

public class ServiceSubCategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String subCategoryName;
	
	private BigInteger subCategoryId;
	
	private String subCategoryUUID;

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
	public BigInteger getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(BigInteger subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryUUID() {
		return subCategoryUUID;
	}

	public void setSubCategoryUUID(String subCategoryUUID) {
		this.subCategoryUUID = subCategoryUUID;
	}
	

}
