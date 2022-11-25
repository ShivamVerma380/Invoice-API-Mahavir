package mahavir.invoice.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.plaf.ColorUIResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import mahavir.invoice.model.UserInfo;

@Component
public class PdfService {
    
    private Logger logger = LoggerFactory.getLogger(PdfService.class);

    public ByteArrayInputStream createPdf(UserInfo userInfo){

        logger.info("Create pdf started...");

        String title = "Mahavir Invoice Api Generation";
        String content = "API for generating invoices and sending on email";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();

        PdfWriter.getInstance(document, outputStream);

        HeaderFooter footer = new HeaderFooter(true, new Phrase(". Invoice"));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorderWidthBottom(0);
        document.setFooter(footer);


        document.open();

        try {
            Image img = Image.getInstance(new URL("https://i.ibb.co/XLQJ6vd/Screenshot-2022-11-13-135108.png"));
            img.scaleToFit(483, 100);
            document.add(img);
        } catch(Exception e){
            e.printStackTrace();
        }

        // Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20,Font.BOLD);

        // Paragraph titlePara = new Paragraph(title,titleFont);
        // titlePara.setAlignment(Element.ALIGN_CENTER);
        // document.add(titlePara);

        // Font paraFont = FontFactory.getFont(FontFactory.HELVETICA,18,Font.NORMAL);

        // Paragraph para = new Paragraph(content,paraFont);
        // para.add(new Chunk("Added After"));
        // document.add(para);

        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.BOLD);
        Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,6);
        Table table = new Table(3);
        // table.setBorderWidth(1);
        // table.setBorderColor(new ColorUIResource(0, 0, 255));
        table.setPadding(3);
        table.setCellsFitPage(true);
        table.setTableFitsPage(true);
        // table.setWidths(;
        table.setWidth(92);
        table.setAlignment(Element.ALIGN_LEFT);
        // table.setLeft(0);
        // table.setSpacing(0);
        // table.setWidth(483);

        Cell cell = new Cell(new Paragraph("INVOICE NUMBER",titleFont));
        cell.setHeader(true);
        cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        cell = new Cell(new Paragraph("ORDER DATE",titleFont));
        cell.setHeader(true);
        cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        cell = new Cell(new Paragraph("ORDER NUMBER",titleFont));
        cell.setHeader(true);
        cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        table.endHeaders();

        cell = new Cell(new Paragraph(userInfo.getInvoiceId(),bodyFont));
        cell.setColspan(1);
        table.addCell(cell);

        cell = new Cell(new Paragraph(userInfo.getOrderDate(),bodyFont));
        cell.setColspan(1);
        table.addCell(cell);

        cell = new Cell(new Paragraph(userInfo.getOrderNumber(),bodyFont));
        cell.setColspan(1);
        table.addCell(cell);

        cell = new Cell(new Paragraph("Bill To:\n"+userInfo.getBillingAddress()+"\n"+userInfo.getBillingCity()+"-"+userInfo.getBillingPincode()+"\n"+userInfo.getBillingState(),bodyFont));
        table.addCell(cell);
        
        cell = new Cell(new Paragraph("Ship To:\n"+userInfo.getShippingAddress()+"\n"+userInfo.getShippingCity()+"-"+userInfo.getShippingPincode()+"\n"+userInfo.getShippingState(),bodyFont)); 
        table.addCell(cell);

        document.add(table);

        // document.add(new Paragraph("\n"));

        // titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.BOLD);
        // Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,6);
        table = new Table(7);
        // table.setBorderWidth(1);
        // table.setBorderColor(new ColorUIResource(0, 0, 255));
        table.setPadding(3);
        table.setCellsFitPage(true);
        table.setTableFitsPage(true);
        // table.setWidths(;
        table.setWidth(92);
        table.setAlignment(Element.ALIGN_LEFT);

        float[] widths = {1,3,2,1,1,2,1};
        table.setWidths(widths);

        cell = new Cell(new Paragraph("SR.No.",titleFont));
        cell.setHeader(true);
        // cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        cell = new Cell(new Paragraph("PRODUCTS DETAILS",titleFont));
        cell.setHeader(true);
        // cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        cell = new Cell(new Paragraph("HSN Code",titleFont));
        cell.setHeader(true);
        // cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        cell = new Cell(new Paragraph("GST%",titleFont));
        cell.setHeader(true);
        // cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        cell = new Cell(new Paragraph("QTY",titleFont));
        cell.setHeader(true);
        // cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        cell = new Cell(new Paragraph("RATE",titleFont));
        cell.setHeader(true);
        // cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        cell = new Cell(new Paragraph("AMOUNT",titleFont));
        cell.setHeader(true);
        // cell.setColspan(1);
        cell.setBackgroundColor(ColorUIResource.GRAY);
        table.addCell(cell);

        table.endHeaders();

        for(int i=0;i<userInfo.getProducts().size();i++){
            cell = new Cell(new Paragraph(String.valueOf(i+1),bodyFont));
            // cell.setColspan(1);
            table.addCell(cell);

            String str = userInfo.getProducts().get(i).getName()+"\n"+"Serial Nos:"+userInfo.getProducts().get(i).getSerialNos()+"\n"+"Sales Person:"+userInfo.getProducts().get(i).getSalesPerson()+"\n"+"Delivery Type:"+userInfo.getProducts().get(i).getDeliveryType()+"\n"+"Transporter Name:"+userInfo.getProducts().get(i).getTransporterName()+"\n"+"Mobile Number:"+userInfo.getProducts().get(i).getMobileNumber();

            cell = new Cell(new Paragraph(str,bodyFont));
            // cell.setColspan(1);
            table.addCell(cell);

            cell = new Cell(new Paragraph(userInfo.getProducts().get(i).getHsnCode(),bodyFont));
            // cell.setColspan(1);
            table.addCell(cell);

            cell = new Cell(new Paragraph(userInfo.getProducts().get(i).getGst(),bodyFont));
            // cell.setColspan(1);
            table.addCell(cell);

            cell = new Cell(new Paragraph(userInfo.getProducts().get(i).getQuantity(),bodyFont));
            // cell.setColspan(1);
            table.addCell(cell);

            cell = new Cell(new Paragraph(userInfo.getProducts().get(i).getRate(),bodyFont));
            // cell.setColspan(1);
            table.addCell(cell);

            cell = new Cell(new Paragraph(userInfo.getProducts().get(i).getAmount(),bodyFont));
            // cell.setColspan(1);
            table.addCell(cell);
        }

        document.add(table);

        try {
            Image img = Image.getInstance(new URL("https://i.ibb.co/WymynFj/Screenshot-2022-11-13-150654.png"));
            img.scaleToFit(483, 100);
            document.add(img);
        } catch(Exception e){
            e.printStackTrace();
        }


        document.close();

        sendEmail(outputStream.toByteArray(),userInfo);


        logger.info("Create pdf ended...");


        return new ByteArrayInputStream(outputStream.toByteArray());

    }

    public void sendEmail(byte []arr,UserInfo userInfo){
        String smtpHost = "smtp.gmail.com"; //replace this with a valid host
        int smtpPort = 465; //replace this with a valid port

        String sender = "edatamahavir@gmail.com"; //replace this with a valid sender email address
        String recipient = userInfo.getEmail(); //replace this with a valid recipient email address
        String content = "Dear Customer,\nThank you for shopping with Mahavir Electronics."; //this will be the text of the email
        String subject = "Mahavir Invoice API"; //this will be the subject of the email

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);     
        properties.put("mail.smtp.ssl.enable", "true"); // security purposes
        properties.put("mail.smtp.auth", "true"); //for authentication
        
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(sender, "jiecciwfawjoonam");
            }

        });

        session.setDebug(true);
        // session.setTLS(true);
        //construct the pdf body part
        DataHandler dataSource = new DataHandler(arr, "application/pdf");

        MimeBodyPart pdfBodyPart = new MimeBodyPart();
        try {
            pdfBodyPart.setDataHandler(dataSource);
            String str = "Invoice-"+userInfo.getName()+".pdf";
            pdfBodyPart.setFileName(str);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        //construct the mime multi part
        MimeMultipart mimeMultipart = new MimeMultipart();

        
        try {
            MimeBodyPart texBodyPart = new MimeBodyPart();
            texBodyPart.setText(content);
            mimeMultipart.addBodyPart(texBodyPart);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {
            mimeMultipart.addBodyPart(pdfBodyPart);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        //create the sender/recipient addresses
        
        InternetAddress iaRecipient=null;
        InternetAddress iaSender=null;
        try {
            iaSender = new InternetAddress(sender);
            iaRecipient = new InternetAddress(recipient);
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //construct the mime message
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        //send off the email
        try {
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        logger.info("Mail Sent......");
    }
}
