package com.acculytixs.wayuparty.dto.services;

import java.io.Serializable;
import java.math.BigInteger;

public class ServicesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigInteger serviceId;
	
	private String serviceName;
	
	private String serviceUUID;
	
	private String serviceImage;
	

	public BigInteger getServiceId() {
		return serviceId;
	}

	public void setServiceId(BigInteger serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceUUID() {
		return serviceUUID;
	}

	public void setServiceUUID(String serviceUUID) {
		this.serviceUUID = serviceUUID;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}
	
	

}
