package org.driver.check.service;

import java.util.Collection;
import java.util.List;

import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.springframework.dao.DataAccessException;

public interface ClientService {
    
    Collection<Client> findAll() throws DataAccessException;
    
    void deleteClientByClientId(final int clientId);
    
    void addClient(final int clientId, final String name, final String address, final String city);
    
    void addClient(final int clientId, final String name, final String address, final String city, final List<Employee> employees);
    
    void updateClient(final int clientId, final String name, final String address, final String city);
}
