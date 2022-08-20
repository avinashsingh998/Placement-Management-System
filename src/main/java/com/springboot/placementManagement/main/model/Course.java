package com.springboot.placementManagement.main.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Course {

	@Id
	public String courseId;

	@Column(nullable = false)
	String name;
	String department;
	int semester;
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Student> students = new LinkedList<>();
	
	@ManyToMany( fetch = FetchType.LAZY)
	private List<Company> company = new LinkedList<>();
	
	
	
	public void setCompany(List<Company> company) {
		this.company = company;
	}
	public Course(String courseId, String name, String department, int semester, List<Student> students,
			List<Company> company) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.department = department;
		this.semester = semester;
		this.students = students;
		this.company = company;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	 public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Company> getCompany() {
		return company;
	}
	
	

	


}
