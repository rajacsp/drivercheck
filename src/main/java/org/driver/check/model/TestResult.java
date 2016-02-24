package org.driver.check.model;

import java.util.Date;

import org.springframework.core.style.ToStringCreator;

public class TestResult{
	
    private Date testTakenDate;
    
	public Date getTestTakenDate(){
		return testTakenDate;
	}

	public void setTestTakenDate(Date testTakenDate) {
		this.testTakenDate = testTakenDate;
	}
    
    // client_id, employee_id will be added later
	
    public TestResult() {
	}
    
    public TestResult(final Date testTakenDate) {
    	this.testTakenDate = testTakenDate;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("test_taken_date", this.testTakenDate)                
                .toString();
    }
}
