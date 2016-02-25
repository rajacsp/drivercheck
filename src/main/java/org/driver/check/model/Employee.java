
package org.driver.check.model;

import org.springframework.core.style.ToStringCreator;

public class Employee extends Person {
	
	/*
	 * empId
	 * first_name
	 * last_name
	 * address
	 * city
	 * telephone
	 */
	
	private String id;
	
	private int empId;
	
	private String address;

    private String city;

    private String telephone;
    
    // client_id will be added later
    
    public String getId(){
    	return id;
    }
    
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

    @Override
    public String toString() {
        return new ToStringCreator(this)
        		.append("empId", empId)	
                .append("lastName", lastName)
                .append("firstName", firstName)
                .append("address", this.address)
                .append("city", this.city)
                .append("telephone", this.telephone)
                .toString();
    }
}
