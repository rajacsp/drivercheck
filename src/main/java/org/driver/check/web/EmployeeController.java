package org.driver.check.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.driver.check.model.Employee;
import org.driver.check.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes(types = Employee.class)
public class EmployeeController {

    private final ClinicService clinicService;


    @Autowired
    public EmployeeController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String processFindForm(Employee employee, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /employees to return all records
        if (employee.getLastName() == null) {
            employee.setLastName(""); // empty string signifies broadest possible search
        }

        // find employees by last name
        Collection<Employee> results = this.clinicService.findEmployeeByLastName(employee.getLastName());
        if (results.size() < 1) {
            // no employees found
            result.rejectValue("lastName", "notFound", "not found");
            return "employees/findemployees";
        }
        if (results.size() > 1) {
            // multiple employees found
            model.put("selections", results);
            return "employees/employeesList";
        } else {
            // 1 employee found
            employee = results.iterator().next();
            return "redirect:/employees/" + employee.getId();
        }
    }
}
