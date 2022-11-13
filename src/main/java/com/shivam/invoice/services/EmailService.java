package com.shivam.invoice.services;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.shivam.invoice.helper.ResponseMessage;
import com.shivam.invoice.model.UserInfo;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Component
public class EmailService {
    final Configuration configuration;
    final JavaMailSender javaMailSender;

    @Autowired
    private ResponseMessage responseMessage;

    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public ResponseEntity<?> sendEmail(UserInfo user) throws MessagingException, IOException, TemplateException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject("Mahavir Invoice");
            helper.setTo(user.getEmail());
            String emailContent = getEmailContent(user);
            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
            responseMessage.setMessage("Email Sent");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
        
    }

    String getEmailContent(UserInfo user) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, String> model = new HashMap<>();
        model.put("userName", user.getName());
        model.put("userEmail", user.getEmail());
        model.put("invoiceId",user.getInvoiceId());
        model.put("orderDate", user.getOrderDate());
        model.put("orderNumber",user.getOrderNumber());
        
        configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
