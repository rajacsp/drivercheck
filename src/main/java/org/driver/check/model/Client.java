
package org.driver.check.model;

import org.springframework.core.style.ToStringCreator;

public class Client{
	
	private int id;
	
    private String name;
    
    private String address;

    private String city;

    public Client() {
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
    
    public Client(final int id, final String name, final String address, final String city) {
    	this.id = id;
    	this.name = name;
    	this.address = address;
    	this.city = city;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.id)
                .append("name", this.name)
                .append("address", this.address)
                .append("city", this.city)
                .toString();
    }

}
