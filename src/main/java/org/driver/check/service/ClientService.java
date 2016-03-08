package org.driver.check.service;

import java.util.Collection;
import java.util.List;

import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.driver.check.model.TestResult;
import org.driver.check.morphia.model.ClientMOM;
import org.driver.check.morphia.model.EmployeeMOM;
import org.springframework.dao.DataAccessException;

public interface ClientService {
    
    Collection<Client> findAll() throws DataAccessException;
    
    Collection<Client> findByName(String name) throws DataAccessException;
    
    Client findBy_id(String _id) throws DataAccessException;
    
    List<ClientMOM> findByClientName(String clientName);
    
    List<ClientMOM> findByEmployeeId(String empId) throws DataAccessException;
    
    EmployeeMOM findEmployeeByEmpId(String empId) throws DataAccessException;
    
    List<EmployeeMOM> findEmployees() throws DataAccessException;
    
    List<EmployeeMOM> findEmployeeByEmployeeFirstName(String empId) throws DataAccessException;
    
    List<ClientMOM> findByEmployeeFirstName(String employeeFirstName) throws DataAccessException;
    
    List<ClientMOM> findByEmployeeLastName(String employeeLastName) throws DataAccessException;
    
    List<EmployeeMOM> findByEmployeeCity(String employeeCity) throws DataAccessException;
    
    void deleteClientBy_id(final String _id);
    
    void deleteClientAll();
    
    void addClient(final String _id, final String name, final String address, final String city);
    
    void saveClient(final Client client);
    
    void addClient(final String _id, final String name, final String address, final String city, final List<Employee> employees);
    
    void updateClient(final Client client);
    
    void updateClient(final String _id, final String name, final String address, final String city);
    
    void addEmployee(final String _id,  final String empId, final String firstName, final String lastName, final String address, final String city, final String telephone);
    
    void addEmployee(final String _id, final Employee employee);
    
    void saveEmployee(final String _id, final Employee employee);
    
    void updateEmployee(final String _id, final String empId, final String firstName, final String lastName, 
  			final String address, final String city, final String telephone);
    
    void updateEmployee(final String _id, final Employee employee);
    
    void updateEmployees(final String _id, List<Employee> employees);
    
    void removeEmployee(final String _id, final String empId);
    
    void updateClient(final String _id, final String name, final String address, final String city, final List<Employee> employees);
    
    void addTest(final String _id, final String empId, final TestResult test);
    
    void updateTest(final String empId, final TestResult test);
    
    // test method
    void addCustomer();
}
