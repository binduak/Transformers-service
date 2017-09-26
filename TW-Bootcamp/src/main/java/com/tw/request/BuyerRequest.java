package com.tw.request;

public class BuyerRequest  extends UserRequest{
	
	private String gender;
	//"yyyy-MM-dd"
	private String dateOfBirth;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
