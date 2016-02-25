package org.driver.check.rest;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.driver.check.model.Employee;
import org.driver.check.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public @ResponseBody Collection<Employee> findAll() {
        return this.employeeService.findAll();
    }
    
    // delete [Method = GET]
    @RequestMapping(value = "/employee/delete/{empId}", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> deleteClientByClientId(@PathVariable("empId") Integer empId) {
    	
    	employeeService.deleteEmployeeByEmpId(empId);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // delete [Method = DELETE]
    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, String> deleteClientByClientId1(@PathVariable("empId") Integer empId) {
    	
    	employeeService.deleteEmployeeByEmpId(empId);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // add employee [Method = GET]
    @RequestMapping(value = "/employee/add", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> addEmployee(    		
    		@RequestParam("emp_id") int empId,
    		@RequestParam("first_name") String firstName,
    		@RequestParam("last_name") String lastName,
    		@RequestParam("address") String address,
    		@RequestParam("city") String city,
    		@RequestParam("telephone") String telephone
    		) {
    	
    	employeeService.addEmployee(empId, firstName, lastName, address, city, telephone);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // updated employee[Method = GET]
    @RequestMapping(value = "/employee/update", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> updateEmployee(    		
    		@RequestParam("emp_id") int empId,
    		@RequestParam("first_name") String firstName,
    		@RequestParam("last_name") String lastName,
    		@RequestParam("address") String address,
    		@RequestParam("city") String city,
    		@RequestParam("telephone") String telephone
    		) {
    	
    	employeeService.updateEmployee(empId, firstName, lastName, address, city, telephone);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // Add an option to search employees by name
    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public @ResponseBody Collection<Employee> findByName(@PathVariable("name") Integer name) {
    	// will be provided later
    	return null;
    }
}
