package org.driver.check.morphia.model;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import lombok.Data;

@Entity("client")
@Data
public class ClientMOM {

	@Id
    private String id;
	
    private String name;
    
    private String address;

    private String city;
    
    @Embedded
    private List<EmployeeMOM> employees;    
    
    public ClientMOM() {
	}
    
    public ClientMOM(final String _id, final String name, final String address, final String city) {
    	this.name = name;
    	this.address = address;
    	this.city = city;
    }
    
    public ClientMOM(final String _id, final String name, final String address, final String city, final List<EmployeeMOM> employees) {
    	this.name = name;
    	this.address = address;
    	this.city = city;
    	this.employees = employees;
    }
}
