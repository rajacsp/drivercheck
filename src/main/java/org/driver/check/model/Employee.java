
package org.driver.check.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty;

public class Employee implements Serializable {
	
	/*
	 * empId
	 * first_name
	 * last_name
	 * address
	 * city
	 * telephone
	 */
	
	private static final long serialVersionUID = 6835048307189804448L;

	@Column(name = "first_name")
    @NotEmpty
    protected String firstName;

    @Column(name = "last_name")
    @NotEmpty
    protected String lastName;

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
    
    @Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", empId=" + empId + ", address="
				+ address + ", city=" + city + ", telephone=" + telephone + ", tests=" + tests + "]";
	}
}
