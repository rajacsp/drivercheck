package org.driver.check.model;

import java.util.Date;

import org.springframework.core.style.ToStringCreator;

public class TestResult extends BaseEntity{
	
	/*
	 * 
	 * id
	 * testId
	 * test_taken_date
	 * 
	 * 
	 */
	
	private int testId;
	
    private Date testTakenDate;
    
	public Date getTestTakenDate(){
		return testTakenDate;
	}

	public void setTestTakenDate(Date testTakenDate) {
		this.testTakenDate = testTakenDate;
	}
	
	public int getTestId(){
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}
    
    // client_id, employee_id will be added later
	
    public TestResult() {
	}
    
    public TestResult(final int testId, final Date testTakenDate) {
    	this.testId = testId;
    	this.testTakenDate = testTakenDate;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
        		.append("test_id", this.testId)	
                .append("test_taken_date", this.testTakenDate)                
                .toString();
    }
}
