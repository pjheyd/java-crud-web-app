package com.pj.employeeinformation.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pj.employeeinformation.models.Designation;
import com.pj.employeeinformation.models.Employee;
import com.pj.employeeinformation.models.EmployeeDto;
import com.pj.employeeinformation.services.DesignationRepository;
import com.pj.employeeinformation.services.EmployeeRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	 @Autowired
	 private EmployeeRepository emRepo;

	 @Autowired
	 private DesignationRepository desRepo;
	 
	 @GetMapping({"", "/"})
		public String showEmployeeList(Model model) {
			List<Employee> employees = emRepo.findAll();
			model.addAttribute("employees", employees);
			return "employee/EmployeeList";
	 	}
	 
	 @ModelAttribute("employees")
	    public List<Employee> employees() {
	        List<Employee> employees = emRepo.findAll();
	        for (Employee employee : employees) {
	            Designation designation = employee.getDesignation();
	            if (designation != null) {
	                designation = desRepo.findById(designation.getDesignation_ID()).orElse(null);
	                employee.setDesignation(designation);
	            }
	        }
	        return employees;
	    }
	 
	 
	 @GetMapping("/create")
		public String showCreatePage(Model model) {
		 	List<String> designationNames = desRepo.findAllDesignationNames();
	        model.addAttribute("designationNames", designationNames);
			EmployeeDto employeeDto = new EmployeeDto();
			model.addAttribute("employeeDto", employeeDto);
			return "employee/CreateEmployeeInfo";
		}
	 
	 
	 @PostMapping("/create")
		public String createEmployee( 
			@Valid @ModelAttribute EmployeeDto employeeDto,
			BindingResult result,
			Model model
				) {
		 		if (employeeDto.getDesignation_Name().isEmpty()) {
		 			result.addError(new FieldError("employeeDto", "Designation_Name", "The Designation is required"));
		 		}
		 		
				 if (result.hasErrors()) {
					 List<String> designationNames = desRepo.findAllDesignationNames();
				     model.addAttribute("designationNames", designationNames);
					 return "employee/CreateEmployeeInfo";
				 }
		 
				Designation designation = desRepo.findByDesignation_Name(employeeDto.getDesignation_Name());
							 	
				Employee employee = new Employee();
					employee.setLast_Name(employeeDto.getLast_Name());
					employee.setFirst_Name(employeeDto.getFirst_Name());
					employee.setMiddle_Name(employeeDto.getMiddle_Name());
					employee.setGender(employeeDto.getGender());
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate birthdate = LocalDate.parse(employeeDto.getBirthdate(), formatter);
			        System.out.println(birthdate);
			        employee.setBirthdate(birthdate);
			        
					employee.setDesignation(designation);
									
				emRepo.save(employee);
								 
				return "redirect:/employee";
		}
	 
	 
	 @GetMapping("/edit")
		public String showEditPage (
				Model model,
				@RequestParam int id
				) {
		 	
		 	try {
		 		Employee employee = emRepo.findById(id).get();
		 		model.addAttribute("employee", employee);
		 		
		 		EmployeeDto employeeDto = new EmployeeDto();
			 		employeeDto.setFirst_Name(employee.getFirst_Name());
			 		employeeDto.setLast_Name(employee.getLast_Name());
			 		employeeDto.setMiddle_Name(employee.getMiddle_Name());
			 		employeeDto.setGender(employee.getGender());
			 		
			 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        String formattedBirthdate = employee.getBirthdate().format(formatter);
			        employeeDto.setBirthdate(formattedBirthdate); 
			 		
			 		employeeDto.setDesignation_Name(employee.getDesignation().getDesignation_Name());
		 		
		 		List<String> designationNames = desRepo.findAllDesignationNames();
		        model.addAttribute("designationNames", designationNames);
		 		
		 		model.addAttribute("employeeDto", employeeDto);
		 	}
		 	catch (Exception ex) {
		 		System.out.println("Exception: " + ex.getMessage());
		 		return "redirect:/employee";
		 	}
		 
		 return "employee/EditEmployeeInfo";
	 }
	 
	 
	 @PostMapping("/edit")
		public String updateEmployee(
				Model model,
				@RequestParam int id,
				@Valid @ModelAttribute EmployeeDto employeeDto,
				BindingResult result
				) {
			
			try {
				Employee employee = emRepo.findById(id).get();
				model.addAttribute("employee", employee);
				
				if (employeeDto.getDesignation_Name().isEmpty()) {
		 			result.addError(new FieldError("employeeDto", "Designation_Name", "The Designation is required"));
		 		}
		 		
				 if (result.hasErrors()) {
					 List<String> designationNames = desRepo.findAllDesignationNames();
				     model.addAttribute("designationNames", designationNames);
					 return "employee/EditEmployeeInfo";
				 }
							 	
					employee.setLast_Name(employeeDto.getLast_Name());
					employee.setFirst_Name(employeeDto.getFirst_Name());
					employee.setMiddle_Name(employeeDto.getMiddle_Name());
					employee.setGender(employeeDto.getGender());
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate birthdate = LocalDate.parse(employeeDto.getBirthdate(), formatter);
			        employee.setBirthdate(birthdate);
			        
			        Designation designation = desRepo.findByDesignation_Name(employeeDto.getDesignation_Name());
			        employee.setDesignation(designation);
									
				emRepo.save(employee);
				
			}
			catch(Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
		
			return "redirect:/employee";
		}
	 
	 @GetMapping("/delete")
		public String deleteEmployee(
				@RequestParam int id
				) {
			
			try {
				Employee employee = emRepo.findById(id).get();
				
				//delete the employee
				emRepo.delete(employee);
			}
			catch (Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
			
			return "redirect:/employee";
		}
	}
