package com.phongkham.controller;

import com.phongkham.dao.CustomerHiddenRepository;
import com.phongkham.model.CustomerEntity;
import com.phongkham.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/CustomerHidden")
public class CustomerHiddenController {

    private CustomerHiddenRepository customerHiddenRepository;
    private SendMail sendMail;

    @Autowired
    public CustomerHiddenController(CustomerHiddenRepository customerHiddenRepository, SendMail sendMail) {
        this.customerHiddenRepository = customerHiddenRepository;
        this.sendMail = sendMail;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCustomer(@RequestBody CustomerEntity customerEntity) {
        customerHiddenRepository.addCustomer(customerEntity);
        //send mail
        String From = "phongkham158@gmail.com";
        String To = "duc010298@gmail.com";
        String Subject = "This is subject";
        sendMail.sendMailHtml(customerEntity, From, To, Subject);
    }

}
