
package org.driver.check.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class BaseEntity {    

	@Id
	protected ObjectId id;
	
	public ObjectId getId(){
    	return id;
    }
}
