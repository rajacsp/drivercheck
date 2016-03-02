package org.driver.check.repository;

import org.driver.check.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

public class ClientRepositoryCustomImpl implements ClientRepositoryCustom {
	
	@Autowired
    protected MongoTemplate mongoTemplate;

	@Override	
	public void pushMethod(String objectId, Object... employees) {
		mongoTemplate.updateFirst(
	            Query.query(Criteria.where("clientId").is(objectId)), 
	            new Update().pushAll("employees", employees), Client.class);		
	}
}
