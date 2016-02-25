package org.driver.check.rest;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.driver.check.model.TestResult;
import org.driver.check.service.TestResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestResultRestController {
	
    private final TestResultService testResultService;

    @Autowired
    public TestResultRestController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public @ResponseBody Collection<TestResult> findAll() {    	
        return this.testResultService.findAll();
    }
    
    // delete [Method = GET]
    @RequestMapping(value = "/test/delete/{tid}", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> deleteClientByClientId(@PathVariable("tid") Integer testId) {
    	
    	testResultService.deleteTestByTestId(testId);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // delete [Method = DELETE]
    @RequestMapping(value = "/test/delete/{tid}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, String> deleteClientByClientId1(@PathVariable("tid") Integer testId) {
    	
    	testResultService.deleteTestByTestId(testId);
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // add test [Method = GET]
    @RequestMapping(value = "/test/add", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> addClient(    		
    		@RequestParam("test_id") int testId
    		) {
    	
    	testResultService.addTest(testId, new Date());
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // update test [Method = GET]
    @RequestMapping(value = "/test/update", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> updateClient(    		
    		@RequestParam("test_id") int testId
    		) {
    	
    	testResultService.updateTest(testId, new Date());
    	
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	map.put("SUCCESS", "OK");
    	
    	return map;
    }
    
    // Sort tests by newest/oldest
    // newest
    @RequestMapping(value = "/tests/newest", method = RequestMethod.GET)
    public @ResponseBody Collection<TestResult> findNewest() {    	
        return this.testResultService.findNewest();
    }
    
    // oldest
    @RequestMapping(value = "/tests/oldest", method = RequestMethod.GET)
    public @ResponseBody Collection<TestResult> findOldest() {    	
        return this.testResultService.findOldest();
    }
    
    // Add an option to calculate the "pass" rate per client (% of passed tests)
    
    // Filter global tests by date range
}
