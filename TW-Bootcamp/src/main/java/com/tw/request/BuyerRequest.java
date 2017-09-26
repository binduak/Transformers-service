package com.tw.request;

import java.util.Date;

public class BuyerRequest  extends UserRequest{

	private String gender;
	private Date dateOfBirth;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
