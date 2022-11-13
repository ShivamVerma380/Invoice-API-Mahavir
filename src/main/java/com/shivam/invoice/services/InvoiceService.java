package com.shivam.invoice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shivam.invoice.helper.GeneratePdf;
import com.shivam.invoice.helper.ResponseMessage;

@Component
public class InvoiceService {
    
    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public GeneratePdf generatePdf;

    public ResponseEntity<?> sendInvoice(String name){
        try {
            return generatePdf.generateInvoice(name);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        
        }
    }
}
