
package org.driver.check.model;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Person extends BaseEntity {

    protected String firstName;

    protected String lastName;

	public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
