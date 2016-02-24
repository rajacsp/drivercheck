package org.driver.check.rest;

import java.util.Collection;

import org.driver.check.model.Employee;
import org.driver.check.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final ClinicService clinicService;

    @Autowired
    public EmployeeRestController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public @ResponseBody Collection<Employee> findAll() {
        return this.clinicService.findAll();
    }
}
