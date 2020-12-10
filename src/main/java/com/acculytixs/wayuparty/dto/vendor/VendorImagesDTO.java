package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.util.FileInfo;

public class VendorImagesDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<FileInfo> fileInfo;
	
	private String imageType;
	
	private String vendorUUID;

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}
	
	
	
	
}
