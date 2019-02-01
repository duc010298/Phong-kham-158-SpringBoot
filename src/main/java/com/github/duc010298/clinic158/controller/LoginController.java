package com.github.duc010298.clinic158.controller;

import com.github.duc010298.clinic158.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    private CustomerRepository customerRepository;

//    TODO: by test
    @Autowired
    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String login() {
        Date currentDate = new Date();
        long millisecondsInOneYear = (long) 3600000;
        Date oneYearBefore = new Date(currentDate.getTime() - millisecondsInOneYear);
        customerRepository.deleteCustomerBeforeDay(oneYearBefore);
        return "login";
    }

}
