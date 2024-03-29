package com.pj.employeeinformation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "designation")
public class Designation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Designation_ID;
	
	private String Designation_Name;

	public int getDesignation_ID() {
		return Designation_ID;
	}

	public void setDesignation_ID(int designation_ID) {
		Designation_ID = designation_ID;
	}

	public String getDesignation_Name() {
		return Designation_Name;
	}

	public void setDesignation_Name(String designation_Name) {
		Designation_Name = designation_Name;
	}
	
}
