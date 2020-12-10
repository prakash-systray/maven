package com.acculytixs.wayuparty.dto.user;

import java.io.Serializable;
import java.math.BigInteger;

public class CartDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigInteger masterServiceId;
	
	private String serviceName;
	
	private Double orderAmount;

	private Integer quantity;
	
	private Double totalAmount;
	
	private String cartUUID;
	
	private String serviceOrderDate;
	
	private String timeSlot;
	
	private String serviceImage;
	
	private String currency;
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public String getCartUUID() {
		return cartUUID;
	}

	public void setCartUUID(String cartUUID) {
		this.cartUUID = cartUUID;
	}
	
	public String getServiceOrderDate() {
		return serviceOrderDate;
	}

	public void setServiceOrderDate(String serviceOrderDate) {
		this.serviceOrderDate = serviceOrderDate;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigInteger getMasterServiceId() {
		return masterServiceId;
	}

	public void setMasterServiceId(BigInteger masterServiceId) {
		this.masterServiceId = masterServiceId;
	}

}
