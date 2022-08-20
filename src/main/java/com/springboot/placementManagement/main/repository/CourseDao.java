package com.springboot.placementManagement.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.placementManagement.main.model.Course;

public interface CourseDao extends JpaRepository<Course, String> {
	
	

}
