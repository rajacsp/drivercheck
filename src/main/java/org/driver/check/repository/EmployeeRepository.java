package org.driver.check.repository;

import java.util.Collection;

import org.driver.check.model.Employee;
import org.springframework.dao.DataAccessException;


public interface EmployeeRepository {
    Collection<Employee> findByLastName(String lastName) throws DataAccessException;
    
    Collection<Employee> findAll() throws DataAccessException;

	Employee findById(int id);
}
