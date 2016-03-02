package org.driver.check.repository;

import java.util.Collection;

import org.driver.check.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("ClientRepository")
public interface ClientRepository extends MongoRepository<Client, String> {
	public Collection<Client> findByName(String name);	
}	