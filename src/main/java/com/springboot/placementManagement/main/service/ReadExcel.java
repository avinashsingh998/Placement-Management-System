package com.springboot.placementManagement.main.service;

import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.placementManagement.main.model.Course;
import com.springboot.placementManagement.main.model.Student;

@Service
public class ReadExcel implements IReadExcel {
	@Autowired
	StudentCourseService st;

	@Override
	public void insert(List<MultipartFile> uploadedExcelList, Course course) {
		int[] flagArr = {10,10,10,10};
		Workbook workbook = null;
		Iterator<Row> itr = null;
		try {
			workbook = WorkbookFactory.create(uploadedExcelList.get(0).getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			itr = sheet.iterator();
			// place code here to get data from sheet by row and column
		} catch (Exception e) {
		}
		// creating a Sheet object to retrieve object //iterating over excel file
		Row row = itr.next();
		Iterator<Cell> cellIterator = row.cellIterator();
		int i = 0;
		while (cellIterator.hasNext()) {
			try {
				Cell cell = cellIterator.next();
				
				if (cell.getStringCellValue().equalsIgnoreCase("id")
						|| cell.getStringCellValue().equalsIgnoreCase("student id")) {
					flagArr[0] = i;
				} else if (cell.getStringCellValue().equalsIgnoreCase("name")
						|| cell.getStringCellValue().equalsIgnoreCase("student name")) {
					flagArr[1] = i;
				} else if (cell.getStringCellValue().equalsIgnoreCase("mobile number")
						|| cell.getStringCellValue().equalsIgnoreCase("phone number")
						|| cell.getStringCellValue().equalsIgnoreCase("pnumber")) {
					flagArr[2] = i;
				} else if (cell.getStringCellValue().equalsIgnoreCase("email")
						|| cell.getStringCellValue().equalsIgnoreCase("email id")
						|| cell.getStringCellValue().equalsIgnoreCase("emailid")) {
					flagArr[3] = i;
				}
				i++;

			} catch (Exception e) {
			}
		}

		while (itr.hasNext()) {

			Row rowd = itr.next();
			Student student = new Student();
			try {
			if(flagArr[0] != 10) 
				student.setId(rowd.getCell(flagArr[0]).getStringCellValue());
			if(flagArr[1] != 10)
				student.setName(rowd.getCell(flagArr[1]).getStringCellValue());
			if(flagArr[2] != 10)
				student.setPhoneNumber((long)rowd.getCell(flagArr[2]).getNumericCellValue());
			if(flagArr[3] != 10)
				student.setEmailId(rowd.getCell(flagArr[3]).getStringCellValue());
			student.setCourse(course);
			st.saveStudent(student);
			}catch(Exception e) {
				
				System.out.println("error");}
			
		}
		

	}
}
