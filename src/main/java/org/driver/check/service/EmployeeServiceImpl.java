package org.driver.check.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.model.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    	return getDummyEmployees();
    }
    
    // dummy employees for data-fill up
    private List<Employee> getDummyEmployees(){
    	// id, first_name, last_name, street, city, phone
    	Employee employee1 = new Employee(1, "Raja", "Raman", "Balliol Street", "Toronot", "3632636363");
    	Employee employee2 = new Employee(1, "Chris", "Brown", "Hello Street", "Toronto", "474646464");
    	
    	List<Employee> employeeList = new LinkedList<Employee>();
    	employeeList.add(employee1);
    	employeeList.add(employee2);
    	
    	return employeeList;
    }
}
