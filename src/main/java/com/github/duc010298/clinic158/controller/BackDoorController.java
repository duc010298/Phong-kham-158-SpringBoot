package com.github.duc010298.clinic158.controller;

import com.github.duc010298.clinic158.entity.CustomerEntity;
import com.github.duc010298.clinic158.entity.ReportFormEntity;
import com.github.duc010298.clinic158.repository.CustomerRepository;
import com.github.duc010298.clinic158.repository.ReportFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/backdoor")
public class BackDoorController {

    private CustomerRepository customerRepository;
    private ReportFormRepository reportFormRepository;
    private Environment environment;

    @Autowired
    public BackDoorController(CustomerRepository customerRepository, ReportFormRepository reportFormRepository, Environment environment) {
        this.customerRepository = customerRepository;
        this.reportFormRepository = reportFormRepository;
        this.environment = environment;
    }

    @PostMapping(path = "/form/add", produces = "text/plain;charset=UTF-8")
    public ResponseEntity addForm(@RequestParam("name") String name, @RequestParam("content") String content) {
        boolean backdoor = Boolean.parseBoolean(environment.getProperty("app.config.backdoor", "false"));
        if(!backdoor) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        Integer maxOrderNumber = reportFormRepository.getMaxOrderNumber();
        maxOrderNumber = maxOrderNumber == null ? 0 : maxOrderNumber++;
        ReportFormEntity reportFormEntity = new ReportFormEntity();
        reportFormEntity.setOrderNumber(maxOrderNumber);
        reportFormEntity.setContent(content);
        reportFormEntity.setReportName(name);
        reportFormRepository.save(reportFormEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain;charset=UTF-8")
    public ResponseEntity addCustomer(@RequestBody CustomerEntity customerEntity) {
        boolean backdoor = Boolean.parseBoolean(environment.getProperty("app.config.backdoor", "false"));
        if(!backdoor) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        customerRepository.save(customerEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
