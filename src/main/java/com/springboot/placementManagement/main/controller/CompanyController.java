package com.springboot.placementManagement.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.placementManagement.main.model.CompanyForm;
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
	@GetMapping("/companyD")
	public String deleteCompany(@RequestParam("cId") String companyId ) {
		String result="errorPages/errorPage0";
		if(scService.deleteCompany(Long.parseLong(companyId)) == 1) {
		result="redirect:/company";}
		return result;
		
	}
	

}
