package com.pj.employeeinformation.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pj.employeeinformation.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
