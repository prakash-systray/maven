package com.acculytixs.wayuparty.dto.services;

import java.io.Serializable;
import java.util.List;

public class ServicesInfoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String vendorName;
	
	private String location;
	
	private String longitude;
	
	private String latitude;
	
	private String currency;
	
	private String description;
	
	private String profileImage;
	
	private List<ServicesDTO> servicesList;
	
	

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public List<ServicesDTO> getServicesList() {
		return servicesList;
	}

	public void setServicesList(List<ServicesDTO> servicesList) {
		this.servicesList = servicesList;
	}
	

}
