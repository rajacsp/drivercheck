
package org.driver.check.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;


public class Client implements Serializable {
	
	/*
	 * _id
	 * client_id
	 * name
	 * address
	 * city
	 */
	
	private static final long serialVersionUID = -191439285713860309L;

	@Id
	protected String _id;
	
    private String name;
    
    private String address;

    private String city;
    
    private List<Employee> employees;   
    
    public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public List<Employee> getEmployees(){
		return employees;
	}
	
	public void setEmployees(List<Employee> employees){
		this.employees = employees;
	}
	
	public Client() {
	}
    
    public Client(final String _id, final String name, final String address, final String city) {
    	this.name = name;
    	this.address = address;
    	this.city = city;
    }
    
    public Client(final String _id, final String name, final String address, final String city, final List<Employee> employees) {
    	this.name = name;
    	this.address = address;
    	this.city = city;
    	this.employees = employees;
    }
    
    @Override
	public String toString() {
		return "Client [_id=" + _id + ", name=" + name + ", address=" + address + ", city="
				+ city + ", employees=" + employees + "]";
	}
}
