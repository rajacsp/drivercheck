package org.driver.check.service;

import java.util.Collection;

import org.driver.check.model.Employee;
import org.driver.check.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClinicServiceImpl implements ClinicService {

	private EmployeeRepository employeeRepository;

    @Autowired
    public ClinicServiceImpl(EmployeeRepository employeeRepository) {
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
        return employeeRepository.findAll();
    }
}
