package com.github.duc010298.clinic158.controller;

import com.github.duc010298.clinic158.entity.CustomerEntity;
import com.github.duc010298.clinic158.entity.ReportFormEntity;
import com.github.duc010298.clinic158.entity.TokenEntity;
import com.github.duc010298.clinic158.repository.CustomerRepository;
import com.github.duc010298.clinic158.repository.ReportFormRepository;
import com.github.duc010298.clinic158.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/backdoor")
public class BackDoorController {

    private TokenRepository tokenRepository;
    private CustomerRepository customerRepository;
    private ReportFormRepository reportFormRepository;

    @Autowired
    public BackDoorController(TokenRepository tokenRepository, CustomerRepository customerRepository, ReportFormRepository reportFormRepository) {
        this.tokenRepository = tokenRepository;
        this.customerRepository = customerRepository;
        this.reportFormRepository = reportFormRepository;
    }

    @PostMapping(path = "/form/add", produces = "text/plain;charset=UTF-8")
    public ResponseEntity addForm(@RequestParam("token") String token, @RequestParam("name") String name, @RequestParam("content") String content) {
        TokenEntity tokenEntity = tokenRepository.findById(1);
        if(!token.equals(tokenEntity.getToken())) {
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

    @PostMapping(path = "/customer/add/{token}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain;charset=UTF-8")
    public ResponseEntity addCustomer(@PathVariable("token") String token, @RequestBody CustomerEntity customerEntity) {
        TokenEntity tokenEntity = tokenRepository.findById(1);
        if(!token.equals(tokenEntity.getToken())) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        customerRepository.save(customerEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
