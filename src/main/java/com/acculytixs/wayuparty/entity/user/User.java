package com.acculytixs.wayuparty.entity.user;

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

import com.acculytixs.wayuparty.entity.WayupartyRoles;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendors vendorId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "email",columnDefinition = "TEXT default NULL")
	private String email;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "user_name",columnDefinition = "TEXT default NULL")
	private String userName;
	
	@Column(name = "password",columnDefinition = "TEXT default NULL")
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private WayupartyRoles roleId;
	
	@Column(name = "address",columnDefinition = "TEXT default NULL")
	private String address;
	
	@Column(name = "user_image",columnDefinition = "TEXT default NULL")
	private String userImage;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "user_status")
	private String userStatus;
	
	@Column(name = "is_email_verified")
	private String isEmailVerified;
	 
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "status")
	private Integer status;
	
	

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WayupartyRoles getRoleId() {
		return roleId;
	}

	public void setRoleId(WayupartyRoles roleId) {
		this.roleId = roleId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
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

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(String isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	

}
