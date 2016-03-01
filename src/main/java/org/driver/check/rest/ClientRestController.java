package org.driver.check.rest;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.driver.check.model.TestResult;
import org.driver.check.service.ClientService;
import org.driver.check.util.RandomDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientRestController {

    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public @ResponseBody Collection<Client> findAll() {
        return this.clientService.findAll();
    }
    
    // delete [Method = GET]
    @RequestMapping(value = "/client/delete/{cid}", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> deleteClientByClientId(@PathVariable("cid") Integer clientId) {
    	
    	clientService.deleteClientByClientId(clientId);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // delete [Method = DELETE]
    @RequestMapping(value = "/client/{cid}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, String> deleteClientByClientId1(@PathVariable("cid") Integer clientId) {
    	
    	clientService.deleteClientByClientId(clientId);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // add client [Method = GET]
    @RequestMapping(value = "/client/add", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> addClient(    		
    		@RequestParam("client_id") int clientId,
    		@RequestParam("name") String name,
    		@RequestParam("address") String address,
    		@RequestParam("city") String city,
    		
    		@RequestParam(value = "addDummy", required = false) Boolean addDummyValues,
    		
    		@RequestParam(value = "withEmployee", required = false) Boolean withEmployee,
    		@RequestParam(value = "employee_id", required = false) Integer empId,
    		@RequestParam(value = "employee_firstname", required = false) String empFirstName,
    		@RequestParam(value = "employee_lastname", required = false) String empLasName,
    		@RequestParam(value = "employee_address", required = false) String EmployeeAddress,
    		@RequestParam(value = "employee_city", required = false) String employeeCity,
    		@RequestParam(value = "employee_telephone", required = false) String telephone,
    		
    		@RequestParam(value = "withTest", required = false) Boolean withTest,
    		@RequestParam(value = "test_id", required = false) Integer testId    		
    		) {
    	
    	Employee employee = null;
    	
    	if(addDummyValues){    		
        	List<TestResult> tests = TestResult.getRandomTests();        	
    		employee = new Employee(401, "Raja", "Raman", "12, Street", "Toronto", "4444444444", tests);
    	}else{    	
	    	if(withTest){
	    		
	    		TestResult test = new TestResult(testId, new Date());
	        	List<TestResult> tests = new LinkedList<TestResult>();
	        	tests.add(test);
	        	
	    		employee = new Employee(empId, empFirstName, empLasName, address, city, telephone, tests);
	    	} else if(withEmployee){
	    		employee = new Employee(empId, empFirstName, empLasName, address, city, telephone);
	    	}
    	}
    	
    	List<Employee> employees = new LinkedList<Employee>();
    	employees.add(employee);
    	
    	clientService.addClient(clientId, name, address, city,employees);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // update client [Method = GET]
    @RequestMapping(value = "/client/update", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> updateClient(    		
    		@RequestParam("client_id") int clientId,
    		@RequestParam("name") String name,
    		@RequestParam("address") String address,
    		@RequestParam("city") String city
    		) {
    	
    	clientService.updateClient(clientId, name, address, city);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // test API
    @RequestMapping(value = "/client/test", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> testAPI() {
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("id", "230");
    	map.put("name", "Nothing Decided");
    	map.put("city", "Toronto");
    	
    	return map;
    }
}
