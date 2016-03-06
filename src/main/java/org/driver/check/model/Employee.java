
package org.driver.check.model;

import java.util.List;

public class Employee {
	
	/*
	 * empId
	 * firstName
	 * last_name
	 * address
	 * city
	 * telephone
	 */
	
	private String empId;
	
    private String firstName;

    private String lastName;
    
    private String address;

    private String city;

    private String telephone;

	public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmpId(){
    	return empId;
    }
    
    public void setEmpId(String empId){
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
    
    private List<TestResult> tests;
    
    public List<TestResult> getTests(){
		return tests;
	}
	
	public void setTests(List<TestResult> tests){
		this.tests = tests;
	}
    
    public Employee() {
	}
    
    public Employee(final String empId, final String firstName, final String lastName, final String address, final String city, final String telephone) {    	
    	this.empId = empId;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.city = city;
    	this.telephone = telephone;
    }
    
    public Employee(final String empId, final String firstName, final String lastName, final String address, final String city, final String telephone, final List<TestResult> tests) {    	
    	this.empId = empId;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.city = city;
    	this.telephone = telephone;
    	this.tests = tests;
    }
    
    @Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", empId=" + empId + ", address="
				+ address + ", city=" + city + ", telephone=" + telephone + ", ]";
	}
}
