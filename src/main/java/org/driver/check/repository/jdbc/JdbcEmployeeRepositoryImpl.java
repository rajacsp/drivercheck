
package org.driver.check.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.driver.check.model.Employee;
import org.driver.check.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcEmployeeRepositoryImpl implements EmployeeRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertEmployee;

    @Autowired
    public JdbcEmployeeRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.insertEmployee = new SimpleJdbcInsert(dataSource)
                .withTableName("employees")
                .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Override
    public Collection<Employee> findByLastName(String lastName) throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lastName", lastName + "%");
        List<Employee> employees = this.namedParameterJdbcTemplate.query(
                "SELECT id, first_name, last_name, address, city, telephone FROM employees WHERE last_name like :lastName",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Employee.class)
        );
        return employees;
    }

    
    @Override
    public Employee findById(int id) throws DataAccessException {
        Employee employee;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            employee = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, first_name, last_name, address, city, telephone FROM employees WHERE id= :id",
                    params,
                    ParameterizedBeanPropertyRowMapper.newInstance(Employee.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Employee.class, id);
        }
        return employee;
    }

	@Override
	public Collection<Employee> findAll() throws DataAccessException {
		Map<String, Object> params = new HashMap<String, Object>();
        List<Employee> employees = this.namedParameterJdbcTemplate.query(
                "SELECT id, first_name, last_name, address, city, telephone FROM employees",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Employee.class)
        );
        return employees;
	}
}
