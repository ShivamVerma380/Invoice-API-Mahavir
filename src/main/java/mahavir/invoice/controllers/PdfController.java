package mahavir.invoice.controllers;

import java.io.ByteArrayInputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mahavir.invoice.services.PdfService;

@RestController
public class PdfController {

    @Autowired
    private PdfService pdfService;
    
    @PostMapping("/pdf/generate")
    public ResponseEntity<InputStreamResource> createPdf(){
        ByteArrayInputStream pdf =  pdfService.createPdf();
        

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","inline; filename=invoice.pdf");
        
        return ResponseEntity.ok().headers(httpHeaders).contentType(org.springframework.http.MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }
}
