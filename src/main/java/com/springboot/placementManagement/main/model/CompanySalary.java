package com.springboot.placementManagement.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Company_Salary_mapping")
public class CompanySalary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cId", nullable = false)
	private Company company;
	
	@Column(name = "salary")
	long salary;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Student student;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CompanySalary(long id, Company company, long salary) {
		super();
		this.id = id;
		this.company = company;
		this.salary = salary;
	}

	public CompanySalary( Company company, long salary) {
		super();
		
		this.company = company;
		this.salary = salary;
		
	}

	public CompanySalary() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CompanySalary [id=" + id + ", company=" + company + ", salary=" + salary + ", student=" + student + "]";
	}
	
	
	
	

}
