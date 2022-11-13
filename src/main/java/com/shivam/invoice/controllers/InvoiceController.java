package com.shivam.invoice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.invoice.services.InvoiceService;

@RestController
public class InvoiceController {
    
    @Autowired
    public InvoiceService iService;

    @PostMapping("/mail-invoice")
    public ResponseEntity<?> sendInvoice(){
        return iService.sendInvoice();
    }
}
