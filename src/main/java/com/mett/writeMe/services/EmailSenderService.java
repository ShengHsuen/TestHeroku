package com.mett.writeMe.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Mildred Guerra
 *
 */
public class EmailSenderService {
    private String from;
    private String to;
    private String subject;
    private String text;
 
    /**
     * @param from
     * @param to
     * @param subject
     * @param text
     */
    public EmailSenderService(String from, String to, String subject, String text){
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }
	   /**
	 * Send an email
	 */
	public void send(){
		Properties props = new Properties();
        props.put("mail.smtp.host" , "smtp.gmail.com");
        props.setProperty("mail.user" , "writeme.mett@gmail.com");

        //To use TLS
        props.put("mail.smtp.auth", "false"); 
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.password", "mett1234");
        //To use SSL
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", 
            "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        Session session  = Session.getDefaultInstance( props , new javax.mail.Authenticator(){
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(
	                "writeme.mett@gmail.com", "mett1234");// Specify the Username and the PassWord
	        }
    	});
        String to = this.to ;
        String from =   this.from ;
        String subject =  this.subject;
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, 
                new InternetAddress(to));
            msg.setSubject(subject);
            msg.setContent( this.text, "text/html; charset=UTF-8" );
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com" , 465 ,  this.from , "mett1234");
            transport.send(msg);
            System.out.println("fine!!");
        }
        catch(Exception exc) {
            System.out.println(exc);
        }
    }
}