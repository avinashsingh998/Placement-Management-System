package com.springboot.placementManagement.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.placementManagement.main.model.Company;

@Repository
public interface CompanyDao extends JpaRepository<Company, Long> {

}
