
package org.driver.check.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;


public class Client implements Serializable {
	
	/*
	 * id
	 * client_id
	 * name
	 * address
	 * city
	 */
	
	private static final long serialVersionUID = -191439285713860309L;

	@Id
	protected String id;
	
	public String getId(){
    	return id;
    }
	
	private int clientId; //should be made as unique
	
    private String name;
    
    private String address;

    private String city;
    
    private List<Employee> employees;    
    
	public int getClientId(){
		return clientId;
	}
	
	public void setClientId(int clientId){
		this.clientId = clientId;
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
    
    public Client(final int clientId, final String name, final String address, final String city) {
    	this.clientId = clientId;
    	this.name = name;
    	this.address = address;
    	this.city = city;
    }
    
    public Client(final int clientId, final String name, final String address, final String city, final List<Employee> employees) {
    	this.clientId = clientId;
    	this.name = name;
    	this.address = address;
    	this.city = city;
    	this.employees = employees;
    }
    
    @Override
	public String toString() {
		return "Client [id=" + id + ", clientId=" + clientId + ", name=" + name + ", address=" + address + ", city="
				+ city + ", employees=" + employees + "]";
	}
}
