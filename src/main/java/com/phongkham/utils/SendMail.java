package com.phongkham.utils;

import com.phongkham.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
public class SendMail {

    private JavaMailSender javaMailSender;
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    public SendMail(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    public boolean sendMailHtml(CustomerEntity customerEntity, String fromEmail, String toEmail, String subject) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            IContext context = new Context();
            ((Context) context).setVariable("Content", customerEntity.getReport());
            String htmlMsg = springTemplateEngine.process("mailForm", context);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(htmlMsg, true);

            javaMailSender.send(mimeMessage);

            return true;
        } catch (MessagingException ex) {
            return false;
        }
    }



}
