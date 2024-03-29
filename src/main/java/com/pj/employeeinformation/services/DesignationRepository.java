package com.pj.employeeinformation.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pj.employeeinformation.models.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Integer>{
	@Query("SELECT d.Designation_Name FROM Designation d")
    List<String> findAllDesignationNames();
	
	@Query("SELECT d FROM Designation d WHERE d.Designation_Name = ?1")
	Designation findByDesignation_Name(String designationName);
}
