package org.driver.check.repository;

import org.springframework.stereotype.Repository;

@Repository("ClientRepositoryCustom")
public interface ClientRepositoryCustom {
	public void pushMethod(String objectId, Object... events);
}	