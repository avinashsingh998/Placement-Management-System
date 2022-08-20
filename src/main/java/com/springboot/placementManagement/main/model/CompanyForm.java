package com.springboot.placementManagement.main.model;


public class CompanyForm {
	String name;
	String phoneNumber;
	String emailId;
	String arrivalDate;
	String highestPackage;
	String[] selectedCourse;
	
	
	public CompanyForm(String name, String phoneNumber, String emailId, String arrivalDate, String highestPackage,
			String[] selectedCourse) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.arrivalDate = arrivalDate;
		this.highestPackage = highestPackage;
		this.selectedCourse = selectedCourse;
	}
	public String[] getSelectedCourse() {
		return selectedCourse;
	}
	public void setSelectedCourse(String[] selectedCourse) {
		this.selectedCourse = selectedCourse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getHighestPackage() {
		return highestPackage;
	}
	public void setHighestPackage(String highestPackage) {
		this.highestPackage = highestPackage;
	}
	public CompanyForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyForm(String name, String phoneNumber, String emailId, String arrivalDate, String highestPackage) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.arrivalDate = arrivalDate;
		this.highestPackage = highestPackage;
	}
	
	

}
