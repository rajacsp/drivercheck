package org.driver.check.rest;

import static org.driver.check.util.Names.getRandomFirstName;
import static org.driver.check.util.Names.getRandomPhoneNumber;
import static org.driver.check.util.RandomDC.getRandomInt;
import static org.driver.check.util.RandomDC.getUniqueId;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.driver.check.model.TestResult;
import org.driver.check.morphia.model.ClientMOM;
import org.driver.check.morphia.model.EmployeeMOM;
import org.driver.check.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientRestController {
	
	private static Logger _log = LoggerFactory.getLogger(ClientRestController.class);

    private final ClientService clientService;

    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 */
    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/
	 */
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public @ResponseBody Collection<Client> findAll() {
        return this.clientService.findAll();
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/{_id}
	 */
    @RequestMapping(value = "/clients/{_id}", method = RequestMethod.GET)
    public @ResponseBody Client findClientBy_id(@PathVariable("_id") String _id) {
        return clientService.findBy_id(_id);        
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/employees/{_id}
	 */
    @RequestMapping(value = "/clients/by/name/{clientName}", method = RequestMethod.GET)
    public @ResponseBody List<ClientMOM> findClientByClientName(@PathVariable("clientName") String clientName) {
        return clientService.findByClientName(clientName);        
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/by/employee/id/{empId}
	 */
    @RequestMapping(value = "/clients/by/employee/id/{empId}", method = RequestMethod.GET)
    public @ResponseBody List<ClientMOM> findClientByEmpId(@PathVariable("empId") String empId) {
        return clientService.findByEmployeeId(empId);        
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/by/employee/city/{city}
	 * 
	 * buggy because of EmployeeMOM implementation
	 */
    @RequestMapping(value = "/clients/by/employee/city/{city}", method = RequestMethod.GET)
    public @ResponseBody List<EmployeeMOM> findByEmployeeCity(@PathVariable("city") String employeeCity) {
        return clientService.findByEmployeeCity(employeeCity);       
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/{_id}/update
	 */
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public @ResponseBody Client createClient(@RequestBody Client client) {
    	if(client.get_id() == null){ //new client
    		client.set_id(getUniqueId()); //may not be needed
    		clientService.saveClient(client);
    		return client;
    	}
    	
    	_log.info("{create} editing client "+client.get_id());
    	Client existingClient = clientService.findBy_id(client.get_id());    		  
    	BeanUtils.copyProperties(client, existingClient, "_id");
    	clientService.updateClient(client);
    	
    	return client;
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/
	 */
    @RequestMapping(value = "/clients", method = RequestMethod.DELETE)
    public @ResponseBody void deleteClient(@RequestParam(value="_id") String _id) {
    	clientService.deleteClientBy_id(_id);
    	_log.info("{deleteClient} method");    	
    }    
    
    /*
     * POST (add/edit employee)
     * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/{_id}/employees
	 */    
    @RequestMapping(value = "/clients/{_id}/employees", method = RequestMethod.POST)
    public @ResponseBody void saveEmployee(
    		@PathVariable("_id") String _id,
    		@RequestBody Employee employee
    	) {
    	
    	if(employee.getEmpId() == null){
    		employee.setEmpId(getUniqueId());
    		_log.info("{saveEmployee} adding employee _id : "+_id+", employee : "+employee);
    		
    		//clientService.addEmployee(_id, employee);   // not working; should be fixed later 		
    		
    		clientService.addEmployee(_id, employee.getEmpId(), employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getCity(), employee.getTelephone());        	        	
    	}
    	
    	// assume the remaining action is 'EDIT'
    	_log.info("{saveEmployee} editing employee _id : "+_id+", employee : "+employee);
    	clientService.updateEmployee(_id, employee);    	
    }    
    
    /*
     * DELETE - delete client
     * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/{_id}/employees
	 */
    @RequestMapping(value = "/clients/{_id}/employees", method = RequestMethod.DELETE)
    public @ResponseBody void deleteEmployee(
    		@PathVariable("_id") String _id,
    		@RequestParam(value="empId") String empId
    	) {    	
    	
    	_log.info("{deleteEmployee} _id : "+_id+", empId : "+empId);
    	
    	clientService.removeEmployee(_id, empId);
    }
    
    /*
     * POST (add/edit test)
     * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/{_id}/{empId}/
	 */    
    @RequestMapping(value = "/clients/{_id}/{empId}/tests", method = RequestMethod.POST)
    public @ResponseBody void saveTest(
    		@PathVariable("_id") String _id,
    		@PathVariable("empId") String empId,
    		@RequestBody TestResult test
    	) {
    	
    	_log.info("{saveTest} _id : "+_id+", empId : "+empId);
    	
    	List<TestResult> tests = TestResult.getRandomTests();
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/find/by/name?name={name}
	 */
    @RequestMapping(value = "/clients/find/by/name", method = RequestMethod.GET)
    public @ResponseBody Collection<Client> findByName(
    		@RequestParam("name") String name
    		) {
        return clientService.findByName(name);
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/find/by/employee/first_name?name=Heejun
	 * 
	 * note: buggy; has to be fixed
	 */
    @RequestMapping(value = "/clients/find/by/employee/first_name", method = RequestMethod.GET)
    public @ResponseBody <T> T findClientByEmployeeFirstName(
    		@RequestParam("name") String employeeFirstName
    		) {
        return (T) clientService.findByEmployeeFirstName(employeeFirstName);
    }
    
    /*
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/find/by/employee/last_name?name=Mathpal
	 * 
	 * note: buggy; has to be fixed
	 */
    @RequestMapping(value = "/clients/find/by/employee/last_name", method = RequestMethod.GET)
    public @ResponseBody <T> T findClientByEmployeeLastName(
    		@RequestParam("name") String employeeLastName
    		) {
        return (T) clientService.findByEmployeeLastName(employeeLastName);
    }
    
    /*
     * delete [Method = GET] // should be moved to POST/DELETE
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
	 */
    @RequestMapping(value = "/client/delete/{_id}", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> deleteClientBy_id(@PathVariable("id") String _id) {
    	
    	clientService.deleteClientBy_id(_id);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    /*
     * delete all
	 * 
	 * possible url:
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
	 */
    @RequestMapping(value = "/client/{_id}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, String> deleteClientBy_id1(@PathVariable("_id") String _id) {
    	
    	clientService.deleteClientBy_id(_id);
    	
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
    		@RequestParam("_id") String _id,
    		@RequestParam("name") String name,
    		@RequestParam("address") String address,
    		@RequestParam("city") String city,
    		
    		// if dummy values needed, use this parameter
    		@RequestParam(value = "dummy", required = false, defaultValue="false") boolean addDummyValues,
    		
    		// add client with or without employee
    		@RequestParam(value = "withEmployee", required = false, defaultValue="false") Boolean withEmployee,    		
    		
    		@RequestParam(value = "emp_id", required = false) String empId,
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
    		employee = new Employee(getUniqueId(), getRandomFirstName(), getRandomFirstName(), getRandomInt(1, 100)+", Street", "Toronto", getRandomPhoneNumber(), tests);
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
     		clientService.addClient(_id, name, address, city,employees);
     	} else{ //assume its plain call (witout employee,test) 
     		clientService.addClient(_id, name, address, city);
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
    @RequestMapping(value = "/client/{_id}/add/employee", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> addEmployee(
    		@PathVariable(value = "_id") String _id,
    		@RequestParam(value = "dummy", required = false) boolean addDummyValues,
    		
    		// pass original values
    		@RequestParam(value = "emp_id", required = false) String empId,
    		@RequestParam(value = "first_name", required = false) String firstName,
    		@RequestParam(value = "last_name", required = false) String lastName,
    		@RequestParam(value = "address", required = false) String address,
    		@RequestParam(value = "city", required = false) String city,
    		@RequestParam(value = "telephone", required = false) String telephone
    		) {
    	
    	Employee employee = null;
    	if(addDummyValues){    		
    		employee = new Employee(getUniqueId(), getRandomFirstName(), getRandomFirstName(), getRandomInt(1, 100)+", Street", "Toronto", getRandomPhoneNumber());
    	} else {    	
    		employee = new Employee(empId, firstName, lastName, address, city, telephone);
    		List<Employee> employees = new LinkedList<Employee>();
    		employees.add(employee);
    	}
    	
    	clientService.addEmployee(_id, empId, firstName, lastName, address, city, telephone);    	
    	
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
    @RequestMapping(value = "/client/{_id}/update/employee/1", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> updateEmployee(
    		@PathVariable(value = "_id") String _id,
    		
    		// pass original values
    		@RequestParam(value = "emp_id", required = false) String empId,
    		@RequestParam(value = "first_name", required = false) String firstName,
    		@RequestParam(value = "last_name", required = false) String lastName,
    		@RequestParam(value = "address", required = false) String address,
    		@RequestParam(value = "city", required = false) String city,
    		@RequestParam(value = "telephone", required = false) String telephone
    		) {
    	
    	Employee employee = new Employee(empId, firstName, lastName, address, city, telephone);
		List<Employee> employees = new LinkedList<Employee>();
		employees.add(employee);    	
    	
    	clientService.updateEmployee(_id, empId, firstName, lastName, address, city, telephone);    	
    	
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
    @RequestMapping(value = "/client/{_id}/update/employee", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> updateEmployee1(
    		@PathVariable(value = "_id") String _id,    		
    		@RequestBody Employee employee
    		) {
    	
    	_log.info("{updateEmployee} _id : "+_id);
		
    	clientService.updateEmployee(_id, employee);    	
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }    
    
    /*
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/{_id}/remove/employee?empId={emp_id}
	 * 		
	 */
    @RequestMapping(value = "/client/{_id}/remove/employee", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> removeEmployee(
    		@PathVariable(value = "_id") String _id,
    		@RequestParam(value = "emp_id", required = false) String empId
    		) {
    	
    	_log.info("{removeEmployee} empId : "+empId);
    	
    	clientService.removeEmployee(_id, empId);  	
    	
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
    		@RequestParam(value = "_id") String _id,
    		@RequestParam(value = "name", required = false) String name,
    		@RequestParam(value = "address", required = false) String address,
    		@RequestParam(value = "city", required = false) String city
    		) {
    	
    	clientService.updateClient(_id, name, address, city);
    	
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
