package com.springboot.placementManagement.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.placementManagement.main.model.Course;
import com.springboot.placementManagement.main.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, String>{
	
	public List<Student> findByCourse(Course course);
	public List<Student> findByNameContaining(String name);
	 
	public List<Student> findByCourseAndNameContaining(Course course, String name);
	public List<Student> findByHighestPackageLessThanEqualAndCourseAndNameContaining(long highestPackage, Course course,String searchName);
	public List<Student> findByCourseAndNameContainingAndPlaced(Course course, String searchName, boolean bool);
	public List<Student> findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlaced(long highestPackage, Course course,String searchName, boolean bool);
	
	public List<Student> findByCourseAndNameContainingOrderByName(Course course, String name);
	public List<Student> findByHighestPackageLessThanEqualAndCourseAndNameContainingOrderByName(long highestPackage, Course course,String searchName);
	public List<Student> findByCourseAndNameContainingAndPlacedOrderByName(Course course, String searchName, boolean bool);
	public List<Student> findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderByName(long highestPackage, Course course,String searchName, boolean bool);
	
	public List<Student> findByCourseAndNameContainingOrderById(Course course, String name);
	public List<Student> findByHighestPackageLessThanEqualAndCourseAndNameContainingOrderById(long highestPackage, Course course,String searchName);
	public List<Student> findByCourseAndNameContainingAndPlacedOrderById(Course course, String searchName, boolean bool);
	public List<Student> findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderById(long highestPackage, Course course,String searchName, boolean bool);
	
	public List<Student> findByCourseAndNameContainingOrderByHighestPackageDesc(Course course, String name);
	public List<Student> findByHighestPackageLessThanEqualAndCourseAndNameContainingOrderByHighestPackageDesc(long highestPackage, Course course,String searchName);
	public List<Student> findByCourseAndNameContainingAndPlacedOrderByHighestPackageDesc(Course course, String searchName, boolean bool);
	public List<Student> findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderByHighestPackageDesc(long highestPackage, Course course,String searchName, boolean bool);
	
}
