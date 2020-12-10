package com.acculytixs.wayuparty.dto.services;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class VendorServicesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigInteger serviceId;
	
	private BigInteger vendorId;
	
	private String service;
	
	private String category;
	
	private String subCategory;
	
	private Double actualPrice;
	
	private Double offerPrice;
	
	private String currency;
	
	private String serviceStartDate;
	
	private String serviceEndDate;
	
	private String masterServiceUUID;
	
	private String serviceImage;
	
	private String serviceTimeSlots;
	
	private List<TimeSlotSchedulerInfo> timeSlotList;
	
	private List<ServiceDateDTO> serviceDates;
	
	private List<MenuOfferingItemsDTO> packageMenuItems;
	
	private Integer allowed;
	
	private String termsAndConditions;
	
	private String startDate;
	
	private String endDate;
	
	private String description;
	
	private String serviceOffer;
	
	private BigInteger categoryId;
	
	private BigInteger subCategoryId;
	
	private String eventLocation;
	
	private String musicGenre;
	
	private String artist;
	
	private String packageMenu;
	
	private String guestEntryTime;

	public BigInteger getServiceId() {
		return serviceId;
	}

	public void setServiceId(BigInteger serviceId) {
		this.serviceId = serviceId;
	}

	public BigInteger getVendorId() {
		return vendorId;
	}

	public void setVendorId(BigInteger vendorId) {
		this.vendorId = vendorId;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
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
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public String getMasterServiceUUID() {
		return masterServiceUUID;
	}

	public void setMasterServiceUUID(String masterServiceUUID) {
		this.masterServiceUUID = masterServiceUUID;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public String getServiceTimeSlots() {
		return serviceTimeSlots;
	}

	public void setServiceTimeSlots(String serviceTimeSlots) {
		this.serviceTimeSlots = serviceTimeSlots;
	}

	public List<TimeSlotSchedulerInfo> getTimeSlotList() {
		return timeSlotList;
	}

	public void setTimeSlotList(List<TimeSlotSchedulerInfo> timeSlotList) {
		this.timeSlotList = timeSlotList;
	}

	public List<ServiceDateDTO> getServiceDates() {
		return serviceDates;
	}

	public void setServiceDates(List<ServiceDateDTO> serviceDates) {
		this.serviceDates = serviceDates;
	}
	
	public List<MenuOfferingItemsDTO> getPackageMenuItems() {
		return packageMenuItems;
	}

	public void setPackageMenuItems(List<MenuOfferingItemsDTO> packageMenuItems) {
		this.packageMenuItems = packageMenuItems;
	}

	public Integer getAllowed() {
		return allowed;
	}

	public void setAllowed(Integer allowed) {
		this.allowed = allowed;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
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

	public BigInteger getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(BigInteger categoryId) {
		this.categoryId = categoryId;
	}

	public BigInteger getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(BigInteger subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getServiceOffer() {
		return serviceOffer;
	}

	public void setServiceOffer(String serviceOffer) {
		this.serviceOffer = serviceOffer;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getPackageMenu() {
		return packageMenu;
	}

	public void setPackageMenu(String packageMenu) {
		this.packageMenu = packageMenu;
	}

	public String getGuestEntryTime() {
		return guestEntryTime;
	}

	public void setGuestEntryTime(String guestEntryTime) {
		this.guestEntryTime = guestEntryTime;
	}

	

}
