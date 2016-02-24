package org.driver.check.service;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.model.TestResult;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Override
	@Transactional(readOnly = true)
	public TestResult findClientById(int id) throws DataAccessException {
    	
    	List<TestResult> testResultList = getDummyTestResults();
    	
    	for (TestResult testResult : testResultList) {
			if(testResult == null) continue;
			
			if(testResult.getId() == id)
				return testResult;
		}
		return null;
	}
    
    @Override
    @Transactional(readOnly = true)
    public Collection<TestResult> findAll() throws DataAccessException {
    	return getDummyTestResults();
    }
    
    // dummy test results
    private List<TestResult> getDummyTestResults(){
    	// id, test_taken_date
    	
    	// deprecated Date is used just for testing purpose
    	TestResult test1 = new TestResult(1, new Date());
    	TestResult test2 = new TestResult(1, new Date()); 
    	TestResult test3 = new TestResult(1, new Date()); 
    	TestResult test4 = new TestResult(1, new Date()); 
    	TestResult test5 = new TestResult(1, new Date()); 
    	
    	List<TestResult> testResultList = new LinkedList<TestResult>();
    	testResultList.add(test1);
    	testResultList.add(test2);
    	testResultList.add(test3);
    	testResultList.add(test4);
    	testResultList.add(test5);
    	
    	return testResultList;
    }
}
