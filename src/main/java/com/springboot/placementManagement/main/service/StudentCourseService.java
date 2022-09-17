package com.springboot.placementManagement.main.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.placementManagement.main.model.Company;
import com.springboot.placementManagement.main.model.CompanyForm;
import com.springboot.placementManagement.main.model.CompanySalary;
import com.springboot.placementManagement.main.model.Course;
import com.springboot.placementManagement.main.model.CourseForm;
import com.springboot.placementManagement.main.model.Student;
import com.springboot.placementManagement.main.model.StudentForm;
import com.springboot.placementManagement.main.repository.CompanyDao;
import com.springboot.placementManagement.main.repository.CompanySalaryDao;
import com.springboot.placementManagement.main.repository.CourseDao;
import com.springboot.placementManagement.main.repository.StudentDao;

@Service
public class StudentCourseService implements IStudentService {

	@Autowired
	StudentDao stdRepo;
	@Autowired
	CourseDao coRepo;

	@Autowired
	CompanyDao cRepo;

	@Autowired
	CompanySalaryDao csRepo;

	@Override
	public void saveStudent(StudentForm student) {
		Student std = new Student();
		std.setId(student.getId());
		std.setCourse(coRepo.findById(student.getCourseId()).get());
		std.setName(student.getName());
		std.setEmailId(student.getEmailId());
		if (!student.getPhoneNumber().equals("")) {
			std.setPhoneNumber(Long.parseLong(student.getPhoneNumber()));
		}

		stdRepo.save(std);

	}

	@Override
	public void updateStudent(Student student) {
		stdRepo.deleteById(student.getId());
		stdRepo.save(student);

	}

	@Override
	public void deleteStudent(Student student) {
		Optional<Student> stud = stdRepo.findById(student.getId());
		if (stud.isPresent()) {
			stdRepo.delete(stud.get());
		}
	}

	@Override
	public List<Student> getStudentbyCourse(Course course) {

		return stdRepo.findByCourse(course);

	}

	@Override
	public List<Student> getAllStudent() {
		return stdRepo.findAll();
	}

	@Override
	public List<Student> getStudentbyNC(Course course, String name) {

		return stdRepo.findByCourseAndNameContaining(course, name);
	}

	@Override
	public List<Student> getStudentbyN(String name) {
		return stdRepo.findByNameContaining(name);
	}

	@Override
	public List<Course> getAllCourses() {

		return coRepo.findAll();
	}

	@Override
	public void saveCourse(CourseForm course) {
		Course co = new Course();
		co.setCourseId(course.getCourseId());
		co.setName(course.getName());
		co.setDepartment(course.getDepartment());
		try {
			co.setSemester(Integer.parseInt(course.getSemester()));
		} catch (NumberFormatException e) {
		}
		coRepo.save(co);

	}

	@Override
	public Student getStudentById(String id) {

		return stdRepo.findById(id).get();
	}

	@Override
	public void addCompany(CompanyForm company) {
		Company cObj = new Company();
		cObj.setName(company.getName());
		cObj.setEmailId(company.getEmailId());
		try {
			cObj.setHighestPackage(Long.parseLong(company.getHighestPackage()));
		} catch (NumberFormatException e) {
		}
		try {
			cObj.setPhoneNumber(Long.parseLong(company.getPhoneNumber()));
		} catch (NumberFormatException e) {
		}
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		try {
			cObj.setArrivalDate(f.parse(company.getArrivalDate()));
		} catch (ParseException e) {
		}
		
		cRepo.save(cObj);
		for (String courseId : company.getSelectedCourse()) {
			Course course = coRepo.findById(courseId).get();
			course.getCompany().add(cObj);
			saveCourse(course);

		}

	}

	@Override
	public List<Company> getAllCompany() {

		return cRepo.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		stdRepo.save(student);

	}

	@Override
	public Company getCompanyById(Long id) {
		return cRepo.findById(id).get();

	}

	@Override
	public CompanySalary getCompanySalaryById(Long id) {
		return csRepo.findById(id).get();

	}

	@Override
	public int deleteCompany(long cId) {
		List<Course> coList = coRepo.findAll();
		Company company = cRepo.findById(cId).get();
		
		for(Course course:coList) {
			course.getCompany().remove(company);
			coRepo.save(course);
		}
		try {
			cRepo.deleteById(cId);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public Course getCourseById(String courseId) {
		Optional<Course> optionalCourse = coRepo.findById(courseId);
		if (optionalCourse.isPresent())
			return optionalCourse.get();
		return null;
	}

	@Override
	public void saveCourse(Course course) {
		coRepo.save(course);

	}

	@Override
	public List<Student> getAllStudentByProperties(Course course, String sortBy, String placed, long cId,
			String searchName) {
		List<Student> students = null;
		Optional<Company> compOptional = cRepo.findById(cId);

		if (sortBy.equalsIgnoreCase("sort By")) {

			if (placed.equalsIgnoreCase("")) {

				if (!compOptional.isPresent()) {

					students = stdRepo.findByCourseAndNameContaining(course, searchName);
				} else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContaining(
							compOptional.get().getHighestPackage(), course, searchName);
				}
			} else if (placed.equalsIgnoreCase("true")) {
				
				if (!compOptional.isPresent()) {
					students = stdRepo.findByCourseAndNameContainingAndPlaced(course, searchName, true);
				}else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlaced(compOptional.get().getHighestPackage(), course, searchName, true);
				}

			}else if (placed.equalsIgnoreCase("false")) {
				if (!compOptional.isPresent()) {
					students = stdRepo.findByCourseAndNameContainingAndPlaced(course, searchName, false);
				}else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlaced(compOptional.get().getHighestPackage(), course, searchName, false);
				}
			} 

		} else if (sortBy.equalsIgnoreCase("name")) {
			


			if (placed.equalsIgnoreCase("")) {

				if (!compOptional.isPresent()) {

					students = stdRepo.findByCourseAndNameContainingOrderByName(course, searchName);
				} else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingOrderByName(
							compOptional.get().getHighestPackage(), course, searchName);
				}
			} else if (placed.equalsIgnoreCase("true")) {
				
				if (!compOptional.isPresent()) {
					students = stdRepo.findByCourseAndNameContainingAndPlacedOrderByName(course, searchName, true);
				}else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderByName(compOptional.get().getHighestPackage(), course, searchName, true);
				}

			}else if (placed.equalsIgnoreCase("false")) {
				if (!compOptional.isPresent()) {
					students = stdRepo.findByCourseAndNameContainingAndPlacedOrderByName(course, searchName, false);
				}else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderByName(compOptional.get().getHighestPackage(), course, searchName, false);
				}
			} 


		}

		else if (sortBy.equalsIgnoreCase("id")) {
			


			if (placed.equalsIgnoreCase("")) {

				if (!compOptional.isPresent()) {

					students = stdRepo.findByCourseAndNameContainingOrderById(course, searchName);
				} else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingOrderById(
							compOptional.get().getHighestPackage(), course, searchName);
				}
			} else if (placed.equalsIgnoreCase("true")) {
				
				if (!compOptional.isPresent()) {
					students = stdRepo.findByCourseAndNameContainingAndPlacedOrderById(course, searchName, true);
				}else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderById(compOptional.get().getHighestPackage(), course, searchName, true);
				}

			}else if (placed.equalsIgnoreCase("false")) {
				if (!compOptional.isPresent()) {
					students = stdRepo.findByCourseAndNameContainingAndPlacedOrderById(course, searchName, false);
				}else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderById(compOptional.get().getHighestPackage(), course, searchName, false);
				}
			} 
		}

		else if (sortBy.equalsIgnoreCase("highestPackage")) {
			


			if (placed.equalsIgnoreCase("")) {

				if (!compOptional.isPresent()) {

					students = stdRepo.findByCourseAndNameContainingOrderByHighestPackageDesc(course, searchName);
				} else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingOrderByHighestPackageDesc(
							compOptional.get().getHighestPackage(), course, searchName);
				}
			} else if (placed.equalsIgnoreCase("true")) {
				
				if (!compOptional.isPresent()) {
					students = stdRepo.findByCourseAndNameContainingAndPlacedOrderByHighestPackageDesc(course, searchName, true);
				}else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderByHighestPackageDesc(compOptional.get().getHighestPackage(), course, searchName, true);
				}

			}else if (placed.equalsIgnoreCase("false")) {
				if (!compOptional.isPresent()) {
					students = stdRepo.findByCourseAndNameContainingAndPlacedOrderByHighestPackageDesc(course, searchName, false);
				}else {
					students = stdRepo.findByHighestPackageLessThanEqualAndCourseAndNameContainingAndPlacedOrderByHighestPackageDesc(compOptional.get().getHighestPackage(), course, searchName, false);
				}
			} 

		}

		return students;
	}

	public List<Student> getAllStudentByCompany(long cidl) {
		Company cmp = cRepo.findById(cidl).get();
		List<Student> students = csRepo.findAll().stream().filter(cs -> cs.getCompany().equals(cmp)).map(p->p.getStudent()).collect(Collectors.toList());
		return students;
	}

}
