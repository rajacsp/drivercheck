/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.driver.check.repository.springdatajpa;

import java.util.Collection;

import org.driver.check.model.Employee;
import org.driver.check.repository.EmployeeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA specialization of the {@link EmployeeRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface SpringDataEmployeeRepository extends EmployeeRepository, Repository<Employee, Integer> {
		
		@Override
	    @Query("SELECT DISTINCT employee FROM employee employee WHERE employee.lastName LIKE :lastName%")
	    public Collection<Employee> findByLastName(@Param("lastName") String lastName);
		
		@Override
		@Query("SELECT employee FROM employee employee WHERE employee.id =:id")
	    public Employee findById(@Param("id") int id);
		
		@Override
		@Query("SELECT employee FROM employee employee")
		public Collection<Employee> findAll();
}
