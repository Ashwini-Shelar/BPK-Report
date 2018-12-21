package com.ln.bpk.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class SendMail {

	@Value("${serverEnvironment}")
	private String serverEnvironmentProp;

	@Value("${folderPath}")
	private String folderPathProp;

	@Value("${mail.smtp.auth}")
	private String mailSmtpAuth;

	@Value("${mail.smtp.starttls.enable}")
	private String starttlsEnable;

	@Value("${mail.smtp.starttls.required}")
	private String starttlsRequired;

	@Value("${mail.smtp.host}")
	private String host;

	@Value("${mail.smtp.port}")
	private String port;

	@Value("${mail.debug}")
	private String maildebug;

	@Value("${email}")
	private String email;

	@Value("${password}")
	private String password;

	@Value("${emailSubject}")
	private String emailSubject;

	@Value("${emailBody}")
	private String emailBody;

	@Value("${recipentTo}")
	private String recipentTo;

	@Value("${recipentCC}")
	private String recipentCC;

	@Value("${recipentBCC}")
	private String recipentBCC;

	@Value("${purgingFlag}")
	private String purgingFlagProp;

	private static final Logger logger = LoggerFactory.getLogger(SendMail.class);

	private String smtpEmail;
	private String smtpPassword;
	private Properties property;
	private Message message;
	private Properties configProp;

	public void mail(String filename) {
		
		
		Properties mailProperties = new Properties();
		
		logger.info("mailSmtpAuth = {}",mailSmtpAuth);
		logger.info("starttlsEnable = {}",starttlsEnable);
		logger.info("starttlsRequired = {}",starttlsRequired);
		logger.info("host = {}",host);
		logger.info("port = {}",port);
		logger.info("maildebug = {}",maildebug);
		logger.info("purgingFlag = {}",purgingFlagProp);
		logger.info("recipentCC = {}",recipentCC);
		logger.info("recipentBCC = {}",recipentBCC);
		logger.info("recipentTo = {}",recipentTo);
		logger.info("emailBody = {}",emailBody);
		logger.info("emailSubject = {}",emailSubject);
		logger.info("password = {}",password);
		logger.info("email = {}",email);
		
		mailProperties.put("mail.smtp.auth", mailSmtpAuth);
		mailProperties.put("mail.smtp.starttls.enable", starttlsEnable);
		mailProperties.put("mail.smtp.starttls.required", starttlsRequired);
		mailProperties.put("mail.smtp.host", host);
		mailProperties.put("mail.smtp.port", port);
		mailProperties.put("mail.debug", maildebug);

		
		this.smtpEmail = email;
		this.smtpPassword = password;
		Session session = Session.getInstance(mailProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SendMail.this.smtpEmail, SendMail.this.smtpPassword);
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(email));

			List<String> to = new ArrayList<String>();
			List<String> cc = new ArrayList<String>();
			List<String> bcc = new ArrayList<String>();
			// Email To
			String emails = recipentTo;
			if (emails != null) {
				String[] emailArr = emails.split(",");
				for (int i = 0; i < emailArr.length; i++) {
					to.add(emailArr[i]);
				}
			}
			// Email CC
			String emailsCC = recipentCC;
			if (emailsCC != null) {
				String[] emlCC = emailsCC.split(",");
				for (int i = 0; i < emlCC.length; i++) {
					cc.add(emlCC[i]);
				}
			}
			// Email BCC
			String emailsBCC = recipentBCC;
			if (emailsBCC != null) {
				String[] emlBCC = emailsBCC.split(",");
				for (int i = 0; i < emlBCC.length; i++) {
					bcc.add(emlBCC[i]);
				}
			}
			InternetAddress[] address = null;
			InternetAddress[] addressCC = null;
			InternetAddress[] addressBCC = null;
			if (!to.isEmpty()) {
				address = new InternetAddress[to.size()];
				for (int i = 0; i < to.size(); i++) {
					address[i] = new InternetAddress(to.get(i));
				}
			}

			if (!cc.isEmpty()) {
				addressCC = new InternetAddress[cc.size()];
				for (int i = 0; i < cc.size(); i++) {
					addressCC[i] = new InternetAddress(cc.get(i));
				}
			}
			if (!bcc.isEmpty()) {
				addressBCC = new InternetAddress[bcc.size()];
				for (int i = 0; i < bcc.size(); i++) {
					addressBCC[i] = new InternetAddress(bcc.get(i));
				}
			}
			if (null != address) {
				helper.setTo(address);
			}
			if (null != addressCC) {
				helper.setCc(addressCC);
			}
			if (null != addressBCC) {
				helper.setBcc(addressBCC);
			}

			helper.setSubject(emailSubject);
			helper.setText(emailBody);
			FileSystemResource file = new FileSystemResource(folderPathProp + filename);
			helper.addAttachment(file.getFilename(), file);
			Transport.send(message);

			// delete report file functionality
			String purgingFlag = purgingFlagProp;
			if (purgingFlag.equalsIgnoreCase("true")) {
				File fs = new File(folderPathProp + filename);
				if (fs.exists()) {
					if (fs.delete()) {
					} else {
					}
				}
			}

		} catch (AddressException e) {
			System.out.println("exception: " + e.getMessage());
		} catch (MessagingException e) {
			System.out.println("exception: " + e.getMessage());
		}
	}
	
	
}