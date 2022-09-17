package com.springboot.placementManagement.main.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.placementManagement.main.model.Company;
import com.springboot.placementManagement.main.model.CompanyForm;
import com.springboot.placementManagement.main.model.Course;
import com.springboot.placementManagement.main.model.Student;
import com.springboot.placementManagement.main.service.StudentCourseService;

@Controller
public class CompanyController {
	@Autowired
	StudentCourseService scService;
	
	@PostMapping(value="/companyS", consumes = "application/x-www-form-urlencoded")
	public String setCompany(ModelMap model, CompanyForm companyForm) {
		scService.addCompany(companyForm);
		model.addAttribute("msg", "Company added sucessfully.....");
		model.addAttribute("courses" ,scService.getAllCourses());
		model.addAttribute("companies" ,scService.getAllCompany());
		
		return "companyForm";
		
	}
	
	@GetMapping("/company")
	public String setCompany(ModelMap model) {
		
		model.addAttribute("courses" ,scService.getAllCourses());
		model.addAttribute("companies" ,scService.getAllCompany());
		
		
		return "companyForm";
		
	}
	@GetMapping("/detailsOfCompany")
	public String setCompany(ModelMap model, @RequestParam("cId") String cid) {
		long cidl = Long.parseLong(cid);
		Company cmp = scService.getCompanyById(cidl);
		
		model.addAttribute("company", cmp);
		List<Student>  sts = scService.getAllStudentByCompany(cidl);
			
		model.addAttribute("students" ,sts);	
		List<Course> courses = scService.getAllCourses().stream().filter(c -> c.getCompany().contains(cmp)).collect(Collectors.toList());
		model.addAttribute("courses", courses);		
		
		return "companyDetails";
		
	}
	@GetMapping("/companyD")
	public String deleteCompany(@RequestParam("cId") String companyId ) {
		String result="errorPages/errorPage0";
		if(scService.deleteCompany(Long.parseLong(companyId)) == 1) {
		result="redirect:/company";}
		return result;
		
	}
	

}
