package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;

import com.acculytixs.wayuparty.annotations.Required;

public class VendorCustomDetailsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String workingHours;
	
	@Required(desc="termsAndConditions")
	private String termsAndConditions;
	
	private String sliderImages;
	
	private String galleryImages;
	
	private String menuImages;
	
	private String vendorVideos;
	
	

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public String getSliderImages() {
		return sliderImages;
	}

	public void setSliderImages(String sliderImages) {
		this.sliderImages = sliderImages;
	}

	public String getGalleryImages() {
		return galleryImages;
	}

	public void setGalleryImages(String galleryImages) {
		this.galleryImages = galleryImages;
	}

	public String getMenuImages() {
		return menuImages;
	}

	public void setMenuImages(String menuImages) {
		this.menuImages = menuImages;
	}

	public String getVendorVideos() {
		return vendorVideos;
	}

	public void setVendorVideos(String vendorVideos) {
		this.vendorVideos = vendorVideos;
	}
	
	

}
