package com.springboot.placementManagement.main.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long cId;
	String name;
	long phoneNumber;
	String emailId;
	Date arrivalDate;
	long highestPackage;
	
	 
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<CompanySalary> companySalary = new LinkedList<>();

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Company(String name, long phoneNumber, String emailId, Date arrivalDate, long highestPackage,
			 List<CompanySalary> companySalary) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.arrivalDate = arrivalDate;
		this.highestPackage = highestPackage;
		this.companySalary = companySalary;
	}


	public long getcId() {
		return cId;
	}


	public void setcId(long cId) {
		this.cId = cId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Date getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public long getHighestPackage() {
		return highestPackage;
	}


	public void setHighestPackage(long highestPackage) {
		this.highestPackage = highestPackage;
	}


	
	public List<CompanySalary> getCompanySalary() {
		return companySalary;
	}


	public void setCompanySalary(List<CompanySalary> companySalary) {
		this.companySalary = companySalary;
	}


	
	

}
