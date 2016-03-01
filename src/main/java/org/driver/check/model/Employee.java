
package org.driver.check.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Employee extends Person {
	
	/*
	 * empId
	 * first_name
	 * last_name
	 * address
	 * city
	 * telephone
	 */
	
	private int empId;
	
	private String address;

    private String city;

    private String telephone;
    
    private List<TestResult> tests;
    
    // client_id will be added later
    
    public int getEmpId(){
    	return empId;
    }
    
    public void setEmpId(int empId){
    	this.empId = empId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public List<TestResult> getTests(){
		return tests;
	}
	
	public void setTests(List<TestResult> tests){
		this.tests = tests;
	}
    
    public Employee() {
	}
    
    public Employee(final int empId, final String firstName, final String lastName, final String address, final String city, final String telephone) {    	
    	this.empId = empId;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.city = city;
    	this.telephone = telephone;
    }
    
    public Employee(final int empId, final String firstName, final String lastName, final String address, final String city, final String telephone, final List<TestResult> tests) {    	
    	this.empId = empId;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.city = city;
    	this.telephone = telephone;
    	this.tests = tests;
    }
}
