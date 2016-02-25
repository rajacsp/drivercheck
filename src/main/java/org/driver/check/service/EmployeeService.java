package org.driver.check.service;

import java.util.Collection;

import org.driver.check.model.Employee;
import org.springframework.dao.DataAccessException;

public interface EmployeeService {
    Collection<Employee> findEmployeeByLastName(String lastName) throws DataAccessException;
    
    Collection<Employee> findAll() throws DataAccessException;
    
    void deleteEmployeeByEmpId(final int empId);
    
    void addEmployee(final int empId, final String firstName, final String lastName, final String address, final String city, final String telephone);
    
    void updateEmployee(final int empId, final String firstName, final String lastName, final String address, final String city, final String telephone);
}
