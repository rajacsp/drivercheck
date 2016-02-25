
package org.driver.check.model;

public class Client{
	/*
	 * id
	 * client_id
	 * name
	 * address
	 * city
	 */
	
	private String id;
	
	private int clientId; //should be made as unique
	
    private String name;
    
    private String address;

    private String city;

    public Client() {
	}
    
    public String getId() {
		return id;
	}
	
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
    
    public Client(final int clientId, final String name, final String address, final String city) {
    	this.clientId = clientId;
    	this.name = name;
    	this.address = address;
    	this.city = city;
    }

    /*
    // this method is not working properly in Json print
    @Override
    public String toString() {
        return new ToStringCreator(this)
                //.append("id", this.id) //bson id is not necessary
        		.append("name", this.name)
                .append("name", this.name)
                .append("address", this.address)
                .append("city", this.city)
                .toString();
    }
    */
    
    @Override
	public String toString() {
		return "Client [id=" + id + ", cliend_id="+clientId+" name=" + name + ", address=" + address + ", city="+city+"]";
	}

}
