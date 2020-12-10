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
@Table(name = "package_menu_offering_items")
public class PackageMenuOfferingItems implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendors vendorId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_offering_id")
	private PackageMenuOffering menuOfferingId;
	
	@Column(name = "menu_offering_item",columnDefinition = "TEXT default NULL")
	private String menuOfferingItem;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private User createdBy;
	
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

	public PackageMenuOffering getMenuOfferingId() {
		return menuOfferingId;
	}

	public void setMenuOfferingId(PackageMenuOffering menuOfferingId) {
		this.menuOfferingId = menuOfferingId;
	}

	public String getMenuOfferingItem() {
		return menuOfferingItem;
	}

	public void setMenuOfferingItem(String menuOfferingItem) {
		this.menuOfferingItem = menuOfferingItem;
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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
