package models;

import Main.main;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail {

    protected Session mailSession;

    public void login(String host, String smtpPort){


        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.socketFactory.port", smtpPort);
        properties.put("mail.smtp.SocketFactory.class", "jacax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", smtpPort);

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Login.p.get_nachname(), Login.p.get_passwort());
            }
        };

        this.mailSession = Session.getInstance(properties, auth);
        System.out.println("Sie wurden erfolgreich eingeloggt!");
    }

    public void send(String senderMail, String senderName, String subject, String message) throws MessagingException, UnsupportedEncodingException {
        if (mailSession == null) {
            throw new IllegalStateException("Sie müssen sich zuerst einloggen");
        }
        MimeMessage msg = new MimeMessage(mailSession);
        msg.addHeader("Content-Type", "test/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(senderMail, senderName));
        msg.setReplyTo(InternetAddress.parse(senderMail, false));
        msg.setSubject(subject, "UTF-8");
        msg.setText(message, "UTF-8");
        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Login.p.get_email(), false));
        System.out.println("Versende Email ...");
        System.out.println("Email konnte NICHT versendet werden!");
        /*
        try {
            Transport.send(msg);
            System.out.println("Email wurde erfolgreich versendet!");
        } catch (Exception e) {
            System.out.println("Email konnte Nicht versendet werden!");
        }

         */

    }
}
