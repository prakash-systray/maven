package com.acculytixs.wayuparty.entity.vendor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.acculytixs.wayuparty.entity.user.User;

@Entity
@Table(name = "vendors")
public class Vendors implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "vendor_name",columnDefinition = "TEXT default NULL")
	private String vendorName;
	
	@Column(name = "vendor_code")
	private String vendorCode;
	
	@Column(name = "vendor_capacity")
	private Integer vendorCapacity;
	
	@Column(name = "established_year")
	private Integer estabilshedYear;
	
	@Column(name = "vendor_email",columnDefinition = "TEXT default NULL")
	private String vendorEmail;
	
	@Column(name = "vendor_mobile")
	private String vendorMobile;
	
	@Column(name = "vendor_description",columnDefinition = "TEXT default NULL")
	private String vendorDescription;
	
	@Column(name = "best_selling_items",columnDefinition = "TEXT default NULL")
	private String bestSellingItems;
	
	@Column(name = "cost_for_two_people")
	private Integer costForTwoPeople;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "location",columnDefinition = "TEXT default NULL")
	private String location;
	
	@Column(name = "address",columnDefinition = "TEXT default NULL")
	private String address;
	
	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "profile_image",columnDefinition = "TEXT default NULL")
	private String profileImage;
	
	@Column(name = "vendor_categories",columnDefinition = "TEXT default NULL")
	private String vendorCategories;
	
	@Column(name = "vendor_facilities",columnDefinition = "TEXT default NULL")
	private String vendorFacilities;
	
	@Column(name = "vendor_music_genre",columnDefinition = "TEXT default NULL")
	private String vendorMusicGenre;
	
	@Column(name = "vendor_cuisine",columnDefinition = "TEXT default NULL")
	private String vendorCuisine;
	
	@Column(name = "terms_conditions",columnDefinition = "LONGTEXT default NULL")
	private String termsAndConditions;
	
	@Column(name = "working_hours",columnDefinition = "LONGTEXT default NULL")
	private String workingHours;
	
	@Column(name = "slider_images",columnDefinition = "LONGTEXT default NULL")
	private String sliderImages;
	
	@Column(name = "gallery_images",columnDefinition = "LONGTEXT default NULL")
	private String galleryImages;
	
	@Column(name = "menu_images",columnDefinition = "LONGTEXT default NULL")
	private String menuImages;
	
	@Column(name = "vendor_videos",columnDefinition = "LONGTEXT default NULL")
	private String vendorVideos;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "status")
	private Integer status;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(mappedBy = "vendorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<User> user = new HashSet<User>(0);
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(mappedBy = "vendorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<VendorBankAccount> vendorBankAccount = new HashSet<VendorBankAccount>(0);
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public Integer getVendorCapacity() {
		return vendorCapacity;
	}

	public void setVendorCapacity(Integer vendorCapacity) {
		this.vendorCapacity = vendorCapacity;
	}

	public Integer getEstabilshedYear() {
		return estabilshedYear;
	}

	public void setEstabilshedYear(Integer estabilshedYear) {
		this.estabilshedYear = estabilshedYear;
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

	public Integer getCostForTwoPeople() {
		return costForTwoPeople;
	}

	public void setCostForTwoPeople(Integer costForTwoPeople) {
		this.costForTwoPeople = costForTwoPeople;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Set<VendorBankAccount> getVendorBankAccount() {
		return vendorBankAccount;
	}

	public void setVendorBankAccount(Set<VendorBankAccount> vendorBankAccount) {
		this.vendorBankAccount = vendorBankAccount;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getVendorCategories() {
		return vendorCategories;
	}

	public void setVendorCategories(String vendorCategories) {
		this.vendorCategories = vendorCategories;
	}

	public String getVendorFacilities() {
		return vendorFacilities;
	}

	public void setVendorFacilities(String vendorFacilities) {
		this.vendorFacilities = vendorFacilities;
	}

	public String getVendorMusicGenre() {
		return vendorMusicGenre;
	}

	public void setVendorMusicGenre(String vendorMusicGenre) {
		this.vendorMusicGenre = vendorMusicGenre;
	}

	public String getVendorCuisine() {
		return vendorCuisine;
	}

	public void setVendorCuisine(String vendorCuisine) {
		this.vendorCuisine = vendorCuisine;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
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
