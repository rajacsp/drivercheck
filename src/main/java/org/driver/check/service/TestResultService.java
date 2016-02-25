package org.driver.check.service;

import java.util.Collection;
import java.util.Date;

import org.driver.check.model.TestResult;
import org.springframework.dao.DataAccessException;

public interface TestResultService {
    
    Collection<TestResult> findAll() throws DataAccessException;
    
    Collection<TestResult> findNewest() throws DataAccessException;
    
    Collection<TestResult> findOldest() throws DataAccessException;
    
    void deleteTestByTestId(final int testId);
    
    void addTest(final int testId, final Date testTakenDate);
    
    void updateTest(final int testId, final Date testTakenDate);
}
