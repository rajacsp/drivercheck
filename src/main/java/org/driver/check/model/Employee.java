
package org.driver.check.model;

import org.springframework.core.style.ToStringCreator;

public class Employee extends Person {
	
	/*
	 * 
	 * first_name
	 * last_name
	 * address
	 * city
	 * telephone
	 */
	
	private String address;

    private String city;

    private String telephone;
    
    // client_id will be added later   

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
    
    public Employee(final String firstName, final String lastName, final String address, final String city, final String telephone) {    	
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.city = city;
    	this.telephone = telephone;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)                
                .append("new", this.isNew())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName())
                .append("address", this.address)
                .append("city", this.city)
                .append("telephone", this.telephone)
                .toString();
    }
}
