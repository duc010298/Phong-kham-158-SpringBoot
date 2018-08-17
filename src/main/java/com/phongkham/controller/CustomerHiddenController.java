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
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(path = "/CustomerHidden")
public class CustomerHiddenController {

    private CustomerHiddenRepository customerHiddenRepository;
    private JavaMailSender javaMailSender;
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    public CustomerHiddenController(CustomerHiddenRepository customerHiddenRepository, JavaMailSender javaMailSender,
                                    SpringTemplateEngine springTemplateEngine) {
        this.customerHiddenRepository = customerHiddenRepository;
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCustomer(@RequestBody CustomerEntity customerEntity) throws MessagingException {
        customerHiddenRepository.addCustomer(customerEntity);
        //send mail
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        IContext context = new Context();
        ((Context) context).setVariable("Content", customerEntity.getReport());
        String htmlMsg = springTemplateEngine.process("mailForm", context);

        mimeMessageHelper.setFrom("phongkham158@gmail.com");
        mimeMessageHelper.setTo("duc010298@gmail.com");
        mimeMessageHelper.setSubject("Test subject");
        mimeMessageHelper.setText(htmlMsg, true);

        javaMailSender.send(mimeMessage);
    }

}
