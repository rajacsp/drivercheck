package org.driver.check.morphia.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;

import lombok.Data;


@Entity("employees")
@Indexes(
    @Index(value = "salary", fields = @Field("salary"))
)
@Data
public class EmployeeMOM {
	
	@Id
    private ObjectId id;
	
    private String name;
    
    @Property("wage")
    private Double salary;
    
    public EmployeeMOM(String name, Double salary){
    	this.name = name;
    	this.salary = salary;
    }
    
    public EmployeeMOM(){
    	
    }
}
