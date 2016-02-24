package org.driver.check.service;

import java.util.Collection;

import org.driver.check.model.Client;
import org.springframework.dao.DataAccessException;

public interface ClientService {
    
	Client findClientByName(String name) throws DataAccessException;
    
    Collection<Client> findAll() throws DataAccessException;
}
