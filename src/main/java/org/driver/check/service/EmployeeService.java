package org.driver.check.service;

import java.util.Collection;

import org.driver.check.model.Employee;
import org.springframework.dao.DataAccessException;

public interface EmployeeService {
    Collection<Employee> findEmployeeByLastName(String lastName) throws DataAccessException;
    
    Collection<Employee> findAll() throws DataAccessException;
}
