package org.driver.check.service;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.driver.check.util.RandomDC.getUniqueId;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.driver.check.business.constants.Const;
import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.driver.check.model.TestResult;
import org.driver.check.repository.ClientRepository;
import org.driver.check.repository.CustomerRepository;
import org.driver.check.util.Names;
import org.driver.check.util.RandomDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;

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
    @Transactional(readOnly = true)
    public Client findBy_id(String _id) throws DataAccessException{
    	return clientRepository.findBy_id(_id);
    }
    
    @Override
    public void deleteClientAll(){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
		// remove all
    }
    
    @Override
    public void addClient(final String _id, final String name, final String address, final String city){   	
    	
    	Client client = new Client(_id, name, address, city);
    	Employee emp = new Employee(getUniqueId(), Names.getRandomFirstName(), Names.getRandomLasstName(), RandomDC.getRandomInt(400, 500)+" Street", "Toronto", Names.getRandomPhoneNumber());
    	Employee emp1 = new Employee(getUniqueId(), Names.getRandomFirstName(), Names.getRandomLasstName(), RandomDC.getRandomInt(400, 500)+" Street", "Toronto", Names.getRandomPhoneNumber());
    	
    	List<Employee> emps = new LinkedList<Employee>();
    	emps.add(emp);
    	emps.add(emp1);
    	
    	client.setEmployees(emps);
    	
    	_log.info("{addClient} client : "+client);
    	
    	clientRepository.save(client);		
    }
    
    /*
     * 
     * (non-Javadoc)
     * @see org.driver.check.service.ClientService#saveClient(org.driver.check.model.Client)
     * 
     * Note: As this method is clearing existing employees, this method call has to to be used only for add purposes, not for updating purposes
     * 		
     */
    @Override
    public void saveClient(final Client client){    	
    	_log.info("{addClient} client : "+client);    	
    	clientRepository.save(client);		
    }
    
    @Override
    public void addClient(final String _id, final String name, final String address, final String city, final List<Employee> employees){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	Client p = new Client(_id, name, address, city, employees);
		mongoOps.insert(p);	
    }
    
    @Override
    public void updateClient(final Client client){
    	Mongo mongo = new Mongo("localhost", 27017);
  	  	DB db = mongo.getDB(MONGO_DB_NAME);  	  	
    	DBCollection collection = db.getCollection(COLLECTION_BASE);   		
		
		DBObject query = new BasicDBObject("_id", client.get_id());
        DBObject update = new BasicDBObject();
        BasicDBObject bObject = new BasicDBObject("_id", client.get_id());
        
    	bObject.append("name", client.getName());
    	bObject.append("address", client.getAddress());        
    	bObject.append("city", client.getCity());
        		
        update.put("$set", bObject);
         
        WriteResult result = collection.update(query, update);
        _log.info("{updateClient} result : "+result);
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
    public void updateClient(final String _id, final String name, final String address, final String city){
    	
    	// this formula should be removed
    	Mongo mongo = new Mongo("localhost", 27017);
  	  	DB db = mongo.getDB(MONGO_DB_NAME);  	  	
    	DBCollection collection = db.getCollection(COLLECTION_BASE);   		
		
		DBObject query = new BasicDBObject("_id", _id);
        DBObject update = new BasicDBObject();
        BasicDBObject bObject = new BasicDBObject("_id", _id);
        
    	bObject.append("name", name);
    	bObject.append("address", address);        
    	bObject.append("city", city);
        		
        update.put("$set", bObject);
         
        WriteResult result = collection.update(query, update);
        _log.info("{updateClient} result : "+result);
    }
    
    public void addEmployee(final String _id, final String empId, final String firstName, final String lastName, final String address, final String city, final String telephone) {
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME)); 
		Query searchUserQuery = new Query(Criteria.where("_id").is(_id));

		Employee employee = new Employee(empId, firstName, lastName, address, city, telephone);
		
		List<TestResult> tests = TestResult.getRandomTests();
		employee.setTests(tests);

		Update update = Update.update("_id", _id).addToSet("employees", employee);
		WriteResult result = mongoOps.updateFirst(searchUserQuery, update, Client.class);
		
		_log.info("{addEmployee} result : "+result);		
	}
    
    @Override
    public void addEmployee(final String _id, final Employee employee){
    	Mongo mongo = new Mongo("localhost", 27017);
  	  	DB db = mongo.getDB(MONGO_DB_NAME);  	  	
    	DBCollection collection = db.getCollection(COLLECTION_BASE);
    	
		BasicDBObject newDocument = new BasicDBObject();
		
		newDocument.append("$addToSet", new BasicDBObject().append("employees", employee));	
		
		BasicDBObject searchQuery = new BasicDBObject().append("_id", _id);		
		collection.update(searchQuery, newDocument);
    }    
    
    @Override
    public void addTest(final String _id, final String empId, final TestResult test){
    	Mongo mongo = new Mongo("localhost", 27017);
  	  	DB db = mongo.getDB(MONGO_DB_NAME);  	  	
    	DBCollection collection = db.getCollection(COLLECTION_BASE);
    	
		BasicDBObject newDocument = new BasicDBObject();
		
		newDocument.append("$addToSet", new BasicDBObject().append("tests", test));	
		
		BasicDBObject searchQuery = new BasicDBObject().append("_id", _id);		
		collection.update(searchQuery, newDocument);
    }
    
    @Override
    public void saveEmployee(final String _id, final Employee employee){
    	Mongo mongo = new Mongo("localhost", 27017);
  	  	DB db = mongo.getDB(MONGO_DB_NAME);  	  	
    	DBCollection collection = db.getCollection(COLLECTION_BASE);
    	
		BasicDBObject newDocument = new BasicDBObject();
		
		newDocument.append("$addToSet", new BasicDBObject().append("employees", employee));	
		
		BasicDBObject searchQuery = new BasicDBObject().append("_id", _id);		
		collection.update(searchQuery, newDocument);
    }
    
    @Override
  	public void updateEmployee(final String _id, final String empId, final String firstName, final String lastName, 
  			final String address, final String city, final String telephone) {
  		
		MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB(MONGO_DB_NAME);
         
        DBCollection collection = db.getCollection(COLLECTION_BASE);
         
        //Update sub-document in a single document
        DBObject query = new BasicDBObject("employees.empId", empId);
        DBObject update = new BasicDBObject();
        BasicDBObject bObject = new BasicDBObject("employees.$.empId", empId);
        
        if(firstName != null)
        	bObject.append("employees.$.firstName", firstName);
        if(lastName != null)
        	bObject.append("employees.$.lastName", lastName);
        
        if(empId != null)
        	bObject.append("employees.$.empId", empId);
        
        if(address != null)
        	bObject.append("employees.$.address", address);
        
        if(city != null)
        	bObject.append("employees.$.city", city);
        
        if(telephone != null)
        	bObject.append("employees.$.telephone", telephone);
        		
        update.put("$set", bObject);
         
        WriteResult result = collection.update(query, update);      
         
        mongo.close();  		
  	}
    
    @Override
    public void updateEmployee(final String _id, final Employee employee){
    	MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB(MONGO_DB_NAME);
         
        DBCollection collection = db.getCollection(COLLECTION_BASE);
         
        //Update sub-document in a single document
        DBObject query = new BasicDBObject("employees.empId", employee.getEmpId());
        DBObject update = new BasicDBObject();
        BasicDBObject bObject = new BasicDBObject("employees.$.empId", employee.getEmpId());
        
        if(employee.getFirstName() != null)
        	bObject.append("employees.$.firstName", employee.getFirstName());
        
        if(employee.getLastName() != null)
        	bObject.append("employees.$.lastName", employee.getLastName());
        
        if(employee.getEmpId() != null)
        	bObject.append("employees.$.empId", employee.getEmpId());
        
        if(employee.getAddress() != null)
        	bObject.append("employees.$.address", employee.getAddress());
        
        if(employee.getCity() != null)
        	bObject.append("employees.$.city", employee.getCity());
        
        if(employee.getTelephone() != null)
        	bObject.append("employees.$.telephone", employee.getTelephone());
        		
        update.put("$set", bObject);
         
        WriteResult result = collection.update(query, update);
        _log.info("{updateEmployee} result : "+result);
         
        mongo.close();
    }
  	
    @Override
    public void removeEmployee(final String _id, final String empId){
    	MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB(MONGO_DB_NAME);
         
        DBCollection collection = db.getCollection(COLLECTION_BASE);
         
        BasicDBObject match = new BasicDBObject("employees.empId", empId); //to match your direct app document
        BasicDBObject update2 = new BasicDBObject("empId", empId);
        collection.update(match, new BasicDBObject("$pull", new BasicDBObject("employees", update2)));
         
        mongo.close();
    }
    
    @Override
    public void updateEmployees(final String _id, List<Employee> employees){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	mongoOps.updateFirst(query(where("_id").is(_id)), update("employees", employees), Client.class);    	    	
		
		Client p = mongoOps.findOne(query(where("_id").is(_id)), Client.class);
		
		_log.info("{updateEmployees}: " + p);		
    }
    
    @Override
    public <T> T findClientByEmployeeFirstName(String employeeName) {
    	DBObject dbObject = QueryBuilder.start("employees.firstName").is(employeeName).get();
		
    	DBObject or2 = QueryBuilder.start().or(dbObject).get();

		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB(MONGO_DB_NAME);
		DBCollection collection = db.getCollection(COLLECTION_BASE);
		return (T) collection.find(or2).getCollection();
	}
    
    // dummy clients
    private List<Client> getDummyClients(){
    	// id, name, street, city
    	Client client1 = new Client(""+103, "Driver Check", "11, Street", "Toronto");
    	Client client2 = new Client(""+104, "SmileGate", "12, Street", "Toronto");
    	Client client3 = new Client(""+104, "GTECH", "13, Street", "Toronto");
    	
    	List<Client> clientList = new LinkedList<Client>();
    	clientList.add(client1);
    	clientList.add(client2);
    	clientList.add(client3);
    	
    	return clientList;
    }

	@Override
	public void updateClient(String _id, String name, String address, String city, List<Employee> employees) {
	}
	
	@Override
	public void addCustomer() {
		//repository.deleteAll();
		
		//customerRepository.save(new Customer("Alice", "Smith"));
		//customerRepository.save(new Customer("Bob", "Smith"));
	}

	@Override
	public void deleteClientBy_id(String _id) {
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
		Client p = mongoOps.findOne(query(where("_id").is(_id)), Client.class);
		_log.info("{deleteClientBy_id} p "+p);		
		mongoOps.remove(p);		
	}
}
