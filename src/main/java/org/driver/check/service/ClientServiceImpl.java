package org.driver.check.service;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.business.constants.Const;
import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.Mongo;

@Service
public class ClientServiceImpl implements ClientService, Const {
	
	private static Logger _log = LoggerFactory.getLogger(ClientServiceImpl.class);
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Client> findAll() throws DataAccessException {
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));    	
    	return mongoOps.findAll(Client.class);
    }
    
    @Override
    public void deleteClientByClientId(final int clientId) {	
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
		Client p = mongoOps.findOne(query(where("clientId").is(clientId)), Client.class);
		_log.info("{deleteClientByClientId} p "+p);		
		mongoOps.remove(p);
	}
    
    @Override
    public void addClient(final int clientId, final String name, final String address, final String city){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	org.driver.check.model.Client p = new org.driver.check.model.Client(clientId, name, address, city);
		mongoOps.insert(p);		
    }
    
    @Override
    public void addClient(final int clientId, final String name, final String address, final String city, final List<Employee> employees){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	Client p = new Client(clientId, name, address, city, employees);
		mongoOps.insert(p);	
    }
    
    /*
     * 
     * (non-Javadoc)
     * @see org.driver.check.service.ClientService#updateClient(int, java.lang.String, java.lang.String, java.lang.String)
     * 
     * sample mongo query:
     * db.clients.update({_id: 2}, {$set : {"name" : "SmileGateWest"} });
     */
    @Override
    public void updateClient(final int clientId, final String name, final String address, final String city){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	mongoOps.updateFirst(query(where("clientId").is(clientId)), update("address", address), Client.class);
    	mongoOps.updateFirst(query(where("clientId").is(clientId)), update("name", name), Client.class);
    	mongoOps.updateFirst(query(where("clientId").is(clientId)), update("city", city), Client.class);    	
		
		Client p = mongoOps.findOne(query(where("clientId").is(clientId)), Client.class);
		
		_log.info("{updateClient}: " + p);		
    }
    
    @Override
    public void updateEmployees(final int clientId, List<Employee> employees){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	mongoOps.updateFirst(query(where("clientId").is(clientId)), update("employees", employees), Client.class);    	    	
		
		Client p = mongoOps.findOne(query(where("clientId").is(clientId)), Client.class);
		
		_log.info("{updateEmployees}: " + p);		
    }
    
    // dummy clients
    private List<Client> getDummyClients(){
    	// id, name, street, city
    	Client client1 = new Client(103, "Driver Check", "11, Street", "Toronto");
    	Client client2 = new Client(104, "SmileGate", "12, Street", "Toronto");
    	Client client3 = new Client(104, "GTECH", "13, Street", "Toronto");
    	
    	List<Client> clientList = new LinkedList<Client>();
    	clientList.add(client1);
    	clientList.add(client2);
    	clientList.add(client3);
    	
    	return clientList;
    }

	@Override
	public void updateClient(int clientId, String name, String address, String city, List<Employee> employees) {
		// TODO Auto-generated method stub
		
	}
}
