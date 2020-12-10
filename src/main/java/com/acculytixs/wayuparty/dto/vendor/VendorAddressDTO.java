package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;

import com.acculytixs.wayuparty.annotations.Required;

public class VendorAddressDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Required(desc="vendorLocation")
	private String vendorLocation;
	
	@Required(desc="country")
	private String country;
	
	@Required(desc="state")
	private String state;
	
	@Required(desc="city")
	private String city;

	@Required(desc="pincode")
	private String pincode;
	
	@Required(desc="latitude")
	private String latitude;
	
	@Required(desc="longitude")
	private String longitude;
	
	@Required(desc="phoneNumber")
	private String phoneNumber;
	
	@Required(desc="vendorAddress")
	private String vendorAddress;
	
	private String vendorUUID;
	
	

	public String getVendorLocation() {
		return vendorLocation;
	}

	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
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

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}
	
	

}
