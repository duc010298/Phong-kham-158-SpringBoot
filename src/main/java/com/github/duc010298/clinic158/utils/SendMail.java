package com.github.duc010298.clinic158.utils;


import com.github.duc010298.clinic158.entity.CustomerHiddenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

@Component
public class SendMail {

    private JavaMailSender javaMailSender;
    private SpringTemplateEngine springTemplateEngine;
    private CSSInline cssInline;
    private Environment environment;

    @Autowired
    public SendMail(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine,
                    CSSInline cssInline, Environment environment) {
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
        this.cssInline = cssInline;
        this.environment = environment;
    }

    public boolean sendMailHtml(CustomerHiddenEntity customerHiddenEntity) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            IContext context = new Context();
            ((Context) context).setVariable("Content", customerHiddenEntity.getReport());
            String htmlMsg = springTemplateEngine.process("mail", context);
            htmlMsg = cssInline.convert(htmlMsg);

            String fromEmail = environment.getProperty("spring.mail.username", "phongkham158@gmail.com");
            String toEmail = environment.getProperty("app.mail.send-to", "duc010298@gmail.com");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = simpleDateFormat.format(customerHiddenEntity.getDayVisit());

            String subject = "Vào ngày " + date + " đã có 1 ca đã được in nhưng chưa được lưu";

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

