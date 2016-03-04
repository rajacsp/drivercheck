package org.driver.check.model;

public class Employee1 {	

	private String name;
	
	private String department;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public void setDepartment(String department){
		this.department = department;
	}
	
	public Employee1(){
		
	}
	
	public Employee1(String name, String department){
		this.name = name;
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + "]";
	}
}
