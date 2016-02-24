
package org.driver.check.model;

import org.springframework.core.style.ToStringCreator;

public class Client{
	/*
	 * id
	 * name
	 * address
	 * city
	 */
	
	private Object id;
	
    private String name;
    
    private String address;

    private String city;

    public Client() {
	}
    
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
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
    
    public Client(final String name, final String address, final String city) {    	
    	this.name = name;
    	this.address = address;
    	this.city = city;
    }

    // this method is not working properly in Json print
    @Override
    public String toString() {
        return new ToStringCreator(this)
                //.append("id", this.id) //bson id is not necessary
                .append("name", this.name)
                .append("address1", this.address)
                .append("city", this.city)
                .toString();
    }

}
