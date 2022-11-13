package com.shivam.invoice.controllers;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.invoice.helper.ResponseMessage;
import com.shivam.invoice.model.UserInfo;
import com.shivam.invoice.services.EmailService;
import com.shivam.invoice.services.InvoiceService;

import freemarker.template.TemplateException;

@RestController
public class InvoiceController {
    
    @Autowired
    public EmailService emailService;
    
    @Autowired
    public ResponseMessage responseMessage;

    @PostMapping("/mail-invoice")
    public ResponseEntity<?> sendInvoice(@RequestBody UserInfo[] userInfo){
        try {
            return emailService.sendEmail(userInfo);
        } catch (MessagingException | IOException | TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
        
    }
}
