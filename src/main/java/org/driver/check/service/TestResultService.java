package org.driver.check.service;

import java.util.Collection;

import org.driver.check.model.TestResult;
import org.springframework.dao.DataAccessException;

public interface TestResultService {
    
	TestResult findClientById(int id) throws DataAccessException;
    
    Collection<TestResult> findAll() throws DataAccessException;
}
