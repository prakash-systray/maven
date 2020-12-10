package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.util.List;


public class VendorWorkingHoursDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	List<TimeSchedulerInfo> timeSchedulerInfo;

	public List<TimeSchedulerInfo> getTimeSchedulerInfo() {
		return timeSchedulerInfo;
	}

	public void setTimeSchedulerInfo(List<TimeSchedulerInfo> timeSchedulerInfo) {
		this.timeSchedulerInfo = timeSchedulerInfo;
	}
	
	
}
