package org.driver.check.service;

import java.util.Collection;
import java.util.List;

import org.driver.check.model.Employee;
import org.springframework.dao.DataAccessException;

public interface EmployeeService {
    Collection<Employee> findEmployeeByLastName(String lastName) throws DataAccessException;
    
    Collection<Employee> findAll() throws DataAccessException;
    
    void deleteEmployeeByEmpId(final int empId);
    
    void addEmployee(final int empId, final String firstName, final String lastName, final String address, final String city, final String telephone);
    
    void updateEmployee(final int empId, final String firstName, final String lastName, final String address, final String city, final String telephone);
    
    List<Employee> findByFirstName(String firstName);
    
    List<Employee> findByLastName(String lastName);
}
