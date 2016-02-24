
package org.driver.check.model;

import java.util.Date;

import org.springframework.core.style.ToStringCreator;

public class TestResult{
	
	private int id;
	
    private Date testTakenDate;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getTestTakenDate(){
		return testTakenDate;
	}

	public void setTestTakenDate(Date testTakenDate) {
		this.testTakenDate = testTakenDate;
	}
    
    // client_id, employee_id will be added later
	
    public TestResult() {
	}
    
    public TestResult(final int id, final Date testTakenDate) {
    	this.id = id;
    	this.testTakenDate = testTakenDate;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.id)
                .append("test_taken_date", this.testTakenDate)                
                .toString();
    }
}
