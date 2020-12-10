package com.acculytixs.wayuparty.dto.services;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.annotations.Required;
import com.acculytixs.wayuparty.util.FileInfo;

public class VendorEntryServiceDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Required(desc="entryType")
	private String entryType;
	
	@Required(desc="eventName")
	private String eventName;
	
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
	
	@Required(desc="startTime")
	private String startTime;
	
	@Required(desc="endTime")
	private String endTime;
	
	private List<FileInfo> fileInfo;
	
	@Required(desc="musicGenre")
	private String musicGenre;
	
	@Required(desc="artist")
	private String artist;
	
	@Required(desc="eventLocation")
	private String eventLocation;
	
	private String guestEntryTime;
	
	private String vendorUUID;
	
	private String serviceUUID;
	
	private String entryUUID;
	
	private String guestUUID;
	
	

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
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

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
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

	public String getEntryUUID() {
		return entryUUID;
	}

	public void setEntryUUID(String entryUUID) {
		this.entryUUID = entryUUID;
	}

	public String getGuestUUID() {
		return guestUUID;
	}

	public void setGuestUUID(String guestUUID) {
		this.guestUUID = guestUUID;
	}

	public String getGuestEntryTime() {
		return guestEntryTime;
	}

	public void setGuestEntryTime(String guestEntryTime) {
		this.guestEntryTime = guestEntryTime;
	}

	
}
