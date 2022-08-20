package com.springboot.placementManagement.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.placementManagement.main.model.Course;
import com.springboot.placementManagement.main.service.StudentCourseService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	StudentCourseService scService;
	
	@GetMapping("/")
	public String HomePage(ModelMap model) {
		List<Course> courses = scService.getAllCourses();
		model.addAttribute("courses", courses);
		if(courses.isEmpty()) {
			model.addAttribute("msg", "You have not created any course yet!.....");
		}

		return "home";
	}

	
}
