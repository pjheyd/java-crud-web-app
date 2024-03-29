package com.pj.employeeinformation.models;

//import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeDto {
	@NotEmpty(message = "The Last Name is required")
	private String Last_Name;
	
	@NotEmpty(message = "The First Name is required")
	private String First_Name;
	
	@NotEmpty(message = "The Middle Name is required")
	private String Middle_Name;
	
	@NotEmpty(message = "The Gender is required")
    private String Gender;

    @NotNull(message = "The Birthdate is required")
    private String Birthdate;

	private String Designation_Name;

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getMiddle_Name() {
		return Middle_Name;
	}

	public void setMiddle_Name(String middle_Name) {
		Middle_Name = middle_Name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}
	
	public String getBirthdate() {
		return Birthdate;
	}

	public void setBirthdate(String birthdate) {
		Birthdate = birthdate;
	}

	public String getDesignation_Name() {
		return Designation_Name;
	}

	public void setDesignation_Name(String designation_Name) {
		Designation_Name = designation_Name;
	}
	
    
    
}
