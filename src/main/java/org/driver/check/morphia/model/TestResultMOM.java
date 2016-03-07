package org.driver.check.morphia.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.model.TestResultType;
import org.driver.check.util.RandomDC;
import org.mongodb.morphia.annotations.Embedded;
import org.springframework.core.style.ToStringCreator;

import lombok.Data;

@Embedded
@Data
public class TestResultMOM{
	
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
    
    private TestResultType result;
    
    public TestResultMOM() {
	}
    
    public TestResultMOM(final int testId, final Date testTakenDate) {
    	this.testId = testId;
    	this.testTakenDate = testTakenDate;
    }
    
    public TestResultMOM(final int testId, final Date testTakenDate, TestResultType result) {
    	this.testId = testId;
    	this.testTakenDate = testTakenDate;
    	this.result = result;
    }
    
    public static List<TestResultMOM> getRandomTests(){
    	int max = RandomDC.getRandomInt(1, 5);
    	max = 2;
    	List<TestResultMOM> tests = new LinkedList<TestResultMOM>();
    	for(int i = 0;i< max; i++){
    		tests.add(new TestResultMOM(RandomDC.getRandomInt(), new Date(), TestResultType.PASS));
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
