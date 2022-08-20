package com.springboot.placementManagement.main.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Student {
	
	@Id
	@Size(max = 11, min=9)
	String id;
	
	@Column(nullable=false)
	String name;
	
	@JoinColumn(nullable = false)
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	Course course;
	
	@Column(nullable=true)
	long highestPackage;
	
	@Column(nullable=true)
	long phoneNumber;

	
	@Column(nullable=true)
	String emailId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
	@Column(nullable=true)
	List<CompanySalary> selectedInCompany = new LinkedList<>();
	
	boolean placed;
	

	

	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setObject(Student student) {
		this.id = student.id;
		this.name = student.name;
		this.course = student.course;
		this.highestPackage = student.highestPackage;
		this.phoneNumber = student.phoneNumber;
		this.emailId = student.emailId;
		this.selectedInCompany = student.selectedInCompany;
		this.placed = student.placed;
		
	}





	public Student(@Size(max = 11, min = 9) String id, String name, Course course, long highestPackage,
			long phoneNumber, String emailId, List<CompanySalary> selectedInCompany, boolean placed) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.highestPackage = highestPackage;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.selectedInCompany = selectedInCompany;
		this.placed = placed;
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





	public Course getCourse() {
		return course;
	}





	public void setCourse(Course course) {
		this.course = course;
	}





	public long getHighestPackage() {
		return highestPackage;
	}





	public void setHighestPackage(long highestPackage) {
		this.highestPackage = highestPackage;
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





	public List<CompanySalary> getSelectedInCompany() {
		return selectedInCompany;
	}





	public void setSelectedInCompany(List<CompanySalary> selectedInCompany) {
		this.selectedInCompany = selectedInCompany;
	}





	public boolean isPlaced() {
		return placed;
	}





	public void setPlaced(boolean placed) {
		this.placed = placed;
	}





	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ",    course=" + course + ", highestPackage=" + highestPackage
				+ "    phoneNumber=" + phoneNumber + ", emailId=" + emailId + ",  selectedInCompany=" + selectedInCompany
				+ ", placed=" + placed + "]";
	}


	

}
