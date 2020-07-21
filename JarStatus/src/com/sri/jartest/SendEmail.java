package com.sri.jartest;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
   public void sendMail() {
     
      String to = "jaihoanu1234@gmail.com";
      String from = "jaihoanu1234@gmail.com";
      final String username = "jaihoanu1234@gmail.com";
      final String password = "anurag@1976";
      String host = "smtp.mailtrap.io";
      Properties props = new Properties();
      //props.put("mail.smtp.auth", "true");
      //props.put("mail.smtp.starttls.enable", "true");
      //props.put("mail.smtp.host", host);
      //props.put("mail.smtp.port", "9041");

      //props.put("mail.smtp.auth", "true");
      //props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "127.0.0.1");
      props.put("mail.smtp.port", "2500");
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
    }
         });

      try {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
    message.setSubject("Mail Regarding Jar Information");
    message.setText("Jar is Down or Writing in log is Stopped....Please do the needful action.");
    Transport.send(message);
    System.out.println("This is System Generated Mail");
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}