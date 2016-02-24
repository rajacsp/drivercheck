package org.driver.check.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.model.Employee;
import org.driver.check.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findEmployeeByLastName(String lastName) throws DataAccessException {
        return employeeRepository.findByLastName(lastName);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findAll() throws DataAccessException {  
    	// add dummy values for testing; later it will be replaced by mongodb
    
    	Employee employee1 = new Employee(1, "Raja", "Raman", "Balliol Street", "Toronot", "3632636363");
    	Employee employee2 = new Employee(1, "Chris", "Brown", "Hello Street", "Toronto", "474646464");
    	
    	List<Employee> employeeList = new LinkedList<Employee>();
    	employeeList.add(employee1);
    	employeeList.add(employee2);
    	
        return employeeList;
    }
}
