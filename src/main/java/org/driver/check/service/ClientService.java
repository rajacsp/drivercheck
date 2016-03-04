package org.driver.check.service;

import java.util.Collection;
import java.util.List;

import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.springframework.dao.DataAccessException;

public interface ClientService {
    
    Collection<Client> findAll() throws DataAccessException;
    
    Collection<Client> findByName(String name) throws DataAccessException;    
    
    void deleteClientByClientId(final int clientId);
    
    void deleteClientAll();
    
    void addClient(final int clientId, final String name, final String address, final String city);
    
    void addClient(final int clientId, final String name, final String address, final String city, final List<Employee> employees);
    
    void updateClient(final int clientId, final String name, final String address, final String city);
    
    void addEmployee(final int clientId,  final Integer empId, final String firstName, final String lastName, final String address, final String city, final String telephone);
    
    void addEmployee(final int clientId, final Employee employee);
    
    void updateEmployees(final int clientId, List<Employee> employees);
    
    void updateClient(final int clientId, final String name, final String address, final String city, final List<Employee> employees);
    
    // test method
    void addCustomer();
}
