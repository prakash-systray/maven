package com.acculytixs.wayuparty.entity.services;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

@Entity
@Table(name = "vendor_master_service")
public class VendorMasterService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id", nullable = false)
	private Vendors vendorId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id", nullable = false)
	private WayupartySerivces serviceId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private WayupartyServiceCategory categoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_category_id", nullable = true)
	private WayupartyServiceSubCategory subCategoryId;
	
	@Column(name = "actual_price")
	private Double actualPrice;
	
	@Column(name = "offer_price")
	private Double offerPrice;
	
	@Column(name = "allowed")
	private Integer allowed;
	
	@Column(name = "max_booking_allowed")
	private Integer maxBookingAllowed;
	
	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "service_start_date")
	private Date serviceStartDate;
	
	@Column(name = "service_end_date")
	private Date serviceEndDate;
	
	@Column(name = "service_time_slots",columnDefinition = "LONGTEXT")
	private String serviceTimeSlots;
	
	@Column(name = "service_description",columnDefinition = "LONGTEXT")
	private String serviceDescription;
	
	@Column(name = "service_offer",columnDefinition = "LONGTEXT")
	private String serviceOffer;
	
	@Column(name = "terms_and_conditions",columnDefinition = "LONGTEXT")
	private String termsAndConditions;
	
	@Column(name = "service_image",columnDefinition = "LONGTEXT")
	private String serviceImage;
	
	@Column(name = "music_genre",columnDefinition = "LONGTEXT")
	private String musicGenre;
	
	@Column(name = "event_location",columnDefinition = "LONGTEXT")
	private String eventLocation;
	
	@Column(name = "artist",columnDefinition = "LONGTEXT")
	private String artist;
	
	@Column(name = "package_menu",columnDefinition = "LONGTEXT")
	private String packageMenu;
	
	@Column(name = "guest_entry_time")
	private String guestEntryTime;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "status")
	private Integer status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = true)
	private User createdBy;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vendors getVendorId() {
		return vendorId;
	}

	public void setVendorId(Vendors vendorId) {
		this.vendorId = vendorId;
	}
	
	public WayupartySerivces getServiceId() {
		return serviceId;
	}

	public void setServiceId(WayupartySerivces serviceId) {
		this.serviceId = serviceId;
	}

	public WayupartyServiceCategory getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(WayupartyServiceCategory categoryId) {
		this.categoryId = categoryId;
	}

	public WayupartyServiceSubCategory getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(WayupartyServiceSubCategory subCategoryId) {
		this.subCategoryId = subCategoryId;
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

	public Integer getAllowed() {
		return allowed;
	}

	public void setAllowed(Integer allowed) {
		this.allowed = allowed;
	}
	
	public Integer getMaxBookingAllowed() {
		return maxBookingAllowed;
	}

	public void setMaxBookingAllowed(Integer maxBookingAllowed) {
		this.maxBookingAllowed = maxBookingAllowed;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public String getServiceTimeSlots() {
		return serviceTimeSlots;
	}

	public void setServiceTimeSlots(String serviceTimeSlots) {
		this.serviceTimeSlots = serviceTimeSlots;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceOffer() {
		return serviceOffer;
	}

	public void setServiceOffer(String serviceOffer) {
		this.serviceOffer = serviceOffer;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	
	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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
