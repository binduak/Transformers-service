package com.tw.response;

import com.tw.utility.ApplicationUtility;

public class BaseResponse <T>{
	private T data;
	private int responseCode;
	private String responseStatus;
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void setSucessResponse () {
		this.responseCode = ApplicationUtility.RESPONSE_SUCESS_CODE;
		this.responseStatus = ApplicationUtility.RESPONSE_SUCESS_MESSAGE;
	}
	
	public void setFailureResponse () {
		this.responseCode = ApplicationUtility.RESPONSE_FAILURE_CODE;
		this.responseStatus = ApplicationUtility.RESPONSE_FAILURE_MESSAGE;
	}
	
}
