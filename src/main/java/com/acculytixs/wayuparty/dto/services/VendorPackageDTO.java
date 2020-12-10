package com.acculytixs.wayuparty.dto.services;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.annotations.Required;
import com.acculytixs.wayuparty.util.FileInfo;

public class VendorPackageDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Required(desc="packageType")
	private String packageType;
	
	@Required(desc="packageName")
	private String packageName;
	
	@Required(desc="allowed")
	private Integer allowed;
	
	@Required(desc="actualPrice")
	private Double actualPrice;
	
	@Required(desc="offerPrice")
	private Double offerPrice;
	
	@Required(desc="startDate")
	private String startDate;
	
	@Required(desc="endDate")
	private String endDate;
	
	@Required(desc="description")
	private String description;
	
	@Required(desc="termsAndConditions")
	private String termsAndConditions;
	
	private List<TimeSlotSchedulerInfo> timeSchedulerInfo;
	
	private List<FileInfo> fileInfo;
	
	private String packageUUID;
	
	private String vendorUUID;
	
	private String serviceUUID;


	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public Integer getAllowed() {
		return allowed;
	}

	public void setAllowed(Integer allowed) {
		this.allowed = allowed;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public List<TimeSlotSchedulerInfo> getTimeSchedulerInfo() {
		return timeSchedulerInfo;
	}

	public void setTimeSchedulerInfo(List<TimeSlotSchedulerInfo> timeSchedulerInfo) {
		this.timeSchedulerInfo = timeSchedulerInfo;
	}

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}
	
	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getServiceUUID() {
		return serviceUUID;
	}

	public void setServiceUUID(String serviceUUID) {
		this.serviceUUID = serviceUUID;
	}

	public String getPackageUUID() {
		return packageUUID;
	}

	public void setPackageUUID(String packageUUID) {
		this.packageUUID = packageUUID;
	}
	
	
	
}
