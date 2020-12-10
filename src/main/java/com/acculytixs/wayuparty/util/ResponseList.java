package com.acculytixs.wayuparty.util;

import java.util.List;

public class ResponseList<T> {


	private List<T> data;
	private String response;
	private String responseMessage;
	private int recordsTotal;
	private int recordsFiltered;


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getResponse()
	{
		return response;
	}

	public void setResponse(String response)
	{
		this.response = response;
	}

	public String getResponseMessage()
	{
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	
}
