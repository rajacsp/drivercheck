package org.driver.check.morphia.model;

import java.util.List;

import org.driver.check.model.TestResult;

import lombok.Data;

@Data
public class EmployeeMOM {

	private String empId;
	
    private String firstName;

    private String lastName;
    
    private String address;

    private String city;

    private String telephone;
    
    private List<TestResult> tests;
}
