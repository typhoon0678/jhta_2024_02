package com.typhoon0678.jspmodel2.mail;

import jakarta.servlet.ServletContext;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;


public class NaverMail {

    private final Properties mailServerInfo;
    private final Authenticator authenticator;

    public NaverMail(ServletContext application) {
        mailServerInfo = new Properties();
        mailServerInfo.put("mail.smtp.host", "smtp.naver.com");
        mailServerInfo.put("mail.smtp.port", "465");
        mailServerInfo.put("mail.smtp.ssl.enable", "true");
        mailServerInfo.put("mail.smtp.ssl.trust", "smtp.naver.com");
        mailServerInfo.put("mail.smtp.starttls.enable", "true");
        mailServerInfo.put("mail.smtp.auth", "true");
        mailServerInfo.put("mail.smtp.debug", "true");
        mailServerInfo.put("mail.smtp.ssl.protocols", "TLSv1.3");

        authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                String naverID = application.getInitParameter("NaverID");
                String naverPW = application.getInitParameter("NaverPW");
                return new PasswordAuthentication(naverID, naverPW);
            }
        };
    }

    public void sendMail(Map<String, String> sendMailInfo) throws MessagingException {
        Session session = Session.getInstance(mailServerInfo, authenticator);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMailInfo.get("from")));
        message.addRecipients(Message.RecipientType.TO,
                String.valueOf(new InternetAddress(sendMailInfo.get("to"))));
        message.setSubject(sendMailInfo.get("subject"));
        message.setContent(sendMailInfo.get("content"), sendMailInfo.get("format"));

        Transport.send(message);
    }
}
