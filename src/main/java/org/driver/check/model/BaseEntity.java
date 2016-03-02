
package org.driver.check.model;

import org.springframework.data.annotation.Id;

public class BaseEntity {    

	@Id
	protected String id;
	
	public String getId(){
    	return id;
    }
}
