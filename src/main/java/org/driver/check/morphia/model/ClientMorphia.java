package org.driver.check.morphia.model;

import java.util.List;

import org.driver.check.model.Employee;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import lombok.Data;

@Entity("client")
@Data
public class ClientMorphia {

	@Id
    private String id;
	
    private String name;
    
    private String address;

    private String city;
    
    private List<Employee> employees;    
    
    public ClientMorphia() {
	}
    
    public ClientMorphia(final String _id, final String name, final String address, final String city) {
    	this.name = name;
    	this.address = address;
    	this.city = city;
    }
    
    public ClientMorphia(final String _id, final String name, final String address, final String city, final List<Employee> employees) {
    	this.name = name;
    	this.address = address;
    	this.city = city;
    	this.employees = employees;
    }
}
