package com.springboot.placementManagement.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.placementManagement.main.model.Company;
import com.springboot.placementManagement.main.model.Course;
import com.springboot.placementManagement.main.model.CourseForm;
import com.springboot.placementManagement.main.model.Student;
import com.springboot.placementManagement.main.service.StudentCourseService;

@Controller
public class CourseController {

	boolean courseFlag;

	@Autowired
	StudentCourseService scService;

	@RequestMapping("/course")
	public String course(ModelMap model) {
		if (courseFlag) {
			model.addAttribute("msg", "Course added! Add One more course .....");
			courseFlag = false;
		}
		return "courseForm";
	}

	@PostMapping(value = "/course", consumes = "application/x-www-form-urlencoded")
	public String createCourse(CourseForm course) {
		scService.saveCourse(course);
		courseFlag = true;
		return "redirect:/course";
	}

	@GetMapping("/courses")
	public String courses(ModelMap model, @RequestParam String courseId, @RequestParam String name, @RequestParam String sortBy, @RequestParam String cId, @RequestParam String placed ) {
		Course course = scService.getCourseById(courseId);
		Company company = new Company();
		company.setName("Student who can apply for the company");
		 if(!cId.equals("") && !cId.equals("0")) company = scService.getCompanyById(Long.parseLong(cId));
		 long companyId=0;
		 if(!cId.equals("")) {
			  companyId = Long.parseLong(cId);
		 }
		List<Student> students=null;
		
			
			students = scService.getAllStudentByProperties(course, sortBy, placed, companyId, name);
			
		
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("company", company);
		model.addAttribute("placed", placed.equals("true")?"Only Placed Students":placed.equals("false")?"Only Not Placed Students":"All Students");
		model.addAttribute("placedV", placed);
		model.addAttribute("companies", course.getCompany());
		model.addAttribute("course", course);
		model.addAttribute("students", students);
		if(students==null || (students != null && students.isEmpty())) {
			model.addAttribute("msg", "There is no record to display.....");
		}
		

		return "studentList";
	}

}
