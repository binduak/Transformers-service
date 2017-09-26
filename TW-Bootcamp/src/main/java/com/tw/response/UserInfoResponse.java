package com.tw.response;

public class UserInfoResponse {

	private String name;
	private String emailId;
	private String username;
	private String address;
	private String mobileNumber;
	private boolean isBuyer;
	

	public UserInfoResponse(String name, String emailId, String username, String address, String mobileNumber,
			boolean isBuyer) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.username = username;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.isBuyer = isBuyer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public boolean isBuyer() {
		return isBuyer;
	}
	public void setBuyer(boolean isBuyer) {
		this.isBuyer = isBuyer;
	}
	
	
}
