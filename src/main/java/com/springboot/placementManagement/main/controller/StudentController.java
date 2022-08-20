package com.springboot.placementManagement.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.springboot.placementManagement.main.model.CompanySalary;
import com.springboot.placementManagement.main.model.Student;
import com.springboot.placementManagement.main.model.StudentForm;
import com.springboot.placementManagement.main.repository.CompanyDao;
import com.springboot.placementManagement.main.repository.CourseDao;
import com.springboot.placementManagement.main.service.ReadExcel;
import com.springboot.placementManagement.main.service.StudentCourseService;

@Controller
@RequestMapping("/")
public class StudentController {

	private boolean savedFlag;
	private boolean uriFlag;

	@Autowired
	StudentCourseService st;
	@Autowired
	ReadExcel readExcel;
	
	@PostMapping(value = "/student", consumes = "application/x-www-form-urlencoded")
	public String saveStudent(StudentForm student) {
		st.saveStudent(student);
		savedFlag = true;
		return "redirect:/studentA";
	}

	@PostMapping(value = "/studentU", consumes = "application/x-www-form-urlencoded")
	public String updateStudent(ModelMap model, StudentForm student) {
		st.saveStudent(student);
		model.addAttribute("msg", " Record updated Sucessfully!   Add more.......");
		return "studentForm";
	}

	@GetMapping(value = "/studentU")
	public String updateFormStudent(ModelMap model, @RequestParam("id") String id) {
		model.addAttribute("obj", st.getStudentById(id));
		model.addAttribute("uri", "/studentU");
		model.addAttribute("updateFlag", "readonly");
		uriFlag = true;

		model.addAttribute("courses", st.getAllCourses());
		if (!uriFlag) {
			model.addAttribute("uri", "/student");

		}
		uriFlag = false;
		if (savedFlag) {
			model.addAttribute("msg", " Record Added Sucessfully!   Add more.......");
			savedFlag = false;
		}
		return "studentForm";
	}

	@GetMapping(value = "/studentD")
	public String deleteStudent(ModelMap model, @RequestParam("id") String id) {
		Student std = st.getStudentById(id);
		st.deleteStudent(std);

		return "redirect:/courses?courseId=" + std.getCourse().getCourseId() + "&name=&sortBy=Sort+By&cId=&placed=";
	}

	@GetMapping("/studentA")
	public String studentForm(ModelMap model, StudentForm student) {
		model.addAttribute("courses", st.getAllCourses());
		if (!uriFlag) {
			model.addAttribute("uri", "/student");

		}
		uriFlag = false;
		if (savedFlag) {
			model.addAttribute("msg", " Record Added Sucessfully!   Add more.......");
			savedFlag = false;
		}
		return "studentForm";
	}



	@GetMapping(value = "/studentPU")
	public String updatePlacementStatusStudent(ModelMap model, @RequestParam("id") String id) {
		Student student = st.getStudentById(id);
		model.addAttribute("student",student);
		model.addAttribute("companies", student.getCourse().getCompany());

		return "updateStudentStatus";
	}

	

	@PostMapping("/studentPU")
	public @ResponseBody String setStudentDetails(ModelMap model, HttpServletRequest request) {
		String result = "fail";
		String[] companyId = request.getParameterValues("companyId[]");
		String[] salary = request.getParameterValues("salary[]");
		String studentId = request.getParameter("studentId");
		Student student = st.getStudentById(studentId);
		if (companyId != null && salary != null) {
			student.setPlaced(true);
			int i = 0;
			for (String id : companyId) {
				CompanySalary cs = new CompanySalary(st.getCompanyById(Long.parseLong(id)),
						Long.parseLong(salary[i]));
				i++;
				cs.setStudent(student);
				student.getSelectedInCompany().add(cs);
				if (student.getHighestPackage() < cs.getSalary())
					student.setHighestPackage(cs.getSalary());
				st.updateStudent(student);
			}
		} else {
			st.deleteStudent(student);
			student.getSelectedInCompany().clear();
			student.setPlaced(false);
			student.setHighestPackage(0);
			st.saveStudent(student);

		}

		result = "success";
		return result;
	}

	@PostMapping("/addStudentDetailsInBulk")
	public @ResponseBody String setStudentDetailsInBulk(ModelMap model, HttpServletRequest request, @RequestParam String courseId) {
		String result  = "fail";
		StandardMultipartHttpServletRequest multiRequest = (StandardMultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> tempMap = multiRequest.getMultiFileMap();			
		List<MultipartFile> uploadedExcelList = (tempMap.get(new ArrayList<>(tempMap.keySet()).get(0)));

		String fname = uploadedExcelList.get(0).getOriginalFilename();
		int strLength = fname.length();
		if(fname.subSequence(strLength-4, strLength).equals("xlsx")) {
		readExcel.insert(uploadedExcelList, st.getCourseById(courseId));
		result= "success";}
		
		
		return result;
		
	}
	@PostMapping("/deleteCompany")
	public String deleteCompanyForStudent(@RequestParam String cId,@RequestParam String id) {
		Student student =st.getStudentById(id);
		student.getSelectedInCompany().remove(st.getCompanySalaryById(Long.parseLong(cId)));
		st.updateStudent(student);
		return "redirect:/studentPU?id="+student.getId();
	}
}
