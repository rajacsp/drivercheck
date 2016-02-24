package org.driver.check.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.model.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.Mongo;


@Service
public class EmployeeServiceImpl implements EmployeeService {

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
    	Employee employee1 = new Employee("Raja", "Raman", "Balliol Street", "Toronot", "3632636363");
    	Employee employee2 = new Employee("Chris", "Brown", "Hello Street", "Toronto", "474646464");
    	
    	List<Employee> employeeList = new LinkedList<Employee>();
    	employeeList.add(employee1);
    	employeeList.add(employee2);
    	
    	return employeeList;
    }
}
