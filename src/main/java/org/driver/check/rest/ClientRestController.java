package org.driver.check.rest;

import static org.driver.check.util.Names.getRandomFirstName;
import static org.driver.check.util.Names.getRandomPhoneNumber;
import static org.driver.check.util.RandomDC.getRandomInt;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.driver.check.model.TestResult;
import org.driver.check.service.ClientService;
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

    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		http://localhost:3030/drivercheck/api/client/
	 */
    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		http://localhost:3030/drivercheck/api/client/
	 */
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public @ResponseBody Collection<Client> findAll() {
        return this.clientService.findAll();
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		http://localhost:3030/drivercheck/api/client/
	 */
    @RequestMapping(value = "/clients/find", method = RequestMethod.GET)
    public @ResponseBody Collection<Client> findByName(
    		@RequestParam("name") String name
    		) {
        return clientService.findByName(name);
    }
    
    /*
     * delete [Method = GET]
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		http://localhost:3030/drivercheck/api/client/
	 */
    @RequestMapping(value = "/client/delete/{cid}", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> deleteClientByClientId(@PathVariable("cid") Integer clientId) {
    	
    	clientService.deleteClientByClientId(clientId);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    /*
     * delete all
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		http://localhost:3030/drivercheck/api/client/
	 */
    @RequestMapping(value = "/client/delete/all", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> deleteAllClients() {
    	
    	// delete all method should be provided
    	clientService.deleteClientAll();
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    /*
     * delete [Method = DELETE]
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		http://localhost:3030/drivercheck/api/client/
	 */
    @RequestMapping(value = "/client/{cid}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, String> deleteClientByClientId1(@PathVariable("cid") Integer clientId) {
    	
    	clientService.deleteClientByClientId(clientId);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }

    /*
     * add client [Method = GET]
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/add
	 * 		http://localhost:3030/drivercheck/api/client/add?client_id=103&name=AV&address=13_street&city=Toronto
	 */
    @RequestMapping(value = "/client/add", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> addClient(    		
    		@RequestParam("client_id") int clientId,
    		@RequestParam("name") String name,
    		@RequestParam("address") String address,
    		@RequestParam("city") String city,
    		
    		// if dummy values needed, use this parameter
    		@RequestParam(value = "dummy", required = false, defaultValue="false") boolean addDummyValues,
    		
    		// add client with or without employee
    		@RequestParam(value = "withEmployee", required = false, defaultValue="false") Boolean withEmployee,    		
    		
    		@RequestParam(value = "emp_id", required = false) Integer empId,
    		@RequestParam(value = "emp_firstname", required = false) String empFirstName,
    		@RequestParam(value = "emp_lastname", required = false) String empLasName,
    		@RequestParam(value = "emp_address", required = false) String EmployeeAddress,
    		@RequestParam(value = "emp_city", required = false) String employeeCity,
    		@RequestParam(value = "emp_telephone", required = false) String telephone,
    		
    		@RequestParam(value = "withTest", required = false) Boolean withTest,
    		@RequestParam(value = "test_id", required = false) Integer testId    		
    		) {
    	
    	Employee employee = null;
    	
    	if(addDummyValues){    		
        	List<TestResult> tests = TestResult.getRandomTests();        	
    		employee = new Employee(401, getRandomFirstName(), getRandomFirstName(), getRandomInt(1, 100)+", Street", "Toronto", getRandomPhoneNumber(), tests);
    	}else{
    		/*
	    	if(withTest){
	    		
	    		TestResult test = new TestResult(testId, new Date());
	        	List<TestResult> tests = new LinkedList<TestResult>();
	        	tests.add(test);
	        	
	    		employee = new Employee(empId, empFirstName, empLasName, address, city, telephone, tests);
	    	} else
	    	*/
	    	 if(withEmployee){
	    		employee = new Employee(empId, empFirstName, empLasName, address, city, telephone);
	    	}
    	}
    	
    	if(employee != null){
     		List<Employee> employees = new LinkedList<Employee>();
     		employees.add(employee);    	
     		clientService.addClient(clientId, name, address, city,employees);
     	} else{ //assume its plain call (witout employee,test) 
     		clientService.addClient(clientId, name, address, city);
     	}    	
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }

    /*
     * update client; add employee [Method = GET]
     * 
     * mongo plain query:
     * 	db.clients.update({"_id": 2},  {$addToSet : { "employees" : [ {"name"  : "jay", "position" : "senior dev"}, {"name"  : "heejun", "position" : "manager"}] }});
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		
	 */
    @RequestMapping(value = "/client/{clientid}/add/employee", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> addEmployee(
    		@PathVariable(value = "clientid") int clientId,
    		@RequestParam(value = "dummy", required = false) boolean addDummyValues,
    		
    		// pass original values
    		@RequestParam(value = "emp_id", required = false) Integer empId,
    		@RequestParam(value = "first_name", required = false) String firstName,
    		@RequestParam(value = "last_name", required = false) String lastName,
    		@RequestParam(value = "address", required = false) String address,
    		@RequestParam(value = "city", required = false) String city,
    		@RequestParam(value = "telephone", required = false) String telephone
    		) {
    	
    	Employee employee = null;
    	if(addDummyValues){    		
    		employee = new Employee(getRandomInt(401, 500), getRandomFirstName(), getRandomFirstName(), getRandomInt(1, 100)+", Street", "Toronto", getRandomPhoneNumber());
    	} else {    	
    		employee = new Employee(empId, firstName, lastName, address, city, telephone);
    		List<Employee> employees = new LinkedList<Employee>();
    		employees.add(employee);
    	}
    	
    	clientService.addEmployee(clientId, empId, firstName, lastName, address, city, telephone);    	
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    /*
     * update employee [Method = GET]
     * 
     * mongo plain query:
     * 	db.clients.update({"_id": 2},  {$addToSet : { "employees" : [ {"name"  : "jay", "position" : "senior dev"}, {"name"  : "heejun", "position" : "manager"}] }});
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		
	 */
    @RequestMapping(value = "/client/{clientid}/update/employee", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> updateEmployee(
    		@PathVariable(value = "clientid") int clientId,
    		
    		// pass original values
    		@RequestParam(value = "emp_id", required = false) Integer empId,
    		@RequestParam(value = "first_name", required = false) String firstName,
    		@RequestParam(value = "last_name", required = false) String lastName,
    		@RequestParam(value = "address", required = false) String address,
    		@RequestParam(value = "city", required = false) String city,
    		@RequestParam(value = "telephone", required = false) String telephone
    		) {
    	
    	Employee employee = new Employee(empId, firstName, lastName, address, city, telephone);
		List<Employee> employees = new LinkedList<Employee>();
		employees.add(employee);    	
    	
    	clientService.updateEmployee(clientId, empId, firstName, lastName, address, city, telephone);    	
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    /*
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/{clientid}/remove/employee?empId={emp_id}
	 * 		
	 * 		
	 */
    @RequestMapping(value = "/client/{clientid}/remove/employee", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> removeEmployee(
    		@PathVariable(value = "clientid") int clientId,
    		@RequestParam(value = "emp_id", required = false) Integer empId
    		) {
    	
    	System.out.println("{removeEmployee} empId : "+empId);
    	
    	clientService.removeEmployee(empId);  	
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    /*
     * update client [Method = GET]
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/update
	 * 		
	 */
    @RequestMapping(value = "/client/update", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> updateClient(    		
    		@RequestParam(value = "client_id") int clientId,
    		@RequestParam(value = "name", required = false) String name,
    		@RequestParam(value = "address", required = false) String address,
    		@RequestParam(value = "city", required = false) String city
    		) {
    	
    	clientService.updateClient(clientId, name, address, city);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    /*
     * test API
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		
	 */
    @RequestMapping(value = "/client/test", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> testAPI() {
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("id", "230");
    	map.put("name", "Nothing Decided");
    	map.put("city", "Toronto");
    	
    	return map;
    }
    
    /*
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 * 		
	 */
    @RequestMapping(value = "/client/customer/add", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> addCustomer() {
	
    	clientService.addCustomer();
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
}
