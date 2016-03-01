package org.driver.check.rest;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.driver.check.model.Client;
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
    		@RequestParam("city") String city
    		) {
    	
    	clientService.addClient(clientId, name, address, city);
    	
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
