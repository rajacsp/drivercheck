package org.driver.check.service;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.driver.check.business.constants.Const;
import org.driver.check.model.Client;
import org.driver.check.model.Customer;
import org.driver.check.model.Employee;
import org.driver.check.repository.ClientRepository;
import org.driver.check.repository.ClientRepositoryCustom;
import org.driver.check.repository.CustomerRepository;
import org.driver.check.util.Names;
import org.driver.check.util.RandomDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

@Service("clientService")
public class ClientServiceImpl implements ClientService, Const {
	
	private static Logger _log = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Resource
	private CustomerRepository customerRepository;
	
	@Resource
	private ClientRepository clientRepository;
	
	//@Autowired //not working because of missing bean
	//@Qualifier("ClientRepositoryCustomImpl")
	//private ClientRepositoryCustom clientRepositoryCustom;
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Client> findAll() throws DataAccessException {    	
    	return clientRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Client> findByName(String name) throws DataAccessException{
    	return clientRepository.findByName(name);
    }
    
    @Override
    public void deleteClientByClientId(final int clientId) {	
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
		Client p = mongoOps.findOne(query(where("clientId").is(clientId)), Client.class);
		_log.info("{deleteClientByClientId} p "+p);		
		mongoOps.remove(p);
	}
    
    @Override
    public void deleteClientAll(){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
		// remove all
    }
    
    @Override
    public void addClient(final int clientId, final String name, final String address, final String city){
    	
    	/*
    	 * set 1
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	org.driver.check.model.Client p = new org.driver.check.model.Client(clientId, name, address, city);
		mongoOps.insert(p);	
		*/
    	
    	/* set 2
    	Mongo mongo = new Mongo("localhost", 27017);
    	DB db = mongo.getDB("test");
    	DBCollection collection = db.getCollection("client");
    	
    	Client client = new Client(clientId, name, address, city);
    	BasicDBObject basicDBObject = new BasicDBObject();
    	basicDBObject.put("clients", client);

    	collection.save(basicDBObject);
    	*/
    	
    	Client client = new Client(clientId, name, address, city);
    	Employee emp = new Employee(RandomDC.getRandomInt(400, 500), Names.getRandomFirstName(), Names.getRandomLasstName(), RandomDC.getRandomInt(400, 500)+" Street", "Toronto", Names.getRandomPhoneNumber());
    	Employee emp1 = new Employee(RandomDC.getRandomInt(400, 500), Names.getRandomFirstName(), Names.getRandomLasstName(), RandomDC.getRandomInt(400, 500)+" Street", "Toronto", Names.getRandomPhoneNumber());
    	
    	List<Employee> emps = new LinkedList<Employee>();
    	emps.add(emp);
    	emps.add(emp1);
    	
    	client.setEmployees(emps);
    	
    	_log.info("{addClient} client : "+client);
    	
    	clientRepository.save(client);		
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
    	
    	// this formula should be removed
    	Mongo mongo = new Mongo("localhost", 27017);
  	  	DB db = mongo.getDB("test");  	  	
    	DBCollection collection = db.getCollection("client");
    	
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("address", address));
		if(name != null)
			newDocument.append("$set", new BasicDBObject().append("name", name));
		if(city != null)
			newDocument.append("$set", new BasicDBObject().append("city", city));
		
		BasicDBObject searchQuery = new BasicDBObject().append("clientId", clientId);		
		collection.update(searchQuery, newDocument);
    }
    /*
     * 
     * (non-Javadoc)
     * @see org.driver.check.service.ClientService#addEmployee(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     * 
     * plain query:
     * 	db.clients.update({"_id": 1},  {$addToSet : { "employees" : [ {"name"  : "Feli", "position" : "Director"}, {"name"  : "Salma", "position" : "Tester"}] }});
     */
    @Override
    public void addEmployee(final int clientId, final Integer empId, final String firstName, final String lastName, final String address, final String city, final String telephone){
    	
    	
    	Mongo mongo = new Mongo("localhost", 27017);
  	  	DB db = mongo.getDB("test");  	  	
    	DBCollection collection = db.getCollection("client");
    	
		BasicDBObject newDocument = new BasicDBObject();
		
		Employee employee = new Employee(empId, firstName, lastName, address, city, telephone);
		
		newDocument.append("$addToSet", new BasicDBObject().append("employees", employee));	
		
		BasicDBObject searchQuery = new BasicDBObject().append("clientId", clientId);		
		collection.update(searchQuery, newDocument);
		
    }    
    
    @Override
    public void addEmployee(final int clientId, final Employee employee){
    	Mongo mongo = new Mongo("localhost", 27017);
  	  	DB db = mongo.getDB("test");  	  	
    	DBCollection collection = db.getCollection("client");
    	
		BasicDBObject newDocument = new BasicDBObject();
		
		newDocument.append("$addToSet", new BasicDBObject().append("employees", employee));	
		
		BasicDBObject searchQuery = new BasicDBObject().append("clientId", clientId);		
		collection.update(searchQuery, newDocument);
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
	
	
	
	@Override
	public void addCustomer() {
		//repository.deleteAll();
		
		//customerRepository.save(new Customer("Alice", "Smith"));
		//customerRepository.save(new Customer("Bob", "Smith"));
	}
}
