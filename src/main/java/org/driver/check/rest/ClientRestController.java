package org.driver.check.rest;

import java.util.Collection;

import org.driver.check.model.Employee;
import org.driver.check.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientRestController {

    private final EmployeeService employeeService;

    @Autowired
    public ClientRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public @ResponseBody Collection<Employee> findAll() {
        return this.employeeService.findAll();
    }
}
