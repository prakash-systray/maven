package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;

public class TimeSchedulerInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String workingDay;
	
	private String startTime;
	
	private String endTime;
	
	

	public String getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(String workingDay) {
		this.workingDay = workingDay;
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

}
