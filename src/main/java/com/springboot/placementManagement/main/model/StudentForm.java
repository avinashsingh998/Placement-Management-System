package com.springboot.placementManagement.main.model;

public class StudentForm {
	
	String id;
	String name;
	String courseId;
	String emailId;
	String phoneNumber;
	
	
	public StudentForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentForm(String id, String name, String courseId, String emailId, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.courseId = courseId;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
