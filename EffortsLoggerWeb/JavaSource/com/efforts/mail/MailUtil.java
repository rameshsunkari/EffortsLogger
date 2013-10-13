package com.efforts.mail;

import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	private static final String username = "root";
	private static final String password = "root";

	Properties mailProperties = new Properties();

	public static void sendMessage(String mailTo, String ccTO, String subKey, Object[] subParams, String msgKey, Object[] msgParams) {
		Properties props = new Properties();
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "localhost");
		// props.put("mail.smtp.port", "4555");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@effortslogger.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mailTo));
			message.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(ccTO));
			message.setSubject(getMailMessage(subKey, subParams));
//			message.setText(getMailMessage(msgKey, msgParams));
			message.setContent(getMailMessage(msgKey, msgParams), "text/html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getMailMessage(String msgKey, Object[] params) {

		String msg = ResourceBundle.getBundle(
				"com.efforts.messages.MailMessage").getString(msgKey);

		return MessageFormat.format(msg, params);

	}

}
