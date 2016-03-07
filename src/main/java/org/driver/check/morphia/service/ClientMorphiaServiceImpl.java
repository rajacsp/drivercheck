package org.driver.check.morphia.service;

import java.util.Date;

import org.driver.check.business.constants.Const;
import org.driver.check.model.TestResult;
import org.driver.check.morphia.model.ClientMOM;
import org.driver.check.morphia.model.TestResultMOM;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;

@Service("clientMorphiaService")
public class ClientMorphiaServiceImpl implements ClientMorphiaService, Const {
	
	private static Logger _log = LoggerFactory.getLogger(ClientMorphiaServiceImpl.class);

	@Override
    public void addTest(final String _id, final String empId, final TestResult test){   	
    	
    	final Morphia morphia = new Morphia();
		morphia.mapPackage(MORPHIA_PACKAGE_BASE);
		MongoClient mongo = new MongoClient("localhost", 27017);		
		final Datastore datastore = morphia.createDatastore(mongo, MONGO_DB_NAME);
    	
    	final Query<ClientMOM> underPaidQuery = (Query) datastore.createQuery(ClientMOM.class).filter("employees.empId", empId);
		
		TestResultMOM testMOM = new TestResultMOM(test.getTestId(), new Date(), test.getResult());		
		final UpdateOperations<ClientMOM> updateOperations = datastore.createUpdateOperations(ClientMOM.class).add("employees.$.tests", testMOM);
		final UpdateResults results = datastore.update(underPaidQuery, updateOperations);
		
		_log.info("{addTest} results : "+results);
    }
}
