package com.phongkham.controller;

import com.phongkham.dao.CustomerHiddenRepository;
import com.phongkham.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping(path = "/CustomerHidden")
public class CustomerHiddenController {

    private CustomerHiddenRepository customerHiddenRepository;
    private JavaMailSender javaMailSender;

    @Autowired
    public CustomerHiddenController(CustomerHiddenRepository customerHiddenRepository, JavaMailSender javaMailSender) {
        this.customerHiddenRepository = customerHiddenRepository;
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCustomer(@RequestBody CustomerEntity customerEntity) throws MessagingException {
        customerHiddenRepository.addCustomer(customerEntity);
        //send mail
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String htmlMsg = "<!DOCTYPE html><html> <head> <style>body{background-color: #F0F0F0;}.page{width: 46.5625rem;" +
                " margin: 3.125rem auto 1.25rem auto; background-color: #fff; overflow: hidden; box-shadow: 0.3125rem" +
                " 0.3125rem 0.3125rem #7F7979; font-family: \"Times New Roman\", Times, serif;}.page-container{margin:" +
                " 1.875rem 2.8125rem;}.page-container table, td{border: 1px solid #F5E8E8; border-collapse: collapse;}" +
                ".page-container td{padding: 0.3125rem;}</style> </head> <body><div class=\"page\"> <div class=\"page-" +
                "container\"> <div>";
        htmlMsg += customerEntity.getReport();
        htmlMsg += "</div></div></div></body></html>";

        helper.setFrom("phongkham158@gmail.com");
        helper.setTo("ducpanzer@gmail.com");
        helper.setSubject("Báo cáo");
        helper.setText(htmlMsg, true);

        javaMailSender.send(message);
    }

}
