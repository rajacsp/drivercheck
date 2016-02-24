
package org.driver.check.model;

public class BaseEntity {
    
	protected Object id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public boolean isNew() {
        return (this.id == null);
    }
}
