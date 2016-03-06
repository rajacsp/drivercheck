package org.driver.check.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.driver.check.util.RandomDC.getUniqueId;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.business.constants.Const;
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
public class EmployeeServiceImpl implements EmployeeService, Const {
	
	private static Logger _log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findAll() throws DataAccessException {  
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	return mongoOps.findAll(Employee.class);
    }
    
    @Override
    public void deleteEmployeeByEmpId(final int empId){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
		Employee p = mongoOps.findOne(query(where("empId").is(empId)), Employee.class);
		_log.info("{deleteEmployeeByEmpId} p "+p);
		mongoOps.remove(p);
    }
    
    @Override
    public void addEmployee(final String empId, final String firstName, final String lastName, final String address, final String city, final String telephone){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	Employee p = new Employee(empId, firstName, lastName, address, city, telephone);
		mongoOps.insert(p);
		_log.info("{addEmployee}: " + p);
    }
    
    @Override
    public void updateEmployee(final String empId, final String firstName, final String lastName, final String address, final String city, final String telephone){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	mongoOps.updateFirst(query(where("empId").is(empId)), update("firstName", firstName), Employee.class);
		mongoOps.updateFirst(query(where("empId").is(empId)), update("lastName", lastName), Employee.class);
		mongoOps.updateFirst(query(where("empId").is(empId)), update("address", address), Employee.class);
		mongoOps.updateFirst(query(where("empId").is(empId)), update("city", city), Employee.class);
		mongoOps.updateFirst(query(where("empId").is(empId)), update("telephone", telephone), Employee.class);
		Employee p = mongoOps.findOne(query(where("empId").is(empId)), Employee.class);
		_log.info("{updateEmployee} Updated : "+p);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByFirstName(String firstName){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	List<Employee> EmployeeList =  mongoOps.find(query(where("firstName").is(firstName)), Employee.class);		
		return EmployeeList;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByLastName(String lastName){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	List<Employee> EmployeeList =  mongoOps.find(query(where("lastName").is(lastName)), Employee.class);		
		return EmployeeList;
    }
}
