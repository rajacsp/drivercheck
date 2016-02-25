package org.driver.check.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.business.constants.Const;
import org.driver.check.model.TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.Mongo;

@Service
public class TestResultServiceImpl implements TestResultService, Const {
	
	private static Logger _log = LoggerFactory.getLogger(TestResultServiceImpl.class);
    
    @Override
    @Transactional(readOnly = true)
    public Collection<TestResult> findAll() throws DataAccessException {
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	return mongoOps.findAll(TestResult.class);
    }    
    
    @Override
    @Transactional(readOnly = true)
    public Collection<TestResult> findNewest() throws DataAccessException {
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	
    	Query query = new Query();
    	//query.limit(10); //limit is not needed in this case; will be figured out later
    	query.with(new Sort(Sort.Direction.DESC, "testTakenDate"));    	
    	return mongoOps.find(query, TestResult.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<TestResult> findOldest() throws DataAccessException {
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	
    	Query query = new Query();
    	//query.limit(10); //limit is not needed in this case; will be figured out later
    	query.with(new Sort(Sort.Direction.ASC, "testTakenDate"));    	
    	return mongoOps.find(query, TestResult.class);
    }
    
    @Override
    public void deleteTestByTestId(final int testId) {	
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
		TestResult p = mongoOps.findOne(query(where("testId").is(testId)), TestResult.class);
		_log.info("{deleteTestByTestId} p "+p);
		mongoOps.remove(p);
	}
    
    @Override
    public void addTest(final int testId, final Date testTakenDate){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	TestResult p = new TestResult(testId, testTakenDate);
		mongoOps.insert(p);
		_log.info("{addTest}: " + p);
    }
    
    @Override
    public void updateTest(final int testId, final Date testTakenDate){
    	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), MONGO_DB_NAME));
    	mongoOps.updateFirst(query(where("testId").is(testId)), update("testTakenDate", testTakenDate), TestResult.class);		
		TestResult p = mongoOps.findOne(query(where("testId").is(testId)), TestResult.class);
		_log.info("{updateTest}: " + p);
    }
    
    // dummy test results
    private List<TestResult> getDummyTestResults(){
    	// testId, test_taken_date
    	
    	// deprecated Date is used just for testing purpose
    	TestResult test1 = new TestResult(1, new Date());
    	TestResult test2 = new TestResult(2, new Date()); 
    	TestResult test3 = new TestResult(3, new Date()); 
    	TestResult test4 = new TestResult(4, new Date()); 
    	TestResult test5 = new TestResult(5, new Date()); 
    	
    	List<TestResult> testResultList = new LinkedList<TestResult>();
    	testResultList.add(test1);
    	testResultList.add(test2);
    	testResultList.add(test3);
    	testResultList.add(test4);
    	testResultList.add(test5);
    	
    	return testResultList;
    }
}
