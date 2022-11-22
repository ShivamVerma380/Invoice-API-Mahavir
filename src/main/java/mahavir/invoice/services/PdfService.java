package mahavir.invoice.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfService {
    
    private Logger logger = LoggerFactory.getLogger(PdfService.class);

    public ByteArrayInputStream createPdf(){

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

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20,Font.BOLD);

        Paragraph titlePara = new Paragraph(title,titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(titlePara);

        Font paraFont = FontFactory.getFont(FontFactory.HELVETICA,18,Font.NORMAL);

        Paragraph para = new Paragraph(content,paraFont);
        para.add(new Chunk("Added After"));
        document.add(para);


        document.close();

        sendEmail(outputStream.toByteArray());


        logger.info("Create pdf ended...");


        return new ByteArrayInputStream(outputStream.toByteArray());

    }

    public void sendEmail(byte []arr){
        String smtpHost = "smtp.gmail.com"; //replace this with a valid host
        int smtpPort = 465; //replace this with a valid port

        String sender = "edatamahavir@gmail.com"; //replace this with a valid sender email address
        String recipient = "shivamvermasv380@gmail.com"; //replace this with a valid recipient email address
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
            pdfBodyPart.setFileName("test.pdf");
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
