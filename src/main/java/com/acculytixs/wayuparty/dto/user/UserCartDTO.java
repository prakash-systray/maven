package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;

public class UserCartDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private Long serviceId;
	
	private Long vendorId;
	
	private String serviceOrderDate;
	
	private String timeslot;
	
	private Double orderAmount;
	
	private Integer quantity;
	
	private Double totalAmount;
	
	private String currency;
	
	private String packageMenuItems;
	
	private String userUUID;
	
	private String vendorUUID;
	
	private String masterServiceUUID;
	
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getServiceOrderDate() {
		return serviceOrderDate;
	}

	public void setServiceOrderDate(String serviceOrderDate) {
		this.serviceOrderDate = serviceOrderDate;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getPackageMenuItems() {
		return packageMenuItems;
	}

	public void setPackageMenuItems(String packageMenuItems) {
		this.packageMenuItems = packageMenuItems;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getMasterServiceUUID() {
		return masterServiceUUID;
	}

	public void setMasterServiceUUID(String masterServiceUUID) {
		this.masterServiceUUID = masterServiceUUID;
	}

}
