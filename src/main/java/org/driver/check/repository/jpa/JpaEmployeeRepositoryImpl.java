
package org.driver.check.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.driver.check.model.Employee;
import org.driver.check.repository.EmployeeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    public Collection<Employee> findByLastName(String lastName) {
        Query query = this.em.createQuery("SELECT DISTINCT employee FROM employee employee WHERE employee.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        Query query = this.em.createQuery("SELECT employee FROM Employee employee WHERE employee.id =:id");
        query.setParameter("id", id);
        return (Employee) query.getSingleResult();
    }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Employee> findAll() throws DataAccessException {
		Query query = this.em.createQuery("SELECT employee FROM Employee employee");
        return query.getResultList();
	}
}
