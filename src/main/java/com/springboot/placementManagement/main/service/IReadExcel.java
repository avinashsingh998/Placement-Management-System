package com.springboot.placementManagement.main.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.placementManagement.main.model.Course;

public interface IReadExcel {
	public void insert(List<MultipartFile> uploadedExcelList, Course course); 


}
