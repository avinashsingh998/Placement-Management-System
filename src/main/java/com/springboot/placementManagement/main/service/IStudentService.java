package com.springboot.placementManagement.main.service;

import java.util.List;

import com.springboot.placementManagement.main.model.Company;
import com.springboot.placementManagement.main.model.CompanyForm;
import com.springboot.placementManagement.main.model.CompanySalary;
import com.springboot.placementManagement.main.model.Course;
import com.springboot.placementManagement.main.model.CourseForm;
import com.springboot.placementManagement.main.model.Student;
import com.springboot.placementManagement.main.model.StudentForm;

public interface IStudentService {
	public void saveStudent(StudentForm student);
	public void saveStudent(Student student);
	public void updateStudent(Student student);	
	public void deleteStudent(Student student);
	public List<Student> getStudentbyCourse(Course course);
	public List<Student> getAllStudent();
	public List<Student> getAllStudentByProperties(Course course,String sortBy, String placed, long cId, String searchName);
	public List<Student> getStudentbyNC(Course course, String name);
	public List<Student> getStudentbyN(String name);
	public Student getStudentById(String id);
	
	
	public List<Course> getAllCourses();
	public Course getCourseById(String courseId);
	public void saveCourse(CourseForm course);
	public void saveCourse(Course course);
	
	public void addCompany(CompanyForm company);
	public List<Company> getAllCompany();
	public Company getCompanyById(Long id);
	public int deleteCompany(long cId);
	
	public CompanySalary getCompanySalaryById(Long id);

}
