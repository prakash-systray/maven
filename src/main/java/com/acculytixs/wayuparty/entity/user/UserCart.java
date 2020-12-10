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

import com.acculytixs.wayuparty.entity.services.VendorMasterService;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

@Entity
@Table(name = "user_cart")
public class UserCart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendors vendorId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_master_service_id")
	private VendorMasterService vendorMasterServiceId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "order_amount")
	private Double orderAmount;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "service_ordered_date")
	private Date serviceOrderedDate;
	
	@Column(name = "service_time_slot",columnDefinition = "TEXT default NULL")
	private String serviceTimeSlot;
	
	@Column(name = "package_menu_items",columnDefinition = "TEXT default NULL")
	private String packageMenuItems;
	
	
	@Column(name = "uuid")
	private String uuid;
	
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

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Vendors getVendorId() {
		return vendorId;
	}

	public void setVendorId(Vendors vendorId) {
		this.vendorId = vendorId;
	}

	public VendorMasterService getVendorMasterServiceId() {
		return vendorMasterServiceId;
	}

	public void setVendorMasterServiceId(VendorMasterService vendorMasterServiceId) {
		this.vendorMasterServiceId = vendorMasterServiceId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
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

	public Date getServiceOrderedDate() {
		return serviceOrderedDate;
	}

	public void setServiceOrderedDate(Date serviceOrderedDate) {
		this.serviceOrderedDate = serviceOrderedDate;
	}

	public String getServiceTimeSlot() {
		return serviceTimeSlot;
	}

	public void setServiceTimeSlot(String serviceTimeSlot) {
		this.serviceTimeSlot = serviceTimeSlot;
	}
	
	public String getPackageMenuItems() {
		return packageMenuItems;
	}

	public void setPackageMenuItems(String packageMenuItems) {
		this.packageMenuItems = packageMenuItems;
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
	

}
