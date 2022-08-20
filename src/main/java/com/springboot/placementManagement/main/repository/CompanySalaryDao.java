package com.springboot.placementManagement.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.placementManagement.main.model.CompanySalary;

public interface CompanySalaryDao extends JpaRepository<CompanySalary, Long> {

}
