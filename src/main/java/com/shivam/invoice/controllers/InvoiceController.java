package com.shivam.invoice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {
    

    @PostMapping("/mail-invoice")
    public ResponseEntity<?> sendInvoice(){
        return ResponseEntity.ok("Invoice sent successfully");
    }
}
