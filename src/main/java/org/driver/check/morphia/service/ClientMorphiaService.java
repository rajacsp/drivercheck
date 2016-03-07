package org.driver.check.morphia.service;

import org.driver.check.model.TestResult;

public interface ClientMorphiaService {

	void addTest(final String _id, final String empId, final TestResult test);
}
