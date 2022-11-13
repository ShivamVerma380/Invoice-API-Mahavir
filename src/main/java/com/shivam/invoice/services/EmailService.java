package com.shivam.invoice.services;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    public ResponseEntity<?> sendEmail(UserInfo[] userList) throws MessagingException, IOException, TemplateException {
        try {
            for(UserInfo user: userList){
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setSubject("Mahavir Invoice");
                helper.setTo(user.getEmail());
                String emailContent = getEmailContent(user);
                helper.setText(emailContent, true);
                // File file = new File(emailContent);
                // // Byte
                // // helper.addAttachment(emailContent,null);
                // helper.addAttachment("invoice",file);
                // Session session = Session.getInstance(propert, null)

                

                javaMailSender.send(mimeMessage);
            }


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
        Map<String, Object> model = new HashMap<>();
        model.put("user",user);
        // model.put("userName", user.getName());
        // model.put("userEmail", user.getEmail());
        // model.put("invoiceId",user.getInvoiceId());
        // model.put("orderDate", user.getOrderDate());
        // model.put("orderNumber",user.getOrderNumber());
        // model.put("billingAddress",user.getBillingAddress());
        // model.put("billingCity",user.getBillingCity());
        // model.put("billingPincode",user.getBillingPincode());
        // model.put("billingState",user.getBillingState());
        // model.put("shippingAddress",user.getShippingAddress());
        // model.put("shippingCity",user.getShippingCity());
        // model.put("shippingPincode",user.getShippingPincode());
        // model.put("shippingState",user.getShippingState());
        // model.put("products",user.getProducts());
        
        configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
