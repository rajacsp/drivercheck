package org.driver.check.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
public class EmployeeServiceImpl implements EmployeeService {
	
	private static Logger _log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findEmployeeByLastName(String lastName) throws DataAccessException {
    	List<Employee> employeeList = getDummyEmployees();
    	
    	List<Employee> filteredList = new LinkedList<Employee>();
    	for (Employee employee : employeeList) {
			if(employee == null) continue;
			
			if(employee.getLastName() != null && employee.getLastName().equalsIgnoreCase(lastName)){
				filteredList.add(employee);
			}				
		}
        return filteredList;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findAll() throws DataAccessException {  
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "test"));
    	return mongoOps.findAll(Employee.class);
    }
    
    // dummy employees for data-fill up
    private List<Employee> getDummyEmployees(){
    	// id, first_name, last_name, street, city, phone
    	Employee employee1 = new Employee(1, "Raja", "Raman", "Balliol Street", "Toronot", "3632636363");
    	Employee employee2 = new Employee(2, "Chris", "Brown", "Hello Street", "Toronto", "474646464");
    	
    	List<Employee> employeeList = new LinkedList<Employee>();
    	employeeList.add(employee1);
    	employeeList.add(employee2);
    	
    	return employeeList;
    }
    
    @Override
    public void deleteEmployeeByEmpId(final int empId){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "test"));
		Employee p = mongoOps.findOne(query(where("empId").is(empId)), Employee.class);
		_log.info("{deleteEmployeeByEmpId} p "+p);
		mongoOps.remove(p);
    }
    
    @Override
    public void addEmployee(final int empId, final String firstName, final String lastName, final String address, final String city, final String telephone){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "test"));
    	Employee p = new Employee(empId, firstName, lastName, address, city, telephone);
		mongoOps.insert(p);
		_log.info("{addEmployee}: " + p);
    }
    
    @Override
    public void updateEmployee(final int empId, final String firstName, final String lastName, final String address, final String city, final String telephone){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "test"));
    	mongoOps.updateFirst(query(where("empId").is(empId)), update("firstName", firstName), Employee.class);
		mongoOps.updateFirst(query(where("empId").is(empId)), update("lastName", lastName), Employee.class);
		mongoOps.updateFirst(query(where("empId").is(empId)), update("address", address), Employee.class);
		mongoOps.updateFirst(query(where("empId").is(empId)), update("city", city), Employee.class);
		mongoOps.updateFirst(query(where("empId").is(empId)), update("telephone", telephone), Employee.class);
		Employee p = mongoOps.findOne(query(where("empId").is(empId)), Employee.class);
		_log.info("{updateEmployee} Updated : "+p);
    }
    
    @Override
    public List<Employee> findByFirstName(String firstName){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "test"));
    	List<Employee> EmployeeList =  mongoOps.find(query(where("firstName").is(firstName)), Employee.class);		
		return EmployeeList;
    }
    
    @Override
    public List<Employee> findByLastName(String lastName){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "test"));
    	List<Employee> EmployeeList =  mongoOps.find(query(where("lastName").is(lastName)), Employee.class);		
		return EmployeeList;
    }
}
