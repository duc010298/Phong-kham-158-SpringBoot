package com.phongkham.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestMailController {

    private JavaMailSender javaMailSender;

    @Autowired
    public TestMailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @GetMapping
    public String test() throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("duc010298@gmail.com");
        simpleMailMessage.setFrom("phongkham158@gmail.com");
        simpleMailMessage.setSubject("Test! This is Subject");
        simpleMailMessage.setText("Test! This is text");

        javaMailSender.send(simpleMailMessage);
        return "Mail Sended";
    }

}
