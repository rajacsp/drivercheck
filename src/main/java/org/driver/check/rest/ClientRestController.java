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
import org.driver.check.morphia.service.ClientMorphiaService;
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

	@Autowired
    private ClientService clientService;
    
	@Autowired
    private ClientMorphiaService clientMorphiaService;
   
	/**
	 * 
	 * Find All Clients
	 * 
	 * [HTTP Method : GET]
	 * 
	 * @return
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/
	 */
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public @ResponseBody Collection<Client> findAll() {
        return this.clientService.findAll();
    }
    
    /**
     * Find Client by Id
     * 
     * [HTTP Method : GET]
     * 
     * @param _id
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/{_id}
     */
    @RequestMapping(value = "/clients/{_id}", method = RequestMethod.GET)
    public @ResponseBody Client findClientBy_id(@PathVariable("_id") String _id) {
        return clientService.findBy_id(_id);        
    }
    
    /**
     * 
     * Find Client by Name
     * 
     * [HTTP Method : GET]
     * 
     * @param clientName
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/by/name/{clientName}
     */
    @RequestMapping(value = "/clients/by/name/{clientName}", method = RequestMethod.GET)
    public @ResponseBody List<ClientMOM> findClientByClientName(@PathVariable("clientName") String clientName) {
        return clientService.findByClientName(clientName);        
    }
    
    /**
     * 
     * Find Employee by EmpId
     * 
     * [HTTP Method : GET]
     * 
     * @param empId
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/employees/{empId}
     */
    @RequestMapping(value = "/employees/{empId}", method = RequestMethod.GET)
    public @ResponseBody EmployeeMOM findEmployeeById(@PathVariable("empId") String empId) {
    	_log.info("{findEmployeeById} empId "+empId);
        return clientService.findEmployeeByEmpId(empId);        
    }
    
    
    /**
     * 
     * Find Tests by id
     * 
     * [HTTP Method : GET]
     * 
     * @param _id
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/testresults/{_id}
     */
    @RequestMapping(value = "/testresults/{_id}", method = RequestMethod.GET)
    public @ResponseBody Client findTestResults(@PathVariable("_id") String _id) {
        return clientService.findBy_id(_id);        
    }    
    
    /**
     * 
     * Find Employees By Employee First Name
     * 
     * [HTTP Method : GET]
     * 
     * @param firstName
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/employees/{firstName}
     */
    @RequestMapping(value = "/employees/firstname/{firstName}", method = RequestMethod.GET)
    public @ResponseBody List<EmployeeMOM> findEmployeeByEmployeeFirstName(@PathVariable("firstName") String firstName) {
        return clientService.findEmployeeByEmployeeFirstName(firstName);        
    }
    
    /**
     * 
     * Find Clients By EmpId
     * 
     * [HTTP Method : GET]
     * 
     * @param empId
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/by/employee/id/{empId}
     */
    @RequestMapping(value = "/clients/by/employee/id/{empId}", method = RequestMethod.GET)
    public @ResponseBody List<ClientMOM> findClientByEmpId(@PathVariable("empId") String empId) {
        return clientService.findByEmployeeId(empId);        
    }
    
    /**
     * 
     * Find Employee by empId
     * 
     * [HTTP Method : GET]
     * 
     * @param empId
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/by/employee/id/{empId}
     */
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public @ResponseBody <T> T findEmployeesByEmployeeId(@RequestParam(value = "empId", defaultValue="empty") String empId) {
    	
    	if(empId.equalsIgnoreCase("empty")){
    		return (T) clientService.findEmployees();
    	}
    	
        return (T) clientService.findEmployeeByEmpId(empId);        
    }
    
    /**
     * 
     * Find Employee By Employee City
     * 
     * [HTTP Method : GET]
     * 
     * @param employeeCity
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/by/employee/city/{city}
	 * 
	 * note:
	 * 	buggy because of EmployeeMOM implementation
     */
    @RequestMapping(value = "/clients/by/employee/city/{city}", method = RequestMethod.GET)
    public @ResponseBody List<EmployeeMOM> findByEmployeeCity(@PathVariable("city") String employeeCity) {
        return clientService.findByEmployeeCity(employeeCity);       
    }
    
    /**
     * 
     * Update Client
     * 
     * [HTTP Method : POST]
     * 
     * @param client
     * @return
     * 
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
    
    /**
     * 
     * Delete Client by Id
     * 
     * [HTTP Method : DELETE]
     * 
     * @param _id
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/
     */
    @RequestMapping(value = "/clients", method = RequestMethod.DELETE)
    public @ResponseBody void deleteClient(@RequestParam(value="_id") String _id) {
    	clientService.deleteClientBy_id(_id);
    	_log.info("{deleteClient} method");    	
    }    
    
    /**
     * 
     * Add / Edit Employee Under a specific client
     * 
     * [HTTP Method : POST]
     * 
     * @param _id
     * @param employee
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
    
    /**
     * 
     * Delete Employee under a specific Client
     * 
     * [HTTP Method : DELETE]
     * 
     * @param _id
     * @param empId
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
    
    /**
     * 
     * Add / Edit Test under a specific Test
     * 
     * [HTTP Method : POST]
     * 
     * @param empId
     * @param test
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/employees/{empId}/tests
     */
    @RequestMapping(value = "/clients/employees/{empId}/tests", method = RequestMethod.POST)
    public @ResponseBody void saveTest(    		
    		@PathVariable("empId") String empId,
    		@RequestBody TestResult test
    	) {
    	
    	_log.info("{saveTest} empId : "+empId+", test : "+test);
    	
    	clientMorphiaService.addTest("1", empId, test);
    }
    
    /**
     * 
     * Add / Edit Test under a specific Employee
     * 
     * [HTTP Method : GET]
     * 
     * @param empId
     * @param test
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/employees/{empId}/tests
     */
    @RequestMapping(value = "/clients/employees/{empId}/tests", method = RequestMethod.GET)
    public @ResponseBody void saveTestByGet(    		
    		@PathVariable("empId") String empId,
    		@RequestBody TestResult test
    	) {
    	
    	_log.info("{saveTest} empId : "+empId+", test : "+test);
    	
    	//clientService.updateTest(empId, test);
    	
    	clientMorphiaService.addTest("1", empId, test);		
		
		//System.out.println(results);
    }
    
    /**
     * Find Clients by Name
     * 
     * [HTTP Method : GET]
     * 
     * @param name
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/clients/find/by/name?name={name}
     */
    @RequestMapping(value = "/clients/find/by/name", method = RequestMethod.GET)
    public @ResponseBody Collection<Client> findByName(
    		@RequestParam("name") String name
    		) {
        return clientService.findByName(name);
    }
    
    /**
     * 
     * Find Clients by Employee First Name
     * 
     * [HTTP Method : GET]
     * 
     * @param employeeFirstName
     * @return
     * 
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
    
    /**
     * 
     * Find Clients by Employee Last Name
     * 
     * [HTTP Method : GET]
     * 
     * @param employeeLastName
     * @return
     * 
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
    
    /**
     * 
     * Delete Client by id
     * 
     * [HTTP Method : GET]
     * 
     * @param _id
     * @return
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
    
    /**
     * 
     * Delete All Clients
     * 
     * [HTTP Method : GET]
     * 
     * @return
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
    
    /**
     * 
     * Delete Client by id
     * 
     * [HTTP Method : DELETE]
     * 
     * @param _id
     * @return
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

    /**
     * 
     * Add Client
     * 
     * [HTTP Method : GET]
     * 
     * @param _id
     * @param name
     * @param address
     * @param city
     * @param addDummyValues
     * @param withEmployee
     * @param empId
     * @param empFirstName
     * @param empLasName
     * @param EmployeeAddress
     * @param employeeCity
     * @param telephone
     * @param withTest
     * @param testId
     * @return
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

    /**
     * 
     * Update Client	(add employee under client)
     * 
     * [HTTP Method = GET]			
     * 
     * @param _id
     * @param addDummyValues
     * @param empId
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param telephone
     * @return
     * 
     * mongo plain query:
     * 	db.clients.update({"_id": 2},  {$addToSet : { "employees" : [ {"name"  : "jay", "position" : "senior dev"}, {"name"  : "heejun", "position" : "manager"}] }});
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
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
    
    /**
     * 
     * Update Employee under a specific Client
     * 
     * [HTTP Method = GET]
     * 
     * @param _id
     * @param empId
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param telephone
     * @return
     * 
     * mongo plain query:
     * 	db.clients.update({"_id": 2},  {$addToSet : { "employees" : [ {"name"  : "jay", "position" : "senior dev"}, {"name"  : "heejun", "position" : "manager"}] }});
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
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
    
    /**
     * 
     * Update Employee under a specific Client
     * 
     * [HTTP Method : GET]
     * 
     * @param _id
     * @param employee
     * @return
     * 
     * mongo plain query:
     * 	db.clients.update({"_id": 2},  {$addToSet : { "employees" : [ {"name"  : "jay", "position" : "senior dev"}, {"name"  : "heejun", "position" : "manager"}] }});
	 * 
	 * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
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
    
    /**
     * 
     * Delete Employee under a specific Client
     * 
     * [HTTP Method : GET]
     * 
     * @param _id
     * @param empId
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/{_id}/remove/employee?empId={emp_id}
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
    
    /**
     * 
     * Update Client by id
     * 
     * [HTTP Method : GET]
     * 
     * @param _id
     * @param name
     * @param address
     * @param city
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/update
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
    
    /**
     * test API
     * 
     * [HTTP Method : GET]
     * 
     * @return
     * 
     * possible url:
	 * 		http://localhost:3030/drivercheck/api/client/
     */
    @RequestMapping(value = "/client/test", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> testAPI() {
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("id", "230");
    	map.put("name", "Nothing Decided");
    	map.put("city", "Toronto");
    	
    	return map;
    }
    
    /**
     * Show All Tests
     * 
     * [HTTP Method : GET]
     * 
     * @return
     */
    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public @ResponseBody Collection<TestResult> showAllTests() {
    	Collection<Client> clients = this.clientService.findAll();
    	
    	List<TestResult> testAll = new LinkedList<TestResult>();
    	for (Client client : clients) {
			List<Employee> employees = client.getEmployees();
			
			for (Employee employee : employees) {
				List<TestResult> tests = employee.getTests();
				testAll.addAll(tests);				
			}
		}
    	return testAll;
    }
}
