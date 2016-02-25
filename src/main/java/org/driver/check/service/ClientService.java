package org.driver.check.service;

import java.util.Collection;

import org.driver.check.model.Client;
import org.springframework.dao.DataAccessException;

public interface ClientService {
    
    Collection<Client> findAll() throws DataAccessException;
    
    void deleteClientByClientId(final int clientId);
    
    void addClient(final int clientId, final String name, final String address, final String city);
    
    void updateClient(final int clientId, final String name, final String address, final String city);
}
