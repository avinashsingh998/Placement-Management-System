package com.springboot.placementManagement.main.model;

public class CourseForm {
	
	String courseId;
	String name;
	String department;
	String semester;
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public CourseForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseForm(String courseId, String name, String department, String semester) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.department = department;
		this.semester = semester;
	}
	

}
