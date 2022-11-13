package com.shivam.invoice.helper;

import java.io.File;
import java.nio.file.FileSystems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.itextpdf.commons.utils.Base64.OutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Component
public class GeneratePdf {

    @Autowired
    private ResponseMessage responseMessage;

    // @Value("${invoice.pdf.path}")
    
    // private String destination = "src\\main\\resources".toUri();

    // File file = new File("src\\main\\resources\\invoice.jpg");
   
    public ResponseEntity<?> generateInvoice(String name) {
        try {
            // PdfWriter pdfWriter = new PdfWriter(destination);
            
            // //Creating a pdf document
            // PdfDocument pdfDoc = new PdfDocument(pdfWriter);

            // //Adding an empty page
            // pdfDoc.addNewPage();


            // //Creating a document object
            // Document document = new Document(pdfDoc);

            // //Closing the document
            // document.close();

            
            responseMessage.setMessage("Invoice generated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
    
}
