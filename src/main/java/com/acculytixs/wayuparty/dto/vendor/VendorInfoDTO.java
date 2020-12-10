package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.util.List;

public class VendorInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String vendorName;
	
	private String vendorUUID;
	
	private String vendorEmail;
	
	private String vendorMobile;
	
	private String vendorProfileImg;
	
	private Integer establishedYear;
	
	private Integer vendorCapacity;
	
	private Integer costForTwoPeople;
	
	private String vendorCode;
	
	private String vendorDescription;
	
	private String bestSellingItems;
	
	private String location;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String pincode;
	
	private String latitude;
	
	private String longitude;
	
	private String phoneNumber;
	
	private String vendorAddress;
	
	private String termsAndConditions;
	
	private List<String> categoriesList;
	
	private List<String> facilitiesList;
	
	private List<String> musicList;
	
	private List<String> cuisineList;
	
	List<TimeSchedulerInfo> workingHoursList;
	
	private List<String> menuList;
	
	private List<String> sliderList;
	
	private List<String> galleryList;
	
	private List<String> videoList;
	
	private String categories;
	
	private String facilities;
	
	private String music;
	
	private String cuisine;
	
	private String menus;
	
	private String sliders;
	
	private String gallery;
	
	private String videos;
	
	private String workingHours;


	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getVendorEmail() {
		return vendorEmail;
	}

	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	public String getVendorMobile() {
		return vendorMobile;
	}

	public void setVendorMobile(String vendorMobile) {
		this.vendorMobile = vendorMobile;
	}

	public String getVendorProfileImg() {
		return vendorProfileImg;
	}

	public void setVendorProfileImg(String vendorProfileImg) {
		this.vendorProfileImg = vendorProfileImg;
	}

	public Integer getEstablishedYear() {
		return establishedYear;
	}

	public void setEstablishedYear(Integer establishedYear) {
		this.establishedYear = establishedYear;
	}

	public Integer getVendorCapacity() {
		return vendorCapacity;
	}

	public void setVendorCapacity(Integer vendorCapacity) {
		this.vendorCapacity = vendorCapacity;
	}

	public Integer getCostForTwoPeople() {
		return costForTwoPeople;
	}

	public void setCostForTwoPeople(Integer costForTwoPeople) {
		this.costForTwoPeople = costForTwoPeople;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorDescription() {
		return vendorDescription;
	}

	public void setVendorDescription(String vendorDescription) {
		this.vendorDescription = vendorDescription;
	}

	public String getBestSellingItems() {
		return bestSellingItems;
	}

	public void setBestSellingItems(String bestSellingItems) {
		this.bestSellingItems = bestSellingItems;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	
	public List<String> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(List<String> categoriesList) {
		this.categoriesList = categoriesList;
	}

	public List<String> getFacilitiesList() {
		return facilitiesList;
	}

	public void setFacilitiesList(List<String> facilitiesList) {
		this.facilitiesList = facilitiesList;
	}

	public List<String> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<String> musicList) {
		this.musicList = musicList;
	}

	public List<String> getCuisineList() {
		return cuisineList;
	}

	public void setCuisineList(List<String> cuisineList) {
		this.cuisineList = cuisineList;
	}

	public List<TimeSchedulerInfo> getWorkingHoursList() {
		return workingHoursList;
	}

	public void setWorkingHoursList(List<TimeSchedulerInfo> workingHoursList) {
		this.workingHoursList = workingHoursList;
	}

	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

	public List<String> getSliderList() {
		return sliderList;
	}

	public void setSliderList(List<String> sliderList) {
		this.sliderList = sliderList;
	}

	public List<String> getGalleryList() {
		return galleryList;
	}

	public void setGalleryList(List<String> galleryList) {
		this.galleryList = galleryList;
	}

	public List<String> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<String> videoList) {
		this.videoList = videoList;
	}
	
	public String getWorkingHours() {
		return workingHours;
	}
	
	public String getCategories() {
		return categories;
	}

	public String getFacilities() {
		return facilities;
	}

	public String getMusic() {
		return music;
	}

	public String getCuisine() {
		return cuisine;
	}

	public String getMenus() {
		return menus;
	}

	public String getSliders() {
		return sliders;
	}

	public String getGallery() {
		return gallery;
	}

	public String getVideos() {
		return videos;
	}
	

}
