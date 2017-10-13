package com.isms.utils.mail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailingUtilities {

	private static String mailPrpertyFile = "mailer.properties";
	private static MimeMessage mimeMessage;
	private static Properties mailProps;
	private static Authenticator authenticator;
	
	public static void main(String[] args) throws MessagingException {
		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
		sesndMails("abc", "ccccc", "bharateeshd@gmail.com");
		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
	}
	
	private static Authenticator getAuthenticator(Properties props) {
		if(authenticator==null) {
			authenticator =  new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(props.getProperty("mail.username"),
							props.getProperty("mail.password"));
				}
			};
		}
		return authenticator;
	}

	private static Properties getMailingPrperties() {
		if (mailProps == null) {
			mailProps = new Properties();
			
				System.out.println(System.getProperty("user.dir"));
//				mailProps.load(new FileReader(mailPrpertyFile));
				mailProps.put("mail.smtp.auth", "true");
				mailProps.put("mail.smtp.starttls.enable","true");
				mailProps.put("mail.smtp.starttls", "true");
				mailProps.put("mail.smtp.host", "smtp.gmail.com");
				mailProps.put("mail.smtp.port", "587");
				mailProps.put("mail.username", "RukminiPusalkarVidyalaya.Anava@gmail.com");
				mailProps.put("mail.password", "Oxford@123");
		}
		return mailProps;
	}

	private static MimeMessage getMimeMessage(Session session, Properties prop) {
		if (mimeMessage == null) {
			mimeMessage = new MimeMessage(session);
			// creating internet address objects of from and two addresses and recepent type
			InternetAddress fromAdd;
			try {
				fromAdd = new InternetAddress(prop.getProperty("mail.username"));

			// setting from and to addresse in message object
				mimeMessage.setFrom(fromAdd);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mimeMessage;
	}

	public static void sesndMails(String subject, String content, String recipent) throws MessagingException {
		Properties props = getMailingPrperties();
		Authenticator auth = getAuthenticator(props);
		Session session = Session.getDefaultInstance(props, auth);
		Message message = getMimeMessage(session, props);
		InternetAddress toAdd = new InternetAddress(recipent);
		RecipientType type = (RecipientType) RecipientType.TO;
		message.setRecipient(type, toAdd);
		message.setSubject(subject);
		message.setText(content);
		Transport.send(message);
	}

}