package com.acculytixs.wayuparty.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.acculytixs.wayuparty.entity.email.EmailMessages;

@Component
public class EmailUtil {
	
	@Value("${appUrl}")
	private String appUrl;

	public void sendTemplateEmail(EmailMessages emailMessages) {
		
		try {
			 InternetAddress[] iAdressArray = InternetAddress.parse(emailMessages.getToEmail());
			 String emailTemplate = EmailTemplateHTMLUtil.convertBodyOfEmailTemplateToHTML(appUrl, emailMessages);
			 sendEmail(emailMessages, emailTemplate, iAdressArray);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(EmailMessages emailMessages, String emailTemplate, InternetAddress[] iAdressArray) throws Exception{
		

		Properties props = new Properties();
		final String username = emailMessages.getFromEmail().trim(); // should be from email of the System.
		final String password = emailMessages.getFromPassword().trim();// should be password of from email of the System.

		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailMessages.getSmtphost());
		props.put("mail.smtp.port", emailMessages.getPort().toString());

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailMessages.getFromEmail(), emailMessages.getFromName()));
			message.setRecipients(Message.RecipientType.TO, iAdressArray);

			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setSubject(emailMessages.getSubject());
			MimeMultipart mimeMultipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(emailTemplate, "text/html");
			mimeMultipart.addBodyPart(messageBodyPart);
			message.setContent(mimeMultipart);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
