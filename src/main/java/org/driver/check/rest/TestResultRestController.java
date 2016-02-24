package org.driver.check.rest;

import java.util.Collection;

import org.driver.check.model.TestResult;
import org.driver.check.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestResultRestController {

    private final TestResultService testResultService;

    @Autowired
    public TestResultRestController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public @ResponseBody Collection<TestResult> findAll() {
        return this.testResultService.findAll();
    }
}
