package org.driver.check.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.util.RandomDC;
import org.springframework.core.style.ToStringCreator;

public class TestResult{
	
	/*
	 * 
	 * 
	 * testId
	 * test_taken_date
	 * result [true/false] 
	 * 
	 */
	
	private int testId;
	
    private Date testTakenDate;
    
    private Boolean result;
    
    public Boolean getResult(){
    	return this.result;
    }
    
    public void setResult(Boolean result){
    	this.result = result;
    }
    
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
    
    public TestResult(final int testId, final Date testTakenDate, Boolean result) {
    	this.testId = testId;
    	this.testTakenDate = testTakenDate;
    	this.result = result;
    }
    
    public static List<TestResult> getRandomTests(){
    	int max = RandomDC.getRandomInt(1, 5);
    	max = 2;
    	List<TestResult> tests = new LinkedList<TestResult>();
    	for(int i = 0;i< max; i++){
    		tests.add(new TestResult(RandomDC.getRandomInt(), new Date(), true));
    	}
    	return tests;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
        		.append("test_id", this.testId)	
                .append("test_taken_date", this.testTakenDate)                
                .toString();
    }
}
