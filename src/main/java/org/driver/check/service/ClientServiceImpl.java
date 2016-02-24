package org.driver.check.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.model.Client;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientServiceImpl implements ClientService {

    @Override
    @Transactional(readOnly = true)
    public Client findClientByName(String name) throws DataAccessException {
    	List<Client> clientList = getDummyClients();
    	
    	for (Client client : clientList) {
			if(client == null) continue;
			
			if(client.getName() != null && client.getName().equalsIgnoreCase(name))
				return client;
		}
    	
		return null;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Client> findAll() throws DataAccessException {
    	return getDummyClients();
    }
    
    // dummy clients
    private List<Client> getDummyClients(){
    	// id, name, street, city
    	Client client1 = new Client(1, "Driver Check", "11, Street", "Toronto");
    	Client client2 = new Client(2, "SmileGate", "12, Street", "Toronto");
    	Client client3 = new Client(3, "GTECH", "13, Street", "Toronto");
    	
    	List<Client> clientList = new LinkedList<Client>();
    	clientList.add(client1);
    	clientList.add(client2);
    	clientList.add(client3);
    	
    	return clientList;
    }
}
