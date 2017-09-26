package com.tw.request;

public class SellerRequest extends UserRequest{

	private int experienceYears;
	private int experienceMonths;
	private String panCardNo;
	public int getExperienceYears() {
		return experienceYears;
	}
	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}
	public int getExperienceMonths() {
		return experienceMonths;
	}
	public void setExperienceMonths(int experienceMonths) {
		this.experienceMonths = experienceMonths;
	}
	public String getPanCardNo() {
		return panCardNo;
	}
	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}
	
	
	
}
