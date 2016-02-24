package org.driver.check.rest;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.driver.check.model.Client;
import org.driver.check.model.Employee;
import org.driver.check.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public @ResponseBody Collection<Employee> findAll() {
        return this.employeeService.findAll();
    }
}
