package com.pj.employeeinformation.models;

import java.time.LocalDate;
//import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Employee_ID;
	
	private String Last_Name;
	private String First_Name;
	private String Middle_Name;
	private String Gender;
	
	@Column(name = "birthdate")
    private LocalDate Birthdate;
	 
	@ManyToOne
    @JoinColumn(name = "Designation_ID")
	private Designation designation;

	public int getEmployee_ID() {
		return Employee_ID;
	}

	public void setEmployee_ID(int employee_ID) {
		Employee_ID = employee_ID;
	}

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

	public LocalDate getBirthdate() {
		return Birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		Birthdate = birthdate;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	
	
	 
}
